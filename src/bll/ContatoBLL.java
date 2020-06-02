package bll;

import dal.ContatoDAL;
import java.util.List;
import model.Contato;

public class ContatoBLL {
    ContatoDAL dal;
    
    public ContatoBLL() {
        dal = new ContatoDAL();
    }
    
    public boolean salvar(Contato contato) throws Exception {
        return dal.adicionar(contato);
    }

    public boolean editar(Contato contato) throws Exception {
        return dal.alterar(contato);
    }

    public boolean remover(Contato contato) throws Exception {
        return dal.excluir(contato.getCodigo());
    }

    public List<Contato> consultar() {
        return dal.consultar();
    }

    public Contato consultaPorId(int id) {
        return dal.consultarPorId(id);
    }

    public boolean verificarNumerosIguais(String numero){
        return dal.verificarTelefoneIgual(numero);
    }
    
}
