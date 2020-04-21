import java.util.*;
/**
 * class of one cashier for the shop
 *
 * @author Harry Zhu
 * @version 4/20/2020
 */
public class Cashier implements Comparable<Cashier>
{

  public int time;
  public int counter;
  //private int timeLower;
  //private int timeUpper;

  public Cashier(int counter){
    time = 21600;
    this.counter = counter;
    //timeLower = t1;
    //timeUpper = t2;
  }
  /**
   * method for a cashier to serve a customer
   *
   * @param  customer  the customer to be served
   * @return    revenue generated from serving the customer
   */
  public float serve(Customer customer){
    if(customer.arrival > time) time = customer.arrival + customer.orderingTime;
    else time += customer.orderingTime;
    return customer.orderAmount;
  }

  /**
   * compare by time
   *
   * @param  e  the cashier to be compared
   * @return    the difference in time
   */
  public int compareTo(Cashier e){
    return time - e.time;
  }
}
