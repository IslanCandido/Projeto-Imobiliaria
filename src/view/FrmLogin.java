/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bll.FuncionarioBLL;
import javax.swing.JOptionPane;
import model.Funcionario;

public class FrmLogin extends javax.swing.JFrame {

    Funcionario funcionario = new Funcionario();
    FuncionarioBLL funcionarioBLL = new FuncionarioBLL();

    FrmMenu menu;

    public FrmLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSenhaAdmin = new javax.swing.JPasswordField();
        IconeUsuario = new javax.swing.JLabel();
        IconeSenha = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnEsqueceuSenha = new javax.swing.JButton();
        Logotipo = new javax.swing.JLabel();
        btnAdministrador = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login de funcionários");
        setResizable(false);
        getContentPane().setLayout(null);

        IconeUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_usuario.png"))); // NOI18N
        getContentPane().add(IconeUsuario);
        IconeUsuario.setBounds(30, 140, 40, 50);

        IconeSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_senha.png"))); // NOI18N
        getContentPane().add(IconeSenha);
        IconeSenha.setBounds(30, 180, 40, 50);

        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(70, 150, 170, 30);

        btnLogin.setBackground(new java.awt.Color(51, 51, 51));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Fazer Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(50, 250, 190, 40);

        btnEsqueceuSenha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEsqueceuSenha.setForeground(new java.awt.Color(51, 0, 255));
        btnEsqueceuSenha.setText("Esqueceu a senha?");
        btnEsqueceuSenha.setToolTipText("");
        btnEsqueceuSenha.setContentAreaFilled(false);
        btnEsqueceuSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEsqueceuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsqueceuSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEsqueceuSenha);
        btnEsqueceuSenha.setBounds(110, 220, 150, 23);

        Logotipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/logotipo_ilustrativo.png"))); // NOI18N
        getContentPane().add(Logotipo);
        Logotipo.setBounds(80, 0, 120, 140);

        btnAdministrador.setBackground(new java.awt.Color(51, 51, 51));
        btnAdministrador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdministrador.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministrador.setText("Administrador");
        btnAdministrador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministradorActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministrador);
        btnAdministrador.setBounds(50, 300, 190, 40);

        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSenhaKeyTyped(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(70, 190, 170, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_login.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, -20, 360, 440);

        setSize(new java.awt.Dimension(286, 392));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void limparCampos() {
        txtSenha.setText("");
        txtUsuario.setText("");
    }


    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "TODOS OS CAMPOS SÃO OBRIGATORIOS!", "Atenção!", JOptionPane.WARNING_MESSAGE);
        } else {
            if (!funcionarioBLL.autenticarLogin(txtUsuario.getText(), txtSenha.getText())) {
                JOptionPane.showMessageDialog(rootPane, "USUÁRIO OU SENHA INVALIDA!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                limparCampos();
            } else {
                String nome = funcionarioBLL.pegarNome(txtUsuario.getText());
                String cargo = funcionarioBLL.pegarCargo(txtUsuario.getText());

                menu = new FrmMenu(nome, cargo);
                menu.setVisible(true);
                dispose();
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtUsuario.getText().length();
        if (comprimentoDeCampo >= 11) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "   LIMITE DE 11 CARACTERES!\n(Se ja for cadastrado digite seu cpf)", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtSenha.getText().length();
        if (comprimentoDeCampo >= 12) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, " MAXIMO DE 12 CARACTERES!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtSenhaKeyTyped

    private void btnAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministradorActionPerformed
        JOptionPane.showConfirmDialog(rootPane, new Object[]{txtSenhaAdmin}, "Senha de acesso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String senhaDigitada = new String(txtSenhaAdmin.getPassword());

        if ("admin".equals(senhaDigitada)) {
            menu = new FrmMenu("Administrador", "Administrador");
            menu.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, "SENHA INVALIDA!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            txtSenha.setText("");
        }
    }//GEN-LAST:event_btnAdministradorActionPerformed

    private void btnEsqueceuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsqueceuSenhaActionPerformed

    }//GEN-LAST:event_btnEsqueceuSenhaActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IconeSenha;
    private javax.swing.JLabel IconeUsuario;
    private javax.swing.JLabel Logotipo;
    private javax.swing.JButton btnAdministrador;
    private javax.swing.JButton btnEsqueceuSenha;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPasswordField txtSenhaAdmin;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
