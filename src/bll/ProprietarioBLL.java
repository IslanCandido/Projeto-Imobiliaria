package bll;

import dal.ProprietarioDAL;
import java.util.List;
import java.util.Vector;
import model.Contato;
import model.Proprietario;

public class ProprietarioBLL {
    ProprietarioDAL dal;
    
    public ProprietarioBLL() {
        dal = new ProprietarioDAL();
    }
    
    public void salvar(Proprietario proprietario) throws Exception {
        dal.adicionar(proprietario);
    }

    public void editar(Proprietario proprietario) throws Exception {
        dal.alterar(proprietario);
    }

    public void remover(Proprietario proprietario) throws Exception {
        dal.excluir(proprietario.getCodigo());
    }

    public List<Proprietario> consultar() {
        return dal.consultar();
    }

    public Proprietario consultaPorId(int id) {
        return dal.consultaPorId(id);
    }
    
    public Vector<Contato> listarContatos(){
        return dal.listarContatos();
    }
    
    public boolean verificarCPFsIguais(String cpf){
        return dal.verificarCpfIgual(cpf);
    }
}
