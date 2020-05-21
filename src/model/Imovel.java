package model;

import java.sql.Date;

public class Imovel {
    private int codigo;
    private Date dtInscricao;
    private Categoria idCategoria;
    private Endereco idEndereco;
    private float metros;
    private int nQuartos;
    private int nSuites;
    private String descricao;
    private double preco;
    private String situacao;
    private Date dtBaixa;
    private String motivo;

    public Imovel() {
        idCategoria = new Categoria();
        idEndereco = new Endereco();
    }

    public Imovel(int codigo, Date dtInscricao, Categoria idCategoria, Endereco idEndereco, float metros, int nQuartos, int nSuites, String descricao, double preco, String situacao, Date dt_baixa, String motivo) {
        this.codigo = codigo;
        this.dtInscricao = dtInscricao;
        this.idCategoria = idCategoria;
        this.idEndereco = idEndereco;
        this.metros = metros;
        this.nQuartos = nQuartos;
        this.nSuites = nSuites;
        this.descricao = descricao;
        this.preco = preco;
        this.situacao = situacao;
        this.dtBaixa = dt_baixa;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return getIdCategoria().getNome() + " - " + getDescricao();
    }    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDtInscricao() {
        return dtInscricao;
    }

    public void setDtInscricao(Date dtInscricao) {
        this.dtInscricao = dtInscricao;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    public float getMetros() {
        return metros;
    }

    public void setMetros(float metros) {
        this.metros = metros;
    }

    public int getnQuartos() {
        return nQuartos;
    }

    public void setnQuartos(int nQuartos) {
        this.nQuartos = nQuartos;
    }

    public int getnSuites() {
        return nSuites;
    }

    public void setnSuites(int nSuites) {
        this.nSuites = nSuites;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDt_baixa() {
        return dtBaixa;
    }

    public void setDt_baixa(Date dt_baixa) {
        this.dtBaixa = dt_baixa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
}
