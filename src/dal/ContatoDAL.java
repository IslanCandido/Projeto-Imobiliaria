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
import model.Pessoa;
import util.Conexao;

public class ContatoDAL {

    private Connection conexao;

    public ContatoDAL() {
        conexao = Conexao.getConexao();
    }

    public void adicionar(Contato contato) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO contatos (tipo, numero, fk_pes) VALUES (?, ?, ?)");

            ps.setString(1, contato.getTipo());
            ps.setString(2, contato.getNumero());
            ps.setInt(3, contato.getIdPessoa().getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM contatos WHERE id_con = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Contato contato) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE contatos SET tipo = ?, numero = ?, fk_pes = ? WHERE id_con = ?");

            ps.setString(1, contato.getTipo());
            ps.setString(2, contato.getNumero());
            ps.setInt(3, contato.getIdPessoa().getCodigo());
            ps.setInt(4, contato.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

    public List<Contato> consultar() {
        List<Contato> contatos = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from contatos c\n"
                    + "inner join pessoas p on c.fk_pes = p.id_pes");

            while (rs.next()) {
                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("id_con"));
                contato.setTipo(rs.getString("tipo"));
                contato.setNumero(rs.getString("numero"));

                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.getIdEndereco();

                contato.setIdPessoa(pessoa);

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
            PreparedStatement ps = conexao.prepareStatement("select * from contatos c\n"
                    + "inner join pessoas p on c.fk_pes = p.id_pes \n"
                    + "where c.id_con = ?");
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contato.setCodigo(rs.getInt("id_con"));
                contato.setTipo(rs.getString("tipo"));
                contato.setNumero(rs.getString("numero"));

                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));

                contato.setIdPessoa(pessoa);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }

        return contato;
    }
    
    public Vector<Pessoa> listarPessoas() {
        Vector<Pessoa> pessoas = new Vector<Pessoa>();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM pessoas");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.getIdEndereco();
                
                pessoas.add(pessoa);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR PESSOAS! ");
        }
        return pessoas;
    }
    
    public boolean verificarTelefoneIgual(String telefone) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM contatos WHERE numero = ?");
            preparedStatement.setString(1, telefone);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CEPs! ");
        }

        return resultado;
    }
}
