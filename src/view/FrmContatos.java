package view;

import bll.ContatoBLL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Contato;
import model.Pessoa;

public class FrmContatos extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    ContatoBLL contatoBll = new ContatoBLL();
    Contato contato = new Contato();

    Vector<Pessoa> vetorPessoas;

    public FrmContatos() {
        criarTabela();
        consultar();
        initComponents();
        preencherCbxEnderecos();
    }

    private void criarTabela() {
        tblContatos = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Tipo");
        modelo.addColumn("Numero");
        modelo.addColumn("Pessoa");

        tblContatos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblContatos.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblContatos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblContatos.getColumnModel().getColumn(3).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Contato> lista = new ArrayList<Contato>();

        lista = contatoBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getTipo(),
                    lista.get(i).getNumero(),
                    lista.get(i).getIdPessoa().getNome()
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
        cbxPessoas.setSelectedItem(contato.getIdPessoa());
    }

    private void limparCampos() {
        txtTelefone.setText("");
        cbxTipoTelefone.setSelectedIndex(0);
        cbxPessoas.setSelectedIndex(0);
        btnSalvar.setEnabled(true);
    }

    private void preencherCbxEnderecos() {
        vetorPessoas = contatoBll.listarPessoas();
        cbxPessoas.setModel(new DefaultComboBoxModel(vetorPessoas));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxTipoTelefone = new javax.swing.JComboBox<>();
        txtTelefone = new javax.swing.JFormattedTextField();
        cbxPessoas = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContatos = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarPessoa = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de contatos");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tipo ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 30, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Telefone");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 10, 49, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Pessoa");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 60, 50, 20);

        cbxTipoTelefone.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Pessoal", "Residêncial", "Outro" }));
        getContentPane().add(cbxTipoTelefone);
        cbxTipoTelefone.setBounds(30, 30, 140, 30);

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
        txtTelefone.setBounds(200, 30, 140, 30);

        getContentPane().add(cbxPessoas);
        cbxPessoas.setBounds(30, 80, 270, 28);

        tblContatos.setModel(modelo);
        tblContatos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblContatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblContatos);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 350, 130);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(70, 260, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(140, 260, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(210, 260, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(280, 260, 55, 41);

        btnAdicionarPessoa.setText("+");
        btnAdicionarPessoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarPessoaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarPessoa);
        btnAdicionarPessoa.setBounds(300, 80, 41, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 440, 380);

        setSize(new java.awt.Dimension(375, 341));
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

    FrmPessoas telaPessoas;

    private void btnAdicionarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarPessoaActionPerformed
        if (telaPessoas == null) {
            telaPessoas = new FrmPessoas();
            telaPessoas.setVisible(true);
        } else {
            telaPessoas.dispose();
            telaPessoas.setVisible(true);
            telaPessoas.setResizable(false);
        }
    }//GEN-LAST:event_btnAdicionarPessoaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            contato.setTipo(cbxTipoTelefone.getSelectedItem().toString());
            contato.setNumero(txtTelefone.getText());
            contato.setIdPessoa(vetorPessoas.get(cbxPessoas.getSelectedIndex()));

            if (txtTelefone.getText().isEmpty() || cbxTipoTelefone.getSelectedItem().equals("Selecione")) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!contatoBll.verificarNumerosIguais(txtTelefone.getText())) {
                    contatoBll.salvar(contato);
                    consultar();
                    limparCampos();
                } else {
                    if (contatoBll.verificarNumerosIguais(txtTelefone.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "NUMERO JÁ CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
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
                contatoBll.remover(contatoBll.consultaPorId(contato.getCodigo()));
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
            contato.setIdPessoa(vetorPessoas.get(cbxPessoas.getSelectedIndex()));

            if (txtTelefone.getText().isEmpty() || cbxTipoTelefone.getSelectedItem().equals("Selecione")) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                contatoBll.editar(contato);
                consultar();
                limparCampos();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO EDITAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
        preencherCbxEnderecos();
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
    private javax.swing.JButton btnAdicionarPessoa;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxPessoas;
    private javax.swing.JComboBox<String> cbxTipoTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblContatos;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
