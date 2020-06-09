package bll;

import dal.FormaPagamentoDAL;
import java.util.List;
import model.FormaPagamento;

public class FormaPagamentoBLL {
    FormaPagamentoDAL dal;
    
    public FormaPagamentoBLL() {
        dal = new FormaPagamentoDAL();
    }
    
    public boolean salvar(FormaPagamento formaPagamento) throws Exception {
        return dal.adicionar(formaPagamento);
    }

    public boolean editar(FormaPagamento formaPagamento) throws Exception {
        return dal.alterar(formaPagamento);
    }

    public boolean remover(FormaPagamento formaPagamento) throws Exception {
        return dal.excluir(formaPagamento.getCodigo());
    }
    
     public List<FormaPagamento> consultar(){
        return dal.consultar();
    }
     
    public FormaPagamento consultaPorId(int id){
        return dal.consultarPorId(id);
    }
    
    public boolean verificarFormaPagamentoIgual(String formaPagamento){
        return dal.verificarFormaPagamentoIgual(formaPagamento);
    }
    
}
