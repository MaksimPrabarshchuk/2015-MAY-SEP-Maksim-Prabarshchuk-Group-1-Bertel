package by.epam.mentoring.miltithreading.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Producer.class.getName());
    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Message msg = new Message(i);
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            putMessage(msg);
            LOGGER.info("Produced {}", msg.getMsg());
        }
        //adding exit message
        putMessage(new Message(-1));
    }

    private void putMessage(Message message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

}