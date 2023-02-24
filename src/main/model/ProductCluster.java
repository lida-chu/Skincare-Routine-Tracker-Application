package model;

import java.util.LinkedList;
import java.util.List;

// Represents a cluster (or collection) of skin products
public class ProductCluster {
    private List<SkinProduct> cluster;

    // EFFECT: Create new empty collection of skin products
    public ProductCluster() {
        this.cluster = new LinkedList<>();
    }

    // EFFECTS: returns cluster
    public List<SkinProduct> getCluster() {
        return this.cluster;
    }

    // MODIFIES: this
    public void addToCluster(SkinProduct product) {
        this.cluster.add(product);
    }

    // REQUIRES: Product must already exist in the collection
    // MODIFIES: this
    public void removeFromCluster(SkinProduct product) {
        this.cluster.remove(product);
    }

    // EFFECTS: returns true if product is in routine
    public Boolean isInCluster(SkinProduct product) {
        return this.cluster.contains(product);
    }

    // EFFECTS: returns true if product with given name is in routine
    public Boolean isInCluster(String productName) {
        for (SkinProduct sp: cluster) {
            if (sp.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

}
