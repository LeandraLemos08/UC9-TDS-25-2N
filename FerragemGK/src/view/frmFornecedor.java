
package view;

import dao.FornecedorDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Fornecedor;
import util.SessaoUsuario;


public class frmFornecedor extends javax.swing.JInternalFrame {

   private final FornecedorDAO fornecedorDAO =
        new FornecedorDAO();

   private long idFornecedorSelecionado = 0;

   private void configurarTela() {

    txtCodigoFornecedor.setEditable(false);

    btnForExcluir.setEnabled(
            SessaoUsuario.isMaster()
    );

    tblFornecedores.setSelectionMode(
            javax.swing.ListSelectionModel
                    .SINGLE_SELECTION
    );

    tblFornecedores.setAutoCreateRowSorter(
            true
    );

    tblFornecedores.setModel(
            criarModeloTabela()
    );
}
   
   private DefaultTableModel criarModeloTabela() {

    return new DefaultTableModel(
            new Object[]{
                "Código",
                "Razão Social",
                "Nome Fantasia",
                "CNPJ",
                "Telefone",
                "Email",
                "Cidade",
                "UF",
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
        List<Fornecedor> fornecedores
) {

    DefaultTableModel modelo =
            (DefaultTableModel)
            tblFornecedores.getModel();

    modelo.setRowCount(0);

    for (
            Fornecedor fornecedor :
            fornecedores
    ) {

        modelo.addRow(
                new Object[]{
                    fornecedor.getIdFornecedor(),
                    fornecedor.getRazaoSocial(),
                    fornecedor.getNomeFantasia(),
                    fornecedor.getCnpj(),
                    fornecedor.getTelefone(),
                    fornecedor.getEmail(),
                    fornecedor.getCidade(),
                    fornecedor.getUf(),
                    fornecedor.isAtivo()
                            ? "Sim"
                            : "Não"
                }
        );
    }
}
   
   private void listarFornecedores() {

    try {

        preencherTabela(
                fornecedorDAO.listarTodos()
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar fornecedores."
                + erro.getMessage()
        );
    }
}
   
   private void limparCampos() {

    idFornecedorSelecionado = 0;

    txtCodigoFornecedor.setText("");
    txtRazaoSocial.setText("");
    txtNomeFantasia.setText("");
    txtCnpj.setText("");
    txtFornecedorTelefone.setText("");
    txtFornecedorEmail.setText("");
    txtFornecedorEndereco.setText("");
    txtFornecedorNumero.setText("");
    txtFornecedorComplemento.setText("");
    txtFornecedorBairro.setText("");
    txtFornecedorCidade.setText("");
    txtFornecedorCep.setText("");

    cmbUf.setSelectedIndex(0);

    chkFAtivo.setSelected(true);

    txtRazaoSocial.requestFocus();
}
   
   private boolean validarCampos() {

    if (
            txtRazaoSocial
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe a razão social."
        );

        txtRazaoSocial.requestFocus();

        return false;
    }

    if (
            txtCnpj
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe o CNPJ."
        );

        txtCnpj.requestFocus();

        return false;
    }

    if (
            cmbUf.getSelectedIndex() == 0
            && !txtFornecedorCidade
                    .getText()
                    .trim()
                    .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione a UF."
        );

        cmbUf.requestFocus();

        return false;
    }

    return true;
}
   
   private Fornecedor criarFornecedorComCampos() {

    Fornecedor fornecedor =
            new Fornecedor();

    fornecedor.setIdFornecedor(
            idFornecedorSelecionado
    );

    fornecedor.setRazaoSocial(
            txtRazaoSocial.getText().trim()
    );

    fornecedor.setNomeFantasia(
            txtNomeFantasia.getText().trim()
    );

    fornecedor.setCnpj(
            txtCnpj.getText().trim()
    );

    fornecedor.setTelefone(
            txtFornecedorTelefone.getText().trim()
    );

    fornecedor.setEmail(
            txtFornecedorEmail.getText().trim()
    );

    fornecedor.setEndereco(
            txtFornecedorEndereco.getText().trim()
    );

    fornecedor.setNumero(
            txtFornecedorNumero.getText().trim()
    );

    fornecedor.setComplemento(
            txtFornecedorComplemento.getText().trim()
    );

    fornecedor.setBairro(
            txtFornecedorBairro.getText().trim()
    );

    fornecedor.setCidade(
            txtFornecedorCidade.getText().trim()
    );

    if (
            cmbUf.getSelectedIndex() > 0
    ) {

        fornecedor.setUf(
                cmbUf
                .getSelectedItem()
                .toString()
        );

    } else {

        fornecedor.setUf("");
    }

    fornecedor.setCep(
            txtFornecedorCep.getText().trim()
    );

    fornecedor.setAtivo(
            chkFAtivo.isSelected()
    );

    return fornecedor;
}
   
    public frmFornecedor() {
        
        initComponents();
        
        configurarTela();

        listarFornecedores();

        limparCampos();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabFornecedores = new javax.swing.JTabbedPane();
        iCadastro = new javax.swing.JPanel();
        tela = new javax.swing.JPanel();
        txtCodigoFornecedor = new javax.swing.JTextField();
        txtRazaoSocial = new javax.swing.JTextField();
        txtCnpj = new javax.swing.JTextField();
        txtFornecedorTelefone = new javax.swing.JTextField();
        txtFornecedorEmail = new javax.swing.JTextField();
        txtFornecedorEndereco = new javax.swing.JTextField();
        txtFornecedorCep = new javax.swing.JTextField();
        txtFornecedorNumero = new javax.swing.JTextField();
        txtFornecedorComplemento = new javax.swing.JTextField();
        txtFornecedorBairro = new javax.swing.JTextField();
        txtFornecedorCidade = new javax.swing.JTextField();
        cmbUf = new javax.swing.JComboBox<>();
        chkFAtivo = new javax.swing.JCheckBox();
        btnForSalvar = new javax.swing.JButton();
        btnForAlterar = new javax.swing.JButton();
        btnForExcluir = new javax.swing.JButton();
        btnForCancelar = new javax.swing.JButton();
        btnForConsulta = new javax.swing.JButton();
        txtNomeFantasia = new javax.swing.JTextField();
        iConsulta = new javax.swing.JPanel();
        txtPesquisaFornecedor = new javax.swing.JTextField();
        btnLocalizarFornecedor = new javax.swing.JButton();
        btnForListarTodos = new javax.swing.JButton();
        btnNovoFornecedor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFornecedores = new javax.swing.JTable();
        btnCarregarFornecedor = new javax.swing.JButton();
        cmbFiltrarFornecedor = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro e Consulta de Fornecedores");

        txtCodigoFornecedor.setToolTipText("");
        txtCodigoFornecedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Código:"));
        txtCodigoFornecedor.addActionListener(this::txtCodigoFornecedorActionPerformed);

        txtRazaoSocial.setToolTipText("Digite a razão social");
        txtRazaoSocial.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Razão Social:"));
        txtRazaoSocial.addActionListener(this::txtRazaoSocialActionPerformed);

        txtCnpj.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "CNPJ:"));
        txtCnpj.addActionListener(this::txtCnpjActionPerformed);

        txtFornecedorTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Telefone:"));
        txtFornecedorTelefone.addActionListener(this::txtFornecedorTelefoneActionPerformed);

        txtFornecedorEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "E-mail:"));
        txtFornecedorEmail.addActionListener(this::txtFornecedorEmailActionPerformed);

        txtFornecedorEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Endereço:"));
        txtFornecedorEndereco.addActionListener(this::txtFornecedorEnderecoActionPerformed);

        txtFornecedorCep.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "CEP:"));
        txtFornecedorCep.addActionListener(this::txtFornecedorCepActionPerformed);

        txtFornecedorNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Número:"));
        txtFornecedorNumero.addActionListener(this::txtFornecedorNumeroActionPerformed);

        txtFornecedorComplemento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Complemento:"));
        txtFornecedorComplemento.addActionListener(this::txtFornecedorComplementoActionPerformed);

        txtFornecedorBairro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Bairro:"));
        txtFornecedorBairro.addActionListener(this::txtFornecedorBairroActionPerformed);

        txtFornecedorCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Cidade:"));
        txtFornecedorCidade.addActionListener(this::txtFornecedorCidadeActionPerformed);

        cmbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        cmbUf.setSelectedIndex(-1);
        cmbUf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Estado:"));
        cmbUf.addActionListener(this::cmbUfActionPerformed);

        chkFAtivo.setText("Ativo");

        btnForSalvar.setBackground(new java.awt.Color(255, 153, 0));
        btnForSalvar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnForSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnForSalvar.setText("Salvar");
        btnForSalvar.setToolTipText("");
        btnForSalvar.addActionListener(this::btnForSalvarActionPerformed);

        btnForAlterar.setBackground(new java.awt.Color(255, 0, 51));
        btnForAlterar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnForAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnForAlterar.setText("Alterar");
        btnForAlterar.setToolTipText("");
        btnForAlterar.addActionListener(this::btnForAlterarActionPerformed);

        btnForExcluir.setBackground(new java.awt.Color(204, 0, 0));
        btnForExcluir.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnForExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnForExcluir.setText("Excluir");
        btnForExcluir.setToolTipText("");
        btnForExcluir.addActionListener(this::btnForExcluirActionPerformed);

        btnForCancelar.setBackground(new java.awt.Color(0, 255, 0));
        btnForCancelar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnForCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnForCancelar.setText("Cancelar");
        btnForCancelar.setToolTipText("");
        btnForCancelar.addActionListener(this::btnForCancelarActionPerformed);

        btnForConsulta.setBackground(new java.awt.Color(255, 153, 153));
        btnForConsulta.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnForConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btnForConsulta.setText("Consulta");
        btnForConsulta.setToolTipText("");
        btnForConsulta.addActionListener(this::btnForConsultaActionPerformed);

        txtNomeFantasia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nome Fantasia:"));
        txtNomeFantasia.addActionListener(this::txtNomeFantasiaActionPerformed);

        javax.swing.GroupLayout telaLayout = new javax.swing.GroupLayout(tela);
        tela.setLayout(telaLayout);
        telaLayout.setHorizontalGroup(
            telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(telaLayout.createSequentialGroup()
                        .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFornecedorTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFornecedorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazaoSocial)
                    .addComponent(txtNomeFantasia)
                    .addGroup(telaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtFornecedorEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFornecedorNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFornecedorComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(telaLayout.createSequentialGroup()
                        .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFornecedorCidade)
                            .addComponent(txtFornecedorBairro)
                            .addGroup(telaLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnForSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnForConsulta)
                                .addGap(12, 12, 12)
                                .addComponent(btnForAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(btnForExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(telaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFornecedorCep)
                                    .addComponent(cmbUf, 0, 118, Short.MAX_VALUE)))
                            .addGroup(telaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnForCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkFAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        telaLayout.setVerticalGroup(
            telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornecedorTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornecedorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFornecedorEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornecedorNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornecedorComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFornecedorCep)
                    .addComponent(txtFornecedorBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFornecedorCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFAtivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForSalvar)
                    .addComponent(btnForConsulta)
                    .addComponent(btnForAlterar)
                    .addComponent(btnForExcluir)
                    .addComponent(btnForCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout iCadastroLayout = new javax.swing.GroupLayout(iCadastro);
        iCadastro.setLayout(iCadastroLayout);
        iCadastroLayout.setHorizontalGroup(
            iCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
            .addGroup(iCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        iCadastroLayout.setVerticalGroup(
            iCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
            .addGroup(iCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabFornecedores.addTab("Cadastro", iCadastro);

        txtPesquisaFornecedor.setToolTipText("Escreva o nome de um cliente para pesquisa");
        txtPesquisaFornecedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Pesquisa:"));
        txtPesquisaFornecedor.addActionListener(this::txtPesquisaFornecedorActionPerformed);

        btnLocalizarFornecedor.setBackground(new java.awt.Color(0, 153, 0));
        btnLocalizarFornecedor.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnLocalizarFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        btnLocalizarFornecedor.setText("Localizar");
        btnLocalizarFornecedor.addActionListener(this::btnLocalizarFornecedorActionPerformed);

        btnForListarTodos.setBackground(new java.awt.Color(0, 51, 255));
        btnForListarTodos.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnForListarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnForListarTodos.setText("Listar Todos");
        btnForListarTodos.setToolTipText("");
        btnForListarTodos.addActionListener(this::btnForListarTodosActionPerformed);

        btnNovoFornecedor.setBackground(new java.awt.Color(51, 204, 255));
        btnNovoFornecedor.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnNovoFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoFornecedor.setText("Cadastro");
        btnNovoFornecedor.setToolTipText("");
        btnNovoFornecedor.addActionListener(this::btnNovoFornecedorActionPerformed);

        tblFornecedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblFornecedores);

        btnCarregarFornecedor.setBackground(new java.awt.Color(255, 204, 51));
        btnCarregarFornecedor.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnCarregarFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        btnCarregarFornecedor.setText("Carregar");
        btnCarregarFornecedor.setToolTipText("");
        btnCarregarFornecedor.addActionListener(this::btnCarregarFornecedorActionPerformed);

        cmbFiltrarFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Razao Social", "Nome Fantasia", "Codigo", "CNPJ", "Email" }));
        cmbFiltrarFornecedor.setSelectedIndex(-1);
        cmbFiltrarFornecedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Filtrar por:"));
        cmbFiltrarFornecedor.addActionListener(this::cmbFiltrarFornecedorActionPerformed);

        javax.swing.GroupLayout iConsultaLayout = new javax.swing.GroupLayout(iConsulta);
        iConsulta.setLayout(iConsultaLayout);
        iConsultaLayout.setHorizontalGroup(
            iConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iConsultaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(iConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(iConsultaLayout.createSequentialGroup()
                        .addComponent(cmbFiltrarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPesquisaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnLocalizarFornecedor))
                    .addGroup(iConsultaLayout.createSequentialGroup()
                        .addComponent(btnForListarTodos)
                        .addGap(29, 29, 29)
                        .addComponent(btnCarregarFornecedor)
                        .addGap(27, 27, 27)
                        .addComponent(btnNovoFornecedor)))
                .addGap(152, 152, Short.MAX_VALUE))
        );
        iConsultaLayout.setVerticalGroup(
            iConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iConsultaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(iConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(iConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLocalizarFornecedor)
                        .addComponent(txtPesquisaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbFiltrarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(iConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCarregarFornecedor)
                    .addComponent(btnForListarTodos)
                    .addComponent(btnNovoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabFornecedores.addTab("Consulta", iConsulta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabFornecedores, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabFornecedores)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoFornecedorActionPerformed
        txtCodigoFornecedor.setEditable(false);
    }//GEN-LAST:event_txtCodigoFornecedorActionPerformed

    private void txtRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaoSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaoSocialActionPerformed

    private void txtCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjActionPerformed

    private void txtFornecedorTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorTelefoneActionPerformed

    private void txtFornecedorEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorEmailActionPerformed

    private void txtFornecedorEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorEnderecoActionPerformed

    private void txtFornecedorCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorCepActionPerformed

    private void txtFornecedorNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorNumeroActionPerformed

    private void txtFornecedorComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorComplementoActionPerformed

    private void txtFornecedorBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorBairroActionPerformed

    private void txtFornecedorCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornecedorCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorCidadeActionPerformed

    private void cmbUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUfActionPerformed

    }//GEN-LAST:event_cmbUfActionPerformed

    private void btnForSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForSalvarActionPerformed
      if (
            idFornecedorSelecionado != 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Existe um fornecedor carregado para edição."
                + "Utilize Alterar ou clique em Novo."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    try {

        Fornecedor fornecedor =
                criarFornecedorComCampos();

        long codigo =
                fornecedorDAO.cadastrar(
                        fornecedor
                );

        JOptionPane.showMessageDialog(
                this,
                "Fornecedor cadastrado com sucesso."
                + "Código: "
                + codigo
        );

        limparCampos();

        listarFornecedores();

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar o fornecedor."
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnForSalvarActionPerformed

    private void btnForAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForAlterarActionPerformed
        if (
            idFornecedorSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um fornecedor antes de alterar."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja salvar as alterações deste fornecedor?",
                    "Alterar Fornecedor",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        Fornecedor fornecedor =
                criarFornecedorComCampos();

        boolean alterado =
                fornecedorDAO.alterar(
                        fornecedor
                );

        if (alterado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fornecedor alterado com sucesso."
            );

            limparCampos();

            listarFornecedores();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum registro foi alterado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível alterar o fornecedor."
                + erro.getMessage()
        );
    }       
    }//GEN-LAST:event_btnForAlterarActionPerformed

    private void btnForExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForExcluirActionPerformed
      if (
            !SessaoUsuario.isMaster()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Você não possui permissão para excluir fornecedores."
        );

        return;
    }

    if (
            idFornecedorSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um fornecedor antes de excluir."
        );

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este fornecedor?",
                    "Excluir Fornecedor",
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
                fornecedorDAO.excluir(
                        idFornecedorSelecionado
                );

        if (excluido) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fornecedor excluído com sucesso."
            );

            limparCampos();

            listarFornecedores();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Fornecedor não encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível excluir o fornecedor."
                + "Ele pode possuir compras vinculadas."
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnForExcluirActionPerformed

    private void btnForCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnForCancelarActionPerformed

    private void btnForConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForConsultaActionPerformed
         limparCampos();
        tabFornecedores.setSelectedIndex(1)   ;   
    }//GEN-LAST:event_btnForConsultaActionPerformed

    private void txtNomeFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeFantasiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeFantasiaActionPerformed

    private void txtPesquisaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaFornecedorActionPerformed

    private void btnLocalizarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarFornecedorActionPerformed
     String pesquisa =
            txtPesquisaFornecedor
            .getText()
            .trim();

    String filtro =
            cmbFiltrarFornecedor
            .getSelectedItem()
            .toString();

    if (pesquisa.isEmpty()) {

        listarFornecedores();

        return;
    }

    if (
            filtro.equals("ID")
    ) {

        try {

            Long.parseLong(
                    pesquisa
            );

        } catch (
                NumberFormatException erro
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Para pesquisar por ID informe apenas números."
            );

            txtPesquisaFornecedor.requestFocus();

            return;
        }
    }

    try {

        List<Fornecedor> fornecedores =
                fornecedorDAO.pesquisar(
                        filtro,
                        pesquisa
                );

        preencherTabela(
                fornecedores
        );

        if (
                fornecedores.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum fornecedor encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro na pesquisa."
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnLocalizarFornecedorActionPerformed

    private void btnForListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForListarTodosActionPerformed
        txtPesquisaFornecedor.setText("");

        listarFornecedores();
    }//GEN-LAST:event_btnForListarTodosActionPerformed

    private void carregarFornecedorSelecionado() {

    int linha =
            tblFornecedores
            .getSelectedRow();

    if (linha == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um fornecedor na tabela."
        );

        return;
    }

    int linhaModelo =
            tblFornecedores
            .convertRowIndexToModel(
                    linha
            );

    long idFornecedor =
            Long.parseLong(
                    tblFornecedores
                    .getModel()
                    .getValueAt(
                            linhaModelo,
                            0
                    )
                    .toString()
            );

    try {

        Fornecedor fornecedor =
                fornecedorDAO.buscarPorId(
                        idFornecedor
                );

        if (
                fornecedor == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fornecedor não encontrado."
            );

            return;
        }

        preencherCampos(
                fornecedor
        );

        tabFornecedores.setSelectedIndex(
                0
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar fornecedor."
                + erro.getMessage()
        );
    }
}
    
    private String valorTexto(
        String valor
) {

    if (valor == null) {

        return "";
    }

    return valor;
}
    
    private void preencherCampos(
        Fornecedor fornecedor
) {

    idFornecedorSelecionado =
            fornecedor.getIdFornecedor();

    txtCodigoFornecedor.setText(
            String.valueOf(
                    fornecedor.getIdFornecedor()
            )
    );

    txtRazaoSocial.setText(
            valorTexto(
                    fornecedor.getRazaoSocial()
            )
    );

    txtNomeFantasia.setText(
            valorTexto(
                    fornecedor.getNomeFantasia()
            )
    );

    txtCnpj.setText(
            valorTexto(
                    fornecedor.getCnpj()
            )
    );

    txtFornecedorTelefone.setText(
            valorTexto(
                    fornecedor.getTelefone()
            )
    );

    txtFornecedorEmail.setText(
            valorTexto(
                    fornecedor.getEmail()
            )
    );

    txtFornecedorEndereco.setText(
            valorTexto(
                    fornecedor.getEndereco()
            )
    );

    txtFornecedorNumero.setText(
            valorTexto(
                    fornecedor.getNumero()
            )
    );

    txtFornecedorComplemento.setText(
            valorTexto(
                    fornecedor.getComplemento()
            )
    );

    txtFornecedorBairro.setText(
            valorTexto(
                    fornecedor.getBairro()
            )
    );

    txtFornecedorCidade.setText(
            valorTexto(
                    fornecedor.getCidade()
            )
    );

    txtFornecedorCep.setText(
            valorTexto(
                    fornecedor.getCep()
            )
    );

    if (
            fornecedor.getUf() != null
            && !fornecedor
                    .getUf()
                    .isBlank()
    ) {

        cmbUf.setSelectedItem(
                fornecedor.getUf()
        );

    } else {

        cmbUf.setSelectedIndex(
                0
        );
    }

    chkFAtivo.setSelected(
            fornecedor.isAtivo()
    );
}
    
    private void btnNovoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoFornecedorActionPerformed
        limparCampos();

        tabFornecedores.setSelectedIndex(0);
    }//GEN-LAST:event_btnNovoFornecedorActionPerformed

    private void tblFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFornecedoresMouseClicked
        if (
            evt.getClickCount() == 2
        ) {

            carregarFornecedorSelecionado();
        }
    }//GEN-LAST:event_tblFornecedoresMouseClicked

    private void btnCarregarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarFornecedorActionPerformed
        carregarFornecedorSelecionado();
    }//GEN-LAST:event_btnCarregarFornecedorActionPerformed

    private void cmbFiltrarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltrarFornecedorActionPerformed
        String tipo = cmbFiltrarFornecedor.getSelectedItem().toString();
        txtCodigoFornecedor.setText("");
        if (tipo.equals("Codigo")) {
            txtCodigoFornecedor.setToolTipText("Digite o código completo");
        } else if (tipo.equals("Nome Fantasia")) {
            txtCodigoFornecedor.setToolTipText("Digite o nome fantasia ou parte dele"); 
        } else if (tipo.equals("CNPJ")) {
            txtCodigoFornecedor.setToolTipText("Digite o cnpj ou parte dele");
        } else if (tipo.equals("Razao Social")) {
            txtCodigoFornecedor.setToolTipText("Digite a razão social ou parte dela");
        } else if (tipo.equals("Email")) {
            txtCodigoFornecedor.setToolTipText("Digite o e-mail ou parte dele");
        } txtCodigoFornecedor.requestFocus();
    }//GEN-LAST:event_cmbFiltrarFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCarregarFornecedor;
    private javax.swing.JButton btnForAlterar;
    private javax.swing.JButton btnForCancelar;
    private javax.swing.JButton btnForConsulta;
    private javax.swing.JButton btnForExcluir;
    private javax.swing.JButton btnForListarTodos;
    private javax.swing.JButton btnForSalvar;
    private javax.swing.JButton btnLocalizarFornecedor;
    private javax.swing.JButton btnNovoFornecedor;
    private javax.swing.JCheckBox chkFAtivo;
    private javax.swing.JComboBox<String> cmbFiltrarFornecedor;
    private javax.swing.JComboBox<String> cmbUf;
    private javax.swing.JPanel iCadastro;
    private javax.swing.JPanel iConsulta;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabFornecedores;
    private javax.swing.JTable tblFornecedores;
    private javax.swing.JPanel tela;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtCodigoFornecedor;
    private javax.swing.JTextField txtFornecedorBairro;
    private javax.swing.JTextField txtFornecedorCep;
    private javax.swing.JTextField txtFornecedorCidade;
    private javax.swing.JTextField txtFornecedorComplemento;
    private javax.swing.JTextField txtFornecedorEmail;
    private javax.swing.JTextField txtFornecedorEndereco;
    private javax.swing.JTextField txtFornecedorNumero;
    private javax.swing.JTextField txtFornecedorTelefone;
    private javax.swing.JTextField txtNomeFantasia;
    private javax.swing.JTextField txtPesquisaFornecedor;
    private javax.swing.JTextField txtRazaoSocial;
    // End of variables declaration//GEN-END:variables
}
