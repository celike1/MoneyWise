package model;


// ExpenseTest class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.security.SecureRandom;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Expense
public class ExpenseTest {

    Expense expense1 = new Expense("apples", 5);
    Expense expense2 = new Expense("apples", 5);
    Expense expense3 = new Expense("water", 2);
    Expense expense4 = new Expense("water", 3);
    Expense expense5 = new Expense("carrot", 5);

    @Test
    public void testExpense(){
        assertEquals(5, expense1.getAmount());
        assertEquals("apples", expense1.getName());

    }

    @Test
    public void testGetAmount(){

    assertEquals(5,expense1.getAmount());

    }

    @Test
    public void testGetName(){

        assertEquals("apples", expense1.getName());
    }

    @Test
    public void testEqualsIfTrue(){

        assertTrue(expense1.equals(expense2));
    }

    @Test
    public void testEqualsIfFalse(){

        assertFalse(expense3.equals(expense2));
    }



    @Test
    public void testEqualsObject(){

        String s = new String();
        assertFalse(equals(s));



    }

    @Test
    public void testEqualsNullObject(){

        String s = null;
        assertFalse(equals(s));



    }






    @Test
    public void testEqualsIfNull(){
        Expense expense4;
        expense4 = null;
        assertFalse(expense2.equals(expense4));
    }

    @Test
    public void testEqualsFalseCase(){

        assertFalse(expense3.getClass() != expense1.getClass());
    }

    @Test
    public void testEqualsLastCase(){

        assertFalse(expense1.equals(expense3));
        assertFalse(expense1.equals(expense5));
        assertFalse(expense3.equals(expense4));
        assertTrue(expense1.equals(expense2));
    }



    @Test
    public void testToString(){

        assertEquals("apples $5.0", expense1.toString());
    }

    @Test
    public void testToJson(){

        assertEquals("{\"amount\":5,\"name\":\"apples\"}", expense1.toJson().toString());

    }



}
