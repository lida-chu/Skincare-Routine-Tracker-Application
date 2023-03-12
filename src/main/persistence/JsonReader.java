package persistence;

import model.Routine;
import model.SkinProduct;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

// Represents an object that reads from Json file

// References:
//

public class JsonReader {

    private String source;

    // EFFECTS: creates new JsonReader with given source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the skincare routine and returns what is read
    public Routine read() throws IOException {
        String data = readFile(source);
        JSONObject jsonObject = new JSONObject(data);
        return parseRoutine(jsonObject);
    }

    // EFFECTS: reads the source file as a string and returns the string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8);
        stream.forEach(s -> contentBuilder.append(s));

        return contentBuilder.toString();
    }

    // EFFECTS: returns the parsed skincare routine from the JSON object
    private Routine parseRoutine(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        Routine sr = new Routine(name);
        loadProducts(sr, jsonObject);
        return sr;
    }

    // MODIFIES: sr
    // EFFECTS: adds the parsed skincare products from the JSON 'routine' to the routine
    private void loadProducts(Routine sr, JSONObject jsonObject) {
        JSONArray jsonProducts = jsonObject.getJSONArray("Products");
        for (Object j : jsonProducts) {
            JSONObject sp = (JSONObject) j;
            loadProduct(sr, sp);
        }
    }

    // MODIFIES: sr
    // EFFECTS: adds parsed skincare product from the Json object to the skincare routine
    private void loadProduct(Routine sr, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String brand = jsonObject.getString("Brand");
        String category = jsonObject.getString("Category");
        String usage = jsonObject.getString("Usage Frequency");
        int price = jsonObject.getInt("Price");

        SkinProduct sp = new SkinProduct(name, brand);
        sp.setCategory(category);
        sp.setPrice(price);
        sp.setUsage(usage);

        sr.addToRoutine(sp);
    }

}
