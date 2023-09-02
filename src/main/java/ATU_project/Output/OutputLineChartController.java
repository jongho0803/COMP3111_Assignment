package ATU_project.Output;

import ATU_project.Team;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Controller class to control the output line chart UI.
 * @author Yoanna Lo
 */
public class OutputLineChartController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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
     * Set the line chart data according to the given sorted team list. Add tooltips as well.
     * @see XYChart
     * @see Tooltip
     * @param output The sorted team list.
     */
    public void setLineChart(Output output){
        XYChart.Series<String, Number> avgK1 = new XYChart.Series();
        avgK1.setName("K1");
        for (Team team: output.getSortedTeamList()){
            avgK1.getData().add(new XYChart.Data<>(Integer.toString(team.getId()), team.getK1_avg()));
        }

        XYChart.Series<String, Number> avgK2 = new XYChart.Series();
        avgK2.setName("K2");
        for (Team team: output.getSortedTeamList()){
            avgK2.getData().add(new XYChart.Data<>(Integer.toString(team.getId()), team.getK2_avg()));
        }

        XYChart.Series<String, Number> avgK1K2 = new XYChart.Series();
        avgK1K2.setName("K1 + K2");
        for (Team team: output.getSortedTeamList()){
            avgK1K2.getData().add(new XYChart.Data<>(Integer.toString(team.getId()), (team.getK1_avg() + team.getK2_avg())/2));
        }

        lineChart.getData().addAll(avgK1, avgK2, avgK1K2);

        for (final XYChart.Data<String, Number> data: avgK1.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Tooltip.install(data.getNode(), new Tooltip("Team: " + data.getXValue()
                            + "\nAverage K1: " + new DecimalFormat("#.##").format((data.getYValue()))));
                }
            });
        }
        for (final XYChart.Data<String, Number> data: avgK2.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Tooltip.install(data.getNode(), new Tooltip("Team: " + data.getXValue()
                            + "\nAverage K2: " + new DecimalFormat("#.##").format((data.getYValue()))));
                }
            });
        }
        for (final XYChart.Data<String, Number> data: avgK1K2.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Tooltip.install(data.getNode(), new Tooltip("Team: " + data.getXValue()
                            + "\nAverage K1 + K2: " + new DecimalFormat("#.##").format((data.getYValue()))));
                }
            });
        }
    }
}
