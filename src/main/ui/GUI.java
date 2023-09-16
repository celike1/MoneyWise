package ui;


// This GUI class references code from ListDemo example:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

// This GUI class references code from the video:
// https://www.youtube.com/watch?v=Kmgo00avvEw&list=LL&index=3&t=6585s


// This GUI class references code from the video:
//https://www.youtube.com/watch?v=19xwjgsC_58&list=LL&index=3&t=614s


// This GUI class references code from the video:
//https://www.youtube.com/watch?v=1OyYyVlziRM&list=LL&index=2





import model.Expense;
import model.ExpensesList;


import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import javax.swing.border.TitledBorder;
import javax.swing.event.*;


//GUI for budgeting app.
public class GUI extends JPanel
        implements ListSelectionListener {



    private JList<Expense> listExpense;
    private DefaultListModel<Expense> listModel;



    private String name;
    private String amount;
    double sum = 0;




    private static final String addExpenseString = "Add Expense";
    private static final String removeExpenseString = "Remove Expense";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JButton removeExpenseButton;
    private JButton addExpenseButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField expenseName;
    private JTextField expenseAmount;
    private JLabel totalLabel;
    private JLabel nameForExpense;
    private JLabel amountForExpense;
    private JLabel budgetLabel;
    private JTextField budgetField;
    private JLabel goalLabel;
    private JTextField goalField;
    private JLabel infoLabel;
    private JTextField infoField;

    private JPanel buttonPaneUp;


    private static final String JSON_STORE = "./data/expenselist.json";
    private Scanner input;
    private ExpensesList expenseList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel logoPanel;
    private JPanel mainPanel;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel buttonPaneDown;
    private JPanel rightFieldPane;



    //EFFECTS: constructs a GUI
    public GUI() {

        super(new GridBagLayout());

        gridBagLayoutSetUp();

        panelRightLeft();


        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        listModel = new DefaultListModel<>();
        expenseList = new ExpensesList();


        //Create the list and put it in a scroll pane.
        listExpense = new JList<>(listModel);
        listExpense.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listExpense.setSelectedIndex(0);
        listExpense.addListSelectionListener(this);
        listExpense.setVisibleRowCount(5);



        addAndRemoveExpenseButtonMethod();

        saveAndLoad();

        labelsAndFields();

        buttonPaneUpMethod();


        buttonPaneDownMethod();


        rightFieldPaneMethod();


        placingPanels();

    }

    //MODIFIES:this
    //EFFECTS: function for left and right panels.
    public void panelRightLeft() {

        panelLeft = new JPanel();
        panelLeft.setBorder(new TitledBorder("Expense Information"));
        panelLeft.setLayout(new BorderLayout());

        panelRight = new JPanel();
        panelRight.setBorder(new TitledBorder("Analysis"));
        panelRight.setLayout(new BorderLayout());


    }


    //MODIFIES:this
    //EFFECTS: function for main gridBagLayoutSetUp
    public void gridBagLayoutSetUp() {

        ImageIcon mainLogo = new ImageIcon("./data/mainlogo.png");
        mainLogo.setImage(mainLogo.getImage().getScaledInstance(300,100,Image.SCALE_SMOOTH));
        JLabel imageForLogo = new JLabel(mainLogo);


        GridBagConstraints gbc = new GridBagConstraints();

        logoPanel = new JPanel(new FlowLayout(0,0,0));
        logoPanel.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;

        logoPanel.add(imageForLogo);
        add(logoPanel,gbc);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(mainPanel,gbc);




    }

    //MODIFIES:this
    //EFFECTS: function for main add-remove buttons
    public void addAndRemoveExpenseButtonMethod() {

        addExpenseButton = new JButton(addExpenseString);
        addExpenseButton.setFont((new Font("Calibri",Font.BOLD,20)));
        addExpenseButton.setBackground(new Color(172, 172, 172));
        AddListener addListener = new AddListener(addExpenseButton);
        addExpenseButton.setActionCommand(addExpenseString);
        addExpenseButton.addActionListener(addListener);
        addExpenseButton.setEnabled(false);

        expenseName = new JTextField(10);
        expenseName.setFont((new Font("Calibri",Font.PLAIN,20)));
        expenseName.addActionListener(addListener);
        expenseName.getDocument().addDocumentListener(addListener);


        expenseAmount = new JTextField(10);
        expenseAmount.setFont((new Font("Calibri",Font.PLAIN,20)));
        expenseAmount.addActionListener(addListener);
        expenseAmount.getDocument().addDocumentListener(addListener);

        removeExpenseButton = new JButton(removeExpenseString);
        removeExpenseButton.setFont((new Font("Calibri",Font.BOLD,20)));
        removeExpenseButton.setBackground(new Color(172, 172, 172));
        removeExpenseButton.setActionCommand(removeExpenseString);
        removeExpenseButton.addActionListener(new RemoveListener());




    }


    //MODIFIES:this
    //EFFECTS: function for save-load buttons
    public void saveAndLoad() {

        saveButton = new JButton(saveString);
        saveButton = new JButton(saveString);
        saveButton.setFont((new Font("Calibri",Font.BOLD,20)));
        saveButton.setBackground(new Color(172, 172, 172));
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());


        loadButton = new JButton(loadString);
        loadButton = new JButton(loadString);
        loadButton.setFont((new Font("Calibri",Font.BOLD,20)));
        loadButton.setBackground(new Color(172, 172, 172));
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());







    }

    //MODIFIES:this
    //EFFECTS: function for labels and fields used in GUI
    public void labelsAndFields() {


        totalLabel = new JLabel("TOTAL MONEY SPENT : 0 $");
        totalLabel.setFont(new Font("Calibri",Font.BOLD,30));
        nameForExpense = new JLabel("Enter name:");
        nameForExpense.setFont(new Font("Calibri",Font.BOLD,20));
        amountForExpense = new JLabel("Enter amount:");
        amountForExpense.setFont(new Font("Calibri",Font.BOLD,20));

        budgetLabel = new JLabel("Enter your budget:");
        goalLabel = new JLabel("Enter your goal:");
        infoLabel = new JLabel("The money left for you to spend:");


        budgetField = new JTextField(10);
        goalField = new JTextField(10);
        infoField = new JTextField(10);








    }


    //MODIFIES:this
    //EFFECTS: function for buttonPaneUp
    public void buttonPaneUpMethod() {

        buttonPaneUp = new JPanel();
        buttonPaneUp.setLayout(new GridLayout(3,2));


        buttonPaneUp.add(nameForExpense);
        buttonPaneUp.add(expenseName);
        buttonPaneUp.add(amountForExpense);
        buttonPaneUp.add(expenseAmount);
        buttonPaneUp.add(addExpenseButton);
        buttonPaneUp.add(removeExpenseButton);

    }

    //MODIFIES:this
    //EFFECTS: function for buttonPaneUp
    public void buttonPaneDownMethod() {

        buttonPaneDown = new JPanel();
        buttonPaneDown.setLayout(new GridLayout(1,2));
        buttonPaneDown.add(saveButton);
        buttonPaneDown.add(loadButton);
        buttonPaneDown.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


    }


    //MODIFIES:this
    //EFFECTS: function for rightFieldPane
    public void rightFieldPaneMethod() {


        rightFieldPane = new JPanel();
        rightFieldPane.setLayout(new GridLayout(2,2));

        rightFieldPane.add(budgetLabel);
        rightFieldPane.add(budgetField);
        rightFieldPane.add(goalLabel);
        rightFieldPane.add(goalField);



    }


    //MODIFIES:this
    //EFFECTS: function for placing panels.
    public void placingPanels() {

        JScrollPane listScrollPane = new JScrollPane(listExpense);
        panelLeft.add(listScrollPane);


        panelLeft.add(listScrollPane, BorderLayout.CENTER);
        panelLeft.add(buttonPaneUp, BorderLayout.PAGE_START);
        panelLeft.add(buttonPaneDown, BorderLayout.PAGE_END);


        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        totalLabel.setVerticalAlignment(JLabel.CENTER);

        panelRight.add(totalLabel,BorderLayout.CENTER);


        mainPanel.add(panelLeft);
        mainPanel.add(panelRight);



    }




    class SaveListener implements ActionListener {

        //MODIFIES:this
        //EFFECTS: after save is clicked stores data in the list.
        public void actionPerformed(ActionEvent e) {

            try {
                jsonWriter.open();
                jsonWriter.write(expenseList);
                jsonWriter.close();
                System.out.println("Saved " + " to " + JSON_STORE);
            } catch (FileNotFoundException a) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }



        }
    }


    class LoadListener implements ActionListener {

        //MODIFIES:this
        //EFFECTS: after load is clicked load data back to the list.
        public void actionPerformed(ActionEvent e) {

            try {
                expenseList = jsonReader.read();
                System.out.println("Loaded " + " from " + JSON_STORE);
                for (Expense expense : expenseList.view()) {
                    listModel.addElement(expense);
                }
                totalLabel.setText("TOTAL MONEY SPENT : " + calculateTotal() + "$");

            } catch (IOException a) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }


        }

    }



    class RemoveListener implements ActionListener {


        //MODIFIES:this
        //EFFECTS: after remove is clicked remose the chosen item from list.
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.



            int index = listExpense.getSelectedIndex();
            listModel.remove(index);
            expenseList.removeExpense(expenseList.view().get(index));

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable removing.
                removeExpenseButton.setEnabled(false);
                totalLabel.setText("TOTAL MONEY SPENT : " + calculateTotal() + "$");

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;

                }

                listExpense.setSelectedIndex(index);
                listExpense.ensureIndexIsVisible(index);

                totalLabel.setText("TOTAL MONEY SPENT : " + calculateTotal() + "$");
            }



        }
    }



    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;


        //EFFECTS: constructs lister for add.
        public AddListener(JButton button) {
            this.button = button;
        }

        //MODIFIES:this
        //EFFECTS: after add is clicked the item is added to the list.
        public void actionPerformed(ActionEvent e) {


            name = expenseName.getText();
            amount = expenseAmount.getText();


            int index = listExpense.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }


            Expense newExpense = new Expense(name, Double.parseDouble(amount));

            //when something is added to the listModel it is also added to list of expenses.
            listModel.insertElementAt(newExpense, index);
            expenseList.addExpense(newExpense);


            //Reset the text field.
            expenseName.requestFocusInWindow();
            expenseName.setText("");

            //Reset the text field.
            expenseAmount.requestFocusInWindow();
            expenseAmount.setText("");



            //Select the new item and make it visible.
            listExpense.setSelectedIndex(index);
            listExpense.ensureIndexIsVisible(index);

            listExpense.setFont((new Font("Calibri",Font.PLAIN,20)));

            //Select the new item and make it visible.
            listExpense.setSelectedIndex(index);
            listExpense.ensureIndexIsVisible(index);



            totalLabel.setText("TOTAL MONEY SPENT : " + calculateTotal() + "$");

        }

        //EFFECTS: helper method for DocumentListener
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //EFFECTS: helper method for DocumentListener
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //EFFECTS: helper method for DocumentListener
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }


        //EFFECTS: helper method for DocumentListener
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        //EFFECTS: handles what happens when text field is empty.
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //EFFECTS: helper method for ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (listExpense.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeExpenseButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeExpenseButton.setEnabled(true);
            }
        }
    }



    //EFFECTS: calculates the total amount spent.
    public double calculateTotal() {
        int sum = 0;
        for (int i = 0; i < listModel.size(); i++) {
            sum += listModel.get(i).getAmount();
        }
        return sum;
    }


    //EFFECTS: creates and shows GUI
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MoneyWise");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of app


        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(false); //content panes must be opaque

        frame.setContentPane(newContentPane);


        //Display the window.
        frame.pack();
        frame.setSize(1300,800);
        frame.setVisible(true); //make frame visible



        ImageIcon image = new ImageIcon("./data/logo.png"); //create an ImageIcon
        frame.setBackground(Color.WHITE);
        frame.setIconImage(image.getImage()); //change icon of frame.


        frame.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new PrintHelp();
            }
        });



    }



    //EFFECTS:the main method
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });



    }



}

