package bll;

import dal.CategoriaDAL;
import java.util.List;
import model.Categoria;

public class CategoriaBLL {
    CategoriaDAL dal;
    
    public CategoriaBLL() {
        dal = new CategoriaDAL();
    }
    
    public boolean salvar(Categoria categoria) throws Exception {
        return dal.adicionar(categoria);
    }

    public boolean editar(Categoria categoria) throws Exception {
        return dal.alterar(categoria);
    }

    public boolean remover(Categoria categoria) throws Exception {
        return dal.excluir(categoria.getCodigo());
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
