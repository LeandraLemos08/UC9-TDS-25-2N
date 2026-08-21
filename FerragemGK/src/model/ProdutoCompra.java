
package model;

import java.math.BigDecimal;

public class ProdutoCompra {

    private long idProdutoCompra;
    private long idCompra;
    private Produto produto;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal subtotal;

    public ProdutoCompra() {
    }
    
    

    public long getIdProdutoCompra() {
        return idProdutoCompra;
    }

    public void setIdProdutoCompra(
            long idProdutoCompra
    ) {

        this.idProdutoCompra =
                idProdutoCompra;
    }

    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(
            long idCompra
    ) {

        this.idCompra = idCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(
            Produto produto
    ) {

        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(
            BigDecimal quantidade
    ) {

        this.quantidade = quantidade;

        calcularSubtotal();
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(
            BigDecimal valorUnitario
    ) {

        this.valorUnitario =
                valorUnitario;

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