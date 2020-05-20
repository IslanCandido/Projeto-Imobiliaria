package bll;

import dal.CategoriaDAL;
import java.util.List;
import model.Categoria;

public class CategoriaBLL {
    CategoriaDAL dal;
    
    public CategoriaBLL() {
        dal = new CategoriaDAL();
    }
    
    public void salvar(Categoria categoria) throws Exception {
        dal.adicionar(categoria);
    }

    public void editar(Categoria categoria) throws Exception {
        dal.alterar(categoria);
    }

    public void remover(Categoria categoria) throws Exception {
        dal.excluir(categoria.getCodigo());
    }
    
     public List<Categoria> consultar(){
        return dal.consultar();
    }
     
    public Categoria consultaPorId(int id){
        return dal.consultarPorId(id);
    }
    
    public boolean verificarCategoriasIguais(String categoria){
        return dal.verificarCategoriaIgual(categoria);
    }
}
