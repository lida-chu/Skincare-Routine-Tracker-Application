package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a skincare product that can be added to a skincare routine with a name, brand, type (category),
// price (cents), and frequency of usage.
public class SkinProduct {

    /* public static final List<String> CATEGORIES = new ArrayList<>(List.of("Cleanser", "Exfoliator", "Toner", "Serum",
            "Moisturizer", "Eye Cream", "Spot Treatment", "Sunscreen", "Face Mask"));*/
    public static final List<String> USAGE = new ArrayList<>(Arrays.asList("Daily", "Weekly"));

    public static final List<String> CATEGORIES = new ArrayList<>(Arrays.asList(
            "Cleanser", "Exfoliator", "Toner", "Serum", "Moisturizer", "Eye Cream", "Spot Treatment", "Sunscreen",
            "Face Mask"
    ));

    private String name;
    private String brand;
    private String category;
    private String usageFrequency;
    private int price;

    // EFFECTS: Creates a new skin product with the given name and brand
    public SkinProduct(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    // EFFECTS: Creates a new skin product
    public SkinProduct() {}

    // MODIFIES: this
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns name of the skin product
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // EFFECTS: returns brand of the skin product
    public String getBrand() {
        return this.brand;
    }

    // REQUIRES: Integer provided is [0, CATEGORIES.size())
    // MODIFIES: this
    public void setCategory(int categoryNum) {
        this.category = CATEGORIES.get(categoryNum);
    }

    // EFFECTS: returns the category of the skin product
    public String getCategory() {
        return this.category;
    }

    // MODIFIES: this
    public void setPrice(int price) {
        this.price = price;
    }

    // EFFECTS: returns price (in cents) of skin product
    public int getPrice() {
        return this.price;
    }

    // REQUIRES: Integer provided is [0, 1]
    // MODIFIES: this
    public void setUsage(int usageNum) {
        this.usageFrequency = USAGE.get(usageNum);
    }

    // EFFECTS: returns usage frequency of the skin product
    public String getUsage() {
        return this.usageFrequency;
    }
}
