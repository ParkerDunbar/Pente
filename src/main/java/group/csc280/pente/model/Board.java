package group.csc280.pente.model;



public class Board {
	public enum Direction {
		left(-1, 0),
		right(1,0),
		down(0, -1),
		up(0, 1),
		upright(1, 1),
		upleft(-1, 1),
		downleft(-1, -1),
		downright(1, -1);
		
		public int dx;
		public int dy;
		
		Direction (int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	private char[][] board;

	public Board() {
		board = new char[19][19];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		board[9][9] = 'B';
	}

	public char[][] getBoard(){
		return board;
	}
	
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println();
		}
	}

	public boolean makeMove(char turn, int x, int y) {
		if (isValidMove(x, y)) {
			board[x][y] = turn;
			return true;
		}
		return false;
	}

	public boolean isValidMove(int x, int y) {
		if (board[x][y] == ' ') {
			return true;
		}
		return false;
	}

	private void capture(Direction direction, int x, int y) {
		board[x+direction.dx][y+direction.dy] = ' ';
		board[x+(direction.dx*2)][y+(direction.dy*2)] = ' ';
	}

	public boolean checkCapture(char turn, char notTurn, int x, int y) {
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
				capture(Direction.right, x, y);
			} else if (x + j > 18) {
				break;
			} else if (board[x + j][y] == turn && right == 2) {
				right++;
			} else if (board[x + j][y] == notTurn) {
				right++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (up == 3) {
				found = true;
				capture(Direction.up, x, y);
			} else if (y + j > 18) {
				break;
			} else if (board[x][y + j] == turn && up == 2) {
				up++;
			} else if (board[x][y + j] == notTurn) {
				up++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (upright == 3) {
				found = true;
				capture(Direction.upright, x, y);
			} else if (y + j > 18 || x + j > 18) {
				break;
			} else if (board[x + j][y + j] == turn && upright == 2) {
				upright++;
			} else if (board[x + j][y + j] == notTurn) {
				upright++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (upleft == 3) {
				found = true;
				capture(Direction.upleft, x, y);
			} else if (x - j < 0 || y + j > 18) {
				break;
			} else if (board[x - j][y + j] == turn && upleft == 2) {
				upleft++;
			} else if (board[x - j][y + j] == notTurn) {
				upleft++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (left == 3) {
				found = true;
				capture(Direction.left, x, y);
			} else if (x - j < 0) {
				break;
			} else if (board[x - j][y] == turn && left == 2) {
				left++;
			} else if (board[x - j][y] == notTurn) {
				left++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (down == 3) {
				found = true;
				capture(Direction.down, x, y);
			} else if (y - j < 0) {
				break;
			} else if (board[x][y - j] == turn && down == 2) {
				down++;
			} else if (board[x][y - j] == notTurn) {
				down++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (downleft == 3) {
				found = true;
				capture(Direction.downleft, x, y);
			} else if (x - j < 0 || y - j < 0) {
				break;
			} else if (board[x - j][y - j] == turn && downleft == 2) {
				downleft++;
			} else if (board[x + j][y - j] == notTurn) {
				downright++;
			} else {
				break;
			}
		}
		for (int j = 1; j < 4; j++) {
			if (downright == 3) {
				found = true;
				capture(Direction.downright, x, y);
			} else if (x + j > 18 || y - j < 0) {
				break;
			} else if (board[x + j][y - j] == turn && downright == 2) {
				downright++;
			} else if (board[x + j][y - j] == notTurn) {
				downright++;
			} else {
				break;
			}
		}
		return found;
	}

	public boolean isWinner(char turn, int x, int y) {
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