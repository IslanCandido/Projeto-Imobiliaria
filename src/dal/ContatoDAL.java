package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.Contato;
import model.Cliente;
import util.Conexao;

public class ContatoDAL {

    private Connection conexao;

    public ContatoDAL() {
        conexao = Conexao.getConexao();
    }

    public void adicionar(Contato contato) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO contatos (con_tipo, con_numero) VALUES (?, ?)");

            ps.setString(1, contato.getTipo());
            ps.setString(2, contato.getNumero());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM contatos WHERE con_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Contato contato) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE contatos SET con_tipo = ?, con_numero = ? WHERE con_id = ?");

            ps.setString(1, contato.getTipo());
            ps.setString(2, contato.getNumero());
            ps.setInt(3, contato.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

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

    public Contato consultaPorId(int id) {
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
