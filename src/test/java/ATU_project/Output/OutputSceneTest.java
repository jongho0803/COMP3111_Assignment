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
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

/**
 * Test class for OutputScene. Test cases are written using JUnit4 and TestFX.
 * @author Yoanna Lo
 */
public class OutputSceneTest extends ApplicationTest {
    /**
     * The output scene used in test cases.
     */
    OutputScene outputScene;

    /**
     * Fixture method that's called before every test case. Build the output scene here and store into {@link OutputSceneTest#outputScene}.
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
        outputScene = new OutputScene(new Output(teamList));
        stage.setScene(outputScene);
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
     * Test the Submit button and Clear button with empty input. Both should be disabled when both text fields are empty.
     */
    @Test
    public void testSubmitBtnClearBtnWithEmptyInput(){
        FxAssert.verifyThat(("#submitButton"), NodeMatchers.isDisabled());
        FxAssert.verifyThat(("#clearButton"), NodeMatchers.isDisabled());
    }

    /**
     * Test the Submit button and Clear button with 1 input.
     * Submit button should be disabled when only 1 text field is non-empty.
     * Clear button should be enabled when only 1 text field is non-empty.
     */
    @Test
    public void testSubmitBtnClearBtnWithOneInput(){
        clickOn("#studentName");
        write("ABC");
        FxAssert.verifyThat(("#submitButton"), NodeMatchers.isDisabled());
        FxAssert.verifyThat(("#clearButton"), NodeMatchers.isEnabled());
    }

    /**
     * Test the Submit button and Clear button with 1 input. Both should be enabled when only both text field are non-empty.
     */
    @Test
    public void testSubmitBtnClearBtnWithTwoInput(){
        clickOn("#studentName");
        write("ABC");
        clickOn("#sid");
        write("1234");
        FxAssert.verifyThat(("#submitButton"), NodeMatchers.isEnabled());
        FxAssert.verifyThat(("#clearButton"), NodeMatchers.isEnabled());
    }

    /**
     * Test both warning labels with both invalid inputs. Both warning labels for student name and SID should be visible when both text fields submitted invalid inputs.
     */
    @Test
    public void testLabelWithInvalidInput(){
        clickOn("#studentName");
        write(" ");
        clickOn("#sid");
        write("1234");
        clickOn("#submitButton");
        FxAssert.verifyThat(("#invalidName"), NodeMatchers.isVisible());
        FxAssert.verifyThat(("#invalidSid"), NodeMatchers.isVisible());
    }

    /**
     * Test warning label for SID with invalid SID only. Only warning label for SID should be visible when only SID is invalid.
     */
    @Test
    public void testLabelWithInvalidSid(){
        clickOn("#studentName");
        write("InvalidSid");
        clickOn("#sid");
        write("1234");
        clickOn("#submitButton");
        FxAssert.verifyThat(("#invalidName"), NodeMatchers.isInvisible());
        FxAssert.verifyThat(("#invalidSid"), NodeMatchers.isVisible());
    }

    /**
     * Test warning label for student name with invalid name only. Only warning label for name should be visible when only name is invalid.
     */
    @Test
    public void testLabelWithInvalidName(){
        clickOn("#studentName");
        write("InvalidName ");
        clickOn("#sid");
        write("12345678");
        clickOn("#submitButton");
        FxAssert.verifyThat(("#invalidName"), NodeMatchers.isVisible());
        FxAssert.verifyThat(("#invalidSid"), NodeMatchers.isInvisible());
    }

    /**
     * Test submit request with valid inputs and that student exists in the record.
     * The UI should change to output form page after submit valid inputs.
     * Then, clicking the Back button should return to the output inquiry page.
     */
    @Test
    public void testSubmitWithValidInput(){
        clickOn("#studentName");
        write("Joe");
        clickOn("#sid");
        write("11001234");
        clickOn("#submitButton");
        FxAssert.verifyThat(("#outputInquiry"), NodeMatchers.isInvisible());
        //FxAssert.verifyThat(("#outputForm"), NodeMatchers.isVisible());
        //Node dialogPane = lookup(".dialog-pane").query();
        //FxAssert.verifyThat(("#alert"), NodeMatchers.isNotNull());
        clickOn("#backButton");
        FxAssert.verifyThat(("#outputInquiry"), NodeMatchers.isVisible());
        //FxAssert.verifyThat(("#outputForm"), NodeMatchers.isInvisible());
    }

    /**
     * Test the Clear button when there's non-empty input field. The non-empty input fields should be empty after clear button is clicked.
     */
    @Test
    public void testClearBtnWithInput(){
        clickOn("#studentName");
        write("Trash");
        clickOn("#sid");
        write("12345");
        clickOn("#clearButton");
        FxAssert.verifyThat(("#studentName"), TextInputControlMatchers.hasText(""));
        FxAssert.verifyThat(("#sid"), TextInputControlMatchers.hasText(""));
    }

}