package model;

// ExpensesListTest class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for ExpensesList
public class ExpensesListTest {

    private ExpensesList expList;
    private Expense exp1;
    private Expense exp2;


    @BeforeEach
    public void runBefore() {
        expList = new ExpensesList();
    }

    @Test
    public void testExpensesList(){
        assertEquals(0, expList.size());
    }


    @Test
    public void testAddExpense() {
        expList.addExpense(exp2);

        assertTrue(expList.contains(exp2));
        assertEquals(1, expList.size());
    }

    @Test
    public void testDuplicate() {
        expList.addExpense(exp1);
        expList.addExpense(exp1);

        assertTrue(expList.contains(exp1));
        assertEquals(2, expList.size());
    }


    @Test
    public void testRemove() {
        expList.addExpense(exp1);
        expList.removeExpense(exp1);
        assertFalse(expList.contains(exp1));
        assertEquals(0, expList.size());
    }





    @Test
    public void testContains(){

        expList.addExpense(exp1);
        assertTrue(expList.contains(exp1));

    }


    @Test
    public void testSize(){

        expList.addExpense(exp1);
        expList.addExpense(exp2);

        assertEquals(2, expList.size());

    }



    @Test
    public void testGetAmounts() {
        exp1 = new Expense("milk",2);
        exp2 = new Expense("salt",4);
        expList.addExpense(exp1);
        expList.addExpense(exp2);
        assertEquals(6, expList.getAmounts());

    }

    @Test
    public void testGetExpense(){

        exp1 = new Expense("milk",2);
        exp2 = new Expense("salt",4);
        expList.addExpense(exp1);
        expList.addExpense(exp2);
        List<Expense> unmodlist = new ArrayList<Expense>();
        unmodlist.add(exp1);
        unmodlist.add(exp2);

        assertEquals(unmodlist , expList.getExpenses());

    }

    @Test
    public void testView(){
        exp1 = new Expense("milk", 2);
        exp2 = new Expense("salt", 4);
        expList.addExpense(exp1);
        expList.addExpense(exp2);

        List<Expense> viewlist = new ArrayList<Expense>();
        viewlist.add(exp1);
        viewlist.add(exp2);


        assertEquals(viewlist, expList.view());

    }


    @Test
    public void testToJson(){
        JSONObject json = new JSONObject();

        assertTrue(json.isEmpty());

    }

    @Test
    public void testExpensesToJson(){
        exp1 = new Expense("milk",2);
        exp2 = new Expense("salt", 4);
        expList.addExpense(exp1);


        assertEquals("[{\"amount\":2,\"name\":\"milk\"}]" , expList.expensesToJson().toString());

    }





}