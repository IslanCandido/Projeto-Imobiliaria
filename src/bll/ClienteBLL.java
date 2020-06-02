package bll;

import dal.ClienteDAL;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Endereco;
import model.Cliente;
import model.Contato;

public class ClienteBLL extends ValidacoesPessoasBLL {

    ClienteDAL dal;

    public ClienteBLL() {
        dal = new ClienteDAL();
    }

    public boolean salvar(Cliente cliente) throws Exception {
        return dal.adicionar(cliente);
    }

    public boolean editar(Cliente cliente) throws Exception {
        return dal.alterar(cliente);
    }

    public boolean remover(Cliente cliente) throws Exception {
        return dal.excluir(cliente.getCodigo());
    }

    public List<Cliente> consultar() {
        return dal.consultar();
    }

    public Cliente consultaPorId(int id) {
        return dal.consultarPorId(id);
    }

    public Vector<Endereco> listarEnderecos() {
        return dal.listarEnderecos();
    }

    public Vector<Contato> listarContatos() {
        return dal.listarContatos();
    }

    public boolean verificarCPFsIguais(String cpf) {
        return dal.verificarCpfIgual(cpf);
    }

    @Override
    public boolean isEmail(String email) {
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isData(String data) {
        String[] dataparticionada = data.split("/");
        int dia = Integer.parseInt(dataparticionada[0]);
        int mes = Integer.parseInt(dataparticionada[1]);
        int ano = Integer.parseInt(dataparticionada[2]);
        boolean anoBissexto = ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;

        if (((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia >= 1 && dia <= 31) && (ano >= 1920 && ano <= 2002))
                || ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia >= 1 && dia <= 30) && (ano >= 1920 && ano <= 2002))
                || ((mes == 2) && (anoBissexto) && (dia >= 1 && dia <= 29) && (ano >= 1920 && ano <= 2002))
                || ((mes == 2) && !(anoBissexto) && (dia >= 1 && dia <= 28) && (ano >= 1920 && ano <= 2002))) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isCpf(String CPF) {
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
