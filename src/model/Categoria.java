package model;

public class Categoria {
    //private static Categoria categoriaGeral = null; 
    private int codigo;
    private String nome;

    /*public static Categoria getCategoria(){
        //if(categoriaGeral == null){
            categoriaGeral = new Categoria();
        //}
        return categoriaGeral;
    }
    
    private Categoria() {
    }*/
    
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
