package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnectionFactory;
import databaseApplication.jdbc.Teacher;

public class TeacherDAO {

	public static void addTeacher(Teacher teacher) throws SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			final String sql = "insert into Teacher (first_name, last_name, designation) values (?,?,?)";
			// create the prepared statement with an option to get
			// auto-generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// set parameters
			stmt.setString(1, teacher.getFirstName());
			stmt.setString(2, teacher.getLastName());
			stmt.setString(3, teacher.getDesignation());

			stmt.execute();

			// Get auto-generated keys
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				teacher.setId(rs.getInt(1));

			rs.close();
			stmt.close();
		} finally {
			con.close();
		}

	}

	public List<Teacher> getTeachers() throws SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();

		List<Teacher> teachers = new ArrayList<Teacher>();
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
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setFirstName(rs.getString("first_name"));
				teacher.setLastName(rs.getString("last_name"));
				teacher.setDesignation(rs.getString("designation"));
				teachers.add(teacher);
			}

			return teachers;
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

