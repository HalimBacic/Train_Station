package Tests;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Items.Linija;
import Services.RasporedService;

class RasporedServiceTest {

	RasporedService rasporedService;
	
	@BeforeEach
	void setUp() throws Exception {
		rasporedService = new RasporedService();
	}

	@Test
	void testGetAll() {
	
		try {
			ArrayList<Linija> list = rasporedService.getAll();

			System.out.println(list.get(0).getStanke());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetDetails() {
		HashMap<String, LocalTime> hashMap = new HashMap<String, LocalTime>();
		try {
			hashMap = rasporedService.getDetails(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(hashMap.get("Banja Luka").toString());
	}

	@Test
	void testChangeStatusLinije() {
		rasporedService.changeStatusLinije(1, "Banja Luka", false);
	}

}
