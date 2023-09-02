package ATU_project.Event;

import ATU_project.Output.Output;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to set scene to teacher output scene, which contains is the Overview page containing the line chart.
 * @author Yoanna Lo
 */
public class SetTeacherOutputSceneEvent extends Event {

    /**
     * Event type of SetTeacherOutputSceneEvent.
     */
    public static final EventType<SetTeacherOutputSceneEvent> SET_TEACHER_OUTPUT_SCENE_EVENT_TYPE = new EventType<>("SET_TEACHER_OUTPUT_SCENE");;

    /**
     * The sorted team list used in teacher output scene.
     */
    private Output output;

    /**
     * The constructor for SetTeacherOutputSceneEvent.
     * @param type SetTeacherOutputSceneEvent EventType.
     * @param output The sorted team list.
     */
    public SetTeacherOutputSceneEvent(EventType<? extends SetTeacherOutputSceneEvent> type, Output output){
        super(type);
        this.output = output;
    }

    /**
     * Getter for {@link SetTeacherOutputSceneEvent#output}.
     * @return The sorted team list.
     */
    public Output getOutput(){
        return output;
    }
}