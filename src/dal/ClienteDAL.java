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
import model.Cliente;
import model.Contato;
import util.Conexao;

public class ClienteDAL {

    private Connection conexao;

    public ClienteDAL() {
        conexao = Conexao.getConexao();
    }

    public void adicionar(Cliente cliente) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO clientes (cli_cpf, cli_email, cli_dt_nascimento, cli_nome, cli_fk_end, cli_fk_con) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getEmail());
            ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
            ps.setString(4, cliente.getNome());
            ps.setInt(5, cliente.getIdEndereco().getCodigo());
            ps.setInt(6, cliente.getIdContato().getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM clientes WHERE cli_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Cliente cliente) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE clientes SET cli_cpf = ?, cli_email = ?, cli_dt_nascimento = ?, cli_nome = ?, cli_fk_end = ?, cli_fk_con = ? WHERE cli_id = ?");

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getEmail());
            ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
            ps.setString(4, cliente.getNome());
            ps.setInt(5, cliente.getIdEndereco().getCodigo());
            ps.setInt(6, cliente.getIdContato().getCodigo());
            ps.setInt(7, cliente.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

    public List<Cliente> consultar() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from clientes cl \n"
                    + "inner join enderecos e on cl.cli_fk_end = e.end_id \n"
                    + "inner join contatos c on cl.cli_fk_con = c.con_id ");

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setDataNascimento(rs.getDate("cli_dt_nascimento"));
                cliente.setNome(rs.getString("cli_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                cliente.setIdEndereco(endereco);

                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));

                cliente.setIdContato(contato);

                clientes.add(cliente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return clientes;
    }

    public Cliente consultaPorId(int id) {
        Cliente cliente = new Cliente();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from clientes cl \n"
                    + "inner join enderecos e on cl.cli_fk_end = e.end_id \n"
                    + "inner join contatos c on cl.cli_fk_con = c.con_id \n"
                    + "where cl.cli_id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setCodigo(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setDataNascimento(rs.getDate("cli_dt_nascimento"));
                cliente.setNome(rs.getString("cli_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                cliente.setIdEndereco(endereco);

                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));

                cliente.setIdContato(contato);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return cliente;
    }

    public Vector<Endereco> listarEnderecos() {
        Vector<Endereco> enderecos = new Vector<Endereco>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM enderecos");
            ResultSet rs = ps.executeQuery();

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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR ENDEREÇOS! ");
        }
        return enderecos;
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
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM clientes WHERE cli_cpf = ?");
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CPFs! ");
        }

        return resultado;
    }
}
