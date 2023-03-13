package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Represents a skincare routine; a specific list of skincare products
public class Routine implements Writable {

    private String name;
    private List<SkinProduct> routine;
    private boolean outOfBudget;
    private boolean validRoutine;

    // EFFECTS: Create new blank skincare routine with outOfBudget and validRoutine set to false
    public Routine() {
        this.routine = new LinkedList<>();
        this.outOfBudget = false;
        this.validRoutine = false;
    }

    // EFFECTS: Create new blank skincare routine with given name and outOfBudget and validRoutine set to false
    public Routine(String title) {
        this.name = title;
        this.routine = new LinkedList<>();
        this.outOfBudget = false;
        this.validRoutine = false;
    }

    public String getName() {
        return this.name;
    }

    // EFFECTS: returns true if product is in routine
    public boolean isInRoutine(SkinProduct product) {
        return this.routine.contains(product);
    }

    // EFFECTS: returns true if product with given name is in routine
    public boolean isInRoutine(String productName) {
        for (SkinProduct sp: routine) {
            if (sp.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns routine
    public List<SkinProduct> getRoutine() {
        return this.routine;
    }

    // MODIFIES: this
    public void addToRoutine(SkinProduct product) {
        this.routine.add(product);
    }

    // REQUIRES: Product must already exist in the collection
    // MODIFIES: this
    public void removeFromRoutine(SkinProduct product) {
        this.routine.remove(product);
    }

    // EFFECTS: returns outOfBudget value
    public boolean getOutOfBudget() {
        return this.outOfBudget;
    }

    // EFFECTS: returns total cost of all products in the routine in cents
    public int totalExpenses() {
        int total = 0;
        for (SkinProduct s: this.routine) {
            total += s.getPrice();
        }
        return total;
    }

    // REQUIRES: budget is a non-negative integer
    // MODIFIES: this
    public void checkBudget(int budget) {
        outOfBudget = this.totalExpenses() > budget;
    }

    // EFFECTS: return true if routine is empty
    public boolean isBlank() {
        return this.routine.isEmpty();
    }

    public void setValidRoutine() {
        this.validRoutine = isValid();
    }

    public boolean getValidRoutine() {
        return this.validRoutine;
    }

    //EFFECTS: returns true if the routine has at least one serum and face mask, and only one product per every other
    //         category; returns false otherwise
    public boolean isValid() {
        int numSerum = numInCategory("Serum");
        int numMask = numInCategory("Face Mask");

        if ((numSerum < 1) || (numMask < 1)) {
            return false;
        } else {
            for (int i = 0; i < SkinProduct.CATEGORIES.size(); i++) {
                String currentCat = SkinProduct.CATEGORIES.get(i);
                Boolean otherCategory = (!(currentCat.equals("Serum"))) && (!(currentCat.equals("Face Mask")));

                if (otherCategory && !(numInCategory(currentCat) == 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    // REQUIRES: category is a string in SkinProduct.CATEGORIES
    // EFFECTS: returns number of products of the given category in the current skincare routine
    public int numInCategory(String category) {
        int count = 0;
        for (SkinProduct sp: routine) {
            if (sp.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: returns a Json representation of a skin routine
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Products", productsToJson());
        return json;
    }

    // EFFECTS: returns the skin products in the routine as a Json array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SkinProduct sp : routine) {
            jsonArray.put(sp.toJson());
        }

        return jsonArray;
    }


}
