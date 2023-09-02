package ATU_project.Input;

import ATU_project.Event.BackHomeEvent;
import ATU_project.Event.CloseProgramEvent;
import ATU_project.Event.ProceedToGraphEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to control the proceeding dialogue UI.
 * @author Winnie Lam
 */
public class ProceedingUIController implements Initializable {
    @FXML
    private Button backHome;

    @FXML
    private HBox buttonBar;

    @FXML
    private Button close;

    @FXML
    private Button overview;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label statusText;

    /**
     * Event handler for clicking the Back Home button. Fire {@link BackHomeEvent}.
     * Go back to Home Screen.
     * @param event ActionEvent of clicking the Back Home button.
     */
    @FXML
    void backHome(ActionEvent event) {
        try {
            backHome.fireEvent(new BackHomeEvent());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Event handler for clicking the Close Program button. Fire {@link CloseProgramEvent}.
     * Close the application.
     * @param event ActionEvent of clicking the Close Program button.
     */
    @FXML
    void closeProgram(ActionEvent event) {
        try {
            close.fireEvent(new CloseProgramEvent());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Event handler for clicking the Overview Service button. Fire {@link ProceedToGraphEvent}.
     * Open the overview page.
     * @param event ActionEvent of clicking the Overview Service button.
     */
    @FXML
    void openOverview(ActionEvent event) {
        try{
            overview.fireEvent(new ProceedToGraphEvent());
        } catch (Exception e1){
            e1.printStackTrace();
        }
    }

    /**
     * Initialize the controller. Set up the action of progress bar.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0), new KeyValue(buttonBar.opacityProperty(), 0),new KeyValue(buttonBar.disableProperty(), true)),
                new KeyFrame(Duration.seconds(1), e-> {
                    statusText.setText("Team list created.");
                }, new KeyValue(progressBar.progressProperty(), 1), new KeyValue(buttonBar.opacityProperty(), 1), new KeyValue(buttonBar.disableProperty(), false))
        );
        timeline.play();
    }

}
