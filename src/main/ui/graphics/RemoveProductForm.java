package ui.graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class RemoveProductForm extends AppPanel {

    private JPanel titleArea;
    private JPanel homeBtnArea;
    private JPanel formArea;

    private JLabel titleLbl;
    private JLabel nameLbl;

    private JButton homeBtn;
    private JButton submitBtn;

    private JTextField nameBox;

    private ArrayList<JButton> allButtons;

    private static final Color TEXT_COLOR = new Color(0,0,0);
    private static final Color TEXT_FIELD_COLOR = new Color(151, 255, 255);
    private static final Font TEXT_FIELD_FONT = new Font("Consolas", Font.PLAIN, 35);

    private int width;
    private int height;

    public RemoveProductForm(int width, int height) {
        this.height = height;
        this.width = width;

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        setUpAreas();
        makeForm();
        makeHomeArea();
        makeTitleArea();
        addAreasToMainPanel();
    }

    private void addAreasToMainPanel() {
        this.add(titleArea, BorderLayout.NORTH);
        this.add(formArea, BorderLayout.CENTER);
        this.add(homeBtnArea, BorderLayout.SOUTH);
    }

    private void makeTitleArea() {
        titleLbl = new JLabel("Removing a Skin Product from Skincare Routine");
        titleArea.add(titleLbl);
    }

    private void makeHomeArea() {
        homeBtn = new JButton("Return to Main Menu");
        homeBtnArea.add(homeBtn);
    }

    private void makeForm() {
        formArea.setLayout(new GridLayout(2, 2));

        submitBtn = new JButton("Remove Product");
        nameLbl = new JLabel("Please enter the name of the product you want to remove:");
        nameBox = new JTextField();
        setTextBoxVisuals();

        allButtons = new ArrayList<>();
        addButtonsToList();

        formArea.add(nameLbl);
        formArea.add(nameBox);
        formArea.add(new JLabel());
        formArea.add(submitBtn);
    }

    private void setTextBoxVisuals() {
        nameBox.setFont(TEXT_FIELD_FONT);
        nameBox.setForeground(TEXT_COLOR);
        nameBox.setBackground(TEXT_FIELD_COLOR);
    }

    private void addButtonsToList() {
        allButtons.add(homeBtn);
        allButtons.add(submitBtn);
    }

    private void setUpAreas() {
        titleArea = new JPanel();
        homeBtnArea = new JPanel();
        formArea = new JPanel();

        titleArea.setPreferredSize(new Dimension(width, height / 8));
        homeBtnArea.setPreferredSize(new Dimension(width, height / 8));
        formArea.setPreferredSize(new Dimension(width, 6 * height / 8));
    }

    public JButton getHomeBtn() {
        return homeBtn;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public JTextField getNameBox() {
        return nameBox;
    }

    @Override
    public ArrayList<JButton> getAllButtons() {
        return allButtons;
    }
}
