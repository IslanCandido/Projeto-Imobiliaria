package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cargo;
import util.Conexao;

public class CargoDAL {
    private Connection conexao;

    public CargoDAL() {
        conexao = Conexao.getConexao();
    }
    
    public void adicionar(Cargo cargo) {
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("INSERT INTO Cargos (nome, salario) VALUES (?, ?)");
            ps.setString(1, cargo.getNome());
            ps.setFloat(2, cargo.getSalario());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR DADOS!");
        }
    }
    
    public void excluir(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("DELETE FROM Cargos WHERE id_car = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER DADOS!");
        }
    }
    
    public void alterar(Cargo cargo) {
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("UPDATE Cargos SET nome = ?, salario = ? WHERE id_car = ?");
            ps.setString(1, cargo.getNome());
            ps.setFloat(2, cargo.getSalario());
            ps.setInt(3, cargo.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EDITAR DADOS!");
        }
    }
    
    public List<Cargo> consultar(){
        List<Cargo> cargos = new ArrayList<>();
        try {
            Statement ps = conexao.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM Cargos");
            while (rs.next()){
                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("id_car"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getFloat("salario"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR TODOS OS DADOS!");
        }
        return cargos;
    }
    
    public Cargo consultarPorId(int id){
        Cargo cargo = new Cargo();
        try {
            PreparedStatement ps = conexao.prepareStatement
            ("SELECT * FROM Cargos WHERE id_car = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                cargo.setCodigo(rs.getInt("id_car"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getFloat("salario"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR DADOS!");
        }
        return cargo;
    }
}
