package jdbcConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Demonstration of INOUT parameters of stored procedures.
 * 
 * @author Sudharaka
 *
 */
public class StoredProcedures_OUT {

	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		Connection conn;
		CallableStatement callable;
		
		conn = DriverManager.getConnection(dbUrl, user, password);
		
		String department = "Engineering";
		
		// prepare the stored procedure
		callable = conn.prepareCall("{call get_count_for_department(?, ?)}");
		
		// set the parameters
		callable.registerOutParameter(2, Types.VARCHAR);
		callable.setString(1, department);
		
		// call stored procedure
		callable.execute();
		
		// get value of out parameter
		int outPara = callable.getInt(2);
		
		// print the value of the OUT parameter
		System.out.println("Result: " + outPara);
	}
}
