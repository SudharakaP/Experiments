package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDelete {

	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
	
		// Connect to MySQL databse
		Connection conn = DriverManager.getConnection(dbUrl, user, password);
		
		// Create SQL statement
		Statement stat = conn.createStatement();
		
		// Display row in database
		String query = "SELECT * FROM demo.employees WHERE first_name='John' AND last_name='Davis'";
		displayData(conn, query);
		
		// Delete the previous row from database
		int rowsAffected = stat.executeUpdate("DELETE FROM demo.employees WHERE first_name='John' AND last_name='Davis'");
		System.out.println("Database row deleted");
		
		// Try to display deleted row in database
		displayData(conn, query);
	}
	
	private static void displayData(Connection conn, String query) throws SQLException{
		// Create SQL statement
		Statement stat = conn.createStatement();
		
		// Execute SQL query
		ResultSet resSet = stat.executeQuery(query);
		
		while(resSet.next())
			System.out.println(resSet.getString("first_name") + ", " + resSet.getString("email"));		
	}	
}
