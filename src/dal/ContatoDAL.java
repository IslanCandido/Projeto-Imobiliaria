package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contato;
import util.Conexao;

public class ContatoDAL implements BasicoDAL<Contato>{

    private Connection conexao;

    public ContatoDAL() {
        conexao = Conexao.getConexao();
    }

    @Override
    public boolean adicionar(Contato contato) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO contatos (con_tipo, con_numero) VALUES (?, ?)");

            ps.setString(1, contato.getTipo());
            ps.setString(2, contato.getNumero());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
        return result;
    }

    public boolean excluir(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM contatos WHERE con_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }

    @Override
    public boolean alterar(Contato contato) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE contatos SET con_tipo = ?, con_numero = ? WHERE con_id = ?");

            ps.setString(1, contato.getTipo());
            ps.setString(2, contato.getNumero());
            ps.setInt(3, contato.getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }

    @Override
    public List<Contato> consultar() {
        List<Contato> contatos = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from contatos");

            while (rs.next()) {
                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));
                
                contatos.add(contato);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return contatos;
    }

    @Override
    public Contato consultarPorId(int id) {
        Contato contato = new Contato();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from contatos \n"
                    + "where con_id = ?");
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }

        return contato;
    }
    
    public boolean verificarTelefoneIgual(String numero) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM contatos WHERE con_numero = ?");
            preparedStatement.setString(1, numero);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NUMEROS! ");
        }

        return resultado;
    }
}
