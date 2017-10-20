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

@SuppressWarnings("restriction")
public class MainView extends Application {
	private double cellLength;
	private double cellWidth;
	private int length;
	private int width;
	private int intersections;
	private double cellsize;
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
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();
		start.setOnAction((event) -> {
			String c = inputColumn.getText();
			String r = inputRow.getText();
			boolean goodvalue = checkNumber(c, r);
			if (goodvalue) {
				mycanvas = new Canvas(500, 500);
				doCalculations(c, r, false);
				Scene temp = getTheGameStuff();
				primaryStage.setScene(temp);
				primaryStage.setMaximized(true);
			}
		});
		
//		dimLabel = new Label("Please enter the desired dimensions of the board");
//		insertColumn = new Label("Column");
//		inputColumn = new TextField();
//		insertRow = new Label("Row");
//		inputRow = new TextField();
//		start = new Button("Start");
//
//		startGrid = new GridPane();
//		badinput = new Label();
//		startGrid.add(dimLabel, 0, 0, 2, 1);
//		startGrid.add(insertColumn, 0, 1);
//		startGrid.add(inputColumn, 1, 1);
//		startGrid.add(insertRow, 0, 2);
//		startGrid.add(inputRow, 1, 2);
//		startGrid.add(start, 0, 3);
//		startGrid.add(badinput, 1, 3);
//		startGrid.setAlignment(Pos.CENTER);
//		startPane = new BorderPane();
//		startPane.setCenter(startGrid);
//		BorderPane.setAlignment(startGrid, Pos.CENTER);
//		Scene startScreen = new Scene(startPane, 500, 250);
//		primaryStage.setScene(startScreen);
//		primaryStage.setTitle("Pente");
//		primaryStage.setResizable(false);
//		primaryStage.show();
//
//		start.setOnAction((event) -> {
//			String c = inputColumn.getText();
//			String r = inputRow.getText();
//			length = 39 ;
//			width =39;	
//			boolean goodvalue = checkNumber(c, r);
//			if (goodvalue) {
//				Scene temp = getTheGameStuff();
//				primaryStage.setScene(temp);
//				primaryStage.setMaximized(true);
//			}
//		});
	}

	private boolean checkNumber(String c2, String r2) {
		int column, row;
		if (c2 != null && r2 != null) {
			char[] bufferC = c2.toCharArray();
			char[] bufferR = r2.toCharArray();
			for (int i = 0; i < bufferC.length; i++) {
				if (Character.isDigit(bufferC[i])) {
					column = Integer.parseInt(c2);
					if (column % 2 == 1) {
						if (column <= 39 && column >= 9) {
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
		mycanvas = CreateGrid(mycanvas, length, width, cellsize);
		columnLabel = new Label("Column");
		column = new TextField();
		rowLabel = new Label("Row");
		row = new TextField();
		submit = new Button();
		submit.setText("Submit");
		submit.setOnAction((event) -> {
			r = row.getText();
			c = column.getText();
			doCalculations(r, c, true);
		});
		gameHbox = new HBox();
		gameHbox.getChildren().addAll(columnLabel, column, rowLabel, row, submit);
		gameHbox.setAlignment(Pos.CENTER);
		gamePane = new BorderPane();
		gamePane.setCenter(mycanvas);
		gamePane.setBottom(gameHbox);
		Scene scene = new Scene(gamePane);
		return scene;
	}

	private void doCalculations(String r2, String c2, boolean piece) {
		int R = Integer.parseInt(r2);
		int C = Integer.parseInt(c2);
		length = R + 1;
		width = C + 1;
		intersections = (length - 1) * (width - 1);
		cellsize = (length * width) / intersections;
		cellLength = 500 / length;
		cellWidth = 500 / width;
		b = new Board(R, C);
		char p = 'B';
		if (times % 2 == 0) {
			p = 'W';
		}
		if (piece) {
			drawPiece(40, 40);
		}
	}

	private void drawPiece(double c2, double r2) {
		gc.setFill(Color.BLACK);
		gc.fillOval(c2, r2, cellsize, cellsize);

		times++;
	}

	private Canvas CreateGrid(Canvas mycanvas2, int width, int length, double cellsize) {
		Canvas can = mycanvas2;
		gc = can.getGraphicsContext2D();
		gc.setLineWidth(1.0);
		for (double i = 0; i < mycanvas.getWidth(); i += (mycanvas.getWidth() / width)) {
			gc.moveTo(i, 0);
			gc.lineTo(i, mycanvas.getWidth());
			gc.stroke();
		}
		for (double i = 0; i < mycanvas.getHeight(); i += (mycanvas.getHeight() / length)) {
			double y1;
			y1 = i;
			gc.moveTo(0, y1);
			gc.lineTo(mycanvas.getHeight(), y1);
			gc.stroke();
		}
		return can;
	}
}
