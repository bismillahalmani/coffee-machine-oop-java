package com.Coffee.VendingMachine;

public class Order {
    Drink drink;
    String toppingChoice;
    Payment payment;
    Order(Drink drink,String toppingChoice,Payment paymentDetails){
        this.drink = drink;
        this.payment = paymentDetails; 
        this.toppingChoice = toppingChoice;
    }

    public void generateReceipt(){
        System.out.println("----------------RECIEPT----------------");
        System.out.println("Drink : " + drink.name);
        System.out.println("Toppings : " + toppingChoice);
        System.out.println("Amount Recieved : " + payment.getAmountRecieved() + "$");
        System.out.println("Amount Paid : " + payment.getPrice()  + "$");
        System.out.println("Amount Returned : " + payment.getAmountReturned() + "$");
    }
}
