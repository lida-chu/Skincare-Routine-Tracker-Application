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
    private EventLog el;

    // EFFECTS: Create new blank skincare routine with outOfBudget and validRoutine set to false, and starts
    //          an event log
    public Routine() {
        this.routine = new LinkedList<>();
        this.outOfBudget = false;
        this.validRoutine = false;
        this.el = EventLog.getInstance();
    }

    // EFFECTS: Create new blank skincare routine with given name and outOfBudget and validRoutine set to false,
    //          and starts an event log
    public Routine(String title) {
        this.name = title;
        this.routine = new LinkedList<>();
        this.outOfBudget = false;
        this.validRoutine = false;
        this.el = EventLog.getInstance();
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
        el.logEvent(new Event("Added product to routine."));
    }

    // REQUIRES: Product must already exist in the collection
    // MODIFIES: this
    public void removeFromRoutine(SkinProduct product) {
        this.routine.remove(product);
        el.logEvent(new Event("Removed product from routine."));
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
        el.logEvent(new Event("Displayed total expenses."));
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
        for (int i = 0; i < SkinProduct.CATEGORIES.size(); i++) {
            String currentCat = SkinProduct.CATEGORIES.get(i);
            Boolean otherCategory = (!(currentCat.equals("Serum"))) && (!(currentCat.equals("Face Mask")));

            if ((numInCategory(currentCat) < 1)) {
                return false;
            } else if (otherCategory && (numInCategory(currentCat) > 1)) {
                return false;
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

    // EFFECTS: Logs new event for when the routine is saved to file.
    public void logSavedEvent() {
        el.logEvent(new Event("Saved current routine."));
    }

    // EFFECTS: Logs new event for when the previous routine is loaded from file.
    public void logLoadedEvent() {
        el.logEvent(new Event("Loaded previous routine."));
    }


}
