package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTest {

	public static void main(String[] args) {
		Connection myConn = null;
		Statement myStat = null;
		ResultSet resulSet = null;
		
		
		try {
			// Connecting to the Database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
			
			System.out.println("Database Connection Sucessful!\n");
			
			//Create a Statement
			myStat = myConn.createStatement();
			
			// Execute the SQL Query
			resulSet = myStat.executeQuery("select * from employees");
			
			// Process result
			while (resulSet.next()){
				System.out.println(resulSet.getString("last_name") + ", " + resulSet.getString("first_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
