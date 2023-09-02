package ATU_project.Output;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.GridPane;

/**
 * Controller class to control the teacher output scene UI. Control logic for {@link TeacherOutputScene}.
 * @author Yoanna Lo
 */
public class TeacherOutputSceneController {
    @FXML
    private LineChart outputLineChart;

    @FXML
    private OutputLineChartController outputLineChartController;

    @FXML
    private GridPane teacherOutputForm;

    @FXML
    private TeacherOutputFormController teacherOutputFormController;

    /**
     * Pass the sorted team list to controllers of output line chart UI and output teacher form UI.
     * @param output The sorted team list.
     * @see OutputLineChartController
     * @see TeacherOutputFormController
     */
    public void setTeam(Output output) {
        outputLineChartController.setLineChart(output);
        teacherOutputFormController.setTeamList(output);
    }
}
