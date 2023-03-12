package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoutineTest {

    SkinProduct testCleanser = new SkinProduct("Foaming Facial Cleanser", "Cerave");
    SkinProduct testExfoliator = new SkinProduct("2% BHA Liquid Exfoliant", "Paula's Choice");
    SkinProduct testToner = new SkinProduct("Advanced Snail 96 Mucin Power Essence", "Corsyx");
    SkinProduct testSerum = new SkinProduct("Tea Tree Relief Serum", "iUNIK");
    SkinProduct testMoisturizer = new SkinProduct("Ultra Facial Cream", "Khiel's");
    SkinProduct testEyeCream = new SkinProduct("Creamy Eye Treatment with Avocado", "Khiel's");
    SkinProduct testSpot = new SkinProduct("Acne Gel", "Benzagel");
    SkinProduct testSunscreen = new SkinProduct("UV Aqua Rich Watery Essence", "Biore");
    SkinProduct testFaceMask = new SkinProduct("Rare Earth Deep Pore Cleansing Mask", "Khiel's");

    Routine testRoutine1;
    Routine testRoutine2;
    Routine testRoutine3;

    @BeforeEach
    public void setUp() {
        testRoutine1 = new Routine();
        testRoutine2 = new Routine();
        setCategories();
    }

    @Test
    public void constructWithNameTest() {
        testRoutine3 = new Routine("X's Skincare Routine");
        assertEquals("X's Skincare Routine", testRoutine3.getName());
    }
    @Test
    public void getRoutineTest() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine2.addToRoutine(testSerum);

        assertTrue(testRoutine1.getRoutine().contains(testCleanser));
        assertTrue(testRoutine2.getRoutine().contains(testSerum));
    }

    @Test
    public void isInRoutineTest_True() {
        testRoutine1.addToRoutine(testCleanser);
        assertTrue(testRoutine1.isInRoutine(testCleanser));
        testRoutine2.addToRoutine(testSerum);
        assertTrue(testRoutine2.isInRoutine(testSerum));
    }

    @Test
    public void isInRoutineTest_False() {
        assertFalse(testRoutine1.isInRoutine(testExfoliator));
        assertFalse(testRoutine2.isInRoutine(testToner));
    }

    @Test
    public void isNameInRoutineTest_True() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.addToRoutine(testSerum);

        assertTrue(testRoutine1.isInRoutine("Foaming Facial Cleanser"));
        assertTrue(testRoutine1.isInRoutine("Tea Tree Relief Serum"));
    }

    @Test
    public void isNameInRoutineTest_False() {
        assertFalse(testRoutine1.isInRoutine("2% BHA Liquid Exfoliant"));
        assertFalse(testRoutine2.isInRoutine("Advanced Snail 96 Mucin Power Essence"));
    }

    @Test
    public void removeFromRoutineTest() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.removeFromRoutine(testCleanser);
        assertFalse(testRoutine1.isInRoutine(testCleanser));
        testRoutine2.addToRoutine(testSerum);
        testRoutine2.removeFromRoutine(testSerum);
        assertFalse(testRoutine2.isInRoutine(testSerum));
    }

    @Test
    public void totalExpensesTest() {
        testCleanser.setPrice(5000);
        testToner.setPrice(4000);
        testExfoliator.setPrice(2500);
        testSerum.setPrice(1000);

        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.addToRoutine(testToner);
        testRoutine1.addToRoutine(testExfoliator);
        testRoutine1.addToRoutine(testSerum);

        assertEquals(5000 + 4000 + 2500 + 1000, testRoutine1.totalExpenses());

        testRoutine2.addToRoutine(testCleanser);
        testRoutine2.addToRoutine(testExfoliator);

        assertEquals(5000 + 2500, testRoutine2.totalExpenses());
    }

    @Test
    public void checkBudgetTest_True() {
        testEyeCream.setPrice(2000);
        testMoisturizer.setPrice(1000);
        testFaceMask.setPrice(3000);

        testRoutine1.addToRoutine(testEyeCream);
        testRoutine1.addToRoutine(testMoisturizer);
        testRoutine1.addToRoutine(testFaceMask);

        testRoutine1.checkBudget(2000 + 1000 + 3000 - 1);
        assertTrue(testRoutine1.getOutOfBudget());
    }

    @Test
    public void checkBudgetTest_False() {
        testEyeCream.setPrice(1000);
        testMoisturizer.setPrice(3000);
        testFaceMask.setPrice(2000);

        testRoutine2.addToRoutine(testEyeCream);
        testRoutine2.addToRoutine(testMoisturizer);
        testRoutine2.addToRoutine(testFaceMask);

        testRoutine2.checkBudget(1000 + 3000 + 2000);
        assertFalse(testRoutine2.getOutOfBudget());
    }

    @Test
    public void isBlankTest_True() {
        assertTrue(testRoutine1.isBlank());
    }

    @Test
    public void isBlankTest_False() {
        testRoutine1.addToRoutine(testCleanser);
        assertFalse(testRoutine1.isBlank());
    }

    @Test
    public void validRoutineTest_True_OneSerumOneFaceMask() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.addToRoutine(testExfoliator);
        testRoutine1.addToRoutine(testToner);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testMoisturizer);
        testRoutine1.addToRoutine(testEyeCream);
        testRoutine1.addToRoutine(testSpot);
        testRoutine1.addToRoutine(testSunscreen);
        testRoutine1.addToRoutine(testFaceMask);

        testRoutine1.setValidRoutine();
        assertTrue(testRoutine1.getValidRoutine());
    }

    @Test
    public void validRoutineTest_True_OneSerum() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.addToRoutine(testExfoliator);
        testRoutine1.addToRoutine(testToner);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testMoisturizer);
        testRoutine1.addToRoutine(testEyeCream);
        testRoutine1.addToRoutine(testSpot);
        testRoutine1.addToRoutine(testSunscreen);
        testRoutine1.addToRoutine(testFaceMask);
        testRoutine1.addToRoutine(testFaceMask);

        testRoutine1.setValidRoutine();
        assertTrue(testRoutine1.getValidRoutine());
    }

    @Test
    public void validRoutineTest_True_OneFaceMask() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.addToRoutine(testExfoliator);
        testRoutine1.addToRoutine(testToner);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testMoisturizer);
        testRoutine1.addToRoutine(testEyeCream);
        testRoutine1.addToRoutine(testSpot);
        testRoutine1.addToRoutine(testSunscreen);
        testRoutine1.addToRoutine(testFaceMask);

        testRoutine1.setValidRoutine();
        assertTrue(testRoutine1.getValidRoutine());
    }

    @Test
    public void validRoutineTest_False_LessThanOne() {
        testRoutine2.addToRoutine(testExfoliator);
        testRoutine2.addToRoutine(testToner);
        testRoutine2.addToRoutine(testSerum);
        testRoutine2.addToRoutine(testSerum);
        testRoutine2.addToRoutine(testMoisturizer);
        testRoutine2.addToRoutine(testEyeCream);
        testRoutine2.addToRoutine(testSpot);
        testRoutine2.addToRoutine(testSunscreen);
        testRoutine2.addToRoutine(testFaceMask);
        testRoutine2.addToRoutine(testFaceMask);

        testRoutine2.setValidRoutine();
        assertFalse(testRoutine2.getValidRoutine());
    }

    @Test
    public void validRoutineTest_False_NoSerum() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine2.addToRoutine(testExfoliator);
        testRoutine2.addToRoutine(testToner);
        testRoutine2.addToRoutine(testMoisturizer);
        testRoutine2.addToRoutine(testEyeCream);
        testRoutine2.addToRoutine(testSpot);
        testRoutine2.addToRoutine(testSunscreen);
        testRoutine2.addToRoutine(testFaceMask);
        testRoutine2.addToRoutine(testFaceMask);

        testRoutine2.setValidRoutine();
        assertFalse(testRoutine2.getValidRoutine());
    }

    @Test
    public void validRoutineTest_False_NoFaceMask() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine2.addToRoutine(testExfoliator);
        testRoutine2.addToRoutine(testToner);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testSerum);
        testRoutine2.addToRoutine(testMoisturizer);
        testRoutine2.addToRoutine(testEyeCream);
        testRoutine2.addToRoutine(testSpot);
        testRoutine2.addToRoutine(testSunscreen);

        testRoutine2.setValidRoutine();
        assertFalse(testRoutine2.getValidRoutine());
    }

    @Test
    public void validRoutineTest_False_NoFaceMaskOrSerum() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine2.addToRoutine(testExfoliator);
        testRoutine2.addToRoutine(testToner);
        testRoutine2.addToRoutine(testMoisturizer);
        testRoutine2.addToRoutine(testEyeCream);
        testRoutine2.addToRoutine(testSpot);
        testRoutine2.addToRoutine(testSunscreen);

        testRoutine2.setValidRoutine();
        assertFalse(testRoutine2.getValidRoutine());
    }

    @Test
    public void validRoutineTest_False_TooMany() {
        testRoutine1.addToRoutine(testCleanser);
        testRoutine1.addToRoutine(testExfoliator);
        testRoutine1.addToRoutine(testToner);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testSerum);
        testRoutine1.addToRoutine(testMoisturizer);
        testRoutine1.addToRoutine(testEyeCream);
        testRoutine1.addToRoutine(testSpot);
        testRoutine1.addToRoutine(testSunscreen);
        testRoutine1.addToRoutine(testSunscreen);
        testRoutine1.addToRoutine(testFaceMask);
        testRoutine1.addToRoutine(testFaceMask);

        testRoutine1.setValidRoutine();
        assertFalse(testRoutine1.getValidRoutine());
    }

    private void setCategories() {
        testCleanser.setCategory(0);
        testExfoliator.setCategory(1);
        testToner.setCategory(2);
        testSerum.setCategory(3);
        testMoisturizer.setCategory(4);
        testEyeCream.setCategory(5);
        testSpot.setCategory(6);
        testSunscreen.setCategory(7);
        testFaceMask.setCategory(8);
    }


}
