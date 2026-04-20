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

import model.Carte;
import query.Carte_service;

/**
 * Servlet implementation class ModificaCarta
 */
@WebServlet("/ModificaCarta")
public class ModificaCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaCarta() {
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
			String numeroCarta = request.getParameter("numeroCarta");
			String nomeCartaModifica = request.getParameter("nomeCartaModifica");
			double prezzoCartaModifica = Double.parseDouble(request.getParameter("prezzoCartaModifica"));

			Carte_service.aggiornaCarte(numeroCarta, nomeCartaModifica, prezzoCartaModifica);
			Carte cartaAggiornata = Carte_service.ottieniCartaByNumero(numeroCarta);
			session.setAttribute("carta", cartaAggiornata);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/viewCartaModificata.jsp");
			dispatcher.forward(request, response);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

	}

}
