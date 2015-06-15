package by.epam.mentoring.miltithreading.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Consumer.class.getName());
    public static final Integer EXIT = -1;
    private BlockingQueue<Message> queue;
    private Integer messagesSum = 0;

    public Consumer(BlockingQueue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            Message msg = queue.take();
            while (!msg.getMsg().equals(EXIT)) {
                Thread.sleep(10);
                Integer message = msg.getMsg();
                messagesSum += message;
                LOGGER.info("Consumed {}, total sum {}", message, messagesSum);
                msg = queue.take();
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}