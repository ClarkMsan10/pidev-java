package pidev;

import pidev.entity.Project;
import pidev.entity.User;
import pidev.service.ProjectCrud;
import pidev.service.UserCrud;
import pidev.service.VoteCrud;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserCrud us = new UserCrud();
		
		ProjectCrud pc = new ProjectCrud();
		
		VoteCrud vc = new VoteCrud();
		
		User u = new User("frankarnaud@gmail.com", "bonjour", "Frank", "Arnaud");
		User u1 = new User("armelgamako@gmail.com", "bonjour", "Armel", "Gamako");
		
		//us.addUser(u);
		//us.addUser(u1);
		
		
//	    try {
//	    	System.out.println(us.recupIdUser(u));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		User user1 = us.authUser("frankarnaud@gmail.com", "bonjour");
//		User user2 = us.authUser("armelgamako@gmail.com", "bonjour");
//		
//		Project p = new Project("Troieme Projet", "tu bien maider ? ", 10, 11, user1);
//		Project p1 = new Project("Troieme", "Bonjour, je m'appelle patrick, et je suis un peu fatigué, j'ai besoin de toi,  veux tu bien maider ? ", 10, 11, user1);
//		Project p2 = new Project("rojet", "un peu fatigué, j'ai besoin de toi,  veux tu bien maider ? ", 10, 11, user2);
//		Project p3 = new Project("ieme Pro", "j'ai besoin de toi,  veux tu bien maider ? ", 10, 11, user2);
//		Project p4 = new Project("Tro", "Bonjour,  j'ai besoin de toi,  veux tu bien maider ? ", 10, 11, user1);
//		
//		pc.addProjectTwo(p);
//		pc.addProjectTwo(p1);
//		pc.addProjectTwo(p2);
//		pc.addProjectTwo(p3);
//		pc.addProjectTwo(p4);
		
		//System.out.println(pc.retrieveProjectById(1));
		
		//vc.addVote(3, 1);
		System.out.println(pc.retrieveAllProject());
	    

	}

}
