package ui.graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// Represents the form that is displayed and used to add a new skincare product to the routine
public class AddProductForm extends AppPanel {

    private JPanel titleArea;
    private JPanel homeBtnArea;
    private JPanel formArea;

    private JLabel titleLbl;

    private JButton homeBtn;

    private JTextField nameBox;
    private JTextField brandBox;
    private JTextField priceBox;

    private JLabel nameLbl;
    private JLabel brandLbl;
    private JLabel priceLbl;
    private JLabel categoryLbl;
    private JLabel usageLbl;

    private JPanel categoryPanel;
    private JPanel usagePanel;
    
    private JRadioButton cleanserBtn;
    private JRadioButton tonerBtn;
    private JRadioButton exfoliatorBtn;
    private JRadioButton serumBtn;
    private JRadioButton moisturizerBtn;
    private JRadioButton eyeCreamBtn;
    private JRadioButton spotBtn;
    private JRadioButton sunscreenBtn;
    private JRadioButton faceMaskBtn;

    private JRadioButton dailyBtn;
    private JRadioButton weeklyBtn;

    private JButton submitBtn;

    private ButtonGroup categoryGroup;
    private ButtonGroup usageGroup;

    private ArrayList<JButton> formButtons;
    private ArrayList<JRadioButton> formRadioButtons;

    private int width;
    private int height;

    private static final Color TEXT_COLOR = new Color(0,0,0);
    private static final Color TEXT_FIELD_COLOR = new Color(151, 255, 255);
    private static final Font TEXT_FIELD_FONT = new Font("Consolas", Font.PLAIN, 35);


    public AddProductForm(int width, int height) {
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

    private void makeTitleArea() {
        titleLbl = new JLabel("Adding a Skin Product to Routine");
        titleArea.add(titleLbl);
    }

    private void makeHomeArea() {
        homeBtn = new JButton("Return to Main Menu");
        homeBtnArea.add(homeBtn);
    }

    private void setUpAreas() {
        titleArea = new JPanel();
        homeBtnArea = new JPanel();
        formArea = new JPanel();

        titleArea.setPreferredSize(new Dimension(width, height / 8));
        homeBtnArea.setPreferredSize(new Dimension(width, height / 8));
        formArea.setPreferredSize(new Dimension(width, 6 * height / 8));
    }

    private void makeForm() {
        formArea.setLayout(new GridLayout(6, 2));
        makeFormButtons();
        makeButtonPanels();
        makeTextBoxes();
        makeFormLabels();
        addFormComponents();

        formButtons = new ArrayList<>();
        formRadioButtons = new ArrayList<>();
        addButtonsToList();
        addRadioButtonsToList();
    }

    private void makeButtonPanels() {
        categoryPanel = new JPanel();
        usagePanel = new JPanel();
        
        categoryPanel.setLayout(new GridLayout(5, 2));
        usagePanel.setLayout(new GridLayout(1, 2));
    }

    private void makeFormLabels() {
        nameLbl = new JLabel("Name:");
        brandLbl = new JLabel("Brand:");
        priceLbl = new JLabel("Price (in Cents)");
        categoryLbl = new JLabel("Category:");
        usageLbl = new JLabel("Usage Frequency:");
    }

    private void makeFormButtons() {
        cleanserBtn = new JRadioButton("Cleanser");
        tonerBtn = new JRadioButton("Toner");
        exfoliatorBtn = new JRadioButton("Exfoliator");
        serumBtn = new JRadioButton("Serum");
        moisturizerBtn = new JRadioButton("Moisturizer");
        eyeCreamBtn = new JRadioButton("Eye Cream");
        spotBtn = new JRadioButton("Spot Treatment");
        sunscreenBtn = new JRadioButton("Sunscreen");
        faceMaskBtn = new JRadioButton("Face Mask");

        dailyBtn = new JRadioButton("Daily");
        weeklyBtn = new JRadioButton("Weekly");

        submitBtn = new JButton("Add to Current Routine");

        addButtonsToGroup();
    }

    private void addButtonsToGroup() {
        categoryGroup = new ButtonGroup();
        categoryGroup.add(cleanserBtn);
        categoryGroup.add(tonerBtn);
        categoryGroup.add(exfoliatorBtn);
        categoryGroup.add(serumBtn);
        categoryGroup.add(moisturizerBtn);
        categoryGroup.add(eyeCreamBtn);
        categoryGroup.add(spotBtn);
        categoryGroup.add(sunscreenBtn);
        categoryGroup.add(faceMaskBtn);

        usageGroup = new ButtonGroup();
        usageGroup.add(dailyBtn);
        usageGroup.add(weeklyBtn);
    }

    private void makeTextBoxes() {
        nameBox = new JTextField();
        brandBox = new JTextField();
        priceBox = new JTextField();

        setTextBoxVisuals();
    }

    private void setTextBoxVisuals() {
        nameBox.setFont(TEXT_FIELD_FONT);
        nameBox.setForeground(TEXT_COLOR);
        nameBox.setBackground(TEXT_FIELD_COLOR);

        brandBox.setFont(TEXT_FIELD_FONT);
        brandBox.setForeground(TEXT_COLOR);
        brandBox.setBackground(TEXT_FIELD_COLOR);

        priceBox.setFont(TEXT_FIELD_FONT);
        priceBox.setForeground(TEXT_COLOR);
        priceBox.setBackground(TEXT_FIELD_COLOR);
    }

    private void addFormComponents() {
        formArea.add(nameLbl);
        formArea.add(nameBox);
        
        formArea.add(brandLbl);
        formArea.add(brandBox);

        formArea.add(priceLbl);
        formArea.add(priceBox);
        
        addButtonsToPanels();

        formArea.add(categoryLbl);
        formArea.add(categoryPanel);

        formArea.add(usageLbl);
        formArea.add(usagePanel);

        formArea.add(new JLabel());
        formArea.add(submitBtn);
    }

    private void addButtonsToPanels() {
        categoryPanel.add(cleanserBtn);
        categoryPanel.add(tonerBtn);
        categoryPanel.add(exfoliatorBtn);
        categoryPanel.add(serumBtn);
        categoryPanel.add(moisturizerBtn);
        categoryPanel.add(eyeCreamBtn);
        categoryPanel.add(spotBtn);
        categoryPanel.add(sunscreenBtn);
        categoryPanel.add(faceMaskBtn);

        usagePanel.add(dailyBtn);
        usagePanel.add(weeklyBtn);
    }

    private void addAreasToMainPanel() {
        this.add(titleArea, BorderLayout.NORTH);
        this.add(homeBtnArea, BorderLayout.SOUTH);
        this.add(formArea, BorderLayout.CENTER);
    }

    private void addButtonsToList() {
        formButtons.add(homeBtn);
        formButtons.add(submitBtn);
    }

    private void addRadioButtonsToList() {
        formRadioButtons.add(cleanserBtn);
        formRadioButtons.add(tonerBtn);
        formRadioButtons.add(exfoliatorBtn);
        formRadioButtons.add(serumBtn);
        formRadioButtons.add(moisturizerBtn);
        formRadioButtons.add(eyeCreamBtn);
        formRadioButtons.add(spotBtn);
        formRadioButtons.add(sunscreenBtn);
        formRadioButtons.add(faceMaskBtn);
        formRadioButtons.add(dailyBtn);
        formRadioButtons.add(weeklyBtn);
    }

    @Override
    public ArrayList<JButton> getAllButtons() {
        return formButtons;
    }

    public ArrayList<JRadioButton> getAllRadioButtons() {
        return formRadioButtons;
    }

    public JTextField getNameBox() {
        return nameBox;
    }

    public JTextField getBrandBox() {
        return brandBox;
    }

    public JTextField getPriceBox() {
        return priceBox;
    }

    public JRadioButton getCleanserBtn() {
        return cleanserBtn;
    }

    public JRadioButton getTonerBtn() {
        return tonerBtn;
    }

    public JRadioButton getExfoliatorBtn() {
        return exfoliatorBtn;
    }

    public JRadioButton getSerumBtn() {
        return serumBtn;
    }

    public JRadioButton getMoisturizerBtn() {
        return moisturizerBtn;
    }

    public JRadioButton getEyeCreamBtn() {
        return eyeCreamBtn;
    }

    public JRadioButton getSpotBtn() {
        return spotBtn;
    }

    public JRadioButton getSunscreenBtn() {
        return sunscreenBtn;
    }

    public JRadioButton getFaceMaskBtn() {
        return faceMaskBtn;
    }

    public JRadioButton getDailyBtn() {
        return dailyBtn;
    }

    public JRadioButton getWeeklyBtn() {
        return weeklyBtn;
    }

    public JButton getHomeBtn() {
        return homeBtn;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }
}
