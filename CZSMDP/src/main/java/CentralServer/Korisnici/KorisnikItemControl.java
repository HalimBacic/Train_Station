package CentralServer.Korisnici;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Items.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class KorisnikItemControl implements Initializable{

    @FXML
    private TextField imeTF;

    @FXML
    private TextField stanicaTF;

    @FXML
    private Button brisiBtn;

    @FXML
    private Button izvjBtn;

    @FXML
    void brisiBtnClick(ActionEvent event) throws SQLException {
    	
    	Korisnik korisnik = new Korisnik();
    	korisnik.removeObject(imeTF.getText());
    	
    }

    @FXML
    void izvjBtnClick(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	
		
	}
	
	public void init(String name,String stanica)
	{
		imeTF.setText(name);
		stanicaTF.setText(stanica);
	}

}