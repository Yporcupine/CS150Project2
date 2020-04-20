
/**
 * Write a description of class arrival here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arrival extends Event
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class arrival
     */
    public Arrival(Customer customer)
    {
        // initialise instance variables
        this.customer = customer;
        startTime = customer.arrival;
    }

}
