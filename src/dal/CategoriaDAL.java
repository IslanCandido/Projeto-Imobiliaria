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

public class CategoriaDAL {
    private Connection conexao;

    public CategoriaDAL() {
        conexao = Conexao.getConexao();
    }
    
    public void adicionar(Categoria categoria) {
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("INSERT INTO Categorias (nome) VALUES (?)");
            ps.setString(1, categoria.getNome());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!");
        }
    }
    
    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("DELETE FROM Categorias WHERE id_cat = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }
    
    public void alterar(Categoria categoria) {
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("UPDATE Categorias SET nome = ? WHERE id_cat = ?");
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }
    
    public List<Categoria> consultar(){
        List<Categoria> categorias = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM Categorias");
            while (rs.next()){
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("id_cat"));
                categoria.setNome(rs.getString("nome"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return categorias;
    }
    
    public Categoria consultarPorId(int id){
        Categoria categoria = new Categoria();
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("SELECT * FROM Categorias WHERE id_cat = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                categoria.setCodigo(rs.getInt("id_cat"));
                categoria.setNome(rs.getString("nome"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return categoria;
    }
    
    public boolean verificarCategoriaIgual(String categoria) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM categorias WHERE nome = ?");
            preparedStatement.setString(1, categoria);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CATEGORIAS!");
        }

        return resultado;
    }
}
