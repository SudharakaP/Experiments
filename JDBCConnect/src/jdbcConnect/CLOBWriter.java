package jdbcConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Reads a txt file and generates a CLOB (Character large object)
 * 
 * @author Sudharaka
 *
 */
public class CLOBWriter {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		// get connection to a database
		Connection conn = DriverManager.getConnection(dbUrl, user, pass);
		
		// prepared statement
		PreparedStatement stat;
		
		FileReader inputStream;
		
		// update employees table (write using prepared statement)
		String sql = "update employees set resume=? where email='john.doe@foo.com'";
		stat = conn.prepareStatement(sql);
		
		// get resume file
		File file = new File("data\\sample_resume.txt");
		inputStream = new FileReader(file);
		stat.setCharacterStream(1, inputStream);
		
		System.out.println("Reading input file:" + file.getAbsolutePath());
		
		// execute statement
		System.out.println("\nStoring resume in database: " + file);
		System.out.println(sql);
		
		stat.executeUpdate();
		
		System.out.println("\nCompleted successfully");
	}
}
