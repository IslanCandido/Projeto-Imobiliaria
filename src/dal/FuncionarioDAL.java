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
import model.Cargo;
import model.Funcionario;
import model.Pessoa;
import util.Conexao;

public class FuncionarioDAL {

    private Connection conexao;

    public FuncionarioDAL() {
        conexao = Conexao.getConexao();
    }

    public void adicionar(Funcionario funcionario) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionarios (pis, cat_trabalho, n_contrato, senha, fk_pes, fk_car) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, funcionario.getPis());
            ps.setString(2, funcionario.getnCarteiraTrabalho());
            ps.setString(3, funcionario.getnContrato());
            ps.setString(4, funcionario.getSenha());
            ps.setInt(5, funcionario.getIdPessoa().getCodigo());
            ps.setInt(6, funcionario.getIdCargo().getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!\n" + e);
        }
    }

    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM funcionarios WHERE id_fun = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }

    public void alterar(Funcionario funcionario) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE funcionarios SET pis = ?, cat_trabalho = ?, n_contrato = ?, senha = ?, fk_pes = ?, fk_car = ? WHERE id_fun = ?");

            ps.setString(1, funcionario.getPis());
            ps.setString(2, funcionario.getnCarteiraTrabalho());
            ps.setString(3, funcionario.getnContrato());
            ps.setString(4, funcionario.getSenha());
            ps.setInt(5, funcionario.getIdPessoa().getCodigo());
            ps.setInt(6, funcionario.getIdCargo().getCodigo());
            ps.setInt(7, funcionario.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }

    public List<Funcionario> consultar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from pessoas p \n"
                    + "inner join endereços e on p.fk_end = e.id_end");

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("id_fun"));
                funcionario.setPis(rs.getString("pis"));
                funcionario.setnCarteiraTrabalho(rs.getString("cat_trabalho"));
                funcionario.setnContrato(rs.getString("n_contrato"));
                funcionario.setSenha(rs.getString("senha"));

                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.getIdEndereco();

                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("id_car"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getFloat("salario"));

                funcionario.setIdPessoa(pessoa);
                funcionario.setIdCargo(cargo);

                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return funcionarios;
    }

    public Funcionario consultaPorId(int id) {
        Funcionario funcionario = new Funcionario();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from funcionarios f \n"
                    + "inner join pessoas p on f.fk_pes = p.id_pes \n"
                    + "inner join cargos c on f.fk_car = c.id_car \n"
                    + "where f.id_fun = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                funcionario.setCodigo(rs.getInt("id_fun"));
                funcionario.setPis(rs.getString("pis"));
                funcionario.setnCarteiraTrabalho(rs.getString("cat_trabalho"));
                funcionario.setnContrato(rs.getString("n_contrato"));
                funcionario.setSenha(rs.getString("senha"));

                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(rs.getInt("id_pes"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setDataNascimento(rs.getDate("dt_nascimento"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.getIdEndereco();

                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("id_car"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getFloat("salario"));

                funcionario.setIdPessoa(pessoa);
                funcionario.setIdCargo(cargo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return funcionario;
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
    
    public Vector<Cargo> listarCargos(){
        Vector<Cargo> cargos = new Vector<Cargo>();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM cargos");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("id_car"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getFloat("salario"));
                
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR CARGOS! ");
        }
        return cargos;
    }
}
