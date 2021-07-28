package CentralServer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CentralServerControl implements Initializable {

    @FXML
    private Button korBtn;

    @FXML
    private Button redvBtn;

    @FXML
    private Button izvjBtn;

    @FXML
    private Button izlBtn;
    
    private CentralServer centralServer;
    
    
    public CentralServerControl()
    {
    }
    

    @FXML
    void izlBtnClick(ActionEvent event) {
    	Stage stage = (Stage) izlBtn.getScene().getWindow();
    	CentralServer.setWorking(false);
    	stage.close();
    }

    @FXML
    void izvjBtnClick(ActionEvent event) {
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Izvjestaj/IzvjestajiView.fxml"));
	        Scene scene = new Scene(root, 408, 475);
	        Stage stage = new Stage();
	        stage.setTitle("CZS-MDP");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void korBtnClick(ActionEvent event) {
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Korisnici/KorisniciView.fxml"));
	        Scene scene = new Scene(root, 510, 420);
	        Stage stage = new Stage();
	        stage.setTitle("CZS-MDP");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }

    @FXML
    void redvBtnClick(ActionEvent event) {
    	
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Linija/LinijaView.fxml"));
	        Scene scene = new Scene(root, 716, 404);
	        Stage stage = new Stage();
	        stage.setTitle("CZS-MDP");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }


	public void initialize(URL arg0, ResourceBundle arg1) {
    	System.out.println("I am here");
    	Thread thread = new Thread(new Runnable() {
			
			public void run() {
				CentralServer.StartCentralServer();
				
			}
		});
    	thread.start();
		
	}
    
}
