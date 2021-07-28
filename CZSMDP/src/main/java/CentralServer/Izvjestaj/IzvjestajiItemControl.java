package CentralServer.Izvjestaj;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class IzvjestajiItemControl implements Initializable{

    @FXML
    private Text usernameText;

    @FXML
    private Text dateText;

    @FXML
    private Button downloadBtn;

    @FXML
    private Button obrisiBtn;
    
    ArhivaInterface arhivaInterface;

    @FXML
    void downloadBtnClick(ActionEvent event) {
    	try {
			arhivaInterface.download(usernameText.getText());
			arhivaInterface.open(usernameText.getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void obrisiBtnClick(ActionEvent event) {
    	try {
			arhivaInterface.deleteFromArchive(usernameText.getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.setProperty("java.security.policy","client_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(2000);
			arhivaInterface = (ArhivaInterface) registry.lookup("Arhiva");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(String username, String dateString)
	{
		usernameText.setText(username);
		dateText.setText(dateString);
	}
	
	public boolean checkFind(String str)
	{
		if(usernameText.getText().contains(str))
			return true;
		else {
			return false;
		}
	}
}
