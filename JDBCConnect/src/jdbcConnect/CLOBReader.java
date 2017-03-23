/**
 * 
 */
package jdbcConnect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Read from a CLOB to a txt file
 * 
 * @author Sudharaka
 *
 */
public class CLOBReader {

	public static void main(String[] args) throws SQLException, IOException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		// get connection to a database and create statement
		Connection conn = DriverManager.getConnection(dbUrl, user, pass);
		Statement stat = conn.createStatement();
		
		FileWriter outputStream;
		Reader inputStream;
		
		// execute statement
		String sql = "select resume from employees where email='john.doe@foo.com'";
		ResultSet rs = stat.executeQuery(sql);
		
		// set up a handle for the file
		File file = new File("data\\sample_resume_from_db.txt");
		outputStream = new FileWriter(file);
		
		if (rs.next()){
			inputStream = rs.getCharacterStream("resume");
			System.out.println("Reading from the database");
			System.out.println(sql);
			
			int theChar;
			while ((theChar = inputStream.read()) > 0){
				outputStream.write(theChar);
			}
			System.out.println("\nSaved to file: " + file.getAbsolutePath());
			System.out.println("\nCompleted successfully");
			outputStream.close();
		}
	}
}
