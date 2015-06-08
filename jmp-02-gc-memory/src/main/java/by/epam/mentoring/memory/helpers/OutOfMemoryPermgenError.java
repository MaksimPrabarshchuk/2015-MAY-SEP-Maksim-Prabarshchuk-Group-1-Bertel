package by.epam.mentoring.memory.helpers;

import by.epam.mentoring.classloader.CustomClassLoader;
import by.epam.mentoring.processor.IProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class OutOfMemoryPermgenError implements IProcessor {
    private static final Logger LOGGER = LogManager.getLogger(OutOfMemoryPermgenError.class.getName());
    public static final String BY_EPAM_MENTORING_HELPER_CONSOLE_MESSENGER_CLASS = "by.epam.mentoring.helper.ConsoleMessenger.class";
    public static final String USER_DIR = "user.dir";
    public static final String JAR_NAME = "/test.jar";

    List<ClassLoader> list = new ArrayList<ClassLoader>();

    @Override
    public void process() {
        final String workingDir = System.getProperty(USER_DIR);
        LOGGER.info("Current working dir = {} ", workingDir);
        try {
            while (true) {
                CustomClassLoader classLoader = new CustomClassLoader();
                classLoader.loadJar(new JarFile(workingDir + JAR_NAME));
                list.add(classLoader);
                classLoader.loadClass(BY_EPAM_MENTORING_HELPER_CONSOLE_MESSENGER_CLASS);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
