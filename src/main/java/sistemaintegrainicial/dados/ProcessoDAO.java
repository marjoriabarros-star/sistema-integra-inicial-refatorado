/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemaintegrainicial.modelo.Processo;
import sistemaintegrainicial.util.ConexaoBD;

/**
 *
 * @author Marjori Barros
 */
/**
 * Data Access Object para Processo
 * Responsabilidade: Operações de banco de dados relacionadas a processos
 */
public class ProcessoDAO {

    /**
     * Lista todos os processos
     */
    public List<Processo> listarTodos() {
        List<Processo> processos = new ArrayList<>();
        String sql = "SELECT p.id_processo, p.descricao, s.nome_setor " +
                     "FROM Processos p LEFT JOIN Setores s ON p.setor_id = s.id_setor";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.conectar();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Processo processo = new Processo();
                processo.setId(rs.getInt("id_processo"));
                processo.setDescricao(rs.getString("descricao"));
                processo.setNomeSetor(rs.getString("nome_setor"));
                processos.add(processo);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar processos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        
        return processos;
    }

    /**
     * Busca processo por ID
     */
    public Processo buscarPorId(int id) {
        String sql = "SELECT p.id_processo, p.descricao, s.nome_setor " +
                     "FROM Processos p LEFT JOIN Setores s ON p.setor_id = s.id_setor " +
                     "WHERE p.id_processo = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.conectar();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Processo processo = new Processo();
                processo.setId(rs.getInt("id_processo"));
                processo.setDescricao(rs.getString("descricao"));
                processo.setNomeSetor(rs.getString("nome_setor"));
                return processo;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar processo: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        
        return null;
    }

    /**
     * Insere um novo processo
     */
    public boolean inserir(Processo processo) {
        String sql = "INSERT INTO Processos (descricao, setor_id) VALUES (?, ?)";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = ConexaoBD.conectar();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, processo.getDescricao());
            stmt.setInt(2, processo.getSetorId());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir processo: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
