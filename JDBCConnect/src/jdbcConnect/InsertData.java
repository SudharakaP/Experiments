package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

	public static void main(String[] args) throws SQLException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String username = "student";
		String password = "student";
		
		Connection conn = null;
		Statement statement = null;
		ResultSet resSet = null;
		
		try{
			// Connect to database
			conn = DriverManager.getConnection(dbUrl, username, password);
			
			// Create and SQL statement object
			statement = conn.createStatement();
			
			// Execute query to insert a new entry
			int rows = statement.executeUpdate("insert into employees " + 
			"(last_name, first_name, email , department, salary) " +
					"values " + "('Palamakumbura', 'Sudharaka', 'sudharakap@mun.ca', 'Software Dev', 10000.00)");
			
			resSet = statement.executeQuery("select * from employees");
			
			while (resSet.next()){
				System.out.println(resSet.getString("last_name") + ", " + resSet.getString("first_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (resSet != null)
				resSet.close();
		}
	}
}
