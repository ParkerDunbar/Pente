package group.csc280.pente.view;

import java.awt.Event;

import group.csc280.pente.controller.Pente;
import group.csc280.pente.model.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MainView extends Application {
	private int length;
	private int width;
	private int intersections = 39;
	private int cellsize = (length * width) / intersections;
	private int times = 0;
	private Board b;
	private BorderPane startPane, gamePane;
	private GridPane startGrid;
	private HBox gameHbox;
	private Canvas mycanvas;
	private GraphicsContext gc;
	private Button start, submit;
	private Label columnLabel, rowLabel, dimLabel, insertColumn, insertRow, badinput;
	private TextField column, row, inputColumn, inputRow;
	private String r, c;

	@Override
	public void start(Stage primaryStage) throws Exception {
		dimLabel = new Label("Please enter the desired dimensions of the board");
		insertColumn = new Label("Column");
		inputColumn = new TextField();
		insertRow = new Label("Row");
		inputRow = new TextField();
		start = new Button("Start");

		startGrid = new GridPane();
		badinput = new Label();
		startGrid.add(dimLabel, 0, 0, 2, 1);
		startGrid.add(insertColumn, 0, 1);
		startGrid.add(inputColumn, 1, 1);
		startGrid.add(insertRow, 0, 2);
		startGrid.add(inputRow, 1, 2);
		startGrid.add(start, 0, 3);
		startGrid.add(badinput, 1, 3);
		startGrid.setAlignment(Pos.CENTER);
		startPane = new BorderPane();
		startPane.setCenter(startGrid);
		BorderPane.setAlignment(startGrid, Pos.CENTER);
		Scene startScreen = new Scene(startPane, 500, 250);
		primaryStage.setScene(startScreen);
		primaryStage.setTitle("Pente");
		primaryStage.setResizable(false);
		primaryStage.show();

		start.setOnAction((event) -> {
			String c = inputColumn.getText();
			String r = inputRow.getText();
			length = 39 ;
			width =39;	
			boolean goodvalue = checkNumber(c, r);
			if (goodvalue) {
				Scene temp = getTheGameStuff();
				primaryStage.setScene(temp);
				primaryStage.setMaximized(true);
			}
		});
	}

	private boolean checkNumber(String c2, String r2) {
		int column, row;
		System.out.println(c2 + r2);
		if (c2 != null && r2 != null) {
			System.out.println("here2");
			char[] bufferC = c2.toCharArray();
			char[] bufferR = r2.toCharArray();
			for (int i = 0; i < bufferC.length; i++) {
				if (Character.isDigit(bufferC[i])) {
					System.out.println("here3");
					column = Integer.parseInt(c2);
					if (column % 2 == 1) {
						if (column <= 39 && column >= 9) {
							System.out.println("here4");
							length = column;
							return true;
						} else {
							badinput.setText("Both column and row need to be between 9 and 39.");
							return false;
						}

					} else {
						badinput.setText("Both column and row need to be an odd number.");
						return false;
					}
				}
			}
			for (int i = 0; i < bufferR.length; i++) {
				if (Character.isDigit(bufferR[i])) {
					row = Integer.parseInt(r2);
					if (row % 2 == 1) {
						if (row <= 39 && row >= 9) {
							width = row;
							return true;
						} else {
							badinput.setText("Both column and row need to be between 9 and 39.");
						}

					} else {
						badinput.setText("Both column and row need to be an odd number.");
						return false;
					}
				}
			}
		} else {
			badinput.setTextFill(Color.RED);
			badinput.setText("Bad Input Please Try Again");
			return false;
		}
		badinput.setTextFill(Color.RED);
		badinput.setText("Bad Input Please Try Again");
		return false;
	}

	private Scene getTheGameStuff() {
		mycanvas = new Canvas(length, width);
		CreateGrid(mycanvas, length, width, cellsize);
		columnLabel = new Label("Column");
		column = new TextField();
		rowLabel = new Label("Row");
		row = new TextField();
		submit = new Button();
		submit.setText("Submit");
		submit.setOnAction((event) -> {
			r = row.getText();
			c = column.getText();
			doCalculations(r, c);
		});
		gameHbox = new HBox();
		gameHbox.getChildren().addAll(columnLabel, column, rowLabel, row, submit);
		gameHbox.setAlignment(Pos.CENTER);
		gamePane = new BorderPane();
		gamePane.setCenter(mycanvas);
		gamePane.setBottom(gameHbox);
		Scene scene = new Scene(gamePane);
		b = new Board(1000, 1000);
		return scene;
	}

	private void doCalculations(String r2, String c2) {
		int R = Integer.parseInt(r2);
		int C = Integer.parseInt(c2);
		char p = 'B';
		if (times % 2 == 0) {
			p = 'W';
		}
		if (b.makeMove(p, C, R, false)) {
			drawPiece(((C - 0.5) * (length / intersections)), ((R - 0.5) * (width / intersections)));
		}
	}

	private void drawPiece(double c2, double r2) {
		if (times % 2 == 0) {
			gc.setFill(Color.BLACK);
			gc.fillOval(c2, r2, cellsize, cellsize);
		} else {
			gc.setFill(Color.GRAY);
			gc.fillOval(c2, r2, cellsize, cellsize);
		}
		times++;
	}

	private Canvas CreateGrid(Canvas mycanvas2, int width, int height, int cellsize) {
		gc = mycanvas2.getGraphicsContext2D();
		gc.setLineWidth(1.0);
		for (int i = 0; i <= width; i += cellsize) {
			double x1;
			x1 = i;
			gc.moveTo(x1, 0);
			gc.lineTo(x1, height);
			gc.stroke();
		}
		for (int i = 0; i <= height; i += cellsize) {
			double y1;
			y1 = i;
			gc.moveTo(0, y1);
			gc.lineTo(width, y1);
			gc.stroke();
		}

		return mycanvas2;
	}
}
