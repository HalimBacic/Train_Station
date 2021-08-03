package CentralServer.Korisnici;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import Items.Connection;
import Items.Korisnik;
import Items.MdpUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KorisniciController implements Initializable {
	
    @FXML
    private TextArea korimeTF;

    @FXML
    private TextArea passTF;

    @FXML
    private ComboBox<String> stanicaBox;

    @FXML
    private Button dodajBtn;

    @FXML
    private Button traziBtn;

    @FXML
    private VBox vbox;

    @FXML
    private Button izlazBtn;

    @FXML
    void dodajBtnAction(ActionEvent event) {
    	
    	Properties properties = MdpUtility.getProperties();
    	String xmlPath = properties.getProperty("korisnicixml");
    	
    	
    	String usernameString = korimeTF.getText();
    	String passString = getHash(passTF.getText());
    	String tipString = "Radnik";
    	String stanicaString = stanicaBox.getValue();
    	
    	Korisnik korisnik = new Korisnik(usernameString, passString, tipString, stanicaString);
    	try {
			korisnik.addObject();
			
			Serializer serializer = new Persister();
			File file = new File(xmlPath+usernameString+".xml");
			serializer.write(korisnik, file);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void izlazBtnClick(ActionEvent event) {

    	Stage stage = (Stage) izlazBtn.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void traziBtnAction(ActionEvent event) throws SQLException {
    	
    	String filterString;
    	ArrayList<Korisnik> korisniciNaStanici = new ArrayList<Korisnik>();
    	Korisnik korisnik = new Korisnik();
    	if(korimeTF.getText().equals("")) {
    		filterString = stanicaBox.getValue();
    		/*Functuon whick I must change*/
    		korisniciNaStanici = korisnik.getFilter(filterString);
    	}
    	else {
			korisnik = korisnik.getObject(korimeTF.getText());
			
			if(korisnik!=null)
				korisniciNaStanici.add(korisnik);
		}
    	
    	try {
			ShowInformation(korisniciNaStanici);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private void ShowInformation(ArrayList<Korisnik> korisniciNaStanici) throws IOException {
		
		if(korisniciNaStanici.size()>0)
		for (Korisnik korisnik : korisniciNaStanici) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KorisnikItem.fxml"));
			Parent root = loader.load();
			KorisnikItemControl korisnikItemControl = loader.getController();
			korisnikItemControl.init(korisnik.getUsername(), korisnik.getStanica());
			vbox.getChildren().add(root);
		}
		else {
			MdpUtility.ShowInformation("Nema rezultata pretrage");
		}
		
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		File folder = new File("Korisnici_xml");
		if(!folder.exists())
			folder.mkdir();
		
		stanicaBox.getItems().clear();
		ArrayList<String> staniceArrayList;
		try {
			staniceArrayList = allStanice();
			for (String stanica : staniceArrayList) {
				stanicaBox.getItems().addAll(stanica);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private ArrayList<String> allStanice() throws SQLException
	{
		ArrayList<String> stanice = new ArrayList<String>();
		Connection connection = Connection.getInstance();
		String stmtString = "select * from `stanica`";
		ResultSet resultSet = connection.getResultSet(stmtString);
		while(resultSet.next())
		{
			stanice.add(resultSet.getString(1));
		}
		return stanice;
	}

	private String getHash(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
  
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
  
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
  
            // return the HashText
            return hashtext;
        }
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
		
	}

}
