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
import model.Categoria;
import model.Endereco;
import model.Imovel;
import model.Proprietario;
import util.Conexao;

public class ImovelDAL implements BasicoDAL<Imovel>{

    private Connection conexao;

    public ImovelDAL() {
        conexao = Conexao.getConexao();
    }

    @Override
    public boolean adicionar(Imovel imovel) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO imoveis (imo_preco, imo_dt_inscriçao, imo_metros, imo_qntd_quartos, imo_qntd_suites, imo_situaçao, imo_descriçao, imo_tipo, imo_dt_baixa,"
                    + " imo_motivo_baixa, imo_fk_pro, imo_fk_cat, imo_fk_end) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setDouble(1, imovel.getPreco());
            ps.setDate(2, new java.sql.Date(imovel.getDtInscricao().getTime()));
            ps.setFloat(3, imovel.getMetros());
            ps.setInt(4, imovel.getnQuartos());
            ps.setInt(5, imovel.getnSuites());
            ps.setString(6, imovel.getSituacao());
            ps.setString(7, imovel.getDescricao());
            ps.setString(8, imovel.getTipo());
            ps.setDate(9, new java.sql.Date(imovel.getDtBaixa().getTime()));
            ps.setString(10, imovel.getMotivo());
            ps.setInt(11, imovel.getIdProprietario().getCodigo());
            ps.setInt(12, imovel.getIdCategoria().getCodigo());
            ps.setInt(13, imovel.getIdEndereco().getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS! " + e);
        }
        return result;
    }

    @Override
    public boolean excluir(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM imoveis WHERE imo_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }

    @Override
    public boolean alterar(Imovel imovel) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE imoveis SET imo_preco = ?, imo_dt_inscriçao = ?, imo_metros = ?, imo_qntd_quartos = ?, imo_qntd_suites = ?,"
                    + " imo_situaçao = ?, imo_descriçao = ?, imo_tipo = ?, imo_dt_baixa = ?, imo_motivo_baixa = ?, imo_fk_pro = ?, imo_fk_cat = ?, imo_fk_end = ? WHERE imo_id = ?");

            ps.setDouble(1, imovel.getPreco());
            ps.setDate(2, new java.sql.Date(imovel.getDtInscricao().getTime()));
            ps.setFloat(3, imovel.getMetros());
            ps.setInt(4, imovel.getnQuartos());
            ps.setInt(5, imovel.getnSuites());
            ps.setString(6, imovel.getSituacao());
            ps.setString(7, imovel.getDescricao());
            ps.setString(8, imovel.getTipo());
            ps.setDate(9, new java.sql.Date(imovel.getDtBaixa().getTime()));
            ps.setString(10, imovel.getMotivo());
            ps.setInt(11, imovel.getIdProprietario().getCodigo());
            ps.setInt(12, imovel.getIdCategoria().getCodigo());
            ps.setInt(13, imovel.getIdEndereco().getCodigo());
            ps.setInt(14, imovel.getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }

    @Override
    public List<Imovel> consultar() {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id ");

            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setCodigo(rs.getInt("imo_id"));
                imovel.setPreco(rs.getDouble("imo_preco"));
                imovel.setDtInscricao(rs.getDate("imo_dt_inscriçao"));
                imovel.setMetros(rs.getFloat("imo_metros"));
                imovel.setnQuartos(rs.getInt("imo_qntd_quartos"));
                imovel.setnSuites(rs.getInt("imo_qntd_suites"));
                imovel.setSituacao(rs.getString("imo_situaçao"));
                imovel.setDescricao(rs.getString("imo_descriçao"));
                imovel.setTipo(rs.getString("imo_tipo"));
                imovel.setDtBaixa(rs.getDate("imo_dt_baixa"));
                imovel.setMotivo(rs.getString("imo_motivo_baixa"));

                Proprietario prop = new Proprietario();
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));
                proprietario.getIdContato();

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);

                imoveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return imoveis;
    }

    public List<Imovel> consultarDisponiveis() {
        List<Imovel> imoveisDisponiveis = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_situaçao like'%Disponivel%'");

            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setCodigo(rs.getInt("imo_id"));
                imovel.setPreco(rs.getDouble("imo_preco"));
                imovel.setDtInscricao(rs.getDate("imo_dt_inscriçao"));
                imovel.setMetros(rs.getFloat("imo_metros"));
                imovel.setnQuartos(rs.getInt("imo_qntd_quartos"));
                imovel.setnSuites(rs.getInt("imo_qntd_suites"));
                imovel.setSituacao(rs.getString("imo_situaçao"));
                imovel.setDescricao(rs.getString("imo_descriçao"));
                imovel.setTipo(rs.getString("imo_tipo"));
                imovel.setDtBaixa(rs.getDate("imo_dt_baixa"));
                imovel.setMotivo(rs.getString("imo_motivo_baixa"));

                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));
                proprietario.getIdContato();

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);

                imoveisDisponiveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS IMOVEIS DISPONIVEIS!");
        }
        return imoveisDisponiveis;
    }
    
    public List<Imovel> consultarIndisponiveis() {
        List<Imovel> imoveisIndisponiveis = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_situaçao like'%Indisponivel%'");

            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setCodigo(rs.getInt("imo_id"));
                imovel.setPreco(rs.getDouble("imo_preco"));
                imovel.setDtInscricao(rs.getDate("imo_dt_inscriçao"));
                imovel.setMetros(rs.getFloat("imo_metros"));
                imovel.setnQuartos(rs.getInt("imo_qntd_quartos"));
                imovel.setnSuites(rs.getInt("imo_qntd_suites"));
                imovel.setSituacao(rs.getString("imo_situaçao"));
                imovel.setDescricao(rs.getString("imo_descriçao"));
                imovel.setTipo(rs.getString("imo_tipo"));
                imovel.setDtBaixa(rs.getDate("imo_dt_baixa"));
                imovel.setMotivo(rs.getString("imo_motivo_baixa"));

                Proprietario prop = new Proprietario();
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));
                proprietario.getIdContato();

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);

                imoveisIndisponiveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS IMOVEIS INDISPONIVEIS!");
        }
        return imoveisIndisponiveis;
    }

    @Override
    public Imovel consultarPorId(int id) {
        Imovel imovel = new Imovel();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                imovel.setCodigo(rs.getInt("imo_id"));
                imovel.setPreco(rs.getDouble("imo_preco"));
                imovel.setDtInscricao(rs.getDate("imo_dt_inscriçao"));
                imovel.setMetros(rs.getFloat("imo_metros"));
                imovel.setnQuartos(rs.getInt("imo_qntd_quartos"));
                imovel.setnSuites(rs.getInt("imo_qntd_suites"));
                imovel.setSituacao(rs.getString("imo_situaçao"));
                imovel.setDescricao(rs.getString("imo_descriçao"));
                imovel.setTipo(rs.getString("imo_tipo"));
                imovel.setDtBaixa(rs.getDate("imo_dt_baixa"));
                imovel.setMotivo(rs.getString("imo_motivo_baixa"));

                Proprietario prop = new Proprietario();
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));
                proprietario.getIdContato();

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return imovel;
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

    public Vector<Categoria> listarCategorias() {
        Vector<Categoria> categorias = new Vector<Categoria>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM categorias");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR CATEGORIAS!");
        }
        return categorias;
    }

    public Vector<Proprietario> listarProprietarios() {
        Vector<Proprietario> proprietarios = new Vector<Proprietario>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM proprietarios");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(rs.getInt("pro_id"));
                proprietario.setCpf(rs.getString("pro_cpf"));
                proprietario.setEmail(rs.getString("pro_email"));
                proprietario.setDataNascimento(rs.getDate("pro_dt_nascimento"));
                proprietario.setNome(rs.getString("pro_nome"));
                proprietario.getIdContato();

                proprietarios.add(proprietario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR PROPRIETÁRIOS!");
        }
        return proprietarios;
    }
}
