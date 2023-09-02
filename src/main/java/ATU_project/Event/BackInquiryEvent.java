package ATU_project.Event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event to go back to the output inquiry page in inquiry form page.
 * @author Yoanna Lo
 */
public class BackInquiryEvent extends Event {
    /**
     * Event type of BackInquiryEvent.
     */
    public static final EventType<BackInquiryEvent> BACK_INQUIRY_EVENT_TYPE = new EventType<>("BACK_INQUIRY");;


    /**
     * Constructor of BackInquiryEvent.
     */
    public BackInquiryEvent(){
        super(BACK_INQUIRY_EVENT_TYPE);
    }
}