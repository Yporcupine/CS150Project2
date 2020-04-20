
/**
* Write a description of class Customer here.
*
* @author Yuehao Zhu
* @version 3/25/2020
*/
public class Customer implements Comparable<Customer>
{
  // instance variables - replace the example below with your own
  public int arrival;
  public int orderingTime;
  public boolean tooLate = false;
  float orderAmount;

  /**
  * Constructor for objects of class Customer
  */
  public Customer(String arrivalTime, int orderingTime,float orderAmount){
    String[] numbers = arrivalTime.split(":");
    if(Integer.parseInt(numbers[0]) >= 21) tooLate = true;
    else{
      tooLate = false;
      arrival = Integer.parseInt(numbers[0])*3600 + Integer.parseInt(numbers[1])*60 + Integer.parseInt(numbers[2]);
      this.orderingTime = orderingTime;
      this.orderAmount = orderAmount;
    }
  }




  /**
  * compares each instance of customer by its arrival time
  *
  * @param  customer  the customer to be added into the customer array
  * @return    the difference of the two compared
  */
  public int compareTo(Customer customer){
    return this.arrival - customer.arrival;
  }

  public static void print(){
    Customer customer1 = new Customer("06:00:00",12,12);
    System.out.println(customer1.arrival + "," + customer1.orderingTime);
  }

}
