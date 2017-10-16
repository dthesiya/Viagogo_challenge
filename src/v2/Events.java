package v2;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * <h1>event details!</h1>
 * The Events class holds event details
 * like location of the events and prices of tickets of all events.
 *
 * @author  Darshit Thesiya
 * @version 2.0
 */
class Events {
    private ArrayList<PriorityQueue<Float>> events = new ArrayList<>();
    final int x, y;

    Events(int x, int y){
        this.x = x;
        this.y = y;
    }

    int addEvent(){
        events.add(new PriorityQueue<>());
        return events.size() - 1;
    }

    boolean eventExists(){
        return events.size() > 0;
    }

    void addTicket(int eventNumber, float price) throws ArrayIndexOutOfBoundsException{
        events.get(eventNumber).offer(price);
    }

    public void removeTicket(int eventNumber, float price) throws ArrayIndexOutOfBoundsException{
        events.get(eventNumber).remove(price);
    }

    float getCheapestTicket(int eventNumber) throws ArrayIndexOutOfBoundsException{
        return events.get(eventNumber).peek();
    }

    int getNumberOfEvents(){
        return events.size();
    }

    @Override
    public String toString() {
        return x+":" + y + " with tickets: " + events.toString();
    }
}
