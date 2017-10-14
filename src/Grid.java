import pojo.EventDetails;

import java.util.*;

public class Grid{
    private ArrayList<ArrayList<Events>> grid = new ArrayList<>();
    private int minX = -10, maxX = 10;
    private int minY = -10, maxY = 10;

    public Grid(){
        this(-10, 10, -10, 10, 0);
    }

    public Grid(int minX, int maxX, int minY, int maxY, int numberOfEventsAtAPlace){
        this.minX = minX; this.maxX = maxX;
        this.minY = minY; this.maxY = maxY;
        // creating an empty grid
        for(int i = 0; i < maxX - minX + 1; i++){
            ArrayList<Events> list = new ArrayList<>();
            for(int j = 0; j < maxY - minY + 1; j++){
                list.add(new Events(i, j, numberOfEventsAtAPlace));
            }
            grid.add(list);
        }
    }

    public void fillRandomEventsAndTickets(int numberOfEvents, int numberOfTicketsAtAnEvent, float maxAllowedPrice){
        // generating random seeds and filling grid with it
        Random rand = new Random();
        int X = maxX - minX + 1;
        int Y = maxY - minY + 1;
        System.out.println("--------Distribution--------");
        while(numberOfEvents > 0){
            int x = rand.nextInt(X);
            int y = rand.nextInt(Y);
            System.out.println("Point:- "+x+":"+y+" with distance: "+ manhattanDistance(x+minX, y+minY, Driver.x, Driver.y));
            Events events = grid.get(x).get(y);
            events.addEvent();
            int tickets = numberOfTicketsAtAnEvent;
            while (tickets > 0){
                float ticket = rand.nextFloat() * maxAllowedPrice;
                events.addTicket(ticket);
                tickets--;
            }
            numberOfEvents--;
        }
        System.out.println("--------Distribution Done--------");
    }

    public List<EventDetails> findNClosestEvents(int x, int y, int N){
        List<EventDetails> results = new ArrayList<>();
        int p = x - minX;
        int q = y - minY;
        PriorityQueue<Events> queue = new PriorityQueue<>(Comparator.comparingInt(o -> manhattanDistance(o.x, o.y, x - minX, y - minY)));
        int iter = 1;
        while (queue.size() <= N){
            if(!findInLoop(p, q, iter, 0, queue)) break;
            iter++;
        }
        N = Math.min(N, queue.size());

        for(int i = 0; i < N; i++){ // return N elements only
            Events events = queue.poll();
            results.add(new EventDetails(events.x + minX, events.y + minY,
                    manhattanDistance(x, y, events.x + minX, events.y + minY),
                    events.getCheapestTicket()));
        }
        return results;
    }

    /* find events at i distance from point(x, y) */
    private boolean findInLoop(int x, int y, int i, int j, PriorityQueue<Events> queue){
        int q = i/2;
        int p = i - q;
        p = Math.min(p, q);
        if(x - p < 0 && x + p > maxX - minX && y - p < 0 && y + p > maxY - minY) return false;
        while(i > 0){
            if(isEvent(x - i, y + j)) queue.offer(grid.get(x - i).get(y + j));
            if(isEvent(x + j, y + i)) queue.offer(grid.get(x + j).get(y + i));
            if(isEvent(x + i, y - j)) queue.offer(grid.get(x + i).get(y - j));
            if(isEvent(x - j, y - i)) queue.offer(grid.get(x - j).get(y - i));

            i--; j++;
        }
        return true;
    }

    /* check if coordinates are in range and the event exists in place */
    private boolean isEvent(int x, int y){
        return !(x < 0 || x > maxX - minX || y < 0 || y > maxY - minY || !grid.get(x).get(y).eventExists());
    }

    /* utility function to calculate manhattan distance */
    private int manhattanDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
