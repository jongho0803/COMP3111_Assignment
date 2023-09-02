package ATU_project.Input;

import ATU_project.App;
import ATU_project.Event.*;
import ATU_project.Output.Output;
import ATU_project.Process;
import ATU_project.Student;
import ATU_project.Team;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class to control the table view UI.
 * @author Winnie Lam
 */
public class TableViewParallelController implements Initializable {
	private Stage dialogStage;

	@FXML
	private Button clearBtn;
    @FXML
    private ChoiceBox<String> ViewSelection;

	@FXML
	private TextField searchBox;

	@FXML
	private VBox mainFrame;

    @FXML
    private Button home;

    @FXML
    private Button proceedButton;

    @FXML
    private VBox statListBox;

    @FXML
    private VBox stuListBox;
    
    @FXML
    private HBox tableContainer;
    
	private TableView<InputUI.Statistics> statTable = new TableView<InputUI.Statistics>();
	private TableView<Student> studentTable = new TableView<Student>();

	/**
	 * The sorted team list used in output scenes. Store the sorted team list.
	 */
	private Output output;
	/**
	 * The student list used in to form team. Pass to {@link Process}.
	 */
	private Student[] studentList;

	/**
	 * Event handler for clicking the Clear button.
	 * Clear the search bar.
	 * @param event ActionEvent of clicking the Clear button.
	 */
	@FXML
	void handleClearSearchText(ActionEvent event) {
		searchBox.setText("");
		event.consume();
	}

	/**
	 * Method used to create table column and input student, statistics data from
	 * observable list into tableview. Used in {@link TableViewParallelController#initialize} method.
	 */
	@FXML
    private void makeTable() {
    	ObservableList<Student> student_list = InputUI.get_student_list();
    	ObservableList<InputUI.Statistics> stat_list = InputUI.get_stat_list();
    	
    	studentTable.setEditable(true);
    	TableColumn<Student, Number> indexColumn = new TableColumn<Student, Number>("Index");
    	indexColumn.setSortable(false);
    	indexColumn.setMinWidth(40);
    	indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(studentTable.getItems().indexOf(column.getValue())+1));
    	
		TableColumn<Student, String> studentid_column = new TableColumn<Student, String>("Student_ID");
		studentid_column.setMinWidth(70);
		studentid_column.setCellValueFactory(new PropertyValueFactory<Student, String>("sid"));

		TableColumn<Student, String> studentname_column = new TableColumn<Student, String>("Student_Name");
		studentname_column.setMinWidth(100);
		studentname_column.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));

		TableColumn<Student, String> studentemail_column = new TableColumn<Student, String>("Student_Email");
		studentemail_column.setMinWidth(150);
		studentemail_column.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		
		TableColumn<Student, String> k1energy_column = new TableColumn<Student, String>("K1");
		k1energy_column.setMinWidth(40);
		k1energy_column.setCellValueFactory(new PropertyValueFactory<Student, String>("k1"));

		TableColumn<Student, String> k2energy_column = new TableColumn<Student, String>("K2");
		k2energy_column.setMinWidth(40);
		k2energy_column.setCellValueFactory(new PropertyValueFactory<Student, String>("k2"));

		TableColumn<Student, String> k3trick1_column = new TableColumn<Student, String>("K3_Trick1");
		k3trick1_column.setMinWidth(60);
		k3trick1_column.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getK3_tick1_inString()));

		TableColumn<Student, String> k3trick2_column = new TableColumn<Student, String>("K3_Trick2");
		k3trick2_column.setMinWidth(60);
		k3trick2_column.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getK3_tick2_inString()));

		TableColumn<Student, String> mypreference_column = new TableColumn<Student, String>("My_Preference");
		mypreference_column.setMinWidth(60);
		mypreference_column.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getLeaderPref_inString()));

		TableColumn<Student, String> concerns_column = new TableColumn<Student, String>("Concerns");
		concerns_column.setMinWidth(60);
		concerns_column.setCellValueFactory(new PropertyValueFactory<Student, String>("concerns"));

		studentTable.setItems(student_list);
		studentTable.getColumns().addAll(indexColumn, studentid_column, studentname_column, studentemail_column, k1energy_column, k2energy_column,
				k3trick1_column, k3trick2_column, mypreference_column, concerns_column);
		studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		statTable.setEditable(true);
		
    	TableColumn<InputUI.Statistics, Number> statIndexColumn = new TableColumn<InputUI.Statistics, Number>("Index");
    	statIndexColumn.setSortable(false);
    	statIndexColumn.setMinWidth(30);
    	statIndexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(statTable.getItems().indexOf(column.getValue())+1));
		
		TableColumn<InputUI.Statistics, String> entry_column = new TableColumn<InputUI.Statistics, String>("Entry");
		entry_column.setMinWidth(150);
		entry_column.setCellValueFactory(new PropertyValueFactory<InputUI.Statistics, String>("entry"));

		TableColumn<InputUI.Statistics, String> value_column = new TableColumn<InputUI.Statistics, String>("Value");
		value_column.setMinWidth(50);
		value_column.setCellValueFactory(new PropertyValueFactory<InputUI.Statistics, String>("value"));

		statTable.setItems(stat_list);
		statTable.getColumns().addAll(statIndexColumn, entry_column, value_column);
		statTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

	/**
	 * Initialize the controller. <p>
	 * Set up the table view scene and define the event handler for
	 * ChoiceBox which allows user to select which table to focus on.
	 *
	 * @param location  The location used to resolve relative paths for the root object, or
	 *                  {@code null} if the location is not known.
	 * @param resources The resources used to localize the root object, or {@code null} if
	 *                  the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	if (stuListBox.getChildren().size() > 1)
    		stuListBox.getChildren().remove(1);

    	makeTable();

    	stuListBox.getChildren().addAll(studentTable);
    	statListBox.getChildren().addAll(statTable);

		searchBox.textProperty().addListener((observable, oldValue, newValue) ->
				studentTable.setItems(filterList(newValue.toLowerCase()))
		);

    	ViewSelection.getItems().addAll(
    		    "Student List Only",
    		    "Statistic Only",
    		    "Parallel View"
    		);
    	
    	ViewSelection.setValue("Parallel View");
    	
        EventHandler<ActionEvent> focusOn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				if (ViewSelection.getValue().equals("Parallel View")) {
					try {
						tableContainer.getChildren().clear();
						tableContainer.getChildren().addAll(stuListBox,statListBox);
						stuListBox.setPrefWidth(InputUI.tableViewWidth*2/3);
						statListBox.setPrefWidth(InputUI.tableViewWidth*0.8/3);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if (ViewSelection.getValue().equals("Student List Only")) {
					try {
						tableContainer.getChildren().clear();
						tableContainer.getChildren().addAll(stuListBox);
						stuListBox.setPrefWidth(InputUI.tableViewWidth*2.9/3);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						tableContainer.getChildren().clear();
						tableContainer.getChildren().addAll(statListBox);
						statListBox.setPrefWidth(InputUI.tableViewWidth*2/3);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
        };
		
        ViewSelection.setOnAction(focusOn);
    }

	/**
	 * Event handler for clicking the Back Home button.
	 * Go back to Home screen.
	 * @param event ActionEvent of clicking the Back Home button.
	 */
	@FXML
    void backToHome(ActionEvent event) {
        try {
        	App.HomeStage.setScene(App.HomeScene);
        	App.HomeStage.setTitle("ATU Program - Home Screen");
        	App.HomeStage.show();
        	App.HomeStage.setX((App.width - App.HomeStage.getWidth()) / 2);
        	App.HomeStage.setY((App.height - App.HomeStage.getHeight()) / 2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }

	/**
	 * Event handler for clicking the Proceed button.
	 * Prompt a dialogue window for selecting the next action when the user have confirmed the table. <p>
	 * Add EventHandlers for {@link BackHomeEvent}, {@link CloseProgramEvent}, and {@link ProceedToGraphEvent}.
	 * @param event ActionEvent of clicking the Proceed button.
	 * @throws IOException when having error to construct the Proceeding UI dialogue scene.
	 */
    @FXML
    void onProceedBtnClicked(ActionEvent event) throws IOException{
		Process process = new Process(studentList);
		Team[] teams = process.formTeam();
		File theDir = new File(App.PATH_TO_EXPORT);
		if (!theDir.exists()){
			theDir.mkdirs();
		}
		process.createTeamFile(teams);
		output = new Output(teams);
		output.serialize();
		dialogStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/UI/proceedingUI.fxml"));
		Scene dialogScene = new Scene(root);
		dialogStage.setAlwaysOnTop(true);
		dialogStage.setScene(dialogScene);
		dialogStage.show();
		dialogStage.setTitle("Dialogue: Select the next action");
		dialogStage.addEventHandler(BackHomeEvent.BACK_HOME_EVENT_TYPE, this::onBackHome);
		dialogStage.addEventHandler(CloseProgramEvent.CLOSE_PROGRAM_EVENT_TYPE, this::onCloseProgram);
		dialogStage.addEventHandler(ProceedToGraphEvent.PROCEED_TO_GRAPH_EVENT_TYPE, this::onProceedGraph);
	}

	/**
	 * Event handler for {@link BackHomeEvent}. Fire {@link SetOutputSourceEvent}.
	 * Go back to Home Scene after clicking the Back Home button in the dialogue window. Close the dialogue window.
	 * @param event The BackHomeEvent.
	 */
	public void onBackHome(BackHomeEvent event) {
		proceedButton.fireEvent(new SetOutputSourceEvent(SetOutputSourceEvent.SET_OUTPUT_SOURCE_EVENT_TYPE, output));
		App.HomeStage.setScene(App.HomeScene);
		App.HomeStage.setTitle("ATU Program - Home Screen");
		App.HomeStage.show();
		App.HomeStage.setX((App.width - App.HomeStage.getWidth()) / 2);
		App.HomeStage.setY((App.height - App.HomeStage.getHeight()) / 2);
		dialogStage.close();
	}

	/**
	 * Event handler for {@link CloseProgramEvent}.
	 * Close the application after clicking the Close Program button in the dialogue window. Close the dialogue window.
	 * @param event The CloseProgramEvent.
	 */
	public void onCloseProgram(CloseProgramEvent event) {
		App.HomeStage.close();
		dialogStage.close();
	}

	/**
	 * Event handler for {@link ProceedToGraphEvent}. Fire {@link SetTeacherOutputSceneEvent}.
	 * Go to the Overview page after clicking the Overview Service button in the dialogue window. Close the dialogue window.
	 * @param event The ProceedToGraphEvent.
	 */
	public void onProceedGraph(ProceedToGraphEvent event){
		proceedButton.fireEvent(new SetTeacherOutputSceneEvent(SetTeacherOutputSceneEvent.SET_TEACHER_OUTPUT_SCENE_EVENT_TYPE, output));
		dialogStage.close();
	}

	/**
	 * Setter for {@link TableViewParallelController#studentList}.
	 * @param studentList The student list.
	 */
	public void setStudentList(Student[] studentList){
		this.studentList = studentList;
	}

	/**
	 * Method used to search a certain student through SID or name.
	 * @param searchText The desired information about the student.
	 * @return A filtered student list with the target student.
	 */
	private ObservableList<Student> filterList(String searchText){
		List<Student> filteredList = new ArrayList<>();

		for (Student order : studentList){
			if(searchFindsOrder(order, searchText)){
				filteredList.add(order);
			}
		}
		return FXCollections.observableList(filteredList);
	}

	/**
	 * Method used to interpret the existence of the student. Used in {@link TableViewParallelController#filterList}.
	 * @param order The compared student.
	 * @param searchText The text inputted.
	 * @return boolean instance about whether the student exist.
	 */
	private boolean searchFindsOrder(Student order, String searchText){
		return (order.getName().toLowerCase().contains(searchText)) ||
				(order.getNameWithNoComma().toLowerCase().contains(searchText)) ||
				Long.valueOf(order.getSid()).toString().contains(searchText);
	}

}
