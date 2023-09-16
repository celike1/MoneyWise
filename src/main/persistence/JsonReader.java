package persistence;

// JsonReader class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Expense;
import model.ExpensesList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads expense list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads expenselist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpensesList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses expenselist from JSON object and returns it
    private ExpensesList parseExpenseList(JSONObject jsonObject) {
        ExpensesList el = new ExpensesList();
        addThingies(el, jsonObject);
        return el;
    }

    // MODIFIES: el
    // EFFECTS: parses expenses from JSON object and adds them to expenselist
    private void addThingies(ExpensesList el, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(el, nextExpense);
        }
    }

    // MODIFIES: el
    // EFFECTS: parses expense from JSON object and adds it to expenselist
    private void addExpense(ExpensesList el, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = Double.valueOf(jsonObject.getDouble("amount"));
        Expense expense = new Expense(name, amount);
        el.addExpense(expense);
    }

}
