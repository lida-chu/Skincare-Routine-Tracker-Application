package persistence;

import model.Routine;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents an object that writes to JSON file

// Reference for code displayed in README
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter fileWriter;
    private String filePath;

    public JsonWriter(String path) {
        filePath = path;
    }

    // MODIFIES: this
    // EFFECTS: opens the json writer; if the file at the given path is not found, throws exception
    public void openWriter() throws FileNotFoundException {
        fileWriter = new PrintWriter(new File(filePath));
    }

    // MODIFIES: this
    // EFFECTS: creates a file for the JSON version of the skin routine
    public void write(Routine routine) {
        JSONObject jsonRoutine = routine.toJson();
        saveToFile(jsonRoutine.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes the given routine string to the file
    public void saveToFile(String jsonRoutine) {
        fileWriter.print(jsonRoutine);
    }

    // MODIFIES: this
    // EFFECTS: closes the json writer
    public void closeWriter() {
        fileWriter.close();
    }

}
