package query;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.NumeriCarte;

public class NumeriCarte_service {

	// METODO PER OTTENERE IL SALDO

	public static NumeriCarte getSaldoByNumeroCarta(String numero_carta)
			throws SQLException, NamingException, NoSuchAlgorithmException {

		NumeriCarte carte = new NumeriCarte();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT credito FROM numericarta WHERE numero_carta=? ;";
			connection = Database.execute();
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
			rs = statement.executeQuery();

			if (rs.next()) {

				carte.setCredito(rs.getDouble("credito"));
			}

			else {
				System.out.println("Nessuna carta trovata: ");
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

		return carte;
	}

	// METODO PER VERIFICARE L'ESISTENZA DELLA CARTA

	public static boolean checkCarta(String numero) throws NamingException, SQLException {

		boolean esiste = false;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM numericarta WHERE numero_carta=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero);
			result = statement.executeQuery();

			while (result.next()) {

				esiste = true;
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

		return esiste; // falso se non esiste, vero se esiste
	}

}
