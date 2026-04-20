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
import model.Circuiti;
import model.Utenti;
import query.CarteUtenti_service;
import query.Carte_service;

/**
 * Servlet implementation class CompletaPagamento
 */
@WebServlet("/CompletaPagamento")
public class CompletaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompletaPagamento() {
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
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Utenti utenti = (Utenti) session.getAttribute("utente");
			String nomecarta = request.getParameter("nomeCarta");
			String numerocarta = request.getParameter("numeroCarta");
			String cvvCasualeString1 = request.getParameter("cvvCasuale");
			String prezzoString = request.getParameter("prezzoCarta");
			String quantitàString = request.getParameter("quantita");
			String numerocartapagamento = request.getParameter("cartaPagamento");
			String cvvString = request.getParameter("cvv");

			double prezzo = Double.parseDouble(prezzoString);
			int quantità = Integer.parseInt(quantitàString);
			int cvvCasuale = Integer.parseInt(cvvCasualeString1);
			int cvv = Integer.parseInt(cvvString);
			int id_utente = utenti.getId();
			String address = "";

			// Verifica se il CVV è valido
			if (!CarteUtenti_service.checkCartaCvv(numerocartapagamento, cvv)) {
				String errorMessage = "La carta con il CVV specificato non esiste. Controlla i dati e riprova.";
				request.setAttribute("messaggio", errorMessage);
				address = "/WEB-INF/view/completaAcquisto.jsp";
			} else {
				String statoCarta = CarteUtenti_service.stato(numerocartapagamento);
				CarteUtenti creditoAttuale = CarteUtenti_service.ottieniCreditoUtente(id_utente, numerocartapagamento);
				// Verifica lo stato della carta
				if ("bloccata".equals(statoCarta)) {
					String errorMessage = "Impossibile completare l'acquisto. La tua carta è bloccata!";
					request.setAttribute("messaggio", errorMessage);
					address = "/WEB-INF/view/completaAcquisto.jsp";
				} else if (creditoAttuale.getCredito() <= 0) {
					String message = "Credito insufficiente. Devi effettuare una ricarica per completare l'acquisto";
					request.setAttribute("messaggio2", message);
					address = "/WEB-INF/view/completaAcquisto.jsp";
				} else {
					// Esegue l'acquisto
					CarteUtenti_service.diminuisciCreditoUtente(numerocartapagamento, prezzo, quantità, cvv);
					Carte_service.aggiornaQuantita(numerocarta, quantità);
					CarteUtenti creditoAggiornato = CarteUtenti_service.ottieniCreditoUtente(id_utente,
							numerocartapagamento);
					CarteUtenti cartautente = CarteUtenti_service.ottieniCarta(numerocartapagamento);
					Carte_service.ottieniCartaAcquistata(nomecarta, numerocarta, cvvCasuale, prezzo, quantità,
							id_utente);
					Circuiti circuito = Carte_service.getTipoCircuito(numerocarta);
					int circuito_id = circuito.getId();
					Carte_service.inserisciCartaAcquistata(nomecarta, numerocarta, cvvCasuale, id_utente, circuito_id);
					Carte_service.eliminaCartaDopoAcquisto(numerocarta);

					session.setAttribute("nomeCarta", nomecarta);
					session.setAttribute("numeroCarta", numerocarta);
					session.setAttribute("cvv", cvvCasuale);
					session.setAttribute("creditoAggiornato", creditoAggiornato);
					session.setAttribute("cartautente", cartautente);
					address = "/WEB-INF/view/messaggi/acquistoCompletato.jsp";
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);

		} catch (SQLException | NamingException | NoSuchAlgorithmException e) {
			// Eccezioni legate alla connessione al database o all'esecuzione della query
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
