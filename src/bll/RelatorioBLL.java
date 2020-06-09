package bll;

import dal.RelatoriosDAL;
import java.sql.Date;
import java.util.List;
import model.Imovel;

public class RelatorioBLL {
    
    RelatoriosDAL dal;

    public RelatorioBLL() {
        dal = new RelatoriosDAL();
    }    
    
    public List<Imovel> relatorioImoveisPorCidade(String cidade){
        return dal.mostrarImoveisPorCidade(cidade);
    }
    
    public List<Imovel> relatorioImoveisPorDataInscricao(Date dataInscricao){
        return dal.mostrarImoveisPorDataInscricao(dataInscricao);
    }
    
    public List<Imovel> relatorioImoveisPorCategoria(String categoria){
        return dal.mostrarImoveisPorCategoria(categoria);
    }
    
    public List<Imovel> relatorioImoveisPorCorretor(String cpfCorretor){
        return dal.mostrarImoveisPorCorretor(cpfCorretor);
    }
    
    public List<Imovel> relatorioImoveisPorSituacao(String situacao){
        return dal.mostrarImoveisPorSituacao(situacao);
    }
    
    public List<Imovel> relatorioImoveisPorMotivoBaixa(String motivo){
        return dal.mostrarImoveisPorMotivoBaixa(motivo);
    }
    
    public List<Imovel> relatorioImoveisPorCliente(String cpfCliente){
        return dal.mostrarImoveisPorCliente(cpfCliente);
    }
    
    /*public List<Imovel> relatorioImoveisPorDataVenda(Date dataVenda){
        return dal.mostrarImoveisPorDataVenda(dataVenda);
    }*/
    
    public List<Imovel> relatorioImoveisCadastrados(){
        return dal.mostrarImoveisCadastrados();
    }
}
