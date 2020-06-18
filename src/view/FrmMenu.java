package view;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class FrmMenu extends javax.swing.JFrame {

    FrmClientes telaClientes;
    FrmFuncionarios telaFuncionarios;
    FrmImoveis telaImoveis;
    FrmVendas telaVendas;
    FrmProprietarios telaPropietarios;
    FrmRelatorios telaRelatorios;
    FrmEstatisticas telaEstatisticas;
    FrmLogin telaLogin;

    public FrmMenu(String nome, String cargo) {
        initComponents();
        lblusuario.setText(nome);
        lblcargo.setText(cargo);
        lblData.setText(getDataAtual());
    }

    private FrmMenu() {
        initComponents();
        lblData.setText(getDataAtual());
    }
    
    private String getDataAtual(){
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblcargo = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        TeladeFundo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuItemCliente = new javax.swing.JMenuItem();
        MenuItemFuncionario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuItemImovel = new javax.swing.JMenuItem();
        MenuItemProprietario = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuItemVenda = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MenuItemRelatorios = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MenuItemEstatistixas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuário:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 450, 50, 30);

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblusuario.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblusuario);
        lblusuario.setBounds(70, 450, 460, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cargo: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 480, 50, 30);

        lblcargo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblcargo.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblcargo);
        lblcargo.setBounds(70, 480, 340, 30);

        lblData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblData);
        lblData.setBounds(1000, 490, 80, 20);

        TeladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_Menu.jpg"))); // NOI18N
        getContentPane().add(TeladeFundo);
        TeladeFundo.setBounds(0, 0, 1200, 540);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_pessoa.png"))); // NOI18N
        jMenu1.setText("    ");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuItemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_pessoa.png"))); // NOI18N
        MenuItemCliente.setText("Cadastrar Cliente");
        MenuItemCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemClienteActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemCliente);

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

        MenuItemProprietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_proprietario.png"))); // NOI18N
        MenuItemProprietario.setText("Cadastrar Proprietário");
        MenuItemProprietario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemProprietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemProprietarioActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItemProprietario);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_venda.png"))); // NOI18N
        jMenu3.setText("   ");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuItemVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_venda.png"))); // NOI18N
        MenuItemVenda.setText("Gerenciar Vendas");
        MenuItemVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemVendaActionPerformed(evt);
            }
        });
        jMenu3.add(MenuItemVenda);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_relatorios.png"))); // NOI18N
        jMenu4.setText("    ");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuItemRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_relatorios.png"))); // NOI18N
        MenuItemRelatorios.setText("Relatórios");
        MenuItemRelatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemRelatoriosActionPerformed(evt);
            }
        });
        jMenu4.add(MenuItemRelatorios);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/cadastrar_estatisticas.png"))); // NOI18N
        jMenu5.setText("       ");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuItemEstatistixas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_estatisticas.png"))); // NOI18N
        MenuItemEstatistixas.setText("Estátisticas");
        MenuItemEstatistixas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemEstatistixas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemEstatistixasActionPerformed(evt);
            }
        });
        jMenu5.add(MenuItemEstatistixas);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1096, 653));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemClienteActionPerformed
        if (lblcargo.getText().equals("Corretor")) {
            telaClientes = FrmClientes.getTelaCliente();
            telaClientes.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_MenuItemClienteActionPerformed

    private void MenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemFuncionarioActionPerformed
        if (lblcargo.getText().equals("Administrador")) {
            telaFuncionarios = FrmFuncionarios.getTelaFuncionario();
            telaFuncionarios.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_MenuItemFuncionarioActionPerformed

    private void MenuItemImovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemImovelActionPerformed
        if (lblcargo.getText().equals("Corretor")) {
            telaImoveis = FrmImoveis.getTelaImovel();
            telaImoveis.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_MenuItemImovelActionPerformed

    private void MenuItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemVendaActionPerformed
        if (lblcargo.getText().equals("Corretor")) {
            telaVendas = FrmVendas.getTelaVenda();
            telaVendas.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_MenuItemVendaActionPerformed

    private void MenuItemProprietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemProprietarioActionPerformed
        if (lblcargo.getText().equals("Corretor")) {
            telaPropietarios = FrmProprietarios.getTelaProprietario();
            telaPropietarios.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MenuItemProprietarioActionPerformed

    private void MenuItemRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemRelatoriosActionPerformed
        if (lblcargo.getText().equals("Diretor")) {
            telaRelatorios = FrmRelatorios.getTelaRelatorio();
            telaRelatorios.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MenuItemRelatoriosActionPerformed

    private void MenuItemEstatistixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemEstatistixasActionPerformed
        if (lblcargo.getText().equals("Diretor")) {
            telaEstatisticas = FrmEstatisticas.getTelaEstatistica();
            telaEstatisticas.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "SEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MenuItemEstatistixasActionPerformed

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
    private javax.swing.JMenuItem MenuItemCliente;
    private javax.swing.JMenuItem MenuItemEstatistixas;
    private javax.swing.JMenuItem MenuItemFuncionario;
    private javax.swing.JMenuItem MenuItemImovel;
    private javax.swing.JMenuItem MenuItemProprietario;
    private javax.swing.JMenuItem MenuItemRelatorios;
    private javax.swing.JMenuItem MenuItemVenda;
    private javax.swing.JLabel TeladeFundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblcargo;
    private javax.swing.JLabel lblusuario;
    // End of variables declaration//GEN-END:variables
}
