package by.epam.mentoring.miltithreading.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Antoni_Bertel on 6/7/2015.
 */
public class NonBlockingCustomThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(NonBlockingCustomThread.class.getName());
    public static final int SLEEP_MILLIS = 10000;
    private Object firstLock;
    private Object secondLock;

    public NonBlockingCustomThread(Object firstLock, Object secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        LOGGER.info("Got access to first obj {}", firstLock);
        doWork();
        LOGGER.info("Got access to second obj {}", secondLock);
        doWork();
        LOGGER.info("{} finished execution", name);
    }

    private void doWork() {
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }


}
