package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

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
import query.Carte_service;
import query.NumeriCarte_service;
import model.Circuiti;

/**
 * Servlet implementation class AggiungiCartaUtente
 */
@WebServlet("/AggiungiCartaUtente")
public class AggiungiCartaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiCartaUtente() {
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
			Utenti utente = (Utenti) session.getAttribute("utente");
			String nome_carta = request.getParameter("nomecarta");
			String numero_carta = request.getParameter("card");
			int cvv = Integer.parseInt(request.getParameter("cvv"));
			String operazione = request.getParameter("operazione");
			String address = "";

			boolean isCartaEsistente = false;
			isCartaEsistente = CarteUtenti_service.checkCarta(numero_carta);
			boolean isNumeroEsistente = Carte_service.checkCarta(numero_carta);
			boolean isCartaValida = NumeriCarte_service.checkCarta(numero_carta);

			if (operazione.equals("aggiungi")) {
				if (!isCartaEsistente && !isNumeroEsistente && !isCartaValida) {
					Circuiti circuito = Carte_service.getTipoCircuito(numero_carta);
					int utente_id = utente.getId();
					if (circuito != null) {
						if (!CarteUtenti_service.checkCvv(cvv) && !Carte_service.checkCvv(cvv)) {
							int circuito_id = circuito.getId();
							CarteUtenti_service.aggiungiCarta(nome_carta, numero_carta, cvv, utente_id, circuito_id);
							CarteUtenti cartaU = CarteUtenti_service.ottieniCarta(numero_carta);
							session.setAttribute("carta", cartaU);
							session.setAttribute("circuito", circuito);

							address = "/WEB-INF/view/viewCartaAggiunta.jsp";

							RequestDispatcher dispatcher = request.getRequestDispatcher(address);
							dispatcher.forward(request, response);
						} else {
							String messaggio = "Il cvv inserito è già esistente!";
							response.setHeader("errorMessage", messaggio);
							request.setAttribute("messaggio", messaggio);
						}
					} else {
						String messaggio = "La carta non appartiene a nessun circuito valido";
						response.setHeader("errorMessage", messaggio);
						request.setAttribute("messaggio", messaggio);
					}
				} else {
					String messaggio = "Questo numero di carta è già utilizzato nel nostro sistema";
					response.setHeader("errorMessage", messaggio);
					request.setAttribute("messaggio", messaggio);
				}
			}

		} catch (NamingException | SQLException | IllegalArgumentException | NoSuchAlgorithmException e) {
			throw new ServletException(e);
		}
	}

}
