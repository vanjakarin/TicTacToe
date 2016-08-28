import java.util.Scanner;

public class TicTacToeGame {
	
	private static char EMPTY_BOX = ' ';
	private static String BOX_DIVISOR = "|";
	private static char PLAYER_1 = 'X';
	private static char PLAYER_2 = 'O';
	private static String PLAYER_1_WINS = "XXX";
	private static String PLAYER_2_WINS = "OOO";
	private static final String ROUND_DIVISOR = "------------------------------------";

	private int size;
	private char board[][];

	public TicTacToeGame(int size) {
		this.size = size;
		initiateBoard();
		System.out.println("Welcome to the classic game of Tic Tac Toe");
		System.out
				.println("When it is your turn, type x,y for your move, e.g. 0,0 for top left");
	}

	public void drawBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(BOX_DIVISOR);
				System.out.print(board[j][i]);
			}
			System.out.println(BOX_DIVISOR);
		}
	}

	public void play() {

		char player = PLAYER_1;

		Scanner scan = new Scanner(System.in);

		String winner = null;
		while (winner == null && hasEmptyBoxes()) {

			System.out.println("Player " + player + " - make your move");

			String input;
			do {
				input = scan.nextLine();
			} while (!storeInput(input, player));

			drawBoard();

			player = toogglePlayer(player);

			winner = findWinner();
			System.out.println(ROUND_DIVISOR);
		}

		if (winner != null) {
			System.out.println("THE WINNER IS PLAYER: " + winner.charAt(0));
		} else {
			System.out.println("Game over! No winner..");
		}
		scan.close();
	}

	private boolean hasEmptyBoxes() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(board[j][i] == EMPTY_BOX){
					return true;
				}
			}
		}
		return false;
	}

	private String findWinner() {

		StringBuilder sbRow = new StringBuilder();
		StringBuilder sbCol = new StringBuilder();
		StringBuilder sbDiag = new StringBuilder();

		// Check rows, cols and diags
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sbRow.append(board[i][j]);
				sbCol.append(board[j][i]);
				if (i == j) {
					sbDiag.append(board[i][j]);
				}
			}
			String row = sbRow.toString();
			String col = sbCol.toString();
			String diag = sbDiag.toString();

			if (isWinning(row)) {
				return row;
			} else if (isWinning(col)) {
				return col;
			} else if (isWinning(diag)) {
				return diag;
			}
			sbRow.setLength(0);
			sbCol.setLength(0);
		}

		StringBuilder sbDiagInv = new StringBuilder();
		sbDiagInv.append(board[0][size - 1]).append(board[1][1])
				.append(board[size - 1][0]);
		String diagInv = sbDiagInv.toString();
		if (isWinning(diagInv)) {
			return diagInv;
		}

		return null;
	}

	private boolean isWinning(String row) {
		return row.equals(PLAYER_1_WINS) || row.equals(PLAYER_2_WINS);
	}

	private boolean storeInput(String input, char player) {

		if (!input.contains(",")) {
			System.out
					.println("Input must contain the a ',' between the coordinates. Try again!");
			return false;
		}

		String[] coordinates = input.split(",");
		int x, y;
		try {
			x = new Integer(coordinates[0].trim());
			y = new Integer(coordinates[1].trim());
			if (!withinBounds(x) || !withinBounds(y)
					|| board[x][y] != EMPTY_BOX) {
				System.out.println("x or y coordinate invalid. Try again!");
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		board[x][y] = player;
		return true;
	}

	private boolean withinBounds(int a) {
		return a >= 0 && a < size;
	}

	private char toogglePlayer(char player) {
		if (player == PLAYER_1) {
			return PLAYER_2;
		}
		return PLAYER_1;
	}

	private void initiateBoard() {
		board = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[j][i] = EMPTY_BOX;
			}
		}
	}

}
