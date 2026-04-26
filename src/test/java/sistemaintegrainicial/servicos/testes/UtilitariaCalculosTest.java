/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.servicos.testes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sistemaintegrainicial.util.UtilitariaCalculos;

/**
 *
 * @author Marjori Barros
 */
/**
 * Testes unitários para UtilitariaCalculos
 * Testa cálculos e validações puras, sem dependência externa
 */
public class UtilitariaCalculosTest {

    @Test
    @DisplayName("Processo com palavra 'licença' deve ter prazo de 30 dias")
    public void testCalcularPrazoEstimado_Licenca_DeveRetornar30Dias() {
        // Arrange
        String descricao = "Solicitação de Licença Ambiental";
        
        // Act
        int prazo = UtilitariaCalculos.calcularPrazoEstimado(descricao);
        
        // Assert
        assertEquals(30, prazo, "Processos com licença devem ter 30 dias de prazo");
    }

    @Test
    @DisplayName("Processo com palavra 'relatório' deve ter prazo de 15 dias")
    public void testCalcularPrazoEstimado_Relatorio_DeveRetornar15Dias() {
        // Arrange
        String descricao = "Elaboração de Relatório Mensal";
        
        // Act
        int prazo = UtilitariaCalculos.calcularPrazoEstimado(descricao);
        
        // Assert
        assertEquals(15, prazo, "Processos com relatório devem ter 15 dias de prazo");
    }

    @Test
    @DisplayName("Processo simples deve ter prazo padrão de 5 dias")
    public void testCalcularPrazoEstimado_Simples_DeveRetornar5Dias() {
        // Arrange
        String descricao = "Cadastro de novo fornecedor";
        
        // Act
        int prazo = UtilitariaCalculos.calcularPrazoEstimado(descricao);
        
        // Assert
        assertEquals(5, prazo, "Processos simples devem ter 5 dias de prazo");
    }

    @Test
    @DisplayName("Descrição nula deve retornar prazo zero")
    public void testCalcularPrazoEstimado_DescricaoNula_DeveRetornarZero() {
        // Arrange & Act
        int prazo = UtilitariaCalculos.calcularPrazoEstimado(null);
        
        // Assert
        assertEquals(0, prazo, "Descrição nula deve retornar prazo zero");
    }

    @Test
    @DisplayName("Email válido com @ e . deve ser aceito")
    public void testValidarFormatoEmail_EmailValido_DeveRetornarTrue() {
        // Arrange
        String email = "usuario@sistema.com";
        
        // Act
        boolean valido = UtilitariaCalculos.validarFormatoEmail(email);
        
        // Assert
        assertTrue(valido, "Email com formato correto deve ser válido");
    }

    @Test
    @DisplayName("Email sem @ deve ser rejeitado")
    public void testValidarFormatoEmail_SemArroba_DeveRetornarFalse() {
        // Arrange
        String email = "usuariosistema.com";
        
        // Act
        boolean valido = UtilitariaCalculos.validarFormatoEmail(email);
        
        // Assert
        assertFalse(valido, "Email sem @ deve ser inválido");
    }

    @Test
    @DisplayName("Email nulo deve ser rejeitado")
    public void testValidarFormatoEmail_EmailNulo_DeveRetornarFalse() {
        // Arrange & Act
        boolean valido = UtilitariaCalculos.validarFormatoEmail(null);
        
        // Assert
        assertFalse(valido, "Email nulo deve ser inválido");
    }

    @Test
    @DisplayName("Processo com palavra 'urgente' deve ter prioridade alta (3)")
    public void testCalcularPrioridade_Urgente_DeveRetornar3() {
        // Arrange
        String descricao = "Atendimento urgente ao cliente";
        
        // Act
        int prioridade = UtilitariaCalculos.calcularPrioridade(descricao);
        
        // Assert
        assertEquals(3, prioridade, "Processos urgentes devem ter prioridade 3");
    }

    @Test
    @DisplayName("Processo com palavra 'importante' deve ter prioridade média (2)")
    public void testCalcularPrioridade_Importante_DeveRetornar2() {
        // Arrange
        String descricao = "Atualização importante de cadastro";
        
        // Act
        int prioridade = UtilitariaCalculos.calcularPrioridade(descricao);
        
        // Assert
        assertEquals(2, prioridade, "Processos importantes devem ter prioridade 2");
    }

    @Test
    @DisplayName("Processo sem palavras-chave deve ter prioridade baixa (1)")
    public void testCalcularPrioridade_Padrao_DeveRetornar1() {
        // Arrange
        String descricao = "Consulta de informações";
        
        // Act
        int prioridade = UtilitariaCalculos.calcularPrioridade(descricao);
        
        // Assert
        assertEquals(1, prioridade, "Processos padrão devem ter prioridade 1");
    }
}
