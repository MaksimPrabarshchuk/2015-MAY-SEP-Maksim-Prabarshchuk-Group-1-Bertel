package by.epam.mentoring.memory;


import by.epam.mentoring.memory.helpers.CyclicReference;
import by.epam.mentoring.memory.helpers.OutOfMemoryHeapError;
import by.epam.mentoring.memory.helpers.OutOfMemoryPermgenError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Starter {
    private static final Logger LOGGER = LogManager.getLogger(Starter.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Parameters: 1 - OutOfMemoryPermgen, 2 - OutOfMemoryHeap, 3 - OutOfMemory (Cyclic references)");
        if (args.length < 1) {
            LOGGER.error("Expected parameter");
            return;
        }
        Integer attribute = Integer.parseInt(args[0]);
        LOGGER.info("Got attributes {}", attribute);
        if (attribute.equals(1)) {
            new OutOfMemoryPermgenError().process();
        }
        if (attribute.equals(2)) {
            new OutOfMemoryHeapError().process();
        }
        new CyclicReference();

    }
}
