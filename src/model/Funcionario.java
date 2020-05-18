package model;

public class Funcionario {
    private int codigo;
    private String pis;
    private String nContrato;
    private String nCarteiraTrabalho;
    private String senha;
    private Pessoa idPessoa;
    private Cargo idCargo;

    public Funcionario() {
        idPessoa = new Pessoa();
        idCargo = new Cargo();
    }

    public Funcionario(int codigo, String pis, String nContrato, String nCarteiraTrabalho, String senha, Pessoa idPessoa, Cargo idCargo) {
        this.codigo = codigo;
        this.pis = pis;
        this.nContrato = nContrato;
        this.nCarteiraTrabalho = nCarteiraTrabalho;
        this.senha = senha;
        this.idPessoa = idPessoa;
        this.idCargo = idCargo;
    }

    @Override
    public String toString() {
        return getIdPessoa().getNome();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getnCarteiraTrabalho() {
        return nCarteiraTrabalho;
    }

    public void setnCarteiraTrabalho(String nCarteiraTrabalho) {
        this.nCarteiraTrabalho = nCarteiraTrabalho;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }
    
    
}
