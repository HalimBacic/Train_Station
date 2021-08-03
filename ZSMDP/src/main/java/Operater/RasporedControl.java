package Operater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import Items.Linija;
import Items.MdpUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RasporedControl implements Initializable{

    @FXML
    private TableView<Linija> linijaTable;

    @FXML
    private TableColumn<Linija, Integer> idColumn;

    @FXML
    private TableColumn<Linija, String> startColumn;

    @FXML
    private TableColumn<Linija, String> stankaColumn;

    @FXML
    private TableColumn<Linija, String> stopColumn;

    @FXML
    private TableView<Linija> detailLinijaTable;

    @FXML
    private TableColumn<Linija, String> stankaColumnDetail;

    @FXML
    private TableColumn<Linija, LocalTime> vrijemeColumn;
    
    private String URLBASE = MdpUtility.getProperties().getProperty("localpath");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
	        URL url = new URL(URLBASE);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while((line=reader.readLine())!=null)
	        	System.out.println(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

