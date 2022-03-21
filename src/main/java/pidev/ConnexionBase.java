package pidev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBase {

	public String url= "jdbc:mysql://localhost:3306/pidev-java";
	public String login= "root";
	public String pwd= "";
	public static Connection con;
	public static ConnexionBase myc1 ;
	
    private ConnexionBase (){
        try {
         con = DriverManager.getConnection(url, login, pwd);
         System.out.println("connexion reussie");
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
         System.out.println("connexion echouée");
     }
 }

 public static Connection getConnection(){
     return con;
 }

 public static ConnexionBase getInstance(){
       if(myc1 == null)
         {
             myc1 = new ConnexionBase();
         }
         return myc1;
 }
}
