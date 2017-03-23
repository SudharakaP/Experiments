package databaseApplication.jdbc;

import java.sql.SQLException;
import java.util.List;

import dataAccessObject.TeacherDAO;

public class Teacher extends Person {
	private String designation;
	private int id;
	private TeacherDAO teacherDAO = new TeacherDAO();	

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Teacher> getTeachers() throws SQLException {
		return teacherDAO.getTeachers();
	}
	
	public void addCourse() throws SQLException {
		TeacherDAO.addTeacher(this);
	}
}
