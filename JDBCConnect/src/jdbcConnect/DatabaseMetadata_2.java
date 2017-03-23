package jdbcConnect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Getting database metadata
 * 
 * @author Sudharaka
 *
 */
public class DatabaseMetadata_2 {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		try {
			// get connection and get metadata
			conn = DriverManager.getConnection(dbUrl, user, pass);
			DatabaseMetaData metaData = conn.getMetaData();
			
			// get list of tables
			System.out.println("List of Tables");
			System.out.println("--------------");
			
			rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
					
			while(rs.next()){
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			// get list of columns
			System.out.println("\nList of Columns");
			System.out.println("-----------------");
			
			rs = metaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			
			while(rs.next()){
				System.out.println(rs.getString("COLUMN_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
}
