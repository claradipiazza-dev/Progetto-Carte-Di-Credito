package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Acquisti;
import model.CarteUtenti;
import model.Utenti;
import query.Acquisti_service;
import query.CarteUtenti_service;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			HttpSession session = request.getSession();
			Utenti utente = (Utenti) session.getAttribute("utente");
			String address = "";

			if (utente.getRuolo() != null && utente.getRuolo().equals("utente")) {
				// Codice per il ruolo "utente"

				CarteUtenti carta = CarteUtenti_service.ottieniCarte(utente.getId());
				Acquisti listaAcquisti = Acquisti_service.ottieniAcquisti(utente.getId());
				session.setAttribute("carteu", carta);
				session.setAttribute("listaAcquisti", listaAcquisti);

				address = "/WEB-INF/view/utenteView.jsp";

			} else if (utente.getRuolo() != null && utente.getRuolo().equals("amministratore")) {
				// Codice per il ruolo "amministratore"

				session.setAttribute("amministratore", utente);
				CarteUtenti carta = CarteUtenti_service.ottieniCarte(utente.getId());
				session.setAttribute("carte", carta);
				address = "/WEB-INF/view/amministratoreView.jsp";

			} else {
				// Gestione caso in cui il ruolo sia nullo o diverso da "utente" e "amministratore"
				address = "/WEB-INF/view/errore.jsp";
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);

		} catch (NullPointerException ex) {
			throw new ServletException(ex);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
