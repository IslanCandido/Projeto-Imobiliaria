package view;

import bll.RelatorioBLL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Imovel;

public class FrmRelatorios extends javax.swing.JFrame {

    private static FrmRelatorios telaRelatoriosGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    RelatorioBLL relatorioBLL = new RelatorioBLL();

    public static FrmRelatorios getTelaRelatorio() {
        if (telaRelatoriosGeral == null) {
            telaRelatoriosGeral = new FrmRelatorios();
        }
        return telaRelatoriosGeral;
    }

    private FrmRelatorios() {
        initComponents();
        criarTabela();
        iniciar();
    }
    
    private Date CriarNovaData(String data) {
        if (data == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        java.sql.Date a = null;
        try {
            a = new java.sql.Date(format.parse(data).getTime());
        } catch (ParseException e) {
        }
        return a;
    }

    private void iniciar(){
        txtTipo.setVisible(false);
        lblTipoRelatorio.setVisible(false);
        txtDatas.setVisible(false);
        btnGerarRelatorio.setVisible(false);
    }

    private void criarTabela() {
        tblRelatorios = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Data Inscrição");
        modelo.addColumn("Categoria");
        modelo.addColumn("Endereço");
        modelo.addColumn("Metros");
        modelo.addColumn("Quartos");
        modelo.addColumn("Suítes");
        modelo.addColumn("Descrição");
        modelo.addColumn("Tipo");
        modelo.addColumn("Preço");
        modelo.addColumn("Proprietário");
        modelo.addColumn("Situação");

        tblRelatorios.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblRelatorios.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblRelatorios.getColumnModel().getColumn(7).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(8).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(9).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(10).setPreferredWidth(50);
        tblRelatorios.getColumnModel().getColumn(11).setPreferredWidth(50);
    }

    private void consultar(List aux) {
        modelo.setNumRows(0);
        List<Imovel> lista;

        lista = aux;

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getDtInscricao(),
                    lista.get(i).getIdCategoria().getNome(),
                    lista.get(i).getIdEndereco().getBairro(),
                    lista.get(i).getMetros(),
                    lista.get(i).getnQuartos(),
                    lista.get(i).getnSuites(),
                    lista.get(i).getDescricao(),
                    lista.get(i).getTipo(),
                    lista.get(i).getPreco(),
                    lista.get(i).getIdProprietario().getNome(),
                    lista.get(i).getSituacao()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void limpar() {
        txtTipo.setText("");
        txtDatas.setText("");
        modelo.setNumRows(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblRelatorios = new javax.swing.JTable();
        cbxTipoRelatorio = new javax.swing.JComboBox<>();
        btnGerarRelatorio = new javax.swing.JButton();
        txtTipo = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();
        lblTipoRelatorio = new javax.swing.JLabel();
        txtDatas = new javax.swing.JFormattedTextField();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emitir relatórios");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null);

        tblRelatorios.setModel(modelo);
        tblRelatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRelatoriosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRelatorios);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 140, 980, 270);

        cbxTipoRelatorio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um tipo de relatório", "Relatório de imóveis por cidade", "Relatório de imóveis por periodo", "Relatório de imóveis por categoria", "Relatório de imóveis vendido por corretor", "Relatório de imóveis por situação", "Relatório de imóveis por motivo de baixa", "Relatório de imóveis vendidos por cliente", "Relatório de imóveis vendidos por período", "Relatório de imóveis cadastrados no sistema" }));
        cbxTipoRelatorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoRelatorioItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxTipoRelatorio);
        cbxTipoRelatorio.setBounds(80, 10, 380, 40);

        btnGerarRelatorio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGerarRelatorio.setText("Gerar");
        btnGerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarRelatorioActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerarRelatorio);
        btnGerarRelatorio.setBounds(390, 90, 70, 30);
        getContentPane().add(txtTipo);
        txtTipo.setBounds(210, 90, 170, 30);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(860, 80, 55, 41);

        lblTipoRelatorio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getContentPane().add(lblTipoRelatorio);
        lblTipoRelatorio.setBounds(210, 70, 170, 20);

        try {
            txtDatas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatasActionPerformed(evt);
            }
        });
        txtDatas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDatasKeyTyped(evt);
            }
        });
        getContentPane().add(txtDatas);
        txtDatas.setBounds(210, 90, 170, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 1020, 480);

        setSize(new java.awt.Dimension(1019, 466));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRelatoriosMouseClicked

    }//GEN-LAST:event_tblRelatoriosMouseClicked

    private void btnGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarRelatorioActionPerformed
        try {
            if (cbxTipoRelatorio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(rootPane, "SELECIONE UM TIPO DE RELATÓRIO!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 1) {
                consultar(relatorioBLL.relatorioImoveisPorCidade(txtTipo.getText()));
                
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 2) {
                consultar(relatorioBLL.relatorioImoveisPorDataInscricao(CriarNovaData(txtDatas.getText())));
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 3) {
                consultar(relatorioBLL.relatorioImoveisPorCategoria(txtTipo.getText()));
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 4) {

            }
            if (cbxTipoRelatorio.getSelectedIndex() == 5) {
                consultar(relatorioBLL.relatorioImoveisPorSituacao(txtTipo.getText()));
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 6) {
                consultar(relatorioBLL.relatorioImoveisPorMotivoBaixa(txtTipo.getText()));
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 7) {
                consultar(relatorioBLL.relatorioImoveisPorCliente(txtTipo.getText()));
            }
            if (cbxTipoRelatorio.getSelectedIndex() == 8) {

            }
            if (cbxTipoRelatorio.getSelectedIndex() == 9) {
                consultar(relatorioBLL.relatorioImoveisCadastrados());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO GERAR RELATÓRIO!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnGerarRelatorioActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
        cbxTipoRelatorio.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void cbxTipoRelatorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoRelatorioItemStateChanged
        if (cbxTipoRelatorio.getSelectedIndex() == 0) {
            txtTipo.setVisible(false);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(false);
            btnGerarRelatorio.setVisible(false);
            txtTipo.setText("");
            txtDatas.setText("");
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 1) {
            txtTipo.setVisible(true);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("Cidade");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 2) {
            txtTipo.setVisible(false);
            txtDatas.setVisible(true);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("Data de Inscrição");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 3) {
            txtTipo.setVisible(true);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("Categoria");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 4) {
            txtTipo.setVisible(true);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("CPF do corretor");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 5) {
            txtTipo.setVisible(true);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("Situação");
            
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 6) {
            txtTipo.setVisible(true);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("Motivo de baixa");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 7) {
            txtTipo.setVisible(true);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("CPF do cliente");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 8) {
            txtTipo.setVisible(false);
            txtDatas.setVisible(true);
            lblTipoRelatorio.setVisible(true);
            btnGerarRelatorio.setVisible(true);
            
            lblTipoRelatorio.setText("Data de venda");
            limpar();
        }
        if (cbxTipoRelatorio.getSelectedIndex() == 9) {
            txtTipo.setVisible(false);
            txtDatas.setVisible(false);
            lblTipoRelatorio.setVisible(false);
            btnGerarRelatorio.setVisible(true);  
            limpar();
        }
    }//GEN-LAST:event_cbxTipoRelatorioItemStateChanged

    private void txtDatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatasActionPerformed

    }//GEN-LAST:event_txtDatasActionPerformed

    private void txtDatasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatasKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDatasKeyTyped

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
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRelatorios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerarRelatorio;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> cbxTipoRelatorio;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTipoRelatorio;
    private javax.swing.JTable tblRelatorios;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtDatas;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
