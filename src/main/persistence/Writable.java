package persistence;

import org.json.JSONObject;

// Code was partially based on the JsonSerializationDemo project
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    
    public JSONObject toJson();
}
