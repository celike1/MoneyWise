# My Budgeting Application

## Project Proposal 

This budgeting application will gather data on users' income and expenses and enable them to keep track of their 
finances. Users will be able to set savings goals and the app will show them how much money they should save to reach 
those goals. Users will be able to change the amount that they set for their goals. They will be able to view 
all of their expenses and have control over their money. This app is mainly designed for **students** and *people who
want to know how they are managing their money.* 

I am interested in creating a budgeting app because:

- I am a student who lives on campus and I have a lot of expenses. My family send me money every month and I want to 
exactly know the things that I am spending money to.
- I want to get better at saving money and I believe that having an application that can show me what my expenses are
  can help me make more informed decisions about money.

## User Stories

For my budgeting application, possible stories are:

- As a user, I want to be able to add a new expense with a title and and amount to my Expenses List.
- As a user, I want to be able to delete an expense entry from my Expenses List.
- As a user, I want to be able to view the list of expenses on my Expenses List.
- As a user, I want to be able to see the total amount of the expenses on my Expenses List.
- As a user, I want to be able to save my Expense List to file.
- As a user, I want to be able to be able to load my Expense List from file. 




## Phase 4: Task 2

When I add 3 items and remove one of them the logging of events looks like the following:

Fri Nov 26 12:56:24 PST 2021
Added expense: a $1.0

Fri Nov 26 12:56:27 PST 2021
Added expense: b $2.0

Fri Nov 26 12:56:30 PST 2021
Added expense: c $3.0

Fri Nov 26 12:56:35 PST 2021
Removed expense: a $1.0




## Phase 4: Task 3




In my GUI class I have a lot of code about the frame, panels and layouts(general design). Instead of putting them 
in my GUI class, putting them in a separate class and making that class only about the visual aspect of my GUI
can increase the readability of my code. My GUI class is quite long at the moment.


Also, all of the buttons I added to my GUI have similar features and as a result there is a lot of repeated code. 
To reduce duplication I can extract the repeated parts and make a separate method that I can
use for my buttons.

In my static main method I have another static method called createAndShowGUI() that creates and shows the GUI when 
the application is run. Since that method is static I wasn't able to use the fields of the GUI class. As a result,
just for logging functionality I had to create a new class. To avoid this, instead of using a separate static method 
like createAndShowGUI() inside GUI class, GUI class can be structered in a way that calling new GUI() results in 
creating the GUI.


