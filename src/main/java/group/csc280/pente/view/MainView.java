package group.csc280.pente.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainView extends Application {

	int times = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Pente");
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	public void buttonHandler(ActionEvent e) {
		if (times % 2 == 0) {
			Button test = (Button) e.getSource();
			test.setStyle("-fx-background-color: #FFF");
		} else {
			Button test = (Button) e.getSource();
			test.setStyle("-fx-background-color: #000");
		}
		times++;

	}

}

