package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.FormaPagamento;
import model.Funcionario;
import model.Imovel;
import model.Venda;
import util.Conexao;

public class EstatisticasDAL {

    private Connection conexao;

    public EstatisticasDAL() {
        conexao = Conexao.getConexao();
    }

    public List<Venda> MostarPorData(Date dtInicio, Date dtFim) {
        List<Venda> vendas = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from vendas v\n"
                    + "inner join funcionarios f on v.ven_fk_fun = f.fun_id\n"
                    + "inner join imoveis i on v.ven_fk_imo = i.imo_id\n"
                    + "inner join categorias ct on i.imo_fk_cat = ct.cat_id \n"
                    + "inner join pagamentos p on v.ven_fk_pag = p.pag_id\n"
                    + "WHERE v.ven_dt_venda BETWEEN ? AND ?");

            ps.setDate(1, dtInicio);
            ps.setDate(2, dtFim);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));
                venda.getIdCliente();

                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("fun_id"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setEmail(rs.getString("fun_email"));
                funcionario.setDataNascimento(rs.getDate("fun_dt_nascimento"));
                funcionario.setPis(rs.getString("fun_pis"));
                funcionario.setnContrato(rs.getString("fun_n_contrato"));
                funcionario.setSenha(rs.getString("fun_senha"));
                funcionario.getIdCargo();
                funcionario.getIdContato();
                funcionario.getIdEndereco();

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
                imovel.getIdProprietario();
                imovel.getIdEndereco();

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                imovel.setIdCategoria(categoria);

                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setCodigo(rs.getInt("pag_id"));
                formaPagamento.setFormaPagamento(rs.getString("pag_forma_pagamento"));

                venda.setIdFuncionario(funcionario);
                venda.setIdFuncionario(funcionario);
                venda.setIdImovel(imovel);

                vendas.add(venda);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR VENDAS POR DATA!");
        }
        return vendas;
    }

    public List<Venda> MostrarPorCorretor(String cpf) {
        List<Venda> vendas = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from vendas v\n"
                    + "inner join funcionarios f on v.ven_fk_fun = f.fun_id\n"
                    + "inner join imoveis i on v.ven_fk_imo = i.imo_id\n"
                    + "inner join categorias ct on i.imo_fk_cat = ct.cat_id \n"
                    + "inner join pagamentos p on v.ven_fk_pag = p.pag_id\n"
                    + "where f.fun_cpf = ?");

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));
                venda.getIdCliente();

                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("fun_id"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setEmail(rs.getString("fun_email"));
                funcionario.setDataNascimento(rs.getDate("fun_dt_nascimento"));
                funcionario.setPis(rs.getString("fun_pis"));
                funcionario.setnContrato(rs.getString("fun_n_contrato"));
                funcionario.setSenha(rs.getString("fun_senha"));
                funcionario.getIdCargo();
                funcionario.getIdContato();
                funcionario.getIdEndereco();

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
                imovel.getIdProprietario();
                imovel.getIdEndereco();

                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("cat_id"));
                categoria.setNome(rs.getString("cat_nome"));

                imovel.setIdCategoria(categoria);

                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setCodigo(rs.getInt("pag_id"));
                formaPagamento.setFormaPagamento(rs.getString("pag_forma_pagamento"));

                venda.setIdFuncionario(funcionario);
                venda.setIdFuncionario(funcionario);
                venda.setIdImovel(imovel);

                vendas.add(venda);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR VENDAS POR CORRETOR!");
        }
        return vendas;
    }

    public int numeroDeVendas() {
        int result = 0;
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from vendas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result++;
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO PEGAR NUMERO DE VENDAS!");
        }
        return result;
    }
}
