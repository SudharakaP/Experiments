package jdbcConnect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Reads from the BLOB and constructs a pdf
 * 
 * @author Sudharaka
 *
 */
public class BLOBReader {

	public static void main(String[] args) throws SQLException, IOException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		// get connection to a database and create statement
		Connection conn = DriverManager.getConnection(dbUrl, user, pass);
		Statement stat = conn.createStatement();
		
		FileOutputStream outputStream;
		InputStream inputStream;
		
		// execute statement
		String sql = "select resume from employees where email='john.doe@foo.com'";
		ResultSet rs = stat.executeQuery(sql);
		
		// set up a handle for the file
		File file = new File("data\\resume_from_db.pdf");
		outputStream = new FileOutputStream(file);
		
		if (rs.next()){
			inputStream = rs.getBinaryStream("resume");
			System.out.println("Reading from the database");
			System.out.println(sql);
			
			byte[] buffer = new byte[1024];
			while (inputStream.read(buffer)>0){
				outputStream.write(buffer);
			}
			System.out.println("\nSaved to file: " + file.getAbsolutePath());
			System.out.println("\nCompleted successfully");
			outputStream.close();
		}
	}
}
