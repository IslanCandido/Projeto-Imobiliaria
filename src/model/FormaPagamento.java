package model;

public class FormaPagamento {
    private int codigo;
    private String formaPagamento;

    public FormaPagamento() {
    }

    public FormaPagamento(int codigo, String formaPagamento) {
        this.codigo = codigo;
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return getFormaPagamento();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
}
