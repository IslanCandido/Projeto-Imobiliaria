package model;

import java.sql.Date;

public class Venda {

    private int codigo;
    private Date dataVenda;
    private Funcionario idFuncionario;
    private Imovel idImovel;
    private Cliente idCliente;
    private FormaPagamento idFormaPagamento;
    private double valor;
    private int percentualComissao;
    private int mesesPagos;

    public Venda() {
        idFuncionario = new Funcionario();
        idImovel = new Imovel();
        idCliente = new Cliente();
        idFormaPagamento = new FormaPagamento();
    }

    public Venda(int codigo, Date dataVenda, Funcionario idFuncionario, Imovel idImovel, Cliente idCliente, FormaPagamento idFormaPagamento, double valor, int percentualComissao, int mesesPagos) {
        this.codigo = codigo;
        this.dataVenda = dataVenda;
        this.idFuncionario = idFuncionario;
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.idFormaPagamento = idFormaPagamento;
        this.valor = valor;
        this.percentualComissao = percentualComissao;
        this.mesesPagos = mesesPagos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Imovel getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Imovel idImovel) {
        this.idImovel = idImovel;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public FormaPagamento getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(FormaPagamento idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(int percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public int getMesesPagos() {
        return mesesPagos;
    }

    public void setMesesPagos(int mesesPagos) {
        this.mesesPagos = mesesPagos;
    }

   
}
