package group.csc280.pente.model;

public class Board {
	private char[][] board;
	int xSize;
	int ySize;
	int BcaptureCount;
	int WcaptureCount;

	// Initializes the board state
	public Board(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		BcaptureCount = 0;
		WcaptureCount = 0;
		board = new char[xSize][ySize];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		board[(int) ((xSize / 2) + .5)][(int) ((ySize / 2) + .5)] = 'B';
	}

	// Returns the board's current state
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	// resets the board to its initial state
	public boolean resetBoard() {
		BcaptureCount = 0;
		WcaptureCount = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		board[(int) ((xSize / 2) + .5)][(int) ((ySize / 2) + .5)] = 'B';
		return true;
	}

	// Prints the board to the console
	public boolean printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println();
		}
		return true;
	}

	// makes a move after validating the move
	public boolean makeMove(char turn, int x, int y, boolean isBlacksSecondMove) {
		if (isValidMove(x, y, isBlacksSecondMove)) {
			board[x][y] = turn;
			return true;
		}
		return false;
	}

	// checks if the attempted move is a valid move
	public boolean isValidMove(int x, int y, boolean isBlacksSecondMove) {
		if (x < 0 || x > xSize - 1 || y < 0 || y > ySize - 1) {
			return false;
		} else if (board[x][y] == ' ' && !isBlacksSecondMove) {
			return true;
		} else if (isBlacksSecondMove && board[x][y] == ' '
				&& ((x < (int) ((xSize / 2) + .5 - 2) || x > (int) ((xSize / 2) + .5 + 2))
						|| (y < (int) ((ySize / 2) + .5 - 2) || y > (int) ((ySize / 2) + .5 + 2)))) {
			return true;
		}
		return false;
	}

	// Deletes the two pieces that need capturing and adds those to the capture
	// count
	private void capture(Direction direction, char notTurn, int x, int y) {
		board[x + direction.dx][y + direction.dy] = ' ';
		board[x + (direction.dx * 2)][y + (direction.dy * 2)] = ' ';
		if (notTurn == 'B' || notTurn == 'b') {
			BcaptureCount++;
		}
		if (notTurn == 'W' || notTurn == 'w') {
			WcaptureCount++;
		}
	}

	// Checks if the placed piece captures anything and calls capture to capture the
	// pieces
	public boolean checkCapture(char turn, char notTurn, int x, int y) {
		if (!isValidMove(x, y, false)) {
			return false;
		}
		int right = 0;
		int up = 0;
		int left = 0;
		int down = 0;
		int upright = 0;
		int upleft = 0;
		int downright = 0;
		int downleft = 0;
		boolean found = false;
		for (int j = 1; j < 4; j++) {
			if (right == 3) {
				found = true;
				capture(Direction.right, notTurn, x, y);
			} else if (x + j > 18) {
				break;
			} else if (board[x + j][y] == turn && right == 2) {
				right++;
			} else if (board[x + j][y] == notTurn && right < 2) {
				right++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (up == 3) {
				found = true;
				capture(Direction.up, notTurn, x, y);
			} else if (y + j > 18) {
				break;
			} else if (board[x][y + j] == turn && up == 2) {
				up++;
			} else if (board[x][y + j] == notTurn && up < 2) {
				up++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (upright == 3) {
				found = true;
				capture(Direction.upright, notTurn, x, y);
			} else if (y + j > 18 || x + j > 18) {
				break;
			} else if (board[x + j][y + j] == turn && upright == 2) {
				upright++;
			} else if (board[x + j][y + j] == notTurn && upright < 2) {
				upright++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (upleft == 3) {
				found = true;
				capture(Direction.upleft, notTurn, x, y);
			} else if (x - j < 0 || y + j > 18) {
				break;
			} else if (board[x - j][y + j] == turn && upleft == 2) {
				upleft++;
			} else if (board[x - j][y + j] == notTurn && upleft < 2) {
				upleft++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (left == 3) {
				found = true;
				capture(Direction.left, notTurn, x, y);
			} else if (x - j < 0) {
				break;
			} else if (board[x - j][y] == turn && left == 2) {
				left++;
			} else if (board[x - j][y] == notTurn && left < 2) {
				left++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (down == 3) {
				found = true;
				capture(Direction.down, notTurn, x, y);
			} else if (y - j < 0) {
				break;
			} else if (board[x][y - j] == turn && down == 2) {
				down++;
			} else if (board[x][y - j] == notTurn && down < 2) {
				down++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (downleft == 3) {
				found = true;
				capture(Direction.downleft, notTurn, x, y);
			} else if (x - j < 0 || y - j < 0) {
				break;
			} else if (board[x - j][y - j] == turn && downleft == 2) {
				downleft++;
			} else if (board[x + j][y - j] == notTurn && downleft < 2) {
				downright++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (downright == 3) {
				found = true;
				capture(Direction.downright, notTurn, x, y);
			} else if (x + j > 18 || y - j < 0) {
				break;
			} else if (board[x + j][y - j] == turn && downright == 2) {
				downright++;
			} else if (board[x + j][y - j] == notTurn && downright < 2) {
				downright++;
			} else {
				break;
			}
		}
		return found;
	}

	// checks to see if the piece just placed causes that player to win by 5 in a
	// row
	public boolean isWinner(char turn, int x, int y) {
		if (!isValidMove(x, y, false)) {
			return false;
		}
		int horiz = 0;
		int vert = 0;
		int diagUp = 0;
		int diagDown = 0;
		for (int j = 1; j < 5; j++) {
			if (x + j > 18) {
				break;
			} else if (board[x + j][y] == turn) {
				horiz++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (y + j > 18) {
				break;
			} else if (board[x][y + j] == turn) {
				vert++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (y + j > 18 || x + j > 18) {
				break;
			} else if (board[x + j][y + j] == turn) {
				diagUp++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (x - j < 0 || y + j > 18) {
				break;
			}
			if (board[x - j][y + j] == turn) {
				diagUp++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (horiz == 4) {
				return true;
			} else if (x - j < 0) {
				break;
			} else if (board[x - j][y] == turn) {
				horiz++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (vert == 4) {
				return true;
			} else if (y - j < 0) {
				break;
			} else if (board[x][y - j] == turn) {
				vert++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (diagUp == 4) {
				return true;
			} else if (x - j < 0 || y - j < 0) {
				break;
			} else if (board[x - j][y - j] == turn) {
				diagUp++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 5; j++) {
			if (diagDown == 4) {
				return true;
			} else if (x + j > 18 || y - j < 0) {
				break;
			} else if (board[x + j][y - j] == turn) {
				diagDown++;
			} else {
				break;
			}
		}
		return false;
	}
}