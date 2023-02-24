package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductClusterTest {

    SkinProduct testCleanser = new SkinProduct("Foaming Facial Cleanser", "Cerave");
    SkinProduct testExfoliator = new SkinProduct("2% BHA Liquid Exfoliant", "Paula's Choice");
    SkinProduct testToner = new SkinProduct("Advanced Snail 96 Mucin Power Essence", "Corsyx");

    ProductCluster testCluster1;
    ProductCluster testCluster2;

    @BeforeEach
    public void setUp() {
        testCluster1 = new ProductCluster();
        testCluster2 = new ProductCluster();
        testCluster2.addToCluster(testToner);
    }

    @Test
    public void getClusterTest() {
        testCluster1.addToCluster(testCleanser);
        testCluster1.addToCluster(testExfoliator);

        assertTrue(testCluster1.getCluster().contains(testCleanser));
        assertTrue(testCluster1.getCluster().contains(testExfoliator));
    }
    @Test
    public void isInClusterTest_True() {
        testCluster1.addToCluster(testCleanser);
        assertTrue(testCluster1.isInCluster(testCleanser));
        testCluster1.addToCluster(testExfoliator);
        assertTrue(testCluster1.isInCluster(testExfoliator));
    }

    @Test
    public void isInClusterTest_False() {
        assertFalse(testCluster1.isInCluster(testCleanser));
        assertFalse(testCluster1.isInCluster(testExfoliator));
    }

    @Test
    public void isNameInClusterTest_True() {
        testCluster1.addToCluster(testCleanser);
        assertTrue(testCluster1.isInCluster("Foaming Facial Cleanser"));
        testCluster1.addToCluster(testExfoliator);
        assertTrue(testCluster1.isInCluster("2% BHA Liquid Exfoliant"));
    }

    @Test
    public void isNameInClusterTest_False() {
        assertFalse(testCluster1.isInCluster("Foaming Facial Cleanser"));
        assertFalse(testCluster1.isInCluster("2% BHA Liquid Exfoliant"));
    }

    @Test
    public void removeFromClusterTest() {
        testCluster2.removeFromCluster(testToner);
        assertFalse(testCluster2.isInCluster(testToner));
    }
}
