/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.servicos;

import sistemaintegrainicial.dados.UsuarioDAO;
import sistemaintegrainicial.modelo.Usuario;


/**
 *
 * @author Marjori Barros
 */
/**
 * Serviço de Autenticação
 * Responsabilidade: Regras de negócio relacionadas à autenticação
 * Princípio SOLID aplicado: Single Responsibility Principle (SRP)
 */
public class AutenticacaoService {
    
    private UsuarioDAO usuarioDAO;

    public AutenticacaoService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Autentica um usuário no sistema
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return Mensagem de sucesso ou erro
     */
    public String autenticar(String email, String senha) {
        // Validação de campos obrigatórios
        if (email == null || email.trim().isEmpty()) {
            return "ERRO: Email não pode ser vazio";
        }
        
        if (senha == null || senha.trim().isEmpty()) {
            return "ERRO: Senha não pode ser vazia";
        }
        
        // Autenticação no banco
        Usuario usuario = usuarioDAO.autenticar(email, senha);
        
        if (usuario != null) {
            return "SUCESSO:" + usuario.getCargo();
        } else {
            return "ERRO: Email ou senha inválidos";
        }
    }

    /**
     * Verifica se o usuário tem permissão para acessar determinada funcionalidade
     * @param cargo Cargo do usuário
     * @param funcionalidade Nome da funcionalidade
     * @return true se tiver permissão, false caso contrário
     */
    public boolean temPermissao(String cargo, String funcionalidade) {
        if (cargo == null || funcionalidade == null) {
            return false;
        }
        
        // Administrador e Direção têm acesso total
        if (cargo.equals("Administrador") || cargo.equals("Direção")) {
            return true;
        }
        
        // Verifica permissões específicas
        switch (funcionalidade) {
            case "USUARIOS":
                return cargo.equals("Administrador") || cargo.equals("Direção");
            case "PROCESSOS":
                return cargo.equals("Gerência") || cargo.equals("Supervisão") || 
                       cargo.equals("Analistas") || cargo.equals("Assistentes") ||
                       cargo.equals("Auxiliares") || cargo.equals("Atendentes");
            case "MANUAIS":
                return cargo.equals("Gerência") || cargo.equals("Supervisão") || 
                       cargo.equals("Analistas");
            default:
                return false;
        }
    }
}
