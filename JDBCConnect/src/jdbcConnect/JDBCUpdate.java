package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUpdate {

	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		Connection conn = null;
		Statement stat = null;
		ResultSet resSet = null;
		
		try {
			// Connecting to MySQL database
			conn = DriverManager.getConnection(dbUrl, user, pass);
			
			// Creating a MySQL statement
			stat = conn.createStatement();
			
			// Query a value from the database and display it
			displayEmployee(conn);
			
			// Update database
			int rowsUpdated = stat.executeUpdate("UPDATE demo.employees SET email='sudharakap@mun.ca' WHERE last_name='Davis'");
			
			if (rowsUpdated == 1){
				System.out.println("Database Updated!");
			}
			
			// Display updated value
			displayEmployee(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (resSet != null)
				resSet.close();
		}
	}
	
	/**
	 * Through a given connection to the database retrieves employee information.
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException 
	 */
	private static void displayEmployee(Connection conn) throws SQLException{
		Statement stat = conn.createStatement();
		ResultSet resSet = stat.executeQuery("SELECT first_name, email from demo.employees WHERE last_name='Davis'");
		
		while (resSet.next())
			System.out.println(resSet.getString("first_name") + ", " + resSet.getString("email"));
		
	}
}
