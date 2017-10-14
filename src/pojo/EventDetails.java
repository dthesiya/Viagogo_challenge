package pojo;

/**
 * Created by dthesiya on 10/13/17.
 */
public class EventDetails {
    private final int x, y;
    private int distance;
    private float ticket;

    public EventDetails(int x, int y, int distance, float ticket){
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.ticket = ticket;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    public float getTicket() {
        return ticket;
    }
}
