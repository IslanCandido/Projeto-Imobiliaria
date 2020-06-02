package model;

import java.sql.Date;

public class Venda {
    private int codigo;
    private Date dataVenda;
    private Funcionario idFuncionario;
    private Imovel idImovel;
    private Cliente idCliente;
    private double valor;
    private int percentualComissao;

    public Venda() {
    }

    public Venda(int codigo, Date dataVenda, Funcionario idFuncionario, Imovel idImovel, Cliente idCliente, double valor, int percentualComissao) {
        this.codigo = codigo;
        this.dataVenda = dataVenda;
        this.idFuncionario = idFuncionario;
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.valor = valor;
        this.percentualComissao = percentualComissao;
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
    
    
}
