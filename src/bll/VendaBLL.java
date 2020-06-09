package bll;

import dal.VendaDAL;
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import model.Cliente;
import model.FormaPagamento;
import model.Funcionario;
import model.Imovel;
import model.Venda;

public class VendaBLL {
    VendaDAL dal;
    
    public VendaBLL() {
        dal = new VendaDAL();
    }
    
    public boolean salvar(Venda venda) throws Exception {
        return dal.adicionar(venda);
    }

    public boolean editar(Venda venda) throws Exception {
        return dal.alterar(venda);
    }

    public boolean remover(Venda venda) throws Exception {
        return dal.excluir(venda.getCodigo());
    }

    public List<Venda> consultar() {
        return dal.consultar();
    }

    public Venda consultaPorId(int id) {
        return dal.consultarPorId(id);
    }

    public Vector<Cliente> listarClientes() {
        return dal.listarClientes();
    }

    public Vector<Funcionario> listarFuncionarios() {
        return dal.listarFuncionarios();
    }

    public Vector<Imovel> listaImoveisVender(){
        return dal.listarImoveisVender();
    }
    
    public Vector<Imovel> listaImoveisAlugar(){
        return dal.listarImoveisAlugar();
    }
    
    public Vector<Imovel> listaImoveis(){
        return dal.listarImoveis();
    }
    
    public Vector<FormaPagamento> listarFormasDePagamento(){
        return dal.listarFormasdePagamento();
    }
    
    public double getPreco(int id){
        return dal.pegarPreco(id);
    }
    
    public boolean deixarImovelIndisponivel(int id, Date data){
        return dal.deixarImovelIndisponivel(id, data);
    }
    
    public boolean deixarImovelDisponivel(int id, Date data){
        return dal.deixarImovelDisponivel(id, data);
    }
    
    public boolean isData(String data) {
        String[] dataparticionada = data.split("/");
        int dia = Integer.parseInt(dataparticionada[0]);
        int mes = Integer.parseInt(dataparticionada[1]);
        int ano = Integer.parseInt(dataparticionada[2]);
        boolean anoBissexto = ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;

        if (((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia >= 1 && dia <= 31))
                || ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia >= 1 && dia <= 30))
                || ((mes == 2) && (anoBissexto) && (dia >= 1 && dia <= 29) && (ano >= 1920 && ano <= 2020))
                || ((mes == 2) && !(anoBissexto) && (dia >= 1 && dia <= 28) && (ano >= 1920 && ano <= 2020))) {

            return true;
        } else {
            return false;
        }
    }
}
