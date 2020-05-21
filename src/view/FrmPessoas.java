package view;

import bll.PessoaBLL;
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
import model.Endereco;
import model.Pessoa;

public class FrmPessoas extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    PessoaBLL pessoaBll = new PessoaBLL();
    Pessoa pessoa = new Pessoa();

    Vector<Endereco> vetorEnderecos;

    public FrmPessoas() {
        criarTabela();
        consultar();
        initComponents();
        preencherCbxEnderecos();
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
        tblPessoas = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Email");
        modelo.addColumn("Data de nascimento");
        modelo.addColumn("Endereço");

        tblPessoas.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblPessoas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Pessoa> lista = new ArrayList<Pessoa>();

        lista = pessoaBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getNome(),
                    lista.get(i).getCpf(),
                    lista.get(i).getEmail(),
                    lista.get(i).getDataNascimento(),
                    lista.get(i).getIdEndereco().getCep()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        pessoa = pessoaBll.consultaPorId(id);
        txtNome.setText(pessoa.getNome());
        txtCpf.setText(pessoa.getCpf());
        txtEmail.setText(pessoa.getEmail());
        txtDataNascimento.setText(convertDate(pessoa.getDataNascimento()));
        cbxEnderecos.setSelectedItem(pessoa.getIdEndereco());
    }

    private void limparCampos() {
        txtNome.setText("");
        cbxEnderecos.setSelectedIndex(0);
        txtCpf.setValue("");
        txtEmail.setText("");
        txtDataNascimento.setText("");
        btnSalvar.setEnabled(true);
    }

    private void preencherCbxEnderecos() {
        vetorEnderecos = pessoaBll.listarEnderecos();
        cbxEnderecos.setModel(new DefaultComboBoxModel(vetorEnderecos));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxEnderecos = new javax.swing.JComboBox<>();
        btnAdicionarEndereco = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();
        btnAdicionarContato = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de pessoas");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 10, 32, 20);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(30, 30, 290, 28);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" CPF");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(350, 10, 30, 20);

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
        txtCpf.setBounds(350, 30, 150, 28);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Email");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 60, 30, 20);

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(30, 80, 290, 28);

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
        txtDataNascimento.setBounds(350, 80, 150, 28);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data Nascimento");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(350, 60, 100, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(" Endereço");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 110, 70, 20);

        getContentPane().add(cbxEnderecos);
        cbxEnderecos.setBounds(30, 130, 360, 28);

        btnAdicionarEndereco.setText("+");
        btnAdicionarEndereco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarEndereco);
        btnAdicionarEndereco.setBounds(390, 130, 41, 28);

        tblPessoas.setModel(modelo);
        tblPessoas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPessoasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPessoas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 510, 170);

        btnAdicionarContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_contato.png"))); // NOI18N
        btnAdicionarContato.setToolTipText("Cadastrar novo contato");
        btnAdicionarContato.setContentAreaFilled(false);
        btnAdicionarContato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarContatoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarContato);
        btnAdicionarContato.setBounds(440, 120, 70, 50);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(230, 350, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(300, 350, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(370, 350, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(440, 350, 55, 41);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 610, 460);

        setSize(new java.awt.Dimension(536, 433));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            pessoa.setNome(txtNome.getText());
            pessoa.setCpf(txtCpf.getText());
            pessoa.setEmail(txtEmail.getText());
            pessoa.setDataNascimento(CriarNovaData(txtDataNascimento.getText()));
            pessoa.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));

            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtEmail.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {

                if (isEmail(txtEmail.getText()) && isCPF(txtCpf.getText()) && isData(txtDataNascimento.getText())
                        && !pessoaBll.verificarCPFsIguais(txtCpf.getText())) {
                    pessoaBll.salvar(pessoa);
                    consultar();
                    limparCampos();
                } else {
                    if (pessoaBll.verificarCPFsIguais(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtEmail.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                pessoaBll.remover(pessoaBll.consultaPorId(pessoa.getCodigo()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            pessoa.setNome(txtNome.getText());
            pessoa.setCpf(txtCpf.getText());
            pessoa.setEmail(txtEmail.getText());
            pessoa.setDataNascimento(CriarNovaData(txtDataNascimento.getText()));
            pessoa.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));

            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtEmail.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                pessoaBll.editar(pessoa);
                consultar();
                limparCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO EDITAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
        preencherCbxEnderecos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPessoasMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblPessoas.getSelectedRow();
        Integer codigo = (Integer) tblPessoas.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblPessoasMouseClicked

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtEmail.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtEmailKeyTyped

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
            java.util.logging.Logger.getLogger(FrmPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPessoas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarContato;
    private javax.swing.JButton btnAdicionarEndereco;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxEnderecos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPessoas;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
