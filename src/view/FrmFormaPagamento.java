package view;

import bll.FormaPagamentoBLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.FormaPagamento;

public class FrmFormaPagamento extends javax.swing.JFrame {
    private static FrmFormaPagamento telaCategoriaGeral = null;
    
    DefaultTableModel modelo = new DefaultTableModel();
    FormaPagamentoBLL formaPagamentoBll = new FormaPagamentoBLL();
    FormaPagamento formaPagamento = new FormaPagamento();

    public static FrmFormaPagamento getTelaFormaPagamento() {
        if (telaCategoriaGeral == null) {
            telaCategoriaGeral = new FrmFormaPagamento();
        }
        return telaCategoriaGeral;
    }
    
    public FrmFormaPagamento() {
        criarTabela();
        consultar();
        initComponents();
    }

    private void criarTabela() {
        tblFormasDePagamentos = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Forma de Pagamento");

        tblFormasDePagamentos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblFormasDePagamentos.getColumnModel().getColumn(1).setPreferredWidth(30);

    }

    private void consultar() {
        modelo.setNumRows(0);
        List<FormaPagamento> lista;

        lista = formaPagamentoBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getFormaPagamento()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }
    
    private void preencheCampos(int id) {
        formaPagamento = formaPagamentoBll.consultaPorId(id);
        txtFormaDePagamento.setText(formaPagamento.getFormaPagamento());
    }

    private void limparCampos() {
        txtFormaDePagamento.setText("");
        btnSalvar.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtFormaDePagamento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFormasDePagamentos = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Forma de pagamento");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Forma de pagamento");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 121, 20);

        txtFormaDePagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFormaDePagamentoKeyTyped(evt);
            }
        });
        getContentPane().add(txtFormaDePagamento);
        txtFormaDePagamento.setBounds(20, 30, 290, 28);

        tblFormasDePagamentos.setModel(modelo);
        tblFormasDePagamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblFormasDePagamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormasDePagamentosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFormasDePagamentos);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 320, 90);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(130, 170, 55, 41);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(70, 170, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(190, 170, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(250, 170, 55, 41);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 380, 290);

        setSize(new java.awt.Dimension(345, 247));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFormaDePagamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFormaDePagamentoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtFormaDePagamento.getText().length();
        if (comprimentoDeCampo >= 30) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 30 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE LETRAS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtFormaDePagamentoKeyTyped

    private void tblFormasDePagamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormasDePagamentosMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblFormasDePagamentos.getSelectedRow();
        Integer codigo = (Integer) tblFormasDePagamentos.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblFormasDePagamentosMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtFormaDePagamento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (formaPagamentoBll.remover(formaPagamentoBll.consultaPorId(formaPagamento.getCodigo()))) {
                    JOptionPane.showMessageDialog(rootPane, "Removido com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao remover!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            formaPagamento.setFormaPagamento(txtFormaDePagamento.getText());

            if (txtFormaDePagamento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!formaPagamentoBll.verificarFormaPagamentoIgual(txtFormaDePagamento.getText())) {
                    if (formaPagamentoBll.salvar(formaPagamento)) {
                        JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "CATEGORIA JÁ FOI CADASTRADA!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            formaPagamento.setFormaPagamento(txtFormaDePagamento.getText());

            if (txtFormaDePagamento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (formaPagamentoBll.editar(formaPagamento)) {
                    JOptionPane.showMessageDialog(rootPane, "Editado com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao editar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                }
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
            java.util.logging.Logger.getLogger(FrmFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFormaPagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFormasDePagamentos;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtFormaDePagamento;
    // End of variables declaration//GEN-END:variables
}
