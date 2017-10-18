package group.csc280.pente.view;

import group.csc280.pente.controller.Pente;
import group.csc280.pente.model.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainView extends Application {
	private Board b;
	int times = 0;

	@FXML
	public GridPane grid;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Pente");
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();
		b = new Board();
	}

	@FXML
	public void buttonHandler(ActionEvent e) {
		if (times % 2 == 0) {
			Button test = (Button) e.getSource();
			int Y = GridPane.getColumnIndex(test);
			int X = GridPane.getRowIndex(test);
//			System.out.println(X + " " + Y);
//			if (b.makeMove('B', X, Y)) {
				test.setStyle("-fx-background-color: #000");
//				b.checkCapture('B', 'W', X, Y);
//			}
		} else {
			Button test = (Button) e.getSource();
			int Y = GridPane.getColumnIndex(test);
			int X = GridPane.getRowIndex(test);
//			if (b.makeMove('W', X, Y)) {
				test.setStyle("-fx-background-color: #FFF");
//				b.checkCapture('W', 'B', X, Y);
//			}
		}
		times++;

	}

}
