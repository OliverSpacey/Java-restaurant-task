package resturant;
import sheffield.*;

public class Order {

  //:::::::::::::Required for Task 2

  static EasyReader keyboard = new EasyReader();

  static Order askForANewThingOrdered() {
      //This method asks all the diners for their order, and the price if the 
      //order was not null. If the diner ordered nothing, the method returns
      //null, and in any other case returns the object foodPrice which contains
      //the food item + the price of it.
      double price = 0;
      String course = keyboard.readString("What do you want? (starter, main, pudding, side, drink, or nothing) ");
      MenuItem courseName = (MenuItem.called(course));
      if (courseName == null){
          Order foodPrice = null;
          return foodPrice;
      }
      else{
          String stringCourseName = (String)courseName.toString().toLowerCase();
          price = keyboard.readDouble("What is the "+stringCourseName+"'s price? ");
          Order foodPrice = new Order(MenuItem.called(course), price);
          return foodPrice;
      }
      
  }

  private MenuItem menuItem;
  private int itemsPriceInPence;
  public int getPriceInPence() {  return itemsPriceInPence;  }

  private Order (MenuItem i, double p) {
     //This is the constructor
     menuItem = i;
     itemsPriceInPence = (int)(p*100);     
  }

  public String toString() {
     //I haven't taught you this and you don't need to understand it
     //to use the method.  Don't change it
     return String.format("%-10s%6.2f",menuItem,itemsPriceInPence/100.0);
  }

  //:::::::::::::Required for Task 4

  static Order askForANewThingOrdered(String diner) {
      //The diner is asked for their second order, and as long as the diner's 
      //input is not null, a new object is created and returned. If the diner
      //orders nothing, null is returned.
      String secondRound = keyboard.readString("What else do you want "+diner+"? ");
      MenuItem secondItem = (MenuItem.called(secondRound));
      if (secondItem != null){
          String stringItemTwo = (String)secondItem.toString().toLowerCase();
          double secondPrice = keyboard.readDouble("What is the "+stringItemTwo+"'s price? ");
          Order secondOrder = new Order(secondItem, secondPrice);
          return secondOrder;
      }
      else{
          return null;
      }
  }


}
