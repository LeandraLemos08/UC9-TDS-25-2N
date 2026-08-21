
package model;

import java.math.BigDecimal;

public class ProdutoVenda {

    private long idProdutoVenda;
    private long idVenda;
    private Produto produto;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal subtotal;

    public ProdutoVenda() {
    }

    public long getIdProdutoVenda() {
        return idProdutoVenda;
    }

    public void setIdProdutoVenda(long idProdutoVenda) {
        this.idProdutoVenda = idProdutoVenda;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        calcularSubtotal();
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    private void calcularSubtotal() {

        if (
                quantidade != null
                && valorUnitario != null
        ) {

            subtotal =
                    quantidade.multiply(
                            valorUnitario
                    );
        }
    }
}
