package Operater;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.xml.rpc.ServiceException;
import Items.MdpUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControl implements Initializable {

    @FXML
    private TextField usrnm;

    @FXML
    private TextField pass;

    @FXML
    private Button loginBtn;

    @FXML
    void loginBtnClick(ActionEvent event) {

    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}