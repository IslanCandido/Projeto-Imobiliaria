package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Endereco;
import util.Conexao;

public class EnderecoDAL {

    private Connection conexao;

    public EnderecoDAL() {
        conexao = Conexao.getConexao();
    }

    public void adicionar(Endereco endereco) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO endereços (logradouro, cep, cidade, uf, bairro) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getUf());
            ps.setString(5, endereco.getBairro());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n"+ e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM endereços WHERE id_end = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Endereco endereco) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE Endereços SET logradouro = ?, cep = ?, cidade = ?, uf = ?, bairro = ? WHERE id_end = ?");

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getUf());
            ps.setString(5, endereco.getBairro());
            ps.setInt(6, endereco.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

    public List<Endereco> consultar() {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM endereços");

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("id_end"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setBairro(rs.getString("bairro"));

                enderecos.add(endereco);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return enderecos;
    }

    public Endereco consultaPorId(int id) {
        Endereco endereco = new Endereco();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM endereços WHERE id_end = ?");
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                endereco.setCodigo(rs.getInt("id_end"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setBairro(rs.getString("bairro"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return endereco;
    }
    
    public boolean verificarCepIgual(String cep) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM endereços WHERE cep = ?");
            preparedStatement.setString(1, cep);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CEPs! ");
        }

        return resultado;
    }
}
