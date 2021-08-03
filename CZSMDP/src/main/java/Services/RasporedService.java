package Services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import Items.Connection;
import Items.Linija;

public class RasporedService {
	
	public RasporedService()
	{}
	
	public ArrayList<Linija> getAll() throws SQLException
	{
		ArrayList<Linija> listaArrayList = new ArrayList<Linija>();
		String stmtString = " {call sveLinije()} ";
		Connection connection = Connection.getInstance();
		CallableStatement callableStatement = connection.callProcedure(stmtString);
		ResultSet rSet = callableStatement.executeQuery();
		
		Integer idInteger = 1;
		String startString = "";
		String stopString = "";
		String stankaString = "";
		while(rSet.next())
		{
			Integer idInteger2 = rSet.getInt(1);
			if(idInteger == idInteger2)
			{
				stankaString += "  "+rSet.getString(4);
				startString = rSet.getString(2);
				stopString = rSet.getString(3);
			}
			else if(idInteger != idInteger2) {
				idInteger = idInteger2;
				Linija linija = new Linija(idInteger,startString,stopString,stankaString);
				listaArrayList.add(linija);
			}
		}
		
		//Add last in row
		Linija linija = new Linija(idInteger,startString,stopString,stankaString);
		listaArrayList.add(linija);
		return listaArrayList;
	}
	
	public HashMap<String, LocalTime> getDetails(Integer id) throws SQLException
	{
		HashMap<String, LocalTime> map = new HashMap<String, LocalTime>();
		String stmtString = "{ call ispisLinije(?) }";
		Connection connection = Connection.getInstance();
		CallableStatement callableStatement = connection.callProcedure(stmtString);
		callableStatement.setInt(1, id);
		ResultSet resultSet = callableStatement.executeQuery();
		while(resultSet.next())
		{
			LocalTime lTime = LocalTime.parse(resultSet.getTime(2).toString());
			String gradString = resultSet.getString(1);
			map.put(gradString, lTime);
		}
		
		return map;		
	}
	
	public boolean changeStatusLinije(Integer id,String ime,boolean status)
	{
		String stmtString = "{ call promjeniStatus(?,?,?) }";
		Connection connection = Connection.getInstance();
		CallableStatement callableStatement;
		try {
			callableStatement = connection.callProcedure(stmtString);
			callableStatement.setInt(1, id);
			callableStatement.setString(2, ime);
			callableStatement.setBoolean(3, status);
			callableStatement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
