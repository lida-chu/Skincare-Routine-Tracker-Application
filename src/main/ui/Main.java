package ui;

import ui.graphics.AddProductForm;
import ui.graphics.MainScreen;
import ui.graphics.RemoveProductForm;
import ui.graphics.RoutineDisplay;

import javax.swing.*;

// Starts the Skincare Routine Tracker
public class Main {
    public static void main(String[] args) {

        /*JFrame myFrame = new JFrame();
        RemoveProductForm myMS = new RemoveProductForm(500, 500);
        myFrame.add(myMS);
        myFrame.pack();
        myFrame.setVisible(true);*/

        new RoutineTracker();
    }
}
