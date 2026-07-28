
package tela;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import src.Professores;


public class CadastroProfessores extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CadastroProfessores.class.getName());

    public CadastroProfessores() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        txtEmail = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbCadastroProfessores = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtDisciplina = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "E-mail do Professor:"));

        txtTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Telefone do Professor:"));

        btnCadastrar.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); 
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);

        btnLimpar.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); 
        btnLimpar.setText("Limpar");
        btnLimpar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnConsultar.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); 
        btnConsultar.setText("Consultar");
        btnConsultar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConsultar.addActionListener(this::btnConsultarActionPerformed);

        btnSair.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14));
        btnSair.setText("Sair");
        btnSair.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSair.addActionListener(this::btnSairActionPerformed);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        lbCadastroProfessores.setBackground(new java.awt.Color(255, 255, 255));
        lbCadastroProfessores.setFont(new java.awt.Font("Gabriola", 1, 36));
        lbCadastroProfessores.setForeground(new java.awt.Color(255, 255, 255));
        lbCadastroProfessores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCadastroProfessores.setText("Cadastro de Professores");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbCadastroProfessores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lbCadastroProfessores, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nome do Professor:"));

        txtDisciplina.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Disciplina em seu currículo:"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmail)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtNome)
                            .addComponent(txtDisciplina))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnLimpar)
                    .addComponent(btnSair)
                    .addComponent(btnConsultar))
                .addGap(0, 49, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {

        String nome = txtNome.getText().trim();
        String disciplina = txtDisciplina.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (nome.isEmpty() || disciplina.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Preencha os campos obrigatórios: Nome, Disciplina, E-mail e Telefone.",
                "Campos obrigatórios",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Professores professor = new Professores();
        professor.setNome(nome);
        professor.setDisciplina(disciplina);
        professor.setEmail(email);
        professor.setTelefone(telefone);

        boolean sucesso = professor.cadastrarProfessores();

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                "Professor cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this,
                "Não foi possível cadastrar o professor. Verifique se o email já está em uso ou se o banco está acessível.",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

     private void limparCampos() {
    txtNome.setText("");
    txtDisciplina.setText("");
    txtEmail.setText("");
    txtTelefone.setText("");
    txtNome.requestFocus();
}
    
    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        ConsultaProfessores tela = new ConsultaProfessores();
        tela.setVisible(true);
        this.setVisible(false);
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente sair?",
            "Confirmar saída.",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            dispose();
            System.exit(0);
        }
    }

    
    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(() -> new CadastroProfessores().setVisible(true));
    }

    
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCadastroProfessores;
    private javax.swing.JTextField txtDisciplina;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
   
}
