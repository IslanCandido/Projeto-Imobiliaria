package model;

import java.util.Date;

public class Pessoa {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private Endereco idEndereco;

    public Pessoa() {
        idEndereco = new Endereco();
    }

    public Pessoa(int codigo, String nome, String cpf, String email, Date dataNascimento, Endereco idEndereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idEndereco = idEndereco;
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
    
    
}
