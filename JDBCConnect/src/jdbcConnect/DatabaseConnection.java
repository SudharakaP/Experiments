/**
 * 
 */
package jdbcConnect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Sudharaka
 *
 */
public class DatabaseConnection {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		
		// get database connection using separate configuration file.
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String dbUrl = props.getProperty("dbUrl");
		String username = props.getProperty("user");
		String password = props.getProperty("pass");
		
		System.out.println("Database Url: " + dbUrl);
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		
		// get connection to the database
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		
		// print some data just to verify
		printSomeData(conn);
	}
	
	public static void printSomeData(Connection conn) throws SQLException{
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select first_name from demo.employees");
		
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
	}
}
