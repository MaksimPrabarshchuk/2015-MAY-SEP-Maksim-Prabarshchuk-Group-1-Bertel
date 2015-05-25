package by.epam.mentoring.classloader;

import by.epam.mentoring.helper.Messenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.jar.JarFile;

/**
 * Created by Antoni Bertel on 17.05.2015.
 */
public class Starter {
    private static final Logger LOGGER = LogManager.getLogger(Starter.class.getName());
    public static final String USER_DIR = "user.dir";
    public static final int MINIMAL_ARGS_SIZE = 2;

    public static void main(String args[]) {
        if (args.length < MINIMAL_ARGS_SIZE) {
            LOGGER.error("Expect a relative path to jar and full class name, example by.epam.mentoring.helper.ConsoleMessenger.class");
            return;
        }
        final String jarName = args[0];
        final String className = args[1];
        final String workingDir = System.getProperty(USER_DIR);
        LOGGER.info("Current working dir = {} ", workingDir);
        CustomClassLoader myClassLoader = new CustomClassLoader();
        try {
            myClassLoader.loadJar(new JarFile(workingDir + "/" + jarName));
            Class someClass = myClassLoader.loadClass(className);
            Messenger messenger = (Messenger) someClass.newInstance();
            messenger.printMessage();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
