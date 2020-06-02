package bll;

import dal.CargoDAL;
import java.util.List;
import model.Cargo;

public class CargoBLL {
    CargoDAL dal;
    
    public CargoBLL() {
        dal = new CargoDAL();
    }
    
    public boolean salvar(Cargo cargo) throws Exception {
        return dal.adicionar(cargo);
    }

    public boolean editar(Cargo cargo) throws Exception {
        return dal.alterar(cargo);
    }

    public boolean remover(Cargo cargo) throws Exception {
        return dal.excluir(cargo.getCodigo());
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
