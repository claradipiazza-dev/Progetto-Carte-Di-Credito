package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servizi.Mail;
import model.Utenti;
import query.Utenti_service;

/**
 * Servlet implementation class RecuperoPassword
 */
@WebServlet("/RecuperoPassword")
public class RecuperoPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperoPassword() {
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
			String email = request.getParameter("email3");
			String operazione = request.getParameter("operazione");
			Utenti utente = Utenti_service.recuperoPassword(email);
			String address = "";
			response.setContentType("text/plain");
			boolean emailEsistente = Utenti_service.checkMailExistence(email);

			if (operazione.equals("recupera")) {
				if (emailEsistente) {
					String passwordC = utente.getPassword();
					Mail.inviaEmail(email, passwordC);
					address = "/WEB-INF/view/invioEmail.jsp";

					RequestDispatcher dispatcher = request.getRequestDispatcher(address);
					dispatcher.forward(request, response);
				} else {
					String message = "L'indirizzo email fornito non esiste nel nostro sistema!";
					response.setHeader("errorMessage", message);
					request.setAttribute("messaggio", message);
				}
			}

		} catch (SQLException | NamingException | MessagingException |

				NoSuchAlgorithmException ex) {
			throw new ServletException(ex);
		}
	}

}
