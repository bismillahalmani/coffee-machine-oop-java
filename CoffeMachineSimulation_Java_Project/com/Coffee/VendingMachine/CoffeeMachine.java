package com.Coffee.VendingMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeMachine {
    private int water = 1000;   // ml
    private int milk = 500;     // ml
    private int beans = 200;    // grams
    private int cups = 20;
    private int money = 0;

    private ArrayList<Order> orderHistory = new ArrayList<>();
    private  Drink[] drinks = {
    new Drink("Espresso Shot", 50, 0, 15, 2),
    new Drink("Americano Classic", 80, 0, 15, 2),
    new Drink("Classic Brew", 100, 50, 15, 2),
    new Drink("Velvet Latte", 50, 100, 15, 3),
    new Drink("Cappuccino Supreme", 50, 50, 15, 4),
    new Drink("Mocha Delight", 50, 100, 20, 5)
};  
     

    public void showMenu(){
        System.out.println("||~~~~--------------MENU-----------~~~~||");
        System.out.println(" COFFEE             ||           PRICE");
        System.out.println(" 1. Espresso shot                 - $2");
        System.out.println(" 2. Americano Classic             - $2");
        System.out.println(" 3. Classic Brew                  - $2");                  
        System.out.println(" 4. Velvet Latte                  - $3");
        System.out.println(" 5. Cappuccino Supreme            - $4");                    
        System.out.println(" 6. Mocha Delight                 - $5");
    }

    


    public int choiceDrink(Scanner scanner) {
    int choice;

    do {
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // removes leftover ENTER
        if (choice == 0) return choice;

        if (choice < 1 || choice > drinks.length) {
            System.out.println("Invalid option. Try again.");
        }

    } while (choice < 1 || choice > drinks.length);

    return choice;
}



public int choiceTopping(Scanner scanner){
        int choice;

        System.out.println("\nDo you want a topping?");
        System.out.println("1. None");
        System.out.println("2. Chocolate (+$2)");
        System.out.println("3. Whipped Cream (+$2)");
        System.out.println("4. Caramel (+$2)");

         do {
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid option. Try again.");
            }

        } while (choice < 1 || choice > 4);

        return choice;
        
    }



    //////make coffee

    public Order makeCoffee(int drinkChoice,int toppingChoice,Scanner scanner){

        //returns false if resources are not available
        if (drinkChoice < 1 || drinkChoice > drinks.length) {
        System.out.println("Invalid drink selection");
        return null;
        }
         
        Drink drinkSelected = drinks[drinkChoice-1];
        String topping = "None"; 
       
       
        if (!checkResources(drinkSelected)) {
    System.out.println("Low on ingredients");
    System.out.println("Press 'r' to refill or any other key to cancel:");

    char choice = scanner.next().charAt(0);
    scanner.nextLine(); // clear buffer

    if (choice == 'r' || choice == 'R') {
        Refill();
         // RECHECK
        if (!checkResources(drinkSelected)) {
            System.out.println("Still not enough resources.");
            return null;
        }

    } else {
        System.out.println("Order cancelled");
        return null;
    }
}
        int totalPrice = drinkSelected.price;
        final int TOPPING_PRICE = 2;
       switch (toppingChoice) {
            case 2:
                topping = "Chocolate";
                totalPrice += TOPPING_PRICE;
                break;
            case 3:
                topping = "Whipped Cream";
                totalPrice += TOPPING_PRICE;
                break;
            case 4:
                topping = "Caramel";
                totalPrice += TOPPING_PRICE;
                break;
        }

        Payment paymentDetails = processPayment(scanner, totalPrice);

        if (paymentDetails == null) {
            return null;
        }
        
        this.water -= drinkSelected.waterRequired;
        this.milk -= drinkSelected.milkRequired;
        this.beans -= drinkSelected.beansRequired;
        this.cups -= 1;

        Order order = new Order(drinkSelected, topping, paymentDetails);
        orderHistory.add(order);
        OrderLogger.saveOrder(order);
        return order;
        }



    public boolean checkResources(Drink drinkSelected) {
        return water >= drinkSelected.waterRequired &&
               milk >= drinkSelected.milkRequired &&
               beans >= drinkSelected.beansRequired &&
               cups > 0;
    }



    public Payment processPayment(Scanner scanner,int price){
//returns payment paid
        Payment payment;
        int amountRecieved,amountReturned;
        System.out.println("Enter payment( " +  price  + "$):");
       
        amountRecieved = scanner.nextInt();
        scanner.nextLine(); // removes leftover ENTER
        amountReturned = amountRecieved - price;
        
        if (amountRecieved < price){
            System.out.println("\nInvalid Amount!Transaction cancelled");
            return null ;
        }
        if (amountRecieved >= price){
                        System.out.println("\n\nAmount paid!\n Enjoy your drink...\n");
            money += price;
        }
        payment = new Payment(price, amountRecieved, amountReturned);
        return payment;

        
    }

    public void Refill(){
        this.water = 1000; //ml
        this.milk = 500; //ml
        this.beans = 200; ///g
        this.cups = 20;
        System.out.println("Machine is Refilled Succesfully");
    }

    void showStatus(){
        for(int i =0 ; i<50 ; i++){
            System.out.println();
        }
        System.out.println("Money earned : " + money + " $");
        System.out.println("Stock Available :");
        System.out.println("Milk  - " + milk + "ml");
        System.out.println("Water  - " + water + "ml");
        System.out.println("Beans  - " + beans + "g");
        System.out.println("cups  - " + cups);
    }

    public void showOrderHistory() {
    System.out.println("\n===== ORDER HISTORY =====");

    OrderLogger.showOrders();
}

    }




