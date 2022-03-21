package pidev.gui;

import static pidev.gui.AccueilController.projectSelect;
import static pidev.gui.LoginController.userConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pidev.service.ProjectCrud;
import pidev.service.VoteCrud;

public class ProjectController implements Initializable {
	@FXML
	private Label labPage;
	@FXML
	private Button btnReturnHome;
	@FXML
	private Button btnLogout;
	@FXML
	private Label labDescription;
	@FXML
	private Label labEstimation;
	@FXML
	private Label labBudget;
	@FXML
	private Label labTitle;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnVoter;

	@FXML
	private Label labPublishedBy;

	@FXML
	private Label labVotes;

	VoteCrud vc = new VoteCrud();
	ProjectCrud pc = new ProjectCrud();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		verifVoteForVisibleButton();
		changeButtonVote();
	}

	public Label getLabPage() {
		return labPage;
	}

	public void setLabPage(String labPage) {
		this.labPage.setText(labPage);
	}

	public Button getBtnReturnHome() {
		return btnReturnHome;
	}

	public void setBtnReturnHome(Button btnReturnHome) {
		this.btnReturnHome = btnReturnHome;
	}

	public Button getBtnLogout() {
		return btnLogout;
	}

	public void setBtnLogout(Button btnLogout) {
		this.btnLogout = btnLogout;
	}

	public Label getLabDescription() {
		return labDescription;
	}

	public void setLabDescription(String labDescription) {
		this.labDescription.setText(labDescription);
	}

	public Label getLabEstimation() {
		return labEstimation;
	}

	public void setLabEstimation(String labEstimation) {
		this.labEstimation.setText(labEstimation);
	}

	public Label getLabBudget() {
		return labBudget;
	}

	public void setLabBudget(String labBudget) {
		this.labBudget.setText(labBudget);
	}

	public Label getLabTitle() {
		return labTitle;
	}

	public void setLabTitle(String labTitle) {
		this.labTitle.setText(labTitle);
	}

	public Button getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(Button btnEdit) {
		this.btnEdit = btnEdit;
	}

	public Button getBtnSupprimer() {
		return btnSupprimer;
	}

	public void setBtnSupprimer(Button btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}

	public Button getBtnVoter() {
		return btnVoter;
	}

	public void setBtnVoter(Button btnVoter) {
		this.btnVoter = btnVoter;
	}

	public Label getLabPublishedBy() {
		return labPublishedBy;
	}

	public void setLabPublishedBy(String labPublishedBy) {
		this.labPublishedBy.setText(labPublishedBy);
	}

	public Label getLabVotes() {
		return labVotes;
	}

	public void setLabVotes(String labVotes) {
		this.labVotes.setText(labVotes);
	}

	// Event Listener on Button[#btnReturnHome].onAction
	@FXML
	public void returnHome(ActionEvent event) throws IOException {

		System.out.println(userConnect);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));

		AnchorPane anchor = loader.load();
		AccueilController ac = loader.getController();
		ac.setLabelNOmUser("Bonjour " + userConnect.getFirstname() + " " + userConnect.getLastname());
		((Node) event.getSource()).getScene().setRoot(anchor);

	}

	// Event Listener on Button[#btnLogout].onAction
	@FXML
	public void logout(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));

		AnchorPane anchor;
		try {
			anchor = loader.load();
			((Node) event.getSource()).getScene().setRoot(anchor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#btnEdit].onAction
	@FXML
	public void editProject(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyProject.fxml"));
		AnchorPane anchor;
		try {
			anchor = loader.load();
			ModifyProjectController mc = loader.getController();
			mc.setLabUserConnect("Bonjour " + userConnect.getFirstname() + " " + userConnect.getLastname());
			mc.setFieldDescription(projectSelect.getDescription());
			mc.setFieldEstimation(""+projectSelect.getEstimation());
			mc.setFieldBudget(""+projectSelect.getBudget());
			mc.setFieldTitle(projectSelect.getTitle());
			((Node) event.getSource()).getScene().setRoot(anchor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// Event Listener on Button[#btnSupprimer].onAction
	@FXML
	public void deleteProject(ActionEvent event) {
		
		pc.deleteProject(projectSelect.getSlug());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));

		AnchorPane anchor;
		try {
			anchor = loader.load();
			AccueilController ac = loader.getController();
			ac.setLabelNOmUser("Bonjour " + userConnect.getFirstname() + " " + userConnect.getLastname());
			((Node) event.getSource()).getScene().setRoot(anchor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	// Event Listener on Button[#btnVoter].onAction
	@FXML
	public void votedAction(ActionEvent event) {

		if (vc.verifVote(projectSelect.getId(), userConnect.getId())) {
			vc.deleteVote(projectSelect.getId(), userConnect.getId());
			pc.updateVoteToProject(projectSelect.getId());
			projectSelect = pc.retrieveProjectById(projectSelect.getId());
			resetScene();

		} else {
			vc.addVote(projectSelect.getId(), userConnect.getId());
			pc.updateVoteToProject(projectSelect.getId());
			projectSelect = pc.retrieveProjectById(projectSelect.getId());
			resetScene();
		}
	}

	public void verifVoteForVisibleButton() {

		if (projectSelect.getPublishedBy().getId() != userConnect.getId()) {
			btnEdit.setVisible(false);
			btnSupprimer.setVisible(false);
			btnVoter.setVisible(true);
		} else {
			btnEdit.setVisible(true);
			btnSupprimer.setVisible(true);
			btnVoter.setVisible(false);
		}
	}

	public void changeButtonVote() {

		if (vc.verifVote(projectSelect.getId(), userConnect.getId())) {
			btnVoter.setText("Devoter");
			btnVoter.setStyle("-fx-background-color: red;");
		} else {
			btnVoter.setText("Voter");
			btnVoter.setStyle("-fx-background-color: green;");
		}
	}

	public void resetScene() {

		setLabTitle(projectSelect.getTitle());
		setLabDescription(projectSelect.getDescription());
		setLabEstimation("" + projectSelect.getEstimation() + "");
		setLabBudget("" + projectSelect.getBudget() + "");
		setLabPublishedBy("" + projectSelect.getPublishedBy().getFirstname() + " "
				+ projectSelect.getPublishedBy().getLastname());
		setLabVotes("" + projectSelect.getVotes() + "");

		verifVoteForVisibleButton();
		changeButtonVote();
	}
}
