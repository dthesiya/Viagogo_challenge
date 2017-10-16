package v2;

import pojo.EventDetails;
import java.util.*;


/**
 * <h1>Grid application</h1>
 * The Grid class contains functionalities
 * like grid initialization, filling data and fetching N nearest events with cheapest tickets
 *
 * @author  Darshit Thesiya
 * @version 2.0
 */
class Grid{
    private ArrayList<ArrayList<Events>> grid = new ArrayList<>();
    private int minX = -10, maxX = 10;
    private int minY = -10, maxY = 10;

    /**
     * Initializes grid with
     * X-axis ranging from -10 to 10 and
     * Y-axis ranging from -10 to 10
     */
    Grid(){
        this(-10, 10, -10, 10);
    }

    /**
     * Initializes grid with
     * X-axis ranging from minX to maxX and
     * Y-axis ranging from minY to maxY
     *
     * @param minX indicates lowest value of X-axis
     * @param maxX indicates highest value of X-axis
     * @param minY indicates lowest value of Y-axis
     * @param maxY indicates highest value of Y-axis
     */
    Grid(int minX, int maxX, int minY, int maxY){
        this.minX = minX; this.maxX = maxX;
        this.minY = minY; this.maxY = maxY;
        // creating an empty grid
        for(int i = 0; i < maxX - minX + 1; i++){
            ArrayList<Events> list = new ArrayList<>();
            for(int j = 0; j < maxY - minY + 1; j++){
                list.add(new Events(i, j));
            }
            grid.add(list);
        }
    }

    /**
     * This method is a utility method to generate random coordinates in the grid and
     * fill it with random ticket prices within given boundaries
     *
     * @param noOfEvents indicates total number of events allowed in the grid
     * @param maxEventsAtAPlace indicates maximum number of events allowed in one place
     * @param ticketClasses indicates maximum classes of tickets allowed for an event
     * @param maxPrice indicates maximum price allowed for a ticket
     *
     * @return nothing
     */
    void fillRandomEventsAndTickets(int noOfEvents, int maxEventsAtAPlace, int ticketClasses, float maxPrice){
        // generating random seeds and filling grid with it
        Random rand = new Random();
        int X = maxX - minX + 1; int Y = maxY - minY + 1;
        while(noOfEvents > 0){
            int x = rand.nextInt(X); int y = rand.nextInt(Y);

            Events events = grid.get(x).get(y);
            int eCount = 1 + rand.nextInt(maxEventsAtAPlace);
            while(eCount-- > 0 && noOfEvents-- > 0) { // keep totalNumberOfEvents in check
                int eIndex = events.addEvent();
                int tickets = ticketClasses;
                while (tickets-- > 0) {
                    float ticket = rand.nextFloat() * maxPrice;
                    events.addTicket(eIndex, ticket);
                }
            }
        }
    }

    /**
     * This method is used to fetch N closest events from point(x, y),
     * with cheapest tickets for each event.
     *
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @param N number of events this method is expected to return
     *
     * @return list of events along with their coordinates and cheapest ticket prices.
     */
    List<EventDetails> findNClosestEvents(int x, int y, int N){
        List<EventDetails> results = new ArrayList<>();
        int p = x - minX; int q = y - minY;
        PriorityQueue<Events> queue = new PriorityQueue<>(Comparator.comparingInt(o -> manhattanDistance(o.x, o.y, x - minX, y - minY)));
        int iter = 1;
        while (queue.size() <= N){
            if(!findInLoop(p, q, iter, queue)) break;
            iter++;
        }

        for(int i = 0; i < N && !queue.isEmpty();){
            Events events = queue.poll();
            int noOfEvents = events.getNumberOfEvents();
            for(int j = 0; j < noOfEvents && i < N; j++){
                results.add(new EventDetails(Integer.valueOf(events.x + "" + events.y + "" + j),
                        events.x + minX, events.y + minY,
                        manhattanDistance(x, y, events.x + minX, events.y + minY),
                        Float.valueOf(String.format("%.2f", events.getCheapestTicket(j)))));
                i++; // to break out of both loops when total N events are found
            }
        }
        return results;
    }

    /**
     * this method finds all existing events at i distance from point(x, y)
     */
    private boolean findInLoop(int x, int y, int i, PriorityQueue<Events> queue){
        int q = i/2; int p = i - q;
        p = Math.min(p, q);

        if(x - p < 0 && x + p > maxX - minX && y - p < 0 && y + p > maxY - minY) return false;

        int j = 0;
        while(i > 0){
            if(isEvent(x - i, y + j)) queue.offer(grid.get(x - i).get(y + j));
            if(isEvent(x + j, y + i)) queue.offer(grid.get(x + j).get(y + i));
            if(isEvent(x + i, y - j)) queue.offer(grid.get(x + i).get(y - j));
            if(isEvent(x - j, y - i)) queue.offer(grid.get(x - j).get(y - i));

            i--; j++;
        }
        return true;
    }

    /**
     * this method checks if given coordinates are in range and the event exists in place
     */
    private boolean isEvent(int x, int y){
        return !(x < 0 || x > maxX - minX || y < 0 || y > maxY - minY || !grid.get(x).get(y).eventExists());
    }

    /**
     * this is a utility function to calculate manhattan distance between given coordinates
     */
    private int manhattanDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
