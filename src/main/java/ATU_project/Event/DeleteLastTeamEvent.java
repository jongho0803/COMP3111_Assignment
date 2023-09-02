package ATU_project.Event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to delete last formed team data.
 * @author Yoanna Lo
 */
public class DeleteLastTeamEvent extends Event {
    /**
     * Event type of DeleteLastTeamEvent.
     */
    public static final EventType<DeleteLastTeamEvent> DELETE_LAST_TEAM_EVENT_TYPE = new EventType<>("DELETE_LAST_TEAM");;

    /**
     * Constructor of DeleteLastTeamEvent.
     */
    public DeleteLastTeamEvent(){
        super(DELETE_LAST_TEAM_EVENT_TYPE);
    }

}
