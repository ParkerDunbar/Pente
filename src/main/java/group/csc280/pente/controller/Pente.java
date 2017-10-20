package group.csc280.pente.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import group.csc280.pente.model.Board;
import group.csc280.pente.view.MainView;

public class Pente {
	private final File file = new File("./Board.txt");

	public void run() {
		MainView.launch(MainView.class);

	}

	public String getRules() {
		String rules = "Object of Game:\r\nWin by placing five (or more) of your stones in a row, vertically, horizontally, or diagonally, with no empty points between them. Or, win by capturing five (or more) pairs of your opponent's stones."
				+ "\r\n\r\nHow to Play:\r\nPlay starts with the board completely clear of stones. The first player (black) begins the game by playing one stone on the center point. Thereafter the players take turns placing their stones, one at a time, on any empty intersection. The stones are placed on the intersections of the lines (including the very edge of the board), rather than in the squares. Once played, a stone cannot be moved again, except when removed by a capture. Players alternate turns adding new stones to the board, building up their positions, until one player wins"
				+ "\r\n\r\nCaptures:\r\nWhenever your opponent has two stones (and only two) which are adjacent, those stones are vulnerable to capture. The pair can be captured by bracketing its two ends with your own stones. The captured stones are removed from the board.\r\n"
				+ "\r\n"
				+ "Captures can be made vertically, horizontally, or diagonally, and multiple captures can occur on a single move"
				+ "\r\n\r\nWinning the Game:\r\nThe game ends immediately when one player captures five pairs, or places five stones in a row. The opposing player has no “last chance” to make a final move.\r\n"
				+ "\r\n"
				+ "When a player obtains an unblocked row of four stones, called a tessera, a win is imminent. Therefore, an unblocked row of three stones, called a tria, is a serious threat that should be blocked unless a stronger offensive move exists. An unblocked row of three stones, if it contains one gap, is still considered a tria. In the example to the right, black has formed both a horizontal and a vertical tria, while white has formed a tessera and will win with the next move";
		return rules;
	}

	public boolean saveBoard(char turn, Board b) throws IOException {
		File file = this.file;
		FileWriter fileW = new FileWriter(file);
		char[][] board = b.getBoard();
		fileW.write(turn + "&" + board.length + "&" + board[0].length + "&" + b.getCaptureCount('B') + "&"
				+ b.getCaptureCount('W') + "&");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				fileW.write(board[i][j]);
			}
		}
		fileW.close();
		return true;
	}

	public char loadBoard(Board b) throws IOException {
		File file = this.file;
		FileReader fileR = new FileReader(file);
		String unSplit = "";
		char c = ' ';
		int a;
		while ((a = fileR.read()) != -1) {
			c = (char) a;
			unSplit += c;
		}
		String[] split = unSplit.split("&");
		int x = Integer.parseInt(split[1]);
		int y = Integer.parseInt(split[2]);
		char[][] board = new char[x][y];
		char[] splitChars = split[5].toCharArray();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[i][j] = splitChars[i * y + j];
			}
		}
		fileR.close();
		b.setBoard(board);
		b.setCaptureCount(Integer.parseInt(split[3]), Integer.parseInt(split[4]));
		return split[0].toCharArray()[0];
	}

}
