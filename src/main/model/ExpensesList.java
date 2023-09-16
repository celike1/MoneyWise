package model;


// ExpensesList class references code from this https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab
// Link: https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab.git

// ExpensesList class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//Represents a list of expenses.
public class ExpensesList implements Writable {

    private List<Expense> expenseList;

    //EFFECTS: constructor for expenselist
    public ExpensesList() {
        expenseList = new ArrayList<Expense>();
    }

    //MODIFIES: this
    //EFFECTS: adds expense into an expense list.
    public void addExpense(Expense e) {
        EventLog.getInstance().logEvent(new Event("Added expense: " + e));
        expenseList.add(e);
    }


    //MODIFIES: this
    //EFFECTS: removes the expense from the list.
    public void removeExpense(Expense e) {
        EventLog.getInstance().logEvent(new Event("Removed expense: " + e));
        expenseList.remove(e);

    }

    //EFFECTS: if the list contains expense returns true, else false
    public boolean contains(Expense e) {
        return expenseList.contains(e);
    }

    //EFFECTS: returns the size of the list.
    public int size() {
        return expenseList.size();
    }

    //EFFECTS: sums all the expense amounts in a expense list.
    public int getAmounts() {
        int sum = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            sum += expenseList.get(i).getAmount();
        }
        return sum;

    }


    // EFFECTS: returns an unmodifiable list of expenses in this expenseList
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenseList);
    }


    public List<Expense> view() {
        return expenseList;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expensesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    protected JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : expenseList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }






}


