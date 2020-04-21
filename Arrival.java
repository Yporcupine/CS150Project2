
/**
 * event of a customer arriving at shop
 *
 * @author Harry Zhu
 * @version 4/20/2020
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
