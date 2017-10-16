package v2;

import pojo.EventDetails;
import java.util.List;
import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);
        System.out.println("Please input coordinates: \n>");
        String[] input = sc.nextLine().split(",");


        int x = Integer.parseInt(input[0]), y = Integer.parseInt(input[1]);
        List<EventDetails> results = grid.findNClosestEvents(x, y, 5);
        System.out.println("Closest event to (" + x + "," + y + "):");
        for(EventDetails eDetails: results){
            System.out.println("Event "+ eDetails.getId() + " - $" + eDetails.getTicket()
                    +", Distance "+ eDetails.getDistance());
        }
    }
}
