
package view;

import dao.ProdutoDAO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import util.SessaoUsuario;

public class frmProduto extends javax.swing.JInternalFrame {

   private final ProdutoDAO produtoDAO =
        new ProdutoDAO();

   private long idProdutoSelecionado = 0;

   private final DecimalFormat formatoValor =
        new DecimalFormat("#,##0.00");

   private final DecimalFormat formatoQuantidade =
        new DecimalFormat("#,##0.000");
    
    
    public frmProduto() {
        
        initComponents();
        
        configurarTela();

        listarProdutos();

        limparCampos();
        
    }

  
    private void configurarTela() {

    txtCodigoProduto.setEditable(false);

    btnProExcluir.setEnabled(
            SessaoUsuario.isMaster()
    );

    tblProdutos.setSelectionMode(
            javax.swing.ListSelectionModel
                    .SINGLE_SELECTION
    );

    tblProdutos.setAutoCreateRowSorter(
            true
    );

    tblProdutos.setModel(
            criarModeloTabela()
    );
}
    
    private DefaultTableModel criarModeloTabela() {

    return new DefaultTableModel(
            new Object[]{
                "Código",
                "Descrição",
                "Unidade",
                "Preço de Custo",
                "Preço de Venda",
                "Estoque",
                "Estoque Mínimo",
                "Ativo"
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
    
    private void preencherTabela(
        List<Produto> produtos
) {

    DefaultTableModel modelo =
            (DefaultTableModel)
            tblProdutos.getModel();

    modelo.setRowCount(0);

    for (
            Produto produto :
            produtos
    ) {

        modelo.addRow(
                new Object[]{
                    produto.getIdProduto(),
                    produto.getDescricao(),
                    produto.getUnidade(),
                    formatoValor.format(
                            produto.getPrecoCusto()
                    ),
                    formatoValor.format(
                            produto.getPrecoVenda()
                    ),
                    formatoQuantidade.format(
                            produto.getEstoque()
                    ),
                    formatoQuantidade.format(
                            produto.getEstoqueMinimo()
                    ),
                    produto.isAtivo()
                            ? "Sim"
                            : "Não"
                }
        );
    }
}
    
    private void listarProdutos() {

    try {

        preencherTabela(
                produtoDAO.listarTodos()
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produtos.\n"
                + erro.getMessage()
        );
    }
}
    
    private void limparCampos() {

    idProdutoSelecionado = 0;

    txtCodigoProduto.setText("");

    txtDescricao.setText("");

    cmbUnidade.setSelectedIndex(0);

    txtPrecoCusto.setText("0,00");

    txtPrecoVenda.setText("0,00");

    txtEstoqueAtual.setText("0,000");

    txtEstoqueMinimo.setText("0,000");

    chkPAtivo.setSelected(true);

    txtDescricao.requestFocus();
}
    
    private BigDecimal converterDecimal(
        String texto
) {

    String valor =
            texto
            .trim()
            .replace(".", "")
            .replace(",", ".");

    return new BigDecimal(
            valor
    );
}
    
    private boolean validarCampos() {

    if (
            txtDescricao
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe a descrição do produto."
        );

        txtDescricao.requestFocus();

        return false;
    }

    if (
            cmbUnidade.getSelectedItem() == null
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione a unidade."
        );

        cmbUnidade.requestFocus();

        return false;
    }

    try {

        BigDecimal precoCusto =
                converterDecimal(
                        txtPrecoCusto.getText()
                );

        BigDecimal precoVenda =
                converterDecimal(
                        txtPrecoVenda.getText()
                );

        BigDecimal estoque =
                converterDecimal(
                        txtEstoqueAtual.getText()
                );

        BigDecimal estoqueMinimo =
                converterDecimal(
                        txtEstoqueMinimo.getText()
                );

        if (
                precoCusto.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O preço de custo não pode ser negativo."
            );

            return false;
        }

        if (
                precoVenda.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O preço de venda não pode ser negativo."
            );

            return false;
        }

        if (
                estoque.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O estoque não pode ser negativo."
            );

            return false;
        }

        if (
                estoqueMinimo.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O estoque mínimo não pode ser negativo."
            );

            return false;
        }

    } catch (
            NumberFormatException erro
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe valores numéricos válidos."
        );

        return false;
    }

    return true;
}
    
    private Produto criarProdutoComCampos() {

    Produto produto =
            new Produto();

    produto.setIdProduto(
            idProdutoSelecionado
    );

    produto.setDescricao(
            txtDescricao
            .getText()
            .trim()
    );

    produto.setUnidade(
            cmbUnidade
            .getSelectedItem()
            .toString()
    );

    produto.setPrecoCusto(
            converterDecimal(
                    txtPrecoCusto.getText()
            )
    );

    produto.setPrecoVenda(
            converterDecimal(
                    txtPrecoVenda.getText()
            )
    );

    produto.setEstoque(
            converterDecimal(
                    txtEstoqueAtual.getText()
            )
    );

    produto.setEstoqueMinimo(
            converterDecimal(
                    txtEstoqueMinimo.getText()
            )
    );

    produto.setAtivo(
            chkPAtivo.isSelected()
    );

    return produto;
}
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabProdutos = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigoProduto = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtEstoqueAtual = new javax.swing.JTextField();
        txtPrecoCusto = new javax.swing.JTextField();
        txtPrecoVenda = new javax.swing.JTextField();
        txtEstoqueMinimo = new javax.swing.JTextField();
        cmbUnidade = new javax.swing.JComboBox<>();
        chkPAtivo = new javax.swing.JCheckBox();
        bntProCancelar = new javax.swing.JButton();
        bntProConsulta = new javax.swing.JButton();
        bntProSalvar = new javax.swing.JButton();
        bntProAlterar = new javax.swing.JButton();
        btnProExcluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbFiltrarProdutos = new javax.swing.JComboBox<>();
        txtPesquisaProdutos = new javax.swing.JTextField();
        btnProCadastro = new javax.swing.JButton();
        btnProLocalizar = new javax.swing.JButton();
        btnEstoqueBaixo = new javax.swing.JButton();
        btnProListarTodos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        btnProCarregar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro e Consulta de Produtos");

        txtCodigoProduto.setToolTipText("");
        txtCodigoProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Código:"));

        txtDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Descrição:"));

        txtEstoqueAtual.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Estoque Atual:"));
        txtEstoqueAtual.addActionListener(this::txtEstoqueAtualActionPerformed);

        txtPrecoCusto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Preço de Custo:"));

        txtPrecoVenda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Preço de Venda:"));

        txtEstoqueMinimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Estoque Mínimo:"));

        cmbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KG", "G", "L", "M", "ML", "CM", "CX", "PC", "PCT" }));
        cmbUnidade.setSelectedIndex(-1);
        cmbUnidade.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Unidade:"));

        chkPAtivo.setText("Ativo");
        chkPAtivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        bntProCancelar.setBackground(new java.awt.Color(153, 153, 255));
        bntProCancelar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        bntProCancelar.setForeground(new java.awt.Color(255, 255, 255));
        bntProCancelar.setText("Cancelar");
        bntProCancelar.addActionListener(this::bntProCancelarActionPerformed);

        bntProConsulta.setBackground(new java.awt.Color(0, 204, 204));
        bntProConsulta.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        bntProConsulta.setForeground(new java.awt.Color(255, 255, 255));
        bntProConsulta.setText("Consulta");
        bntProConsulta.addActionListener(this::bntProConsultaActionPerformed);

        bntProSalvar.setBackground(new java.awt.Color(0, 51, 204));
        bntProSalvar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        bntProSalvar.setForeground(new java.awt.Color(255, 255, 255));
        bntProSalvar.setText("Salvar");
        bntProSalvar.addActionListener(this::bntProSalvarActionPerformed);

        bntProAlterar.setBackground(new java.awt.Color(255, 153, 255));
        bntProAlterar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        bntProAlterar.setForeground(new java.awt.Color(255, 255, 255));
        bntProAlterar.setText("Alterar");
        bntProAlterar.addActionListener(this::bntProAlterarActionPerformed);

        btnProExcluir.setBackground(new java.awt.Color(204, 102, 0));
        btnProExcluir.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnProExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnProExcluir.setText("Excluir");
        btnProExcluir.addActionListener(this::btnProExcluirActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkPAtivo)
                        .addGap(49, 49, 49))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEstoqueAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(bntProConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(bntProSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(bntProAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnProExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(bntProCancelar)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkPAtivo))
                .addGap(18, 18, 18)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstoqueAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProExcluir)
                    .addComponent(bntProAlterar)
                    .addComponent(bntProCancelar)
                    .addComponent(bntProSalvar)
                    .addComponent(bntProConsulta))
                .addGap(27, 27, 27))
        );

        tabProdutos.addTab("Cadastro", jPanel1);

        cmbFiltrarProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descricao", "Unidade", "Codigo" }));
        cmbFiltrarProdutos.setSelectedIndex(-1);
        cmbFiltrarProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Filtrar por:"));

        txtPesquisaProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Pesquisa:"));

        btnProCadastro.setBackground(new java.awt.Color(255, 204, 153));
        btnProCadastro.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnProCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnProCadastro.setText("Cadastro");

        btnProLocalizar.setBackground(new java.awt.Color(102, 204, 255));
        btnProLocalizar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnProLocalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnProLocalizar.setText("Localizar");
        btnProLocalizar.addActionListener(this::btnProLocalizarActionPerformed);

        btnEstoqueBaixo.setBackground(new java.awt.Color(255, 153, 153));
        btnEstoqueBaixo.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnEstoqueBaixo.setForeground(new java.awt.Color(255, 255, 255));
        btnEstoqueBaixo.setText("Estoque Baixo");
        btnEstoqueBaixo.addActionListener(this::btnEstoqueBaixoActionPerformed);

        btnProListarTodos.setBackground(new java.awt.Color(204, 204, 255));
        btnProListarTodos.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnProListarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnProListarTodos.setText("Listar Todos");
        btnProListarTodos.addActionListener(this::btnProListarTodosActionPerformed);

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        btnProCarregar.setBackground(new java.awt.Color(255, 204, 255));
        btnProCarregar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnProCarregar.setForeground(new java.awt.Color(255, 255, 255));
        btnProCarregar.setText("Carregar");
        btnProCarregar.addActionListener(this::btnProCarregarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(cmbFiltrarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPesquisaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(471, 471, 471)
                            .addComponent(btnProLocalizar)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnProCarregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnProListarTodos)
                        .addGap(18, 18, 18)
                        .addComponent(btnEstoqueBaixo)
                        .addGap(18, 18, 18)
                        .addComponent(btnProCadastro)))
                .addContainerGap(182, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnProLocalizar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtPesquisaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(cmbFiltrarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProCadastro)
                    .addComponent(btnProListarTodos)
                    .addComponent(btnEstoqueBaixo)
                    .addComponent(btnProCarregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabProdutos.addTab("Consulta", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabProdutos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabProdutos)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEstoqueAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstoqueAtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstoqueAtualActionPerformed

    private void bntProConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntProConsultaActionPerformed
    
    limparCampos();

    tabProdutos.setSelectedIndex(0);
    
    }//GEN-LAST:event_bntProConsultaActionPerformed

    private void bntProSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntProSalvarActionPerformed
      
    if (
            idProdutoSelecionado != 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Existe um produto carregado para edição.\n"
                + "Utilize Alterar ou clique em Novo."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    try {

        Produto produto =
                criarProdutoComCampos();

        long codigo =
                produtoDAO.cadastrar(
                        produto
                );

        JOptionPane.showMessageDialog(
                this,
                "Produto cadastrado com sucesso.\n"
                + "Código: "
                + codigo
        );

        limparCampos();

        listarProdutos();

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar o produto.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_bntProSalvarActionPerformed

    private void btnProLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProLocalizarActionPerformed
       String pesquisa = txtPesquisaProdutos.getText().trim();

    String filtro = cmbFiltrarProdutos.getSelectedItem().toString();

    if (pesquisa.isEmpty()) {

        listarProdutos();

        return;
    }

    if (filtro.equals("Codigo") ) {

        try {

            Long.parseLong(pesquisa);

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(this, "Para pesquisar por ID informe apenas números.");

            txtPesquisaProdutos.requestFocus();

            return;
        }
    }

    try {

        List<Produto> produtos =
                produtoDAO.pesquisar(filtro, pesquisa);

        preencherTabela(produtos);

        if (produtos.isEmpty()) {
         JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.");
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro na pesquisa.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnProLocalizarActionPerformed

    private void btnProListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProListarTodosActionPerformed
       
    txtPesquisaProdutos.setText("");

    listarProdutos();
    }//GEN-LAST:event_btnProListarTodosActionPerformed

    private void btnEstoqueBaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstoqueBaixoActionPerformed
      try {

        List<Produto> produtos = produtoDAO.listarEstoqueBaixo();

        preencherTabela(produtos);

        if (produtos.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Nenhum produto está com estoque baixo.");
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(this, "Erro ao consultar estoque baixo.\n" + erro.getMessage());
    }
    }//GEN-LAST:event_btnEstoqueBaixoActionPerformed

    private void btnProCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProCarregarActionPerformed
      carregarProdutoSelecionado();
    }//GEN-LAST:event_btnProCarregarActionPerformed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
      
    if (
            evt.getClickCount() == 2
    ) {

        carregarProdutoSelecionado();
      }
    }//GEN-LAST:event_tblProdutosMouseClicked

    private void bntProAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntProAlterarActionPerformed
         if (
            idProdutoSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um produto antes de alterar."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja salvar as alterações deste produto?",
                    "Alterar Produto",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        Produto produto =
                criarProdutoComCampos();

        boolean alterado =
                produtoDAO.alterar(
                        produto
                );

        if (alterado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto alterado com sucesso."
            );

            limparCampos();

            listarProdutos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum registro foi alterado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível alterar o produto.\n"
                + erro.getMessage()
        );
     }
    }//GEN-LAST:event_bntProAlterarActionPerformed

    private void btnProExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProExcluirActionPerformed
           if (
            !SessaoUsuario.isMaster()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Você não possui permissão para excluir produtos."
        );

        return;
    }

    if (
            idProdutoSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um produto antes de excluir."
        );

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este produto?",
                    "Excluir Produto",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        boolean excluido =
                produtoDAO.excluir(
                        idProdutoSelecionado
                );

        if (excluido) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto excluído com sucesso."
            );

            limparCampos();

            listarProdutos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto não encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível excluir o produto.\n"
                + "Ele pode possuir compras ou vendas vinculadas.\n"
                + erro.getMessage()
        );
     }
    }//GEN-LAST:event_btnProExcluirActionPerformed

    private void bntProCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntProCancelarActionPerformed
       
       limparCampos();
       
    }//GEN-LAST:event_bntProCancelarActionPerformed

    private void carregarProdutoSelecionado() {

    int linha =
            tblProdutos
            .getSelectedRow();

    if (linha == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um produto na tabela."
        );

        return;
    }

    int linhaModelo =
            tblProdutos
            .convertRowIndexToModel(
                    linha
            );

    long idProduto =
            Long.parseLong(
                    tblProdutos
                    .getModel()
                    .getValueAt(
                            linhaModelo,
                            0
                    )
                    .toString()
            );

    try {

        Produto produto =
                produtoDAO.buscarPorId(
                        idProduto
                );

        if (
                produto == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto não encontrado."
            );

            return;
        }

        preencherCampos(
                produto
        );

        tabProdutos.setSelectedIndex(
                0
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produto.\n"
                + erro.getMessage()
        );
    }
}
    
    private void preencherCampos(
        Produto produto
) {

    idProdutoSelecionado =
            produto.getIdProduto();

    txtCodigoProduto.setText(
            String.valueOf(
                    produto.getIdProduto()
            )
    );

    txtDescricao.setText(
            produto.getDescricao()
    );

    cmbUnidade.setSelectedItem(
            produto.getUnidade()
    );

    txtPrecoCusto.setText(
            produto
            .getPrecoCusto()
            .toPlainString()
            .replace(".", ",")
    );

    txtPrecoVenda.setText(
            produto
            .getPrecoVenda()
            .toPlainString()
            .replace(".", ",")
    );

    txtEstoqueAtual.setText(
            produto
            .getEstoque()
            .toPlainString()
            .replace(".", ",")
    );

    txtEstoqueMinimo.setText(
            produto
            .getEstoqueMinimo()
            .toPlainString()
            .replace(".", ",")
    );

    chkPAtivo.setSelected(
            produto.isAtivo()
    );
}

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntProAlterar;
    private javax.swing.JButton bntProCancelar;
    private javax.swing.JButton bntProConsulta;
    private javax.swing.JButton bntProSalvar;
    private javax.swing.JButton btnEstoqueBaixo;
    private javax.swing.JButton btnProCadastro;
    private javax.swing.JButton btnProCarregar;
    private javax.swing.JButton btnProExcluir;
    private javax.swing.JButton btnProListarTodos;
    private javax.swing.JButton btnProLocalizar;
    private javax.swing.JCheckBox chkPAtivo;
    private javax.swing.JComboBox<String> cmbFiltrarProdutos;
    private javax.swing.JComboBox<String> cmbUnidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabProdutos;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEstoqueAtual;
    private javax.swing.JTextField txtEstoqueMinimo;
    private javax.swing.JTextField txtPesquisaProdutos;
    private javax.swing.JTextField txtPrecoCusto;
    private javax.swing.JTextField txtPrecoVenda;
    // End of variables declaration//GEN-END:variables
}
