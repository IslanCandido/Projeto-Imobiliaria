package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.Cargo;
import model.Categoria;
import model.Cliente;
import model.Contato;
import model.Endereco;
import model.FormaPagamento;
import model.Funcionario;
import model.Imovel;
import model.Proprietario;
import model.Venda;
import util.Conexao;

public class VendaDAL implements BasicoDAL<Venda> {

    private Connection conexao;

    public VendaDAL() {
        conexao = Conexao.getConexao();
    }

    @Override
    public boolean adicionar(Venda venda) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO vendas (ven_valor_venda, ven_percentual_comissao, ven_dt_venda, ven_fk_cli, ven_fk_fun, ven_fk_imo, ven_fk_pag, ven_meses_pagos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setDouble(1, venda.getValor());
            ps.setInt(2, venda.getPercentualComissao());
            ps.setDate(3, new java.sql.Date(venda.getDataVenda().getTime()));
            ps.setInt(4, venda.getIdCliente().getCodigo());
            ps.setInt(5, venda.getIdFuncionario().getCodigo());
            ps.setInt(6, venda.getIdImovel().getCodigo());
            ps.setInt(7, venda.getIdFormaPagamento().getCodigo());
            ps.setInt(8, venda.getMesesPagos());
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
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM vendas WHERE ven_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }

    @Override
    public boolean alterar(Venda venda) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE vendas SET ven_valor_venda = ?, ven_percentual_comissao = ?, ven_dt_venda = ?, ven_fk_cli = ?, ven_fk_fun = ?, ven_fk_imo = ?, ven_fk_pag = ?, ven_meses_pagos = ? WHERE ven_id = ?");
            ps.setDouble(1, venda.getValor());
            ps.setInt(2, venda.getPercentualComissao());
            ps.setDate(3, new java.sql.Date(venda.getDataVenda().getTime()));
            ps.setInt(4, venda.getIdCliente().getCodigo());
            ps.setInt(5, venda.getIdFuncionario().getCodigo());
            ps.setInt(6, venda.getIdImovel().getCodigo());
            ps.setInt(7, venda.getIdFormaPagamento().getCodigo());
            ps.setInt(8, venda.getMesesPagos());
            ps.setInt(9, venda.getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }

    @Override
    public List<Venda> consultar() {
        List<Venda> vendas = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from vendas v\n"
                    + "inner join clientes c on v.ven_fk_cli = c.cli_id \n"
                    + "inner join funcionarios f on v.ven_fk_fun = f.fun_id \n"
                    + "inner join imoveis i on v.ven_fk_imo = i.imo_id\n"
                    + "inner join pagamentos p on v.ven_fk_pag = p.pag_id");

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));

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

                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setDataNascimento(rs.getDate("cli_dt_nascimento"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.getIdContato();
                cliente.getIdEndereco();

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
                imovel.getIdCategoria();
                imovel.getIdEndereco();

                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setCodigo(rs.getInt("pag_id"));
                formaPagamento.setFormaPagamento(rs.getString("pag_forma_pagamento"));

                venda.setIdCliente(cliente);
                venda.setIdFuncionario(funcionario);
                venda.setIdImovel(imovel);
                venda.setIdFormaPagamento(formaPagamento);

                vendas.add(venda);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return vendas;
    }

    @Override
    public Venda consultarPorId(int id) {
        Venda venda = new Venda();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from vendas v\n"
                    + "inner join clientes c on v.ven_fk_cli = c.cli_id \n"
                    + "inner join funcionarios f on v.ven_fk_fun = f.fun_id \n"
                    + "inner join imoveis i on v.ven_fk_imo = i.imo_id\n"
                    + "inner join pagamentos p on v.ven_fk_pag = p.pag_id \n"
                    + "where v.ven_id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venda.setCodigo(rs.getInt("ven_id"));
                venda.setValor(rs.getDouble("ven_valor_venda"));
                venda.setDataVenda(rs.getDate("ven_dt_venda"));
                venda.setPercentualComissao(rs.getInt("ven_percentual_comissao"));
                venda.setMesesPagos(rs.getInt("ven_meses_pagos"));

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

                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setDataNascimento(rs.getDate("cli_dt_nascimento"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.getIdContato();
                cliente.getIdEndereco();

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
                imovel.getIdCategoria();
                imovel.getIdEndereco();

                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setCodigo(rs.getInt("pag_id"));
                formaPagamento.setFormaPagamento(rs.getString("pag_forma_pagamento"));

                venda.setIdCliente(cliente);
                venda.setIdFuncionario(funcionario);
                venda.setIdImovel(imovel);
                venda.setIdFormaPagamento(formaPagamento);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return venda;
    }

    public Vector<Cliente> listarClientes() {
        Vector<Cliente> clientes = new Vector<Cliente>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setDataNascimento(rs.getDate("cli_dt_nascimento"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.getIdContato();
                cliente.getIdEndereco();

                clientes.add(cliente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR CLIENTES!");
        }
        return clientes;
    }

    public Vector<Funcionario> listarFuncionarios() {
        Vector<Funcionario> funcionarios = new Vector<Funcionario>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("select * from funcionarios f\n"
                    + "inner join enderecos e on f.fun_fk_end = e.end_id \n"
                    + "inner join contatos c on f.fun_fk_con = c.con_id \n"
                    + "inner join cargos cr on f.fun_fk_car = cr.car_id "
                    + "where cr.car_descricao like'Corretor'");

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("fun_id"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setEmail(rs.getString("fun_email"));
                funcionario.setDataNascimento(rs.getDate("fun_dt_nascimento"));
                funcionario.setPis(rs.getString("fun_pis"));
                funcionario.setnContrato(rs.getString("fun_n_contrato"));
                funcionario.setSenha(rs.getString("fun_senha"));

                Endereco endereco = new Endereco();
                endereco.setCodigo(rs.getInt("end_id"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setUf(rs.getString("end_uf"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));

                Contato contato = new Contato();
                contato.setCodigo(rs.getInt("con_id"));
                contato.setTipo(rs.getString("con_tipo"));
                contato.setNumero(rs.getString("con_numero"));

                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("car_id"));
                cargo.setDescricao(rs.getString("car_descricao"));
                cargo.setSalario(rs.getFloat("car_salario"));

                funcionario.setIdEndereco(endereco);
                funcionario.setIdContato(contato);
                funcionario.setIdCargo(cargo);

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR FUNCIONÁRIOS! "+e);
        }
        return funcionarios;
    }

    public Vector<Imovel> listarImoveisVender() {
        Vector<Imovel> imoveisVender = new Vector<Imovel>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_situaçao like'%Disponivel%' and  i.imo_tipo like'%Vender%'");
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

                imoveisVender.add(imovel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMOVEIS! ");
        }
        return imoveisVender;
    }

    public Vector<Imovel> listarImoveisAlugar() {
        Vector<Imovel> imoveisAlugar = new Vector<Imovel>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_situaçao like'%Disponivel%' and  i.imo_tipo like'%Alugar%'");
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

                imoveisAlugar.add(imovel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMOVEIS! ");
        }
        return imoveisAlugar;
    }
    
    public Vector<Imovel> listarImoveis() {
        Vector<Imovel> imoveisAlugar = new Vector<Imovel>();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_situaçao like'%Disponivel%'");
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

                imoveisAlugar.add(imovel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR IMOVEIS! ");
        }
        return imoveisAlugar;
    }

    public Vector<FormaPagamento> listarFormasdePagamento() {
        Vector<FormaPagamento> pagamentos = new Vector<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM pagamentos");
            while (rs.next()) {
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setCodigo(rs.getInt("pag_id"));
                formaPagamento.setFormaPagamento(rs.getString("pag_forma_pagamento"));
                pagamentos.add(formaPagamento);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return pagamentos;
    }

    public double pegarPreco(int id) {
        double result = 0;
        try {
            PreparedStatement ps = conexao.prepareStatement("select i.imo_preco \n"
                    + "from imoveis i\n"
                    + "inner join proprietarios p on i.imo_fk_pro = p.pro_id \n"
                    + "inner join categorias c on i.imo_fk_cat = c.cat_id \n"
                    + "inner join enderecos e on i.imo_fk_end = e.end_id \n"
                    + "where i.imo_id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();
            result = rs.getDouble("imo_preco");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO PEGAR PREÇO DO IMOVEL NO BANCO!\n" + e);
        }

        return result;
    }

    public boolean deixarImovelIndisponivel(int id, Date data) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("update imoveis set imo_situaçao = 'Indisponivel', imo_dt_baixa = ?, imo_motivo_baixa = 'vendido'"
                    + "where imo_id = ?");
            ps.setDate(1, new java.sql.Date(data.getTime()));
            ps.setInt(2, id);

            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR DISPONIBILIDADE DO IMOVEL NO BANCO! \n" + e);
        }
        return result;
    }

    public boolean deixarImovelDisponivel(int id, Date data) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("update imoveis set imo_situaçao = 'Disponivel', imo_dt_baixa = ?, imo_motivo_baixa = ' ' "
                    + "where imo_id = ?");
            ps.setDate(1, data);
            ps.setInt(2, id);

            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR DISPONIBILIDADE DO IMOVEL NO BANCO!");
        }
        return result;
    }
}
