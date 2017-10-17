package group.csc280.pente.controller;

import group.csc280.pente.model.Board;
import group.csc280.pente.view.MainWindow;


public class Run {

	public static void main(String[] args) {
		MainWindow.launch(MainWindow.class);

		Board b = new Board();
		b.printBoard();

	}

}
