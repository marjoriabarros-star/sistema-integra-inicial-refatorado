/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.servicos;

import java.util.List;
import sistemaintegrainicial.dados.ProcessoDAO;
import sistemaintegrainicial.modelo.Processo;

/**
 *
 * @author Marjori Barros
 */
/**
 * Serviço de Processos
 * Responsabilidade: Regras de negócio relacionadas a processos
 */
public class ProcessoService {
    
    private ProcessoDAO processoDAO;

    public ProcessoService() {
        this.processoDAO = new ProcessoDAO();
    }

    /**
     * Lista todos os processos
     */
    public List<Processo> listarTodos() {
        return processoDAO.listarTodos();
    }

    /**
     * Busca um processo por ID
     */
    public Processo buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return processoDAO.buscarPorId(id);
    }

    /**
     * Cadastra um novo processo
     */
    public String cadastrar(Processo processo) {
        // Validações
        if (processo == null) {
            return "ERRO: Processo não pode ser nulo";
        }
        
        if (processo.getDescricao() == null || processo.getDescricao().trim().isEmpty()) {
            return "ERRO: Descrição do processo é obrigatória";
        }
        
        if (processo.getSetorId() <= 0) {
            return "ERRO: Setor deve ser selecionado";
        }
        
        // Cadastro
        boolean sucesso = processoDAO.inserir(processo);
        
        if (sucesso) {
            return "SUCESSO: Processo cadastrado com sucesso";
        } else {
            return "ERRO: Falha ao cadastrar processo";
        }
    }

    /**
     * Valida se o usuário pode gerenciar processos
     */
    public boolean podeGerenciar(String cargo) {
        if (cargo == null) {
            return false;
        }
        
        return cargo.equals("Administrador") || 
               cargo.equals("Direção") ||
               cargo.equals("Gerência") ||
               cargo.equals("Supervisão");
    }
}
