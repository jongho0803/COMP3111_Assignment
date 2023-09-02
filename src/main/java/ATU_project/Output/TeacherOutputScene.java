package ATU_project.Output;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

/**
 * The root scene of teacher output UI.
 * @author Yoanna Lo
 */
public class TeacherOutputScene extends Scene {
    private final TeacherOutputSceneController controller;


    /**
     * The only constructor for TeacherOutputScene. Load the corresponding FXML files and pass the sorted team list to UI controllers. <p>
     *
     * @param output The sorted team list.
     * @throws IOException When it fails to load the FXML.
     */
    public TeacherOutputScene(Output output) throws IOException {
        super(new Label("Output Loading..."));
        final var fxml = getClass().getResource("/UI/teacher-output-scene.fxml");

        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<GridPane>load();
        this.setRoot(startPane);
        this.controller = loader.getController();
        controller.setTeam(output);
    }
}
