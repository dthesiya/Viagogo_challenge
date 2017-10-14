import pojo.EventDetails;

import java.util.List;

/**
 * Created by dthesiya on 10/13/17.
 */
public class Driver {
    static  int x = 5, y = 5;
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.fillRandomEventsAndTickets(20, 1, 50);
        List<EventDetails> results = grid.findNClosestEvents(x, y, 9);
        System.out.println("--------Results--------");
        for(EventDetails eDetails: results){
            System.out.println("At:- "+ eDetails.getX() +":"+ eDetails.getY()
                    +" with price: "+ eDetails.getTicket()
                    +" with distance: "+ eDetails.getDistance());
        }
    }
}
