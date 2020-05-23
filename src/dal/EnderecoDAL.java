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
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO enderecos (end_logradouro, end_cep, end_cidade, end_uf, end_bairro, end_complemento) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getUf());
            ps.setString(5, endereco.getBairro());
            ps.setString(6, endereco.getComplemento());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n"+ e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM enderecos WHERE end_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Endereco endereco) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE enderecos SET end_logradouro = ?, end_cep = ?, end_cidade = ?, end_uf = ?, end_bairro = ?, end_complemento = ? WHERE end_id = ?");

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getUf());
            ps.setString(5, endereco.getBairro());
            ps.setString(6, endereco.getComplemento());
            ps.setInt(7, endereco.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

    public List<Endereco> consultar() {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM enderecos");

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

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
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM enderecos WHERE end_id = ?");
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return endereco;
    }
    
    public boolean verificarCepIgual(String cep) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM enderecos WHERE end_cep = ?");
            preparedStatement.setString(1, cep);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CEPs! ");
        }

        return resultado;
    }
}
