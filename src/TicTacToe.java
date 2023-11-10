import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";
    private static int moveCnt = 0;

    public static void main(String[] args) {
        clearBoard();
        display();

        while (!isGameOver()) {
            playTurn();
            display();
        }
    }

    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if (col < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < ROW - 1) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private static void playTurn() {
        int rowMove, colMove;
        do {
            display(); // Show the board as part of the prompt
            System.out.println("Player " + currentPlayer + "'s turn.");
            rowMove = SafeInput.getRangedInt(new Scanner(System.in), "Enter row using numbers (1-3): ", 1, 3) - 1;
            colMove = SafeInput.getRangedInt(new Scanner(System.in), "Enter column using number  (1-3): ", 1, 3) - 1;
        } while (!isValidMove(rowMove, colMove));

        board[rowMove][colMove] = currentPlayer;
        moveCnt++;

        if (isWin(currentPlayer)) {
            System.out.println("Player " + currentPlayer + " wins!");
            resetGame();
        } else if (isTie()) {
            System.out.println("a tie!");
            resetGame();
        } else {
            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL) {
            System.out.println("Invalid move");
            return false;
        }

        if (!board[row][col].equals(" ")) {
            System.out.println("Invalid move.");
            return false;
        }

        return true;
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        return moveCnt == ROW * COL;
    }

    private static boolean isGameOver() {
        return isWin("X") || isWin("O") || isTie();
    }

    private static void resetGame() {
        clearBoard();
        moveCnt = 0;
        currentPlayer = "X";
        display();
    }
}
