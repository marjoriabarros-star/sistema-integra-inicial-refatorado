# Relatório de Refatoração - Sistema Integra Inicial

## 1. PRINCÍPIOS SOLID APLICADOS

### 1.1 Single Responsibility Principle (SRP) - Princípio da Responsabilidade Única

**O que é:** Cada classe deve ter apenas uma razão para mudar, ou seja, uma única responsabilidade.

**Classes Afetadas:**

#### a) UsuarioDAO.java
- **Antes:** Conexão com banco estava espalhada em todas as telas (TelaLogin, TelaUsuarios, etc.)
- **Depois:** Centralizada na classe UsuarioDAO
- **Justificativa:** Todas as operações de banco relacionadas a usuários ficam em um único local

#### b) ConexaoBD.java
- **Antes:** Código de conexão repetido em 5 classes diferentes
- **Depois:** Classe utilitária única para gerenciar conexões
- **Justificativa:** Centraliza e padroniza o acesso ao banco de dados

#### c) AutenticacaoService.java
- **Antes:** Regras de autenticação e permissão estavam em TelaLogin.java
- **Depois:** Classe de serviço dedicada
- **Justificativa:** Separa regras de negócio da interface gráfica

#### d) ProcessoService.java
- **Antes:** Regras de processo estavam em TelaProcessos.java
- **Depois:** Classe de serviço com validações e regras
- **Justificativa:** Permite reutilização e facilita testes

#### e) Classes de Modelo (Usuario.java, Processo.java, Setor.java)
- **Antes:** Dados misturados com lógica de UI
- **Depois:** Classes POJO (Plain Old Java Objects) puras
- **Justificativa:** Representam apenas os dados, sem comportamento

### 1.2 Dependency Inversion Principle (DIP) - Princípio da Inversão de Dependência

**Aplicação Parcial:**
- Services dependem de DAOs, mas poderiam depender de interfaces
- **Melhoria futura:** Criar interfaces como IUsuarioDAO, IProcessoDAO

## 2. REFACTORIZAÇÕES APLICADAS

### 2.1 Extração de Classe (Extract Class)

- **Code Smell:** Large Class / God Class
- **Onde:** TelaLogin.java, TelaDashboard.java, TelaProcessos.java

- **Antes:**
```java
// Em TelaLogin.java
private Connection conectar(Component parentComponent) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/integra_inicial";
        String user = "root";
        String password = "l5PV3IYH@";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    } catch (...)
}
- **Depois:**
// Em ConexaoBD.java
public class ConexaoBD {
    public static Connection conectar() {
        // Código centralizado
    }
}

### 2.2 Extração de Método (Extract Method)
- **Code Smell:** Long Method
- **Onde:** configurarPermissoes() em TelaDashboard.java
- **Antes:** Método com 50+ linhas e múltiplos if-else
- **Depois:** Lógica movida para AutenticacaoService.temPermissao()

### 2.3 Eliminação de Código Duplicado (Remove Duplicate Code)
- **Code Smell:** Duplicate Code
- **Onde:** Conexão com banco em 5 arquivos diferentes
- **Antes:** 
TelaLogin.java: 30 linhas de conexão
TelaDashboard.java: 30 linhas de conexão
TelaUsuarios.java: 30 linhas de conexão
TelaProcessos.java: 30 linhas de conexão
TelaManuais.java: 30 linhas de conexão
Total: ~150 linhas duplicadas
- **Depois:**
ConexaoBD.java: 40 linhas (reutilizáveis)
Economia: ~110 linhas

### 2.4 Separação de Camadas (Layer Separation)
- **Padrão Aplicado:**MVC (Model-View-Controller) simplificado
- **Camadas Criadas:**
1.Model (modelo): Usuario, Processo, Setor
2.Data Access (dados): UsuarioDAO, ProcessoDAO
3.Service (servicos): AutenticacaoService, ProcessoService
4.View (telas - a refatorar): Futuras telas web

### 3. PADRÕES DE PROJETO UTILIZADOS
### 3.1 DAO (Data Access Object)
- **O que é:** Padrão que isola a lógica de acesso a dados da lógica de negócio.
- **Classes:** UsuarioDAO.java, ProcessoDAO.java
- **Justificativa:**
1.Permite trocar o banco de dados sem afetar o resto do sistema
2.Facilita testes unitários
3.Centraliza queries SQL

### 3.2 Service Layer
- **O que é:** Camada intermediária que contém regras de negócio.
- **Classes:** AutenticacaoService.java, ProcessoService.java
- **Justificativa:**
1.Separa regras de negócio da persistência
2.Facilita reutilização
3.Permite validações centralizadas

### 4. BENEFÍCIOS DA REFACTORIZAÇÃO
1.Manutenibilidade: Código mais fácil de entender e modificar
2.Testabilidade: Classes isoladas permitem testes unitários
3.Reusabilidade: DAOs e Services podem ser usados em diferentes telas
4.Legibilidade: Nomes claros e responsabilidades definidas
5.Redução de Bugs: Menos código duplicado = menos pontos de falha

### 5. ESTRUTURA DO PROJETO
sistemaintegracinicial/
├── modelo/
│   ├── Usuario.java
│   ├── Processo.java
│   └── Setor.java
├── dados/
│   ├── UsuarioDAO.java
│   └── ProcessoDAO.java
├── servicos/
│   ├── AutenticacaoService.java
│   └── ProcessoService.java
├── util/
│   └── ConexaoBD.java
└── Main.java (testes)

### 6. EVIDÊNCIAS DO REPOSITÓRIO GITHUB
- **URL do Repositório:** https://github.com/marjoriabarros-star/sistema-integra-inicial-refatorado
- **Commits Realizados:**
1.feat: estrutura inicial com refatoração SOLID
2.refactor: extração de classes de modelo
3.refactor: criação de DAOs para acesso a dados
4.test: adição de testes na classe Main
