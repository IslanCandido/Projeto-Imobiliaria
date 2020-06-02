package dal;

import java.util.List;

public interface BasicoDAL<T> { 
    public boolean adicionar(T bean);
    public boolean alterar(T bean);
    public boolean excluir(int id);
    public T consultarPorId(int id);
    public List<T> consultar();
}
