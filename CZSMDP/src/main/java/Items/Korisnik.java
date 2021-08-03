package Items;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Korisnik implements Zsdao<Korisnik> {

	@Element
	private String username;
	@Element
	private String password;
	@Element
	private String tip;
	@Element
	private String stanica;
	
	public Korisnik()
	{};
	
	public Korisnik(String username, String password, String tip, String stanica) {
		super();
		this.username = username;
		this.password = password;
		this.tip = tip;
		this.stanica = stanica;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}


	public String getStanica() {
		return stanica;
	}


	public void setStanica(String stanica) {
		this.stanica = stanica;
	}



	/*Add object to database*/
	public void addObject() throws SQLException {
		
		String stmtString = "{ call dodajKorisnika(?,?,?,?) }";
		
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, getUsername());
		statement.setString(2, getPassword());
		statement.setString(3, getTip());
		statement.setString(4, getStanica());
		statement.execute();
		statement.close();
	}




	public void removeObject(String key) throws SQLException {
		
		String stmtString = "{ call brisiKorisnika(?) }";
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, key);
		statement.execute();
		statement.close();
		
	}

	public Korisnik getObject(String key) throws SQLException {
		
		String stmtString = "{ call findKorisnik(?) }";
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, key);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
		 String usernameString = resultSet.getString("username");
		 String passwordString = resultSet.getString("lozinka");
		 String tipString = resultSet.getString("tip");
		 String stanicaString = resultSet.getString("stanica_ime");

		 return new Korisnik(usernameString, passwordString, tipString, stanicaString);
		}		
		
		//TODO Srediti ovo
		return null;
	}

	public ArrayList<Korisnik> getAll() throws SQLException {
		
		ArrayList<Korisnik> list = new ArrayList<Korisnik>();
		
		String stmtString = "select * from `korisnik`";
		Connection connection = Connection.getInstance();
		ResultSet resultSet = connection.getResultSet(stmtString);
		
		while(resultSet.next())
		{
			list.add(new Korisnik(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
		}
		
		return list;
	}
	
	public ArrayList<Korisnik> getFilter(String stanica) throws SQLException {
		
		ArrayList<Korisnik> list = new ArrayList<Korisnik>();
		
		String stmtString = "select * from `korisnik` where stanica_ime=\""+stanica+"\"";
		Connection connection = Connection.getInstance();
		ResultSet resultSet = connection.getResultSet(stmtString);
		
		while(resultSet.next())
		{
			list.add(new Korisnik(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
		}
		
		return list;
	}
}
