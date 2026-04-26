/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Marjori Barros
 */
/**
 * Classe responsável pela conexão com o banco de dados
 * Responsabilidade Única: Gerenciar conexões com o banco
 * Princípio SOLID aplicado: Single Responsibility Principle (SRP)
 */
public class ConexaoBD {
    
    // Configurações do banco
    private static final String URL = "jdbc:mysql://localhost:3306/integra_inicial";
    private static final String USUARIO = "root";
    private static final String SENHA = "l5PV3IYH@";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Estabelece conexão com o banco de dados
     * @return Connection ou null se falhar
     */
    public static Connection conectar() {
        try {
            // Carrega o driver JDBC
            Class.forName(DRIVER);
            
            // Estabelece a conexão
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
            return conexao;
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "Driver JDBC não encontrado: " + e.getMessage(),
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao conectar ao banco: " + e.getMessage(),
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Fecha a conexão com o banco
     * @param conexao Conexão a ser fechada
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
