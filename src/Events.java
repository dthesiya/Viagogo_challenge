import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by dthesiya on 10/13/17.
 */
public class Events {
    private ArrayList<PriorityQueue<Float>> events = new ArrayList<>();
    public final int x, y;

    public Events(int x, int y){
        this.x = x;
        this.y = y;
        events.add(new PriorityQueue<>());
    }

    Events(int x, int y, int numberOfEvents){
        this.x = x;
        this.y = y;
        for(int i = 0; i < numberOfEvents; i++){
            events.add(new PriorityQueue<>());
        }
    }

    int addEvent(){
        events.add(new PriorityQueue<>());
        return events.size() - 1;
    }

    boolean eventExists(){
        return events.size() > 0;
    }

    void addTicket(float price){
        events.get(0).offer(price);
    }

    public void addTicket(int eventNumber, float price) throws ArrayIndexOutOfBoundsException{
        events.get(eventNumber).offer(price);
    }

    public void removeTicket(float price){
        events.get(0).remove(price);
    }

    public void removeTicket(int eventNumber, float price) throws ArrayIndexOutOfBoundsException{
        events.get(eventNumber).remove(price);
    }

    float getCheapestTicket(){
        return events.get(0).peek();
    }

    public float getCheapestTicket(int eventNumber) throws ArrayIndexOutOfBoundsException{
        return events.get(eventNumber).peek();
    }

    @Override
    public String toString() {
        return events.toString();
    }
}
