package Items;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MdpUtility {
	
	public static void ShowInformation(String msg)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informacija");
		alert.setHeaderText(null);
		alert.setContentText("Nema rezultata pretrage.");
		alert.showAndWait();
	}
	
	public static Properties getProperties()
	{
		Properties properties = new Properties();
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("resources/config.properties");
			properties.load(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public static void ShowAlert(String msgType,String msg)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(msgType);
		alert.setContentText(msg);
		alert.showAndWait();
	}

}