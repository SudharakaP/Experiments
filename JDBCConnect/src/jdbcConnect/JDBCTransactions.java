package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC commit and rollback tranactions
 * 
 * @author Sudharaka
 *
 */
public class JDBCTransactions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		
		try {
			// get connection to database
			conn = DriverManager.getConnection(dbUrl, user, pass);
			stat = conn.createStatement();
			
			// turn off auto-commit
			conn.setAutoCommit(false);
			
			// show salaries before
			System.out.println("Salaries Before");
			showSalaries(conn, "HR");
			showSalaries(conn, "Engineering");
			
			// execute step 1
			stat.executeUpdate("delete from employees where department='HR'");
			
			// execute step 2
			stat.executeUpdate("update employees set salary=30000 where department='Engineering'");
			
			System.out.println("\nTransaction steps are ready\n");
			
			boolean ready = readyToCommit();
			
			// commit transaction if user gives permission, otherwise rollback
			if(ready){
				conn.commit();
				System.out.println("\nTransaction Commited\n");
			}else{
				conn.rollback();
				System.out.println("\nTransaction Rollback\n");
			}
			
			// show salaries after
			System.out.println("Salaries After\n");
			showSalaries(conn, "HR");
			showSalaries(conn, "Engineering");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prompt user's permission to commit transaction
	 * 
	 * @return
	 */
	private static boolean readyToCommit() {
		Scanner reader = new Scanner(System.in); 
		System.out.println("Ready to commit? (Yes/No) ");
		String ready = reader.nextLine();
		reader.close();
		
		if(ready.equals("Yes"))
			return true;
		return false;
	}

	/**
	 * Display salaries
	 * 
	 * @param string
	 * @throws SQLException 
	 */
	private static void showSalaries(Connection conn, String department) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select last_name, department, salary from demo.employees where department='" 
				+ department + "'");
		
		System.out.println("\nLastName | Department | Salary\n");
		while(rs.next()){
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
		} 
	}
}
