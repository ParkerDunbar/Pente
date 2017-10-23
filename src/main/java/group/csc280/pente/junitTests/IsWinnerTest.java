package group.csc280.pente.junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import group.csc280.pente.model.Board;

public class IsWinnerTest {

	/*
	 * The necessary test "you cant win" all fail and the "IsWinner" needs reworking
	 */

	@Test
	public void TestIsWinnerBlackFiveVerticalWin() {
		Board b = new Board(19, 19);
		char black = 'B';
		b.makeMove(black, 0, 0, false);
		b.makeMove(black, 1, 0, false);
		b.makeMove(black, 2, 0, false);
		b.makeMove(black, 3, 0, false);
		b.makeMove(black, 4, 0, false);
		assertTrue(b.isWinner(black, 4, 0));
	}

	@Test
	public void TestIsWinnerBlackFiveHerizontalWin() {
		Board b = new Board(19, 19);
		char black = 'B';
		b.makeMove(black, 0, 0, false);
		b.makeMove(black, 0, 1, false);
		b.makeMove(black, 0, 2, false);
		b.makeMove(black, 0, 3, false);
		b.makeMove(black, 0, 4, false);
		assertTrue(b.isWinner(black, 0, 4));
	}

	@Test
	public void TestIsWinnerBlackFiveDiaginalWin() {
		Board b = new Board(19, 19);
		char black = 'B';
		b.makeMove(black, 0, 0, false);
		b.makeMove(black, 1, 1, false);
		b.makeMove(black, 2, 2, false);
		b.makeMove(black, 3, 3, false);
		b.makeMove(black, 4, 4, false);
		assertTrue(b.isWinner(black, 4, 4));
	}

	@Test
	public void TestIsWinnerWhightFiveVerticalWin() {
		Board b = new Board(19, 19);
		char wight = 'B';
		b.makeMove(wight, 0, 0, false);
		b.makeMove(wight, 1, 0, false);
		b.makeMove(wight, 2, 0, false);
		b.makeMove(wight, 3, 0, false);
		b.makeMove(wight, 4, 0, false);
		assertTrue(b.isWinner(wight, 4, 0));
	}

	@Test
	public void TestIsWinnerWhightFiveHerizontalWin() {
		Board b = new Board(19, 19);
		char wight = 'B';
		b.makeMove(wight, 0, 0, false);
		b.makeMove(wight, 0, 1, false);
		b.makeMove(wight, 0, 2, false);
		b.makeMove(wight, 0, 3, false);
		b.makeMove(wight, 0, 4, false);
		assertTrue(b.isWinner(wight, 0, 4));
	}

	@Test
	public void TestIsWinnerWhightFiveDiaginalWin() {
		Board b = new Board(19, 19);
		char whight = 'W';
		b.makeMove(whight, 0, 0, false);
		b.makeMove(whight, 1, 1, false);
		b.makeMove(whight, 2, 2, false);
		b.makeMove(whight, 3, 3, false);
		b.makeMove(whight, 4, 4, false);
		assertTrue(b.isWinner(whight, 4, 4));
	}

	@Test
	public void TestIsNotWinnerRandomLocation() {
		Board b = new Board(19, 19);
		char black = 'B';
		b.makeMove(black, 0, 6, false);
		b.makeMove(black, 7, 8, false);
		b.makeMove(black, 3, 6, false);
		b.makeMove(black, 9, 3, false);
		b.makeMove(black, 1, 5, false);
		assertFalse(b.isWinner(black, 1, 5));
	}

	@Test
	public void TestIsNotWinner3Space2Vertical() {
		Board b = new Board(19, 19);
		char black = 'B';
		if(!b.makeMove(black, 0, 0, false)) {System.out.println("Fail");}
		if(!b.makeMove(black, 1, 0, false)) {System.out.println("Fail");}
		if(!b.makeMove(black, 2, 0, false)) {System.out.println("Fail");}
		if(!b.makeMove(black, 4, 0, false)) {System.out.println("Fail");}
		if(!b.makeMove(black, 5, 0, false)) {System.out.println("Fail");}
		assertFalse(b.isWinner(black, 5, 0));
	}

}
