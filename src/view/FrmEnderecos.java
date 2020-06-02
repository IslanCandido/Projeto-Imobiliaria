package view;

import bll.EnderecoBLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Endereco;
import util.ViaCEP;
import util.ViaCEPException;

public class FrmEnderecos extends javax.swing.JFrame {

    private static FrmEnderecos telaEnderecosGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    EnderecoBLL enderecoBll = new EnderecoBLL();
    Endereco endereco = new Endereco();

    public static FrmEnderecos getTelaEndereco() {
        if (telaEnderecosGeral == null) {
            telaEnderecosGeral = new FrmEnderecos();
        }
        return telaEnderecosGeral;
    }

    private FrmEnderecos() {
        criarTabela();
        consultar();
        initComponents();
        iniciar();
    }

    private void iniciar() {
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnLimpar.setEnabled(false);
        btnSalvar.setEnabled(false);
    }

    private void liberar() {
        btnEditar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnLimpar.setEnabled(true);
    }

    private void criarTabela() {
        tblEnderecos = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("CEP");
        modelo.addColumn("Logradouro");
        modelo.addColumn("Bairro");
        modelo.addColumn("Cidade");
        modelo.addColumn("UF");
        modelo.addColumn("Complemento");

        tblEnderecos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblEnderecos.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblEnderecos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblEnderecos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblEnderecos.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblEnderecos.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblEnderecos.getColumnModel().getColumn(6).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Endereco> lista = new ArrayList<Endereco>();

        lista = enderecoBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getCep(),
                    lista.get(i).getLogradouro(),
                    lista.get(i).getBairro(),
                    lista.get(i).getCidade(),
                    lista.get(i).getUf(),
                    lista.get(i).getComplemento()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        endereco = enderecoBll.consultaPorId(id);
        txtCep.setText(endereco.getCep());
        txtLogradouro.setText(endereco.getLogradouro());
        txtBairro.setText(endereco.getBairro());
        txtCidade.setText(endereco.getCidade());
        txtUf.setText(endereco.getUf());
        txtComplemento.setText(endereco.getComplemento());
    }

    private void limparCampos() {
        txtCep.setValue("");
        txtLogradouro.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        txtComplemento.setText("");
        iniciar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblEnderecos = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        txtCidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUf = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de endereços");
        setResizable(false);
        getContentPane().setLayout(null);

        tblEnderecos.setModel(modelo);
        tblEnderecos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblEnderecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEnderecosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEnderecos);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 610, 160);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(300, 290, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(370, 290, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(440, 290, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(510, 290, 55, 41);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("CEP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 20, 20);

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepActionPerformed(evt);
            }
        });
        txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCepKeyTyped(evt);
            }
        });
        getContentPane().add(txtCep);
        txtCep.setBounds(30, 30, 130, 28);

        txtCidade.setEnabled(false);
        getContentPane().add(txtCidade);
        txtCidade.setBounds(270, 30, 190, 28);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Cidade");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(270, 10, 38, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(" UF");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(490, 10, 40, 20);

        txtUf.setEnabled(false);
        getContentPane().add(txtUf);
        txtUf.setBounds(490, 30, 110, 28);

        txtBairro.setEnabled(false);
        getContentPane().add(txtBairro);
        txtBairro.setBounds(30, 80, 210, 28);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Bairro");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 60, 34, 20);

        txtLogradouro.setEnabled(false);
        txtLogradouro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLogradouroKeyTyped(evt);
            }
        });
        getContentPane().add(txtLogradouro);
        txtLogradouro.setBounds(270, 80, 190, 28);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Logradouro");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 60, 65, 20);

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(170, 30, 70, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Complemento");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(490, 60, 90, 20);

        txtComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoActionPerformed(evt);
            }
        });
        txtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComplementoKeyTyped(evt);
            }
        });
        getContentPane().add(txtComplemento);
        txtComplemento.setBounds(490, 80, 110, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(-10, 0, 710, 390);

        setSize(new java.awt.Dimension(636, 369));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ViaCEP cep = new ViaCEP();
        try {
            cep.buscar(txtCep.getText());

            txtBairro.setText(cep.getBairro());
            txtCidade.setText(cep.getLocalidade());
            txtUf.setText(cep.getUf());
            txtLogradouro.setText(cep.getLogradouro());
            txtComplemento.setText(cep.getComplemento());

            liberar();
            btnSalvar.setEnabled(true);
        } catch (ViaCEPException ex) {
            JOptionPane.showMessageDialog(rootPane, "CEP NÃO ENCONTRADO!");
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            endereco.setCep(txtCep.getText());
            endereco.setLogradouro(txtLogradouro.getText());
            endereco.setBairro(txtBairro.getText());
            endereco.setCidade(txtCidade.getText());
            endereco.setUf(txtUf.getText());
            endereco.setComplemento(txtComplemento.getText());

            if (txtCep.getText().isEmpty() || txtLogradouro.getText().isEmpty() || txtBairro.getText().isEmpty()
                    || txtCidade.getText().isEmpty() || txtUf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!enderecoBll.verificarCEPsIguais(txtCep.getText())) {
                    if (enderecoBll.salvar(endereco)) {
                        JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "CEP JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtCep.getText().isEmpty() || txtLogradouro.getText().isEmpty() || txtBairro.getText().isEmpty()
                    || txtCidade.getText().isEmpty() || txtUf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (enderecoBll.remover(enderecoBll.consultaPorId(endereco.getCodigo()))) {
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
            endereco.setCep(txtCep.getText());
            endereco.setLogradouro(txtLogradouro.getText());
            endereco.setBairro(txtBairro.getText());
            endereco.setCidade(txtCidade.getText());
            endereco.setUf(txtUf.getText());
            endereco.setComplemento(txtComplemento.getText());

            if (txtCep.getText().isEmpty() || txtLogradouro.getText().isEmpty() || txtBairro.getText().isEmpty()
                    || txtCidade.getText().isEmpty() || txtUf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (enderecoBll.editar(endereco)) {
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

    private void tblEnderecosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEnderecosMouseClicked
        btnSalvar.setEnabled(false);
        liberar();
        int linha = tblEnderecos.getSelectedRow();
        Integer codigo = (Integer) tblEnderecos.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblEnderecosMouseClicked

    private void txtLogradouroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLogradouroKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtLogradouro.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtLogradouroKeyTyped

    private void txtCepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCepKeyTyped

    private void txtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoActionPerformed

    private void txtComplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplementoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtComplemento.getText().length();
        if (comprimentoDeCampo >= 20) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 20 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtComplementoKeyTyped

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
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEnderecos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEnderecos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEnderecos;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtUf;
    // End of variables declaration//GEN-END:variables
}
