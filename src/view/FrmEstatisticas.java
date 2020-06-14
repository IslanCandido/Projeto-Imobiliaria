package view;

import bll.EstatisticasBLL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Venda;

public class FrmEstatisticas extends javax.swing.JFrame {

    private static FrmEstatisticas telaEstatisticasGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    EstatisticasBLL estatisticasBll = new EstatisticasBLL();

    public static FrmEstatisticas getTelaEstatistica() {
        if (telaEstatisticasGeral == null) {
            telaEstatisticasGeral = new FrmEstatisticas();
        }
        return telaEstatisticasGeral;
    }

    private FrmEstatisticas() {

        criarTabela();
        initComponents();
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

    private void iniciar() {
        txtAno.setVisible(false);
        cbxMes.setVisible(false);
        lblTitulo.setVisible(false);
        btnGerar.setVisible(false);
        txtCorretor.setVisible(false);
        txtAnodoMes.setVisible(false);
        lblAno.setVisible(false);
        lblPorcentagem.setVisible(false);
        lblResultadoPorcentagem.setVisible(false);
        lblResultadoTitulo.setVisible(false);
        lblResultadoVendas.setVisible(false);
        lblTituloResultado.setVisible(false);
        lblTotalDeVendas.setVisible(false);
        lblResult.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
    }

    private void criarTabela() {
        tblEstatisticas = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Data");
        modelo.addColumn("Funcionário");
        modelo.addColumn("Imóvel");
        modelo.addColumn("Tipo");
        modelo.addColumn("Valor");
        modelo.addColumn("Comissão");

        tblEstatisticas.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblEstatisticas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblEstatisticas.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblEstatisticas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblEstatisticas.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblEstatisticas.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblEstatisticas.getColumnModel().getColumn(6).setPreferredWidth(10);
    }

    private void consultar(List aux) {
        modelo.setNumRows(0);
        List<Venda> lista;

        lista = aux;

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getDataVenda(),
                    lista.get(i).getIdFuncionario().getNome(),
                    lista.get(i).getIdImovel().getIdCategoria().getNome(),
                    lista.get(i).getIdImovel().getTipo(),
                    lista.get(i).getValor(),
                    lista.get(i).getPercentualComissao()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void limpar() {
        txtAno.setValue("");
        txtCorretor.setText("");
        txtAnodoMes.setText("");
        cbxMes.setSelectedIndex(0);
        modelo.setNumRows(0);

        lblPorcentagem.setVisible(false);
        lblResultadoPorcentagem.setVisible(false);
        lblResultadoTitulo.setVisible(false);
        lblResultadoVendas.setVisible(false);
        lblTituloResultado.setVisible(false);
        lblTotalDeVendas.setVisible(false);
        lblResult.setVisible(false);
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel12.setVisible(true);
    }

    private float calculoPorcentagem(int valor, int total) {
        return (valor * 100) / total;
    }

    private void mostrarResultado(int tamanhoVenda) {
        lblPorcentagem.setVisible(true);
        lblResultadoPorcentagem.setVisible(true);
        lblResultadoTitulo.setVisible(true);
        lblResultadoVendas.setVisible(true);
        lblTituloResultado.setVisible(true);
        lblTotalDeVendas.setVisible(true);
        lblResult.setVisible(true);
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel12.setVisible(true);

        lblResultadoTitulo.setText("" + tamanhoVenda);
        lblResultadoVendas.setText("" + estatisticasBll.totalVendas());
        lblResultadoPorcentagem.setText(calculoPorcentagem(tamanhoVenda, estatisticasBll.totalVendas()) + " %");
    }

    private void resultadoCbxMes(String ano) {
        if (cbxMes.getSelectedIndex() == 0 || txtAnodoMes.getValue().toString().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
        } else {
            if (cbxMes.getSelectedItem().equals("Janeiro")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/01/" + ano), CriarNovaData("31/01/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/01/" + ano), CriarNovaData("31/01/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/01/" + ano), CriarNovaData("31/01/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Fevereiro")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/02/" + ano), CriarNovaData("28/02/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/02/" + ano), CriarNovaData("28/02/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/02/" + ano), CriarNovaData("28/02/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Março")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/03/" + ano), CriarNovaData("31/03/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/03/" + ano), CriarNovaData("31/03/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/03/" + ano), CriarNovaData("31/03/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Abril")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/04/" + ano), CriarNovaData("30/04/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/04/" + ano), CriarNovaData("30/04/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/04/" + ano), CriarNovaData("30/04/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Maio")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/05/" + ano), CriarNovaData("31/05/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/05/" + ano), CriarNovaData("31/05/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/05/" + ano), CriarNovaData("31/05/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Junho")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/06/" + ano), CriarNovaData("30/06/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/06/" + ano), CriarNovaData("30/06/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/06/" + ano), CriarNovaData("30/06/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Julho")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/07/" + ano), CriarNovaData("31/07/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/07/" + ano), CriarNovaData("31/07/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/07/" + ano), CriarNovaData("31/07/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Agosto")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/08/" + ano), CriarNovaData("31/08/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/08/" + ano), CriarNovaData("31/08/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/08/" + ano), CriarNovaData("31/08/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Setembro")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/09/" + ano), CriarNovaData("30/09/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/09/" + ano), CriarNovaData("30/09/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/09/" + ano), CriarNovaData("30/09/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Outubro")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/10/" + ano), CriarNovaData("31/10/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/10/" + ano), CriarNovaData("31/10/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/10/" + ano), CriarNovaData("31/10/" + ano)).size());
                }
            }
            if (cbxMes.getSelectedItem().equals("Novembro")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/11/" + ano), CriarNovaData("30/11/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/11/" + ano), CriarNovaData("30/11/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/11/" + ano), CriarNovaData("30/11/" + ano)).size());
                }
            }

            if (cbxMes.getSelectedItem().equals("Dezembro")) {
                if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/12/" + ano), CriarNovaData("31/12/" + ano)).size() <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                    limpar();
                } else {
                    consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/12/" + ano), CriarNovaData("31/12/" + ano)));
                    mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/12/" + ano), CriarNovaData("31/12/" + ano)).size());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblEstatisticas = new javax.swing.JTable();
        cbxTipoEstatistica = new javax.swing.JComboBox<>();
        lblAno = new javax.swing.JLabel();
        btnGerar = new javax.swing.JButton();
        cbxMes = new javax.swing.JComboBox<>();
        lblTitulo = new javax.swing.JLabel();
        txtAno = new javax.swing.JFormattedTextField();
        txtCorretor = new javax.swing.JTextField();
        txtAnodoMes = new javax.swing.JFormattedTextField();
        btnLimpar = new javax.swing.JButton();
        lblTotalDeVendas = new javax.swing.JLabel();
        lblResultadoVendas = new javax.swing.JLabel();
        lblTituloResultado = new javax.swing.JLabel();
        lblResultadoTitulo = new javax.swing.JLabel();
        lblPorcentagem = new javax.swing.JLabel();
        lblResultadoPorcentagem = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emitir estatísticas");
        setResizable(false);
        getContentPane().setLayout(null);

        tblEstatisticas.setModel(modelo);
        tblEstatisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblEstatisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstatisticasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEstatisticas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 110, 880, 220);

        cbxTipoEstatistica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxTipoEstatistica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um tipo de estatística", "Estatística de imóveis por venda mensal", "Estatística de imóveis por venda  anual", "Estatísticas de comissões recebidas por corretor" }));
        cbxTipoEstatistica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoEstatisticaItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxTipoEstatistica);
        cbxTipoEstatistica.setBounds(50, 30, 350, 40);

        lblAno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAno.setText(" Ano: ");
        getContentPane().add(lblAno);
        lblAno.setBounds(450, 20, 34, 20);

        btnGerar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGerar.setText("Gerar");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerar);
        btnGerar.setBounds(680, 40, 65, 30);

        cbxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        getContentPane().add(cbxMes);
        cbxMes.setBounds(510, 40, 150, 30);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(510, 20, 140, 20);

        try {
            txtAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtAno);
        txtAno.setBounds(510, 40, 150, 30);
        getContentPane().add(txtCorretor);
        txtCorretor.setBounds(510, 40, 150, 30);

        try {
            txtAnodoMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtAnodoMes);
        txtAnodoMes.setBounds(450, 40, 50, 30);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(830, 60, 55, 41);

        lblTotalDeVendas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalDeVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDeVendas.setText("Total de Vendas");
        getContentPane().add(lblTotalDeVendas);
        lblTotalDeVendas.setBounds(280, 350, 110, 20);

        lblResultadoVendas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblResultadoVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblResultadoVendas);
        lblResultadoVendas.setBounds(280, 370, 110, 20);

        lblTituloResultado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTituloResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTituloResultado);
        lblTituloResultado.setBounds(440, 350, 110, 20);

        lblResultadoTitulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblResultadoTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblResultadoTitulo);
        lblResultadoTitulo.setBounds(440, 370, 110, 20);

        lblPorcentagem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPorcentagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPorcentagem.setText("Porcentagem");
        getContentPane().add(lblPorcentagem);
        lblPorcentagem.setBounds(590, 350, 110, 20);

        lblResultadoPorcentagem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblResultadoPorcentagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblResultadoPorcentagem);
        lblResultadoPorcentagem.setBounds(590, 370, 110, 20);

        lblResult.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResult.setText("Resultado:");
        getContentPane().add(lblResult);
        lblResult.setBounds(140, 350, 100, 40);

        jLabel1.setText("|");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(260, 370, 10, 30);

        jLabel2.setText("|");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(260, 350, 10, 14);

        jLabel3.setText("|");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(260, 360, 10, 20);

        jLabel4.setText("|");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(420, 350, 10, 14);

        jLabel5.setText("|");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(420, 370, 10, 30);

        jLabel6.setText("|");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(420, 360, 10, 20);

        jLabel7.setText("|");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(570, 350, 10, 14);

        jLabel8.setText("|");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(570, 370, 10, 30);

        jLabel9.setText("|");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(570, 360, 10, 20);

        jLabel10.setText("|");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(710, 350, 10, 14);

        jLabel11.setText("|");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(710, 370, 10, 30);

        jLabel12.setText("|");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(710, 360, 10, 20);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 970, 530);

        setSize(new java.awt.Dimension(910, 442));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblEstatisticasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstatisticasMouseClicked

    }//GEN-LAST:event_tblEstatisticasMouseClicked

    private void cbxTipoEstatisticaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoEstatisticaItemStateChanged
        if (cbxTipoEstatistica.getSelectedIndex() == 0) {
            txtAno.setVisible(false);
            cbxMes.setVisible(false);
            lblTitulo.setVisible(false);
            btnGerar.setVisible(false);
            txtCorretor.setVisible(false);
            txtAnodoMes.setVisible(false);
            lblAno.setVisible(false);
            lblPorcentagem.setVisible(false);
            lblResultadoPorcentagem.setVisible(false);
            lblResultadoTitulo.setVisible(false);
            lblResultadoVendas.setVisible(false);
            lblTituloResultado.setVisible(false);
            lblTotalDeVendas.setVisible(false);
            lblResult.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            jLabel10.setVisible(false);
            jLabel11.setVisible(false);
            jLabel12.setVisible(false);
        }
        if (cbxTipoEstatistica.getSelectedIndex() == 1) {
            txtAno.setVisible(false);
            cbxMes.setVisible(true);
            lblTitulo.setVisible(true);
            btnGerar.setVisible(true);
            txtCorretor.setVisible(false);
            txtAnodoMes.setVisible(true);
            lblAno.setVisible(true);

            lblTitulo.setText(" Mês: ");
            lblTituloResultado.setText("Vendas do mês");
            limpar();
        }
        if (cbxTipoEstatistica.getSelectedIndex() == 2) {
            txtAno.setVisible(true);
            cbxMes.setVisible(false);
            lblTitulo.setVisible(true);
            btnGerar.setVisible(true);
            txtCorretor.setVisible(false);
            txtAnodoMes.setVisible(false);
            lblAno.setVisible(false);

            lblTituloResultado.setText("Vendas do ano");
            lblTitulo.setText(" Ano: ");
            limpar();
        }
        if (cbxTipoEstatistica.getSelectedIndex() == 3) {
            txtAno.setVisible(false);
            cbxMes.setVisible(false);
            lblTitulo.setVisible(true);
            btnGerar.setVisible(true);
            txtCorretor.setVisible(true);
            txtAnodoMes.setVisible(false);
            lblAno.setVisible(false);

            lblTituloResultado.setText("Vendas corretor");
            lblTitulo.setText(" Corretor: ");
            limpar();
        }
    }//GEN-LAST:event_cbxTipoEstatisticaItemStateChanged

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        try {

            if (cbxTipoEstatistica.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(rootPane, "SELECIONE UM TIPO DE ESTATÍSTICAS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
            }

            if (cbxTipoEstatistica.getSelectedIndex() == 1) {

                if (txtAnodoMes.getValue().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                } else {
                    resultadoCbxMes(txtAnodoMes.getText());
                }

            }

            if (cbxTipoEstatistica.getSelectedIndex() == 2) {

                if (txtAno.getValue().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                } else {

                    if (estatisticasBll.EstatitiscasPorData(CriarNovaData("01/01/" + txtAno.getText()), CriarNovaData("31/12/" + txtAno.getText())).size() <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                        limpar();
                    } else {
                        consultar(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/01/" + txtAno.getText()), CriarNovaData("31/12/" + txtAno.getText())));
                        mostrarResultado(estatisticasBll.EstatitiscasPorData(CriarNovaData("01/01/" + txtAno.getText()), CriarNovaData("31/12/" + txtAno.getText())).size());
                    }
                }

            }

            if (cbxTipoEstatistica.getSelectedIndex() == 3) {

                if (txtCorretor.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                } else {

                    if (estatisticasBll.EstatitiscasPorCorretor(txtCorretor.getText()).size() <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "NENHUM DADO ENCONTRADO PARA ESSE TIPO DE CONSULTA!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
                        limpar();
                    } else {
                        consultar(estatisticasBll.EstatitiscasPorCorretor(txtCorretor.getText()));
                        mostrarResultado(estatisticasBll.EstatitiscasPorCorretor(txtCorretor.getText()).size());
                    }

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO GERAR ESTATÍSTICAS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGerarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
        cbxTipoEstatistica.setSelectedIndex(0);
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
            java.util.logging.Logger.getLogger(FrmEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstatisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstatisticas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> cbxMes;
    private javax.swing.JComboBox<String> cbxTipoEstatistica;
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
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblPorcentagem;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblResultadoPorcentagem;
    private javax.swing.JLabel lblResultadoTitulo;
    private javax.swing.JLabel lblResultadoVendas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloResultado;
    private javax.swing.JLabel lblTotalDeVendas;
    private javax.swing.JTable tblEstatisticas;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtAno;
    private javax.swing.JFormattedTextField txtAnodoMes;
    private javax.swing.JTextField txtCorretor;
    // End of variables declaration//GEN-END:variables
}
