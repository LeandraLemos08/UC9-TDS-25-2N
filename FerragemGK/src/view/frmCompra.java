
package view;

import dao.CompraDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import model.Compra;
import model.Fornecedor;
import model.Produto;
import model.ProdutoCompra;

import util.SessaoUsuario;

public class frmCompra extends javax.swing.JInternalFrame {

  private final CompraDAO compraDAO =
        new CompraDAO();

  private final FornecedorDAO fornecedorDAO =
        new FornecedorDAO();

  private final ProdutoDAO produtoDAO =
        new ProdutoDAO();

  private final List<ProdutoCompra> itensCompra =
        new ArrayList<>();

  private final DecimalFormat formatoValor =
        new DecimalFormat("#,##0.00");

  private final DecimalFormat formatoQuantidade =
        new DecimalFormat("#,##0.000");

  private final DateTimeFormatter formatoData =
        DateTimeFormatter.ofPattern(
                "dd/MM/yyyy"
        );

  private BigDecimal valorTotal =
        BigDecimal.ZERO;

    
 
    public frmCompra() {
        
        initComponents();
        
        configurarTela();

        carregarFornecedores();

        carregarProdutos();

        novaCompra();
        
    }
    
    private void configurarTela() {

    txtCodigoCompra.setEditable(
            false
    );

    tblItens.setModel(
            criarModeloTabela()
    );

    tblItens.setSelectionMode(
            javax.swing.ListSelectionModel
                    .SINGLE_SELECTION
    );

    spnParcelas.setModel(
            new SpinnerNumberModel(
                    2,
                    2,
                    24,
                    1
            )
    );

    rdbAvista.setSelected(
            true
    );

    atualizarCamposPagamento();
}

    private DefaultTableModel criarModeloTabela() {

    return new DefaultTableModel(
            new Object[]{
                "Código",
                "Produto",
                "Quantidade",
                "Valor Unitário",
                "Subtotal"
            },
            0
    ) {

        @Override
        public boolean isCellEditable(
                int row,
                int column
        ) {

            return false;
        }
    };
}
    
    private void carregarFornecedores() {

    try {

        List<Fornecedor> fornecedores =
                fornecedorDAO.listarAtivos();

        DefaultComboBoxModel<Fornecedor> modelo =
                new DefaultComboBoxModel<>();

        for (
                Fornecedor fornecedor :
                fornecedores
        ) {

            modelo.addElement(
                    fornecedor
            );
        }

        cmbFornecedor.setModel(
                modelo
        );

        cmbFornecedor.setSelectedItem(
                null
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar fornecedores.\n"
                + erro.getMessage()
        );
    }
}
    
    private void carregarProdutos() {

    try {

        List<Produto> produtos =
                produtoDAO.listarAtivos();

        DefaultComboBoxModel<Produto> modelo =
                new DefaultComboBoxModel<>();

        for (
                Produto produto :
                produtos
        ) {

            modelo.addElement(
                    produto
            );
        }

        cmbProduto.setModel(
                modelo
        );

        cmbProduto.setSelectedItem(
                null
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produtos.\n"
                + erro.getMessage()
        );
    }
}
    
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtCodigoCompra = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        txtValorUnitario = new javax.swing.JTextField();
        txtVencimento = new javax.swing.JTextField();
        cmbFornecedor = new javax.swing.JComboBox<>();
        cmbProduto = new javax.swing.JComboBox<>();
        btnAdicionarProduto = new javax.swing.JButton();
        btnRemoverProduto = new javax.swing.JButton();
        lblValorTotal = new javax.swing.JLabel();
        rdbAvista = new javax.swing.JRadioButton();
        rdbPrazo = new javax.swing.JRadioButton();
        spnParcelas = new javax.swing.JSpinner();
        btnNovaCompra = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFinalizarCompra = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItens = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Movimento de Compras");

        txtCodigoCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Código da Compra:"));

        txtQuantidade.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Quantidade:"));
        txtQuantidade.addActionListener(this::txtQuantidadeActionPerformed);

        txtValorUnitario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Valor Unitário:"));
        txtValorUnitario.addActionListener(this::txtValorUnitarioActionPerformed);

        txtVencimento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Primeiro Vencimento:"));
        txtVencimento.addActionListener(this::txtVencimentoActionPerformed);

        cmbFornecedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Fornecedor:"));
        cmbFornecedor.addActionListener(this::cmbFornecedorActionPerformed);

        cmbProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Produto:"));
        cmbProduto.addActionListener(this::cmbProdutoActionPerformed);

        btnAdicionarProduto.setBackground(new java.awt.Color(102, 204, 255));
        btnAdicionarProduto.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnAdicionarProduto.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarProduto.setText("Adicionar Produto");
        btnAdicionarProduto.addActionListener(this::btnAdicionarProdutoActionPerformed);

        btnRemoverProduto.setBackground(new java.awt.Color(102, 255, 102));
        btnRemoverProduto.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnRemoverProduto.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverProduto.setText("Remover Produto");
        btnRemoverProduto.addActionListener(this::btnRemoverProdutoActionPerformed);

        lblValorTotal.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        lblValorTotal.setText("Valor Total: R$");

        buttonGroup1.add(rdbAvista);
        rdbAvista.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        rdbAvista.setText("À vista");
        rdbAvista.addActionListener(this::rdbAvistaActionPerformed);

        rdbPrazo.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        rdbPrazo.setText("A prazo");
        rdbPrazo.addActionListener(this::rdbPrazoActionPerformed);

        spnParcelas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Parcelas:"));

        btnNovaCompra.setBackground(new java.awt.Color(255, 102, 255));
        btnNovaCompra.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnNovaCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnNovaCompra.setText("Nova Compra");
        btnNovaCompra.addActionListener(this::btnNovaCompraActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnFinalizarCompra.setBackground(new java.awt.Color(102, 102, 255));
        btnFinalizarCompra.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnFinalizarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarCompra.setText("Finalizar Compra");
        btnFinalizarCompra.addActionListener(this::btnFinalizarCompraActionPerformed);

        tblItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblItens);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorTotal))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovaCompra)
                                .addGap(94, 94, 94)
                                .addComponent(btnFinalizarCompra))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdbAvista)
                                .addGap(18, 18, 18)
                                .addComponent(rdbPrazo)
                                .addGap(31, 31, 31)
                                .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoverProduto)
                        .addGap(17, 17, 17))))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnAdicionarProduto)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarProduto)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorTotal)
                    .addComponent(btnRemoverProduto))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbAvista)
                    .addComponent(rdbPrazo)
                    .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovaCompra)
                    .addComponent(btnCancelar)
                    .addComponent(btnFinalizarCompra))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void txtValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUnitarioActionPerformed

    private void txtVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVencimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimentoActionPerformed

    private void btnRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProdutoActionPerformed
        int linha =
            tblItens.getSelectedRow();

    if (linha < 0) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um produto da compra."
        );

        return;
    }

    itensCompra.remove(
            linha
    );

    atualizarTabelaItens();

    calcularTotal();

    }//GEN-LAST:event_btnRemoverProdutoActionPerformed

    private void cmbFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFornecedorActionPerformed

    private void cmbProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProdutoActionPerformed
        Produto produto =
            (Produto)
            cmbProduto.getSelectedItem();

    if (produto != null) {

        txtValorUnitario.setText(
                produto
                .getPrecoCusto()
                .toPlainString()
                .replace(".", ",")
        );
    }
    }//GEN-LAST:event_cmbProdutoActionPerformed

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        adicionarProduto();
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void rdbAvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbAvistaActionPerformed
        atualizarCamposPagamento();
    }//GEN-LAST:event_rdbAvistaActionPerformed

    private void rdbPrazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbPrazoActionPerformed
        atualizarCamposPagamento();
    }//GEN-LAST:event_rdbPrazoActionPerformed

    private void btnNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaCompraActionPerformed
        novaCompra();
    }//GEN-LAST:event_btnNovaCompraActionPerformed

    private void btnFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarCompraActionPerformed
    if (!validarCompra()) {

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja finalizar esta compra?",
                    "Finalizar Compra",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        Compra compra =
                criarCompra();

        LocalDate primeiroVencimento =
                obterPrimeiroVencimento();

        long codigo =
                compraDAO.finalizarCompra(
                        compra,
                        primeiroVencimento
                );

        JOptionPane.showMessageDialog(
                this,
                "Compra finalizada com sucesso.\n"
                + "Código: "
                + codigo
        );

        novaCompra();

        carregarProdutos();

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível finalizar a compra.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnFinalizarCompraActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja cancelar os dados desta compra?",
                    "Cancelar",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            == JOptionPane.YES_OPTION
    ) {

        novaCompra();
    }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private boolean validarCompra() {

    if (
            cmbFornecedor.getSelectedItem()
            == null
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione o fornecedor."
        );

        return false;
    }

    if (
            itensCompra.isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Adicione pelo menos um produto."
        );

        return false;
    }

    if (
            valorTotal.compareTo(
                    BigDecimal.ZERO
            ) <= 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "O total da compra deve ser maior que zero."
        );

        return false;
    }

    if (
            rdbPrazo.isSelected()
    ) {

        if (
                txtVencimento
                .getText()
                .trim()
                .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o primeiro vencimento."
            );

            return false;
        }

        try {

            LocalDate.parse(
                    txtVencimento
                    .getText()
                    .trim(),
                    formatoData
            );

        } catch (
                DateTimeParseException erro
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o vencimento no formato dd/MM/yyyy."
            );

            return false;
        }
    }

    return true;
}
    
    private Compra criarCompra() {

    Fornecedor fornecedor =
            (Fornecedor)
            cmbFornecedor.getSelectedItem();

    Compra compra =
            new Compra();

    compra.setIdFornecedor(
            fornecedor.getIdFornecedor()
    );

    compra.setIdUsuario(
            SessaoUsuario
            .getUsuarioLogado()
            .getIdUsuario()
    );

    if (
            rdbPrazo.isSelected()
    ) {

        compra.setFormaPagamento(
                "PARCELADO"
        );

        compra.setQuantidadeParcelas(
                (Integer)
                spnParcelas.getValue()
        );

    } else {

        compra.setFormaPagamento(
                "AVISTA"
        );

        compra.setQuantidadeParcelas(
                1
        );
    }

    compra.setValorTotal(
            valorTotal
    );

    compra.setStatus(
            "FINALIZADA"
    );

    for (
            ProdutoCompra item :
            itensCompra
    ) {

        compra.adicionarItem(
                item
        );
    }

    return compra;
}
    
    private LocalDate obterPrimeiroVencimento() {

    if (
            !rdbPrazo.isSelected()
    ) {

        return null;
    }

    return LocalDate.parse(
            txtVencimento
            .getText()
            .trim(),
            formatoData
    );
}
    
    private void novaCompra() {

    txtCodigoCompra.setText(
            ""
    );

    cmbFornecedor.setSelectedItem(
            null
    );

    itensCompra.clear();

    atualizarTabelaItens();

    valorTotal =
            BigDecimal.ZERO;

    calcularTotal();

    limparItem();

    rdbAvista.setSelected(
            true
    );

    spnParcelas.setValue(
            2
    );

    txtVencimento.setText(
            ""
    );

    atualizarCamposPagamento();
}
    
    private void atualizarCamposPagamento() {

    boolean parcelado =
            rdbPrazo.isSelected();

    spnParcelas.setEnabled(
            parcelado
    );

    txtVencimento.setEnabled(
            parcelado
    );

    if (!parcelado) {

        txtVencimento.setText(
                ""
        );
    }
}
    
    private void atualizarTabelaItens() {

    DefaultTableModel modelo =
            (DefaultTableModel)
            tblItens.getModel();

    modelo.setRowCount(0);

    for (
            ProdutoCompra item :
            itensCompra
    ) {

        modelo.addRow(
                new Object[]{
                    item
                    .getProduto()
                    .getIdProduto(),

                    item
                    .getProduto()
                    .getDescricao(),

                    formatoQuantidade.format(
                            item.getQuantidade()
                    ),

                    formatoValor.format(
                            item.getValorUnitario()
                    ),

                    formatoValor.format(
                            item.getSubtotal()
                    )
                }
        );
    }
}
    
    private void calcularTotal() {

    valorTotal =
            BigDecimal.ZERO;

    for (
            ProdutoCompra item :
            itensCompra
    ) {

        valorTotal =
                valorTotal.add(
                        item.getSubtotal()
                );
    }

    lblValorTotal.setText(
            "R$ "
            + formatoValor.format(
                    valorTotal
            )
    );
}
    
    private void limparItem() {

    cmbProduto.setSelectedItem(
            null
    );

    txtQuantidade.setText(
            "1,000"
    );

    txtValorUnitario.setText(
            ""
    );
}
    
    
    
    
    private BigDecimal converterDecimal(
        String texto
) {

    String valor =
            texto.trim();

    if (
            valor.contains(",")
    ) {

        valor =
                valor
                .replace(".", "")
                .replace(",", ".");
    }

    return new BigDecimal(
            valor
    );
}
    
    private void adicionarProduto() {

    Produto produto =
            (Produto)
            cmbProduto.getSelectedItem();

    if (produto == null) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um produto."
        );

        return;
    }

    try {

        BigDecimal quantidade =
                converterDecimal(
                        txtQuantidade.getText()
                );

        BigDecimal valorUnitario =
                converterDecimal(
                        txtValorUnitario.getText()
                );

        if (
                quantidade.compareTo(
                        BigDecimal.ZERO
                ) <= 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "A quantidade deve ser maior que zero."
            );

            return;
        }

        if (
                valorUnitario.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O valor unitário não pode ser negativo."
            );

            return;
        }

        adicionarOuSomarItem(
                produto,
                quantidade,
                valorUnitario
        );

        atualizarTabelaItens();

        calcularTotal();

        limparItem();

    } catch (
            NumberFormatException erro
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe quantidade e valor válidos."
        );
    }
}
    
    private void adicionarOuSomarItem(
        Produto produto,
        BigDecimal quantidade,
        BigDecimal valorUnitario
) {

    for (
            ProdutoCompra item :
            itensCompra
    ) {

        if (
                item
                .getProduto()
                .getIdProduto()
                == produto.getIdProduto()
        ) {

            BigDecimal novaQuantidade =
                    item
                    .getQuantidade()
                    .add(
                            quantidade
                    );

            item.setQuantidade(
                    novaQuantidade
            );

            item.setValorUnitario(
                    valorUnitario
            );

            return;
        }
    }

    ProdutoCompra novoItem =
            new ProdutoCompra();

    novoItem.setProduto(
            produto
    );

    novoItem.setQuantidade(
            quantidade
    );

    novoItem.setValorUnitario(
            valorUnitario
    );

    itensCompra.add(
            novoItem
    );
}
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizarCompra;
    private javax.swing.JButton btnNovaCompra;
    private javax.swing.JButton btnRemoverProduto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbFornecedor;
    private javax.swing.JComboBox<String> cmbProduto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JRadioButton rdbAvista;
    private javax.swing.JRadioButton rdbPrazo;
    private javax.swing.JSpinner spnParcelas;
    private javax.swing.JTable tblItens;
    private javax.swing.JTextField txtCodigoCompra;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValorUnitario;
    private javax.swing.JTextField txtVencimento;
    // End of variables declaration//GEN-END:variables
}
