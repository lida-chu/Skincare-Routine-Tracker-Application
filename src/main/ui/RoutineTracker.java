package ui;

import model.ProductCluster;
import model.Routine;
import model.SkinProduct;

import java.util.Scanner;

// Represents the Skincare Routine Tracker Application

public class RoutineTracker {
    Scanner input = new Scanner(System.in);
    Routine currentRoutine;
    ProductCluster allProducts;
    ProductCluster avoidProducts;
    ProductCluster futureProducts;

    // EFFECTS: creates a new RoutineTracker
    public RoutineTracker() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: takes in user input
    public void runTracker() {
        boolean keepRunning = true;
        String command;

        setUpRoutines();

        System.out.println("Welcome to your Skincare Routine Tracker!\n");

        while (keepRunning) {
            displayOptions();
            command = input.next();

            if (command.equals("x")) {
                keepRunning = false;
            } else {
                chooseCommand(command);
            }
        }
    }

    // EFFECTS: Prints out next options
    public void displayOptions() {
        System.out.println("Please choose from the following options:");
        System.out.println("1 | View current skincare routine");
        System.out.println("2 | Add a product to your current skincare routine");
        System.out.println("3 | Remove a product from your current skincare routine");
        System.out.println("4 | Search for a product in your routine");
        System.out.println("5 | View total expenses");
        System.out.println("x | Exit Skincare Routine Tracker\n");
    }

    // MODIFIES: this
    public void setUpRoutines() {
        currentRoutine = new Routine();
        allProducts = new ProductCluster();
        avoidProducts = new ProductCluster();
        futureProducts = new ProductCluster();
    }

    // EFFECTS: Processes command inputted by user
    public void chooseCommand(String choice) {
        switch (choice) {
            case "1":
                viewRoutine();
                break;
            case "2":
                addProduct();
                break;
            case "3":
                removeProduct();
                break;
            case "4":
                viewProduct();
                break;
            case "5":
                viewTotalExpenses();
                break;
        }
    }

    // EFFECTS: Displays all products in current skincare routine
    public void viewRoutine() {
        if (currentRoutine.isBlank()) {
            System.out.println("No products were added to the current skincare routine.\n");
        } else {
            for (int i = 0; i < currentRoutine.getRoutine().size(); i++) {
                SkinProduct sp = currentRoutine.getRoutine().get(i);
                int spot = i + 1;
                System.out.println(spot + "." + sp.getCategory());
                System.out.println("Name: " + sp.getName() + " | Brand: " + sp.getBrand() + "\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a product to the current skincare routine based on user input
    public void addProduct() {
        SkinProduct toAddProduct = new SkinProduct();
        currentRoutine.addToRoutine(toAddProduct);
        allProducts.addToCluster(toAddProduct);

        System.out.println("ADDING A PRODUCT TO THE CURRENT SKINCARE ROUTINE \n");
        System.out.println("Please enter the name of the product:");
        toAddProduct.setName(input.next());

        System.out.println("Please enter the brand of the product:");
        toAddProduct.setBrand(input.next());

        System.out.println("Please enter the number corresponding to the product category:");
        displayCategories();
        toAddProduct.setCategory(Integer.parseInt(input.next())); // Note: add try-catch

        System.out.println("Please enter the product usage frequency ('0' for daily, '1' for weekly):");
        toAddProduct.setUsage(Integer.parseInt(input.next())); // Note: add try-catch

        System.out.println("Please enter the price of the product (in cents):");
        toAddProduct.setPrice(Integer.parseInt(input.next())); // Note: add try-catch

        System.out.println("You have successfully added the product. :)\n");
    }

    // EFFECTS: Prints out all skincare product categories
    public void displayCategories() {
        System.out.println("0 | Cleanser");
        System.out.println("1 | Exfoliator");
        System.out.println("2 | Toner");
        System.out.println("3 | Serum");
        System.out.println("4 | Moisturizer");
        System.out.println("5 | Eye Cream");
        System.out.println("6 | Spot Treatment");
        System.out.println("7 | Sunscreen");
        System.out.println("8 | Face Mask");
    }

    // MODIFIES: this
    // EFFECTS: Removes a product from the current skincare routine based on user input
    public void removeProduct() {
        System.out.println("REMOVING A PRODUCT FROM THE CURRENT SKINCARE ROUTINE");
        System.out.println("What is the name of the product you want to remove?");

        String productName = input.next();

        if (currentRoutine.isInRoutine(productName)) {
            for (SkinProduct sp: currentRoutine.getRoutine()) {
                if (sp.getName().equals(productName)) {
                    currentRoutine.removeFromRoutine(sp);
                }
            }
            System.out.println("The product, '" + productName + "' was successfully removed from the routine.");
        } else {
            System.out.println("The product, '" + productName + "' does not exist in your current skincare routine.");
        }
    }

    // EFFECTS: Displays a specific product's information based on user input
    public void viewProduct() {
        System.out.println("What is the name of the product you are searching for?");

        String productName = input.next();

        if (currentRoutine.isInRoutine(productName)) {
            for (SkinProduct sp: currentRoutine.getRoutine()) {
                if (sp.getName().equals(productName)) {
                    System.out.println("Product: " + sp.getName());
                    System.out.println("Brand: " + sp.getBrand());
                    System.out.println("Type: " + sp.getCategory());
                    System.out.println("Price: " + sp.getPrice() + "¢");
                    System.out.println("Usage: " + sp.getUsage());
                }
            }
        } else {
            System.out.println("The product, '" + productName + "' does not exist in your current skincare routine.");
        }

    }

    // EFFECTS: Prints total cost of all products in the current skincare routine
    public void viewTotalExpenses() {
        System.out.println("TOTAL EXPENSES OF CURRENT SKINCARE ROUTINE");
        System.out.println("The total cost of the current routine is " + currentRoutine.totalExpenses() + "¢ \n");
    }

}
