package Arhiva;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import items.Korisnik;
import items.MdpUtility;

public class Arhiva implements ArhivaInterface {

	public Arhiva() throws RemoteException
	{}
	
	@Override
	public void upload(Korisnik korisnik, String text) {
		
		archive(korisnik, text.length());
		
		//Stvoriti PDF
		try {
			Properties properties = MdpUtility.getProperties();
			String pdfPath = properties.getProperty("arhivapdf");
			String userPdf = pdfPath + korisnik.getUsername()+".pdf";
			PDDocument pdfDocument = new PDDocument();
			PDPage page = new PDPage();
			pdfDocument.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
			contentStream.newLineAtOffset(20, 750);
			contentStream.showText(text);
			contentStream.endText();
			contentStream.close();
			pdfDocument.save(userPdf);
			pdfDocument.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Folder je npr 
	@Override
	public void open(String name) {
		Properties properties = MdpUtility.getProperties();
		String folder =  properties.getProperty("korisniciizvjestaji")+name+".pdf";
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(folder);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		        // no application registered for PDFs
		    }
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void archive(Korisnik korisnik,Integer size) {
		//Napraviti odgovarajući JSON
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", korisnik.getUsername());
		jsonObject.put("time", LocalDateTime.now());
		jsonObject.put("size",size.toString()+"b");
		
		
		//Dodati JSON u file
		Properties properties = MdpUtility.getProperties();
		String jsonPath = properties.getProperty("arhivajson");
		try {
			// jsonPath + korisnik.getUsername()+".json"
			String path = jsonPath + korisnik.getUsername()+".json";
			PrintWriter printWriter = new PrintWriter(new FileWriter(new File(path)));
			printWriter.write(jsonObject.toJSONString());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void download(String name) throws RemoteException {
		Properties properties = MdpUtility.getProperties();
		String pdfcopy = properties.getProperty("arhivapdf")+name+".pdf";
		String targetString = properties.getProperty("korisniciizvjestaji")+name+".pdf";

		File fileSourceFile = new File(pdfcopy);
		File fileTargetFile = new File(targetString);
		try {
			Files.copy(fileSourceFile.toPath(), fileTargetFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> getPanes() throws RemoteException {
		ArrayList<String> strings = new ArrayList<String>();
		
		Properties properties = MdpUtility.getProperties();
		File directoryFile = new File(properties.getProperty("arhivajson"));
		
		File[] files = directoryFile.listFiles();
		for (File file : files) {
	        // parsing file "JSONExample.json"
	        Object obj;
			try {
				obj = new JSONParser().parse(new FileReader(file));
		          
		        // typecasting obj to JSONObject
		        JSONObject jo = (JSONObject) obj;
		          
		        // getting username and time
		        String username = (String) jo.get("username");
		        String time = (String) jo.get("time");
		        
			/*	FXMLLoader loader = new FXMLLoader(getClass().getResource("IzvjestajiItem.fxml"));
				AnchorPane root = loader.load();
				IzvjestajiItemControl izvjestajItemControl = loader.getController();
				izvjestajItemControl.init(username, time); */
				strings.add(username);
				strings.add(time);
		        
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return strings;
	}
	
	@Override
	public ArrayList<String> getPanes(String str) throws RemoteException {
		ArrayList<String> strings = new ArrayList<String>();
		
		Properties properties = MdpUtility.getProperties();
		File directoryFile = new File(properties.getProperty("arhivajson"));
		
		File[] files = directoryFile.listFiles();
		for (File file : files) {
	        // parsing file "JSONExample.json"
			if(file.getName().contains(str)) {
	        Object obj;
			try {
				obj = new JSONParser().parse(new FileReader(file));
		          
		        // typecasting obj to JSONObject
		        JSONObject jo = (JSONObject) obj;
		          
		        // getting username and time
		        String username = (String) jo.get("username");
		        String time = (String) jo.get("time");
		        
		        strings.add(username);
		        strings.add(time);
		        
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
		return strings;
	}

	@Override
	public void deleteFromArchive(String name) throws RemoteException {
		Properties properties = MdpUtility.getProperties();
		String jsonPath = properties.getProperty("arhivajson");
		String pdfPath = properties.getProperty("arhivapdf");
		
		File file = new File(jsonPath+name+".json");
		file.delete();
		
		file = new File(pdfPath+name+".pdf");
		file.delete();
	}
	
	public static void main(String[] args)
	{
		System.setProperty("java.security.policy", "server_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Arhiva server = new Arhiva();
			ArhivaInterface stub = (ArhivaInterface) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(2000);
			registry.rebind("Arhiva", stub);
			System.out.println("Server started.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
