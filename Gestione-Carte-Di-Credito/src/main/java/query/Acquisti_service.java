package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import database.Database;
import model.Acquisti;

public class Acquisti_service {

	// METODO PER OTTENERE GLI ACQUISTI

	public static Acquisti ottieniAcquisti(int utente_id) throws NamingException, SQLException {

		Acquisti acquisto = null;
		Acquisti acquisti = new Acquisti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM acquisti WHERE utente_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, utente_id);
			result = statement.executeQuery();

			while (result.next()) {
				acquisto = new Acquisti(result.getInt("id_acquisti"), result.getString("nome_carta"),
						result.getString("numero_carta"), result.getInt("cvv"), result.getDouble("prezzo"),
						result.getInt("quantità"), result.getInt("utente_id"));
				acquisti.setLista(acquisto);
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

		return acquisti;

	}

	// METODO PER OTTENERE GLI ACQUISTI DATO IL NUMERO E L'ID

	public static Acquisti ottieniAcquistiUtente(String numero_carta, int utente_id) throws NamingException, SQLException {

		Acquisti acquisto = null;
		Acquisti acquisti = new Acquisti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM acquisti WHERE numero_carta=? AND utente_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
			statement.setInt(2, utente_id);
			result = statement.executeQuery();

			while (result.next()) {
				acquisto = new Acquisti(result.getInt("id_acquisti"), result.getString("nome_carta"),
						result.getString("numero_carta"), result.getInt("cvv"), result.getDouble("prezzo"),
						result.getInt("quantità"), result.getInt("utente_id"));
				acquisti.setLista(acquisto);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudi la connessione, lo statement e il ResultSet nel blocco finally per
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

		return acquisti;

	}

}
