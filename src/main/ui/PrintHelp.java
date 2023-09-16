package ui;


import model.Event;
import model.EventLog;

//class for printLog
public class PrintHelp {

    //EFFECTS: constructor for PrintHelp
    public PrintHelp() {

        printLog(EventLog.getInstance());


    }

    //EFFECTS: prints the log
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }

    }
}
