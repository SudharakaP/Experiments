package databaseApplication.jdbc;

public class Student extends Person {
	private long enrolledsince;
	private int id;

	public long getEnrolledsince() {
		return enrolledsince;
	}

	public void setEnrolledsince(long enrolledsince) {
		this.enrolledsince = enrolledsince;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

