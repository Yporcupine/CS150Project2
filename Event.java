
/**
 * Abstract class Event - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Event implements Comparable<Event>
{
    // instance variables - replace the example below with your own
    public int startTime;
    Customer customer;

    public int compareTo(Event e){
        return startTime - e.startTime;
    }

}
