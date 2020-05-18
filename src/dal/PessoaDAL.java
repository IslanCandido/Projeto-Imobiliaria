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
import model.Endereco;
import model.Pessoa;
import util.Conexao;

public class PessoaDAL {

    private Connection conexao;

    public PessoaDAL() {
        conexao = Conexao.getConexao();
    }

    public void adicionar(Pessoa pessoa) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO pessoas (cpf, email, dt_nascimento, nome, fk_end) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getEmail());
            ps.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            ps.setString(4, pessoa.getNome());
            ps.setInt(5, pessoa.getIdEndereco().getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM pessoas WHERE id_pes = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Pessoa pessoa) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE pessoas SET cpf = ?, email = ?, dt_nascimento = ?, nome = ?, fk_end = ? WHERE id_pes = ?");

            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getEmail());
            ps.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            ps.setString(4, pessoa.getNome());
            ps.setInt(5, pessoa.getIdEndereco().getCodigo());
            ps.setInt(6, pessoa.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

    public List<Pessoa> consultar() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from pessoas p \n"
                    + "inner join endereços e on p.fk_end = e.id_end");

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("id_end"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setBairro(rs.getString("bairro"));

                pessoa.setIdEndereco(endereco);

                pessoas.add(pessoa);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return pessoas;
    }

    public Pessoa consultaPorId(int id) {
        Pessoa pessoa = new Pessoa();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from pessoas p \n"
                    + "inner join endereços e on p.fk_end = e.id_end\n"
                    + "where p.id_pes = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("id_end"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setBairro(rs.getString("bairro"));

                pessoa.setIdEndereco(endereco);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return pessoa;
    }
    
    public Vector<Endereco> listarEnderecos() {
        Vector<Endereco> enderecos = new Vector<Endereco>();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM endereços");
            ResultSet rs = preparedStatement.executeQuery();

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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR ENDEREÇOS! ");
        }
        return enderecos;
    }
    
    public boolean verificarCpfIgual(String cpf) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM pessoas WHERE cpf = ?");
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CPFs! ");
        }

        return resultado;
    }
}
