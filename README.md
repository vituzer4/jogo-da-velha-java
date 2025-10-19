# 🎮 Jogo da Velha em Java

Projeto de Jogo da Velha desenvolvido em Java para console como parte do aprendizado no 1° período de Análise e Desenvolvimento de Sistemas.

## 📝 Sobre o Projeto

Jogo da velha clássico para dois jogadores jogado no terminal. O objetivo foi praticar:
  - Lógica de programação
  - Estruturas de repetição e condicionais
  - Manipulação de arrays bidimensionais
  - Validação de entrada do usuário

## 🤖 Desenvolvimento com IA como Professor

Este projeto foi desenvolvido com a seguinte abordagem: Usei a Manus como inteligencia artifical usada. Ela atuou como um professor, explicando conceitos e orientando a lógica, mas **sem fornecer código pronto**.

### Prompt Utilizado

```
"Atue como um professor de programação Java. Me ajude a criar um Jogo da Velha 
explicando os conceitos e a lógica necessária para cada funcionalidade, mas não 
forneça código pronto. Quando eu implementar algo, revise e sugira melhorias, 
me fazendo perguntas que me levem a entender o porquê de cada decisão. 
Quero construir o conhecimento passo a passo."
```

Todo o aprendizado e decisões de implementação foram feitos pelo desenvolvedor através de compreensão ativa dos conceitos.

## ✨ Funcionalidades

- ✅ Inicialização do tabuleiro 3x3
- ✅ Alternância automática entre jogadores X e O
- ✅ Validação de jogadas (não permite sobrescrever posições)
- ✅ Verificação de vitória (linhas, colunas e diagonais)
- ✅ Detecção de empate
- ✅ Interface visual no console

## 🚀 Como Executar

```bash
# Compilar
javac jogoVelha.java

# Executar
java jogoVelha
```

**Como jogar:**
1. Digite a linha desejada (1-3)
2. Digite a coluna desejada (1-3)
3. Alterne entre os jogadores até vitória ou empate

## 📖 Código Comentado

### Estrutura e Variáveis Principais

```java
// Tabuleiro do jogo representado por uma matriz 3x3
public static char[][] tabuleiro;

// Tamanho fixo do tabuleiro
private static final int TAMANHO_TABULEIRO = 3;

// Indica o jogador atual ('X' ou 'O')
private static char jogadorAtual;

// Scanner para ler entrada do usuário
private static Scanner scanner = new Scanner(System.in);
```

### Método: `inicializarTabuleiro()`

Inicializa o tabuleiro com espaços vazios e define o jogador inicial como 'X'.

```java
public static void inicializarTabuleiro() {
    tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            tabuleiro[i][j] = ' '; // espaço vazio significa posição livre
        }
    }
    jogadorAtual = 'X'; // jogador X começa
}
```

### Método: `exibirTabuleiro()`

Exibe o tabuleiro no console de forma visual.

```java
public static void exibirTabuleiro() {
    for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
        System.out.print("| ");
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            System.out.print(tabuleiro[i][j] + " | "); // exibe cada posição
        }
        System.out.println();
        if (i < TAMANHO_TABULEIRO - 1) {
            System.out.println("-------------"); // separador visual entre linhas
        }
    }
}
```

**Resultado visual:**
```
| X |   | O |
-------------
|   | X |   |
-------------
| O |   | X |
```

### Método: `fazerJogada(int linha, int coluna)`

Realiza a jogada do jogador na posição escolhida, validando se é uma jogada válida.

```java
public static boolean fazerJogada(int linha, int coluna) {
    // Verifica se a posição está dentro do tabuleiro e vazia
    if (linha >= 0 && linha < TAMANHO_TABULEIRO &&
        coluna >= 0 && coluna < TAMANHO_TABULEIRO &&
        tabuleiro[linha][coluna] == ' ') {

        tabuleiro[linha][coluna] = jogadorAtual; // marca a posição com o símbolo do jogador
        return true; // jogada válida
    } else {
        System.out.println("Jogada inválida. Tente novamente.");
        return false; // jogada inválida
    }
}
```

### Método: `verificarVitoria()`

Verifica se o jogador atual venceu checando linhas, colunas e diagonais.

```java
public static boolean verificarVitoria() {
    // Verifica linhas
    for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
        if (tabuleiro[i][0] == jogadorAtual &&
            tabuleiro[i][1] == jogadorAtual &&
            tabuleiro[i][2] == jogadorAtual) {
            return true; // vitória na linha
        }
    }

    // Verifica colunas
    for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
        if (tabuleiro[0][j] == jogadorAtual &&
            tabuleiro[1][j] == jogadorAtual &&
            tabuleiro[2][j] == jogadorAtual) {
            return true; // vitória na coluna
        }
    }

    // Verifica diagonais
    if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
        (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
        return true; // vitória na diagonal
    }

    return false; // nenhuma vitória
}
```

### Método: `verificarEmpate()`

Verifica se o jogo terminou em empate (tabuleiro cheio sem vencedor).

```java
public static boolean verificarEmpate() {
    for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            if (tabuleiro[i][j] == ' ') {
                return false; // ainda existem espaços livres
            }
        }
    }
    return true; // empate
}
```

### Método: `main(String[] args)`

Função principal que executa o loop do jogo.

```java
public static void main(String[] args) {
    inicializarTabuleiro();
    boolean jogoTerminado = false;

    while (!jogoTerminado) {
        exibirTabuleiro(); // mostra tabuleiro atual
        System.out.println("Jogador " + jogadorAtual + ", é a sua vez.");

        int linha, coluna;
        boolean jogadaValida;

        // Loop até o jogador fazer uma jogada válida
        do {
            System.out.print("Digite a linha (1-3): ");
            linha = scanner.nextInt() - 1; // ajusta índice para 0-2
            System.out.print("Digite a coluna (1-3): ");
            coluna = scanner.nextInt() - 1;

            jogadaValida = fazerJogada(linha, coluna);
        } while (!jogadaValida);

        // Verifica se houve vitória ou empate
        if (verificarVitoria()) {
            exibirTabuleiro();
            System.out.println("Parabéns! Jogador " + jogadorAtual + " venceu!");
            jogoTerminado = true;
        } else if (verificarEmpate()) {
            exibirTabuleiro();
            System.out.println("O jogo empatou!");
            jogoTerminado = true;
        } else {
            // Alterna o jogador
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }
    }

    scanner.close(); // fecha o scanner ao final do jogo
}
```

## 🎯 Decisões de Design

**Por que não há comentários no código original?**
Mantive o código original limpo, colocando os comentários detalhados aqui na README para facilitar a compreensão sem poluir visualmente o código.

**Por que métodos estáticos?**
Para um projeto simples, métodos estáticos facilitam o desenvolvimento sem necessidade de instanciar objetos.

**Ajuste de índice (- 1)**
Usuários digitam 1-3 (mais intuitivo), mas arrays usam índices 0-2. Por isso subtraímos 1.

## 🧠 Aprendizados

- ✅ Manipulação de arrays bidimensionais
- ✅ Validação robusta de entrada
- ✅ Lógica de verificação em múltiplas direções
- ✅ Estruturas de repetição aninhadas
- ✅ Controle de fluxo com loops

## 🚀 Melhorias Futuras

- [ ] Modo contra o computador (IA)
- [ ] Interface gráfica
- [ ] Sistema de pontuação
- [ ] Opção de jogar novamente
- [ ] Customização de símbolos

---

**Desenvolvido como projeto de aprendizado - 2º período ADS** 🎓
