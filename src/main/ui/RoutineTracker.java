package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.graphics.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// Represents the Skincare Routine Tracker Application

public class RoutineTracker extends JFrame implements ActionListener, WindowListener {
    Scanner input = new Scanner(System.in);
    private static final String STORE_AT_FILE_PATH = "./data/mainCurrentRoutine.json";
    private JsonReader reader;
    private JsonWriter writer;

    private static final String FILE_NAME = "Current Routine";
    private Routine currentRoutine;
    private ProductCluster allProducts;

    private static final int APP_LENGTH = 700;
    private static final int APP_WIDTH = 700;
    private static final int MENU_BTN_X = APP_WIDTH / 4;
    private static final int MENU_BTN_Y = APP_LENGTH / 4;

    ArrayList<JPanel> allPanels;

    private AddProductForm addProductForm;
    private MainScreen mainScreen;
    private RoutineDisplay routineDisplay;
    private RemoveProductForm removeProductForm;

    // EFFECTS: creates a new RoutineTracker and sets up all panels and buttons so that only the
    //          main screen is visible when created
    public RoutineTracker() {
        setUpRoutines();
        addWindowListener(this);

        this.setTitle("Skincare Routine Tracker");
        this.setSize(APP_WIDTH, APP_LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpPanels();
        allPanels = new ArrayList<>();
        addPanelsToList();

        this.add(mainScreen);
        this.pack();
        this.setVisible(true);
        //runTracker();
    }

    // MODIFIES: allPanels
    private void addPanelsToList() {
        allPanels.add(mainScreen);
        allPanels.add(addProductForm);
        allPanels.add(routineDisplay);
    }

    // MODIFIES: mainScreen
    private void setMainButtonActions() {
        for (JButton b: mainScreen.getAllButtons()) {
            b.addActionListener(this);
        }
    }

    // MODIFIES: addProductForm
    private void setAddProductFormButtonActions() {
        addProductForm.getSubmitBtn().addActionListener(this);
        addProductForm.getHomeBtn().addActionListener(this);
    }

    // MODIFIES: addProductForm
    private void setFormRadioButtonActions() {
        for (JRadioButton rb: addProductForm.getAllRadioButtons()) {
            rb.addActionListener(this);
        }
    }

    // MODIFIES: routineDisplay
    private void setRoutineDisplayButtonActions() {
        for (JButton b: routineDisplay.getAllButtons()) {
            b.addActionListener(this);
        }
    }

    // MODIFIES: removeProductForm
    private void setRemoveProductFormButtonActions() {
        removeProductForm.getSubmitBtn().addActionListener(this);
        removeProductForm.getHomeBtn().addActionListener(this);
    }

    // EFFECTS: creates all other panels (Add, View, Search)
    public void setUpPanels() {
        mainScreen = new MainScreen(APP_WIDTH, APP_LENGTH, MENU_BTN_X, MENU_BTN_Y);
        setMainButtonActions();

        addProductForm = new AddProductForm(APP_WIDTH, APP_LENGTH);
        setAddProductFormButtonActions();
        setFormRadioButtonActions();

        routineDisplay = new RoutineDisplay(APP_WIDTH, APP_LENGTH);
        setRoutineDisplayButtonActions();

        removeProductForm = new RemoveProductForm(APP_WIDTH, APP_LENGTH);
        setRemoveProductFormButtonActions();
    }

    // MODIFIES: this
    // EFFECTS: adds addPanel to this and removes all the other panels
    public void addSelectPanelToFrame(JPanel addPanel) {
        for (JPanel jp: allPanels) {
            if (jp != addPanel) {
                this.remove(jp);
            } else {
                this.add(jp);
            }
        }
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
        System.out.println("6 | Save current skincare routine");
        System.out.println("7 | Load previous skincare routine");
        System.out.println("x | Exit Skincare Routine Tracker\n");
    }

    // MODIFIES: this
    public void setUpRoutines() {
        currentRoutine = new Routine(FILE_NAME);
        allProducts = new ProductCluster();

        input.useDelimiter("\n");

        reader = new JsonReader(STORE_AT_FILE_PATH);
        writer = new JsonWriter(STORE_AT_FILE_PATH);
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
            case "6":
                saveToFile();
                break;
            case "7":
                loadFromFile();
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
        toAddProduct.setCategory(Integer.parseInt(input.next()));

        System.out.println("Please enter the product usage frequency ('0' for daily, '1' for weekly):");
        toAddProduct.setUsage(Integer.parseInt(input.next()));

        System.out.println("Please enter the price of the product (in cents):");
        toAddProduct.setPrice(Integer.parseInt(input.next()));

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
            System.out.println("The product, '" + productName + "' was successfully removed from the routine. \n");
        } else {
            System.out.println("The product, '" + productName + "' does not exist in your current skincare routine.\n");
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
                    System.out.println("Usage: " + sp.getUsage() + "\n");
                }
            }
        } else {
            System.out.println("The product, '" + productName + "' does not exist in your current skincare routine.\n");
        }

    }

    // EFFECTS: Prints total cost of all products in the current skincare routine
    public void viewTotalExpenses() {
        System.out.println("TOTAL EXPENSES OF CURRENT SKINCARE ROUTINE");
        System.out.println("The total cost of the current routine is " + currentRoutine.totalExpenses() + "¢. \n");
    }

    // EFFECTS: saves current skincare routine to file
    public void saveToFile() {
        try {
            writer.openWriter();
            writer.write(currentRoutine);
            writer.closeWriter();

            currentRoutine.logSavedEvent();
            //System.out.println("Saved current skincare routine!");
        } catch (FileNotFoundException e) {
            //System.out.println("File not found. Skincare routine could not be saved.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads saved skincare routine from file
    public void loadFromFile() {
        try {
            currentRoutine = reader.read();

            currentRoutine.logLoadedEvent();
            //System.out.println("Successfully loaded previous skincare routine!");
        } catch (IOException e) {
            //System.out.println("Previous skincare routine could not be loaded.");
        }
    }

    // EFFECTS: processes button command and executes the command
    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainScreen.getAllButtons().contains(e.getSource())) {
            mainScreenAction(e);
        } else if (e.getSource() == addProductForm.getSubmitBtn()) {
            addProductFormActionBtn();
        } else if (e.getSource() == addProductForm.getHomeBtn()) {
            this.remove(addProductForm);
            this.add(mainScreen);
        } else if (e.getSource() == routineDisplay.getHomeBtn()) {
            this.remove(routineDisplay);
            this.add(mainScreen);
        } else if (e.getSource() == removeProductForm.getHomeBtn()) {
            this.remove(removeProductForm);
            this.add(mainScreen);
        } else if (e.getSource() == removeProductForm.getSubmitBtn()) {
            removeProductFormActionBtn();
        }
    }

    // MODIFIES: currentRoutine
    // EFFECTS: removes the product with the entered name from the current skincare routine, if such a product exists;
    //          otherwise, alerts user that the product does not exist
    private void removeProductFormActionBtn() {
        String productName = removeProductForm.getNameBox().getText();
        boolean noName = removeProductForm.getNameBox().getText().isEmpty();
        String msg;

        if (noName) {
            msg = "No name was filled in.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        } else if (currentRoutine.isInRoutine(productName)) {
            for (SkinProduct sp: currentRoutine.getRoutine()) {
                if (sp.getName().equals(productName)) {
                    currentRoutine.removeFromRoutine(sp);
                }
            }
            msg = "The product, " + productName + "was successfully removed.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        } else {
            msg = "This product does not exist in your routine.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // EFFECTS: Alerts user if the add product form was not filled properly, otherwise allows product to be added
    private void addProductFormActionBtn() {
        if (hasEmptyField()) {
            String msg = "Please fill all fields.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        } else if (alreadyExistsInRoutine()) {
            String msg = "A product with this name already exists in your skincare routine.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        } else {
            addProductToRoutine();
        }
    }

    // MODIFIES: currentRoutine, allProducts
    // EFFECTS: creates a new skin product with information entered on the form and adds it to the current
    //          skincare routine
    private void addProductToRoutine() {
        SkinProduct toAddProduct = new SkinProduct();
        currentRoutine.addToRoutine(toAddProduct);
        allProducts.addToCluster(toAddProduct);

        toAddProduct.setName(addProductForm.getNameBox().getText());
        toAddProduct.setBrand(addProductForm.getBrandBox().getText());
        toAddProduct.setPrice(Integer.parseInt(addProductForm.getPriceBox().getText()));
        toAddProduct.setCategory(getSelectedCategory());
        toAddProduct.setUsage(getSelectedUsage());

        String msg = "You have successfully added the product.";
        JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
    }

    // EFFECTS: returns index of USAGE selected; returns -1 if none were selected
    private int getSelectedUsage() {
        if (addProductForm.getDailyBtn().isSelected()) {
            return 0;
        } else if (addProductForm.getWeeklyBtn().isSelected()) {
            return 1;
        }
        return -1;
    }

    // EFFECTS: returns name of category that was selected; returns null if nothing was selected
    private String getSelectedCategory() {
        if (addProductForm.getCleanserBtn().isSelected()) {
            return "Cleanser";
        } else if (addProductForm.getTonerBtn().isSelected()) {
            return "Toner";
        } else if (addProductForm.getExfoliatorBtn().isSelected()) {
            return "Exfoliator";
        } else if (addProductForm.getSerumBtn().isSelected()) {
            return "Serum";
        } else if (addProductForm.getMoisturizerBtn().isSelected()) {
            return "Moisturizer";
        } else if (addProductForm.getEyeCreamBtn().isSelected()) {
            return "Eye Cream";
        } else if (addProductForm.getSpotBtn().isSelected()) {
            return "Spot Treatment";
        } else if (addProductForm.getSunscreenBtn().isSelected()) {
            return "Sunscreen";
        } else if (addProductForm.getFaceMaskBtn().isSelected()) {
            return "Face Mask";
        }
        return null;
    }

    // EFFECTS: returns true if product name in name text field is already in the routine
    private boolean alreadyExistsInRoutine() {
        String name = addProductForm.getNameBox().getText();
        return currentRoutine.isInRoutine(name);
    }

    // EFFECTS: returns true if a text field was not filled in
    private boolean hasEmptyField() {
        boolean noName = addProductForm.getNameBox().getText().isEmpty();
        boolean noBrand = addProductForm.getBrandBox().getText().isEmpty();
        boolean noPrice = addProductForm.getPriceBox().getText().isEmpty();
        return noName || noBrand || noPrice || noCategory() || noUsage();
    }

    // EFFECTS: returns true is no usage frequency was selected
    private boolean noUsage() {
        return !(addProductForm.getDailyBtn().isSelected())
                && !(addProductForm.getWeeklyBtn().isSelected());
    }

    // EFFECTS: returns true if no category was selected
    private boolean noCategory() {
        return !(addProductForm.getCleanserBtn().isSelected())
                && !(addProductForm.getTonerBtn().isSelected())
                && !(addProductForm.getExfoliatorBtn().isSelected())
                && !(addProductForm.getSerumBtn().isSelected())
                && !(addProductForm.getMoisturizerBtn().isSelected())
                && !(addProductForm.getEyeCreamBtn().isSelected())
                && !(addProductForm.getSpotBtn().isSelected())
                && !(addProductForm.getSunscreenBtn().isSelected())
                && !(addProductForm.getFaceMaskBtn().isSelected());
    }

    // EFFECTS: processes action of button pressed on the main screen and executes the command
    private void mainScreenAction(ActionEvent e) {
        if (e.getSource() == mainScreen.getAddBtn()) {
            this.remove(mainScreen);
            this.add(addProductForm);
        } else if (e.getSource() == mainScreen.getViewBtn()) {
            this.remove(mainScreen);
            updateRoutineDisplay();
            this.add(routineDisplay);
        } else if (e.getSource() == mainScreen.getSaveBtn()) {
            saveToFile();
            String msg = "You have successfully saved your current skincare routine.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == mainScreen.getLoadBtn()) {
            loadFromFile();
            String msg = "You have successfully loaded your previous skincare routine.";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == mainScreen.getRemoveBtn()) {
            this.remove(mainScreen);
            this.add(removeProductForm);
        } else if (e.getSource() == mainScreen.getExpenseBtn()) {
            String msg = "The total expense of your skincare routine is " + currentRoutine.totalExpenses() + "¢";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // MODIFIES: routineDisplay
    // EFFECTS: updates the routine display panel based on current skincare routine
    private void updateRoutineDisplay() {
        if (currentRoutine.isBlank()) {
            routineDisplay.emptyRoutineToDisplay(makeJListRoutine());
        } else {
            routineDisplay.addRoutineToDisplay(makeJListRoutine());
        }
    }

    // EFFECTS: returns a JList of the current skincare routine; if empty, returns a JList with message
    public JList makeJListRoutine() {
        DefaultListModel<String> myList = new DefaultListModel<>();

        if (currentRoutine.isBlank()) {
            myList.addElement("No products were added to the current skincare routine.\n");
        } else {
            for (int i = 0; i < currentRoutine.getRoutine().size(); i++) {
                SkinProduct sp = currentRoutine.getRoutine().get(i);
                int spot = i + 1;
                String info1 =  sp.getCategory() + "| Name: " + sp.getName() + " | Brand: " + sp.getBrand() + "| ";
                String info2 =  "Usage: " + sp.getUsage() + "| Price: " + sp.getPrice() + "¢";
                myList.addElement(spot + "." + info1 + info2);
            }
        }
        return new JList<>(myList);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    // EFFECTS: Prints out log once the program quits
    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("window closed :)");
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
