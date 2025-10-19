import java.util.Scanner;

public class jogoVelha {

    public static char[][] tabuleiro;
    private static final int TAMANHO_TABULEIRO = 3;
    private static char jogadorAtual;
    private static Scanner scanner = new Scanner(System.in);

    public static void inicializarTabuleiro() {
        tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
        jogadorAtual = 'X';
    }

    public static void exibirTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.print("| ");
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }

            System.out.println();
            if (i < TAMANHO_TABULEIRO - 1) {
                System.out.println("-------------");
            }
        }
    }

    public static boolean fazerJogada(int linha, int coluna) {
        if (linha >= 0 && linha < TAMANHO_TABULEIRO &&
                coluna >= 0 && coluna < TAMANHO_TABULEIRO &&
                tabuleiro[linha][coluna] == ' ') {

            tabuleiro[linha][coluna] = jogadorAtual;
            return true;
        } else {
            System.out.println("Jogada inválida. Tente novamente.");
            return false;
        }

    }

    public static boolean verificarVitoria() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;
            }
        }

        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            if (tabuleiro[0][j] == jogadorAtual && tabuleiro[1][j] == jogadorAtual && tabuleiro[2][j] == jogadorAtual) {
                return true;
            }
        }

        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
                (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }
        return false;
    }

    public static boolean verificarEmpate() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        inicializarTabuleiro();
        boolean jogoTerminado = false;

        while (!jogoTerminado) {
            exibirTabuleiro();
            System.out.println("jogador " + jogadorAtual + ", é a sua vez.");

            int linha, coluna;
            boolean jogadaValida;

            do {
                System.out.print("Digite a linha (1-3): ");
                linha = scanner.nextInt() - 1;
                System.out.print("Digite a coluna (1-3): ");
                coluna = scanner.nextInt() - 1;

                jogadaValida = fazerJogada(linha, coluna);
            } while (!jogadaValida);

            if (verificarVitoria()) {
                exibirTabuleiro();
                System.out.println("Parábens! Jogador " + jogadorAtual + " venceu!");
                jogoTerminado = true;
            } else if (verificarEmpate()) {
                exibirTabuleiro();
                System.out.println("O jogo empatou!");
                jogoTerminado = true;
            } else {
                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            }
        }
        scanner.close();
    }
}

