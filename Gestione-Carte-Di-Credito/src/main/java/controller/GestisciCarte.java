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
 * Servlet implementation class GestisciCarte
 */
@WebServlet("/GestisciCarte")
public class GestisciCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestisciCarte() {
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
			String numero_carta = request.getParameter("numeroCarta");
			String azione = request.getParameter("azione");
			String address = "";

			if (azione == null || (!azione.equals("blocca") && !azione.equals("sblocca"))) {
				// Parametro "azione" mancante o non valido
				String message = "Azione non valida";
				request.setAttribute("errorMessage", message);
				address = "/WEB-INF/view/gestioneCarte.jsp";
			} else {
				// Continua con la gestione della carta
				if (utente != null && numero_carta != null) {
					CarteUtenti carta = CarteUtenti_service.ottieniCarta(numero_carta);
					String stato = CarteUtenti_service.stato(numero_carta);
					if (carta != null && carta.getStato() != null) {
						if (azione.equals("blocca")) {
							carta.setStato(stato);
							if (carta.getStato().equals("bloccata")) {
								// Carta già sbloccata, mostra un messaggio
								String message = "Questa carta è già bloccata!";
								request.setAttribute("errorMessage", message);
								address = "/WEB-INF/view/gestioneCarte.jsp";
							} else {
								// Carta non ancora sbloccata, procedo con lo sblocco
								CarteUtenti_service.gestisciCarta(numero_carta, azione);
								session.setAttribute("carta", carta);
								address = "/WEB-INF/view/viewCartaBloccata.jsp";
							}
						} else if (azione.equals("sblocca")) {
							carta.setStato(stato);
							if (carta.getStato().equals("sbloccata")) {
								// Carta già bloccata, mostra un messaggio
								String message = "Questa carta è già sbloccata!";
								request.setAttribute("errorMessage", message);
								address = "/WEB-INF/view/gestioneCarte.jsp";
							} else {
								// Carta non ancora bloccata, procedo con il blocco
								CarteUtenti_service.gestisciCarta(numero_carta, azione);
								session.setAttribute("carta", carta);
								address = "/WEB-INF/view/viewCartaSbloccata.jsp";
							}
						}
					}
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);

		} catch (SQLException | NamingException | NoSuchAlgorithmException ex) {
			throw new ServletException(ex);
		}
	}

}
