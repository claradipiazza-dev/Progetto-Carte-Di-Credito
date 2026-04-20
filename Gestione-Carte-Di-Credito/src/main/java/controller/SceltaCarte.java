package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carte;
import model.Utenti;
import query.Carte_service;

/**
 * Servlet implementation class SceltaCarte
 */

@WebServlet("/SceltaCarte")
public class SceltaCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SceltaCarte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Utenti utente = (Utenti) session.getAttribute("utente");

			if (utente != null) {
				Carte carteAggiornate = Carte_service.ottieniCarte();
				session.setAttribute("carta", carteAggiornate);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/acquistoCarte.jsp");
				dispatcher.forward(request, response);

			} else {

				String message = "Si è verificato un errore";
				response.getWriter().print(message);
			}

		} catch (SQLException | NamingException | NullPointerException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
