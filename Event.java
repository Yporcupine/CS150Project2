
/**
* Write a description of class Customer here.
*
* @author Harry Zhu
* @version 4/20/2020
*/
public abstract class Event implements Comparable<Event>
{
    // instance variables - replace the example below with your own
    public int startTime;
    Customer customer;

    /**
     * compare events by time
     *
     * @param  e  the event to be compared to
     * @return    the difference in time
     */
    public int compareTo(Event e){
        return startTime - e.startTime;
    }

}
