/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.util;

/**
 *
 * @author Marjori Barros
 */
/**
 * Classe utilitária para cálculos e validações do sistema
 * Responsabilidade: Funções matemáticas e validações puras
 */
public class UtilitariaCalculos {

    /**
     * Calcula o prazo estimado para conclusão de um processo
     * Regra: 
     * - Processos simples: 5 dias úteis
     * - Processos médios: 15 dias úteis  
     * - Processos complexos: 30 dias úteis
     * 
     * @param descricao Descrição do processo
     * @return Número estimado de dias úteis
     */
    public static int calcularPrazoEstimado(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            return 0;
        }
        
        String descLower = descricao.toLowerCase();
        
        // Processos complexos (palavras-chave)
        if (descLower.contains("licença") || 
            descLower.contains("auditoria") || 
            descLower.contains("fiscalização")) {
            return 30;
        }
        
        // Processos médios
        if (descLower.contains("relatório") || 
            descLower.contains("análise") || 
            descLower.contains("aprovação")) {
            return 15;
        }
        
        // Processos simples (padrão)
        return 5;
    }

    /**
     * Valida se um email tem formato válido (básico)
     * @param email Email a ser validado
     * @return true se válido, false caso contrário
     */
    public static boolean validarFormatoEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Validação simples: deve conter @ e .
        return email.contains("@") && 
               email.contains(".") && 
               email.indexOf("@") < email.lastIndexOf(".");
    }

    /**
     * Calcula prioridade do processo baseado na descrição
     * @param descricao Descrição do processo
     * @return Nível de prioridade: 1 (Baixa), 2 (Média), 3 (Alta)
     */
    public static int calcularPrioridade(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            return 1; // Prioridade baixa padrão
        }
        
        String descLower = descricao.toLowerCase();
        
        // Alta prioridade
        if (descLower.contains("urgente") || 
            descLower.contains("crítico") || 
            descLower.contains("emergência")) {
            return 3;
        }
        
        // Média prioridade
        if (descLower.contains("importante") || 
            descLower.contains("prioritário")) {
            return 2;
        }
        
        return 1; // Baixa prioridade padrão
    }
}