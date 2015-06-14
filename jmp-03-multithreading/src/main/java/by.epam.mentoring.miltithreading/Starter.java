package by.epam.mentoring.miltithreading;

import by.epam.mentoring.miltithreading.helpers.initializers.DeadlockThreadStarter;
import by.epam.mentoring.miltithreading.helpers.initializers.NonBlockingThreadStarter;
import by.epam.mentoring.miltithreading.helpers.initializers.QueueStarter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Starter {
    private static final Logger LOGGER = LogManager.getLogger(Starter.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Parameters: 1 - Blocking thread, 2 - Non-blocking thread, 3 - queue");
        if (args.length < 1) {
            LOGGER.error("Expected parameter");
            return;
        }
        Integer attribute = Integer.parseInt(args[0]);
        LOGGER.info("Got attributes {}", attribute);
        if (attribute.equals(1)) {
            new DeadlockThreadStarter().process();
            return;
        }
        if (attribute.equals(2)) {
            new NonBlockingThreadStarter().process();
            return;
        }

        if (attribute.equals(3)) {
            new QueueStarter().process();
        }
    }
}