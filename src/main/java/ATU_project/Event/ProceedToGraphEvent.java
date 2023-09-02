package ATU_project.Event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to go to output graph page.
 * @author Yoanna Lo
 */
public class ProceedToGraphEvent extends Event {
    /**
     * Event type of ProceedToGraphEvent.
     */
    public static final EventType<ProceedToGraphEvent> PROCEED_TO_GRAPH_EVENT_TYPE = new EventType<>("PROCEED_TO_INQUIRY");;

    /**
     * Constructor of ProceedToGraphEvent.
     */
    public ProceedToGraphEvent(){
        super(PROCEED_TO_GRAPH_EVENT_TYPE);
    }

}
