package view;

import bll.FuncionarioBLL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cargo;
import model.Funcionario;
import model.Contato;
import model.Endereco;

public class FrmFuncionarios extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    FuncionarioBLL funcionarioBll = new FuncionarioBLL();
    Funcionario funcionario = new Funcionario();

    Vector<Endereco> vetorEnderecos;
    Vector<Contato> vetorContatos;
    Vector<Cargo> vetorCargos;

    public FrmFuncionarios() {
        criarTabela();
        consultar();
        initComponents();
        preencherCbxs();
    }

    private Date CriarNovaData(String data) {
        if (data == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        java.sql.Date a = null;
        try {
            a = new java.sql.Date(format.parse(data).getTime());
        } catch (ParseException e) {
        }
        return a;
    }

    private String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formatter.format(dtConsulta);
        } catch (Exception e) {
            return null;
        }
    }

    private void criarTabela() {
        tblFuncionarios = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Email");
        modelo.addColumn("Data nascimento");
        modelo.addColumn("Endereço");
        modelo.addColumn("Contato");
        modelo.addColumn("Cargo");
        modelo.addColumn("PIS");
        modelo.addColumn("Nº Contrato");
        modelo.addColumn("Senha");

        tblFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(8).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(9).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(10).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Funcionario> lista = new ArrayList<Funcionario>();

        lista = funcionarioBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getNome(),
                    lista.get(i).getCpf(),
                    lista.get(i).getEmail(),
                    lista.get(i).getDataNascimento(),
                    lista.get(i).getIdEndereco().getCep(),
                    lista.get(i).getIdContato().getNumero(),
                    lista.get(i).getIdCargo().getDescricao(),
                    lista.get(i).getPis(),
                    lista.get(i).getnContrato(),
                    lista.get(i).getSenha()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        funcionario = funcionarioBll.consultaPorId(id);
        txtNome.setText(funcionario.getNome());
        txtCpf.setText(funcionario.getCpf());
        txtEmail.setText(funcionario.getEmail());
        txtDataNascimento.setText(convertDate(funcionario.getDataNascimento()));
        cbxEnderecos.setSelectedItem(funcionario.getIdEndereco());
        cbxContatos.setSelectedItem(funcionario.getIdContato());
        cbxCargos.setSelectedItem(funcionario.getIdCargo());
        txtPis.setText(funcionario.getPis());
        txtNcontrato.setText(funcionario.getnContrato());
        txtSenhaAcesso.setText(funcionario.getSenha());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setValue("");
        txtEmail.setText("");
        txtDataNascimento.setText("");
        cbxEnderecos.setSelectedIndex(0);
        cbxContatos.setSelectedIndex(0);
        cbxCargos.setSelectedIndex(0);
        txtPis.setValue("");
        txtNcontrato.setText("");
        txtSenhaAcesso.setText("");
        btnSalvar.setEnabled(true);
    }

    private void preencherCbxs() {
        vetorEnderecos = funcionarioBll.listarEnderecos();
        vetorContatos = funcionarioBll.listarContatos();
        vetorCargos = funcionarioBll.listarCargos();

        cbxEnderecos.setModel(new DefaultComboBoxModel(vetorEnderecos));
        cbxContatos.setModel(new DefaultComboBoxModel(vetorContatos));
        cbxCargos.setModel(new DefaultComboBoxModel(vetorCargos));
    }

    private boolean isEmail(String email) {
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean isData(String data) {
        String[] dataparticionada = data.split("/");
        int dia = Integer.parseInt(dataparticionada[0]);
        int mes = Integer.parseInt(dataparticionada[1]);
        int ano = Integer.parseInt(dataparticionada[2]);
        boolean anoBissexto = ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;

        if (((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia >= 1 && dia <= 31))
                || ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia >= 1 && dia <= 30))
                || ((mes == 2) && (anoBissexto) && (dia >= 1 && dia <= 29) && (ano >= 1920 && ano <= 2002))
                || ((mes == 2) && !(anoBissexto) && (dia >= 1 && dia <= 28) && (ano >= 1920 && ano <= 2002))) {

            return true;
        } else {
            return false;
        }
    }

    private boolean isCPF(String CPF) {
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private boolean isPIS(String pis) {
        String multiplicador = "3298765432";
        int total = 0;
        boolean resultado = true;

        if (resultado) {
            for (int i = 0; i < 10; i++) {

                int multiplicando = Integer.parseInt(pis.substring(i, i + 1));
                int totalMultiplicador = Integer.parseInt(multiplicador.substring(i, i + 1));
                total += multiplicando * totalMultiplicador;
            }

            int resto = 11 - total % 11;
            resto = resto == 10 || resto == 11 ? 0 : resto;

            int digito = 99;

            digito = Integer.parseInt("" + pis.charAt(10));
            resultado = resto == digito;
        }

        return resultado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNcontrato = new javax.swing.JTextField();
        cbxCargos = new javax.swing.JComboBox<>();
        btnAdicionarCargo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPis = new javax.swing.JFormattedTextField();
        txtSenhaAcesso = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        cbxEnderecos = new javax.swing.JComboBox<>();
        btnAdicionarEndereco = new javax.swing.JButton();
        cbxContatos = new javax.swing.JComboBox<>();
        btnAdicionarContato = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnGerarSenha = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de funcionários");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" PIS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(650, 60, 30, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Nº contrato");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(650, 10, 80, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Cargo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(360, 110, 40, 20);

        txtNcontrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNcontratoKeyTyped(evt);
            }
        });
        getContentPane().add(txtNcontrato);
        txtNcontrato.setBounds(650, 30, 140, 28);

        cbxCargos.setToolTipText("");
        getContentPane().add(cbxCargos);
        cbxCargos.setBounds(360, 130, 220, 28);

        btnAdicionarCargo.setText("+");
        btnAdicionarCargo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCargoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCargo);
        btnAdicionarCargo.setBounds(580, 130, 41, 28);

        tblFuncionarios.setModel(modelo);
        tblFuncionarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFuncionarios);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 180, 810, 220);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(470, 410, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(540, 410, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(680, 410, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(610, 410, 55, 41);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(" Senha de acesso");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(650, 110, 110, 20);

        try {
            txtPis.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPisKeyTyped(evt);
            }
        });
        getContentPane().add(txtPis);
        txtPis.setBounds(650, 80, 140, 28);

        txtSenhaAcesso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSenhaAcessoKeyTyped(evt);
            }
        });
        getContentPane().add(txtSenhaAcesso);
        txtSenhaAcesso.setBounds(650, 130, 140, 28);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nome");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 10, 32, 20);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(40, 30, 290, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Email");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 60, 30, 20);

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(40, 80, 290, 28);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText(" CPF");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(40, 110, 30, 20);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(40, 130, 130, 28);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Data Nascimento");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(200, 110, 100, 20);

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });
        txtDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataNascimentoKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataNascimento);
        txtDataNascimento.setBounds(200, 130, 130, 28);

        getContentPane().add(cbxEnderecos);
        cbxEnderecos.setBounds(360, 30, 220, 28);

        btnAdicionarEndereco.setText("+");
        btnAdicionarEndereco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarEndereco);
        btnAdicionarEndereco.setBounds(580, 30, 41, 28);

        getContentPane().add(cbxContatos);
        cbxContatos.setBounds(360, 80, 220, 28);

        btnAdicionarContato.setText("+");
        btnAdicionarContato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarContatoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarContato);
        btnAdicionarContato.setBounds(580, 80, 41, 28);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Contato");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(360, 60, 50, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText(" Endereço");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(360, 10, 70, 20);

        btnGerarSenha.setBackground(new java.awt.Color(153, 153, 255));
        btnGerarSenha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnGerarSenha.setForeground(new java.awt.Color(255, 255, 255));
        btnGerarSenha.setText("Gerar senha");
        btnGerarSenha.setContentAreaFilled(false);
        btnGerarSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGerarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerarSenha);
        btnGerarSenha.setBounds(700, 160, 100, 10);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 860, 490);

        setSize(new java.awt.Dimension(834, 491));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    FrmCargos telaCargos;

    private void btnAdicionarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCargoActionPerformed
        if (telaCargos == null) {
            telaCargos = new FrmCargos();
            telaCargos.setVisible(true);
        } else {
            telaCargos.setVisible(true);
            telaCargos.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarCargoActionPerformed

    private void txtPisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtPisKeyTyped

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            funcionario.setNome(txtNome.getText());
            funcionario.setCpf(txtCpf.getText());
            funcionario.setEmail(txtEmail.getText());
            funcionario.setDataNascimento(CriarNovaData(txtDataNascimento.getText()));
            funcionario.setPis(txtPis.getText());
            funcionario.setnContrato(txtNcontrato.getText());
            funcionario.setSenha(txtSenhaAcesso.getText());
            funcionario.setIdCargo(vetorCargos.get(cbxCargos.getSelectedIndex()));
            funcionario.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));
            funcionario.setIdContato(vetorContatos.get(cbxContatos.getSelectedIndex()));

            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtDataNascimento.getText().isEmpty() || txtPis.getText().isEmpty()
                    || txtNcontrato.getText().isEmpty() || txtSenhaAcesso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (isPIS(txtPis.getText()) && isEmail(txtEmail.getText()) && isCPF(txtCpf.getText()) && isData(txtDataNascimento.getText())
                        && !funcionarioBll.verificarCpfIgual(txtCpf.getText()) && !funcionarioBll.verificarPisIgual(txtPis.getText())) {
                    funcionarioBll.salvar(funcionario);
                    consultar();
                    limparCampos();
                } else {
                    if (!isPIS(txtPis.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "PIS INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!isEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "EMAIL INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!isCPF(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!isData(txtDataNascimento.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "DATA NASCIMENTO INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (funcionarioBll.verificarCpfIgual(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (funcionarioBll.verificarPisIgual(txtPis.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "PIS JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtDataNascimento.getText().isEmpty() || txtPis.getText().isEmpty()
                    || txtNcontrato.getText().isEmpty() || txtSenhaAcesso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                funcionarioBll.remover(funcionarioBll.consultaPorId(funcionario.getCodigo()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            funcionario.setNome(txtNome.getText());
            funcionario.setCpf(txtCpf.getText());
            funcionario.setEmail(txtEmail.getText());
            funcionario.setDataNascimento(CriarNovaData(txtDataNascimento.getText()));
            funcionario.setPis(txtPis.getText());
            funcionario.setnContrato(txtNcontrato.getText());
            funcionario.setSenha(txtSenhaAcesso.getText());
            funcionario.setIdCargo(vetorCargos.get(cbxCargos.getSelectedIndex()));
            funcionario.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));
            funcionario.setIdContato(vetorContatos.get(cbxContatos.getSelectedIndex()));

            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtDataNascimento.getText().isEmpty() || txtPis.getText().isEmpty()
                    || txtNcontrato.getText().isEmpty() || txtSenhaAcesso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (isPIS(txtPis.getText()) && isEmail(txtEmail.getText()) && isCPF(txtCpf.getText()) && isData(txtDataNascimento.getText())) {
                    funcionarioBll.editar(funcionario);
                    consultar();
                    limparCampos();
                } else {
                    if (!isPIS(txtPis.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "PIS INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!isEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "EMAIL INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!isCPF(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!isData(txtDataNascimento.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "DATA NASCIMENTO INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO EDITAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
        preencherCbxs();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionariosMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblFuncionarios.getSelectedRow();
        Integer codigo = (Integer) tblFuncionarios.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblFuncionariosMouseClicked

    private void txtSenhaAcessoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaAcessoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtSenhaAcesso.getText().length();
        if (comprimentoDeCampo >= 12) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, " SENHA MUITO LONGA!\n(Maximo de 12 caracteres)", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtSenhaAcessoKeyTyped

    private void txtNcontratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNcontratoKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtNcontrato.getText().length();
        if (comprimentoDeCampo >= 15) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 15 NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNcontratoKeyTyped

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtNome.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE LETRAS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNomeKeyTyped

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtEmail.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCpfKeyTyped

    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed

    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void txtDataNascimentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataNascimentoKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDataNascimentoKeyTyped

    FrmEnderecos telaEnderecos;
    FrmContatos telaContatos;

    private void btnAdicionarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarEnderecoActionPerformed
        if (telaEnderecos == null) {
            telaEnderecos = new FrmEnderecos();
            telaEnderecos.setVisible(true);
        } else {
            telaEnderecos.dispose();
            telaEnderecos.setVisible(true);
            telaEnderecos.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarEnderecoActionPerformed

    private void btnAdicionarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarContatoActionPerformed
        if (telaContatos == null) {
            telaContatos = new FrmContatos();
            telaContatos.setVisible(true);
        } else {
            telaContatos.dispose();
            telaContatos.setVisible(true);
            telaContatos.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarContatoActionPerformed

    private void btnGerarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarSenhaActionPerformed
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String senha = "";

        for (int x = 0; x < 10; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }
        
        txtSenhaAcesso.setText(senha);
    }//GEN-LAST:event_btnGerarSenhaActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFuncionarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCargo;
    private javax.swing.JButton btnAdicionarContato;
    private javax.swing.JButton btnAdicionarEndereco;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGerarSenha;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxCargos;
    private javax.swing.JComboBox<String> cbxContatos;
    private javax.swing.JComboBox<String> cbxEnderecos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNcontrato;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtPis;
    private javax.swing.JPasswordField txtSenhaAcesso;
    // End of variables declaration//GEN-END:variables
}
