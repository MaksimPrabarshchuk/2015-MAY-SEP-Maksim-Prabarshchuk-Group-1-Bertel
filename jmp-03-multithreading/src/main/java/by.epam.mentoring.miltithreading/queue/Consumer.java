package by.epam.mentoring.miltithreading.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Consumer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Consumer.class.getName());
    private Random random = new Random();

    private Queue queue;
    private String name;

    public Consumer(Queue queue, String name) {
        this.queue = queue;
        this.name = name;
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        while (true) {
            Object o = queue.get();
            if (o != null) {
                LOGGER.info("{} got :{}", name, o);
            }
            try {
                Thread.sleep(random.nextInt(150));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
