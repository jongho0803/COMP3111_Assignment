package ATU_project.Event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to submit inquiry.
 * @author Yoanna Lo
 */
public class SubmitEvent extends Event {
    /**
     * Event type of SubmitEvent.
     */
    public static final EventType<SubmitEvent> SUBMIT_INQUIRY_EVENT_TYPE = new EventType<>("SUBMIT_INQUIRY");;

    /**
     * The student name to inquire.
     */
    private String name;
    /**
     * The student SID to inquire.
     */
    private long sid;

    /**
     * The constructor of SubmitEvent.
     * @param type  SubmitEvent EventType.
     * @param name The Student name to inquire.
     * @param sid The Student SID to inquire.
     */
    public SubmitEvent(EventType<? extends SubmitEvent> type, String name, long sid){
        super(type);
        this.name = name;
        this.sid = sid;
    }

    /**
     * Getter for {@link SubmitEvent#name}.
     * @return The inquirer name related to the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for {@link SubmitEvent#sid}.
     * @return The inquirer sid related to the event.
     */
    public long getSid() {
        return sid;
    }
}
