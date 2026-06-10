package com.Coffee.VendingMachine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();

        while (true) {

            System.out.println("\n======= COFFEE MACHINE =======");
            System.out.println("1. Buy Coffee");
            System.out.println("2. Admin Panel");
            System.out.println("0. Exit");

            int mainChoice = Integer.parseInt(scanner.nextLine());

            if (mainChoice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            switch (mainChoice) {

                // ================= CUSTOMER =================
                case 1: {

                    machine.showMenu();

                    int drinkchoice = machine.choiceDrink(scanner);

                    if (drinkchoice == 0) {
                        System.out.println("Returning...");
                        break;
                    }

                    int toppingchoice = machine.choiceTopping(scanner);

                    Order order = machine.makeCoffee(drinkchoice, toppingchoice, scanner);

                    if (order != null) {
                        order.generateReceipt();

                        System.out.println("\nPress ENTER to continue OR type 0 to go back...");
                        String input = scanner.nextLine();

                        if (input.equals("0")) {
                            break;
                        }

                        for (int i = 0; i < 50; i++) {
                           System.out.println();
                            }
                    }

                    break;
                }

                // ================= ADMIN =================
                case 2: {

                    boolean inAdmin = true;
                    for (int i = 0; i < 50; i++) {
                            System.out.println();
                            }

                    while (inAdmin) {

                        System.out.println("\n======= ADMIN PANEL =======");
                        System.out.println("1. Show Machine Status");
                        System.out.println("2. Show Order History");
                        System.out.println("3. Refill Machine");
                        System.out.println("0. Back");

                        int adminChoice = Integer.parseInt(scanner.nextLine());

                        switch (adminChoice) {

                            case 1:
                                machine.showStatus();
                                break;

                            case 2:
                                machine.showOrderHistory();
                                break;

                            case 3:
                                machine.Refill();
                                break;

                            case 0:{
                                inAdmin = false;
                                 for (int i = 0; i < 50; i++) {
                                System.out.println();
                                }
                                break;
                            }

                            default:{
                                System.out.println("Invalid admin option");
                            }
                            for (int i = 0; i < 50; i++) {
                            System.out.println();
                            }
                        }
                    }

                    break;
                }

                default:
                    System.out.println("Invalid option");
            }
        }

        scanner.close();
    }
}


