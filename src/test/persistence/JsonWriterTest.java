package persistence;

import model.Routine;
import model.SkinProduct;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void WritingToIllegalFileTest() {
        try {
            JsonWriter testWriter = new JsonWriter("./data/\0DNE.json");
            testWriter.openWriter();
            fail("Expected exception was not thrown.");
        } catch (FileNotFoundException e) {
            // success
        }
    }

    @Test
    public void WritingToEmptyFileTest() {
        Routine sr = new Routine("Test Routine");
        try {
            //Routine sr = new Routine("Test Routine");
            JsonWriter testWriter = new JsonWriter("./data/testWriterEmptyRoutine.json");
            testWriter.openWriter();
            testWriter.write(sr);
            testWriter.closeWriter();

            JsonReader testReader = new JsonReader("./data/testWriterEmptyRoutine.json");
            Routine readSr = testReader.read();
            assertEquals("Current Routine", readSr.getName());
            assertTrue(readSr.getRoutine().isEmpty());

        } catch (FileNotFoundException e) {
            fail("Unexpected exception thrown. File could not be written to.");
        } catch (IOException e) {
            fail("Unexpected exception thrown. File could not be read from.");
        }
    }

    @Test
    public void WritingANonEmptyFileTest() {
        Routine sr = new Routine("Test Routine");
        try {
            //Routine sr = new Routine("Test Routine");
            setUpRoutine(sr);

            JsonWriter testWriter = new JsonWriter("./data/testWriterNotEmptyRoutine.json");
            testWriter.openWriter();
            testWriter.write(sr);
            testWriter.closeWriter();

            JsonReader testReader = new JsonReader("./data/testWriterNotEmptyRoutine.json");
            Routine readSr = testReader.read();
            assertEquals("Current Routine", readSr.getName());

            List<SkinProduct> sps = readSr.getRoutine();
            assertEquals(3, sps.size());

            assertTrue(verifyProduct("Name A", "Cleanser", "Brand A", "Weekly", 1000, sps.get(0)));
            assertTrue(verifyProduct("Name B", "Serum", "Brand B", "Daily", 20000, sps.get(1)));
            assertTrue(verifyProduct("Name C", "Sunscreen", "Brand C", "Daily", 15020, sps.get(2)));

        } catch (FileNotFoundException e) {
            fail("Unexpected exception thrown. File could not be written to.");
        } catch (IOException e) {
            fail("Unexpected exception thrown. File could not be read from.");
        }
    }

    private void setUpRoutine(Routine sr) {
        SkinProduct testCleanser = new SkinProduct("Name A", "Brand A");
        SkinProduct testSerum = new SkinProduct("Name B", "Brand B");
        SkinProduct testSunscreen = new SkinProduct("Name C", "Brand C");

        testCleanser.setCategory("Cleanser");
        testCleanser.setUsage("Weekly");
        testCleanser.setPrice(1000);

        testSerum.setCategory("Serum");
        testSerum.setUsage("Daily");
        testSerum.setPrice(20000);

        testSunscreen.setCategory("Sunscreen");
        testSunscreen.setUsage("Daily");
        testSunscreen.setPrice(15020);

        sr.addToRoutine(testCleanser);
        sr.addToRoutine(testSerum);
        sr.addToRoutine(testSunscreen);
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
