package by.epam.mentoring.helper;

/**
 * Created by Antoni_Bertel on 5/19/2015.
 */
public class ConsoleMessenger implements Messenger {
    @Override
    public void printMessage() {
        System.out.println("Hi, i'm console messenger");
    }
}
