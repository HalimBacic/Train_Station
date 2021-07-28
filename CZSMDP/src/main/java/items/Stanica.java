package items;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Stanica implements Zsdao<Stanica> {
	
	private String ime;
	
	public Stanica()
	{}

	public Stanica(String ime) {
		super();
		this.ime = ime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void addObject() throws SQLException {
		String stmtString = "{ call dodajStanicu(?) }";
		
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, getIme());
		statement.execute();
		statement.close();		
	}

	public void removeObject(String key) throws SQLException {
		String stmtString = "{ call brisiStanicu(?) }";
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, key);
		statement.execute();
		statement.close();	
	}

	public Stanica getObject(String key) throws SQLException {
		
		return new Stanica(key);
	}

	public ArrayList<Stanica> getAll() throws SQLException {
		ArrayList<Stanica> list = new ArrayList<Stanica>();
		
		String stmtString = "select * from `stanica`";
		Connection connection = Connection.getInstance();
		ResultSet resultSet = connection.getResultSet(stmtString);
		
		while(resultSet.next())
		{
			list.add(new Stanica(resultSet.getString(1)));
		}
		
		return list;
	}
	
	
	

}
