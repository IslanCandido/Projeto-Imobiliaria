package view;

import bll.ContatoBLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Contato;

public class FrmContatos extends javax.swing.JFrame {

    private static FrmContatos telaContatosGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    ContatoBLL contatoBll = new ContatoBLL();
    Contato contato = new Contato();

    public static FrmContatos getTelaContato() {
        if (telaContatosGeral == null) {
            telaContatosGeral = new FrmContatos();
        }
        return telaContatosGeral;
    }

    private FrmContatos() {
        criarTabela();
        consultar();
        initComponents();

    }

    private void criarTabela() {
        tblContatos = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Numero");
        modelo.addColumn("Tipo");

        tblContatos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblContatos.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblContatos.getColumnModel().getColumn(2).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Contato> lista = new ArrayList<Contato>();

        lista = contatoBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getNumero(),
                    lista.get(i).getTipo()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        contato = contatoBll.consultaPorId(id);
        txtTelefone.setText(contato.getNumero());
        cbxTipoTelefone.setSelectedItem(contato.getTipo());
    }

    private void limparCampos() {
        txtTelefone.setValue("");
        cbxTipoTelefone.setSelectedIndex(0);
        btnSalvar.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxTipoTelefone = new javax.swing.JComboBox<>();
        txtTelefone = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContatos = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de contatos");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tipo ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 30, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Telefone");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(190, 10, 49, 20);

        cbxTipoTelefone.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Pessoal", "Residêncial" }));
        getContentPane().add(cbxTipoTelefone);
        cbxTipoTelefone.setBounds(20, 30, 140, 30);

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefoneKeyTyped(evt);
            }
        });
        getContentPane().add(txtTelefone);
        txtTelefone.setBounds(190, 30, 140, 30);

        tblContatos.setModel(modelo);
        tblContatos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblContatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblContatos);

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
        btnSalvar.setBounds(50, 210, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(120, 210, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(190, 210, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(260, 210, 55, 41);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 440, 380);

        setSize(new java.awt.Dimension(355, 288));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelefoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTelefoneKeyTyped


    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            contato.setTipo(cbxTipoTelefone.getSelectedItem().toString());
            contato.setNumero(txtTelefone.getText());

            if (txtTelefone.getText().isEmpty() || cbxTipoTelefone.getSelectedItem().equals("Selecione")) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!contatoBll.verificarNumerosIguais(txtTelefone.getText())) {
                    if (contatoBll.salvar(contato)) {
                        JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "NUMERO JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtTelefone.getText().isEmpty() || cbxTipoTelefone.getSelectedItem().equals("Selecione")) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (contatoBll.remover(contatoBll.consultaPorId(contato.getCodigo()))) {
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            contato.setTipo(cbxTipoTelefone.getSelectedItem().toString());
            contato.setNumero(txtTelefone.getText());

            if (txtTelefone.getText().isEmpty() || cbxTipoTelefone.getSelectedItem().equals("Selecione")) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (contatoBll.editar(contato)) {
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

    private void tblContatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblContatosMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblContatos.getSelectedRow();
        Integer codigo = (Integer) tblContatos.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblContatosMouseClicked

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
            java.util.logging.Logger.getLogger(FrmContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmContatos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxTipoTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblContatos;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
