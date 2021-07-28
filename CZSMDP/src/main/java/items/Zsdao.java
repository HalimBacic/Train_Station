package items;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Zsdao<T> {
	
	public void addObject() throws SQLException;
	public void removeObject(String key) throws SQLException;
	public T getObject(String key) throws SQLException;
	public ArrayList<T> getAll() throws SQLException;
}
