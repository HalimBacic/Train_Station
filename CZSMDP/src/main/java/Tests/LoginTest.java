package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Services.Login;

class LoginTest {

	Login login;
	
	@BeforeEach
	void setUp() throws Exception {
		login = new Login();
	}

	@Test
	void testFalsePass() {
		
		try {
			assertEquals(false, login.checkLogin("admin", "takemesomewhereIbelong"));
		} catch (NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testTruePass()
	{
		try {
			assertEquals(true, login.checkLogin("admin", "admin"));
		} catch (NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testNotUser()
	{
		try {
			assertEquals(false,login.checkLogin("nekicova", "covinasifra") );
		} catch (NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
