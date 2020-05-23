package bll;

import dal.ClienteDAL;
import java.util.List;
import java.util.Vector;
import model.Endereco;
import model.Cliente;
import model.Contato;

public class ClienteBLL {
    ClienteDAL dal;
    
    public ClienteBLL() {
        dal = new ClienteDAL();
    }
    
    public void salvar(Cliente cliente) throws Exception {
        dal.adicionar(cliente);
    }

    public void editar(Cliente cliente) throws Exception {
        dal.alterar(cliente);
    }

    public void remover(Cliente cliente) throws Exception {
        dal.excluir(cliente.getCodigo());
    }

    public List<Cliente> consultar() {
        return dal.consultar();
    }

    public Cliente consultaPorId(int id) {
        return dal.consultaPorId(id);
    }
    
    public Vector<Endereco> listarEnderecos(){
        return dal.listarEnderecos();
    }
    
    public Vector<Contato> listarContatos(){
        return dal.listarContatos();
    }
    
    public boolean verificarCPFsIguais(String cpf){
        return dal.verificarCpfIgual(cpf);
    }
}
