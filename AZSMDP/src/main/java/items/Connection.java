package items;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {
	
	private static Connection connection_instance;
	private java.sql.Connection connection;
	
	private Connection()
	{
		String link; String username; String password;
		Properties properties = new Properties();
		
		try {

			properties = MdpUtility.getProperties();	
			link = properties.getProperty("dblink");
			username = properties.getProperty("username");
			password = properties.getProperty("pass");
			
			connection = DriverManager.getConnection(link, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance()
	{
		if(connection_instance==null)
			connection_instance = new Connection();
		
		return connection_instance;
	}

	public java.sql.Connection getConnection() {
		return connection;
	}

	public void setConnection(java.sql.Connection connection) {
		this.connection = connection;
	}
	
	
	public ResultSet getResultSet(String stmt) throws SQLException
	{
		PreparedStatement preparedStatement = connection.prepareStatement(stmt);
		return preparedStatement.executeQuery();
	}
	
	public CallableStatement callProcedure(String stmt) throws SQLException
	{
		CallableStatement callableStatement = connection.prepareCall(stmt);
		return callableStatement;
	}
	
	
	
	public void closeConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connection_instance = null;
	}

}
