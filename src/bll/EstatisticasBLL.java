package bll;

import dal.EstatisticasDAL;
import java.sql.Date;
import java.util.List;
import model.Venda;

public class EstatisticasBLL {
    EstatisticasDAL dal;

    public EstatisticasBLL() {
        dal = new EstatisticasDAL();
    }
    
    public List<Venda> EstatitiscasPorData(Date dtInicio, Date dtFim){
        return dal.MostarPorData(dtInicio, dtFim);
    }
    
    public List<Venda> EstatitiscasPorCorretor(String cpf){
        return dal.MostrarPorCorretor(cpf);
    }
    
    public int totalVendas(){
        return dal.numeroDeVendas();
    }
}
