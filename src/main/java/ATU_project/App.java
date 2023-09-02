package ATU_project;

import ATU_project.Event.*;
import ATU_project.Output.OutputScene;
import ATU_project.Output.TeacherOutputScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static javafx.stage.Screen.getPrimary;

/**
 * The JavaFX application that launches the program.
 * @author Yoanna Lo, Winnie Lam
 */
public class App extends Application{
	/**
	 * Scene for home page.
	 */
	public static Scene HomeScene;
	/**
	 * Primary stage.
	 */
	public static Stage HomeStage;
	/**
	 * Scene for output page.
	 */
	private OutputScene outputScene;
	/**
	 * Scene for teacher output page.
	 */
	private TeacherOutputScene teacherOutputScene;
	private HomeScreenController controller;
	/**
	 * Screen size of running device.
	 */
	private static Rectangle2D screenBounds = getPrimary().getVisualBounds();
	/**
	 * Width of screen size of running device.
	 */
	public static double width = screenBounds.getWidth();
	/**
	 * Height of screen size of running device.
	 */
	public static double height = screenBounds.getHeight();

	/**
	 * Path to the export directory.
	 */
	public static final String PATH_TO_EXPORT = "export";

	/**
	 * Path to the location that stores the Team.csv.
	 */
	public static final String PATH_TO_TEAM_CSV = PATH_TO_EXPORT + "/Team.csv";

	/**
	 * Path to the location that stores the output.ser.
	 */
	public static final String PATH_TO_OUTPUT_SER = PATH_TO_EXPORT + "/output.ser";

	/**
	 * Path to the location that stores the StuPi.csv.
	 */
	public static final String PATH_TO_STUDENT_CSV = "src/main/resources/StuPi.csv";

	/**
	 * The main methods used to entry the start methods
	 * @param args Command-Line arguments.
	 */
    public static void main(String args[]) {
        Application.launch(args);
    }

	/**
	 * The methods used to represent the entry point of Application
	 * and setting up the primary stage for this application <p>
	 * Add Event Handlers for {@link SetOutputSceneEvent}, {@link BackHomeEvent}, {@link SetOutputSourceEvent}, {@link SetTeacherOutputSceneEvent}, and {@link DeleteLastTeamEvent}.
	 * @param primaryStage the primary stage for this application, onto which
	 * the application scene can be set.
	 * Applications may create other stages, if needed, but they will not be
	 * primary stages.
	 * @throws Exception When fails to construct the GUI.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		HomeStage = primaryStage;
		HomeStage.setTitle("ATU Program - Home Screen");

		final var fxml = getClass().getResource("/UI/home-screen.fxml");
		final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
		final var startPane = loader.<BorderPane>load();
		this.controller = loader.getController();

		HomeScene = new Scene(startPane);
		HomeStage.setScene(HomeScene);
		HomeStage.show();
		HomeStage.setX((width - HomeStage.getWidth()) / 2);
		HomeStage.setY((height - HomeStage.getHeight()) / 2);
		HomeStage.addEventHandler(SetOutputSceneEvent.SET_OUTPUT_SCENE_EVENT_TYPE, this::onSetOutputScene);
		HomeStage.addEventHandler(BackHomeEvent.BACK_HOME_EVENT_TYPE, this::onHomeInquiry);
		HomeStage.addEventHandler(SetOutputSourceEvent.SET_OUTPUT_SOURCE_EVENT_TYPE, this::onSetOutput);
		HomeStage.addEventHandler(SetTeacherOutputSceneEvent.SET_TEACHER_OUTPUT_SCENE_EVENT_TYPE, this::onSetTeacherOutputScene);
		HomeStage.addEventHandler(DeleteLastTeamEvent.DELETE_LAST_TEAM_EVENT_TYPE, this::onDeleteLastTeam);
	}

	/**
	 * Event handler for setting output scene.
	 *
	 * @param event The SetOutputSceneEvent.
	 */
	public void onSetOutputScene(SetOutputSceneEvent event){
		try {
			this.outputScene = new OutputScene(event.getOutput());
		}catch (IOException e){
			System.out.println("Output scene FXML can't be opened.");
		}
		controller.setOutput(event.getOutput());
		HomeStage.setScene(outputScene);
		HomeStage.setTitle("ATU Program - Inquiry");
		HomeStage.setX((width - HomeStage.getWidth()) / 2);
		HomeStage.setY((height - HomeStage.getHeight()) / 2);
	}

	/**
	 * Event handler for setting teacher output scene.
	 *
	 * @param event The SetOutputSceneEvent.
	 */
	public void onSetTeacherOutputScene(SetTeacherOutputSceneEvent event){
		try {
			this.teacherOutputScene = new TeacherOutputScene(event.getOutput());
		}catch (IOException e){
			System.out.println("Teacher output scene FXML can't be opened.");
		}
		controller.setOutput(event.getOutput());
		HomeStage.setScene(teacherOutputScene);
		HomeStage.setTitle("ATU Program - Overview");
		HomeStage.setX((width - HomeStage.getWidth()) / 2);
		HomeStage.setY((height - HomeStage.getHeight()) / 2);
	}

	/**
	 * Event handler for going back to home page.
	 *
	 * @param event The BackHomeEvent.
	 */
	public void onHomeInquiry(BackHomeEvent event) {
		HomeStage.setScene(HomeScene);
		HomeStage.setTitle("ATU Program - Home Screen");
		HomeStage.setX((width - HomeStage.getWidth()) / 2);
		HomeStage.setY((height - HomeStage.getHeight()) / 2);
	}

	/**
	 * Event handler for setting the sorted team list for Output UI.
	 *
	 * @param event The SetOutputSourceEvent.
	 */
	public void onSetOutput(SetOutputSourceEvent event) {
		controller.setOutput(event.getOutput());
	}

	/**
	 * Event handler for deleting the last formed team data.
	 * Show Alert Box to ask for confirmation.
	 *
	 * @param event The DeleteLastTeamEvent.
	 */
	public void onDeleteLastTeam(DeleteLastTeamEvent event){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to delete the last formed team list data?");
		alert.setContentText("This will delete the last formed team list data stored in " + new File(App.PATH_TO_EXPORT).getAbsolutePath() + ".\n"
				+ "This action is irreversible.\n");

		//alert.showAndWait();
		Optional<ButtonType> result = alert.showAndWait();
		// alert is exited, no button has been pressed.
		if(!result.isPresent()){
			return;
		}
		// cancel button is pressed
		else if(result.get() == ButtonType.CANCEL){
			return;
		}
		//oke button is pressed
		else if(result.get() == ButtonType.OK){
			File outputSer = new File(PATH_TO_OUTPUT_SER);
			if (outputSer.delete()) {
				System.out.println("Deleted the file: " + outputSer.getName() +" at "+ PATH_TO_OUTPUT_SER);
			} else {
				System.out.println("Failed to delete the file.");
			}
			File teamCsv = new File(PATH_TO_TEAM_CSV);
			if (teamCsv.delete()) {
				System.out.println("Deleted the file: " + teamCsv.getName() +" at "+ PATH_TO_TEAM_CSV);
			} else {
				System.out.println("Failed to delete the file.");
			}
			controller.clear();
		}

	}
}
