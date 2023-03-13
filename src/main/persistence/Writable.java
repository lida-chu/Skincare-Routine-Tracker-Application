package persistence;

import org.json.JSONObject;

// Represents an object that can be written by the JSON Writer
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
