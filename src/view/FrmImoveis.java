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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblImoveis = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarCategoria = new javax.swing.JButton();
        btnAdicionarEnderecos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox<>();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de imóveis");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" Data");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 40, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText(" Categoria");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(160, 10, 60, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Endereço");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(400, 10, 70, 20);

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
        txtDataBaixa.setBounds(270, 130, 100, 28);

        getContentPane().add(cbxCategorias);
        cbxCategorias.setBounds(160, 30, 170, 28);

        getContentPane().add(cbxEnderecos);
        cbxEnderecos.setBounds(400, 30, 260, 28);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText(" Metros");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 60, 50, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(" Nº Suítes");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(160, 60, 60, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(" NºQuartos");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(270, 60, 70, 20);
        getContentPane().add(txtMetros);
        txtMetros.setBounds(30, 80, 100, 28);
        getContentPane().add(txtNumeroSuites);
        txtNumeroSuites.setBounds(160, 80, 90, 28);
        getContentPane().add(txtNumeroQuartos);
        txtNumeroQuartos.setBounds(270, 80, 100, 28);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText(" Descrição");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(400, 60, 60, 20);
        getContentPane().add(txtMotivo);
        txtMotivo.setBounds(400, 130, 300, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText(" Situação");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(160, 110, 60, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText(" Data da baixa");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(270, 110, 100, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText(" Motivo");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(400, 110, 60, 20);

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
        txtDataInscrição.setBounds(30, 30, 100, 28);
        getContentPane().add(txtDescricao);
        txtDescricao.setBounds(400, 80, 300, 28);

        tblImoveis.setModel(modelo);
        tblImoveis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tblImoveis);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 710, 180);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(400, 360, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(470, 360, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEditar);
        btnEditar.setBounds(540, 360, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(610, 360, 55, 41);

        btnAdicionarCategoria.setText("+");
        btnAdicionarCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCategoria);
        btnAdicionarCategoria.setBounds(330, 30, 41, 28);

        btnAdicionarEnderecos.setText("+");
        btnAdicionarEnderecos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarEnderecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEnderecosActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarEnderecos);
        btnAdicionarEnderecos.setBounds(660, 30, 41, 28);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText(" Preço");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 110, 50, 20);
        getContentPane().add(txtPreco);
        txtPreco.setBounds(30, 130, 80, 28);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText(" R$");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(110, 130, 20, 30);

        cbxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Disponivel", "Indisponivel" }));
        getContentPane().add(cbxSituacao);
        cbxSituacao.setBounds(160, 130, 90, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 790, 430);

        setSize(new java.awt.Dimension(734, 439));
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
    private javax.swing.JComboBox<String> cbxCategorias;
    private javax.swing.JComboBox<String> cbxEnderecos;
    private javax.swing.JComboBox<String> cbxSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblImoveis;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtDataBaixa;
    private javax.swing.JFormattedTextField txtDataInscrição;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtMetros;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNumeroQuartos;
    private javax.swing.JTextField txtNumeroSuites;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
