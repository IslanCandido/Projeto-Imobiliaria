package view;

import bll.CargoBLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cargo;

public class FrmCargos extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    CargoBLL cargoBll = new CargoBLL();
    Cargo cargo = new Cargo();

    public FrmCargos() {
        criarTabela();
        consultar();
        initComponents();
    }

    private void criarTabela() {
        tblCargos = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Cargo");
        modelo.addColumn("Salário");

        tblCargos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblCargos.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblCargos.getColumnModel().getColumn(1).setPreferredWidth(30);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Cargo> lista = new ArrayList<Cargo>();

        lista = cargoBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getDescricao(),
                    lista.get(i).getSalario()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        cargo = cargoBll.consultaPorId(id);
        txtCargo.setText(cargo.getDescricao());
        txtSalario.setText(String.valueOf(cargo.getSalario()));
    }

    private void limparCampos() {
        txtCargo.setText("");
        txtSalario.setText("");
        btnSalvar.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        txtSalario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCargos = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de cargos");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Cargo");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 33, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Salario");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 10, 39, 20);

        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCargoKeyTyped(evt);
            }
        });
        getContentPane().add(txtCargo);
        txtCargo.setBounds(30, 30, 160, 28);

        txtSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtSalario);
        txtSalario.setBounds(220, 30, 80, 28);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" R$");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(300, 30, 20, 28);

        tblCargos.setModel(modelo);
        tblCargos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblCargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCargosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCargos);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 330, 130);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(60, 210, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(130, 210, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(200, 210, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(270, 210, 55, 41);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 430, 360);

        setSize(new java.awt.Dimension(361, 295));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            cargo.setDescricao(txtCargo.getText());
            cargo.setSalario(Float.parseFloat(txtSalario.getText()));

            if (txtCargo.getText().isEmpty() || txtSalario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else if (cargo.getSalario() < 800 || cargo.getSalario() > 50000) {
                JOptionPane.showMessageDialog(rootPane, "PREÇO INVALIDO!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!cargoBll.verificarCargosIguais(txtCargo.getText())) {
                    cargoBll.salvar(cargo);
                    consultar();
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "CARGO JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtCargo.getText().isEmpty() || txtSalario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                cargoBll.remover(cargoBll.consultaPorId(cargo.getCodigo()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            cargo.setDescricao(txtCargo.getText());
            cargo.setSalario(Float.parseFloat(txtSalario.getText()));

            if (txtCargo.getText().isEmpty() || txtSalario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else if (cargo.getSalario() < 800 || cargo.getSalario() > 50000) {
                JOptionPane.showMessageDialog(rootPane, "PREÇO INVALIDO!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
            } else {

                cargoBll.editar(cargo);
                consultar();
                limparCampos();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO EDITAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblCargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCargosMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblCargos.getSelectedRow();
        Integer codigo = (Integer) tblCargos.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblCargosMouseClicked

    private void txtCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtCargo.getText().length();
        if (comprimentoDeCampo >= 25) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 25 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE LETRAS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCargoKeyTyped

    private void txtSalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtSalario.getText().length();
        if (comprimentoDeCampo >= 5) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 5 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtSalarioKeyTyped

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
            java.util.logging.Logger.getLogger(FrmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCargos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCargos;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
