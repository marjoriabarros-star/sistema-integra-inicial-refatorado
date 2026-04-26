/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegrainicial.modelo;

/**
 *
 * @author Marjori Barros
 */
/**
 * Classe que representa um processo do sistema
 * Responsabilidade: Armazenar dados de processos
 */
    public class Processo {
    private int id;
    private String descricao;
    private int setorId;
    private String nomeSetor;

    public Processo() {
    }

    public Processo(String descricao, int setorId) {
        this.descricao = descricao;
        this.setorId = setorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSetorId() {
        return setorId;
    }

    public void setSetorId(int setorId) {
        this.setorId = setorId;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
}
