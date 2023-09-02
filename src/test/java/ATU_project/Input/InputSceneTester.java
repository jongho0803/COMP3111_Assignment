package ATU_project.Input;

import ATU_project.App;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.service.query.NodeQuery;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class InputSceneTester extends ApplicationTest {

    @Before
    public void setUpClass() throws Exception{
        File myObj = new File(App.PATH_TO_OUTPUT_SER);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        ApplicationTest.launch(App.class);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
        stage.setAlwaysOnTop(true);
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    // Both submit button and clear button should be disabled when both text fields are empty.
    @Test
    public void ensureInquiryDisabledBeforeProcess(){
        FxAssert.verifyThat(("#inquiryButton"), NodeMatchers.isDisabled());
    }

    @Test
    public void ensureOverviewDisabledBeforeProcess(){
        FxAssert.verifyThat(("#overviewButton"), NodeMatchers.isDisabled());
    }

    @Test
    public void ensureAtuButtonAlwaysExistAndAvailable(){
        FxAssert.verifyThat(("#atuButton"), NodeMatchers.isEnabled());
    }

    @Test
    public void clickOnAtuElementWithSuccedfulJump(){
        clickOn("#atuButton");
        assertNotNull(window("ATU Program - Student List"));
    }

    @Test
    public void backHomeInTableViewWork(){
        clickOn("#atuButton");
        clickOn("#home");
        assertNotNull(window("ATU Program - Home Screen"));
    }

    @Test
    public void proceedButtonInTableViewWork(){
        clickOn("#atuButton");
        clickOn("#proceedButton");
        assertNotNull(window("Dialogue: Form Team"));
    }


    @Test
    public void clearSearchFunction(){
        clickOn("#atuButton");
        clickOn("#searchBox");
        write("ddc");
        clickOn("#clearBtn");
        assertEquals(null, lookup("#searchBox").query().getAccessibleText());
    }
}