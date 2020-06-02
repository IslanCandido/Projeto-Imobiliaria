package bll;

import dal.EnderecoDAL;
import java.util.List;
import model.Endereco;

public class EnderecoBLL {
    EnderecoDAL dal;
    
    public EnderecoBLL() {
        dal = new EnderecoDAL();
    }
    
    public boolean salvar(Endereco endereco) throws Exception {
        return dal.adicionar(endereco);
    }

    public boolean editar(Endereco endereco) throws Exception {
        return dal.alterar(endereco);
    }

    public boolean remover(Endereco endereco) throws Exception {
        return dal.excluir(endereco.getCodigo());
    }

    public List<Endereco> consultar() {
        return dal.consultar();
    }

    public Endereco consultaPorId(int id) {
        return dal.consultarPorId(id);
    }
    
    public boolean verificarCEPsIguais(String cep){
        return dal.verificarCepIgual(cep);
    }
}
