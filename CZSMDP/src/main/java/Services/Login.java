package Services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Items.Connection;

public class Login {

	public Boolean checkLogin(String login, String pass) throws SQLException, NoSuchAlgorithmException {

		MessageDigest mDigest = MessageDigest.getInstance("SHA-512");

		byte[] messageDigest = mDigest.digest(pass.getBytes());

		// Convert byte array into signum representation
		BigInteger no = new BigInteger(1, messageDigest);

		// Convert message digest into hex value
		String passEnc = no.toString(16);

		// Add preceding 0s to make it 32 bit
		while (passEnc.length() < 32) {
			passEnc = "0" + passEnc;
		}

		Connection connection = Connection.getInstance();

		String stmtString = " call vratiLogin(?) ";

		CallableStatement stmtCallableStatement = connection.callProcedure(stmtString);
		stmtCallableStatement.setString(1, login);
		ResultSet resultSet = stmtCallableStatement.executeQuery();

		while (resultSet.next()) {
			String loginBase = resultSet.getString(1).toLowerCase();
			if (loginBase.equals(passEnc)) {
				System.out.println("OK");
				connection.closeConnection();
				return true;
			} else {
				System.out.println("NOK");
				connection.closeConnection();
				return false;
			}
		}
		System.out.println("NOK");
		connection.closeConnection();
		return false;
	}
}
