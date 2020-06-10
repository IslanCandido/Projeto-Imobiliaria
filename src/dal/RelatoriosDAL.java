package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Cliente;
import model.Endereco;
import model.FormaPagamento;
import model.Funcionario;
import model.Imovel;
import model.Proprietario;
import model.Venda;
import util.Conexao;

public class RelatoriosDAL {

    private Connection conexao;

    public RelatoriosDAL() {
        conexao = Conexao.getConexao();
    }

    public List<Imovel> mostrarImoveisPorCidade(String cidade) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "where e.end_cidade = ?");

            ps.setString(1, cidade);
            ResultSet rs = ps.executeQuery();

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
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR CIDADE!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorDataInscricao(Date dataInscricao) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "WHERE i.imo_dt_inscriçao BETWEEN ? AND ?");

            ps.setDate(1, dataInscricao);
            ps.setDate(2, dataInscricao);
            ResultSet rs = ps.executeQuery();

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
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR DATA DE INSCRIÇÃO!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorCategoria(String categoria) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "where c.cat_nome = ?");

            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();

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

                Categoria Objcategoria = new Categoria();
                Objcategoria.setCodigo(rs.getInt("cat_id"));
                Objcategoria.setNome(rs.getString("cat_nome"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(Objcategoria);
                imovel.setIdEndereco(endereco);

                imoveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR CATEGORIA!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorCorretor(String cpfCorretor) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "inner join vendas v on v.ven_fk_imo = i.imo_id\n"
                    + "inner join funcionarios f on v.ven_fk_fun = f.fun_id\n"
                    + "where f.fun_cpf = ?");

            ps.setString(1, cpfCorretor);
            ResultSet rs = ps.executeQuery();

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

                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));
                venda.getIdCliente();
                venda.getIdImovel();
                venda.getIdFormaPagamento();
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("fun_id"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setEmail(rs.getString("fun_email"));
                funcionario.setDataNascimento(rs.getDate("fun_dt_nascimento"));
                funcionario.setPis(rs.getString("fun_pis"));
                funcionario.setnContrato(rs.getString("fun_n_contrato"));
                funcionario.setSenha(rs.getString("fun_senha"));
                funcionario.getIdEndereco();
                funcionario.getIdContato();
                funcionario.getIdCargo();
                
                venda.setIdFuncionario(funcionario);
                
                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);

                imoveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR CORRETOR!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorSituacao(String situacao) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "where i.imo_situaçao = ?");

            ps.setString(1, situacao);
            ResultSet rs = ps.executeQuery();

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
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR SITUAÇÃO!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorMotivoBaixa(String motivo) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "where i.imo_motivo_baixa = ?");

            ps.setString(1, motivo);
            ResultSet rs = ps.executeQuery();

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
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR MOTIVO DE BAIXA!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorCliente(String cpfCliente) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id\n"
                    + "inner join vendas v on v.ven_fk_imo = i.imo_id\n"
                    + "inner join clientes cl on v.ven_fk_cli = cl.cli_id\n"
                    + "where cl.cli_cpf = ?");

            ps.setString(1, cpfCliente);
            ResultSet rs = ps.executeQuery();

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

                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));
                venda.getIdFuncionario();
                venda.getIdImovel();
                venda.getIdFormaPagamento();

                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setDataNascimento(rs.getDate("cli_dt_nascimento"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.getIdContato();
                cliente.getIdEndereco();

                venda.setIdCliente(cliente);

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);

                imoveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR CLIENTE! " + e);
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisPorDataVenda(Date dataVenda) {
        List<Imovel> imoveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id\n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id\n"
                    + "inner join vendas v on v.ven_fk_imo = i.imo_id\n"
                    + "WHERE v.ven_dt_venda BETWEEN ? AND ?");

            ps.setDate(1, dataVenda);
            ps.setDate(2, dataVenda);
            ResultSet rs = ps.executeQuery();

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

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                imovel.setIdProprietario(proprietario);
                imovel.setIdCategoria(categoria);
                imovel.setIdEndereco(endereco);

                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));
                venda.getIdCliente();
                venda.getIdFuncionario();
                venda.getIdImovel();
                venda.getIdFormaPagamento();

                imoveis.add(imovel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMÓVEIS POR DATA DE VENDA!");
        }
        return imoveis;
    }

    public List<Imovel> mostrarImoveisCadastrados() {
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
}
