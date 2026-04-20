package controller;

import java.io.IOException;

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
 * Servlet implementation class ProfiloUtente
 */
@WebServlet("/ProfiloUtente")

public class ProfiloUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfiloUtente() {
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

		HttpSession session = request.getSession();
		Utenti utente = (Utenti) session.getAttribute("utente");

		if (utente != null) {

			CarteUtenti listacarte = CarteUtenti_service.ottieniCarte(utente.getId());
			session.setAttribute("carte", listacarte);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/profiloUtente.jsp");
		dispatcher.forward(request, response);

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
