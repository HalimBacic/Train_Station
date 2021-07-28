package CentralServer.Izvjestaj;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IzvjestajiControl implements Initializable {

    @FXML
    private TextField traziTF;

    @FXML
    private Button traziBtn;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox boxContainter;

    @FXML
    private Button izlazBtn;
    
    ArhivaInterface arhivaInterface;
    
    @FXML
    void izlazBtnClick(ActionEvent event) {

       	Stage stage = (Stage) izlazBtn.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void traziBtnClick(ActionEvent event) throws IOException {
    	
    	String searchtextString = traziTF.getText();
    	
    	boxContainter.getChildren().clear();
    	
    	ArrayList<String> list = arhivaInterface.getPanes(searchtextString);
    	
    	ArrayList<AnchorPane> panes = new ArrayList<AnchorPane>();
    	
    	for(int i = 0;i<list.size();i++) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("IzvjestajiItem.fxml"));
    		AnchorPane root = loader.load();
    		IzvjestajiItemControl izvjestajItemControl = loader.getController();
    		izvjestajItemControl.init(list.get(i), list.get(++i));
    		panes.add(root);
    	}
    	boxContainter.getChildren().addAll(panes);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.setProperty("java.security.policy","client_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "Arhiva";
			Registry registry = LocateRegistry.getRegistry(2000);
			arhivaInterface = (ArhivaInterface) registry.lookup(name);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			populateVBox();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void populateVBox() throws IOException {
		
    	boxContainter.getChildren().clear();
    	
    	ArrayList<String> list = arhivaInterface.getPanes();
    	
    	ArrayList<AnchorPane> panes = new ArrayList<AnchorPane>();
    	
    	for(int i = 0;i<list.size();i++) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("IzvjestajiItem.fxml"));
    		AnchorPane root = loader.load();
    		IzvjestajiItemControl izvjestajItemControl = loader.getController();
    		izvjestajItemControl.init(list.get(i), list.get(++i));
    		panes.add(root);
    	}
    	boxContainter.getChildren().addAll(panes);
		
	}
}
