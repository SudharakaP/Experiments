package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnectionFactory;
import databaseApplication.jdbc.Student;

public class StudentDAO {

	public static void addStudent(Student student) throws SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			final String sql = "insert into Student (first_name, last_name, enrolled_since) values (?,?,?)";
			// create the prepared statement with an option to get
			// auto-generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// set parameters
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setLong(3, student.getEnrolledsince());

			stmt.execute();

			// Get auto-generated keys
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				student.setId(rs.getInt(1));

			rs.close();
			stmt.close();
		} finally {
			con.close();
		}

	}

	public List<Student> getTeachers() throws SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();

		List<Student> students = new ArrayList<Student>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();

			// create SQL statement using left outer join
			StringBuilder sb = new StringBuilder("select * from teacher");

			// execute the query
			rs = stmt.executeQuery(sb.toString());

			// iterate over result set and create Course objects
			// add them to course list
			while (rs.next()) {
				Student student = new Student();
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEnrolledsince(rs.getLong("enrolled_since"));
				students.add(student);
			}

			return students;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}

