package Operater;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Operater extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage stage) throws IOException {		
	        Parent root = FXMLLoader.load(getClass().getResource("HomeOperater.fxml"));   
	        Scene scene = new Scene(root, 971, 586);
	        stage.setTitle("ZSMDP");
	        stage.setScene(scene);
	        stage.show();
	}
}
