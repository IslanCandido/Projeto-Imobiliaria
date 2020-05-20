package model;

public class Cargo {
    private int codigo;
    private String descricao;
    private float salario;

    public Cargo() {
    }

    public Cargo(int codigo, String nome, float salario) {
        this.codigo = codigo;
        this.descricao = nome;
        this.salario = salario;
    }

    public String toString() {
        return getDescricao();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
    
}
