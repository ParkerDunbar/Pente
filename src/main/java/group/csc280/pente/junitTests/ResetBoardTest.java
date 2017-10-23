package group.csc280.pente.junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import group.csc280.pente.model.Board;

public class ResetBoardTest {

	@Test
	public void TestResetBoard() {
		Board b = new Board(19, 19);
		char[][] board = b.getBoard();
		char player = 'P';
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				b.makeMove(player, i, j, false);
			}
		}
		assertTrue(b.resetBoard());
	}
}
