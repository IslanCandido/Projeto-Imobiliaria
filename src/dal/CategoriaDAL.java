package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import util.Conexao;

public class CategoriaDAL implements BasicoDAL<Categoria>{
    private Connection conexao;
    
    public CategoriaDAL() {
        conexao = Conexao.getConexao();
    }
    
    @Override
    public boolean adicionar(Categoria categoria) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("INSERT INTO categorias (cat_nome) VALUES (?)");
            ps.setString(1, categoria.getNome());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!");
        }
        return result;
    }
    
    @Override
    public boolean excluir(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("DELETE FROM categorias WHERE cat_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }
    
    @Override
    public boolean alterar(Categoria categoria) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("UPDATE Categorias SET cat_nome = ? WHERE cat_id = ?");
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }
    
    @Override
    public List<Categoria> consultar(){
        List<Categoria> categorias = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM categorias");
            while (rs.next()){
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!" + e);
        }
        return categorias;
    }
    
    @Override
    public Categoria consultarPorId(int id){
        Categoria categoria = new Categoria();
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("SELECT * FROM categorias WHERE cat_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return categoria;
    }
    
    public boolean verificarCategoriaIgual(String categoria) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM categorias WHERE cat_nome = ?");
            preparedStatement.setString(1, categoria);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CATEGORIAS!");
        }

        return resultado;
    }
}
