package by.epam.mentoring.miltithreading.helpers.initializers;

import by.epam.mentoring.miltithreading.helpers.NonBlockingCustomThread;
import by.epam.mentoring.processor.IProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingThreadStarter implements IProcessor {
    private static final Logger LOGGER = LogManager.getLogger(NonBlockingThreadStarter.class.getName());

    @Override
    public void process() {
        AtomicInteger firstString = new AtomicInteger(1);
        AtomicInteger secondString = new AtomicInteger(2);
        AtomicInteger thirdString =  new AtomicInteger(3);
        Thread firstThread = new Thread(new NonBlockingCustomThread(firstString, secondString), "Thread 1");
        Thread secondThread = new Thread(new NonBlockingCustomThread(secondString, thirdString), "Thread 2");
        Thread thirdThread = new Thread(new NonBlockingCustomThread(thirdString, firstString), "Thread 3");
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
