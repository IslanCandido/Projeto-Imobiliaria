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
import model.Proprietario;
import util.Conexao;

public class ProprietarioDAL implements BasicoDAL<Proprietario>{

    private Connection conexao;

    public ProprietarioDAL() {
        conexao = Conexao.getConexao();
    }

    @Override
    public boolean adicionar(Proprietario proprietario) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO proprietarios (pro_cpf, pro_email, pro_dt_nascimento, pro_nome, pro_fk_con) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, proprietario.getCpf());
            ps.setString(2, proprietario.getEmail());
            ps.setDate(3, new java.sql.Date(proprietario.getDataNascimento().getTime()));
            ps.setString(4, proprietario.getNome());
            ps.setInt(5, proprietario.getIdContato().getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
        return result;
    }

    @Override
    public boolean excluir(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM proprietarios WHERE pro_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }

    @Override
    public boolean alterar(Proprietario proprietario) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE proprietarios SET pro_cpf = ?, pro_email = ?, pro_dt_nascimento = ?, pro_nome = ?, pro_fk_con = ? WHERE pro_id = ?");

            ps.setString(1, proprietario.getCpf());
            ps.setString(2, proprietario.getEmail());
            ps.setDate(3, new java.sql.Date(proprietario.getDataNascimento().getTime()));
            ps.setString(4, proprietario.getNome());
            ps.setInt(5, proprietario.getIdContato().getCodigo());
            ps.setInt(6, proprietario.getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }

    @Override
    public List<Proprietario> consultar() {
        List<Proprietario> proprietarios = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from proprietarios p\n"
                    + "inner join contatos c on p.pro_fk_con = c.con_id ");

            while (rs.next()) {
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));

                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));

                proprietario.setIdContato(contato);

                proprietarios.add(proprietario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return proprietarios;
    }

    @Override
    public Proprietario consultarPorId(int id) {
        Proprietario proprietario = new Proprietario();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from proprietarios p\n"
                    + "inner join contatos c on p.pro_fk_con = c.con_id \n"
                    + "where p.pro_id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));

                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));

                proprietario.setIdContato(contato);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return proprietario;
    }
    
    public Vector<Contato> listarContatos() {
        Vector<Contato> contatos = new Vector<Contato>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM contatos");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));

                contatos.add(contato);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR CONTATOS!");
        }
        return contatos;
    }

    public boolean verificarCpfIgual(String cpf) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM proprietarios WHERE pro_cpf = ?");
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CPFs! ");
        }

        return resultado;
    }
}
