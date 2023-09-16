package persistence;

// JsonTest class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    //EFFECTS: expense checking method for Json Tests
    protected void checkExpense(String name, double amount, Expense expense) {
        assertEquals(name, expense.getName());
        assertEquals(amount, expense.getAmount());

    }
}
