package view;

import bll.ImovelBLL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Endereco;
import model.Imovel;
import model.Proprietario;

public class FrmImoveis extends javax.swing.JFrame {

    private static FrmImoveis telaImoveisGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    ImovelBLL imovelBll = new ImovelBLL();
    Imovel imovel = new Imovel();

    Vector<Endereco> vetorEnderecos;
    Vector<Categoria> vetorCategorias;
    Vector<Proprietario> vetorProprietarios;

    public static FrmImoveis getTelaImovel() {
        if (telaImoveisGeral == null) {
            telaImoveisGeral = new FrmImoveis();
        }
        return telaImoveisGeral;
    }

    private FrmImoveis() {
        criarTabela();
        consultar();
        initComponents();
        preencherCbxs();
        getDataAtual();
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

    private String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formatter.format(dtConsulta);
        } catch (Exception e) {
            return null;
        }
    }

    private void criarTabela() {
        tblImoveis = new JTable(modelo);
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

        tblImoveis.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblImoveis.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblImoveis.getColumnModel().getColumn(7).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(8).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(9).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(10).setPreferredWidth(50);
        tblImoveis.getColumnModel().getColumn(11).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Imovel> lista = new ArrayList<Imovel>();

        lista = imovelBll.consultar();

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

    private void preencheCampos(int id) {
        imovel = imovelBll.consultaPorId(id);
        txtDataInscrição.setText(convertDate(imovel.getDtInscricao()));
        cbxCategorias.setSelectedItem(imovel.getIdCategoria());
        cbxEnderecos.setSelectedItem(imovel.getIdEndereco());
        txtMetros.setText(String.valueOf(imovel.getMetros()));
        txtNumeroQuartos.setText(String.valueOf(imovel.getnQuartos()));
        txtNumeroSuites.setText(String.valueOf(imovel.getnSuites()));
        txtPreco.setText(String.valueOf(imovel.getPreco()));
        txtDescricao.setText(imovel.getDescricao());
        cbxTipoVenda.setSelectedItem(imovel.getTipo());
        cbxProprietarios.setSelectedItem(imovel.getIdProprietario());
        cbxSituacao.setSelectedItem(imovel.getSituacao());
        txtMotivo.setText(imovel.getMotivo());
        if (cbxSituacao.getSelectedItem().equals("Disponivel")) {
            txtDataBaixa.setText("00/00/0000");
        } else {
            txtDataBaixa.setText(convertDate(imovel.getDtBaixa()));
        }

    }

    private void limparCampos() {
        getDataAtual();
        cbxCategorias.setSelectedIndex(0);
        cbxEnderecos.setSelectedIndex(0);
        txtMetros.setText("");
        txtNumeroQuartos.setText("");
        txtNumeroSuites.setText("");
        txtPreco.setText("");
        txtDescricao.setText("");
        cbxTipoVenda.setSelectedIndex(0);
        cbxProprietarios.setSelectedIndex(0);
        cbxSituacao.setSelectedIndex(0);
        txtDataBaixa.setValue("");
        txtMotivo.setText("");
        btnSalvar.setEnabled(true);
        consultar();
    }

    private void preencherCbxs() {
        vetorEnderecos = imovelBll.listarEnderecos();
        vetorCategorias = imovelBll.listarCategorias();
        vetorProprietarios = imovelBll.listarProprietarios();

        cbxEnderecos.setModel(new DefaultComboBoxModel(vetorEnderecos));
        cbxCategorias.setModel(new DefaultComboBoxModel(vetorCategorias));
        cbxProprietarios.setModel(new DefaultComboBoxModel(vetorProprietarios));
    }

    private void getDataAtual() {
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        txtDataInscrição.setText(formatador.format(data));
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDataBaixa = new javax.swing.JFormattedTextField();
        cbxCategorias = new javax.swing.JComboBox<>();
        cbxEnderecos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMetros = new javax.swing.JTextField();
        txtNumeroSuites = new javax.swing.JTextField();
        txtNumeroQuartos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDataInscrição = new javax.swing.JFormattedTextField();
        txtDescricao = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblImoveis = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarCategoria = new javax.swing.JButton();
        btnAdicionarProprietario = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox<>();
        btnMostrarDisponiveis = new javax.swing.JButton();
        btnMostrarIndisponiveis = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbxProprietarios = new javax.swing.JComboBox<>();
        btnAdicionarEnderecos = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cbxTipoVenda = new javax.swing.JComboBox<>();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de imóveis");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" Data");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 10, 40, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText(" Categoria");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(170, 10, 60, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Endereço");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(360, 10, 70, 20);

        try {
            txtDataBaixa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataBaixaActionPerformed(evt);
            }
        });
        txtDataBaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataBaixaKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataBaixa);
        txtDataBaixa.setBounds(470, 130, 100, 30);

        getContentPane().add(cbxCategorias);
        cbxCategorias.setBounds(170, 30, 120, 28);

        getContentPane().add(cbxEnderecos);
        cbxEnderecos.setBounds(360, 30, 330, 28);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText(" Metros");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 60, 50, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(" Nº Suítes");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(260, 60, 60, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(" NºQuartos");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(160, 60, 70, 20);

        txtMetros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMetrosKeyTyped(evt);
            }
        });
        getContentPane().add(txtMetros);
        txtMetros.setBounds(40, 80, 90, 28);

        txtNumeroSuites.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroSuitesKeyTyped(evt);
            }
        });
        getContentPane().add(txtNumeroSuites);
        txtNumeroSuites.setBounds(260, 80, 70, 28);

        txtNumeroQuartos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroQuartosKeyTyped(evt);
            }
        });
        getContentPane().add(txtNumeroQuartos);
        txtNumeroQuartos.setBounds(160, 80, 70, 28);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText(" Descrição");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(360, 60, 60, 20);

        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotivoKeyTyped(evt);
            }
        });
        getContentPane().add(txtMotivo);
        txtMotivo.setBounds(600, 130, 270, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText(" Situação");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(330, 110, 60, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText(" Data da baixa");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(470, 110, 100, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText(" Motivo");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(600, 110, 60, 20);

        try {
            txtDataInscrição.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataInscrição.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInscriçãoActionPerformed(evt);
            }
        });
        txtDataInscrição.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataInscriçãoKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataInscrição);
        txtDataInscrição.setBounds(40, 30, 100, 28);

        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyTyped(evt);
            }
        });
        getContentPane().add(txtDescricao);
        txtDescricao.setBounds(360, 80, 370, 28);

        tblImoveis.setModel(modelo);
        tblImoveis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblImoveis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImoveisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblImoveis);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 890, 180);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(580, 360, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(650, 360, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(720, 360, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(790, 360, 55, 41);

        btnAdicionarCategoria.setText("+");
        btnAdicionarCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCategoria);
        btnAdicionarCategoria.setBounds(290, 30, 41, 28);

        btnAdicionarProprietario.setText("+");
        btnAdicionarProprietario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarProprietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProprietarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarProprietario);
        btnAdicionarProprietario.setBounds(690, 30, 41, 28);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText(" Preço");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(760, 10, 50, 20);

        txtPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecoKeyTyped(evt);
            }
        });
        getContentPane().add(txtPreco);
        txtPreco.setBounds(760, 30, 90, 28);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText(" R$");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(850, 30, 20, 30);

        cbxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Disponivel", "Indisponivel" }));
        cbxSituacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSituacaoItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxSituacao);
        cbxSituacao.setBounds(330, 130, 110, 28);

        btnMostrarDisponiveis.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnMostrarDisponiveis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_disponivel.png"))); // NOI18N
        btnMostrarDisponiveis.setText("DISPONÍVEL");
        btnMostrarDisponiveis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarDisponiveis.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnMostrarDisponiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarDisponiveisActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarDisponiveis);
        btnMostrarDisponiveis.setBounds(40, 360, 150, 40);

        btnMostrarIndisponiveis.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnMostrarIndisponiveis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_indisponivel.png"))); // NOI18N
        btnMostrarIndisponiveis.setText("INDISPONÍVEL");
        btnMostrarIndisponiveis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarIndisponiveis.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnMostrarIndisponiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarIndisponiveisActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarIndisponiveis);
        btnMostrarIndisponiveis.setBounds(200, 360, 150, 40);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Proprietário");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(40, 110, 70, 20);

        getContentPane().add(cbxProprietarios);
        cbxProprietarios.setBounds(40, 130, 220, 28);

        btnAdicionarEnderecos.setText("+");
        btnAdicionarEnderecos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarEnderecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEnderecosActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarEnderecos);
        btnAdicionarEnderecos.setBounds(260, 130, 41, 28);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Tipo");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(760, 60, 24, 20);

        cbxTipoVenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Vender", "Alugar" }));
        getContentPane().add(cbxTipoVenda);
        cbxTipoVenda.setBounds(760, 80, 110, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 930, 450);

        setSize(new java.awt.Dimension(921, 438));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataBaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataBaixaActionPerformed

    }//GEN-LAST:event_txtDataBaixaActionPerformed

    private void txtDataBaixaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataBaixaKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDataBaixaKeyTyped

    private void txtDataInscriçãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInscriçãoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInscriçãoActionPerformed

    private void txtDataInscriçãoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataInscriçãoKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDataInscriçãoKeyTyped

    FrmCategorias telaCategorias;
    FrmEnderecos telaEnderecos;
    FrmProprietarios telaProprietarios;

    private void btnAdicionarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCategoriaActionPerformed
        if (telaCategorias == null) {
            telaCategorias = FrmCategorias.getTelaCategoria();
            telaCategorias.setVisible(true);
        } else {
            telaCategorias.dispose();
            telaCategorias.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarCategoriaActionPerformed

    private void btnAdicionarProprietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProprietarioActionPerformed
        if (telaEnderecos == null) {
            telaEnderecos = FrmEnderecos.getTelaEndereco();
            telaEnderecos.setVisible(true);
        } else {
            telaEnderecos.dispose();
            telaEnderecos.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarProprietarioActionPerformed

    private void btnMostrarDisponiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarDisponiveisActionPerformed
        modelo.setNumRows(0);
        List<Imovel> lista;

        lista = imovelBll.mostrarDisponiveis();

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
    }//GEN-LAST:event_btnMostrarDisponiveisActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            imovel.setDescricao(txtDescricao.getText());
            imovel.setDtInscricao(CriarNovaData(txtDataInscrição.getText()));
            imovel.setDtBaixa(CriarNovaData(txtDataBaixa.getText()));
            imovel.setIdCategoria(vetorCategorias.get(cbxCategorias.getSelectedIndex()));
            imovel.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));
            imovel.setIdProprietario(vetorProprietarios.get(cbxProprietarios.getSelectedIndex()));
            imovel.setMetros(Float.parseFloat(txtMetros.getText()));
            imovel.setMotivo(txtMotivo.getText());
            imovel.setPreco(Double.parseDouble(txtPreco.getText()));
            imovel.setSituacao(cbxSituacao.getSelectedItem().toString());
            imovel.setnQuartos(Integer.parseInt(txtNumeroQuartos.getText()));
            imovel.setnSuites(Integer.parseInt(txtNumeroSuites.getText()));
            imovel.setTipo(cbxTipoVenda.getSelectedItem().toString());
            
            if (txtDescricao.getText().isEmpty() || txtDataInscrição.getText().isEmpty() || txtMetros.getText().isEmpty()
                    || txtPreco.getText().isEmpty() || cbxSituacao.getSelectedItem().equals("Selecione") || cbxTipoVenda.getSelectedItem().equals("Selecione")
                    || txtNumeroQuartos.getText().isEmpty() || txtNumeroSuites.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (imovelBll.isData(txtDataInscrição.getText())) {
                    if (imovelBll.salvar(imovel)) {
                        JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    if (!imovelBll.isData(txtDataInscrição.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "DATA DE INSCRIÇÃO INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR! " + e, "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtDescricao.getText().isEmpty() || txtDataInscrição.getText().isEmpty() || txtMetros.getText().isEmpty()
                    || txtPreco.getText().isEmpty() || cbxSituacao.getSelectedItem().equals("Selecione") || cbxTipoVenda.getSelectedItem().equals("Selecione")
                    || txtNumeroQuartos.getText().isEmpty() || txtNumeroSuites.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (imovelBll.remover(imovelBll.consultaPorId(imovel.getCodigo()))) {
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
            imovel.setDescricao(txtDescricao.getText());
            imovel.setDtInscricao(CriarNovaData(txtDataInscrição.getText()));
            imovel.setDtBaixa(CriarNovaData(txtDataBaixa.getText()));
            imovel.setIdCategoria(vetorCategorias.get(cbxCategorias.getSelectedIndex()));
            imovel.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));
            imovel.setIdProprietario(vetorProprietarios.get(cbxProprietarios.getSelectedIndex()));
            imovel.setMetros(Float.parseFloat(txtMetros.getText()));
            imovel.setMotivo(txtMotivo.getText());
            imovel.setPreco(Double.parseDouble(txtPreco.getText()));
            imovel.setSituacao(cbxSituacao.getSelectedItem().toString());
            imovel.setnQuartos(Integer.parseInt(txtNumeroQuartos.getText()));
            imovel.setnSuites(Integer.parseInt(txtNumeroSuites.getText()));
            imovel.setTipo(cbxTipoVenda.getSelectedItem().toString());

            if (txtDescricao.getText().isEmpty() || txtDataInscrição.getText().isEmpty() || txtMetros.getText().isEmpty()
                    || txtPreco.getText().isEmpty() || cbxSituacao.getSelectedItem().equals("Selecione") || cbxTipoVenda.getSelectedItem().equals("Selecione")
                    || txtNumeroQuartos.getText().isEmpty() || txtNumeroSuites.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (imovelBll.isData(txtDataInscrição.getText())) {
                    if (imovelBll.editar(imovel)) {
                        JOptionPane.showMessageDialog(rootPane, "Editado com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao editar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    if (!imovelBll.isData(txtDataInscrição.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "DATA DE INSCRIÇÃO INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO EDITAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
        preencherCbxs();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblImoveisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImoveisMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblImoveis.getSelectedRow();
        Integer codigo = (Integer) tblImoveis.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblImoveisMouseClicked

    private void btnMostrarIndisponiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarIndisponiveisActionPerformed
        modelo.setNumRows(0);
        List<Imovel> lista = new ArrayList<Imovel>();

        lista = imovelBll.mostrarIndisponiveis();

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
                    lista.get(i).getSituacao(),
                    lista.get(i).getDtBaixa(),
                    lista.get(i).getMotivo()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }//GEN-LAST:event_btnMostrarIndisponiveisActionPerformed

    private void txtDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtDescricao.getText().length();
        if (comprimentoDeCampo >= 80) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 80 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDescricaoKeyTyped

    private void txtMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtMotivo.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtMotivoKeyTyped

    private void txtMetrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMetrosKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtMetros.getText().length();
        if (comprimentoDeCampo >= 6) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 6 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtMetrosKeyTyped

    private void txtNumeroSuitesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroSuitesKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtNumeroSuites.getText().length();
        if (comprimentoDeCampo >= 2) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 2 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNumeroSuitesKeyTyped

    private void txtNumeroQuartosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroQuartosKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtNumeroQuartos.getText().length();
        if (comprimentoDeCampo >= 2) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 2 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNumeroQuartosKeyTyped

    private void txtPrecoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtPreco.getText().length();
        if (comprimentoDeCampo > 6) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 6 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }

        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtPrecoKeyTyped

    private void btnAdicionarEnderecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarEnderecosActionPerformed
        if (telaProprietarios == null) {
            telaProprietarios = FrmProprietarios.getTelaProprietario();
            telaProprietarios.setVisible(true);
        } else {
            telaProprietarios.dispose();
            telaProprietarios.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarEnderecosActionPerformed

    private void cbxSituacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSituacaoItemStateChanged
        if (cbxSituacao.getSelectedItem().equals("Disponivel") || cbxSituacao.getSelectedItem().equals("Selecione")) {
            txtDataBaixa.setEnabled(false);
            txtMotivo.setEnabled(false);
            txtDataBaixa.setText("00/00/0000");
            txtMotivo.setText("");
        } else {
            txtDataBaixa.setEnabled(true);
            txtMotivo.setEnabled(true);
            txtDataBaixa.setValue("");
        }
    }//GEN-LAST:event_cbxSituacaoItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmImoveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmImoveis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCategoria;
    private javax.swing.JButton btnAdicionarEnderecos;
    private javax.swing.JButton btnAdicionarProprietario;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnMostrarDisponiveis;
    private javax.swing.JButton btnMostrarIndisponiveis;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxCategorias;
    private javax.swing.JComboBox<String> cbxEnderecos;
    private javax.swing.JComboBox<String> cbxProprietarios;
    private javax.swing.JComboBox<String> cbxSituacao;
    private javax.swing.JComboBox<String> cbxTipoVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblImoveis;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtDataBaixa;
    private javax.swing.JFormattedTextField txtDataInscrição;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtMetros;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNumeroQuartos;
    private javax.swing.JTextField txtNumeroSuites;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
