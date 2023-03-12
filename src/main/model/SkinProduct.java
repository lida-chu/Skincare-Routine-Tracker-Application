package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a skincare product with a name, brand, type (category), price (cents), and frequency of usage.
public class SkinProduct implements Writable {

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }

    // REQUIRES: Integer provided is [0, CATEGORIES.size())
    // MODIFIES: this
    public void setCategory(int categoryNum) {
        this.category = CATEGORIES.get(categoryNum);
    }

    // REQUIRES: String provided must be included in CATEGORIES
    // MODIFIES: this
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    // REQUIRES: Integer provided is [0, 1]
    // MODIFIES: this
    public void setUsage(int usageNum) {
        this.usageFrequency = USAGE.get(usageNum);
    }

    // REQUIRES: String provided must be included in USAGE
    // MODIFIES: this
    public void setUsage(String usage) {
        this.usageFrequency = usage;
    }

    public String getUsage() {
        return this.usageFrequency;
    }

    // EFFECTS: Returns the json representation of a skin product
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("Name", name);
        json.put("Brand", brand);
        json.put("Category", category);
        json.put("Usage Frequency", usageFrequency);
        json.put("Price", price);

        return json;
    }
}
