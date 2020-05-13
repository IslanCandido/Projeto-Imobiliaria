package view;

import javax.swing.table.DefaultTableModel;


public class FrmImoveis extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();

    public FrmImoveis() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDataBaixa = new javax.swing.JFormattedTextField();
        cbxCategorias = new javax.swing.JComboBox<>();
        cbxEnderecos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMetros = new javax.swing.JTextField();
        txtNumeroSuites = new javax.swing.JTextField();
        txtNumeroQuartos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDataInscrição = new javax.swing.JFormattedTextField();
        txtDescricao = new javax.swing.JTextField();
        rbDisponivel = new javax.swing.JRadioButton();
        rbIndisponivel = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblImoveis = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarCategoria = new javax.swing.JButton();
        btnAdicionarEnderecos = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de imóveis");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 40, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Categoria");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 10, 60, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Endereço");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(350, 10, 70, 20);

        try {
            txtDataBaixa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataBaixaActionPerformed(evt);
            }
        });
        txtDataBaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataBaixaKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataBaixa);
        txtDataBaixa.setBounds(160, 150, 140, 28);

        getContentPane().add(cbxCategorias);
        cbxCategorias.setBounds(140, 30, 140, 28);

        getContentPane().add(cbxEnderecos);
        cbxEnderecos.setBounds(350, 30, 200, 28);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Metros");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(620, 10, 50, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº Suítes");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 70, 60, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("NºQuartos");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(120, 70, 70, 20);
        getContentPane().add(txtMetros);
        txtMetros.setBounds(620, 30, 70, 28);
        getContentPane().add(txtNumeroSuites);
        txtNumeroSuites.setBounds(20, 90, 70, 28);
        getContentPane().add(txtNumeroQuartos);
        txtNumeroQuartos.setBounds(120, 90, 70, 28);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Descrição");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(220, 70, 60, 20);
        getContentPane().add(txtMotivo);
        txtMotivo.setBounds(330, 150, 360, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Situação");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 130, 60, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Data da baixa");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(160, 130, 100, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Motivo");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(330, 130, 60, 14);

        try {
            txtDataInscrição.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataInscrição.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInscriçãoActionPerformed(evt);
            }
        });
        txtDataInscrição.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataInscriçãoKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataInscrição);
        txtDataInscrição.setBounds(20, 30, 90, 28);
        getContentPane().add(txtDescricao);
        txtDescricao.setBounds(220, 90, 470, 28);

        buttonGroup1.add(rbDisponivel);
        rbDisponivel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbDisponivel.setText("DISPONÍVEL");
        getContentPane().add(rbDisponivel);
        rbDisponivel.setBounds(20, 150, 100, 23);

        buttonGroup1.add(rbIndisponivel);
        rbIndisponivel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbIndisponivel.setText("INDISPONÍVEL");
        getContentPane().add(rbIndisponivel);
        rbIndisponivel.setBounds(20, 170, 120, 23);

        tblImoveis.setModel(modelo);
        tblImoveis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tblImoveis);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 200, 690, 210);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(380, 420, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(450, 420, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEditar);
        btnEditar.setBounds(520, 420, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(590, 420, 55, 41);

        btnAdicionarCategoria.setText("+");
        btnAdicionarCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCategoria);
        btnAdicionarCategoria.setBounds(280, 30, 41, 28);

        btnAdicionarEnderecos.setText("+");
        btnAdicionarEnderecos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarEnderecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEnderecosActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarEnderecos);
        btnAdicionarEnderecos.setBounds(550, 30, 41, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, -10, 740, 510);

        setSize(new java.awt.Dimension(716, 505));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataBaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataBaixaActionPerformed

    }//GEN-LAST:event_txtDataBaixaActionPerformed

    private void txtDataBaixaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataBaixaKeyTyped

    }//GEN-LAST:event_txtDataBaixaKeyTyped

    private void txtDataInscriçãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInscriçãoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInscriçãoActionPerformed

    private void txtDataInscriçãoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataInscriçãoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInscriçãoKeyTyped

    FrmCategorias telaCategorias;
    FrmEnderecos telaEnderecos;
    
    private void btnAdicionarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCategoriaActionPerformed
        if (telaCategorias == null) {
            telaCategorias = new FrmCategorias();
            telaCategorias.setVisible(true);
        } else {
            telaCategorias.setVisible(true);
            telaCategorias.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarCategoriaActionPerformed

    private void btnAdicionarEnderecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarEnderecosActionPerformed
        if (telaEnderecos == null) {
            telaEnderecos = new FrmEnderecos();
            telaEnderecos.setVisible(true);
        } else {
            telaEnderecos.setVisible(true);
            telaEnderecos.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarEnderecosActionPerformed

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
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmImoveis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCategoria;
    private javax.swing.JButton btnAdicionarEnderecos;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxCategorias;
    private javax.swing.JComboBox<String> cbxEnderecos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbDisponivel;
    private javax.swing.JRadioButton rbIndisponivel;
    private javax.swing.JTable tblImoveis;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtDataBaixa;
    private javax.swing.JFormattedTextField txtDataInscrição;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtMetros;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNumeroQuartos;
    private javax.swing.JTextField txtNumeroSuites;
    // End of variables declaration//GEN-END:variables
}
