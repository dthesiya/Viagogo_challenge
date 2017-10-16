package pojo;

/**
 * <h1>POJO for event details!</h1>
 * The EventDetails is a plain java class to hold event details
 * like its identifier, location, distance from users location and cheapest ticket price
 *
 * @author  Darshit Thesiya
 */
public class EventDetails {
    private final int x, y, id;
    private int distance;
    private float ticket;

    public EventDetails(int id, int x, int y, int distance, float ticket){
        this.id = id;
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.ticket = ticket;
    }

    /**
     * this method returns the unique identifier of the event
     *
     * @return unique identifier of the event
     */
    public int getId() {
        return id;
    }

    /**
     * this method returns x coordinate of the event location
     *
     * @return x coordinate of the event location
     */
    public int getX() {
        return x;
    }


    /**
     * this method returns y coordinate of the event location
     *
     * @return y coordinate of the event location
     */
    public int getY() {
        return y;
    }

    /**
     * this method returns distance of the event location from given point
     *
     * @return distance of the event location from given point
     */
    public int getDistance() {
        return distance;
    }

    /**
     * this method returns cheapest ticket price for the event
     *
     * @return cheapest ticket price for the event
     */
    public float getTicket() {
        return ticket;
    }
}
