package v1;

import java.util.PriorityQueue;

/**
 * <h1>event details!</h1>
 * The Event class holds event details
 * like all prices of tickets and location of the event.
 *
 * @author  Darshit Thesiya
 * @version 1.0
 */
class Event {
    private PriorityQueue<Float> tickets = new PriorityQueue<>();
    final int x, y;

    Event(int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean eventExists(){
        return tickets.size() > 0;
    }

    void addTicket(float price){
        tickets.offer(price);
    }

    public void removeTicket(float price){
        tickets.remove(price);
    }

    float getCheapestTicket(){
        return tickets.peek();
    }

    @Override
    public String toString() {
        return x+":" + y + " with tickets: " + tickets.toString();
    }
}
