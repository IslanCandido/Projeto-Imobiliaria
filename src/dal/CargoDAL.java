package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cargo;
import util.Conexao;

public class CargoDAL implements BasicoDAL<Cargo>{
    private Connection conexao;

    public CargoDAL() {
        conexao = Conexao.getConexao();
    }
    
    @Override
    public boolean adicionar(Cargo cargo) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("INSERT INTO cargos (car_descricao, car_salario) VALUES (?, ?)");
            ps.setString(1, cargo.getDescricao());
            ps.setFloat(2, cargo.getSalario());
            
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
            PreparedStatement ps = conexao.prepareStatement
            ("DELETE FROM cargos WHERE car_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
        return result;
    }
    
    @Override
    public boolean alterar(Cargo cargo) {
        boolean result = false;
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("UPDATE cargos SET car_descricao = ?, car_salario = ? WHERE car_id = ?");
            ps.setString(1, cargo.getDescricao());
            ps.setFloat(2, cargo.getSalario());
            ps.setInt(3, cargo.getCodigo());
            
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
        return result;
    }
    
    @Override
    public List<Cargo> consultar(){
        List<Cargo> cargos = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM cargos");
            while (rs.next()){
                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("car_id"));
                cargo.setDescricao(rs.getString("car_descricao"));
                cargo.setSalario(rs.getFloat("car_salario"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return cargos;
    }
    
    @Override
    public Cargo consultarPorId(int id){
        Cargo cargo = new Cargo();
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("SELECT * FROM cargos WHERE car_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                cargo.setCodigo(rs.getInt("car_id"));
                cargo.setDescricao(rs.getString("car_descricao"));
                cargo.setSalario(rs.getFloat("car_salario"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return cargo;
    }
    
    public boolean verificarCargoIgual(String cargo) {
        boolean resultado = false;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM cargos WHERE car_descricao = ?");
            preparedStatement.setString(1, cargo);
            ResultSet rs = preparedStatement.executeQuery();            
            
            resultado = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CARGOS!");
        }

        return resultado;
    }
}
