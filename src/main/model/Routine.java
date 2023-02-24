package model;

import java.util.LinkedList;
import java.util.List;

// Represents a skincare routine
public class Routine {

    private List<SkinProduct> routine;
    private boolean outOfBudget;
    private boolean validRoutine;

    // EFFECTS: Create new blank skincare routine with all statuses set to false
    public Routine() {
        this.routine = new LinkedList<>();
        this.outOfBudget = false;
        this.validRoutine = false;
    }

    // EFFECTS: returns true if product is in routine
    public Boolean isInRoutine(SkinProduct product) {
        return this.routine.contains(product);
    }

    // EFFECTS: returns true if product with given name is in routine
    public Boolean isInRoutine(String productName) {
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

    // MODIFIES: this
    public void setValidRoutine() {
        this.validRoutine = isValid();
    }

    public boolean getValidRoutine() {
        return this.validRoutine;
    }

    //EFFECTS: returns true if the routine has at least one serum and face mask, and one product per every other
    //         category
    public boolean isValid() {
        for (int i = 0; i < SkinProduct.CATEGORIES.size(); i++) {
            String currentCat = SkinProduct.CATEGORIES.get(i);
            if ((currentCat.equals("Serum")) || (currentCat.equals("Face Mask"))) {
                if (numInCategory(currentCat) < 1) {
                    return false;
                }
            } else {
                if ((numInCategory(currentCat) > 1) || (numInCategory(currentCat) < 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    // REQUIRES: catIndex is a number between 0 inclusive and SkinProduct.CATEGORIES.size() exclusive, and category is
    //           a string in SkinProduct.CATEGORIES
    // EFFECTS: returns number of products in current skincare routine
    public int numInCategory(String category) {
        int count = 0;
        for (SkinProduct sp: routine) {
            if (sp.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }


}
