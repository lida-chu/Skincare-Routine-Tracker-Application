package model;

import java.util.List;

// Represents a skincare product that can be added to a skincare routine with a name, brand, type (category),
// price (cents), and frequency of usage.
public class SkinProduct {

    public static final List<String> CATEGORIES = List.of("Cleanser", "Exfoliator", "Toner", "Serum", "Moisturizer",
            "Eye Cream", "Sunscreen", "Spot Treatment", "Face Mask");
    public static final List<String> USAGE = List.of("Daily", "Weekly");

    private String name;
    private String brand;
    private String category;
    private String usageFrequency;
    private String notes;
    private int price;

    // EFFECTS: Creates a new skin product with the given name and brand
    public SkinProduct(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    // MODIFIES: this
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns name of the skin product
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    public void setBrand() {
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

    // MODIFIES: this
    public void setNotes(String notes) {
        this.notes = notes;
    }

    // EFFECTS: returns the notes on the skin product
    public String getNotes() {
        return this.notes;
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
