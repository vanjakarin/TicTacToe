

/**
 *
 * @author vanja
 * 
 * TODO: 
 * 1. Ask players for name (i.e. letter) instead of x and o 
 * 2. Make board size configurable 
 * 3. Use letters instead of coordinates for board readability 
 * 4. More specific error messages when invalid input
 * 5. Ask users to play again... 
 *
 */
public class TicTacToe {
	
	private static final int SIZE = 3;

	public static void main(String[] args) {
				
		TicTacToeGame game = new TicTacToeGame(SIZE);
		game.drawBoard();
		game.play();
	}

}
