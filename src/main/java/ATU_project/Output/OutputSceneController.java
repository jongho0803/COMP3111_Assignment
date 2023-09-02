package ATU_project.Output;

import ATU_project.Event.BackInquiryEvent;
import ATU_project.Event.SubmitEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to control the output scene UI. Control logic for {@link OutputScene}.
 * @author Yoanna Lo
 */
public class OutputSceneController implements Initializable {

    @FXML
    private GridPane outputInquiry;

    @FXML
    private OutputInquiryController outputInquiryController;

    @FXML
    private GridPane outputForm;

    @FXML
    private OutputFormController outputFormController;

    /**
     * Initialize the controller. Set {@link OutputSceneController#outputForm} to invisible.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        outputForm.setVisible(false);
    }

    /**
     * Pass the sorted team list to controllers of output form UI and output line chart UI.
     * @param output The sorted team list.
     * @see OutputFormController
     */
    public void setTeam(Output output) {
        outputFormController.setTeamList(output);
    }

    /**
     * Event handler for {@link SubmitEvent} to switch to output form page.
     * Set output form UI to be visible.
     * Set output inquiry UI to be invisible.
     * Call {@link OutputFormController#onSubmitInquiry}.
     *
     * @param event The SubmitEvent data related to the inquiry being submitted.
     */
    public void onSubmitInquiry(SubmitEvent event) {
        outputForm.setVisible(true);
        outputInquiry.setVisible(false);
        outputFormController.onSubmitInquiry(event);
    }

    /**
     * Event handler for {@link BackInquiryEvent} to switch back to output inquiry page.
     * Set output form UI to be invisible.
     * Set output inquiry UI to be visible.
     *
     * @param event The SubmitEvent data related to the inquiry being submitted.
     */
    public void onBackInquiry(BackInquiryEvent event) {
        outputForm.setVisible(false);
        outputInquiry.setVisible(true);
    }
}
