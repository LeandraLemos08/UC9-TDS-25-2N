
package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPagar {

    private long idContaPagar;
    private long idCompra;
    private int numeroParcela;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private BigDecimal valorParcela;
    private BigDecimal valorPago;
    private LocalDate dataPagamento;
    private String status;

    public ContaPagar() {
    }

    public long getIdContaPagar() {
        return idContaPagar;
    }

    public void setIdContaPagar(
            long idContaPagar
    ) {

        this.idContaPagar =
                idContaPagar;
    }

    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(
            long idCompra
    ) {

        this.idCompra = idCompra;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(
            int numeroParcela
    ) {

        this.numeroParcela =
                numeroParcela;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(
            LocalDate dataEmissao
    ) {

        this.dataEmissao =
                dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(
            LocalDate dataVencimento
    ) {

        this.dataVencimento =
                dataVencimento;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(
            BigDecimal valorParcela
    ) {

        this.valorParcela =
                valorParcela;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(
            BigDecimal valorPago
    ) {

        this.valorPago =
                valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(
            LocalDate dataPagamento
    ) {

        this.dataPagamento =
                dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status
    ) {

        this.status = status;
    }
}
