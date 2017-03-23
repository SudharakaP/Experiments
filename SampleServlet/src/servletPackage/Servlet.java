package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet(description = "This is my first Servlet", urlPatterns = { "/Servlet" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		increment();
		PrintWriter writer = response.getWriter();
		writer.println("My name is <b>Suds</b>.");
		decrement();
	}
	
	protected synchronized void increment(){
		count++;
	}
	
	protected synchronized void decrement(){
		count--;
	}
	
	protected synchronized int getCount(){
		return count;
	}

}
