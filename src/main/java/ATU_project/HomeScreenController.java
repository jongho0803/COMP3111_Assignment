package ATU_project;

import ATU_project.Event.DeleteLastTeamEvent;
import ATU_project.Event.SetOutputSceneEvent;
import ATU_project.Event.SetTeacherOutputSceneEvent;
import ATU_project.Input.InputUI;
import ATU_project.Output.Output;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to control the Home screen UI.
 * @author Winnie Lam, Yoanna Lo
 */
public class HomeScreenController implements Initializable {

    @FXML
    private Button inquiryButton;

    @FXML
    private Button atuButton;

    @FXML
    private Button overviewButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label exportLabel;

    @FXML
    private Button uploadButton;

    @FXML
    private Label uploadLabel;
    private Output output;

    /**
     * Indicating whether the output has been set.
     */
    private BooleanProperty hasNoOutput = new SimpleBooleanProperty(true);

    /**
     * Indicating whether the team has been formed.
     */
    private BooleanProperty hasTeam = new SimpleBooleanProperty(false);

    /**
     * Initialize the controller.
     * Set up the scene. Disable Overview and Inquiry service if team list has yet to be formed.
     * Disable ATU service if there's last formed team data.
     *
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inquiryButton.disableProperty().bind(hasNoOutput);
        overviewButton.disableProperty().bind(hasNoOutput);
        atuButton.disableProperty().bind(hasTeam);
        deleteButton.disableProperty().bind(hasNoOutput);
        File csv = new File(App.PATH_TO_STUDENT_CSV);
        String csvPath = csv.getAbsolutePath();
        uploadLabel.setText("Loaded student csv data at " + csvPath);
        InputUI.setInputFile(csv);

        if (new File(App.PATH_TO_OUTPUT_SER).isFile()){
            try {
                Output deserializedOutput = this.deserialize();
                this.output = deserializedOutput;
                System.out.println("Successful to serialize output.");
                System.out.println(output.getSortedTeamList()[0]);
                hasNoOutput.set(false);
                hasTeam.set(true);
                File export = new File(App.PATH_TO_EXPORT);
                String exportPath = export.getAbsolutePath();
                exportLabel.setText("Last formed team list data found at " + exportPath);
            }catch (IOException e){
                System.out.println("Failed to deserialized output.");
                e.printStackTrace();
            }
        }
        else {
            // do nothing
            System.out.println("Nothing.");
        }
    }

    /**
     * Event handler for clicking Inquiry Service button, go to the inquiry scene.
     *
     * @param event ActionEvent of clicking the Inquiry Service button.
     * @throws IOException of setting inquiry scene FXML.
     */
    @FXML
    void onInquiryBtnClicked(ActionEvent event) throws IOException {
        inquiryButton.fireEvent(new SetOutputSceneEvent(SetOutputSceneEvent.SET_OUTPUT_SCENE_EVENT_TYPE, output));
    }

    /**
     * Event handler for clicking ATU Service button, go to view student table.
     *
     * @param event ActionEvent of clicking the ATU Service button.
     */
    @FXML
    void onAtuBtnClicked(ActionEvent event) {
        try {
        	InputUI.run();
        } catch (Exception e1) { //catch the input exception of set scene
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Student File Source Error");
            alert.setContentText("The provided student list is not in correct format.");
            alert.setHeaderText("Please select another student list!");
            alert.showAndWait();
		}
    }

    /**
     * Event handler for clicking Overview Service button, go to the teacher output scene, which contains the line chart inquiry.
     *
     * @param event ActionEvent of clicking the Overview Service button.
     */
    @FXML
    void onOverviewBtnClicked(ActionEvent event) {
        overviewButton.fireEvent(new SetTeacherOutputSceneEvent(SetTeacherOutputSceneEvent.SET_TEACHER_OUTPUT_SCENE_EVENT_TYPE, output));
    }

    /**
     * Event handler for clicking Delete button. Fire {@link DeleteLastTeamEvent}.
     *
     * @param event ActionEvent of clicking the Delete button.
     */
    @FXML
    void onDeleteBtnClicked(ActionEvent event) {
        deleteButton.fireEvent(new DeleteLastTeamEvent());
    }

    /**
     * Event handler for clicking Upload button. Open FileChooser for user to pick a CSV file.
     * If a CSV file is chosen, pass the file to {@link InputUI}.
     *
     * @param event ActionEvent of clicking the Upload button.
     */
    @FXML
    void onUploadBtnClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Student List File");

        File filePath = new File("src/main/resources");
        fileChooser.setInitialDirectory(filePath);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File file = fileChooser.showOpenDialog(App.HomeStage);

        //File export = new File(App.PATH_TO_EXPORT);

        if (file != null) {
            String csvPath = file.getAbsolutePath();
            uploadLabel.setText("Loaded student csv data at " + csvPath);
            InputUI.setInputFile(file);
            System.out.println("Success");
        }else{
            System.out.println("Fail");
        }
    }

    /**
     * Setter for {@link HomeScreenController#output}. Also set {@link HomeScreenController#hasNoOutput} to false and set {@link HomeScreenController#hasTeam} to true.
     * Change export label to export folder absolute path.
     * @param output The sorted team list.
     */
    public void setOutput(Output output){
        this.output = output;
        hasNoOutput.set(false);
        hasTeam.set(true);
        File export = new File(App.PATH_TO_EXPORT);
        String exportPath = export.getAbsolutePath();
        exportLabel.setText("Last formed team list data found at " + exportPath);
    }

    /**
     * Deserialize the output.ser file in "export/output.ser".
     *
     * @return The output instance, which stores the sorted and unsorted team list.
     * @throws IOException when unable to deserialize the output.ser.
     */
    public Output deserialize() throws IOException{
        Output result = null;
        try{
            FileInputStream fileIn = new FileInputStream(App.PATH_TO_OUTPUT_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            result = (Output) in.readObject();
            in.close();
            fileIn.close();
        }catch(ClassNotFoundException e){
            System.out.println("Output class not found");
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    /**
     * Clear last formed team data.
     * Set {@link HomeScreenController#output} to null, set {@link HomeScreenController#hasNoOutput} to true,
     * and set {@link HomeScreenController#hasTeam} to false.
     */
    public void clear(){
        this.output = null;
        hasNoOutput.set(true);
        hasTeam.set(false);
        exportLabel.setText("No last formed team list data found.");
    }
}