package controller;

import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import query.CarteUtenti_service;
import model.Utenti;
import model.CarteUtenti;
import query.Carte_service;

@WebServlet("/AcquistoCarte")
public class AcquistoCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcquistoCarte() {
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
			String cvvString = request.getParameter("cvvCasuale");
			String prezzoString = request.getParameter("prezzoCarta");
			String quantitàString = request.getParameter("quantita");
			double prezzo = Double.parseDouble(prezzoString);
			int cvv = Integer.parseInt(cvvString);
			int quantità = Integer.parseInt(quantitàString);
			String address = "";

			CarteUtenti carte = CarteUtenti_service.ottieniCarte(utenti.getId());
			// Controllo della quantità disponibile della carta
			int quantitàDisponibile = Carte_service.getQuantità(numerocarta);
			boolean quantitàValida = quantità <= quantitàDisponibile;
			session.removeAttribute("errore");

			if (quantitàDisponibile == 0) {

				String message = "Carta esaurita";
				session.setAttribute("quantitàValida", quantitàValida);
				request.setAttribute("errore", message);
				address = "/WEB-INF/view/acquistoCarte.jsp";
			}

			else if (quantità > quantitàDisponibile) {

				String errorMessage = "Quantità superiore a quella disponibile.";
				request.setAttribute("errore", errorMessage);
				address = "/WEB-INF/view/acquistoCarte.jsp";
			}

			else {

				session.setAttribute("carteu", carte);
				session.setAttribute("nomecarta", nomecarta);
				session.setAttribute("numerocarta", numerocarta);
				session.setAttribute("cvv", cvv);
				session.setAttribute("quantità", quantità);
				session.setAttribute("prezzo", prezzo);

				address = "/WEB-INF/view/completaAcquisto.jsp";
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);

		} catch (SQLException | NamingException | NullPointerException e) {
			throw new ServletException(e);
		}

	}

}
