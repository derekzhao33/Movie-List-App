package persistence;

import org.json.JSONObject;

// Interface that represents a class that can be written to JSON
public interface Writable {
    // EFFECTS: returns this as JSON object
    public JSONObject toJson();
}
