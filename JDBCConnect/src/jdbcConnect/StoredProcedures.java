package jdbcConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstration of stored procedures.
 * 
 * @author Sudharaka
 *
 */
public class StoredProcedures {

	public static void main(String[] args) throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		Connection conn;
		CallableStatement callable;
		
		conn = DriverManager.getConnection(dbUrl, user, password);
		
		String department = "Engineering";
		int increaseAmount = 10000;
		
		System.out.println("Salaries Before");
		showSalaries(conn, department);
		
		// prepare the stored procedure
		callable = conn.prepareCall("{call increase_salaries_for_department(?,?)}");
		
		callable.setString(1, department);
		callable.setInt(2, increaseAmount);
		
		// call stored procedure
		callable.execute();
		
		System.out.println("Salaries After");
		showSalaries(conn, department);	
	}

	private static void showSalaries(Connection conn, String department) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select first_name, last_name, salary from demo.employees where "
				+ "department='" + department + "'");
		System.out.println("FirstName | LastName | Salary");
		
		while(rs.next()){
			System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
		}
	}

}
