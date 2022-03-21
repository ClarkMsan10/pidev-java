package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pidev.ConnexionBase;
import pidev.entity.Project;

public class ProjectCrud {

	ConnexionBase myc1 = ConnexionBase.getInstance();
	Connection con = ConnexionBase.getConnection();

	UserCrud userCrud = new UserCrud();
	VoteCrud voteCrud = new VoteCrud();

	public void addProject(Project p) {

		String requete = "INSERT INTO project (title,description,budget,estimation,publishedBy,createdAt)" + "VALUES ('"
				+ p.getTitle() + "','" + p.getDescription() + "','" + p.getBudget() + "','" + p.getEstimation() + "','"
				+ p.getPublishedBy().getId() + "','" + p.getCreatedAt() + "')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(requete);
			System.out.println("Projet ajouté");
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
		}

	}

	public void addProjectTwo(Project p) {

		String requete = "INSERT INTO project (title,description,budget,estimation,publishedBy,createdAt,slug,votes) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setString(1, p.getTitle());
			pst.setString(2, p.getDescription());
			pst.setInt(3, p.getBudget());
			pst.setInt(4, p.getEstimation());
			pst.setInt(5, p.getPublishedBy().getId());
			pst.setDate(6, p.getCreatedAt());
			pst.setString(7, p.getSlug());
			pst.setInt(8, p.getVotes());
			pst.executeUpdate();
			System.out.println("Projet ajouté");
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
		}

	}
	
	
	public void updateProject(Project p) {
		
		String requete ="UPDATE project SET title=?,description=?,estimation=?,budget=?,slug=? WHERE id=?";
		try {
			PreparedStatement pst = con.prepareStatement(requete);
			System.out.println("inthebulding"+p.getTitle());
			pst.setString(1, p.getTitle());
			pst.setString(2, p.getDescription());
			pst.setInt(3, p.getEstimation());
			pst.setInt(4, p.getBudget());
			pst.setString(5, p.getSlug());
			pst.setInt(6, p.getId());
			pst.executeUpdate();
			System.out.println("Projet modifié");
		} catch (SQLException ex) {
			System.out.println("Erreur d'insertion");
			System.out.println(ex.getMessage());
		}
	}

	public Project retrieveProjectById(int id) {

		String requete = "SELECT * FROM project  WHERE id=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == true) {
				Project project = new Project(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("budget"), rs.getInt("estimation"), rs.getDate("createdAt"), rs.getInt("votes"));
				project.setPublishedBy(userCrud.recupIdUser(rs.getInt("publishedBy")));

				return project;
			}
			return null;

		} catch (SQLException ex) {
			System.out.println("Erreur" + ex.getMessage());
			return null;
		}

	}
	
	
	public Project retrieveProjectBySlug(String slug) {

		String requete = "SELECT * FROM project  WHERE slug=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setString(1, slug);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == true) {
				Project project = new Project(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("budget"), rs.getInt("estimation"), rs.getDate("createdAt"), rs.getInt("votes"));
				project.setPublishedBy(userCrud.recupIdUser(rs.getInt("publishedBy")));

				return project;
			}
			return null;

		} catch (SQLException ex) {
			System.out.println("Erreur" + ex.getMessage());
			return null;
		}

	}

	public void updateVoteToProject(int id) {

		int nbre_vote = voteCrud.listVote(id);
		String requete = "UPDATE project SET votes=? where project.id=?";

		try {
			PreparedStatement pst = con.prepareStatement(requete);
			pst.setInt(1, nbre_vote);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public List<Project> retrieveAllProject() {

		String requete = "SELECT * FROM project ORDER BY project.createdAt ASC";

		List<Project> myList = new ArrayList<Project>();

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(requete);

			while (rs.next()) {
				Project p = new Project(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("budget"), rs.getInt("estimation"), rs.getDate("createdAt"), rs.getString("slug"));
				p.setPublishedBy(userCrud.recupIdUser(rs.getInt("publishedBy")));
				myList.add(p);
			}

			return myList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	public boolean verifProjectExist(String slug){
		
		if(retrieveProjectBySlug(slug)!= null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public void deleteProject(String slug) {
		
		String requete = "DELETE FROM project WHERE slug=?";
		
		try {
			PreparedStatement pst;
			pst = con.prepareStatement(requete);
			pst.setString(1, slug);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erreur d'insertion");
			System.out.println(e.getMessage());
		}
	}
}
