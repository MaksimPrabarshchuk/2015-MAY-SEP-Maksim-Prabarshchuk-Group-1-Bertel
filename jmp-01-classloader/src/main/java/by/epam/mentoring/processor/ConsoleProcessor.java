package by.epam.mentoring.processor;

/**
 * Created by Antoni_Bertel on 5/19/2015.
 */
public class ConsoleProcessor implements IProcessor {
    @Override
    public void process() {
        System.out.println("Hi, i'm console messenger");
    }
}
