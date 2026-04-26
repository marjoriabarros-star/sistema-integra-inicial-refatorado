/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.servicos.testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sistemaintegrainicial.servicos.AutenticacaoService;

/**
 * Testes unitários para AutenticacaoService
 * 
 * @author Marjori Barros
 */
public class AutenticacaoServiceTest {
    
    private AutenticacaoService service;
    
    @BeforeEach
    public void setUp() {
        // Executado antes de cada teste
        service = new AutenticacaoService();
    }
    
    @Test
    @DisplayName("Deve rejeitar autenticação com email vazio")
    public void testAutenticar_EmailVazio_DeveRetornarErro() {
        // Arrange (preparar)
        String email = "";
        String senha = "senha123";
        
        // Act (executar)
        String resultado = service.autenticar(email, senha);
        
        // Assert (validar)
        assertTrue(resultado.startsWith("ERRO"), 
            "Deve retornar mensagem de erro para email vazio");
        assertTrue(resultado.contains("não pode ser vazio"),
            "Mensagem deve indicar que email é obrigatório");
    }
    
    @Test
    @DisplayName("Deve rejeitar autenticação com senha vazia")
    public void testAutenticar_SenhaVazia_DeveRetornarErro() {
        // Arrange
        String email = "teste@email.com";
        String senha = "";
        
        // Act
        String resultado = service.autenticar(email, senha);
        
        // Assert
        assertTrue(resultado.startsWith("ERRO"),
            "Deve retornar mensagem de erro para senha vazia");
        assertTrue(resultado.contains("não pode ser vazia"),
            "Mensagem deve indicar que senha é obrigatória");
    }
    
    @Test
    @DisplayName("Deve rejeitar autenticação com email nulo")
    public void testAutenticar_EmailNulo_DeveRetornarErro() {
        // Arrange
        String email = null;
        String senha = "senha123";
        
        // Act
        String resultado = service.autenticar(email, senha);
        
        // Assert
        assertTrue(resultado.startsWith("ERRO"),
            "Deve retornar erro para email nulo");
    }
    
    @Test
    @DisplayName("Administrador deve ter acesso a todas as funcionalidades")
    public void testTemPermissao_Administrador_DeveTerAcessoTotal() {
        // Arrange
        String cargo = "Administrador";
        
        // Act & Assert
        assertTrue(service.temPermissao(cargo, "USUARIOS"),
            "Administrador deve acessar USUARIOS");
        assertTrue(service.temPermissao(cargo, "PROCESSOS"),
            "Administrador deve acessar PROCESSOS");
        assertTrue(service.temPermissao(cargo, "MANUAIS"),
            "Administrador deve acessar MANUAIS");
    }
    
    @Test
    @DisplayName("Gerência não deve ter acesso a gestão de usuários")
    public void testTemPermissao_Gerencia_NaoDeveAcessarUsuarios() {
        // Arrange
        String cargo = "Gerência";
        
        // Act & Assert
        assertFalse(service.temPermissao(cargo, "USUARIOS"),
            "Gerência NÃO deve acessar USUARIOS");
        assertTrue(service.temPermissao(cargo, "PROCESSOS"),
            "Gerência deve acessar PROCESSOS");
        assertTrue(service.temPermissao(cargo, "MANUAIS"),
            "Gerência deve acessar MANUAIS");
    }
    
    @Test
    @DisplayName("Analistas devem ter acesso a processos e manuais")
    public void testTemPermissao_Analistas_DeveAcessarProcessosEManuais() {
        // Arrange
        String cargo = "Analistas";
        
        // Act & Assert
        assertTrue(service.temPermissao(cargo, "PROCESSOS"),
            "Analistas devem acessar PROCESSOS");
        assertTrue(service.temPermissao(cargo, "MANUAIS"),
            "Analistas devem acessar MANUAIS");
        assertFalse(service.temPermissao(cargo, "USUARIOS"),
            "Analistas NÃO devem acessar USUARIOS");
    }
    
    @Test
    @DisplayName("Visitante não deve ter acesso a nenhuma funcionalidade")
    public void testTemPermissao_Visitante_NaoDeveTerAcesso() {
        // Arrange
        String cargo = "Visitante";
        
        // Act & Assert
        assertFalse(service.temPermissao(cargo, "USUARIOS"));
        assertFalse(service.temPermissao(cargo, "PROCESSOS"));
        assertFalse(service.temPermissao(cargo, "MANUAIS"));
    }
    
    @Test
    @DisplayName("Cargo nulo não deve ter permissão")
    public void testTemPermissao_CargoNulo_DeveRetornarFalso() {
        // Arrange & Act & Assert
        assertFalse(service.temPermissao(null, "PROCESSOS"),
            "Cargo nulo não deve ter permissões");
    }
}