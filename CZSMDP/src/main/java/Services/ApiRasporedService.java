package Services;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import Items.Linija;

@Path("/raspored")
public class ApiRasporedService {

	RasporedService rasporedService;
	
	public ApiRasporedService()
	{
		rasporedService = new RasporedService();
	}
	
	
	@GET
	public ArrayList<Linija> getLinije()
	{
		System.out.println(":GET:");
		try {
			return rasporedService.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Linija>();
	}
	
	@GET
	@Path("/{id}")
	public HashMap<String, LocalTime> getDetails(@PathParam("id") int id)
	{
		HashMap<String, LocalTime> mapaHashMap = new HashMap<String, LocalTime>();
		try {
			mapaHashMap = rasporedService.getDetails(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapaHashMap;	
	}
	
	@PUT
	@Path("/{id}_{stanica}_{status}")
	public Response updateStatus(@PathParam("id")Integer id,@PathParam("stanica") String stanica,@PathParam("status") boolean status)
	{
		if(rasporedService.changeStatusLinije(id, stanica, status))
			return Response.status(200).build();
		else {
			return Response.status(404).build();
		}
	}
}
