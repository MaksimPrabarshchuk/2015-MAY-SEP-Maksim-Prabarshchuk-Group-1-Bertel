package by.epam.mentoring.miltithreading.helpers.initializers;

import by.epam.mentoring.miltithreading.queue.Consumer;
import by.epam.mentoring.miltithreading.queue.Message;
import by.epam.mentoring.miltithreading.queue.Producer;
import by.epam.mentoring.processor.IProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueStarter implements IProcessor {
    private static final Logger LOGGER = LogManager.getLogger(QueueStarter.class.getName());

    @Override
    public void process() {
        //Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        //starting producer to produce messages in queue
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        LOGGER.info("Producer and Consumer has been started");
    }

}