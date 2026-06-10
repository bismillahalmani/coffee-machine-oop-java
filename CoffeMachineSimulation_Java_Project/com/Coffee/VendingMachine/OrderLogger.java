package com.Coffee.VendingMachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OrderLogger {

    public static void saveOrder(Order order) {
        try {
            FileWriter writer = new FileWriter("orders.txt", true);

            writer.write("Drink: " + order.drink.name + "\n");
            writer.write("Topping: " + order.toppingChoice + "\n");
            writer.write("Paid: " + order.payment.getPrice() + "\n");
            writer.write("Received: " + order.payment.getAmountRecieved() + "\n");
            writer.write("Returned: " + order.payment.getAmountReturned() + "\n");
            writer.write("-------------------------\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving order.");
        }
    }


    public static void showOrders() {
    try {
        File file = new File("orders.txt");

        if (!file.exists()) {
            System.out.println("No history found.");
            return;
        }

        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            System.out.println(reader.nextLine());
        }

        reader.close();

    } catch (Exception e) {
        System.out.println("Error reading file.");
    }
}
}