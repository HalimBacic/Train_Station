package Arhiva;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import items.Korisnik;
import javafx.scene.layout.AnchorPane;

public interface ArhivaInterface extends Remote{
	
	public void upload(Korisnik korisnik, String text) throws RemoteException;
	public void open(String name) throws RemoteException;
	public void download(String name) throws RemoteException;
	public void archive(Korisnik korisnik,Integer size) throws RemoteException;
	public ArrayList<String> getPanes() throws RemoteException;
	public ArrayList<String> getPanes(String str) throws RemoteException;
	public void deleteFromArchive(String name) throws RemoteException;
}
