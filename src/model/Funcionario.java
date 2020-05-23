package model;

import java.util.Date;

public class Funcionario {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private String pis;
    private String nContrato;
    private String senha;
    private Endereco idEndereco;
    private Contato idContato;
    private Cargo idCargo;

    public Funcionario() {
        idEndereco = new Endereco();
        idContato = new Contato();
        idCargo = new Cargo();
    }

    public Funcionario(int codigo, String nome, String cpf, String email, Date dataNascimento, String pis, String nContrato, String senha, Endereco idEndereco, Contato idContato, Cargo idCargo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.pis = pis;
        this.nContrato = nContrato;
        this.senha = senha;
        this.idEndereco = idEndereco;
        this.idContato = idContato;
        this.idCargo = idCargo;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getnContrato() {
        return nContrato;
    }

    public void setnContrato(String nContrato) {
        this.nContrato = nContrato;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Contato getIdContato() {
        return idContato;
    }

    public void setIdContato(Contato idContato) {
        this.idContato = idContato;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }
 
    
}
