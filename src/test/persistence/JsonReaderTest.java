package persistence;

// JsonReaderTest class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


import model.Expense;
import model.ExpensesList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExpensesList el = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenseList.json");
        try {
            ExpensesList el = reader.read();
            assertEquals(0, el.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseList.json");
        try {
            ExpensesList el = reader.read();
            List<Expense> expenseList = el.getExpenses();
            assertEquals(2, expenseList.size());
            checkExpense("milk", 3, expenseList.get(0));
            checkExpense("bread",2, expenseList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }







}
