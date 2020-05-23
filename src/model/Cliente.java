package model;

import java.util.Date;

public class Cliente {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private Endereco idEndereco;
    private Contato idContato;

    public Cliente() {
        idEndereco = new Endereco();
        idContato = new Contato();
    }

    public Cliente(int codigo, String nome, String cpf, String email, Date dataNascimento, Endereco idEndereco, Contato idContato) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idEndereco = idEndereco;
        this.idContato = idContato;
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
    
    
}
