package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatements {

	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		// Connection to MySQL database
		Connection conn = DriverManager.getConnection(dbUrl, user, password);
		
		// Create prepared statement
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM demo.employees WHERE salary > ? AND department=?");
		
		// Assign values
		prep.setInt(1, 30000);
		prep.setString(2, "Engineering");
		
		// Execute Query
		ResultSet resSet = prep.executeQuery();
		
		System.out.println("Employees witgh salary > 30000 and in Engineering");
		displayResult(resSet);
		
		prep.setInt(1, 5000);
		prep.setString(2, "Software dev");
		
		resSet = prep.executeQuery();
		
		System.out.println("Employees with salary > 5000 and in Software dev");
		displayResult(resSet);
	}

	/**
	 * Display the result of query execution
	 * 
	 * @param resSet
	 * @throws SQLException
	 */
	private static void displayResult(ResultSet resSet) throws SQLException {
		while(resSet.next())
			System.out.println(resSet.getString("last_name") + ", " + resSet.getString("first_name"));
	}
}
