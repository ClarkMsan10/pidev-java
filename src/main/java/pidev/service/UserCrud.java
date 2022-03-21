package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pidev.ConnexionBase;
import pidev.entity.User;

public class UserCrud {

	ConnexionBase myc1 = ConnexionBase.getInstance();
	Connection con = ConnexionBase.getConnection();

	public void addUser(User u) {
		String requete = "INSERT INTO user (email,password,firstname,lastname)" + "VALUES ('" + u.getEmail() + "','"
				+ u.getPassword() + "','" + u.getFirstname() + "','" + u.getLastname() + "')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(requete);
			System.out.println("Utilisateur ajouter");
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
		}

	}

	public void updateUser(User u) {

		String requete = "UPDATE user set email=?, password=?, firstname=?, lastname=? where id=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setString(1, u.getEmail());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getFirstname());
			pst.setString(4, u.getLastname());
			pst.setInt(5, u.getId());
			pst.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Echec Mise A jour");
		}

	}

	public boolean auth(String mail, String pswd) {

		String requete = "SELECT * FROM user WHERE email=? AND password=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setString(1, mail);
			pst.setString(2, pswd);
			ResultSet rs = pst.executeQuery();

			if (rs.next() != true) {

				System.out.println("Erreur d'authentification");
				return false;
			}

			else {

				System.out.println("AUTHENTIFICATION Verifié");
				return true;
			}
		} catch (SQLException ex) {

			System.err.println("Erreur : " + ex.getMessage());
			return false;
		}

	}

	public User authUser(String mail, String pswd) {

		String requete = "SELECT * FROM user WHERE email=? AND password=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setString(1, mail);
			pst.setString(2, pswd);
			ResultSet rs = pst.executeQuery();

			if (rs.next() != true) {

				System.out.println("Erreur d'authentification");
				return null;
			}

			else {

				System.out.println("AUTHENTIFICATION Verifié");
				User user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"),
						rs.getString("firstname"), rs.getString("lastname"));

				return user;
			}
		} catch (SQLException ex) {

			System.err.println("Erreur : " + ex.getMessage());
			return null;
		}

	}

	public User recupIdUser(User u) throws SQLException {
		String requete = "SELECT * FROM user  WHERE email='" + u.getEmail() + "'";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == true) {
				User user = new User(rs.getString("email"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname"));

				return user;
			}
			return null;

		} catch (SQLException ex) {
			System.out.println("Erreur" + ex.getMessage());
			return null;
		}

	}
	
	
	public User recupIdUser(int id) throws SQLException {
		String requete = "SELECT * FROM user  WHERE id=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == true) {
				User user = new User(rs.getInt("id"),rs.getString("email"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname"));

				return user;
			}
			return null;

		} catch (SQLException ex) {
			System.out.println("Erreur" + ex.getMessage());
			return null;
		}

	}

}
