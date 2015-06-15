package by.epam.mentoring.miltithreading.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;

/**
 * Created by Antoni_Bertel on 6/7/2015.
 */
public class CustomThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(CustomThread.class.getName());
    public static final int SLEEP_MILLIS = 10000;
    private final Lock firstLock;
    private final Lock secondLock;


    public CustomThread(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
            firstLock.lock();
        LOGGER.info("Acquired lock on {}", firstLock);
        doWork();
                secondLock.lock();
        LOGGER.info("Acquired lock on {}", secondLock);
        doWork();
                secondLock.unlock();
        LOGGER.info("Released lock on {}", secondLock);
            firstLock.unlock();
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
