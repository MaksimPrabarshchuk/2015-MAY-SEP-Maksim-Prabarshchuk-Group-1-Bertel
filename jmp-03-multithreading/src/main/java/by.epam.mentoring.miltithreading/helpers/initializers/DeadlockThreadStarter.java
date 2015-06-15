package by.epam.mentoring.miltithreading.helpers.initializers;

import by.epam.mentoring.miltithreading.helpers.CustomThread;
import by.epam.mentoring.processor.IProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockThreadStarter implements IProcessor {
    private static final Logger LOGGER = LogManager.getLogger(DeadlockThreadStarter.class.getName());

    @Override
    public void process() {
        Lock firstString = new ReentrantLock();
        Lock secondString = new ReentrantLock();
        Lock thirdString = new ReentrantLock();
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
