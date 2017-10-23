package group.csc280.pente.junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import group.csc280.pente.model.Board;

public class MakeMoveTests {

	@Test
	public void makeMoveTestGoodInput() {
		Board b = new Board(19, 19);
		int x = 1;
		int y = 7;
		char turn = 'T';
		boolean isBlackSecMove = false;
		assertTrue(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test // add a check if out of bounds
	public void makeMoveTestbadInput() {
		Board b = new Board(19, 19);
		int x = -9;
		int y = 99;
		char turn = 'T';
		boolean isBlackSecMove = false;
		assertFalse(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestMinInput() {
		Board b = new Board(19, 19);
		int x = 0;
		int y = 0;
		char turn = 'T';
		boolean isBlackSecMove = false;
		assertTrue(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestMaxInput() {
		Board b = new Board(19, 19);
		int x = 18;
		int y = 18;
		char turn = 'T';
		boolean isBlackSecMove = false;
		assertTrue(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestGoodInputForBlackSecMove() {
		Board b = new Board(19, 19);
		int x = 12;
		int y = 15;
		char turn = 'T';
		boolean isBlackSecMove = true;
		assertTrue(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestBadInputForBlackSecMove() {
		Board b = new Board(19, 19);
		int x = 7;
		int y = 9;
		char turn = 'T';
		boolean isBlackSecMove = true;
		assertFalse(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestMinInputForBlackSecMove() {
		Board b = new Board(19, 19);
		int x = 0;
		int y = 0;
		char turn = 'T';
		boolean isBlackSecMove = true;
		assertTrue(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestGoodMaxForBlackSecMove() {
		Board b = new Board(19, 19);
		int x = 18;
		int y = 18;
		char turn = 'T';
		boolean isBlackSecMove = true;
		assertTrue(b.makeMove(turn, x, y, isBlackSecMove));
	}

	@Test
	public void makeMoveTestInsideSquInputForBlackSecMove() {
		Board b = new Board(19, 19);
		int x = 7;
		int y = 7;
		char turn = 'T';
		boolean isBlackSecMove = true;
		assertFalse(b.makeMove(turn, x, y, isBlackSecMove));
	}

}