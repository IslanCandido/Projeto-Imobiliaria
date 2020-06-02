package bll;

public abstract class ValidacoesPessoasBLL {

    public boolean validacoes(String email, String data, String CPF) {
        if (isEmail(email) && isData(data) && isCpf(CPF)) {
            return true;
        } else{
            return false;
        }
    }

    public abstract boolean isEmail(String email);

    public abstract boolean isData(String data);

    public abstract boolean isCpf(String CPF);

}
