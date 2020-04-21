/**
* Write a description of class Customer here.
*
* @author Harry Zhu
* @version 4/20/2020
*/
public class Departure extends Event
{
    // instance variables - replace the example below with your own
    public int counter;
    /**
     * Constructor for objects of class departure
     */
    public Departure(int time, Customer customer, int counter)
    {
      this.customer = customer;
      if (customer.arrival > time) startTime = customer.arrival + customer.orderingTime;
      else startTime = time + customer.orderingTime;
      this.counter = counter;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */

}
