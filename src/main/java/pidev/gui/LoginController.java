package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidev.entity.User;
import pidev.service.UserCrud;

public class LoginController {
	@FXML
	private TextField email;
	
	@FXML
	private Button btnConnexion;

	@FXML
	private Label labelMessageAuth;

	@FXML
	private PasswordField password;

	public static User userConnect;

//	private Parent root;
//	private Scene scene;
//	private Stage stage;

	/**
	 * Initializes the controller class.
	 */
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	UserCrud serviceCRud = new UserCrud();

	public TextField getEmail() {
		return email;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}


	public PasswordField getPassword() {
		return password;
	}

	public void setPassword(PasswordField password) {
		this.password = password;
	}

	public Button getBtnConnexion() {
		return btnConnexion;
	}

	public void setBtnConnexion(Button btnConnexion) {
		this.btnConnexion = btnConnexion;
	}

	public Label getLabelMessageAuth() {
		return labelMessageAuth;
	}

	public void setLabelMessageAuth(Label labelMessageAuth) {
		this.labelMessageAuth = labelMessageAuth;
	}

	public static User getUserConnect() {
		return userConnect;
	}

	public static void setUserConnect(User userConnect) {
		LoginController.userConnect = userConnect;
	}

	// Event Listener on Button[#btnConnexion].onAction
	@FXML
	public void authenticate(ActionEvent event) {

		String mail = email.getText();
		String passwd = password.getText();

		userConnect = serviceCRud.authUser(mail, passwd);

		if (userConnect != null) {

			email.setText("");
			password.setText("");

			try {
				// root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
				System.out.println(userConnect);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));

				AnchorPane anchor = loader.load();
				AccueilController ac = loader.getController();
				ac.setLabelNOmUser("Bonjour " + userConnect.getFirstname() + " " + userConnect.getLastname());
				// stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				// scene = new Scene(root);
				// stage.setScene(scene);
				// stage.show();
				((Node) event.getSource()).getScene().setRoot(anchor);

			} catch (IOException ex) {
				System.out.println("Error : " + ex.getMessage());
			}

		} else {
			email.setText("");
			password.setText("");
			labelMessageAuth.setText("Erreur d'authentification");
		}

	}
}
