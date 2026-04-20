package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
 * Servlet implementation class EliminaCarta
 */
@WebServlet("/EliminaCarta")
public class EliminaCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaCarta() {
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
			Utenti utente = (Utenti) session.getAttribute("amministratore");
			String numeroCarta = request.getParameter("numeroCarta");
			Carte_service.eliminaCarta(numeroCarta);
			Carte carteAggiornate = Carte_service.ottieniCarte();

			session.setAttribute("carta", carteAggiornate);
			session.setAttribute("utente", utente);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/listaCarte.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | NamingException | NoSuchAlgorithmException | NullPointerException ex) {
			throw new ServletException(ex);
		}

	}

}
