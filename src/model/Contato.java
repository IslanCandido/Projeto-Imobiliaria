package model;

public class Contato {
    private int codigo;
    private String tipo;
    private String numero;

    public Contato() {
    }

    public Contato(int codigo, String tipo, String numero) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return getNumero();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
