package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.FormaPagamento;
import util.Conexao;

public class FormaPagamentoDAL implements BasicoDAL<FormaPagamento> {

    private Connection conexao;

    public FormaPagamentoDAL() {
        conexao = Conexao.getConexao();
    }

    @Override
    public boolean adicionar(FormaPagamento formaPagamento) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO pagamentos (pag_forma_pagamento) VALUES (?)");
            ps.setString(1, formaPagamento.getFormaPagamento());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!");
        }
        return result;
    }

    @Override
    public boolean excluir(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM pagamentos WHERE pag_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }

    @Override
    public boolean alterar(FormaPagamento formaPagamento) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE pagamentos SET pag_forma_pagamento = ? WHERE pag_id = ?");
            ps.setString(1, formaPagamento.getFormaPagamento());
            ps.setInt(2, formaPagamento.getCodigo());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }

    @Override
    public List<FormaPagamento> consultar() {
        List<FormaPagamento> pagamentos = new ArrayList<>();
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

    @Override
    public FormaPagamento consultarPorId(int id) {
        FormaPagamento formaPagamento = new FormaPagamento();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM pagamentos WHERE pag_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                formaPagamento.setCodigo(rs.getInt("pag_id"));
                formaPagamento.setFormaPagamento(rs.getString("pag_forma_pagamento"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return formaPagamento;
    }

    public boolean verificarFormaPagamentoIgual(String formaPagamento) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM pagamentos WHERE pag_forma_pagamento = ?");
            preparedStatement.setString(1, formaPagamento);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR FORMAS DE PAGAMENTO!");
        }

        return resultado;
    }
}
