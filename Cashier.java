import java.util.*;

public class Cashier implements Comparable<Cashier>
{

  public int time;
  Customer customerAtCounter;
  public int counter;
  //private int timeLower;
  //private int timeUpper;

  public Cashier(int counter){
    time = 21600;
    this.counter = counter;
    //timeLower = t1;
    //timeUpper = t2;
  }

  public float serve(Customer customer){
    customerAtCounter = customer;
    if(customer.arrival > time) time = customer.arrival + customer.orderingTime;
    else time += customerAtCounter.orderingTime;
    return customer.orderAmount;
  }

  public int compareTo(Cashier e){
    return time - e.time;
  }
}
