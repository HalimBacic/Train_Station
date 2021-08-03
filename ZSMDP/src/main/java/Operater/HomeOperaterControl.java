package Operater;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Items.Stanica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeOperaterControl implements Initializable {

    @FXML
    private Button obavjestenjeBtn;

    @FXML
    private Button rasporedBtn;

    @FXML
    private Button izlazBtn;

    @FXML
    private MenuButton staniceCombo;

    @FXML
    private VBox chatContainer;

    @FXML
    private TabPane chatTab;

    @FXML
    void izlazBtnClick(ActionEvent event) {
    	Stage stage = (Stage) izlazBtn.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void obavjestenjeBtnClick(ActionEvent event) {

    }

    @FXML
    void rasporedBtnClick(ActionEvent event) {
    	
    	Stage stage = new Stage();
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Raspored.fxml"));
	        Scene scene = new Scene(root, 712, 420);
	        stage.setTitle("CZS-MDP");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }

    @FXML
    void staniceComboClick(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		chatContainer.setSpacing(5);
		
		try {
			initializeComboBox();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initializeComboBox() throws SQLException {
		
		staniceCombo.getItems().clear();
		Stanica stanica = new Stanica();
		
		ArrayList<Stanica> staniceArrayList = stanica.getAll();
		
		for (Stanica stn : staniceArrayList) {
			MenuItem menuItem = new MenuItem(stn.getIme());
			menuItem.setOnAction(event->{
				try {
					initializeActiveOperaters(stn.getIme());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			staniceCombo.getItems().add(menuItem);
		}
	}

	private void initializeActiveOperaters(String ime) throws SQLException {
		chatContainer.getChildren().clear();
		//TODO Pozvati proceduru koja ispisuje aktivne operatere
		String stmtString = new String("call findOperaterStanica(?)");
		Items.Connection connection = Items.Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, ime);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next())
		{
			Button msgButton = new Button(resultSet.getString(1));
			chatContainer.getChildren().add(msgButton);
		}
	}
}
