package ui.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the main screen that is shown when app first starts
public class MainScreen extends AppPanel {

    private JPanel welcomeScreen;
    private JPanel controlPanel;

    private JButton homeBtn;
    private JButton addBtn;
    private JButton viewBtn;
    private JButton searchBtn;
    private JButton removeBtn;
    private JButton expenseBtn;
    private JButton loadBtn;
    private JButton saveBtn;

    private ImageIcon soapBottle;
    private JLabel welcomeLbl;

    private int buttonWidth;
    private int buttonHeight;

    private ArrayList<JButton> menuButtons;

    public MainScreen(int screenWidth, int screenHeight, int buttonWidth, int buttonHeight) {
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;

        controlPanel = new JPanel();
        welcomeScreen = new JPanel();

        controlPanel.setPreferredSize(new Dimension(screenWidth, screenHeight / 2));
        controlPanel.setLayout(new GridLayout(2, 4));
        welcomeScreen.setPreferredSize(new Dimension(screenWidth, screenHeight / 2));

        setUpButtons();
        menuButtons = new ArrayList<>();
        addButtonsToList();
        addButtonsToControlPanel();

        setUpWelcomeScreenComponents();
        addWelcomeScreenComponents();

        this.setLayout(new BorderLayout());
        this.add(welcomeScreen, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setVisible(true);
    }

    private void addWelcomeScreenComponents() {
        welcomeScreen.add(welcomeLbl);
    }

    private void setUpWelcomeScreenComponents() {
        soapBottle = new ImageIcon(this.getClass().getResource("/icons/bottle_icon.png"));
        Image image = soapBottle.getImage();
        Image newImg = image.getScaledInstance(120,120, java.awt.Image.SCALE_SMOOTH);
        soapBottle = new ImageIcon(newImg);

        welcomeLbl = new JLabel("Welcome to your Skincare Routine Tracker");
        welcomeLbl.setIcon(soapBottle);
    }

    private void addButtonsToList() {

        menuButtons.add(viewBtn);
        menuButtons.add(addBtn);
        menuButtons.add(searchBtn);
        menuButtons.add(removeBtn);

        menuButtons.add(expenseBtn);
        menuButtons.add(loadBtn);
        menuButtons.add(saveBtn);
        menuButtons.add(homeBtn);
    }

    @Override
    public ArrayList<JButton> getAllButtons() {
        return menuButtons;
    }

    private void addButtonsToControlPanel() {
        controlPanel.add(viewBtn);
        controlPanel.add(addBtn);
        controlPanel.add(searchBtn);
        controlPanel.add(removeBtn);
        controlPanel.add(expenseBtn);
        controlPanel.add(loadBtn);
        controlPanel.add(saveBtn);
        controlPanel.add(homeBtn);
    }

    // MODIFIES: homeBtn, addBtn, viewBtn, searchBtn, removeBtn, expenseBtn, loadBtn, saveBtn
    // EFFECTS: creates all the buttons for the main gui frame
    private void setUpButtons() {
        homeBtn = new JButton("Home");
        addBtn = new JButton("Add Product");
        viewBtn = new JButton("View Routine");
        searchBtn = new JButton("Search Routine");
        removeBtn = new JButton("Remove Product");
        expenseBtn = new JButton("Expenses");;
        loadBtn = new JButton("Load Previous Routine");
        saveBtn = new JButton("Save Current Routine");
    }

    public JButton getHomeBtn() {
        return homeBtn;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getViewBtn() {
        return viewBtn;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public JButton getExpenseBtn() {
        return expenseBtn;
    }

    public JButton getLoadBtn() {
        return loadBtn;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }
}
