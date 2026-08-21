
package view;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import model.Usuario;
import util.SessaoUsuario;


public class frmPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmPrincipal.class.getName());

    
    public frmPrincipal() {
        initComponents();
        setLocationRelativeTo(null);

    setExtendedState(
            javax.swing.JFrame.MAXIMIZED_BOTH
    );
    
    setDefaultCloseOperation(
        javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
);

    carregarUsuario();

    aplicarPermissoes();
    }
    
    private void confirmarSaida() {

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente encerrar o FerragemGK?",
                    "Sair",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            == JOptionPane.YES_OPTION
    ) {

        System.exit(0);
    }
}
    
    private void carregarUsuario() {

    Usuario usuario =
            SessaoUsuario.getUsuarioLogado();

    if (usuario != null) {

        lblUsuario.setText(
                "Usuário: "
                + usuario.getNome()
        );

        lblNivel.setText(
                "Nível: "
                + usuario.getNivel()
        );

    } else {

        lblUsuario.setText(
                "Usuário: não identificado"
        );

        lblNivel.setText(
                "Nível: não identificado"
        );
    }
}

    private void aplicarPermissoes() {

    if (SessaoUsuario.isMaster()) {

        mnuUsuarios.setEnabled(true);

        mnuContasPagar.setEnabled(true);

    } else {

        mnuUsuarios.setEnabled(false);

        mnuContasPagar.setEnabled(false);
    }
}
    private void abrirTela(
        JInternalFrame tela
) {

    for (
            JInternalFrame frame :
            desktopPrincipal.getAllFrames()
    ) {

        if (
                frame.getClass()
                .equals(
                        tela.getClass()
                )
        ) {

            try {

                frame.setSelected(true);

                if (frame.isIcon()) {

                    frame.setIcon(false);
                }

            } catch (Exception erro) {

                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível selecionar a tela."
                );
            }

            frame.toFront();

            return;
        }
    }

    desktopPrincipal.add(tela);

    tela.setVisible(true);

    centralizarInternalFrame(tela);
}
    
    private void centralizarInternalFrame(
        JInternalFrame tela
) {

    int x =
            (
                desktopPrincipal.getWidth()
                - tela.getWidth()
            ) / 2;

    int y =
            (
                desktopPrincipal.getHeight()
                - tela.getHeight()
            ) / 2;

    if (x < 0) {

        x = 0;
    }

    if (y < 0) {

        y = 0;
    }

    tela.setLocation(x, y);
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPrincipal = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        itemSistema = new javax.swing.JMenu();
        mnuLogout = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        itemCadastros = new javax.swing.JMenu();
        mnuClientes = new javax.swing.JMenuItem();
        mnuFornecedores = new javax.swing.JMenuItem();
        mnuProdutos = new javax.swing.JMenuItem();
        mnuUsuarios = new javax.swing.JMenuItem();
        itemMovimentos = new javax.swing.JMenu();
        mnuCompras = new javax.swing.JMenuItem();
        mnuVendas = new javax.swing.JMenuItem();
        itemFinanceiro = new javax.swing.JMenu();
        mnuContasPagar = new javax.swing.JMenuItem();
        mnuContasReceber = new javax.swing.JMenuItem();
        itemAjuda = new javax.swing.JMenu();
        mnuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        desktopPrincipal.setLayer(lblUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPrincipal.setLayer(lblNivel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPrincipalLayout = new javax.swing.GroupLayout(desktopPrincipal);
        desktopPrincipal.setLayout(desktopPrincipalLayout);
        desktopPrincipalLayout.setHorizontalGroup(
            desktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPrincipalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
                .addComponent(lblNivel)
                .addGap(25, 25, 25))
        );
        desktopPrincipalLayout.setVerticalGroup(
            desktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPrincipalLayout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addGroup(desktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblNivel))
                .addGap(20, 20, 20))
        );

        itemSistema.setText("Sistema");

        mnuLogout.setText("Logout");
        mnuLogout.addActionListener(this::mnuLogoutActionPerformed);
        itemSistema.add(mnuLogout);

        mnuSair.setText("Sair");
        mnuSair.addActionListener(this::mnuSairActionPerformed);
        itemSistema.add(mnuSair);

        jMenuBar1.add(itemSistema);

        itemCadastros.setText("Cadastros");
        itemCadastros.addActionListener(this::itemCadastrosActionPerformed);

        mnuClientes.setText("Clientes");
        mnuClientes.addActionListener(this::mnuClientesActionPerformed);
        itemCadastros.add(mnuClientes);

        mnuFornecedores.setText("Fornecedores");
        mnuFornecedores.addActionListener(this::mnuFornecedoresActionPerformed);
        itemCadastros.add(mnuFornecedores);

        mnuProdutos.setText("Produtos");
        mnuProdutos.addActionListener(this::mnuProdutosActionPerformed);
        itemCadastros.add(mnuProdutos);

        mnuUsuarios.setText("Usuários");
        mnuUsuarios.addActionListener(this::mnuUsuariosActionPerformed);
        itemCadastros.add(mnuUsuarios);

        jMenuBar1.add(itemCadastros);

        itemMovimentos.setText("Movimentos");
        itemMovimentos.addActionListener(this::itemMovimentosActionPerformed);

        mnuCompras.setText("Compras");
        mnuCompras.addActionListener(this::mnuComprasActionPerformed);
        itemMovimentos.add(mnuCompras);

        mnuVendas.setText("Vendas");
        mnuVendas.addActionListener(this::mnuVendasActionPerformed);
        itemMovimentos.add(mnuVendas);

        jMenuBar1.add(itemMovimentos);

        itemFinanceiro.setText("Financeiro");

        mnuContasPagar.setText("Contas a pagar");
        mnuContasPagar.addActionListener(this::mnuContasPagarActionPerformed);
        itemFinanceiro.add(mnuContasPagar);

        mnuContasReceber.setText("Contas a receber");
        mnuContasReceber.addActionListener(this::mnuContasReceberActionPerformed);
        itemFinanceiro.add(mnuContasReceber);

        jMenuBar1.add(itemFinanceiro);

        itemAjuda.setText("Ajuda");

        mnuSobre.setText("Sobre");
        mnuSobre.addActionListener(this::mnuSobreActionPerformed);
        itemAjuda.add(mnuSobre);

        jMenuBar1.add(itemAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLogoutActionPerformed
      int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja encerrar a sessão atual?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            == JOptionPane.YES_OPTION
    ) {

        SessaoUsuario.encerrar();

        frmLogin login =
                new frmLogin();

        login.setVisible(true);

        dispose();
}        
    }//GEN-LAST:event_mnuLogoutActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed

    confirmarSaida();
    
    }//GEN-LAST:event_mnuSairActionPerformed

    private void mnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuariosActionPerformed
       
        if (!SessaoUsuario.isMaster()) {

        JOptionPane.showMessageDialog(
                this,
                "Acesso permitido somente para usuários MASTER."
        );

        return;
    }
        abrirTela (
            new frmUsuario()
        );
    }//GEN-LAST:event_mnuUsuariosActionPerformed

    private void itemCadastrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadastrosActionPerformed

    }//GEN-LAST:event_itemCadastrosActionPerformed

    private void mnuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesActionPerformed
       abrirTela(
            new frmCliente()
    );
    }//GEN-LAST:event_mnuClientesActionPerformed

    private void mnuFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFornecedoresActionPerformed
       abrirTela(
          new frmFornecedor()
       );
    }//GEN-LAST:event_mnuFornecedoresActionPerformed

    private void mnuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProdutosActionPerformed
       abrirTela (
           new frmProduto()
       );
    }//GEN-LAST:event_mnuProdutosActionPerformed

    private void itemMovimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMovimentosActionPerformed
        
    }//GEN-LAST:event_itemMovimentosActionPerformed

    private void mnuVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVendasActionPerformed
       abrirTela(
            new frmVenda()
    );        
    }//GEN-LAST:event_mnuVendasActionPerformed

    private void mnuContasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuContasPagarActionPerformed
      if (!SessaoUsuario.isMaster()) {

        JOptionPane.showMessageDialog(
                this,
                "Acesso permitido somente para usuário MASTER."
        );

        return;
    }

    abrirTela(
            new frmContasPagar()
    );
    }//GEN-LAST:event_mnuContasPagarActionPerformed

    private void mnuContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuContasReceberActionPerformed
      abrirTela(
            new frmContasReceber()
    );
    }//GEN-LAST:event_mnuContasReceberActionPerformed

    private void mnuComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuComprasActionPerformed
       abrirTela(
            new frmCompra()
    );
    }//GEN-LAST:event_mnuComprasActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       confirmarSaida();
    }//GEN-LAST:event_formWindowClosing

    private void mnuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSobreActionPerformed
        JOptionPane.showMessageDialog(
            this,
            "FerragemGK\n"
            + "Sistema desenvolvido em Java Swing\n"
            + "Banco de dados PostgreSQL"
        );
    }//GEN-LAST:event_mnuSobreActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frmPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPrincipal;
    private javax.swing.JMenu itemAjuda;
    private javax.swing.JMenu itemCadastros;
    private javax.swing.JMenu itemFinanceiro;
    private javax.swing.JMenu itemMovimentos;
    private javax.swing.JMenu itemSistema;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem mnuClientes;
    private javax.swing.JMenuItem mnuCompras;
    private javax.swing.JMenuItem mnuContasPagar;
    private javax.swing.JMenuItem mnuContasReceber;
    private javax.swing.JMenuItem mnuFornecedores;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenuItem mnuProdutos;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenuItem mnuSobre;
    private javax.swing.JMenuItem mnuUsuarios;
    private javax.swing.JMenuItem mnuVendas;
    // End of variables declaration//GEN-END:variables
}
