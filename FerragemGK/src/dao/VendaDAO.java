
package dao;

import conexao.Conexao;

import model.ContaReceber;
import model.ProdutoVenda;
import model.Venda;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public long finalizarVenda(
            Venda venda,
            LocalDate primeiroVencimento
    ) {

        try (
                Connection conexao =
                        Conexao.conectar()
        ) {

            conexao.setAutoCommit(false);

            try {

                validarEstoque(
                        conexao,
                        venda
                );

                long idVenda =
                        inserirVenda(
                                conexao,
                                venda
                        );

                for (
                        ProdutoVenda item :
                        venda.getItens()
                ) {

                    inserirItem(
                            conexao,
                            idVenda,
                            item
                    );

                    baixarEstoque(
                            conexao,
                            item
                    );
                }

                if (
                        venda
                        .getFormaPagamento()
                        .equals("PARCELADO")
                ) {

                    List<ContaReceber> parcelas =
                            gerarParcelas(
                                    idVenda,
                                    venda.getValorTotal(),
                                    venda.getQuantidadeParcelas(),
                                    primeiroVencimento
                            );

                    for (
                            ContaReceber conta :
                            parcelas
                    ) {

                        inserirContaReceber(
                                conexao,
                                conta
                        );
                    }
                }

                conexao.commit();

                return idVenda;

            } catch (Exception erro) {

                conexao.rollback();

                throw new RuntimeException(
                        "A venda foi cancelada porque ocorreu um erro.",
                        erro
                );
            }

        } catch (SQLException erro) {

            throw new RuntimeException(
                    "Erro ao finalizar venda.",
                    erro
            );
        }
    }

    private void validarEstoque(
            Connection conexao,
            Venda venda
    ) throws SQLException {

        String sql =
                "SELECT descricao, estoque "
                + "FROM produto "
                + "WHERE id_produto = ? "
                + "AND ativo = TRUE "
                + "FOR UPDATE";

        for (
                ProdutoVenda item :
                venda.getItens()
        ) {

            try (
                    PreparedStatement stmt =
                            conexao.prepareStatement(sql)
            ) {

                stmt.setLong(
                        1,
                        item
                        .getProduto()
                        .getIdProduto()
                );

                try (
                        ResultSet rs =
                                stmt.executeQuery()
                ) {

                    if (!rs.next()) {

                        throw new SQLException(
                                "Produto não encontrado ou inativo."
                        );
                    }

                    BigDecimal estoqueAtual =
                            rs.getBigDecimal(
                                    "estoque"
                            );

                    if (
                            estoqueAtual.compareTo(
                                    item.getQuantidade()
                            ) < 0
                    ) {

                        throw new SQLException(
                                "Estoque insuficiente para o produto: "
                                + rs.getString(
                                        "descricao"
                                )
                        );
                    }
                }
            }
        }
    }

    private long inserirVenda(
            Connection conexao,
            Venda venda
    ) throws SQLException {

        String sql =
                "INSERT INTO venda ("
                + "id_cliente, "
                + "id_usuario, "
                + "forma_pagamento, "
                + "quantidade_parcelas, "
                + "valor_total, "
                + "status"
                + ") VALUES (?, ?, ?, ?, ?, ?) "
                + "RETURNING id_venda";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setLong(1, venda.getIdCliente());
            stmt.setLong(2, venda.getIdUsuario());
            stmt.setString(3, venda.getFormaPagamento());
            stmt.setInt(4, venda.getQuantidadeParcelas());
            stmt.setBigDecimal(5, venda.getValorTotal());
            stmt.setString(6, "FINALIZADA");

            try (
                    ResultSet rs =
                            stmt.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getLong(
                            "id_venda"
                    );
                }
            }
        }

        throw new SQLException(
                "Não foi possível gerar o código da venda."
        );
    }

    private void inserirItem(
            Connection conexao,
            long idVenda,
            ProdutoVenda item
    ) throws SQLException {

        String sql =
                "INSERT INTO produtos_venda ("
                + "id_venda, "
                + "id_produto, "
                + "quantidade, "
                + "valor_unitario, "
                + "subtotal"
                + ") VALUES (?, ?, ?, ?, ?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setLong(1, idVenda);
            stmt.setLong(
                    2,
                    item
                    .getProduto()
                    .getIdProduto()
            );
            stmt.setBigDecimal(3, item.getQuantidade());
            stmt.setBigDecimal(4, item.getValorUnitario());
            stmt.setBigDecimal(5, item.getSubtotal());

            stmt.executeUpdate();
        }
    }

    private void baixarEstoque(
            Connection conexao,
            ProdutoVenda item
    ) throws SQLException {

        String sql =
                "UPDATE produto "
                + "SET estoque = estoque - ? "
                + "WHERE id_produto = ? "
                + "AND ativo = TRUE "
                + "AND estoque >= ?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setBigDecimal(
                    1,
                    item.getQuantidade()
            );

            stmt.setLong(
                    2,
                    item
                    .getProduto()
                    .getIdProduto()
            );

            stmt.setBigDecimal(
                    3,
                    item.getQuantidade()
            );

            int linhas =
                    stmt.executeUpdate();

            if (linhas == 0) {

                throw new SQLException(
                        "Não foi possível baixar o estoque de "
                        + item
                        .getProduto()
                        .getDescricao()
                );
            }
        }
    }

    private List<ContaReceber> gerarParcelas(
            long idVenda,
            BigDecimal total,
            int quantidadeParcelas,
            LocalDate primeiroVencimento
    ) {

        List<ContaReceber> contas =
                new ArrayList<>();

        BigDecimal divisor =
                BigDecimal.valueOf(
                        quantidadeParcelas
                );

        BigDecimal valorBase =
                total.divide(
                        divisor,
                        2,
                        RoundingMode.DOWN
                );

        BigDecimal totalBase =
                valorBase.multiply(
                        divisor
                );

        BigDecimal diferenca =
                total.subtract(
                        totalBase
                );

        int ultimaParcela =
                quantidadeParcelas;

        for (
                int indice = 0;
                indice < quantidadeParcelas;
                indice++
        ) {

            BigDecimal valorParcela =
                    valorBase;

            if (
                    indice + 1
                    == ultimaParcela
            ) {

                valorParcela =
                        valorParcela.add(
                                diferenca
                        );
            }

            ContaReceber conta =
                    new ContaReceber();

            conta.setIdVenda(idVenda);
            conta.setNumeroParcela(indice + 1);
            conta.setDataEmissao(LocalDate.now());

            conta.setDataVencimento(
                    primeiroVencimento
                    .plusMonths(indice)
            );

            conta.setValorParcela(valorParcela);
            conta.setValorRecebido(BigDecimal.ZERO);
            conta.setStatus("ABERTA");

            contas.add(conta);
        }

        return contas;
    }

    private void inserirContaReceber(
            Connection conexao,
            ContaReceber conta
    ) throws SQLException {

        String sql =
                "INSERT INTO contas_receber ("
                + "id_venda, "
                + "numero_parcela, "
                + "data_emissao, "
                + "data_vencimento, "
                + "valor_parcela, "
                + "valor_recebido, "
                + "status"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setLong(1, conta.getIdVenda());
            stmt.setInt(2, conta.getNumeroParcela());

            stmt.setDate(
                    3,
                    Date.valueOf(
                            conta.getDataEmissao()
                    )
            );

            stmt.setDate(
                    4,
                    Date.valueOf(
                            conta.getDataVencimento()
                    )
            );

            stmt.setBigDecimal(5, conta.getValorParcela());
            stmt.setBigDecimal(6, conta.getValorRecebido());
            stmt.setString(7, conta.getStatus());

            stmt.executeUpdate();
        }
    }
}
