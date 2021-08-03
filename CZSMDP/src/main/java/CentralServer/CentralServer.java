package CentralServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;
import Items.Connection;
import Items.Korisnik;
import Items.Linija;
import Items.MdpUtility;
import Items.Stanica;

public class CentralServer {
	private static boolean isWorking = true;
	
	private Korisnik korisnik;
	private Linija linija;
	private Stanica stanica;
	private Connection connection;
	
	public CentralServer()
	{}
	
	public static void StartCentralServer()
	{
		Properties properties = MdpUtility.getProperties();
		ServerSocket serverSocket;
		
		try {
			
			serverSocket = new ServerSocket(Integer.parseInt(properties.getProperty("serverport")));
			
			while(isWorking)
			{
				serverSocket.accept();
				System.out.println("Working...");
				//TODO Obrada zahtjeva
			}
			serverSocket.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	public static boolean isWorking() {
		return isWorking;
	}

	public static void setWorking(boolean isWorking) {
		CentralServer.isWorking = isWorking;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
	}

	public Stanica getStanica() {
		return stanica;
	}

	public void setStanica(Stanica stanica) {
		this.stanica = stanica;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
