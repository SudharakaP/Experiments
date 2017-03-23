package loginApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginApplicationServlet
 */
@WebServlet("/login")
public class LoginApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginApplicationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().write(createForm(null));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// create StringBuilder to hold response string
		StringBuilder responseStr = new StringBuilder();
		if ("admin".equals(userName) && "admin".equals(password)) {
			responseStr.append("<h2>Welcome admin !</h2>").append("You are successfully logged in");
		} else {
			// invalid user credentials
			responseStr.append(createForm("Invalid user id or password. Please try again"));
		}
		response.setContentType("text/html");
		response.getWriter().write(responseStr.toString());
	}

	protected String createForm(String errMsg) {
		StringBuilder sb = new StringBuilder("<h2>Login</h2>");
		// check whether error message is to be displayed
		if (errMsg != null) {
			sb.append("<span style='color: red;'>").append(errMsg).append("</span>");
		}
		// create form
		sb.append("<form method='post'>\n").append("User Name: <input type='text' name='userName'><br>\n")
				.append("Password: <input type='password' name='password'><br>\n")
				.append("<button type='submit' name='submit'>Submit</button>\n")
				.append("<button type='reset'>Reset</button>\n").append("</form>");

		return sb.toString();
	}

}
