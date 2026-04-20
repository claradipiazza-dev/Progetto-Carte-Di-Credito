package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import model.Utenti;
import query.Utenti_service;

public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registrazione() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String numero = request.getParameter("numero");
			String email = request.getParameter("email");
			String password = request.getParameter("pass1");
			String operazione = request.getParameter("operazione");
			String address = "";
			response.setContentType("text/plain");
			boolean isCheckMail = Utenti_service.checkMailExistence(email);
			boolean isCheckPass = Utenti_service.checkPassword(password);
			boolean isCheckTel = Utenti_service.checkTelefono(numero);

			if (operazione.equals("registrati")) {
				if (!isCheckMail) {
					if (!isCheckPass) {
						if (!isCheckTel) {
							// Continua con la registrazione
							Utenti utente = Utenti_service.registrazione(nome, cognome, numero, email, password);
							request.setAttribute("utente", utente);
							address = "/WEB-INF/view/messaggi/registrazioneCompletata.jsp";

							RequestDispatcher dispatcher = request.getRequestDispatcher(address);
							dispatcher.forward(request, response);

						} else {
							// Telefono esistente
							String message = "Devi utilizzare un altro numero di telefono in quanto già esistente";
							response.setHeader("errorMessage", message);
							request.setAttribute("messaggio", message);
						}
					} else {
						// Password esistente
						String message = "Devi utilizzare un'altra password per completare la registrazione";
						response.setHeader("errorMessage", message);
						request.setAttribute("messaggio", message);
					}
				} else {
					// Email esistente
					String message = "L'indirizzo email fornito non può essere utilizzato in quanto già esistente!";
					response.setHeader("errorMessage", message);
					request.setAttribute("messaggio", message);
				}
			}

		} catch (SQLException | NamingException | NoSuchAlgorithmException |

				NullPointerException ex) {
			throw new ServletException(ex);
		}
	}

}
