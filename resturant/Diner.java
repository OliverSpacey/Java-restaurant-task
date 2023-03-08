package resturant;
import sheffield.*;

public class Diner {

    //:::::::::::::Required for Task 1

    static Diner [] allTheDiners;

    static void createPartyFor(String [] peoplesNames) {
        //create new diner object for each person using the array of all diners.
        allTheDiners = new Diner[(peoplesNames.length)];
        for (int i=0; i<peoplesNames.length; i++){
            allTheDiners[i] = new Diner(peoplesNames[i]);
        }
     }

     static EasyWriter screen = new EasyWriter();

     static void listAllTheDiners() {
         //Lists every diner in the array of all diners
         screen.println("The diners are:");
         for (int i=0; i<allTheDiners.length; i++){
             screen.println(allTheDiners[i].theDinersName);
         }
     }

     private String theDinersName;

     private Diner (String called) {
         //This is the constructor
         theDinersName = called;
         thingsOrderedByDiner = new Order[MAX_NUMBER_OF_THINGS_A_DINER_CAN_ORDER];
     }

     //:::::::::::::Additionally required for Task 2

     static void takeEveryonesOrder()  {
         //Iterates through each diner and takes their order
        for (int i=0; i<allTheDiners.length; i++){
            allTheDiners[i].askForTheirOrder();
        }
     }

     static final int MAX_NUMBER_OF_THINGS_A_DINER_CAN_ORDER=10;

     Order[] thingsOrderedByDiner;

     private void askForTheirOrder() {
       //orderState ensures the loop asking for each diner's 
       //order only iterates while the diner has not ordered 'nothing'.
       //'a' counts each item that is added to the array of orders,
       //and will stop the loop once 10 items have been ordered.
       //'a' also serves to place each item in the array at the correct
       //index.
       screen.println("Time for "+theDinersName+" to order");
       boolean orderState = true;
       int a = 0;
       while (orderState && a < MAX_NUMBER_OF_THINGS_A_DINER_CAN_ORDER){
           Order dinersOrder = Order.askForANewThingOrdered();
           if (dinersOrder != null){
               thingsOrderedByDiner[a] = dinersOrder;
               a+=1;

           }
           else
               orderState = false;
       }
       screen.println();
    }

    static void listEveryonesOrder() {
        //Iterates through every diner and prints their full order
        screen.println("The diners' order is");
        for (int i=0; i<allTheDiners.length; i++){
            allTheDiners[i].printOrder();
        }
        screen.println();
    }

   private void printOrder() {
     //The for loop iterates over the entire array of orders for a given diner
     //and only prints out the elements that are not null, and thus the elements
     //that contain an order and price.
     screen.println(theDinersName);
     if (thingsOrderedByDiner[0] == null){
         screen.println("   Nothing");
     }
     else{
         for (int i=0; i<MAX_NUMBER_OF_THINGS_A_DINER_CAN_ORDER; i++){
             if (thingsOrderedByDiner[i] != null){
                 screen.println("   "+thingsOrderedByDiner[i].toString());
             }
         }
     }
  }

  //:::::::::::::Additionally required for Task 3

  static void dealWithTheBill() {
    //This method uses two for loops to print out two totals, and then uses 
    //them to calculate the difference. This is done simply by iterating through
    //the array of diner objects and calculating the price of all the things
    //they ordered, and then adding this to a variable.
    double total = 0;
    int individualTotal = 0;
    int roundedTotal = 0;
    double excess = 0;
    for (int i=0; i<allTheDiners.length; i++){
        total += allTheDiners[i].getShareOfBill();
    }
    total = total/100;
    screen.println("The diners owe "+total);
    screen.println("Everyone's debt rounded up is");
    for (int i=0; i<allTheDiners.length; i++){
        individualTotal = (int)Math.ceil((allTheDiners[i].getShareOfBill()/100));
        screen.println(allTheDiners[i].theDinersName+" "+individualTotal);
        roundedTotal += individualTotal;
    }
    excess = roundedTotal-total;
    screen.println("which is too much by "+excess);
  }

  private double getShareOfBill() {
    //The total for a given diner object's order is calculated and returned
    double subTotal = 0;
    for (int i=0; i<MAX_NUMBER_OF_THINGS_A_DINER_CAN_ORDER; i++){
        if (thingsOrderedByDiner[i] != null){
            subTotal += thingsOrderedByDiner[i].getPriceInPence();
        }
    }
    return subTotal;
  }

//:::::::::::::Additionally required for Task 4

   static void getAnotherRound() {
     //for loop iterates over all the diners
     for (int i=0; i<allTheDiners.length; i++){
         String dinerName = allTheDiners[i].theDinersName;
         //The number of orders in each diner array is counted, to ensure 
         //no one orders more than 10 items.
         int a=0;
         for (int j=0; j<allTheDiners[i].thingsOrderedByDiner.length; j++){
             if (allTheDiners[i].thingsOrderedByDiner[j] != null){
                 a+=1;
             }
         }
         
         //The if statement and for loop combination below ensure that any
         //additions to the array of orders for each diner are added to the 
         //first available null spot in the array, and nowhere else.
         if (a<MAX_NUMBER_OF_THINGS_A_DINER_CAN_ORDER){
             Order secondOrder = Order.askForANewThingOrdered(dinerName);
             boolean firstNull = true;
             for (int j=0; j<allTheDiners[i].thingsOrderedByDiner.length; j++){
                 if (allTheDiners[i].thingsOrderedByDiner[j] == null && firstNull){
                     allTheDiners[i].thingsOrderedByDiner[j] = secondOrder;
                     firstNull = false;
                 }
             }
         }
     }
   }

}