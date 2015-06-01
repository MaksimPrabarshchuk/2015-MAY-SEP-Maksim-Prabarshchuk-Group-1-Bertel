package by.epam.mentoring.memory.helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CyclicReference {
    private CyclicReference cyclicReference;
    private static final Logger LOGGER = LogManager.getLogger(CyclicReference.class.getName());

    public CyclicReference() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        cyclicReference = new CyclicReference();
    }
}
