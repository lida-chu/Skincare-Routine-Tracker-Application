package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SkinProductTest {

    SkinProduct testCleanser;
    SkinProduct testExfoliator;
    SkinProduct testToner;
    SkinProduct testSerum;
    SkinProduct testFaceMask;

    @BeforeEach
    public void setUp() {
        testCleanser = new SkinProduct();
        testExfoliator = new SkinProduct();
        testToner = new SkinProduct("Advanced Snail 96 Mucin Power Essence", "Corsyx");
        testSerum = new SkinProduct("Tea Tree Relief Serum", "iUNIK");
        testFaceMask = new SkinProduct("Rare Earth Deep Pore Cleansing Mask", "Khiel's");
    }

    @Test
    public void setNameTest() {
        testCleanser.setName("Foaming Facial Cleanser");
        testExfoliator.setName("2% BHA Liquid Exfoliant");
        assertEquals("Foaming Facial Cleanser", testCleanser.getName());
        assertEquals("2% BHA Liquid Exfoliant", testExfoliator.getName());
    }

    @Test
    public void setBrandTest() {
        testCleanser.setBrand("Cerave");
        testExfoliator.setBrand("Paula's Choice");
        assertEquals("Cerave", testCleanser.getBrand());
        assertEquals("Paula's Choice", testExfoliator.getBrand());
    }

    @Test
    public void setCategoryTest() {
        testCleanser.setCategory(0);
        assertEquals("Cleanser", testCleanser.getCategory());
        testExfoliator.setCategory(1);
        assertEquals("Exfoliator", testExfoliator.getCategory());
        testSerum.setCategory(3);
        assertEquals("Serum", testSerum.getCategory());
        testFaceMask.setCategory(8);
        assertEquals("Face Mask", testFaceMask.getCategory());
    }

    @Test
    public void setPriceTest() {
        testToner.setPrice(2000);
        assertEquals(2000, testToner.getPrice());
        testSerum.setPrice(5500);
        assertEquals(5500, testSerum.getPrice());
    }

    @Test
    public void setUsageTest() {
        testCleanser.setUsage(0);
        assertEquals("Daily", testCleanser.getUsage());
        testFaceMask.setUsage(1);
        assertEquals("Weekly", testFaceMask.getUsage());
    }

    @Test
    public void productToJsonTest() {
        testToner.setCategory("Toner");
        testToner.setUsage("Daily");
        testToner.setPrice(1000);
        JSONObject jsonTestToner = testToner.toJson();

        assertEquals("Advanced Snail 96 Mucin Power Essence", jsonTestToner.getString("Name"));
        assertEquals("Corsyx", jsonTestToner.getString("Brand"));
        assertEquals("Toner", jsonTestToner.getString("Category"));
        assertEquals("Daily", jsonTestToner.getString("Usage Frequency"));
        assertEquals(1000, jsonTestToner.getInt("Price"));
    }
}