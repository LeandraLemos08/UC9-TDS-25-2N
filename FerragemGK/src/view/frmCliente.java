
package view;

import dao.ClienteDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import util.SessaoUsuario;

public class frmCliente extends javax.swing.JInternalFrame {

    private final ClienteDAO clienteDAO =
        new ClienteDAO();
        
    private long idClienteSelecionado = 0;
    
    private void configurarTela() {

    txtCodigo.setEditable(false);

    btnExcluir.setEnabled(
            SessaoUsuario.isMaster()
    );

    tabelaClientes.setSelectionMode(
            javax.swing.ListSelectionModel
                    .SINGLE_SELECTION
    );

    tabelaClientes.setAutoCreateRowSorter(
            true
    );

    tabelaClientes.setModel(
            criarModeloTabela()
    );
}
    
    private DefaultTableModel criarModeloTabela() {

    return new DefaultTableModel(
            new Object[]{
                "Código",
                "Nome",
                "CPF",
                "Telefone",
                "Email",
                "Cidade",
                "Estado",
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
        List<Cliente> clientes
) {

    DefaultTableModel modelo =
            (DefaultTableModel)
            tabelaClientes.getModel();

    modelo.setRowCount(0);

    for (
            Cliente cliente :
            clientes
    ) {

        modelo.addRow(
                new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getCidade(),
                    cliente.getUf(),
                    cliente.isAtivo()
                            ? "Sim"
                            : "Não"
                }
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
    
    private void listarClientes() {

    try {

        preencherTabela(
                clienteDAO.listarTodos()
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar clientes.\n"
                + erro.getMessage()
        );
    }
}
    
    private void preencherCampos(
        Cliente cliente
) {

    idClienteSelecionado =
            cliente.getIdCliente();

    txtCodigo.setText(
            String.valueOf(
                    cliente.getIdCliente()
            )
    );

    txtNome.setText(
            valorTexto(
                    cliente.getNome()
            )
    );

    txtCpf.setText(
            valorTexto(
                    cliente.getCpf()
            )
    );

    txtTelefone.setText(
            valorTexto(
                    cliente.getTelefone()
            )
    );

    txtEmail.setText(
            valorTexto(
                    cliente.getEmail()
            )
    );

    txtEndereco.setText(
            valorTexto(
                    cliente.getEndereco()
            )
    );

    txtNumero.setText(
            valorTexto(
                    cliente.getNumero()
            )
    );

    txtComplemento.setText(
            valorTexto(
                    cliente.getComplemento()
            )
    );

    txtBairro.setText(
            valorTexto(
                    cliente.getBairro()
            )
    );

    txtCidade.setText(
            valorTexto(
                    cliente.getCidade()
            )
    );

    txtCep.setText(
            valorTexto(
                    cliente.getCep()
            )
    );

    if (
            cliente.getUf() != null
            && !cliente
                    .getUf()
                    .isBlank()
    ) {

        cmbEstado.setSelectedItem(
                cliente.getUf()
        );

    } else {

        cmbEstado.setSelectedIndex(0);
    }

    chkAtivo.setSelected(
            cliente.isAtivo()
    );
}
    
    private void limparCampos() {

    idClienteSelecionado = 0;

    txtCodigo.setText("");
    txtNome.setText("");
    txtCpf.setText("");
    txtTelefone.setText("");
    txtEmail.setText("");
    txtEndereco.setText("");
    txtNumero.setText("");
    txtComplemento.setText("");
    txtBairro.setText("");
    txtCidade.setText("");
    txtCep.setText("");

    cmbEstado.setSelectedIndex(0);

    chkAtivo.setSelected(true);

    txtNome.requestFocus();
}
    
    private boolean validarCampos() {

    if (
            txtNome
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe o nome do cliente."
        );

        txtNome.requestFocus();

        return false;
    }

    if (
            cmbEstado.getSelectedIndex() == 0
            && !txtCidade
                    .getText()
                    .trim()
                    .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione a UF."
        );

        cmbEstado.requestFocus();

        return false;
    }

    return true;
}
    
    private Cliente criarClienteComCampos() {

    Cliente cliente =
            new Cliente();

    cliente.setIdCliente(
            idClienteSelecionado
    );

    cliente.setNome(
            txtNome
            .getText()
            .trim()
    );

    cliente.setCpf(
            txtCpf
            .getText()
            .trim()
    );

    cliente.setTelefone(
            txtTelefone
            .getText()
            .trim()
    );

    cliente.setEmail(
            txtEmail
            .getText()
            .trim()
    );

    cliente.setEndereco(
            txtEndereco
            .getText()
            .trim()
    );

    cliente.setNumero(
            txtNumero
            .getText()
            .trim()
    );

    cliente.setComplemento(
            txtComplemento
            .getText()
            .trim()
    );

    cliente.setBairro(
            txtBairro
            .getText()
            .trim()
    );

    cliente.setCidade(
            txtCidade
            .getText()
            .trim()
    );

    if (
            cmbEstado.getSelectedIndex() > 0
    ) {

        cliente.setUf(
                cmbEstado
                .getSelectedItem()
                .toString()
        );

    } else {

        cliente.setUf("");
    }

    cliente.setCep(
            txtCep
            .getText()
            .trim()
    );

    cliente.setAtivo(
            chkAtivo.isSelected()
    );

    return cliente;
}
    
    private void carregarClienteSelecionado() {

    int linha =
            tabelaClientes.getSelectedRow();

    if (linha == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um cliente na tabela."
        );

        return;
    }

    int linhaModelo =
            tabelaClientes.convertRowIndexToModel(
                    linha
            );

    long idCliente =
            Long.parseLong(
                    tabelaClientes
                    .getModel()
                    .getValueAt(
                            linhaModelo,
                            0
                    )
                    .toString()
            );

    try {

        Cliente cliente =
                clienteDAO.buscarPorId(
                        idCliente
                );

        if (cliente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente não encontrado."
            );

            return;
        }

        preencherCampos(
                cliente
        );

        tabCliente.setSelectedIndex(
                0
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar cliente.\n"
                + erro.getMessage()
        );
    }
}
    
    public frmCliente() {
        initComponents();
        
    configurarTela();

    listarClientes();

    limparCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabCliente = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        cmbEstado = new javax.swing.JComboBox<>();
        chkAtivo = new javax.swing.JCheckBox();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbFiltrar = new javax.swing.JComboBox<>();
        txtPesquisa = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        btnListarTodos = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        btnCarregar1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro e Consulta de Clientes");

        txtCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Código:"));
        txtCodigo.addActionListener(this::txtCodigoActionPerformed);

        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nome:"));
        txtNome.addActionListener(this::txtNomeActionPerformed);

        txtCpf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "CPF:"));
        txtCpf.addActionListener(this::txtCpfActionPerformed);

        txtTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Telefone:"));
        txtTelefone.addActionListener(this::txtTelefoneActionPerformed);

        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "E-mail:"));
        txtEmail.addActionListener(this::txtEmailActionPerformed);

        txtEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Endereço:"));
        txtEndereco.addActionListener(this::txtEnderecoActionPerformed);

        txtCep.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "CEP:"));
        txtCep.addActionListener(this::txtCepActionPerformed);

        txtNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Número:"));
        txtNumero.addActionListener(this::txtNumeroActionPerformed);

        txtComplemento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Complemento:"));
        txtComplemento.addActionListener(this::txtComplementoActionPerformed);

        txtBairro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Bairro:"));
        txtBairro.addActionListener(this::txtBairroActionPerformed);

        txtCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Cidade:"));
        txtCidade.addActionListener(this::txtCidadeActionPerformed);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        cmbEstado.setSelectedIndex(-1);
        cmbEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Estado:"));
        cmbEstado.addActionListener(this::cmbEstadoActionPerformed);

        chkAtivo.setText("Ativo");

        btnSalvar.setBackground(new java.awt.Color(255, 153, 0));
        btnSalvar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Salvar alterações feitas no cadastro de um cliente");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnAlterar.setBackground(new java.awt.Color(255, 0, 51));
        btnAlterar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Alterar");
        btnAlterar.setToolTipText("Alterar as informações de um cadastro de cliente");
        btnAlterar.addActionListener(this::btnAlterarActionPerformed);

        btnExcluir.setBackground(new java.awt.Color(204, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir um cadastro de cliente");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(0, 255, 0));
        btnCancelar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Cancelar tudo feito até agora");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnConsulta.setBackground(new java.awt.Color(255, 153, 153));
        btnConsulta.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        btnConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btnConsulta.setText("Consulta");
        btnConsulta.setToolTipText("Salvar alterações feitas no cadastro de um cliente");
        btnConsulta.addActionListener(this::btnConsultaActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtBairro)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(chkAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCpf))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        tabCliente.addTab("Cadastro", jPanel1);

        cmbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Nome", "CPF", "E-mail" }));
        cmbFiltrar.setSelectedIndex(-1);
        cmbFiltrar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Filtrar por:"));
        cmbFiltrar.addActionListener(this::cmbFiltrarActionPerformed);

        txtPesquisa.setToolTipText("Escreva o nome de um cliente para pesquisa");
        txtPesquisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPesquisa.addActionListener(this::txtPesquisaActionPerformed);

        btnLocalizar.setBackground(new java.awt.Color(153, 153, 153));
        btnLocalizar.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnLocalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnLocalizar.setText("Localizar");
        btnLocalizar.addActionListener(this::btnLocalizarActionPerformed);

        btnListarTodos.setBackground(new java.awt.Color(153, 153, 153));
        btnListarTodos.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnListarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnListarTodos.setText("Listar");
        btnListarTodos.setToolTipText("Listar todos os clientes");
        btnListarTodos.addActionListener(this::btnListarTodosActionPerformed);

        btnNovo.setBackground(new java.awt.Color(153, 153, 153));
        btnNovo.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Carregar clientes");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);

        btnCarregar1.setBackground(new java.awt.Color(153, 153, 153));
        btnCarregar1.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        btnCarregar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCarregar1.setText("Carregar");
        btnCarregar1.setToolTipText("Carregar clientes");
        btnCarregar1.addActionListener(this::btnCarregar1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cmbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLocalizar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCarregar1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(224, Short.MAX_VALUE)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListarTodos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(126, 126, 126))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cmbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLocalizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnListarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovo))
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCarregar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabCliente.addTab("Consulta", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabCliente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabCliente)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
     txtCodigo.setEditable(false);
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void txtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void cmbFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltrarActionPerformed
       String tipo = cmbFiltrar.getSelectedItem().toString();
        txtCodigo.setText("");
        if (tipo.equals("Código")) {
         txtCodigo.setToolTipText("Digite o código completo");
        } else if (tipo.equals("Nome")){
          txtCodigo.setToolTipText("Digite o nome ou parte dele");
        } else if (tipo.equals("CPF")) {
         txtCodigo.setToolTipText("Digite o cpf ou parte dele");
        } else if (tipo.equals("E-mail")) {
         txtCodigo.setToolTipText("Digite o e-mail ou parte dele ");
        } txtCodigo.requestFocus();
    }//GEN-LAST:event_cmbFiltrarActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
     if (
            !SessaoUsuario.isMaster()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Você não possui permissão para excluir clientes."
        );

        return;
    }

    if (
            idClienteSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um cliente antes de excluir."
        );

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este cliente?",
                    "Excluir Cliente",
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
                clienteDAO.excluir(
                        idClienteSelecionado
                );

        if (excluido) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente excluído com sucesso."
            );

            limparCampos();

            listarClientes();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente não encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível excluir o cliente.\n"
                + "O cliente pode possuir registros vinculados.\n"
                + erro.getMessage()
        );
    }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
   limparCampos();

    tabCliente.setSelectedIndex(0);

    }//GEN-LAST:event_btnNovoActionPerformed

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesMouseClicked
      carregarClienteSelecionado();
      
       if (
            evt.getClickCount() == 2
    ) {

        carregarClienteSelecionado();
    }
    }//GEN-LAST:event_tabelaClientesMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
 if (
            idClienteSelecionado != 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Existe um cliente carregado para edição.\n"
                + "Utilize Alterar ou clique em Novo."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    try {

        Cliente cliente =
                criarClienteComCampos();

        long codigo =
                clienteDAO.cadastrar(
                        cliente
                );

        JOptionPane.showMessageDialog(
                this,
                "Cliente cadastrado com sucesso.\n"
                + "Código: "
                + codigo
        );

        limparCampos();

        listarClientes();

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar o cliente.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
   String pesquisa =
            txtPesquisa
            .getText()
            .trim();

    String filtro =
            cmbFiltrar
            .getSelectedItem()
            .toString();

    if (pesquisa.isEmpty()) {

        listarClientes();

        return;
    }

    if (filtro.equals("Codigo")) {

        try {

            Long.parseLong(
                    pesquisa
            );

        } catch (
                NumberFormatException erro
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Para pesquisar por código informe apenas números."
            );

            txtPesquisa.requestFocus();

            return;
        }
    }

    try {

        
        List<Cliente> clientes =
                clienteDAO.pesquisar(
                        filtro,
                        pesquisa
                );

        preencherTabela(
                clientes
        );

        if (clientes.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum cliente encontrado."
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

    private void btnListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodosActionPerformed
       txtPesquisa.setText("");

       listarClientes();
    }//GEN-LAST:event_btnListarTodosActionPerformed

    private void btnCarregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregar1ActionPerformed
      carregarClienteSelecionado();
    }//GEN-LAST:event_btnCarregar1ActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
      if (
            idClienteSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um cliente antes de alterar."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja salvar as alterações deste cliente?",
                    "Alterar Cliente",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        Cliente cliente =
                criarClienteComCampos();

        boolean alterado =
                clienteDAO.alterar(
                        cliente
                );

        if (alterado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente alterado com sucesso."
            );

            limparCampos();

            listarClientes();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum registro foi alterado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível alterar o cliente.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCarregar1;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnListarTodos;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbFiltrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabCliente;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
