package v2;

import pojo.EventDetails;
import java.util.List;

/**
 * <h1>entry point for event manager application!</h1>
 * The Driver class has main method to initiate and execute the grid application.
 *
 * @author  Darshit Thesiya
 * @version 2.0
 */
public class Driver {
    public static void main(String[] args) {
        Grid grid = new Grid(-20, 20, -20, 20);
        grid.fillRandomEventsAndTickets(25, 2, 1, 50);
        List<EventDetails> results = grid.findNClosestEvents(5, 5, 2);
        System.out.println("--------Results Start--------");
        for(EventDetails eDetails: results){
            System.out.println("Event "+ eDetails.getId() + " - $" + eDetails.getTicket()
                    +", Distance "+ eDetails.getDistance());
        }
        System.out.println("--------Results End--------");
    }
}
