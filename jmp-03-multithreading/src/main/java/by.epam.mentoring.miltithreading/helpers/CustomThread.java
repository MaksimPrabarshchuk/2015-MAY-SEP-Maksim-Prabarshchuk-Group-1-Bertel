package by.epam.mentoring.miltithreading.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Antoni_Bertel on 6/7/2015.
 */
public class CustomThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(CustomThread.class.getName());
    public static final int SLEEP_MILLIS = 10000;
    private Object firstLock;
    private Object secondLock;

    public CustomThread(Object firstLock, Object secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (firstLock) {
            LOGGER.info("Acquired lock on {}", firstLock);
            doWork();
            synchronized (secondLock) {
                LOGGER.info("Acquired lock on {}", secondLock);
                doWork();
            }
            LOGGER.info("Released lock on {}", secondLock);
        }
        LOGGER.info("Released lock on {}", firstLock);
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
