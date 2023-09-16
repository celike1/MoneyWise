package persistence;

// JsonWriterTest class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Expense;
import model.ExpensesList;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            ExpensesList el = new ExpensesList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterEmptyExpenseList() {
        try {
            ExpensesList el = new ExpensesList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseList.json");
            el = reader.read();
            assertEquals(0, el.size());
        } catch (IOException e) {
            fail("This exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExpenseList() {
        try {
            ExpensesList el = new ExpensesList();
            el.addExpense(new Expense("bread", 2));
            el.addExpense(new Expense("milk", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseList.json");
            el = reader.read();
            List<Expense> expenses = el.view();
            assertEquals(2, expenses.size());
            checkExpense("bread", 2, expenses.get(0));
            checkExpense("milk", 3, expenses.get(1));

        } catch (IOException e) {
            fail("This exception should not have been thrown.");
        }
    }

}
