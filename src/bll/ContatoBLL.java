package bll;

import dal.ContatoDAL;
import java.util.List;
import java.util.Vector;
import model.Contato;
import model.Pessoa;

public class ContatoBLL {
    ContatoDAL dal;
    
    public ContatoBLL() {
        dal = new ContatoDAL();
    }
    
    public void salvar(Contato contato) throws Exception {
        dal.adicionar(contato);
    }

    public void editar(Contato contato) throws Exception {
        dal.alterar(contato);
    }

    public void remover(Contato contato) throws Exception {
        dal.excluir(contato.getCodigo());
    }

    public List<Contato> consultar() {
        return dal.consultar();
    }

    public Contato consultaPorId(int id) {
        return dal.consultaPorId(id);
    }
    
    public Vector<Pessoa> listarPessoas(){
        return dal.listarPessoas();
    }
    
    public boolean verificarNumerosIguais(String numero){
        return dal.verificarTelefoneIgual(numero);
    }
    
}
