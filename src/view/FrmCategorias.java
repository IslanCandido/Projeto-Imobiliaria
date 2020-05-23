package view;

import bll.CategoriaBLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Categoria;

public class FrmCategorias extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    CategoriaBLL categoriaBll = new CategoriaBLL();
    Categoria categoria = new Categoria();

    public FrmCategorias() {
        criarTabela();
        consultar();
        initComponents();
    }

    private void criarTabela() {
        tblCategorias = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Categoria");

        tblCategorias.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblCategorias.getColumnModel().getColumn(1).setPreferredWidth(30);

    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Categoria> lista = new ArrayList<Categoria>();

        lista = categoriaBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getNome()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        categoria = categoriaBll.consultaPorId(id);
        txtCategoria.setText(categoria.getNome());
    }

    private void limparCampos() {
        txtCategoria.setText("");
        btnSalvar.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de categorias");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Categoria");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 55, 20);

        txtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCategoriaKeyTyped(evt);
            }
        });
        getContentPane().add(txtCategoria);
        txtCategoria.setBounds(30, 30, 240, 28);

        tblCategorias.setModel(modelo);
        tblCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCategorias);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 270, 110);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(90, 190, 55, 41);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(30, 190, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(150, 190, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(210, 190, 55, 41);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 350, 290);

        setSize(new java.awt.Dimension(305, 268));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            categoria.setNome(txtCategoria.getText());

            if (txtCategoria.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!categoriaBll.verificarCategoriasIguais(txtCategoria.getText())) {
                    categoriaBll.salvar(categoria);
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

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtCategoria.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                categoriaBll.remover(categoriaBll.consultaPorId(categoria.getCodigo()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            categoria.setNome(txtCategoria.getText());

            if (txtCategoria.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                categoriaBll.editar(categoria);
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

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblCategorias.getSelectedRow();
        Integer codigo = (Integer) tblCategorias.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblCategoriasMouseClicked

    private void txtCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtCategoria.getText().length();
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
    }//GEN-LAST:event_txtCategoriaKeyTyped

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
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCategorias().setVisible(true);
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
    private javax.swing.JTable tblCategorias;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtCategoria;
    // End of variables declaration//GEN-END:variables
}
