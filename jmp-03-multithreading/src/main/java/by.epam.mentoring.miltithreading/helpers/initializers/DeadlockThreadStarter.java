package by.epam.mentoring.miltithreading.helpers.initializers;

import by.epam.mentoring.miltithreading.helpers.CustomThread;
import by.epam.mentoring.processor.IProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeadlockThreadStarter implements IProcessor {
    private static final Logger LOGGER = LogManager.getLogger(DeadlockThreadStarter.class.getName());

    @Override
    public void process() {
        Integer firstString = Integer.valueOf(1);
        Integer secondString = Integer.valueOf(2);
        Integer thirdString = Integer.valueOf(3);
        Thread firstThread = new Thread(new CustomThread(firstString, secondString), "Thread 1");
        Thread secondThread = new Thread(new CustomThread(secondString, thirdString), "Thread 2");
        Thread thirdThread = new Thread(new CustomThread(thirdString, firstString), "Thread 3");
        try {
            firstThread.start();
            Thread.sleep(5000);
            secondThread.start();
            Thread.sleep(5000);
            thirdThread.start();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }

    }
}
