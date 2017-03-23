package jdbcConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Demonstration of returning result sets in stored procedures.
 * 
 * @author Sudharaka
 *
 */
public class StoredProcedures_ResultSet {

	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		Connection conn;
		CallableStatement callable;
		ResultSet rs = null;
		
		conn = DriverManager.getConnection(dbUrl, user, password);
		
		String department = "Engineering";
		
		// prepare the stored procedure
		callable = conn.prepareCall("{call get_employees_for_department(?)}");
		
		// set the parameters
		callable.setString(1, department);
		
		// call stored procedure
		callable.execute();
		
		// get result set
		rs = callable.getResultSet();
		
		// print out the result set
		System.out.println("FirstName, LastName, Salary");
		while(rs.next()){
			System.out.println(rs.getString("first_name") + ", " + rs.getString("last_name") + ", " + rs.getString("salary"));
		}
	}
}
