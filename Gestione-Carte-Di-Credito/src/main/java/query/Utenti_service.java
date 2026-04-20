package query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.Utenti;

public class Utenti_service {

	// METODO PER IL LOGIN

	public static Utenti accesso(String email, String pass)
			throws SQLException, NamingException, NoSuchAlgorithmException {

		Utenti utente = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE email=? and password=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, pass);
			rs = statement.executeQuery();

			if (rs.next()) {
				// Recupera l'ID dell'utente dal ResultSet
				int idUtente = rs.getInt("id_utenti");
				// Setta l'ID nell'oggetto Utenti solo se non è zero
				if (idUtente != 0) {
					utente = new Utenti(idUtente, rs.getString("nome"), rs.getString("cognome"),
							rs.getString("telefono"), rs.getString("email"), rs.getString("password"),
							rs.getString("ruolo"));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return utente;
	}

	// METODO PER LA REGISTRAZIONE

	public static Utenti registrazione(String nome, String cognome, String numero, String email, String pass)
			throws NamingException, SQLException, NoSuchAlgorithmException {

		Utenti utente = null;
		Connection connection = null;
		PreparedStatement checkStatement = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			if (pass != null) { // Aggiunto il controllo per password non nulla
				String passwordHash;
				passwordHash = passwordHash(pass);
				connection = Database.execute();
				String checkSql = "SELECT * FROM utenti WHERE email=? AND password=?;";
				checkStatement = connection.prepareStatement(checkSql);
				checkStatement.setString(1, email);
				checkStatement.setString(2, passwordHash);
				result = checkStatement.executeQuery();
				if (!result.next()) {
					String ruolo = "utente"; // Imposta ruolo predefinito come "utente"
					String sql2 = "INSERT INTO utenti (nome, cognome, telefono, email, password, ruolo) VALUES (?,?,?,?,?,?)";
					statement = connection.prepareStatement(sql2);
					statement.setString(1, nome);
					statement.setString(2, cognome);
					statement.setString(3, numero);
					statement.setString(4, email);
					statement.setString(5, passwordHash);
					statement.setString(6, ruolo);
					statement.executeUpdate();
					utente = new Utenti(nome, cognome, numero, email, pass, ruolo);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo il result set
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// Chiudi il checkStatement
			if (checkStatement != null) {
				try {
					checkStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// Chiudi lo statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// Chiudi la connessione
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return utente;

	}

	// METODO PER VERIFICARE L'ESISTENZA DELL'EMAIL

	public static boolean checkMailExistence(String email) throws NamingException, SQLException {

		boolean esiste = false; // se l'email è nel DB, allora verrà settato a true
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE email=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			rs = statement.executeQuery();

			while (rs.next()) // SE TROVA UN RECORD, QUESTO SARA' UNICO
			{
				esiste = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER L'HASH DELLA PASSWORD

	public static String passwordHash(String password) throws NoSuchAlgorithmException {

		String passwordHash;

		// Istanza di MD5
		MessageDigest md = MessageDigest.getInstance("MD5");

		// Aggiunta di bytes alla password
		md.update(password.getBytes());

		// Ottenimento dei bytes dell'hash
		byte[] bytes = md.digest();

		// Questo bytes[] ha i bytes in formato decimale
		// vengono convertiti in formato esadecimale
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// Ottenimento dell'hash della password
		passwordHash = sb.toString();
		return passwordHash;
	}

	// METODO PER LA PASSWORD CASUALE

	private static String passwordCasuale() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz?!<>-*@#$%&[]{}0123456789";
		int alphabetLength = alphabet.length();
		String password = "";
		boolean hasNumber = false; // Variabile per tenere traccia se la password contiene un numero

		// Genera una password casuale di lunghezza 20
		for (int i = 0; i < 20; i++) {
			int randomIndexCharInAlphabet = (int) (Math.random() * alphabetLength);
			char randomChar = alphabet.charAt(randomIndexCharInAlphabet);
			password += randomChar;

			// Controlla se il carattere generato è un numero
			if (Character.isDigit(randomChar)) {
				hasNumber = true;
			}
		}

		// Se la password generata non contiene un numero, sostituisci casualmente uno
		// dei caratteri con un numero
		if (!hasNumber) {
			int randomIndex = (int) (Math.random() * 20);
			char randomNumber = (char) ('0' + (int) (Math.random() * 10)); // Genera un numero casuale tra '0' e '9'
			password = password.substring(0, randomIndex) + randomNumber + password.substring(randomIndex + 1);
		}

		return password;
	}

	// METODO PER IL RECUPERO PASSWORD

	public static Utenti recuperoPassword(String email) throws SQLException, NamingException, NoSuchAlgorithmException {

		Utenti utente = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE email=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();

			if (result.next()) {

				String passwordC = passwordCasuale();
				String passwordHash = passwordHash(passwordC); // Calcola l'hash della nuova password

				// Aggiungi istruzioni di log per debug
				System.out.println("Nuova password generata: " + passwordC);
				System.out.println("Nuova password hash: " + passwordHash);

				cambioPassword(email, passwordC);
				utente = new Utenti();
				utente.setPassword(passwordC); // Imposta la nuova password nell'oggetto Utenti
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return utente;
	}

	// METODO PER IL CAMBIO PASSWORD

	public static Utenti cambioPassword(String email, String password)
			throws NoSuchAlgorithmException, NamingException, SQLException {

		String passwordHash = passwordHash(password);
		Utenti utente = accesso(email, password);
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "UPDATE utenti SET password = ? WHERE email = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, passwordHash);
			statement.setString(2, email);
			statement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per
			// garantire il rilascio delle risorse
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return utente;
	}

	// METODO PER RICARICARE IL CREDITO

	public static void ricaricaCreditoUtente(double importo, String numero_carta, int id_utente)
			throws SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			// Aggiorna il credito con la ricarica
			String sql = "UPDATE carteutenti SET credito = credito + ? WHERE num_carta=? and utenti_id=(SELECT id_utenti FROM utenti WHERE id_utenti=?);";
			statement = connection.prepareStatement(sql);
			statement.setDouble(1, importo); // Prima l'importo
			statement.setString(2, numero_carta); // Poi il numero della carta
			statement.setInt(3, id_utente);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per
			// garantire il rilascio delle risorse
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void aggiornaUtente(Utenti utente) throws SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "UPDATE utenti SET password = ? WHERE email = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, utente.getPassword());
			statement.setString(2, utente.getEmail());
			statement.executeUpdate();
			
		} finally {
			// Chiudo le risorse
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	// METODO PER VERIFICARE L'ESISTENZA DELLA PASSWORD

	public static boolean checkPassword(String password)
			throws NamingException, SQLException, NoSuchAlgorithmException {

		boolean esiste = false; // se la password è nel DB, allora verrà settato a true
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE password=?";
			statement = connection.prepareStatement(sql);
			String passwordHash = passwordHash(password);
			statement.setString(1, passwordHash);
			rs = statement.executeQuery();

			if (rs.next()) // SE TROVA UN RECORD, QUESTO SARA' UNICO
			{
				esiste = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER VERIFICARE L'ESISTENZA DI UN NUMERO DI TELEFONO

	public static boolean checkTelefono(String numero) throws NamingException, SQLException, NoSuchAlgorithmException {

		boolean esiste = false; // se il telefono è nel DB, allora verrà settato a true
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE telefono=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero);
			rs = statement.executeQuery();

			if (rs.next()) // SE TROVA UN RECORD, QUESTO SARA' UNICO
			{
				esiste = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudi la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return esiste; // falso se non esiste, vero se esiste
	}

}
