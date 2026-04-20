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

import model.CarteUtenti;
import model.NumeriCarte;
import query.CarteUtenti_service;
import query.NumeriCarte_service;

/**
 * Servlet implementation class Credito
 */
@WebServlet("/Credito")

public class Credito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Credito() {
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
			String numero = request.getParameter("numerocarta");
			String address = "";
			boolean isCartaValida = NumeriCarte_service.checkCarta(numero);
			boolean isCheckCarta = CarteUtenti_service.checkCarta(numero);
			if (isCartaValida) {
				NumeriCarte saldo = NumeriCarte_service.getSaldoByNumeroCarta(numero);
				request.setAttribute("saldo", saldo);

				address = "/WEB-INF/view/visualizzaSaldo.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
				
			} else if (isCheckCarta) {
				CarteUtenti credito = CarteUtenti_service.getCreditoCarta(numero);
				request.setAttribute("credito", credito);

				address = "/WEB-INF/view/visualizzaSaldo.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
				
			} else {
				String message = "Il numero di carta che hai inserito non è valido!";
				response.setHeader("errorMessage", message);
			}

		} catch (NamingException | SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
