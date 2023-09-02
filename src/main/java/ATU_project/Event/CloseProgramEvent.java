package ATU_project.Event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to close the program.
 * @author Winnie Lam
 */
public class CloseProgramEvent extends Event {
    /**
     * Event type of CloseProgramEvent.
     */
    public static final EventType<CloseProgramEvent> CLOSE_PROGRAM_EVENT_TYPE = new EventType<>("CLOSE_PROGRAM");;

    /**
     * Constructor of CloseProgramEvent.
     */
    public CloseProgramEvent(){
        super(CLOSE_PROGRAM_EVENT_TYPE);
    }

}
