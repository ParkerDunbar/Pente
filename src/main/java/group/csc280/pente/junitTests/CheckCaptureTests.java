package group.csc280.pente.junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import group.csc280.pente.model.Board;

public class CheckCaptureTests {
	// all tests are on a 19x19 board

	/**
	 * CheckBoard Needs To Be Fixed All Tests Fail
	 */

	@Test // Fails
	public void CheckTwoPieceVertialCapture() {
		Board b = new Board(19, 19);
		char black = 'B';
		char whight = 'W';
		b.makeMove(black, 0, 0, false);
		b.makeMove(whight, 0, 1, false);
		b.makeMove(whight, 0, 2, false);
		b.makeMove(black, 0, 3, false);
		assertTrue(b.checkCapture(black, whight, 0, 3));
	}

	@Test // Fails
	public void CheckTwoPieceHorizontalCapture() {
		Board b = new Board(19, 19);
		char black = 'B';
		char whight = 'W';
		b.makeMove(black, 0, 0, false);
		b.makeMove(whight, 1, 0, false);
		b.makeMove(whight, 2, 0, false);
		b.makeMove(black, 3, 0, false);
		assertTrue(b.checkCapture(black, whight, 3, 0));
	}

	@Test // Fails
	public void CheckTwoPieceDiaginalCapture() {
		Board b = new Board(19, 19);
		char black = 'B';
		char whight = 'W';
		b.makeMove(black, 0, 0, false);
		b.makeMove(whight, 1, 1, false);
		b.makeMove(whight, 2, 2, false);
		b.makeMove(black, 3, 3, false);
		assertTrue(b.checkCapture(black, whight, 3, 3));
	}



}
