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

import query.Carte_service;
import model.Utenti;
import model.Carte;

@WebServlet("/AggiungiCarta")
public class AggiungiCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AggiungiCarta() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Utenti utente = (Utenti) session.getAttribute("amministratore");
			String nome_carta = request.getParameter("nomecarta");
			String prezzoString = request.getParameter("prezzo");
			String quantitàString = request.getParameter("quantità");
			String operazione = request.getParameter("operazione");
			double prezzo = Double.parseDouble(prezzoString);
			int quantità = Integer.parseInt(quantitàString);
			String address = "";
			
			boolean isCartaValida = Carte_service.isCartaSupportata(nome_carta);
			
			if (operazione.equals("aggiungi")) {
				if(isCartaValida) {
					Carte_service.aggiungiCarte(nome_carta, prezzo, quantità);
					Carte carteAggiornate = Carte_service.ottieniCarte();
					session.setAttribute("carta", carteAggiornate);
					session.setAttribute("quantità", quantità);
					session.setAttribute("utente", utente);
					address = "/WEB-INF/view/acquistoCarte.jsp";

					RequestDispatcher dispatcher = request.getRequestDispatcher(address);
					dispatcher.forward(request, response);		
				} else {
					String messaggio = "La carta inserita non appartiene a nessun circuito!";
					response.setHeader("errorMessage", messaggio);
					request.setAttribute("messaggio", messaggio);
				}
			}

		} catch (NamingException | SQLException |

				IllegalArgumentException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Si è verificato un errore durante l'aggiunta della carta");
		}
	}
}
