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
	private int dim = 500;
	private int intersections = 39;
	private int cellsize = dim/intersections;
	private int times = 0;
	private Board b;
	private BorderPane border;
	private HBox hbox;
	private Canvas mycanvas;
	private GraphicsContext gc;
	private Button submit;
	private Label columnLabel, rowLabel;
	private TextField column, row;
	private String r, c;
	private int l1 = dim;
	private int w1 = dim;

	@Override
	public void start(Stage primaryStage) throws Exception {
		mycanvas = new Canvas(l1, w1);

		
		CreateGrid(mycanvas, dim, dim, cellsize );

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

		hbox = new HBox();
		hbox.getChildren().addAll(columnLabel, column, rowLabel, row, submit);
		hbox.setAlignment(Pos.CENTER);
		border = new BorderPane();
		border.setCenter(mycanvas);
		border.setBottom(hbox);

		Scene scene = new Scene(border);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pente");
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();
		b = new Board(1000,1000);
	}

	private void doCalculations(String r2, String c2) {
		int R = Integer.parseInt(r2);
		int C = Integer.parseInt(c2);
		char p = 'B';
		if (times % 2 == 0) {
			p = 'W';
		}
		if (b.makeMove(p, C, R, false)) {
			drawPiece(((C-0.5)*(l1/intersections)), ((R-0.5)*(w1/intersections)));
		}
	}

	private void drawPiece(double c2, double r2) {
		if (times % 2 == 0) {
			gc.setFill(Color.BLACK);
			gc.fillOval(c2, r2, cellsize, cellsize);
		}
		else {
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
