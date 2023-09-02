package ATU_project.Event;

import ATU_project.Output.Output;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to set scene to set output source.
 * @author Yoanna Lo
 */
public class SetOutputSourceEvent extends Event {
    /**
     * Event to set team list source for output.
     * @author Yoanna Lo
     */


    /**
     * Event type of SetOutputSourceEvent.
     */
    public static final EventType<SetOutputSourceEvent> SET_OUTPUT_SOURCE_EVENT_TYPE = new EventType<>("SET_OUTPUT_SOURCE");;

    /**
     * The sorted team list used in output scene.
     */
    private Output output;

    /**
     * The constructor for SetOutputSceneEvent.
     * @param type SetOutputSceneEvent EventType.
     * @param output The sorted team list.
     */
    public SetOutputSourceEvent(EventType<? extends SetOutputSourceEvent> type, Output output){
        super(type);
        this.output = output;
    }

    /**
     * Getter for {@link SetOutputSourceEvent#output}.
     * @return The sorted team list.
     */
    public Output getOutput(){
        return output;
    }
}

