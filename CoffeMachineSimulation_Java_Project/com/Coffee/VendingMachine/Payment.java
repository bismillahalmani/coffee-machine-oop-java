package com.Coffee.VendingMachine;

public class Payment {
    private int Price;
    private int amountRecieved;
    private int amountReturned;
    
    Payment(int Price,int amountRecieved,int amountReturned){
        this.Price = Price;
        this.amountRecieved = amountRecieved;
        this.amountReturned = amountReturned;
    }
    int getPrice(){
        return Price;
    }
    int getAmountRecieved(){
        return amountRecieved;
    }
    int getAmountReturned(){
        return amountReturned;
    }


}
