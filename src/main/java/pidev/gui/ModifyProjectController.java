package pidev.gui;

import static pidev.gui.AccueilController.projectSelect;
import static pidev.gui.LoginController.userConnect;

import java.io.IOException;

import com.github.slugify.Slugify;
import com.google.common.primitives.Chars;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import pidev.entity.Project;
import pidev.service.ProjectCrud;
import pidev.service.VoteCrud;

public class ModifyProjectController {

	@FXML
	private Label labUserConnect;
	@FXML
	private TextField fieldTitle;
	@FXML
	private TextField fieldEstimation;
	@FXML
	private TextField fieldBudget;
	@FXML
	private TextArea fieldDescription;
	@FXML
	private Button btnAddProject;

	@FXML
	private Label labMessageError;

	@FXML
	private Button btnReturnHome;

	VoteCrud vc = new VoteCrud();
	ProjectCrud pc = new ProjectCrud();

	public Label getLabUserConnect() {
		return labUserConnect;
	}

	public void setLabUserConnect(String labUserConnect) {
		this.labUserConnect.setText(labUserConnect);
	}

	public TextField getFieldTitle() {
		return fieldTitle;
	}

	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle.setText(fieldTitle);
	}

	public TextField getFieldEstimation() {
		return fieldEstimation;
	}

	public void setFieldEstimation(String fieldEstimation) {
		this.fieldEstimation.setText(fieldEstimation);
	}

	public TextField getFieldBudget() {
		return fieldBudget;
	}

	public void setFieldBudget(String fieldBudget) {
		this.fieldBudget.setText(fieldBudget);
	}

	public TextArea getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription.setText(fieldDescription);
	}

	public Button getBtnAddProject() {
		return btnAddProject;
	}

	public void setBtnAddProject(Button btnAddProject) {
		this.btnAddProject = btnAddProject;
	}

	public Button getBtnReturnHome() {
		return btnReturnHome;
	}

	public void setBtnReturnHome(Button btnReturnHome) {
		this.btnReturnHome = btnReturnHome;
	}

	public Label getLabMessageError() {
		return labMessageError;
	}

	public void setLabMessageError(String labMessageError) {
		this.labMessageError.setText(labMessageError);
	}

	// Event Listener on TextField[#fieldEstimation].onKeyPressed
	@FXML
	public void tapKeyEstimation(KeyEvent event) {
		fieldEstimation.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				char num[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '' };
				char ar[] = t.getCharacter().toCharArray();
				char ch = ar[t.getCharacter().toCharArray().length - 1];
				if (!Chars.contains(num, ch)) {
					t.consume();
				}

			}

		});

	}

	// Event Listener on TextField[#fieldBudget].onKeyPressed
	@FXML
	public void tapKeyBudget(KeyEvent event) {
		fieldBudget.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				char num[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '' };
				char ar[] = t.getCharacter().toCharArray();
				char ch = ar[t.getCharacter().toCharArray().length - 1];
				if (!Chars.contains(num, ch)) {
					t.consume();
				}

			}

		});

	}

	// Event Listener on Button[#btnAddProject].onAction
	@FXML
	public void addProject(ActionEvent event) {
		
		String title = fieldTitle.getText();
		String description = fieldDescription.getText();
		int budget = Integer.parseInt(fieldBudget.getText());
		int estimation = Integer.parseInt(fieldEstimation.getText());

		Slugify slg = new Slugify();
		String slug = slg.slugify(title);

		Project test = pc.retrieveProjectBySlug(slug);

		if (test == null || test.getId() == projectSelect.getId()) {
			Project project = new Project(title, description, budget, estimation, userConnect, slug);
			project.setId(projectSelect.getId());
			
			pc.updateProject(project);

			System.out.println(slug);
			
			projectSelect = pc.retrieveProjectBySlug(slug);

			System.out.println(projectSelect);
			
			if (projectSelect != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("project.fxml"));
				AnchorPane anchor;
				try {
					anchor = loader.load();
					ProjectController pc = loader.getController();
					pc.setLabPage(userConnect.getFirstname() + " " + userConnect.getLastname());
					pc.setLabTitle(projectSelect.getTitle());
					pc.setLabDescription(projectSelect.getDescription());
					pc.setLabEstimation("" + projectSelect.getEstimation() + "");
					pc.setLabBudget("" + projectSelect.getBudget() + "");
					pc.setLabPublishedBy("" + projectSelect.getPublishedBy().getFirstname() + " "
							+ projectSelect.getPublishedBy().getLastname());
					pc.setLabVotes("" + projectSelect.getVotes() + "");
					((Node) event.getSource()).getScene().setRoot(anchor);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			setLabMessageError("Il y'a déja un projet qui porte ce nom");
		}

	}

	@FXML
	void returnHome(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));

		AnchorPane anchor;
		try {
			anchor = loader.load();

			AccueilController ac = loader.getController();
			ac.setLabelNOmUser("Bonjour " + userConnect.getFirstname() + " " + userConnect.getLastname());
			((Node) event.getSource()).getScene().setRoot(anchor);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
