package view;

import javax.swing.table.DefaultTableModel;


public class FrmVendas extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();

    public FrmVendas() {
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
        cbxFuncionarios = new javax.swing.JComboBox<>();
        cbxClientes = new javax.swing.JComboBox<>();
        cbxImoveis = new javax.swing.JComboBox<>();
        txtValor = new javax.swing.JTextField();
        txtComissao = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarCliente = new javax.swing.JButton();
        btnAdicionarImovel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de vendas");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Percentual da Comissão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(470, 10, 160, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Valor");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(330, 10, 40, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cliente");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 60, 50, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Imóvel");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(330, 60, 50, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Funcionário");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 10, 80, 20);

        getContentPane().add(cbxFuncionarios);
        cbxFuncionarios.setBounds(20, 30, 280, 28);

        getContentPane().add(cbxClientes);
        cbxClientes.setBounds(20, 80, 240, 28);

        getContentPane().add(cbxImoveis);
        cbxImoveis.setBounds(330, 80, 240, 28);
        getContentPane().add(txtValor);
        txtValor.setBounds(330, 30, 100, 28);
        getContentPane().add(txtComissao);
        txtComissao.setBounds(470, 30, 140, 28);

        tblVendas.setModel(modelo);
        tblVendas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tblVendas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 620, 170);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(310, 300, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(380, 300, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEditar);
        btnEditar.setBounds(450, 300, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(520, 300, 55, 41);

        btnAdicionarCliente.setText("+");
        btnAdicionarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCliente);
        btnAdicionarCliente.setBounds(260, 80, 41, 28);

        btnAdicionarImovel.setText("+");
        btnAdicionarImovel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarImovel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarImovelActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarImovel);
        btnAdicionarImovel.setBounds(570, 80, 41, 28);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("R$");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(430, 30, 20, 30);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(-10, 0, 760, 400);

        setSize(new java.awt.Dimension(646, 379));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    FrmFuncionarios telaFuncionarios;
    FrmPessoas telaPessoas;
    FrmImoveis telaImoveis;
    
    private void btnAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarClienteActionPerformed
        if (telaPessoas == null) {
            telaPessoas = new FrmPessoas();
            telaPessoas.setVisible(true);
        } else {
            telaPessoas.setVisible(true);
            telaPessoas.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarClienteActionPerformed

    private void btnAdicionarImovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarImovelActionPerformed
        if (telaImoveis == null) {
            telaImoveis = new FrmImoveis();
            telaImoveis.setVisible(true);
        } else {
            telaImoveis.setVisible(true);
            telaImoveis.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarImovelActionPerformed

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
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCliente;
    private javax.swing.JButton btnAdicionarImovel;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxClientes;
    private javax.swing.JComboBox<String> cbxFuncionarios;
    private javax.swing.JComboBox<String> cbxImoveis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVendas;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtComissao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
