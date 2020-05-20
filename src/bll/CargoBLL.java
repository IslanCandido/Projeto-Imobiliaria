package bll;

import dal.CargoDAL;
import java.util.List;
import model.Cargo;

public class CargoBLL {
    CargoDAL dal;
    
    public CargoBLL() {
        dal = new CargoDAL();
    }
    
    public void salvar(Cargo cargo) throws Exception {
        dal.adicionar(cargo);
    }

    public void editar(Cargo cargo) throws Exception {
        dal.alterar(cargo);
    }

    public void remover(Cargo cargo) throws Exception {
        dal.excluir(cargo.getCodigo());
    }
    
     public List<Cargo> consultar(){
        return dal.consultar();
    }
     
    public Cargo consultaPorId(int id){
        return dal.consultarPorId(id);
    }
    
    public boolean verificarCargosIguais(String cargo){
        return dal.verificarCargoIgual(cargo);
    }
}
