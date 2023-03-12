package persistence;

import model.Routine;
import model.SkinProduct;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void FileDoesNotExistTest() {
        JsonReader testReader = new JsonReader("./data/DNE.json");

        try {
            Routine sr = testReader.read();
            fail("Expected exception was not thrown.");
        } catch (IOException e) {
            // success
        }
    }

    @Test
    public void FileExistsIsEmptyTest() {
        JsonReader testReader = new JsonReader("./data/testReaderEmptyRoutine.json");

        try {
            Routine sr = testReader.read();
            assertEquals("Test Routine", sr.getName());
            assertTrue(sr.getRoutine().isEmpty());
        } catch (IOException e) {
            fail("Unexpected exception. File could not be read.");
        }
    }

    @Test
    public void FileExistsIsNotEmpty() {
        JsonReader testReader = new JsonReader("./data/testReaderNotEmptyRoutine.json");

        try {
            Routine sr = testReader.read();
            List<SkinProduct> sps = sr.getRoutine();

            assertEquals("Test Routine", sr.getName());
            assertEquals(3, sps.size());

            assertTrue(verifyProduct("Name A", "Cleanser", "Brand A", "Daily", 1000, sps.get(0)));
            assertTrue(verifyProduct("Name B", "Exfoliator", "Brand B", "Weekly", 20000, sps.get(1)));
            assertTrue(verifyProduct("Name C", "Face Mask", "Brand C", "Weekly", 65000, sps.get(2)));
        } catch (IOException e) {
            fail("Unexpected exception. File could not be read.");
        }
    }

    private boolean verifyProduct(String n, String c, String b, String u, int p, SkinProduct sp) {
        boolean sameName = sp.getName().equals(n);
        boolean sameCategory = sp.getCategory().equals(c);
        boolean sameUsage = sp.getUsage().equals(u);
        boolean sameBrand = sp.getBrand().equals(b);
        boolean samePrice = (sp.getPrice() == p);

        return sameName && sameCategory && sameUsage && sameBrand && samePrice;
    }
}
