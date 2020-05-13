package view;

import javax.swing.table.DefaultTableModel;


public class FrmFuncionarios extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();


    public FrmFuncionarios() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPIS = new javax.swing.JTextField();
        txtNCartTrabalho = new javax.swing.JTextField();
        txtNcontrato = new javax.swing.JTextField();
        cbxPessoas = new javax.swing.JComboBox<>();
        cbxCargos = new javax.swing.JComboBox<>();
        btnAdicionarPessoa = new javax.swing.JButton();
        btnAdicionarCargo = new javax.swing.JButton();
        btnAdicionarPerfil = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPerfis = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de funcionários");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" PIS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(420, 10, 30, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText(" N° cart. trabalho");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 10, 100, 14);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Nº contrato");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(270, 70, 80, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Pessoa");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 10, 40, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Cargo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 70, 40, 20);
        getContentPane().add(txtPIS);
        txtPIS.setBounds(420, 30, 110, 28);
        getContentPane().add(txtNCartTrabalho);
        txtNCartTrabalho.setBounds(270, 30, 120, 28);
        getContentPane().add(txtNcontrato);
        txtNcontrato.setBounds(270, 90, 120, 28);

        cbxPessoas.setToolTipText("");
        getContentPane().add(cbxPessoas);
        cbxPessoas.setBounds(20, 30, 180, 28);

        cbxCargos.setToolTipText("");
        getContentPane().add(cbxCargos);
        cbxCargos.setBounds(20, 90, 180, 28);

        btnAdicionarPessoa.setText("+");
        btnAdicionarPessoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarPessoaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarPessoa);
        btnAdicionarPessoa.setBounds(200, 30, 41, 28);

        btnAdicionarCargo.setText("+");
        btnAdicionarCargo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCargoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCargo);
        btnAdicionarCargo.setBounds(200, 90, 41, 28);

        btnAdicionarPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_perfil.png"))); // NOI18N
        btnAdicionarPerfil.setToolTipText("Cadastrar novo perfil");
        btnAdicionarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarPerfil);
        btnAdicionarPerfil.setBounds(440, 70, 70, 50);

        tblPerfis.setModel(modelo);
        tblPerfis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tblPerfis);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 140, 530, 190);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(250, 340, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(320, 340, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEditar);
        btnEditar.setBounds(390, 340, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(460, 340, 55, 41);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 570, 420);

        setSize(new java.awt.Dimension(557, 425));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    FrmPerfis telaPerfis;
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

    private void btnAdicionarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarPerfilActionPerformed
        if (telaPerfis == null) {
            telaPerfis = new FrmPerfis();
            telaPerfis.setVisible(true);
        } else {
            telaPerfis.setVisible(true);
            telaPerfis.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarPerfilActionPerformed

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
    private javax.swing.JButton btnAdicionarPerfil;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPerfis;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtNCartTrabalho;
    private javax.swing.JTextField txtNcontrato;
    private javax.swing.JTextField txtPIS;
    // End of variables declaration//GEN-END:variables
}
