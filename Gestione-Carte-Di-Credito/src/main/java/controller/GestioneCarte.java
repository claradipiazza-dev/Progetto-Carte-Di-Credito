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

import model.CarteUtenti;
import model.Utenti;
import query.CarteUtenti_service;

/**
 * Servlet implementation class GestisciCarte
 */
@WebServlet("/GestioneCarte")

public class GestioneCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneCarte() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HttpSession session = request.getSession();
			Utenti utente = (Utenti) session.getAttribute("amministratore");
			CarteUtenti listacarte = CarteUtenti_service.ottieniCarte();

			session.setAttribute("carte", listacarte);
			session.setAttribute("utente", utente);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneCarte.jsp");
			dispatcher.forward(request, response);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
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
