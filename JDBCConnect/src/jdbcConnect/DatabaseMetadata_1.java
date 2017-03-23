package jdbcConnect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Getting database metadata
 * 
 * @author Sudharaka
 *
 */
public class DatabaseMetadata_1 {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
	
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		try {
			conn = DriverManager.getConnection(dbUrl, user, pass);
			DatabaseMetaData metaData = conn.getMetaData();
			
			// database info
			System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
			System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
			
			// driver info
			System.out.println("JDBC Driver Name: " + metaData.getDriverName());
			System.out.println("JDBC Driver Version: " + metaData.getDriverVersion());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
}
