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
import query.Utenti_service;
import query.CarteUtenti_service;

/**
 * Servlet implementation class Ricarica
 */
@WebServlet("/Ricarica")

public class Ricarica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ricarica() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Utenti utente = (Utenti) session.getAttribute("utente");
			String numero_carta = request.getParameter("cartaPagamento");
			double importo = Double.parseDouble(request.getParameter("importo"));

			Utenti_service.ricaricaCreditoUtente(importo, numero_carta, utente.getId());

			// Ottieni il credito aggiornato dopo la ricarica
			CarteUtenti carta = CarteUtenti_service.ottieniCarteUtenti(numero_carta, utente.getId());
			CarteUtenti creditoAggiornato = CarteUtenti_service.ottieniCreditoUtente(utente.getId(), numero_carta);
			session.setAttribute("carte", carta);
			session.setAttribute("creditoAggiornato", creditoAggiornato);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/visualizzaRicarica.jsp");
			dispatcher.forward(request, response);

		} catch (

		NumberFormatException e) {

			// Eccezione lanciata se il parametro "importo" non è un numero valido
			e.printStackTrace();

		} catch (NullPointerException e) {

			// Eccezione lanciata se si tenta di accedere a un oggetto null
			e.printStackTrace();

		} catch (SQLException | NamingException | NoSuchAlgorithmException e) {

			// Eccezioni legate alla connessione al database o all'esecuzione della query
			e.printStackTrace();

			throw new ServletException(e);
		} catch (Exception e) {

			// Gestione di altre eccezioni non previste
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
