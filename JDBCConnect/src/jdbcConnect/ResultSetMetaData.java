package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Get resultset metadata (gives information about the resultset)
 * 
 * @author Sudharaka
 *
 */
public class ResultSetMetaData {

	public static void main(String[] args) throws SQLException {
		Connection conn;
		Statement stat; 
		ResultSet rs;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		conn = DriverManager.getConnection(dbUrl, user, pass);
		stat = conn.createStatement();
		rs = stat.executeQuery("select id, last_name, first_name, salary from employees");
		
		// get resultset metadata
		java.sql.ResultSetMetaData metaData = rs.getMetaData();
		
		// display info
		int columnCount = metaData.getColumnCount();
		System.out.println("Column count: " + columnCount + "\n");
		
		for (int column = 1; column <= columnCount; column++){
			System.out.println("Column Name: " + metaData.getColumnName(column));
			System.out.println("Column Type Name: " + metaData.getColumnTypeName(column));
			System.out.println("Is Nullable: " + metaData.isNullable(column));
			System.out.println("Is Auto Increment: " + metaData.isAutoIncrement(column) + "\n");
		}
	}
}
