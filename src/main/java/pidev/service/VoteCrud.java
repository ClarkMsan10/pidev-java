package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pidev.ConnexionBase;
import pidev.entity.Vote;

public class VoteCrud {

	ConnexionBase myc1 = ConnexionBase.getInstance();
	Connection con = ConnexionBase.getConnection();

	public void addVote(int id_p, int id_u) {

		String requete = "INSERT INTO vote (id_user, id_project) VALUES (?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setInt(1, id_u);
			pst.setInt(2, id_p);
			pst.executeUpdate();
			System.out.println("Vote ajouté");
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
		}

	}
	
	
	public boolean verifVote(int id_p, int id_u) {
		
		String requete = "SELECT * FROM vote where id_project=? and id_user=?";
		
		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setInt(1, id_p);
			pst.setInt(2, id_u);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == true) {
				Vote vote = new Vote(rs.getInt("id"),rs.getInt("id_project"), rs.getInt("id_user"));
				System.out.println(vote);
				return true;
			}
			
			return false;
			
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
public int listVote(int id_p) {
		
		String requete = "SELECT * FROM vote where id_project=?";
		
		List<Vote> myList = new ArrayList<Vote>();
		
		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setInt(1, id_p);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Vote vote = new Vote(rs.getInt("id"),rs.getInt("id_project"), rs.getInt("id_user"));
				System.out.println(vote);
				myList.add(vote);
			}
			
			return myList.size();
			
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
			return 0;
		}
	}
	
	
	
	public void deleteVote(int id_p, int id_u) {
		
		String requete = "DELETE FROM vote WHERE id_project=? AND id_user=?";
		
		try {
			PreparedStatement pst;
			pst = con.prepareStatement(requete);
			pst.setInt(1, id_p);
			pst.setInt(2, id_u);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erreur d'insertion");
			System.out.println(e.getMessage());
		}
		
	}

}
