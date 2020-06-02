package bll;

import dal.ImovelDAL;
import java.util.List;
import java.util.Vector;
import model.Categoria;
import model.Endereco;
import model.Imovel;
import model.Proprietario;

public class ImovelBLL {
    
    ImovelDAL dal;

    public ImovelBLL() {
        dal = new ImovelDAL();
    }

    public boolean salvar(Imovel imovel) throws Exception {
        return dal.adicionar(imovel);
    }

    public boolean editar(Imovel imovel) throws Exception {
        return dal.alterar(imovel);
    }

    public boolean remover(Imovel imovel) throws Exception {
        return dal.excluir(imovel.getCodigo());
    }

    public List<Imovel> consultar() {
        return dal.consultar();
    }
    
    public List<Imovel> mostrarDisponiveis() {
        return dal.consultarDisponiveis();
    }
    
    public List<Imovel> mostrarIndisponiveis() {
        return dal.consultarIndisponiveis();
    }

    public Imovel consultaPorId(int id) {
        return dal.consultarPorId(id);
    }

    public Vector<Endereco> listarEnderecos() {
        return dal.listarEnderecos();
    }

    public Vector<Categoria> listarCategorias() {
        return dal.listarCategorias();
    }

    public Vector<Proprietario> listarProprietarios() {
        return dal.listarProprietarios();
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
