import java.util.*;
import java.io.*;
public class Simulation{

  Scanner sc;

  int time;
  float revenue;
  float p1;
  float p2;
  int t1;
  int t2;
  float c;
  int cashierNum;
  int overflow;
  int maxWait = 0;
  int averageWaitTime = 0;
  int customerServed = 0;
  int totalCustomers;
  float overflowRate;

  PriorityQueue<Event> eventsIn;
  PriorityQueue<Event> line;
  ArrayList<Customer> customerIn;
  Queue<Cashier> cashiers;
  ArrayDeque<Cashier> cashiersBusy;


  Event rightnow;
  Cashier one;

  //int maxline = 0;

  public static void main(String[] args) {
    try {
      Simulation simulation;
      File cafeSim = new File("cafeSimProfit.csv");
      File overFlow = new File("overFlowRate.csv");
      File averageWaiting = new File("averagewaitimgTime.csv");
      PrintWriter simData = new PrintWriter("cafeSimProfit.csv");
      PrintWriter overflow = new PrintWriter("overFlowRate.csv");
      PrintWriter averagewaiting = new PrintWriter("averagewaitimgTime.csv");
      for (int i = 1; i < 21; i++) {
        simData.append(",");
        overflow.append(",");
        averagewaiting.append(",");
        simData.append(Integer.toString(i));
        overflow.append(Integer.toString(i));
        averagewaiting.append(Integer.toString(i));
      }
      simData.append("\n");
      overflow.append("\n");
      averagewaiting.append("\n");
      for (int i = 1; i<31; i++) {
        simData.append("day" + i + ": ");
        simData.append(",");
        overflow.append("day" + i + ": ");
        overflow.append(",");
        averagewaiting.append("day" + i + ": ");
        averagewaiting.append(",");
        for(int j = 1; j < 21; j++){
          simulation = new Simulation(j);
          simulation.readFile("input.txt", i, i);
          simData.append(Float.toString(simulation.simulate()));
          overflow.append(Float.toString(simulation.overflowRate*100) + "%");
          averagewaiting.append(Integer.toString(simulation.averageWaitTime));
          simData.append(",");
          overflow.append(",");
          averagewaiting.append(",");
        }
        simData.append("\n");
        overflow.append("\n");
        averagewaiting.append("\n");
      }
      simData.close();
      overflow.close();
      averagewaiting.close();
    }catch (Exception e) {
      System.out.println(e);

    }
  }

  public Simulation(int cashierNum)
  {
    cashiers = new PriorityQueue<Cashier>();
    cashiersBusy = new ArrayDeque<Cashier>();
    eventsIn = new PriorityQueue<Event>();
    customerIn = new ArrayList<Customer>();
    line = new PriorityQueue<Event>();
    this.cashierNum = cashierNum;
    overflow = 0;
  }

  public void readFile(String a, int seed1, int seed2){
    try{
      sc = new Scanner(new FileReader(a));
      String curLine = sc.nextLine();
      String[] numbers = curLine.split(" ");
      p1 = Float.parseFloat(numbers[0]);
      p2 = Float.parseFloat(numbers[1]);

      curLine = sc.nextLine();
      c = Float.parseFloat(curLine);

      curLine = sc.nextLine();
      numbers = curLine.split(" ");
      t1 = Integer.parseInt(numbers[0]);
      t2 = Integer.parseInt(numbers[1]);

      for (int i = 0; i < cashierNum; i++) {
        cashiers.add(new Cashier(i));
      }

      Random random = new Random(seed1);
      Random randomMoney = new Random(seed2);
      while(sc.hasNextLine()){
        float money = (p1*100+(float)randomMoney.nextInt((int)(p2*100-p1*100)))/100;
        Customer guy = new Customer(sc.nextLine(), t1+ random.nextInt(t2-t1), money);
        if(!guy.tooLate){
          customerIn.add(guy);
          eventsIn.add(new Arrival(guy));
        }
      }
      totalCustomers = customerIn.size();

      revenue = 0;
    }catch(Exception e){
      System.out.println(e);
    }
  }

  public float simulate(){

    int waitTimeTotal = 0;


    while((!eventsIn.isEmpty()) || (!line.isEmpty())){

      //if no more cashiers, put people in line until next departure
      if(cashiers.isEmpty()) {
        waitForNextCashier();
        continue;
      }


      //take out next event
      if(!line.isEmpty()) rightnow = line.remove();
      else rightnow = eventsIn.remove();

      //if event is arrival, take out one cashier, make him busy, turn the event to departure and put it into eventsIn
      if(rightnow instanceof Arrival){

        //take out one cashier
        one = cashiers.remove();
        if(one.time > rightnow.startTime) waitTimeTotal += one.time - rightnow.startTime;
        customerServed += 1;

        if ((one.time - rightnow.startTime) > maxWait) {
          maxWait = one.time - rightnow.startTime;
        }

        eventsIn.add(new Departure(one.time, rightnow.customer, one.counter));
        revenue += one.serve(rightnow.customer);
        cashiersBusy.add(one);
        continue;
      }

      //if event is departure, put the matching cashier back to cashiers priorityqueue
      else if(rightnow instanceof Departure){
        for(int i = 0; i < cashiersBusy.size(); i++){
          one = cashiersBusy.removeFirst();
          if(one.counter == ((Departure)rightnow).counter){
            cashiers.add(one);
          }else{
            cashiersBusy.addLast(one);
          }
        }
      }
    }
    averageWaitTime = waitTimeTotal / customerServed;
    overflowRate = ((float)overflow)/((float)totalCustomers);

    return revenue-((float)cashierNum)*c;

  }

  private void waitForNextCashier(){
    while(cashiers.isEmpty() ){
      rightnow = eventsIn.remove();
      if(rightnow instanceof Arrival){
        if(line.size() >= cashierNum*8) {
          overflow += 1;
          continue;
        }
        else{
          line.add(rightnow);
          continue;
        }
      }else if(rightnow instanceof Departure){
        while(cashiers.isEmpty()){
          one = cashiersBusy.removeFirst();
          if(one.counter == ((Departure)rightnow).counter){
            cashiers.add(one);
            return;
          }else{
            cashiersBusy.addLast(one);
          }
        }
      }
    }

  }

}
