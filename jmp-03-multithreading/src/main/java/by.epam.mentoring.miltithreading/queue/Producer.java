package by.epam.mentoring.miltithreading.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Producer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Producer.class.getName());
    private Random random = new Random();

    private Queue queue;
    private String name;

    public Producer(Queue queue, String name) {
        this.queue = queue;
        this.name = name;
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        Integer i = 0;
        while (true) {
            try {
                queue.put(++i);
                LOGGER.info("{} put :{}", name, i);
                sleep();
            } catch (FullQueueException fullQueueExceprion) {
                sleep();
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(random.nextInt(150));
        } catch (InterruptedException e) {
            LOGGER.error("Producer:", e);
        }
    }

}
