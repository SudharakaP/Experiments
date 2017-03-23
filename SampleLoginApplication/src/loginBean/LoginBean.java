package loginBean;

public class LoginBean {
	private String userName;
	private String password;

	public boolean isValidUser() {
		// Validation can happen here from a number of sources
		// for example, database and LDAP
		// We are just going to hardcode a valid username and
		// password here.
		return "admin".equals(this.userName) && "admin".equals(this.password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
