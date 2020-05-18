package bll;

import dal.EnderecoDAL;
import java.util.List;
import model.Endereco;

public class EnderecoBLL {
    EnderecoDAL dal;
    
    public EnderecoBLL() {
        dal = new EnderecoDAL();
    }
    
    public void salvar(Endereco endereco) throws Exception {
        dal.adicionar(endereco);
    }

    public void editar(Endereco endereco) throws Exception {
        dal.alterar(endereco);
    }

    public void remover(Endereco endereco) throws Exception {
        dal.excluir(endereco.getCodigo());
    }

    public List<Endereco> consultar() {
        return dal.consultar();
    }

    public Endereco consultaPorId(int id) {
        return dal.consultaPorId(id);
    }
    
    public boolean verificarCEPsIguais(String cep){
        return dal.verificarCepIgual(cep);
    }
}
