package bll;

import dal.PessoaDAL;
import java.util.List;
import java.util.Vector;
import model.Endereco;
import model.Pessoa;

public class PessoaBLL {
    PessoaDAL dal;
    
    public PessoaBLL() {
        dal = new PessoaDAL();
    }
    
    public void salvar(Pessoa pessoa) throws Exception {
        dal.adicionar(pessoa);
    }

    public void editar(Pessoa pessoa) throws Exception {
        dal.alterar(pessoa);
    }

    public void remover(Pessoa pessoa) throws Exception {
        dal.excluir(pessoa.getCodigo());
    }

    public List<Pessoa> consultar() {
        return dal.consultar();
    }

    public Pessoa consultaPorId(int id) {
        return dal.consultaPorId(id);
    }
    
    public Vector<Endereco> listarEnderecos(){
        return dal.listarEnderecos();
    }
    
    public boolean verificarCPFsIguais(String cpf){
        return dal.verificarCpfIgual(cpf);
    }
}
