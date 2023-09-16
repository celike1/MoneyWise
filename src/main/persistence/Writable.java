package persistence;

// Writable class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
