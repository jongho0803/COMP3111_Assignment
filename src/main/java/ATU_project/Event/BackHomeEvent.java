package ATU_project.Event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to go back to home page.
 * @author Winnie Lam
 */
public class BackHomeEvent extends Event {
    /**
     * Event type of BackHomeEvent.
     */
    public static final EventType<BackHomeEvent> BACK_HOME_EVENT_TYPE = new EventType<>("BACK_HOME");;

    /**
     * Constructor of BackHomeEvent.
     */
    public BackHomeEvent(){
        super(BACK_HOME_EVENT_TYPE);
    }
}