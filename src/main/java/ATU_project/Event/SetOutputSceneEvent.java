package ATU_project.Event;

import ATU_project.Output.Output;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to set scene to output scene.
 * @author Yoanna Lo
 */
public class SetOutputSceneEvent extends Event {

    /**
     * Event type of SetOutputSceneEvent.
     */
    public static final EventType<SetOutputSceneEvent> SET_OUTPUT_SCENE_EVENT_TYPE = new EventType<>("SET_OUTPUT_SCENE");;

    /**
     * The sorted team list used in output scene.
     */
    private Output output;

    /**
     * The constructor for SetOutputSceneEvent.
     * @param type SetOutputSceneEvent EventType.
     * @param output The sorted team list.
     */
    public SetOutputSceneEvent(EventType<? extends SetOutputSceneEvent> type, Output output){
        super(type);
        this.output = output;
    }

    /**
     * Getter for {@link SetOutputSceneEvent#output}.
     * @return The sorted team list.
     */
    public Output getOutput(){
        return output;
    }
}