package view;

import bll.VendaBLL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.FormaPagamento;
import model.Funcionario;
import model.Imovel;
import model.Venda;

public class FrmVendas extends javax.swing.JFrame {

    private static FrmVendas telaVendasGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    VendaBLL vendaBll = new VendaBLL();
    Venda venda = new Venda();

    Vector<Cliente> vetorClientes;
    Vector<Funcionario> vetorFuncionarios;
    Vector<Imovel> vetorImoveis;
    Vector<FormaPagamento> vetorFormasPagamentos;

    DecimalFormat df = new DecimalFormat("####");

    public static FrmVendas getTelaVenda() {
        if (telaVendasGeral == null) {
            telaVendasGeral = new FrmVendas();
        }
        return telaVendasGeral;
    }

    private FrmVendas() {

        criarTabela();
        consultar();
        initComponents();
        txtDataVenda.setText(getDataAtual());
        iniciar();
        preencherCbxs();
    }

    private void iniciar() {
        cbxTipodeVenda.setSelectedIndex(0);
        txtComissao.setEnabled(false);
        txtDataVenda.setEnabled(false);
        txtValor.setEnabled(false);
        txtMesesPagos.setEnabled(false);
        cbxClientes.setEnabled(false);
        cbxFormasDePagamento.setEnabled(false);
        cbxFuncionarios.setEnabled(false);
        cbxImoveis.setEnabled(false);
        btnAdicionarCliente.setEnabled(false);
        btnAdicionarFormaPagamento.setEnabled(false);
        btnAdicionarImovel.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        jLabel4.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel8.setEnabled(false);
        jLabel9.setEnabled(false);
        lblPorcentagem.setEnabled(false);
    }

    private void liberar() {
        txtComissao.setEnabled(true);
        txtDataVenda.setEnabled(true);
        txtValor.setEnabled(true);
        txtMesesPagos.setEnabled(true);
        cbxClientes.setEnabled(true);
        cbxFormasDePagamento.setEnabled(true);
        cbxFuncionarios.setEnabled(true);
        cbxImoveis.setEnabled(true);
        btnAdicionarCliente.setEnabled(true);
        btnAdicionarFormaPagamento.setEnabled(true);
        btnAdicionarImovel.setEnabled(true);
        jLabel1.setEnabled(true);
        jLabel2.setEnabled(true);
        jLabel3.setEnabled(true);
        jLabel4.setEnabled(true);
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel9.setEnabled(true);
        lblPorcentagem.setEnabled(true);
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
        tblVendas = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Data Venda");
        modelo.addColumn("Funcionário");
        modelo.addColumn("Cliente");
        modelo.addColumn("Imovel");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pagamento");
        modelo.addColumn("Comissão");

        tblVendas.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblVendas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblVendas.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblVendas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblVendas.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblVendas.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblVendas.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblVendas.getColumnModel().getColumn(7).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Venda> lista = new ArrayList<Venda>();

        lista = vendaBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getDataVenda(),
                    lista.get(i).getIdFuncionario().getNome(),
                    lista.get(i).getIdCliente().getNome(),
                    lista.get(i).getIdImovel().getIdCategoria().getNome(),
                    lista.get(i).getValor(),
                    lista.get(i).getIdFormaPagamento().getFormaPagamento(),
                    lista.get(i).getPercentualComissao()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        venda = vendaBll.consultaPorId(id);
        txtComissao.setText(String.valueOf(venda.getPercentualComissao()));
        txtValor.setText(String.valueOf(venda.getValor()));
        txtDataVenda.setText(convertDate(venda.getDataVenda()));
        cbxClientes.setSelectedItem(venda.getIdCliente());
        cbxFuncionarios.setSelectedItem(venda.getIdFuncionario());
        cbxFormasDePagamento.setSelectedItem(venda.getIdFormaPagamento());
        txtMesesPagos.setText(String.valueOf(venda.getMesesPagos()));
    }

    private void limparCampos() {
        txtComissao.setText("");
        txtValor.setText("");
        txtDataVenda.setText(getDataAtual());
        cbxClientes.setSelectedIndex(0);
        cbxFuncionarios.setSelectedIndex(0);
        cbxImoveis.setSelectedIndex(0);
        cbxFormasDePagamento.setSelectedIndex(0);
        cbxTipodeVenda.setSelectedIndex(0);
        txtMesesPagos.setText("");
        iniciar();
        cbxTipodeVenda.setEnabled(true);
        btnSalvar.setEnabled(true);
    }

    private void preencherCbxs() {
        vetorClientes = vendaBll.listarClientes();
        cbxClientes.setModel(new DefaultComboBoxModel(vetorClientes));

        vetorFuncionarios = vendaBll.listarFuncionarios();
        cbxFuncionarios.setModel(new DefaultComboBoxModel(vetorFuncionarios));

        vetorFormasPagamentos = vendaBll.listarFormasDePagamento();
        cbxFormasDePagamento.setModel(new DefaultComboBoxModel(vetorFormasPagamentos));
        
        vetorImoveis = vendaBll.listaImoveis();
        cbxImoveis.setModel(new DefaultComboBoxModel(vetorImoveis));
    }

    private String getDataAtual() {
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    public double calcularValorAlugel() {
        double valor = Double.parseDouble(txtValor.getText());
        int meses = Integer.parseInt(txtMesesPagos.getText());

        if(cbxTipodeVenda.getSelectedItem().equals("Aluguel")){
            
            return valor * meses;
        } else{
            return valor;
        }   
    }
    
    private void imovel(){
        venda.setIdImovel(vetorImoveis.get(cbxImoveis.getSelectedIndex()));
        int id = venda.getIdImovel().getCodigo();
        txtValor.setText(String.valueOf(df.format(vendaBll.getPreco(id))));
        txtValor.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEditar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        cbxClientes = new javax.swing.JComboBox<>();
        cbxImoveis = new javax.swing.JComboBox<>();
        txtValor = new javax.swing.JTextField();
        txtComissao = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAdicionarCliente = new javax.swing.JButton();
        btnAdicionarImovel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDataVenda = new javax.swing.JFormattedTextField();
        lblPorcentagem = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxFormasDePagamento = new javax.swing.JComboBox<>();
        btnAdicionarFormaPagamento = new javax.swing.JButton();
        txtMesesPagos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxTipodeVenda = new javax.swing.JComboBox<>();
        teladeFundo = new javax.swing.JLabel();

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de vendas");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Comissão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(560, 60, 110, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Valor");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(560, 160, 40, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Cliente");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 160, 50, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText(" Imóvel");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 110, 50, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Funcionário");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(200, 60, 80, 20);

        getContentPane().add(cbxFuncionarios);
        cbxFuncionarios.setBounds(200, 80, 330, 28);

        getContentPane().add(cbxClientes);
        cbxClientes.setBounds(40, 180, 320, 28);

        cbxImoveis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxImoveisItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxImoveis);
        cbxImoveis.setBounds(40, 130, 450, 28);

        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });
        getContentPane().add(txtValor);
        txtValor.setBounds(560, 180, 150, 28);

        txtComissao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComissaoKeyTyped(evt);
            }
        });
        getContentPane().add(txtComissao);
        txtComissao.setBounds(560, 80, 150, 28);

        tblVendas.setModel(modelo);
        tblVendas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVendas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 230, 750, 160);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_venda_realizada.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(470, 400, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_venda_cancelada.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(540, 400, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(610, 400, 55, 41);

        btnAdicionarCliente.setText("+");
        btnAdicionarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCliente);
        btnAdicionarCliente.setBounds(360, 180, 41, 28);

        btnAdicionarImovel.setText("+");
        btnAdicionarImovel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarImovel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarImovelActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarImovel);
        btnAdicionarImovel.setBounds(490, 130, 41, 28);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(" R$");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(710, 180, 20, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText(" Data de Venda");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 60, 90, 20);

        try {
            txtDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataVendaActionPerformed(evt);
            }
        });
        txtDataVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataVendaKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataVenda);
        txtDataVenda.setBounds(40, 80, 130, 28);

        lblPorcentagem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPorcentagem.setText(" %");
        getContentPane().add(lblPorcentagem);
        lblPorcentagem.setBounds(710, 80, 20, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Forma de pagamento");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(560, 110, 121, 20);

        getContentPane().add(cbxFormasDePagamento);
        cbxFormasDePagamento.setBounds(560, 130, 130, 28);

        btnAdicionarFormaPagamento.setText("+");
        btnAdicionarFormaPagamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarFormaPagamentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarFormaPagamento);
        btnAdicionarFormaPagamento.setBounds(690, 130, 41, 28);

        txtMesesPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMesesPagosKeyTyped(evt);
            }
        });
        getContentPane().add(txtMesesPagos);
        txtMesesPagos.setBounds(430, 180, 100, 28);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Meses Pagos");
        jLabel9.setToolTipText("");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(430, 160, 80, 20);

        cbxTipodeVenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbxTipodeVenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo de venda", "Venda", "Aluguel" }));
        cbxTipodeVenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipodeVendaItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxTipodeVenda);
        cbxTipodeVenda.setBounds(250, 10, 220, 30);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, -10, 830, 490);

        setSize(new java.awt.Dimension(775, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    FrmClientes telaPessoas;
    FrmImoveis telaImoveis;
    FrmFormaPagamento telaFormaPagamento;

    private void btnAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarClienteActionPerformed
        if (telaPessoas == null) {
            telaPessoas = FrmClientes.getTelaCliente();
            telaPessoas.setVisible(true);
        } else {
            telaPessoas.dispose();
            telaPessoas.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarClienteActionPerformed

    private void btnAdicionarImovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarImovelActionPerformed
        if (telaImoveis == null) {
            telaImoveis = FrmImoveis.getTelaImovel();
            telaImoveis.setVisible(true);
        } else {
            telaImoveis.dispose();
            telaImoveis.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarImovelActionPerformed

    private void txtDataVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataVendaActionPerformed

    }//GEN-LAST:event_txtDataVendaActionPerformed

    private void txtDataVendaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataVendaKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDataVendaKeyTyped

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            venda.setDataVenda(CriarNovaData(txtDataVenda.getText()));
            venda.setPercentualComissao(Integer.parseInt(txtComissao.getText()));
            venda.setMesesPagos(Integer.parseInt(txtMesesPagos.getText()));
            venda.setValor(calcularValorAlugel());
            venda.setIdCliente(vetorClientes.get(cbxClientes.getSelectedIndex()));
            venda.setIdFuncionario(vetorFuncionarios.get(cbxFuncionarios.getSelectedIndex()));
            venda.setIdImovel(vetorImoveis.get(cbxImoveis.getSelectedIndex()));
            venda.setIdFormaPagamento(vetorFormasPagamentos.get(cbxFormasDePagamento.getSelectedIndex()));

            if (txtDataVenda.getText().isEmpty() || txtComissao.getText().isEmpty() || txtValor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else if (venda.getPercentualComissao() > 20 || venda.getPercentualComissao() < 3) {
                JOptionPane.showMessageDialog(rootPane, "COMISSÃO INVALIDA!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (vendaBll.isData(txtDataVenda.getText())) {
                    if (vendaBll.salvar(venda)) {
                        JOptionPane.showMessageDialog(rootPane, "Venda realizada com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                        if (vendaBll.deixarImovelIndisponivel(venda.getIdImovel().getCodigo(), CriarNovaData(getDataAtual()))) {
                            JOptionPane.showMessageDialog(rootPane, "Imovel não está mais disponivel para venda!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Erro ao mudar disponibilidade do imovel!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao realizar venda!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                    preencherCbxs();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "DATA DE VENDA INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!" + e, "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtDataVenda.getText().isEmpty() || txtComissao.getText().isEmpty() || txtValor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (vendaBll.remover(vendaBll.consultaPorId(venda.getCodigo()))) {
                    JOptionPane.showMessageDialog(rootPane, "Venda cancelada com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    if (vendaBll.deixarImovelDisponivel(venda.getIdImovel().getCodigo(), CriarNovaData("00/00/0000"))) {
                        JOptionPane.showMessageDialog(rootPane, "Imovel agora está disponivel para venda!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao mudar disponibilidade do imovel!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao cancelar venda!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
        preencherCbxs();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            venda.setDataVenda(CriarNovaData(txtDataVenda.getText()));
            venda.setPercentualComissao(Integer.parseInt(txtComissao.getText()));
            venda.setValor(Double.parseDouble(txtValor.getText()));
            venda.setMesesPagos(Integer.parseInt(txtMesesPagos.getText()));
            venda.setIdCliente(vetorClientes.get(cbxClientes.getSelectedIndex()));
            venda.setIdFuncionario(vetorFuncionarios.get(cbxFuncionarios.getSelectedIndex()));
            venda.setIdImovel(vetorImoveis.get(cbxImoveis.getSelectedIndex()));
            venda.setIdFormaPagamento(vetorFormasPagamentos.get(cbxFormasDePagamento.getSelectedIndex()));

            if (txtDataVenda.getText().isEmpty() || txtComissao.getText().isEmpty() || txtValor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (vendaBll.isData(txtDataVenda.getText())) {
                    if (vendaBll.editar(venda)) {
                        JOptionPane.showMessageDialog(rootPane, "Editado com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao editar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                    preencherCbxs();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "DATA DE VENDA INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
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

    private void tblVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendasMouseClicked
        btnSalvar.setEnabled(false);
        liberar();
        cbxTipodeVenda.setEnabled(false);
        int linha = tblVendas.getSelectedRow();
        Integer codigo = (Integer) tblVendas.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblVendasMouseClicked

    private void cbxImoveisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxImoveisItemStateChanged
        venda.setIdImovel(vetorImoveis.get(cbxImoveis.getSelectedIndex()));
        int id = venda.getIdImovel().getCodigo();
        txtValor.setText(String.valueOf(df.format(vendaBll.getPreco(id))));
        txtValor.setEnabled(true);
    }//GEN-LAST:event_cbxImoveisItemStateChanged

    private void btnAdicionarFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarFormaPagamentoActionPerformed
        if (telaFormaPagamento == null) {
            telaFormaPagamento = FrmFormaPagamento.getTelaFormaPagamento();
            telaFormaPagamento.setVisible(true);
        } else {
            telaFormaPagamento.dispose();
            telaFormaPagamento.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarFormaPagamentoActionPerformed

    private void cbxTipodeVendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipodeVendaItemStateChanged
        if (cbxTipodeVenda.getSelectedIndex() == 1) {
            liberar();
            imovel();
            txtMesesPagos.setEnabled(false);
            cbxTipodeVenda.setEnabled(false);
            txtMesesPagos.setText("0");
            
            vetorImoveis = vendaBll.listaImoveisVender();
            cbxImoveis.setModel(new DefaultComboBoxModel(vetorImoveis));
        }
        if (cbxTipodeVenda.getSelectedIndex() == 2) {
            liberar();
            imovel();
            cbxTipodeVenda.setEnabled(false);

            vetorImoveis = vendaBll.listaImoveisAlugar();
            cbxImoveis.setModel(new DefaultComboBoxModel(vetorImoveis));
        }
    }//GEN-LAST:event_cbxTipodeVendaItemStateChanged

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtMesesPagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesesPagosKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtMesesPagos.getText().length();
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
    }//GEN-LAST:event_txtMesesPagosKeyTyped

    private void txtComissaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComissaoKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtComissao.getText().length();
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
    }//GEN-LAST:event_txtComissaoKeyTyped

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
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCliente;
    private javax.swing.JButton btnAdicionarFormaPagamento;
    private javax.swing.JButton btnAdicionarImovel;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxClientes;
    private javax.swing.JComboBox<String> cbxFormasDePagamento;
    private javax.swing.JComboBox<String> cbxFuncionarios;
    private javax.swing.JComboBox<String> cbxImoveis;
    private javax.swing.JComboBox<String> cbxTipodeVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPorcentagem;
    private javax.swing.JTable tblVendas;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JTextField txtComissao;
    private javax.swing.JFormattedTextField txtDataVenda;
    private javax.swing.JTextField txtMesesPagos;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
