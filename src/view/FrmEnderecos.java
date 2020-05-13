package view;

import javax.swing.table.DefaultTableModel;


public class FrmEnderecos extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();


    public FrmEnderecos() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        cbxBairros = new javax.swing.JComboBox<>();
        txtLogradouro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEnderecos = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarBairro = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de endereços");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" Logradouro");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(190, 60, 70, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("CEP");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 24, 30, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Bairro");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(200, 20, 34, 30);
        getContentPane().add(txtCep);
        txtCep.setBounds(50, 20, 120, 28);

        getContentPane().add(cbxBairros);
        cbxBairros.setBounds(240, 20, 180, 28);
        getContentPane().add(txtLogradouro);
        txtLogradouro.setBounds(20, 90, 440, 30);

        tblEnderecos.setModel(modelo);
        tblEnderecos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tblEnderecos);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 140, 460, 180);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(190, 330, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(260, 330, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEditar);
        btnEditar.setBounds(330, 330, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(400, 330, 55, 41);

        btnAdicionarBairro.setText("+");
        btnAdicionarBairro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarBairroActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarBairro);
        btnAdicionarBairro.setBounds(420, 20, 41, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 510, 390);

        setSize(new java.awt.Dimension(486, 413));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    FrmBairros telaBairros;
    
    private void btnAdicionarBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarBairroActionPerformed
        if (telaBairros == null) {
            telaBairros = new FrmBairros();
            telaBairros.setVisible(true);
        } else {
            telaBairros.setVisible(true);
            telaBairros.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarBairroActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEnderecos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarBairro;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxBairros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEnderecos;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtLogradouro;
    // End of variables declaration//GEN-END:variables
}
