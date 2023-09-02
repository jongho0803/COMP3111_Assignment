package ATU_project.Output;

import ATU_project.Event.BackInquiryEvent;
import ATU_project.Event.SubmitEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

/**
 * The root scene of output UI.
 * @author Yoanna Lo
 */
public class OutputScene extends Scene {
    private final OutputSceneController controller;


    /**
     * The only constructor for OutputScene. Load the corresponding FXML files and pass the sorted team list to UI controllers. <p>
     * Add EventHandlers for {@link SubmitEvent} and {@link BackInquiryEvent}.
     *
     * @param output The sorted team list.
     * @throws IOException when it fails to load the FXML.
     */
    public OutputScene(Output output) throws IOException {
        super(new Label("Output Loading..."));
        final var fxml = getClass().getResource("/UI/output-scene.fxml");

        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<StackPane>load();
        this.setRoot(startPane);
        this.controller = loader.getController();
        controller.setTeam(output);
        startPane.addEventHandler(SubmitEvent.SUBMIT_INQUIRY_EVENT_TYPE, controller::onSubmitInquiry);
        startPane.addEventHandler(BackInquiryEvent.BACK_INQUIRY_EVENT_TYPE, controller::onBackInquiry);
    }
}
