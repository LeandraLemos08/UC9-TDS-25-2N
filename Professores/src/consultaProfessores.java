import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class consultaProfessores extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(consultaProfessores.class.getName());

  private void limparPesquisa(){
    txtID.setText("");
    cbTipoConsulta.setSelectedIndex(-1);
    txtID.requestFocus();
   }
   
 
    public consultaProfessores() {
        initComponents();
        carregarTabela();
        limparCampos();
    }
    
     private void limparCampos(){
    
    txtID.setText("");
    txtNome.setText("");
    txtDisciplina.setText("");
    txtEmail.setText("");
    txtTelefone.setText("");
    
    txtID.requestFocus();
    }

 private void carregarTabela(){
   
   DefaultTableModel modelo = new DefaultTableModel();
   modelo.addColumn("ID");
   modelo.addColumn("Nome");
   modelo.addColumn("Disciplina");
   modelo.addColumn("E-mail");
   modelo.addColumn("Telefone");
       
   Professor professor = new Professor();
   
   ArrayList<Professor> lista = professor.listar();
   
   
   for (Professor item: lista) {
   
        modelo.addRow(new Object[]{
        item.getId(),
        item.getNome(),
        item.getDisciplina(),
        item.getEmail(),
        item.getTelefone()
      });
       tabelaProfessores.setModel(modelo);
   }
   
   }
    
   
   private void desativarCampos(){
   
       txtNome.setEnabled(false);
       txtDisciplina.setEnabled(false);
       txtEmail.setEnabled(false);
       txtTelefone.setEnabled(false);
       
       
       bntAlterar.setEnabled(false);
       bntExcluir.setEnabled(false);
       
   }
   
    private void ativarCampos(){
   
       txtNome.setEnabled(true);
       txtDisciplina.setEnabled(true);
       txtEmail.setEnabled(true);
       txtTelefone.setEnabled(true);
       
       bntAlterar.setEnabled(true);
       bntExcluir.setEnabled(true);
       
   }
    
    private void preencherTabela(ArrayList<Professor>lista){
      
        DefaultTableModel modelo = (DefaultTableModel) tabelaProfessores.getModel();
        modelo.setRowCount(0);
        
        for(Professor professor : lista){
         modelo.addRow(new Object[]{
            professor.getId(),
             professor.getNome(),
              professor.getDisciplina(),
              professor.getEmail(),
               professor.getTelefone()}
                      );       
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtDisciplina = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cbTipoConsulta = new javax.swing.JComboBox<>();
        btnLocalizar = new javax.swing.JButton();
        bntAlterar = new javax.swing.JButton();
        bntExcluir = new javax.swing.JButton();
        bntAtualizar = new javax.swing.JButton();
        bntLimpar = new javax.swing.JButton();
        bntFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProfessores = new javax.swing.JTable();
        txtTelefone = new javax.swing.JTextField();
        bntRecarregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Localizar:"));
        txtID.addActionListener(this::txtIDActionPerformed);

        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nome do Professor:"));
        txtNome.addActionListener(this::txtNomeActionPerformed);

        txtDisciplina.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Disciplina do Professor:"));
        txtDisciplina.addActionListener(this::txtDisciplinaActionPerformed);

        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "E-mail do Professor:"));
        txtEmail.addActionListener(this::txtEmailActionPerformed);

        cbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nome", "Disciplina", "E-mail", "Telefone" }));
        cbTipoConsulta.setSelectedIndex(-1);

        btnLocalizar.setText("🔎 Localizar");
        btnLocalizar.addActionListener(this::btnLocalizarActionPerformed);

        bntAlterar.setText("🎨 Alterar");
        bntAlterar.addActionListener(this::bntAlterarActionPerformed);

        bntExcluir.setText("❌ Excluir");
        bntExcluir.addActionListener(this::bntExcluirActionPerformed);

        bntAtualizar.setText("🔄️ Atualizar");
        bntAtualizar.addActionListener(this::bntAtualizarActionPerformed);

        bntLimpar.setText("🚮 Limpar");
        bntLimpar.addActionListener(this::bntLimparActionPerformed);

        bntFechar.setText("🔐 Fechar");
        bntFechar.addActionListener(this::bntFecharActionPerformed);

        tabelaProfessores.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaProfessores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProfessoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProfessores);

        txtTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Telefone do Professor:"));
        txtTelefone.addActionListener(this::txtTelefoneActionPerformed);

        bntRecarregar.setText("🌀 Recarregar");
        bntRecarregar.addActionListener(this::bntRecarregarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(bntAlterar)
                        .addGap(28, 28, 28)
                        .addComponent(bntExcluir)
                        .addGap(29, 29, 29)
                        .addComponent(bntAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntRecarregar)
                        .addGap(29, 29, 29)
                        .addComponent(bntLimpar)
                        .addGap(38, 38, 38)
                        .addComponent(bntFechar)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDisciplina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLocalizar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(cbTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLocalizar)
                        .addGap(18, 18, 18)))
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntAlterar)
                    .addComponent(bntExcluir)
                    .addComponent(bntAtualizar)
                    .addComponent(bntLimpar)
                    .addComponent(bntFechar)
                    .addComponent(bntRecarregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
     btnLocalizar.doClick();
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisciplinaActionPerformed

    }//GEN-LAST:event_txtDisciplinaActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed

        String tipo = cbTipoConsulta.getSelectedItem().toString();
        String valor = txtID.getText().trim();

        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe um valor para a pesquisa", "Campo Obrigatório",  JOptionPane.WARNING_MESSAGE);
            txtID.requestFocus();
            return;

        }

        if (tipo.equals("ID")) {
            try {
                Integer.parseInt(valor);
            } catch (NumberFormatException error){

                JOptionPane.showMessageDialog(this, "Para pesquisar por ID digite apenas números!", "ID inválido!", JOptionPane.WARNING_MESSAGE);
                txtID.requestFocus();
                return;

            }
        }

        Professor professor = new Professor();

        ArrayList<Professor> lista = professor.localizar(tipo, valor);
        if(lista.isEmpty()){

            JOptionPane.showMessageDialog(this, "Nenhum professor foi encontrado", "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
            txtID.requestFocus();
            return;
        }

        if (lista.size() == 1) {

            tabelaProfessores.setRowSelectionInterval(0, 0);

        }

        JOptionPane.showMessageDialog(this, lista.size() + "Professor(es) encontrados.", "Consulta Concluída", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void bntAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAlterarActionPerformed
        if (txtID.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty()
            || txtDisciplina.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        int rp = JOptionPane.showConfirmDialog(this,
            "Realmente deseja alterar os dados deste Professor?",
            "Confirmação da Alteração dos Dados.",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (rp != JOptionPane.YES_OPTION){
            return;
        }
        int id = Integer.parseInt(txtID.getText().trim());
        String nome = txtNome.getText().trim();
        String disciplina = txtDisciplina.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        Professor professor = new Professor();

        boolean alterado = professor.alterar(id, nome, disciplina, email, telefone);
        if (alterado) {
            JOptionPane.showMessageDialog(this, "Professor alterado com sucesso!");
            carregarTabela();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possivel alterar o Professor.");
        }

    }//GEN-LAST:event_bntAlterarActionPerformed

    private void bntExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirActionPerformed
        if (txtID.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo ID!");
            return;
        }

        int rs = JOptionPane.showConfirmDialog
        (this,
            "Deseja realmente excluir esse Professor?",
            "Confirmação.",
            JOptionPane.YES_NO_CANCEL_OPTION);

        if(rs != JOptionPane.YES_OPTION){
            return;
        }

        int id = Integer.parseInt(txtID.getText().trim());

        Professor professor = new Professor();

        boolean excluido = professor.excluir(id);

        if (excluido) {
            JOptionPane.showMessageDialog(this, "Professor excluído com sucesso!");
            carregarTabela();
            limparCampos();
            desativarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível excluir o Professor.");
        }

    }//GEN-LAST:event_bntExcluirActionPerformed

    private void bntAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAtualizarActionPerformed
        carregarTabela();
        limparCampos();
        desativarCampos();
        JOptionPane.showMessageDialog(this,
            "Tabela atualizada com sucesso!");
    }//GEN-LAST:event_bntAtualizarActionPerformed

    private void bntLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLimparActionPerformed
        limparCampos();
        desativarCampos();
    }//GEN-LAST:event_bntLimparActionPerformed

    private void bntFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFecharActionPerformed
        dispose();
    }//GEN-LAST:event_bntFecharActionPerformed

    private void tabelaProfessoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProfessoresMouseClicked

    }//GEN-LAST:event_tabelaProfessoresMouseClicked

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void bntRecarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntRecarregarActionPerformed
       Professor professor = new Professor();
       
       ArrayList<Professor> lista = professor.listar();
       
       preencherTabela(lista);
       
       limparPesquisa();
    }//GEN-LAST:event_bntRecarregarActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new consultaProfessores().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAlterar;
    private javax.swing.JButton bntAtualizar;
    private javax.swing.JButton bntExcluir;
    private javax.swing.JButton bntFechar;
    private javax.swing.JButton bntLimpar;
    private javax.swing.JButton bntRecarregar;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JComboBox<String> cbTipoConsulta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProfessores;
    private javax.swing.JTextField txtDisciplina;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
