package telas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import src.Aluno;
import javax.swing.table.DefaultTableModel;


public class consultaAlunos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(consultaAlunos.class.getName());

   private void carregarTabela(){
   
   DefaultTableModel modelo = new DefaultTableModel();
   modelo.addColumn("ID");
   modelo.addColumn("Nome");
   modelo.addColumn("Turma");
   modelo.addColumn("E-mail");
       
   Aluno aluno = new Aluno();
   
   ArrayList<Aluno> lista = aluno.listar();
   
   
   for (Aluno item: lista) {
   
        modelo.addRow(new Object[]{
        item.getId(),
        item.getNome(),
        item.getTurma(),
        item.getEmail()
      });
       tabelaAlunos.setModel(modelo);
   }
   
   }
    
   
   private void desativarCampos(){
   
       txtNome.setEnabled(false);
       txtEmail.setEnabled(false);
       txtTurma.setEnabled(false);
       
       bntAlterar.setEnabled(false);
       bntExcluir.setEnabled(false);
       
   }
   
    private void ativarCampos(){
   
       txtNome.setEnabled(true);
       txtEmail.setEnabled(true);
       txtTurma.setEnabled(true);
       
       bntAlterar.setEnabled(true);
       bntExcluir.setEnabled(true);
       
   }
   
    public consultaAlunos() {
        initComponents();
        carregarTabela();
        limparCampos();
    }

    private void limparCampos(){
    
    txtID.setText("");
    txtNome.setText("");
    txtTurma.setText("");
    txtEmail.setText("");
    
    txtID.requestFocus();
    }
    
    
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtTurma = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        bntAlterar = new javax.swing.JButton();
        bntExcluir = new javax.swing.JButton();
        bntAtualizar = new javax.swing.JButton();
        bntLimpar = new javax.swing.JButton();
        bntFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAlunos = new javax.swing.JTable();

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtID.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "ID do Aluno:"));
        txtID.addActionListener(this::txtIDActionPerformed);

        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nome do Aluno:"));
        txtNome.addActionListener(this::txtNomeActionPerformed);

        txtTurma.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Turma do Aluno:"));
        txtTurma.addActionListener(this::txtTurmaActionPerformed);

        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "E-mail do Aluno:"));
        txtEmail.addActionListener(this::txtEmailActionPerformed);

        btnLocalizar.setText("🔎 Localizar");
        btnLocalizar.addActionListener(this::btnLocalizarActionPerformed);

        bntAlterar.setText("🎨 Alterar");
        bntAlterar.addActionListener(this::bntAlterarActionPerformed);

        bntExcluir.setText("❌ Excluir");

        bntAtualizar.setText("🔄️ Atualizar");

        bntLimpar.setText("🚮 Limpar");

        bntFechar.setText("🔐 Fechar");
        bntFechar.addActionListener(this::bntFecharActionPerformed);

        tabelaAlunos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAlunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaAlunos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnLocalizar))
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTurma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(bntAlterar)
                        .addGap(28, 28, 28)
                        .addComponent(bntExcluir)
                        .addGap(29, 29, 29)
                        .addComponent(bntAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(bntLimpar)
                        .addGap(38, 38, 38)
                        .addComponent(bntFechar)
                        .addGap(41, 41, 41))))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocalizar))
                .addGap(20, 20, 20)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntAlterar)
                    .addComponent(bntExcluir)
                    .addComponent(bntAtualizar)
                    .addComponent(bntLimpar)
                    .addComponent(bntFechar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTurmaActionPerformed
       
    }//GEN-LAST:event_txtTurmaActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        
    }//GEN-LAST:event_txtEmailActionPerformed

    private void bntFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFecharActionPerformed
       
    }//GEN-LAST:event_bntFecharActionPerformed

    private void tabelaAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAlunosMouseClicked
        
    }//GEN-LAST:event_tabelaAlunosMouseClicked

    private void bntAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAlterarActionPerformed
        if (txtID.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty() 
            || txtTurma.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()){
           JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        return;
    }
        int rp = JOptionPane.showConfirmDialog(this,
                "Realmente deseja alterar os dados deste aluno?",
                "Confirmação da Alteração dos Dados.",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (rp != JOptionPane.YES_OPTION){
        return;
    }
       int id = Integer.parseInt(txtID.getText().trim());
       String nome = txtNome.getText().trim();
       String turma = txtTurma.getText().trim();
       String email = txtEmail.getText().trim();
       
       Aluno aluno = new Aluno();
       
       boolean alterado = aluno.alterar(id, nome, turma, email);
       if (alterado) {
          JOptionPane.showMessageDialog(this, "Aluno alterado com sucesso!");
          carregarTabela();
          limparCampos();
    } else {
     JOptionPane.showMessageDialog(this, "Não foi possivel alterar o aluno.");
    }
       
       
    }//GEN-LAST:event_bntAlterarActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        if(txtID.getText().trim().isEmpty()) {
         JOptionPane.showMessageDialog(this, "Digite o ID do aluno.");
         return;
        }
        
        try {
                int id = Integer.parseInt(txtID.getText().trim());
                Aluno aluno = new Aluno();
                
                Aluno encontrado = aluno.buscarPorId(id);
                
                if(encontrado != null){
                txtNome.setText(encontrado.getNome());
                txtTurma.setText(encontrado.getTurma());
                txtEmail.setText(encontrado.getEmail());
                
                JOptionPane.showMessageDialog(this, "Aluno localizado com sucesso.");
                } else { 
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado.");
                }}
                 catch (NumberFormatException erro) {
                        JOptionPane.showMessageDialog(this, "Digite um id númerico válido.");
                limparCampos();
                ativarCampos();
                }
    }//GEN-LAST:event_btnLocalizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new consultaAlunos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAlterar;
    private javax.swing.JButton bntAtualizar;
    private javax.swing.JButton bntExcluir;
    private javax.swing.JButton bntFechar;
    private javax.swing.JButton bntLimpar;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaAlunos;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTurma;
    // End of variables declaration//GEN-END:variables
}
