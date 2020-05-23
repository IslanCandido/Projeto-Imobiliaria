package bll;

import dal.FuncionarioDAL;
import java.util.List;
import java.util.Vector;
import model.Cargo;
import model.Funcionario;
import model.Contato;
import model.Endereco;

public class FuncionarioBLL {

    FuncionarioDAL dal;

    public FuncionarioBLL() {
        dal = new FuncionarioDAL();
    }

    public void salvar(Funcionario funcionario) throws Exception {
        dal.adicionar(funcionario);
    }

    public void editar(Funcionario funcionario) throws Exception {
        dal.alterar(funcionario);
    }

    public void remover(Funcionario funcionario) throws Exception {
        dal.excluir(funcionario.getCodigo());
    }

    public List<Funcionario> consultar() {
        return dal.consultar();
    }

    public Funcionario consultaPorId(int id) {
        return dal.consultaPorId(id);
    }

    public Vector<Endereco> listarEnderecos() {
        return dal.listarEnderecos();
    }

    public Vector<Contato> listarContatos() {
        return dal.listarContatos();
    }

    public Vector<Cargo> listarCargos() {
        return dal.listarCargos();
    }

    public boolean verificarPisIgual(String pis) {
        return dal.verificarPisIgual(pis);
    }

    public boolean verificarCpfIgual(String cpf) {
        return dal.verificarCpfIgual(cpf);
    }

    public boolean autenticarLogin(String usuario, String senha){
        return dal.autenticarUsuario(usuario, senha);
    }
    
    public String pegarNome(String cpf){
        return dal.pegarNomeUsuario(cpf);
    }
    
    public String pegarCargo(String cargo){
        return dal.pegarCargoUsuario(cargo);
    }
}
