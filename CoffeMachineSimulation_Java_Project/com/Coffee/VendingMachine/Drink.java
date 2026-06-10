package com.Coffee.VendingMachine;
public class Drink{
   String name;
   int waterRequired;
   int milkRequired;
   int beansRequired;
   int price;
   

   Drink(String name,int waterRequired,int milkRequired,int beansRequired,int price){
    this.name = name;
    this.waterRequired = waterRequired;
    this.milkRequired = milkRequired;
    this.beansRequired = beansRequired;
    this.price = price;

   }
   

}