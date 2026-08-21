package dao;

import conexao.Conexao;
import model.Compra;
import model.ContaPagar;
import model.ProdutoCompra;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Produto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

     public long finalizarCompra(
        Compra compra,
        LocalDate primeiroVencimento
) {

    try (
            Connection conexao =
                    Conexao.conectar()
    ) {

        conexao.setAutoCommit(false);

        try {

            long idCompra =
                    inserirCompra(
                            conexao,
                            compra
                    );

            for (
                    ProdutoCompra item :
                    compra.getItens()
            ) {

                inserirItem(
                        conexao,
                        idCompra,
                        item
                );

                aumentarEstoque(
                        conexao,
                        item
                );
            }

            if (
                    compra
                    .getFormaPagamento()
                    .equals("PARCELADO")
            ) {

                List<ContaPagar> parcelas =
                        gerarParcelas(
                                idCompra,
                                compra.getValorTotal(),
                                compra.getQuantidadeParcelas(),
                                primeiroVencimento
                        );

                for (
                        ContaPagar conta :
                        parcelas
                ) {

                    inserirContaPagar(
                            conexao,
                            conta
                    );
                }
            }

            conexao.commit();

            return idCompra;

        } catch (Exception erro) {

            conexao.rollback();

            throw new RuntimeException(
                    "A compra foi cancelada porque ocorreu um erro.",
                    erro
            );
        }

    } catch (SQLException erro) {

        throw new RuntimeException(
                "Erro ao finalizar compra.",
                erro
        );
    }
}
     
     private long inserirCompra(
        Connection conexao,
        Compra compra
) throws SQLException {

    String sql =
            "INSERT INTO compra ("
            + "id_fornecedor, "
            + "id_usuario, "
            + "forma_pagamento, "
            + "quantidade_parcelas, "
            + "valor_total, "
            + "status"
            + ") VALUES ("
            + "?, ?, ?, ?, ?, ?"
            + ") "
            + "RETURNING id_compra";

    try (
            PreparedStatement stmt =
                    conexao.prepareStatement(sql)
    ) {

        stmt.setLong(
                1,
                compra.getIdFornecedor()
        );

        stmt.setLong(
                2,
                compra.getIdUsuario()
        );

        stmt.setString(
                3,
                compra.getFormaPagamento()
        );

        stmt.setInt(
                4,
                compra.getQuantidadeParcelas()
        );

        stmt.setBigDecimal(
                5,
                compra.getValorTotal()
        );

        stmt.setString(
                6,
                "FINALIZADA"
        );

        try (
                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getLong(
                        "id_compra"
                );
            }
        }
    }

    throw new SQLException(
            "Não foi possível gerar o código da compra."
    );
}
     
     private void inserirItem(
        Connection conexao,
        long idCompra,
        ProdutoCompra item
) throws SQLException {

    String sql =
            "INSERT INTO produtos_compra ("
            + "id_compra, "
            + "id_produto, "
            + "quantidade, "
            + "valor_unitario, "
            + "subtotal"
            + ") VALUES (?, ?, ?, ?, ?)";

    try (
            PreparedStatement stmt =
                    conexao.prepareStatement(sql)
    ) {

        stmt.setLong(
                1,
                idCompra
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

        stmt.setBigDecimal(
                4,
                item.getValorUnitario()
        );

        stmt.setBigDecimal(
                5,
                item.getSubtotal()
        );

        stmt.executeUpdate();
    }
}
     
     private void aumentarEstoque(
        Connection conexao,
        ProdutoCompra item
) throws SQLException {

    String sql =
            "UPDATE produto "
            + "SET estoque = estoque + ? "
            + "WHERE id_produto = ? "
            + "AND ativo = TRUE";

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

        int linhas =
                stmt.executeUpdate();

        if (linhas == 0) {

            throw new SQLException(
                    "Produto não encontrado ou inativo: "
                    + item
                    .getProduto()
                    .getDescricao()
            );
        }
    }
}
     
     private List<ContaPagar> gerarParcelas(
        long idCompra,
        BigDecimal total,
        int quantidadeParcelas,
        LocalDate primeiroVencimento
) {

    List<ContaPagar> contas =
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

        ContaPagar conta =
                new ContaPagar();

        conta.setIdCompra(
                idCompra
        );

        conta.setNumeroParcela(
                indice + 1
        );

        conta.setDataEmissao(
                LocalDate.now()
        );

        conta.setDataVencimento(
                primeiroVencimento
                .plusMonths(indice)
        );

        conta.setValorParcela(
                valorParcela
        );

        conta.setValorPago(
                BigDecimal.ZERO
        );

        conta.setStatus(
                "ABERTA"
        );

        contas.add(
                conta
        );
    }

    return contas;
}
     
     private void inserirContaPagar(
        Connection conexao,
        ContaPagar conta
) throws SQLException {

    String sql =
            "INSERT INTO contas_pagar ("
            + "id_compra, "
            + "numero_parcela, "
            + "data_emissao, "
            + "data_vencimento, "
            + "valor_parcela, "
            + "valor_pago, "
            + "status"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (
            PreparedStatement stmt =
                    conexao.prepareStatement(sql)
    ) {

        stmt.setLong(
                1,
                conta.getIdCompra()
        );

        stmt.setInt(
                2,
                conta.getNumeroParcela()
        );

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

        stmt.setBigDecimal(
                5,
                conta.getValorParcela()
        );

        stmt.setBigDecimal(
                6,
                conta.getValorPago()
        );

        stmt.setString(
                7,
                conta.getStatus()
        );

        stmt.executeUpdate();
    }
}
     
     
     


}
