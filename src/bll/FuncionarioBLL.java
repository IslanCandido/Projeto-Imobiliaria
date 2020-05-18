package bll;

import dal.FuncionarioDAL;
import java.util.List;
import java.util.Vector;
import model.Cargo;
import model.Funcionario;
import model.Pessoa;

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
    
    public Vector<Pessoa> listarPessoas(){
        return dal.listarPessoas();
    }
    
    public Vector<Cargo> listarCargos(){
        return dal.listarCargos();
    }
}
