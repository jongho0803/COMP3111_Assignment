package ATU_project.Output;

import ATU_project.Event.BackHomeEvent;
import ATU_project.Event.SubmitEvent;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to control the output inquiry UI.
 * @author Yoanna Lo
 */
public class OutputInquiryController implements Initializable {

    @FXML
    private Button clearButton;

    @FXML
    private TextField sid;

    @FXML
    private TextField studentName;

    @FXML
    private Button submitButton;

    @FXML
    private Label invalidName;

    @FXML
    private Label invalidSid;

    @FXML
    private Button backHomeButton;

    /**
     * Initialize the controller. Disable clear button and submit button when input text field is empty.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearButton.disableProperty().bind(Bindings.isEmpty(studentName.textProperty())
                .and(Bindings.isEmpty(sid.textProperty())));
        submitButton.disableProperty().bind(Bindings.isEmpty(studentName.textProperty())
                .or(Bindings.isEmpty(sid.textProperty())));
    }

    /**
     * Check if name is valid according to the following rules:
     * <li> Special Characters & digits are not allowed. </li>
     * <li> Spaces are only allowed between two words. </li>
     * <li> Only one space is allowed between two words. </li>
     * <li> Spaces at the start or at the end are consider to be invalid. </li>
     * <li> Single word name is also valid. </li>
     *
     * @param name The name to validate.
     * @return boolean value of the validation result.
     */
    private boolean checkValidName(String name)
    {
        return name.matches( "^[a-zA-z]+( [a-zA-Z]+)*$" );
    }

    /**
     * Check if SID is valid as 8-digit number.
     *
     * @param sid The SID to validate.
     * @return boolean value of the validation result.
     */

    private boolean checkValidSid(String sid){
        return sid.matches("^\\d{8}$");
    }

    /**
     * Event handler for submit button. Display warning texts if the inputted student name or SID is invalid.
     * If both student name and SID is valid, fire {@link SubmitEvent}.
     *
     * @param event ActionEvent of clicking the Submit button.
     */
    @FXML
    private void onSubmitBtnClicked(ActionEvent event) {
        String inquirerName = studentName.getText();
        String inquirerSid = sid.getText();
        invalidName.setVisible(false);
        invalidSid.setVisible(false);
        if (checkValidName(inquirerName) && checkValidSid(inquirerSid)){
            sid.clear();
            studentName.clear();
            submitButton.fireEvent(new SubmitEvent(SubmitEvent.SUBMIT_INQUIRY_EVENT_TYPE, inquirerName, Long.valueOf(inquirerSid)));
        }
        else {
            if (!checkValidName(inquirerName)) {
                invalidName.setVisible(true);
            }

            if (!checkValidSid(inquirerSid)) {
                invalidSid.setVisible(true);
            }
        }
    }

    /**
     * Event handler for clear button. Clear both input text field and their warning texts.
     * @param event ActionEvent of clicking the Clear button.
     */
    @FXML
    private void onClearBtnClicked(ActionEvent event) {
        sid.clear();
        studentName.clear();
        invalidName.setVisible(false);
        invalidSid.setVisible(false);
    }

    /**
     * Event handler for clicking the Back Home button. Fire {@link BackHomeEvent}.
     * @param event ActionEvent of clicking the Back Home button.
     */
    @FXML
    private void onBackHomeBtnClicked(ActionEvent event) {
        backHomeButton.fireEvent(new BackHomeEvent());
    }
}