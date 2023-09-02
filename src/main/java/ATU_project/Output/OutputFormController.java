package ATU_project.Output;

import ATU_project.Event.BackInquiryEvent;
import ATU_project.Event.SubmitEvent;
import ATU_project.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Controller class to control the output form UI.
 * @author Yoanna Lo
 */
public class OutputFormController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label k1Avg;

    @FXML
    private Label k2Avg;

    @FXML
    private Label member1;

    @FXML
    private Label member1Email;

    @FXML
    private Label member1sid;

    @FXML
    private Label member2;

    @FXML
    private Label member2Email;

    @FXML
    private Label member2sid;

    @FXML
    private Label member3;

    @FXML
    private Label member3Email;

    @FXML
    private Label member3sid;

    @FXML
    private Label member4;

    @FXML
    private Label member4Email;

    @FXML
    private Label member4sid;

    @FXML
    private Label sid;

    @FXML
    private Label studentName;

    @FXML
    private Label teamLeader;

    @FXML
    private Label teamNumber;

    @FXML
    private Button backButton;

    @FXML
    private Label k1k2Avg;


    /**
     * The Student name to inquire about.
     */
    private String inquirerName;

    /**
     * The Student SID to inquire about.
     */
    private long inquirerSid;

    /**
     * The sorted team list.
     */
    private Output teamList;

    /**
     * Initialize the controller.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Setter for {@link OutputFormController#teamList}.
     * @param output The sorted team list.
     */
    public void setTeamList(Output output){
        this.teamList = output;
    }
    /**
     * Event handler for submitted inquiry.
     * Start searching in the sorted team list.
     * Show Alert Box when it fails to search for the inquiry target.
     *
     * @param event The event data related to the inquiry being submitted.
     * @see SubmitEvent
     */
    public void onSubmitInquiry(SubmitEvent event) {
        this.gridPane.setVisible(true);
        this.inquirerName = event.getName();
        this.inquirerSid = event.getSid();
        boolean found = false;
        if (teamList != null){
            for (Team team: teamList.getSortedTeamList()){
                if(team.contain(inquirerName, inquirerSid)){
                    found = true;
                    studentName.setText(inquirerName);
                    sid.setText(Long.toString(inquirerSid));
                    teamNumber.setText(Integer.toString(team.getId()));
                    teamLeader.setText(team.getLeader().getName());
                    member1.setText(team.getLeader().getName());
                    member1sid.setText(Long.toString(team.getLeader().getSid()));
                    member1Email.setText(team.getLeader().getEmail());

                    member2.setText(team.getTeammateB().getName());
                    member2sid.setText(Long.toString(team.getTeammateB().getSid()));
                    member2Email.setText(team.getTeammateB().getEmail());

                    member3.setText(team.getTeammateC().getName());
                    member3sid.setText(Long.toString(team.getTeammateC().getSid()));
                    member3Email.setText(team.getTeammateC().getEmail());

                    if (team.getSize() == 4){
                        member4.setText(team.getTeammateD().getName());
                        member4sid.setText(Long.toString(team.getTeammateD().getSid()));
                        member4Email.setText(team.getTeammateD().getEmail());
                    }

                    k1Avg.setText(new DecimalFormat("#.##").format(team.getK1_avg()));
                    k2Avg.setText(new DecimalFormat("#.##").format(team.getK2_avg()));
                    k1k2Avg.setText(new DecimalFormat("#.##").format((team.getK1_avg() + team.getK2_avg())/2));
                }
            }
        }
        if (!found){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to search for the inquirer!");
            alert.setContentText(inquirerName + " (SID: " + inquirerSid + ") cannot be found on the team list.\n"
                                + "Please double check your inputs.\n"
                                + "If the problem persists, please contact TA for help.");

            alert.showAndWait();
            backButton.fireEvent(new BackInquiryEvent());
        }
    }

    /**
     * Event handler for back button. Fire {@link BackInquiryEvent}.
     * @param event ActionEvent of clicking the Back button.
     */
    @FXML
    void onBackBtnClicked(ActionEvent event) {
        backButton.fireEvent(new BackInquiryEvent());
    }
}
