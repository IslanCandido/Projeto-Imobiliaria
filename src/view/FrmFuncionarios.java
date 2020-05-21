package view;

import bll.FuncionarioBLL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cargo;
import model.Funcionario;
import model.Pessoa;

public class FrmFuncionarios extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    FuncionarioBLL funcionarioBll = new FuncionarioBLL();
    Funcionario funcionario = new Funcionario();

    Vector<Pessoa> vetorPessoas;
    Vector<Cargo> vetorCargos;

    public FrmFuncionarios() {
        criarTabela();
        consultar();
        initComponents();
        preencherCbxs();
    }

    private void criarTabela() {
        tblFuncionarios = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Cargo");
        modelo.addColumn("PIS");
        modelo.addColumn("N° Contrato");
        modelo.addColumn("Carteira de Trabalho");
        modelo.addColumn("Senha");

        tblFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Funcionario> lista = new ArrayList<Funcionario>();

        lista = funcionarioBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getIdPessoa().getNome(),
                    lista.get(i).getIdCargo().getDescricao(),
                    lista.get(i).getPis(),
                    lista.get(i).getnContrato(),
                    lista.get(i).getnCarteiraTrabalho(),
                    lista.get(i).getSenha()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        funcionario = funcionarioBll.consultaPorId(id);
        txtPis.setText(funcionario.getPis());
        txtNCartTrabalho.setText(funcionario.getnCarteiraTrabalho());
        txtNcontrato.setText(funcionario.getnContrato());
        txtSenhaAcesso.setText(funcionario.getSenha());
        cbxPessoas.setSelectedItem(funcionario.getIdCargo());
        cbxCargos.setSelectedItem(funcionario.getIdPessoa());
    }

    private void limparCampos() {
        txtPis.setValue("");
        txtNCartTrabalho.setValue("");
        txtNcontrato.setText("");
        txtSenhaAcesso.setText("");
        cbxPessoas.setSelectedIndex(0);
        cbxCargos.setSelectedIndex(0);
        btnSalvar.setEnabled(true);
    }

    private void preencherCbxs() {
        vetorPessoas = funcionarioBll.listarPessoas();
        vetorCargos = funcionarioBll.listarCargos();

        cbxPessoas.setModel(new DefaultComboBoxModel(vetorPessoas));
        cbxCargos.setModel(new DefaultComboBoxModel(vetorCargos));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNcontrato = new javax.swing.JTextField();
        cbxPessoas = new javax.swing.JComboBox<>();
        cbxCargos = new javax.swing.JComboBox<>();
        btnAdicionarPessoa = new javax.swing.JButton();
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
        txtNCartTrabalho = new javax.swing.JFormattedTextField();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de funcionários");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" PIS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(490, 10, 30, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText(" N° cart. trabalho");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(330, 10, 100, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Nº contrato");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(330, 60, 80, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Pessoa");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 10, 40, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Cargo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 60, 40, 20);

        txtNcontrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNcontratoKeyTyped(evt);
            }
        });
        getContentPane().add(txtNcontrato);
        txtNcontrato.setBounds(330, 80, 130, 28);

        cbxPessoas.setToolTipText("");
        getContentPane().add(cbxPessoas);
        cbxPessoas.setBounds(30, 30, 230, 28);

        cbxCargos.setToolTipText("");
        getContentPane().add(cbxCargos);
        cbxCargos.setBounds(30, 80, 230, 28);

        btnAdicionarPessoa.setText("+");
        btnAdicionarPessoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarPessoaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarPessoa);
        btnAdicionarPessoa.setBounds(260, 30, 41, 28);

        btnAdicionarCargo.setText("+");
        btnAdicionarCargo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCargoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCargo);
        btnAdicionarCargo.setBounds(260, 80, 41, 28);

        tblFuncionarios.setModel(modelo);
        tblFuncionarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFuncionarios);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 630, 190);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(300, 320, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(370, 320, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(510, 320, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(440, 320, 55, 41);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(" Senha de acesso");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(490, 60, 110, 20);

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
        txtPis.setBounds(490, 30, 130, 28);

        txtSenhaAcesso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSenhaAcessoKeyTyped(evt);
            }
        });
        getContentPane().add(txtSenhaAcesso);
        txtSenhaAcesso.setBounds(490, 80, 130, 28);

        try {
            txtNCartTrabalho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNCartTrabalho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNCartTrabalhoKeyTyped(evt);
            }
        });
        getContentPane().add(txtNCartTrabalho);
        txtNCartTrabalho.setBounds(330, 30, 130, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 690, 410);

        setSize(new java.awt.Dimension(656, 398));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    FrmCargos telaCargos;
    FrmPessoas telaPessoas;

    private void btnAdicionarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarPessoaActionPerformed
        if (telaPessoas == null) {
            telaPessoas = new FrmPessoas();
            telaPessoas.setVisible(true);
        } else {
            telaPessoas.setVisible(true);
            telaPessoas.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarPessoaActionPerformed

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
            funcionario.setPis(txtPis.getText());
            funcionario.setnCarteiraTrabalho(txtNCartTrabalho.getText());
            funcionario.setnContrato(txtNcontrato.getText());
            funcionario.setSenha(txtSenhaAcesso.getText());
            funcionario.setIdPessoa(vetorPessoas.get(cbxPessoas.getSelectedIndex()));
            funcionario.setIdCargo(vetorCargos.get(cbxCargos.getSelectedIndex()));

            if (txtPis.getText().isEmpty() || txtNCartTrabalho.getText().isEmpty()
                    || txtNcontrato.getText().isEmpty() || txtSenhaAcesso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (isPIS(txtPis.getText()) && !funcionarioBll.verificarFuncionariosIguais(funcionario.getIdPessoa().getCodigo())
                        && !funcionarioBll.verificarPisIgual(txtPis.getText()) && !funcionarioBll.verificarNCarteiraIguail(txtNCartTrabalho.getText())) {
                    funcionarioBll.salvar(funcionario);
                    consultar();
                    limparCampos();
                } else {
                    if (!isPIS(txtPis.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "PIS INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (funcionarioBll.verificarFuncionariosIguais(funcionario.getIdPessoa().getCodigo())) {
                        JOptionPane.showMessageDialog(rootPane, "PESSOA JÁ FOI CADASTRADA!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (funcionarioBll.verificarPisIgual(txtPis.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "PIS JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (funcionarioBll.verificarNCarteiraIguail(txtNCartTrabalho.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Nº CARTEIRA DE TRABALHO JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtPis.getText().isEmpty() || txtNCartTrabalho.getText().isEmpty()
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
            funcionario.setPis(txtPis.getText());
            funcionario.setnCarteiraTrabalho(txtNCartTrabalho.getText());
            funcionario.setnContrato(txtNcontrato.getText());
            funcionario.setSenha(txtSenhaAcesso.getText());
            funcionario.setIdPessoa(vetorPessoas.get(cbxPessoas.getSelectedIndex()));
            funcionario.setIdCargo(vetorCargos.get(cbxCargos.getSelectedIndex()));

            if (txtPis.getText().isEmpty() || txtNCartTrabalho.getText().isEmpty()
                    || txtNcontrato.getText().isEmpty() || txtSenhaAcesso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                funcionarioBll.editar(funcionario);
                consultar();
                limparCampos();
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

    private void txtNCartTrabalhoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCartTrabalhoKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNCartTrabalhoKeyTyped

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
    private javax.swing.JButton btnAdicionarPessoa;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxCargos;
    private javax.swing.JComboBox<String> cbxPessoas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtNCartTrabalho;
    private javax.swing.JTextField txtNcontrato;
    private javax.swing.JFormattedTextField txtPis;
    private javax.swing.JPasswordField txtSenhaAcesso;
    // End of variables declaration//GEN-END:variables
}
