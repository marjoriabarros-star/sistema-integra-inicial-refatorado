/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemaintegrainicial.modelo.Usuario;
import sistemaintegrainicial.util.ConexaoBD;

/**
 *
 * @author Marjori Barros
 */
/**
 * Data Access Object para Usuario
 * Responsabilidade: Operações de banco de dados relacionadas a usuários
 * Princípio SOLID aplicado: Single Responsibility Principle (SRP)
 */
public class UsuarioDAO {

    /**
     * Autentica um usuário no sistema
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return Usuario autenticado ou null se falhar
     */
    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT id_usuario, nome, email, cargo FROM Usuarios WHERE email = ? AND senha = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.conectar();
            if (conexao == null) {
                return null;
            }
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCargo(rs.getString("cargo"));
                return usuario;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
        } finally {
            // Fecha recursos na ordem inversa da criação
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
     * Busca um usuário pelo ID
     */
    public Usuario buscarPorId(int id) {
        String sql = "SELECT id_usuario, nome, email, cargo FROM Usuarios WHERE id_usuario = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.conectar();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCargo(rs.getString("cargo"));
                return usuario;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
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
}
