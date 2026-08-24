
package view;
import dao.UsuarioDAO;

import java.util.List;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import model.Usuario;

import util.SessaoUsuario;


public class frmUsuario extends javax.swing.JInternalFrame {

   private final UsuarioDAO usuarioDAO =
        new UsuarioDAO();

   private long idUsuarioSelecionado = 0;

   private String senhaAtualCarregada = "";


    public frmUsuario() {
        initComponents();
    }

    private void configurarTela() {

    txtCodigoU.setEditable(false);

    boolean master =
            SessaoUsuario.isMaster();

    btnSalvarU.setEnabled(master);
    btnListarTodosU.setEnabled(master);
    btnExcluirU.setEnabled(master);

    tblUsuarios.setSelectionMode(
            javax.swing.ListSelectionModel
                    .SINGLE_SELECTION
    );

    tblUsuarios.setAutoCreateRowSorter(
            true
    );

    tblUsuarios.setModel(
            criarModeloTabela()
    );
}
    
    private DefaultTableModel criarModeloTabela() {

    return new DefaultTableModel(
            new Object[]{
                "Código",
                "Nome",
                "Login",
                "Nível",
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
        List<Usuario> usuarios
) {

    DefaultTableModel modelo =
            (DefaultTableModel)
            tblUsuarios.getModel();

    modelo.setRowCount(0);

    for (
            Usuario usuario :
            usuarios
    ) {

        modelo.addRow(
                new Object[]{
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getLogin(),
                    usuario.getNivel(),
                    usuario.isAtivo()
                            ? "Sim"
                            : "Não"
                }
        );
    }
}
    
    private void listarUsuarios() {

    try {

        preencherTabela(
                usuarioDAO.listarTodos()
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar usuários.\n"
                + erro.getMessage()
        );
    }
}
    
    private void limparCampos() {

    idUsuarioSelecionado = 0;
    senhaAtualCarregada = "";

    txtCodigoU.setText("");
    txtPesquisa.setText("");
    txtLoginU.setText("");
    pswSenhaU.setText("");

    cmbNivelU.setSelectedIndex(0);

    chkAtivoU.setSelected(true);

    txtPesquisa.requestFocus();
}
    
    private boolean validarCampos() {

    if (
            txtNomeU
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe o nome do usuário."
        );

        txtNomeU.requestFocus();

        return false;
    }

    if (
            txtLoginU
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe o login do usuário."
        );

        txtLoginU.requestFocus();

        return false;
    }

    if (
            idUsuarioSelecionado == 0
            && new String(
                    pswSenhaU.getPassword()
            ).trim().isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe a senha do usuário."
        );

        pswSenhaU.requestFocus();

        return false;
    }

    return true;
}
    
    private Usuario criarUsuarioComCampos() {

    Usuario usuario =
            new Usuario();

    usuario.setIdUsuario(
            idUsuarioSelecionado
    );

    usuario.setNome(
            txtNomeU
            .getText()
            .trim()
    );

    usuario.setLogin(
            txtLoginU
            .getText()
            .trim()
    );

    String senhaDigitada =
            new String(
                    pswSenhaU.getPassword()).trim();

    if (senhaDigitada.isEmpty()) {

        usuario.setSenha(
                senhaAtualCarregada
        );

    } else {

        usuario.setSenha(
                senhaDigitada
        );
    }

    usuario.setNivel(
            cmbNivelU
            .getSelectedItem()
            .toString()
    );

    usuario.setAtivo(
            chkAtivoU.isSelected()
    );

    return usuario;
}
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabUsuarios = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnConsultaU = new javax.swing.JButton();
        btnAlterarU = new javax.swing.JButton();
        btnExcluirU = new javax.swing.JButton();
        txtCodigoU = new javax.swing.JTextField();
        btnCancelarU = new javax.swing.JButton();
        pswSenhaU = new javax.swing.JPasswordField();
        txtNomeU = new javax.swing.JTextField();
        cmbNivelU = new javax.swing.JComboBox<>();
        chkAtivoU = new javax.swing.JCheckBox();
        txtLoginU = new javax.swing.JTextField();
        btnSalvarU = new javax.swing.JButton();
        btnListarTodos = new javax.swing.JPanel();
        btnCadastro = new javax.swing.JButton();
        txtPesquisa = new javax.swing.JTextField();
        btnCarregar = new javax.swing.JButton();
        btnListarTodosU = new javax.swing.JButton();
        btnLocalizar = new javax.swing.JButton();
        cmbFiltro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();

        btnConsultaU.setBackground(new java.awt.Color(255, 153, 153));
        btnConsultaU.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnConsultaU.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultaU.setText("Consulta");
        btnConsultaU.setToolTipText("Salvar alterações feitas no cadastro de um cliente");
        btnConsultaU.addActionListener(this::btnConsultaUActionPerformed);

        btnAlterarU.setBackground(new java.awt.Color(255, 0, 51));
        btnAlterarU.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnAlterarU.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarU.setText("Alterar");
        btnAlterarU.setToolTipText("Alterar as informações de um cadastro de cliente");
        btnAlterarU.addActionListener(this::btnAlterarUActionPerformed);

        btnExcluirU.setBackground(new java.awt.Color(204, 0, 0));
        btnExcluirU.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnExcluirU.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirU.setText("Excluir");
        btnExcluirU.setToolTipText("Excluir um cadastro de cliente");
        btnExcluirU.addActionListener(this::btnExcluirUActionPerformed);

        txtCodigoU.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Código:"));
        txtCodigoU.addActionListener(this::txtCodigoUActionPerformed);

        btnCancelarU.setBackground(new java.awt.Color(0, 255, 0));
        btnCancelarU.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnCancelarU.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarU.setText("Cancelar");
        btnCancelarU.setToolTipText("Cancelar tudo feito até agora");
        btnCancelarU.addActionListener(this::btnCancelarUActionPerformed);

        pswSenhaU.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Senha:"));

        txtNomeU.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nome:"));
        txtNomeU.addActionListener(this::txtNomeUActionPerformed);

        cmbNivelU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Master" }));
        cmbNivelU.setSelectedIndex(-1);
        cmbNivelU.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nível:"));

        chkAtivoU.setText("Ativo");
        chkAtivoU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtLoginU.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Login:"));
        txtLoginU.addActionListener(this::txtLoginUActionPerformed);

        btnSalvarU.setBackground(new java.awt.Color(255, 153, 0));
        btnSalvarU.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnSalvarU.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarU.setText("Salvar");
        btnSalvarU.setToolTipText("Salvar alterações feitas no cadastro de um cliente");
        btnSalvarU.addActionListener(this::btnSalvarUActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnSalvarU, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsultaU, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarU, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirU, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarU, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigoU, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbNivelU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtLoginU, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pswSenhaU, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNomeU))
                        .addGap(18, 18, 18)
                        .addComponent(chkAtivoU)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNivelU, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtNomeU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoginU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswSenhaU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAtivoU))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterarU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        tabUsuarios.addTab("Cadastro", jPanel1);

        btnCadastro.setBackground(new java.awt.Color(255, 153, 153));
        btnCadastro.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastro.setText("Cadastro");
        btnCadastro.setToolTipText("Salvar alterações feitas no cadastro de um cliente");
        btnCadastro.addActionListener(this::btnCadastroActionPerformed);

        txtPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Pesquisa:"));
        txtPesquisa.addActionListener(this::txtPesquisaActionPerformed);

        btnCarregar.setBackground(new java.awt.Color(255, 153, 0));
        btnCarregar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnCarregar.setForeground(new java.awt.Color(255, 255, 255));
        btnCarregar.setText("Carregar");
        btnCarregar.setToolTipText("");
        btnCarregar.addActionListener(this::btnCarregarActionPerformed);

        btnListarTodosU.setBackground(new java.awt.Color(255, 0, 51));
        btnListarTodosU.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnListarTodosU.setForeground(new java.awt.Color(255, 255, 255));
        btnListarTodosU.setText("Listar  Todos");
        btnListarTodosU.setToolTipText("Alterar as informações de um cadastro de cliente");
        btnListarTodosU.addActionListener(this::btnListarTodosUActionPerformed);

        btnLocalizar.setBackground(new java.awt.Color(0, 255, 0));
        btnLocalizar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnLocalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnLocalizar.setText("Localizar");
        btnLocalizar.setToolTipText("Cancelar tudo feito até agora");
        btnLocalizar.addActionListener(this::btnLocalizarActionPerformed);

        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Codigo", "Login", "Nivel" }));
        cmbFiltro.setSelectedIndex(-1);
        cmbFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Filtro:"));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        javax.swing.GroupLayout btnListarTodosLayout = new javax.swing.GroupLayout(btnListarTodos);
        btnListarTodos.setLayout(btnListarTodosLayout);
        btnListarTodosLayout.setHorizontalGroup(
            btnListarTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnListarTodosLayout.createSequentialGroup()
                .addGroup(btnListarTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnListarTodosLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnLocalizar))
                    .addGroup(btnListarTodosLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnListarTodosU, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnCarregar)))
                .addContainerGap(78, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        btnListarTodosLayout.setVerticalGroup(
            btnListarTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnListarTodosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(btnListarTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocalizar))
                .addGap(18, 18, 18)
                .addGroup(btnListarTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastro)
                    .addComponent(btnListarTodosU)
                    .addComponent(btnCarregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabUsuarios.addTab("Consulta", btnListarTodos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabUsuarios)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabUsuarios, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
         String pesquisa =
            txtPesquisa
            .getText()
            .trim();

    String filtro =
            cmbFiltro
            .getSelectedItem()
            .toString();

    if (pesquisa.isEmpty()) {

        listarUsuarios();

        return;
    }

    if (filtro.equals("ID")) {

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

            txtPesquisa.requestFocus();

            return;
        }
    }

    try {

        List<Usuario> usuarios =
                usuarioDAO.pesquisar(
                        filtro,
                        pesquisa
                );

        preencherTabela(
                usuarios
        );

        if (usuarios.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum usuário encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro na pesquisa.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void btnListarTodosUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodosUActionPerformed
       
    txtPesquisa.setText("");

    listarUsuarios();             
    }//GEN-LAST:event_btnListarTodosUActionPerformed

    
    private void carregarUsuarioSelecionado() {

    int linha =
            tblUsuarios.getSelectedRow();

    if (linha == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um usuário na tabela."
        );

        return;
    }

    int linhaModelo =
            tblUsuarios.convertRowIndexToModel(
                    linha
            );

    long idUsuario =
            Long.parseLong(
                    tblUsuarios
                    .getModel()
                    .getValueAt(
                            linhaModelo,
                            0
                    )
                    .toString()
            );

    try {

        Usuario usuario =
                usuarioDAO.buscarPorId(
                        idUsuario
                );

        if (usuario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário não encontrado."
            );

            return;
        }

        preencherCampos(
                usuario
        );

        tabUsuarios.setSelectedIndex(
                0
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar usuário.\n"
                + erro.getMessage()
        );
    }
}
    
    private void preencherCampos(
        Usuario usuario
) {

    idUsuarioSelecionado =
            usuario.getIdUsuario();

    senhaAtualCarregada =
            usuario.getSenha();

    txtCodigoU.setText(
            String.valueOf(
                    usuario.getIdUsuario()
            )
    );

    txtNomeU.setText(
            valorTexto(
                    usuario.getNome()
            )
    );

    txtLoginU.setText(
            valorTexto(
                    usuario.getLogin()
            )
    );

    pswSenhaU.setText("");

    cmbNivelU.setSelectedItem(
            usuario.getNivel()
    );

    chkAtivoU.setSelected(
            usuario.isAtivo()
    );
}
    private String valorTexto(
        String valor
) {

    if (valor == null) {

        return "";
    }

    return valor;
}
    
    
    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
      carregarUsuarioSelecionado(); 
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        limparCampos();
        tabUsuarios.setSelectedIndex(0)   ;   
    }//GEN-LAST:event_btnCadastroActionPerformed

    private void btnConsultaUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaUActionPerformed
        limparCampos();
        tabUsuarios.setSelectedIndex(1)   ;   
    }//GEN-LAST:event_btnConsultaUActionPerformed

    private void btnAlterarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarUActionPerformed
        if (
            idUsuarioSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um usuário antes de alterar."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja salvar as alterações deste usuário?",
                    "Alterar Usuário",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        Usuario usuario =
                criarUsuarioComCampos();

        boolean alterado =
                usuarioDAO.alterar(
                        usuario
                );

        if (alterado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário alterado com sucesso."
            );

            limparCampos();

            listarUsuarios();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum registro foi alterado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível alterar o usuário.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnAlterarUActionPerformed

    private void btnExcluirUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirUActionPerformed
       if (
            !SessaoUsuario.isMaster()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Você não possui permissão para excluir usuários."
        );

        return;
    }

    if (
            idUsuarioSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um usuário antes de excluir."
        );

        return;
    }

    if (
            idUsuarioSelecionado
            == SessaoUsuario.getUsuarioLogado().getIdUsuario()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Você não pode excluir o próprio usuário logado."
        );

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este usuário?",
                    "Excluir Usuário",
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
                usuarioDAO.excluir(
                        idUsuarioSelecionado
                );

        if (excluido) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário excluído com sucesso."
            );

            limparCampos();

            listarUsuarios();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário não encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível excluir o usuário.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnExcluirUActionPerformed

    private void txtCodigoUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoUActionPerformed
         txtCodigoU.setEditable(false);
    }//GEN-LAST:event_txtCodigoUActionPerformed

    private void btnCancelarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUActionPerformed
      limparCampos();
    }//GEN-LAST:event_btnCancelarUActionPerformed

    private void txtNomeUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUActionPerformed

    private void txtLoginUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginUActionPerformed

    private void btnSalvarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarUActionPerformed
          if (
            idUsuarioSelecionado != 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Existe um usuário carregado para edição.\n"
                + "Utilize Alterar ou clique em Novo."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    try {

        Usuario usuario =
                criarUsuarioComCampos();

        long codigo =
                usuarioDAO.cadastrar(
                        usuario
                );

        JOptionPane.showMessageDialog(
                this,
                "Usuário cadastrado com sucesso.\n"
                + "Código: "
                + codigo
        );

        limparCampos();

        listarUsuarios();

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar o usuário.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnSalvarUActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
         if (
            evt.getClickCount() == 2
    ) {

        carregarUsuarioSelecionado();
    }
    }//GEN-LAST:event_tblUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarU;
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnCancelarU;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnConsultaU;
    private javax.swing.JButton btnExcluirU;
    private javax.swing.JPanel btnListarTodos;
    private javax.swing.JButton btnListarTodosU;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnSalvarU;
    private javax.swing.JCheckBox chkAtivoU;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JComboBox<String> cmbNivelU;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pswSenhaU;
    private javax.swing.JTabbedPane tabUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtCodigoU;
    private javax.swing.JTextField txtLoginU;
    private javax.swing.JTextField txtNomeU;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
