package by.epam.mentoring.miltithreading.helpers.initializers;

import by.epam.mentoring.miltithreading.queue.Consumer;
import by.epam.mentoring.miltithreading.queue.Producer;
import by.epam.mentoring.miltithreading.queue.Queue;
import by.epam.mentoring.processor.IProcessor;

public class QueueStarter implements IProcessor {
    @Override
    public void process() {
        Queue queue = new Queue(10);
        new Producer(queue, "P1");
        new Producer(queue, "P2");
        new Consumer(queue, "C1");
        new Consumer(queue, "C2");
        new Consumer(queue, "C3");
    }
}
