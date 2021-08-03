package Items;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Linija implements Zsdao<Linija> {
	
	private Integer id = 0;
	private String startStanica;
	private String stopStanica;
	private String stanke;
	
	public Linija()
	{}
	
	public Linija(Integer id, String startStanica, String stopStanica, String stanke) {
		super();
		this.id = id;
		this.startStanica = startStanica;
		this.stopStanica = stopStanica;
		this.stanke = stanke;
	}
	
	public Linija(String startStanica, String stopStanica, String stanke) {
		super();
		this.startStanica = startStanica;
		this.stopStanica = stopStanica;
		this.stanke = stanke;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartStanica() {
		return startStanica;
	}

	public void setStartStanica(String startStanica) {
		this.startStanica = startStanica;
	}

	public String getStopStanica() {
		return stopStanica;
	}

	public void setStopStanica(String stopStanica) {
		this.stopStanica = stopStanica;
	}

	public String getStanke() {
		return stanke;
	}

	public void setStanke(String stanke) {
		this.stanke = stanke;
	}



	public void addObject() throws SQLException {
		String stmtString = "{ call dodajLiniju(?,?,?) }";
		
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setString(1, getStartStanica());
		statement.setString(2, getStopStanica());
		statement.setString(3, getStanke());
		statement.execute();
		statement.close();
	}



	/*Argument is number in string format*/
	public void removeObject(String key) throws SQLException {
		String stmtString = "{ call brisiLiniju(?) }";
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setInt(1, Integer.parseInt(key));
		statement.execute();
		statement.close();	
	}



	public Linija getObject(String key) throws SQLException {
		String stmtString = "{ call findLinija(?) }";
		Connection connection = Connection.getInstance();
		CallableStatement statement = connection.callProcedure(stmtString);
		statement.setInt(1, Integer.parseInt(key));
		statement.execute();
		String startString = statement.getString(2);
		String stopString = statement.getString(3);
		String stankaString = statement.getString(4);
		
		return new Linija(getId(), startString, stopString, stankaString);
	}



	public ArrayList<Linija> getAll() throws SQLException {
		ArrayList<Linija> list = new ArrayList<Linija>();
		
		String stmtString = "select * from `linija`";
		Connection connection = Connection.getInstance();
		ResultSet resultSet = connection.getResultSet(stmtString);
		
		while(resultSet.next())
		{
			list.add(new Linija(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
		}
		
		return list;
	}
}
