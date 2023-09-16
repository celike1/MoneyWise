
package ui;

// This BudgettingApp class references code from this https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// BudgettingApp class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Expense;
import model.ExpensesList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

// Budgeting application
public class BudgetingApp {

    private static final String JSON_STORE = "./data/expenselist.json";
    private Scanner input;
    private ExpensesList expenseList;
    private Expense expense;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: runs the budgeting application
    public BudgetingApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        expenseList = new ExpensesList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {

        boolean keepGoing = true;
        String command = null;

        init();


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you next time! Be wise with your money");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("ae")) {
            doAddExpense();
        } else if (command.equals("re")) {
            doRemoveExpense();
        } else if (command.equals("t")) {
            doTotal();
        } else if (command.equals("v")) {
            doView();
        } else if (command.equals("s")) {
            saveExpenseList();
        } else if (command.equals("l")) {
            loadExpenseList();
        } else {
            System.out.println("Invalid selection");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes a new expenses list
    private void init() {
        expenseList = new ExpensesList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays the options to user
    private void displayMenu() {
        System.out.println("\nWhich one would you like to choose ?:");
        System.out.println("\tae -> add expense");
        System.out.println("\tre -> remove expense");
        System.out.println("\tt -> show expense total");
        System.out.println("\tv -> view all of the expenses");
        System.out.println("\ts -> save expenseList to file");
        System.out.println("\tl -> load expenseList from file");
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: conducts a addition of expense.
    private void doAddExpense() {

        System.out.print("Enter the item: ");
        String item = input.next();
        item = item.toLowerCase();


        System.out.print("Enter the the amount: $");
        double amount = input.nextDouble();

        expenseList.addExpense(new Expense(item, amount));


    }

    // MODIFIES: this
    // EFFECTS: conducts a removal of expense.
    private void doRemoveExpense() {
        System.out.print("Enter the item: ");
        String item = input.next();
        item = item.toLowerCase();

        System.out.print("Enter the the amount: $");
        double amount = input.nextDouble();

        Expense exp = new Expense(item, amount);




        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.view().get(i).equals(exp)) {
                expenseList.view().remove(i);
                break;
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: show the total amount of expenses.
    private void doTotal() {
        System.out.print("Total is : " + expenseList.getAmounts());
    }



    //EFFECTS: shows every expense added to the expenselist.
    private void doView() {
        System.out.println("The items you added are:");

        for (Expense e : expenseList.view()) {
            System.out.println(e);
        }


    }



    // EFFECTS: saves the expenselist to file
    public void saveExpenseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseList);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads expenselist from file
    public void loadExpenseList() {
        try {
            expenseList = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }






}



