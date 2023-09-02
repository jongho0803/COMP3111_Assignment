package ATU_project.Output;

import ATU_project.Event.BackHomeEvent;
import ATU_project.Team;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

/**
 * Controller class to control the teacher output form UI.
 * @author Yoanna Lo
 */
public class TeacherOutputFormController {

    @FXML
    private Button backHomeButton;

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
    private Label teamLeader;

    @FXML
    private Label teamNumber;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Label k1k2Avg;

    /**
     * The sorted team list.
     */
    private Output teamList;

    /**
     * Setter for {@link TeacherOutputFormController#teamList}.
     * @param output The sorted team list.
     */
    public void setTeamList(Output output){
        this.teamList = output;
        String[] teams = new String[output.getSortedTeamList().length+1];
        teams[0] = "No team chosen";
        for (int i = 0; i < output.getSortedTeamList().length; i++){
            teams[i+1] = "Team " + (i+1);
        }
        choiceBox.setItems(FXCollections.observableArrayList(teams));
        // add a listener
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == 0) {
                    teamNumber.setText("-");
                    teamLeader.setText("-");
                    member1.setText("-");
                    member1sid.setText("-");
                    member1Email.setText("-");

                    member2.setText("-");
                    member2sid.setText("-");
                    member2Email.setText("-");

                    member3.setText("-");
                    member3sid.setText("-");
                    member3Email.setText("-");

                    member4.setText("-");
                    member4sid.setText("-");
                    member4Email.setText("-");

                    k1Avg.setText("-");
                    k2Avg.setText("-");
                    k1k2Avg.setText("-");
                } else {
                    setForm(teamList.getUnsortedTeamList()[(newValue.intValue())-1]);
                    //System.out.println(newValue.intValue());
                }
            }
        });
    }

    /**
     * Setter for all labels inside the Teacher Output Form UI.
     * @param team The team that labels need to display.
     */
    void setForm(Team team){
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


    /**
     * Event handler for clicking the Back Home button. Fire {@link BackHomeEvent}.
     * Go back to Home Screen.
     * @param event ActionEvent of clicking the Back Home button.
     */
    @FXML
    void onBackHomeBtnClicked(ActionEvent event) {
        backHomeButton.fireEvent(new BackHomeEvent());
    }

}

