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

import model.CarteUtenti;
import model.Utenti;
import query.CarteUtenti_service;

/**
 * Servlet implementation class EliminaCarta
 */
@WebServlet("/EliminaCartaUtente")
public class EliminaCartaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaCartaUtente() {
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

		HttpSession session = request.getSession();
		Utenti utente = (Utenti) session.getAttribute("utente");
		String numeroCarta = request.getParameter("numeroCarta");

		try {
			CarteUtenti_service.eliminaCarta(numeroCarta);
			if (utente != null) {
				CarteUtenti carta = CarteUtenti_service.ottieniCarte(utente.getId());
				session.setAttribute("carte", carta);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/profiloUtente.jsp");
				dispatcher.forward(request, response);
			}

		} catch (SQLException | NamingException | NoSuchAlgorithmException | NullPointerException ex) {
			throw new ServletException(ex);
		}

	}

}
