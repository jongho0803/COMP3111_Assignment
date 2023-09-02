package ATU_project.Output;

import ATU_project.Student;
import ATU_project.Team;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

/**
 * Test class for TeacherOutputScene. Test cases are written using JUnit4 and TestFX.
 * @author Yoanna Lo
 */
public class TeacherOutputSceneTest extends ApplicationTest {
    /**
     * The teacher output scene used in test cases.
     */
    TeacherOutputScene teacherOutputScene;

    /**
     * Fixture method that's called before every test case. Build the teacher output scene here and store into {@link TeacherOutputSceneTest#teacherOutputScene}.
     * @param stage The primary stage.
     * @throws Exception when failing to construct the scene.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // team1: avg_k1 = 50, avg_k2 = 30
        Team team1 = new Team(new Student("Apple", 12345678, "A@email.hk", 20, 30, true, false, true, ""),
                new Student("Ben", 23456789, "B@email.hk", 80, 30, true, false, false, ""),
                new Student("Cat", 34567891, "C@email.hk", 50, 30, false, true, false, ""), 1);
        Team team2  = new Team(new Student("Dog", 45678912, "D@email.hk", 90, 50, true, false, true, ""),
                new Student("Egg", 56789123, "E@email.hk", 90, 50, true, false, false, ""),
                new Student("Fish", 67891234, "F@email.hk", 90, 50, false, true, false, ""), 2);
        // team3 = avg_k1 = 20, avg_k2 = 30
        Team team3 = new Team(new Student("Google", 78912345, "G@email.hk", 20, 30, true, false, true, ""),
                new Student("Hon", 89123456, "H@email.hk", 20, 30, true, false, false, ""),
                new Student("Ian", 91234567, "I@email.hk", 20, 30, false, true, false, ""), 3);
        // team4 = avg_k1 = 50, avg_k2 = 40
        Team team4 = new Team(new Student("Joe", 11001234, "J@email.hk", 50, 50, true, false, true, ""),
                new Student("Ken", 12001234, "K@email.hk", 50, 40, true, false, false, ""),
                new Student("Mal", 15001243, "M@email.hk", 50, 30, false, true, false, ""),
                new Student("Nat", 16203002, "N@email.hk", 50, 40, false, true, false, ""), 4);
        Team[] teamList = new Team[] {team1, team2, team3, team4};
        teacherOutputScene = new TeacherOutputScene(new Output(teamList));
        stage.setScene(teacherOutputScene);
        stage.show();
        stage.setX(0.0);
        stage.setY(0.0);
        stage.toFront();
        stage.setAlwaysOnTop(true);
    }

    /**
     * Fixture method that's called after every test case. It clears unfinished key instruction and mouse instruction for the bot used in TestFX.
     * @throws Exception when failing to hide the stage.
     */
    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    /**
     * Test choice box to select Team 1. The label for team number should be "1".
     */
    @Test
    public void testChoiceBoxToSelectTeam1(){
        clickOn("#choiceBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        FxAssert.verifyThat(("#teamNumber"), LabeledMatchers.hasText("1"));
    }

    /**
     * Test choice box to select Team 1 and then select the default top item. The label for team number should be "-".
     */
    @Test
    public void testChoiceBoxBackToDefault(){
        clickOn("#choiceBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#choiceBox");
        type(KeyCode.UP);
        type(KeyCode.ENTER);
        FxAssert.verifyThat(("#teamNumber"), LabeledMatchers.hasText("-"));
    }

}