package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utenti;
import query.Utenti_service;

/**
 * Servlet implementation class Accesso
 */

public class Accesso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accesso() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String address = "";
			Utenti utente = (Utenti) session.getAttribute("utente");

			// Hash della password fornita dall'utente
			String passwordHash = Utenti_service.passwordHash(password);
			utente = Utenti_service.accesso(email, passwordHash);

			if (utente != null) {

				session.setAttribute("utente", utente);
				address = "Home";
			}

			else {

				address = "CredenzialiErrate";
			}

			response.sendRedirect(address);

		} catch (SQLException | NamingException | NoSuchAlgorithmException | NullPointerException ex) {
			throw new ServletException(ex);
		}
	}

}
