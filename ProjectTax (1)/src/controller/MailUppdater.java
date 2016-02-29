package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MailList;

/**
 * Servlet implementation class MailUppdater
 */
@WebServlet("/MailUppdater")
public class MailUppdater extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MailList ml=new MailList();
		String mail = request.getParameter("mail");
		ml.uppdateMailList(mail);
		Cookie mailCookie=new Cookie("mail","mail");
		response.addCookie(mailCookie);
		request.getSession().setMaxInactiveInterval(60*60*24*7);
		mailCookie.setMaxAge(request.getSession().getMaxInactiveInterval());
		
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("index.html");
		requestDispatcher.forward(request, response);
		
	}

}
