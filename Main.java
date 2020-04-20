import java.io.*;
import java.util.*;
/**
* Write a description of class Main here.
*
* @author (your name)
* @version (a version number or a date)
*/
public class Main
{


  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println();
      System.out.print("number of cashiers: ");
      int x = reader.nextInt();
      reader.close();
      Simulation simulation = new Simulation(x);
      simulation.readFile("input.txt",1,1);

      System.out.println("Net Profit for one day: " + simulation.simulate());
      System.out.println("the number of customer served: " + simulation.customerServed);
      System.out.println("the number of overflow: " + simulation.overflow);
      System.out.println("overflow rate: " + simulation.overflowRate*100 + "%");
      System.out.println("average waiting time for each customer: " + simulation.averageWaitTime);
      System.out.println("maximum waiting time is: " + simulation.maxWait);
    } catch(Exception e) {

    }
  }
}
