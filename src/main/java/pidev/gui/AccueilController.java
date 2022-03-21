package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.entity.Project;
import pidev.service.ProjectCrud;

import static pidev.gui.LoginController.userConnect;

public class AccueilController implements Initializable {

	@FXML
	private Label labelNOmUser;

	@FXML
	private Button btnDeconnexion;

	@FXML
	private TableColumn<Project, Integer> colId;

	@FXML
	private TableColumn<Project, String> colTitle;

	@FXML
	private TableColumn<Project, Integer> colBudget;

	@FXML
	private TableColumn<Project, Date> colDate;

	@FXML
	private TableColumn<Project, Integer> colEstimation;

	@FXML
	private TableView<Project> tableProject;

	@FXML
	private Label idLabelProject;

	@FXML
	private Button btnProject;

	@FXML // fx:id="btnAddProject"
	private Button btnAddProject; // Value injected by FXMLLoader

	@FXML
	private TableColumn<Project, Integer> colVotes;

	public static Project projectSelect;

	ProjectCrud projectCrud = new ProjectCrud();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Project> projects;
		projects = (ArrayList<Project>) projectCrud.retrieveAllProject();
		System.out.println(projects);
		ObservableList<Project> obs = FXCollections.observableArrayList(projects);
		tableProject.setItems(obs);
		colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colBudget.setCellValueFactory(new PropertyValueFactory<>("Budget"));
		colEstimation.setCellValueFactory(new PropertyValueFactory<>("Estimation"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("CreatedAt"));
		colVotes.setCellValueFactory(new PropertyValueFactory<>("Votes"));

		projectSelect = null;

		btnProject.setDisable(true);

		tableProject.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			projectSelect = newValue;
			affichelabel(newValue);
		});

	}

	public Label getLabelNOmUser() {
		return labelNOmUser;
	}

	public void setLabelNOmUser(String labelNOmUser) {
		this.labelNOmUser.setText(labelNOmUser);
	}

	@FXML
	void deconnect(ActionEvent event) {
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

	public void affichelabel(Project p) {

		if (p != null) {
			idLabelProject.setText(p.getTitle());
			btnProject.setDisable(false);

		} else {
			idLabelProject.setText("");
			btnProject.setDisable(true);
		}

	}

	@FXML
	void displayProject(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("project.fxml"));
		AnchorPane anchor = loader.load();
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

	}

	@FXML
	void addProject(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addProject.fxml"));
		AnchorPane anchor = loader.load();
		AddProjectController ac = loader.getController();
		ac.setLabUserConnect(userConnect.getFirstname() + " " + userConnect.getLastname());
		((Node) event.getSource()).getScene().setRoot(anchor);
	}

}
