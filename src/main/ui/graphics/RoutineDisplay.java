package ui.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the panel that displays the current skincare routine (when 'View Routine' is pressed)
public class RoutineDisplay extends AppPanel {

    private JPanel titlePanel;
    private JPanel homeBtnPanel;
    private JPanel displayPanel;

    private JButton homeBtn;
    private JLabel titleLbl;
    private JLabel emptyLbl;

    private ArrayList<JButton> allButtons;

    public RoutineDisplay(int width, int length) {
        this.setLayout(new BorderLayout());
        setUpPanels(width, length);
        setUpComponents();

        allButtons = new ArrayList<>();
        allButtons.add(homeBtn);

        homeBtnPanel.add(homeBtn);
        titlePanel.add(titleLbl);

        this.setBackground(new Color(202, 145, 255));
        addPanels();
    }

    private void setUpComponents() {
        homeBtn = new JButton("Return to Main Screen");
        titleLbl = new JLabel("Your Skincare Routine");
        emptyLbl = new JLabel("No products have been added.");
    }

    private void setUpPanels(int width, int length) {
        displayPanel = new JPanel();
        titlePanel = new JPanel();
        homeBtnPanel = new JPanel();

        displayPanel.setPreferredSize(new Dimension(width, length * 6 / 8));
        titlePanel.setPreferredSize(new Dimension(width, length / 8));
        homeBtnPanel.setPreferredSize(new Dimension(width, length / 8));
    }

    private void addPanels() {
        this.add(displayPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(homeBtnPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: displayPanel
    // EFFECTS: removes the empty label and adds the routine list
    public void addRoutineToDisplay(JList routineJList) {
        displayPanel.remove(emptyLbl);
        displayPanel.add(routineJList);
    }

    // MODIFIES: displayPanel
    // EFFECTS: removes the routine list and adds the empty label
    public void emptyRoutineToDisplay(JList routineJList) {
        displayPanel.remove(routineJList);
        displayPanel.add(emptyLbl);
    }

    @Override
    public ArrayList<JButton> getAllButtons() {
        return allButtons;
    }

    public JButton getHomeBtn() {
        return homeBtn;
    }
}
