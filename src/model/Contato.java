package model;

public class Contato {
    private int codigo;
    private String tipo;
    private String numero;
    private Pessoa idPessoa;

    public Contato() {
        idPessoa = new Pessoa();
    }

    public Contato(int codigo, String tipo, String numero, Pessoa idPessoa) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.numero = numero;
        this.idPessoa = idPessoa;
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

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    
}
