package view;

public class FrmMenu extends javax.swing.JFrame {

    FrmPessoas telaPessoas;
    FrmFuncionarios telaFuncionarios;
    FrmImoveis telaImoveis;
    FrmVendas telaVendas;

    public FrmMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        TeladeFundo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuItemPessoa = new javax.swing.JMenuItem();
        MenuItemFuncionario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuItemImovel = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuário:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 430, 50, 15);
        getContentPane().add(usuario);
        usuario.setBounds(70, 430, 460, 20);

        TeladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_Menu.jpg"))); // NOI18N
        getContentPane().add(TeladeFundo);
        TeladeFundo.setBounds(0, 0, 1020, 570);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_pessoa.png"))); // NOI18N
        jMenu1.setText("    ");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuItemPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_pessoa.png"))); // NOI18N
        MenuItemPessoa.setText("Cadastrar Pessoa");
        MenuItemPessoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemPessoaActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemPessoa);

        MenuItemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_funcionario.png"))); // NOI18N
        MenuItemFuncionario.setText("Cadastrar Funcionário");
        MenuItemFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemFuncionarioActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemFuncionario);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_imovel.png"))); // NOI18N
        jMenu2.setText("     ");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuItemImovel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_imovel.png"))); // NOI18N
        MenuItemImovel.setText("Cadastrar Imóvel");
        MenuItemImovel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemImovel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemImovelActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItemImovel);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_venda.png"))); // NOI18N
        jMenu3.setText("   ");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_venda.png"))); // NOI18N
        jMenuItem1.setText("Gerenciar Vendas");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_relatorios.png"))); // NOI18N
        jMenu4.setText("    ");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_relatorios.png"))); // NOI18N
        jMenuItem2.setText("Relatórios");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_estatisticas.png"))); // NOI18N
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_estatisticas.png"))); // NOI18N
        jMenuItem3.setText("Estátisticas");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(998, 596));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemPessoaActionPerformed
        if (telaPessoas == null) {
            telaPessoas = new FrmPessoas();
            telaPessoas.setVisible(true);
        } else {
            telaPessoas.setVisible(true);
            telaPessoas.setResizable(false);
        }
    }//GEN-LAST:event_MenuItemPessoaActionPerformed

    private void MenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemFuncionarioActionPerformed
        if (telaFuncionarios == null) {
            telaFuncionarios = new FrmFuncionarios();
            telaFuncionarios.setVisible(true);
        } else {
            telaFuncionarios.setVisible(true);
            telaFuncionarios.setResizable(false);
        }
    }//GEN-LAST:event_MenuItemFuncionarioActionPerformed

    private void MenuItemImovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemImovelActionPerformed
        if (telaImoveis == null) {
            telaImoveis = new FrmImoveis();
            telaImoveis.setVisible(true);
        } else {
            telaImoveis.setVisible(true);
            telaImoveis.setResizable(false);
        }
    }//GEN-LAST:event_MenuItemImovelActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (telaVendas == null) {
            telaVendas = new FrmVendas();
            telaVendas.setVisible(true);
        } else {
            telaVendas.setVisible(true);
            telaVendas.setResizable(false);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemFuncionario;
    private javax.swing.JMenuItem MenuItemImovel;
    private javax.swing.JMenuItem MenuItemPessoa;
    private javax.swing.JLabel TeladeFundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
