package CentralServer.Linija;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Items.Linija;
import Items.Stanica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class LinijaControl implements Initializable {

    @FXML
    private TextField traziTF;

    @FXML
    private Button dodajLinijuBtn;

    @FXML
    private MenuButton startCombo;

    @FXML
    private MenuButton medjuCombo;

    @FXML
    private MenuButton stopCombo;

    @FXML
    private Button traziBtn;

    @FXML
    private Button dodajStanicuBtn;

    @FXML
    private TableView<Linija> tableViewLinija;

    @FXML
    private TableColumn<Linija, Integer> idCol;

    @FXML
    private TableColumn<Linija, String> startCol;

    @FXML
    private TableColumn<Linija, String> medjuCol;

    @FXML
    private TableColumn<Linija, String> stopCol;
    
    private ArrayList<Linija> listaLinija;
    private ArrayList<Stanica> listaStanica;

    @FXML
    void dodajLinijuBtnClick(ActionEvent event) {
    		
    	Linija linija = new Linija(startCombo.getText(), stopCombo.getText(), traziTF.getText());
    	try {
			linija.addObject();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	clearInformation();
    	refreshLinije();
    }

    @FXML
    private Button brisiBtn;
    
    @FXML
    void dodajStanicuBtnClick(ActionEvent event) {
    	
    	String stanicaString = traziTF.getText();
    	Stanica stanica = new Stanica(stanicaString);
    	try {
			stanica.addObject();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	clearInformation();
    	refreshStanice();
    }

    @FXML
    void traziBtnClick(ActionEvent event) {

    }
    
    @FXML
    void dodajStanicuAktiviraj(MouseEvent event) {
		if(checkerDodajStanicu())
			dodajStanicuBtn.setDisable(false);
    }
    
    @FXML
    void dodajLinijuAktiviraj() {
    	if(checkerDodajLiniju())
    		dodajLinijuBtn.setDisable(false);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		clearInformation();
		clearTable();
		
		try {
			listaLinija = new Linija().getAll();
			listaStanica = new Stanica().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Stanica stanica : listaStanica) {
			MenuItem firstItem = new MenuItem(stanica.getIme());
			firstItem.setOnAction(event->{
				startCombo.setText(firstItem.getText());
				dodajLinijuAktiviraj();
			});
			startCombo.getItems().add(firstItem);
			
			MenuItem middleItem = new MenuItem(stanica.getIme());
			middleItem.setOnAction(event->{
				String medjuStanicaString = middleItem.getText();
				String currentString = traziTF.getText();
				currentString+=" "+medjuStanicaString;
				medjuCombo.setText(medjuStanicaString);
				traziTF.setText(currentString);
				dodajLinijuAktiviraj();
			});
			medjuCombo.getItems().add(middleItem);
			
			MenuItem lastItem = new MenuItem(stanica.getIme());
			lastItem.setOnAction(event->{
				stopCombo.setText(lastItem.getText());
				dodajLinijuAktiviraj();
			});
 			stopCombo.getItems().add(lastItem);
		}
		
			
        idCol.setCellValueFactory(new PropertyValueFactory<Linija, Integer>("id"));
        startCol.setCellValueFactory(new PropertyValueFactory<Linija, String>("startStanica"));
        medjuCol.setCellValueFactory(new PropertyValueFactory<Linija, String>("stanke"));
        stopCol.setCellValueFactory(new PropertyValueFactory<Linija, String>("stopStanica"));
        
		ObservableList<Linija> dataLinija = FXCollections.observableArrayList(listaLinija);
		tableViewLinija.setItems(dataLinija);
	}
	
	private void refreshStanice()
	{
		clearInformation();
		try {
			listaStanica = new Stanica().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Stanica stanica : listaStanica) {
			MenuItem firstItem = new MenuItem(stanica.getIme());
			firstItem.setOnAction(event->{
				startCombo.setText(firstItem.getText());
				dodajLinijuAktiviraj();
			});
			startCombo.getItems().add(firstItem);
			
			MenuItem middleItem = new MenuItem(stanica.getIme());
			middleItem.setOnAction(event->{
				String medjuStanicaString = middleItem.getText();
				String currentString = traziTF.getText();
				currentString+=" "+medjuStanicaString;
				traziTF.setText(currentString);
				medjuCombo.setText(medjuStanicaString);
				dodajLinijuAktiviraj();
			});
			medjuCombo.getItems().add(middleItem);
			
			MenuItem lastItem = new MenuItem(stanica.getIme());
			lastItem.setOnAction(event->{
				dodajLinijuAktiviraj();
				stopCombo.setText(lastItem.getText());
				dodajLinijuAktiviraj();
			});
 			stopCombo.getItems().add(lastItem);
		}
	}
	
	private void refreshLinije()
	{
		clearTable();
		
		try {
			listaLinija = new Linija().getAll();
			
	        idCol.setCellValueFactory(new PropertyValueFactory<Linija, Integer>("id"));
	        startCol.setCellValueFactory(new PropertyValueFactory<Linija, String>("startStanica"));
	        medjuCol.setCellValueFactory(new PropertyValueFactory<Linija, String>("stanke"));
	        stopCol.setCellValueFactory(new PropertyValueFactory<Linija, String>("stopStanica"));
	        
			ObservableList<Linija> dataLinija = FXCollections.observableArrayList(listaLinija);
			tableViewLinija.setItems(dataLinija);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clearInformation()
	{
		dodajLinijuBtn.setDisable(true);
		dodajLinijuBtn.setDisable(true);
		traziTF.setText("");
		startCombo.getItems().clear();
		medjuCombo.getItems().clear();
		stopCombo.getItems().clear();
		startCombo.setText("Početna stanica");
		medjuCombo.setText("Međustanice");
		stopCombo.setText("Zadnja stanica");
	}
	
	private void clearTable()
	{
		tableViewLinija.getItems().clear();
	}
	
	private boolean checkerDodajLiniju()
	{
		if(!startCombo.getText().equals("Početna stanica") && !medjuCombo.getText().equals("Međustanice")
				&& !stopCombo.getText().equals("Zadnja stanica"))
			return true;
		else {
			return false;
		}
	}
	
	private boolean checkerDodajStanicu()
	{
		String novaStanica = traziTF.getText();
		if(!checkerDodajLiniju() && !novaStanica.equals(""))
			return true;
		else {
			return false;
		}
	}
	
    @FXML
    void brisiBtnClick(ActionEvent event) {

    	Linija linija = tableViewLinija.getSelectionModel().getSelectedItem();
    	try {
			linija.removeObject(linija.getId().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	refreshLinije();
    	
    }
}
