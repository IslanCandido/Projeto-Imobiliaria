package model;

public class Categoria {
    private int codigo;
    private String nome;
    
    public Categoria() {
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String toString() {
        return getNome();
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
    
    
}
