package group.csc280.pente.model;

public class Board {
	private char[][] board = new char[19][19];

	public Board() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		board[9][9] = 'B';
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println();
		}
	}

	public boolean isValidMove(int x, int y) {
		if (board[x][y] == ' ') {
			return true;
		}
		return false;
	}

	public boolean isWinner(char turn, int x, int y) {
		int horiz = 0;
		for (int j = 1; j < 5; j++) {
			if (x + j > 18) {
				break;
			} else if (board[x + j][y] == turn) {
				horiz++;
			} else {
				break;
			}
		}
		int vert = 0;
		for (int j = 1; j < 5; j++) {
			if (y + j > 18) {
				break;
			} else if (board[x][y + j] == turn) {
				vert++;
			} else {
				break;
			}
		}
		int diagUp = 0;
		for (int j = 1; j < 5; j++) {
			if (y + j > 18 || x + j > 18) {
				break;
			} else if (board[x + j][y + j] == turn) {
				diagUp++;
			} else {
				break;
			}
		}
		int diagDown = 0;
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
