/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial;

import sistemaintegrainicial.modelo.Usuario;
import sistemaintegrainicial.modelo.Processo;
import sistemaintegrainicial.servicos.AutenticacaoService;
import sistemaintegrainicial.servicos.ProcessoService;

/**
 *
 * @author Marjori Barros
 */
/**
 * Classe principal com testes das funcionalidades refatoradas
 * Objetivo: Testar as classes de modelo, dados e serviços
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   TESTES DO SISTEMA INTEGRA INICIAL   ");
        System.out.println("========================================\n");

        // Teste 1: Autenticação
        testarAutenticacao();

        // Teste 2: Permissões
        testarPermissoes();

        // Teste 3: Processos
        testarProcessos();

        System.out.println("\n========================================");
        System.out.println("          TODOS OS TESTES FINALIZADOS   ");
        System.out.println("========================================");
    }

    /**
     * Testa a autenticação de usuários
     */
    private static void testarAutenticacao() {
        System.out.println("TESTE 1: AUTENTICAÇÃO");
        System.out.println("----------------------------------------");

        AutenticacaoService auth = new AutenticacaoService();

        // Teste com credenciais válidas (ajuste conforme seu banco)
        System.out.println("Teste 1.1: Autenticação com email válido");
        String resultado1 = auth.autenticar("admin@empresa.com", "admin123");
        System.out.println("Resultado: " + resultado1);
        System.out.println("Status: " + (resultado1.startsWith("SUCESSO") ? "✓ PASSOU" : "✗ FALHOU"));

        // Teste com credenciais inválidas
        System.out.println("\nTeste 1.2: Autenticação com email inválido");
        String resultado2 = auth.autenticar("invalido@email.com", "senhaerrada");
        System.out.println("Resultado: " + resultado2);
        System.out.println("Status: " + (resultado2.startsWith("ERRO") ? "✓ PASSOU" : "✗ FALHOU"));

        // Teste com campos vazios
        System.out.println("\nTeste 1.3: Autenticação com email vazio");
        String resultado3 = auth.autenticar("", "senha");
        System.out.println("Resultado: " + resultado3);
        System.out.println("Status: " + (resultado3.startsWith("ERRO") ? "✓ PASSOU" : "✗ FALHOU"));

        System.out.println();
    }

    /**
     * Testa o sistema de permissões
     */
    private static void testarPermissoes() {
        System.out.println("TESTE 2: PERMISSÕES");
        System.out.println("----------------------------------------");

        AutenticacaoService auth = new AutenticacaoService();

        // Teste Administrador
        System.out.println("Teste 2.1: Permissões do Administrador");
        boolean adminUsuarios = auth.temPermissao("Administrador", "USUARIOS");
        boolean adminProcessos = auth.temPermissao("Administrador", "PROCESSOS");
        boolean adminManuais = auth.temPermissao("Administrador", "MANUAIS");
        System.out.println("  - Usuários: " + adminUsuarios + " (esperado: true)");
        System.out.println("  - Processos: " + adminProcessos + " (esperado: true)");
        System.out.println("  - Manuais: " + adminManuais + " (esperado: true)");
        System.out.println("Status: " + (adminUsuarios && adminProcessos && adminManuais ? "✓ PASSOU" : "✗ FALHOU"));

        // Teste Gerência
        System.out.println("\nTeste 2.2: Permissões da Gerência");
        boolean gerenciaUsuarios = auth.temPermissao("Gerência", "USUARIOS");
        boolean gerenciaProcessos = auth.temPermissao("Gerência", "PROCESSOS");
        boolean gerenciaManuais = auth.temPermissao("Gerência", "MANUAIS");
        System.out.println("  - Usuários: " + gerenciaUsuarios + " (esperado: false)");
        System.out.println("  - Processos: " + gerenciaProcessos + " (esperado: true)");
        System.out.println("  - Manuais: " + gerenciaManuais + " (esperado: true)");
        System.out.println("Status: " + (!gerenciaUsuarios && gerenciaProcessos && gerenciaManuais ? "✓ PASSOU" : "✗ FALHOU"));

        // Teste Analistas
        System.out.println("\nTeste 2.3: Permissões dos Analistas");
        boolean analistaProcessos = auth.temPermissao("Analistas", "PROCESSOS");
        boolean analistaManuais = auth.temPermissao("Analistas", "MANUAIS");
        System.out.println("  - Processos: " + analistaProcessos + " (esperado: true)");
        System.out.println("  - Manuais: " + analistaManuais + " (esperado: true)");
        System.out.println("Status: " + (analistaProcessos && analistaManuais ? "✓ PASSOU" : "✗ FALHOU"));

        System.out.println();
    }

    /**
     * Testa o gerenciamento de processos
     */
    private static void testarProcessos() {
        System.out.println("TESTE 3: PROCESSOS");
        System.out.println("----------------------------------------");

        ProcessoService processoService = new ProcessoService();

        // Teste listagem
        System.out.println("Teste 3.1: Listar todos os processos");
        try {
            var processos = processoService.listarTodos();
            System.out.println("Total de processos encontrados: " + processos.size());
            System.out.println("Status: ✓ PASSOU (sem erros de execução)");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Status: ✗ FALHOU");
        }

        // Teste busca por ID
        System.out.println("\nTeste 3.2: Buscar processo por ID");
        try {
            Processo p = processoService.buscarPorId(1);
            if (p != null) {
                System.out.println("Processo encontrado: " + p.getDescricao());
                System.out.println("Status: ✓ PASSOU");
            } else {
                System.out.println("Processo não encontrado (pode ser normal se não houver ID 1)");
                System.out.println("Status: ✓ PASSOU (sem erros)");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Status: ✗ FALHOU");
        }

        // Teste validação de cadastro
        System.out.println("\nTeste 3.3: Validar cadastro de processo");
        Processo processoInvalido = new Processo("", 0);
        String resultado = processoService.cadastrar(processoInvalido);
        System.out.println("Resultado: " + resultado);
        System.out.println("Status: " + (resultado.startsWith("ERRO") ? "✓ PASSOU" : "✗ FALHOU"));

        // Teste permissão para gerenciar
        System.out.println("\nTeste 3.4: Verificar permissão para gerenciar processos");
        boolean podeGerenciarAdmin = processoService.podeGerenciar("Administrador");
        boolean podeGerenciarVisitante = processoService.podeGerenciar("Visitante");
        System.out.println("  - Administrador pode gerenciar: " + podeGerenciarAdmin + " (esperado: true)");
        System.out.println("  - Visitante pode gerenciar: " + podeGerenciarVisitante + " (esperado: false)");
        System.out.println("Status: " + (podeGerenciarAdmin && !podeGerenciarVisitante ? "✓ PASSOU" : "✗ FALHOU"));

        System.out.println();
    }
}
