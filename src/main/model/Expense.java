package model;

// Expense class references code from this https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab
// Link: https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab.git


// Expense class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

//Represents an expense having an title and an amount
public class Expense implements Writable {
    @Override
    public String toString() {
        return  name + " " + "$" + amount;
    }

    private String name;
    private double amount;


    //REQUIRES: Item name has a non-zero length.
    //EFFECTS: constructs expense
    public Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;

    }


    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }



    //EFFECTS: checks if two objects are equal.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 && Objects.equals(name, expense.name);
    }

    //EFFECTS: produces a json object with given name and amount
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount", amount);
        return json;
    }




}