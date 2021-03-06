package by.epam.mentoring.classloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;

/**
 * Created by Antoni Bertel on 17.05.2015.
 */
public class CustomClassLoader extends ClassLoader {
    private static final Logger LOGGER = LogManager.getLogger(CustomClassLoader.class.getName());
    private JarFile jarFile;

    /**
     * Load classes from jar to memory
     *
     * @param jarfile jar file with classes, which will be loaded
     * @throws IOException
     */
    public void loadJar(JarFile jarfile) throws IOException {
        this.jarFile = jarfile;
    }

    @Override
    protected Class<?> findClass(String className) {
        try {
            InputStream in = this.jarFile.getInputStream(this.jarFile.getEntry(className.replace(".", "/").replace("/class", ".class")));
            byte[] array = new byte[in.available()];
            ByteArrayOutputStream out = new ByteArrayOutputStream(array.length);
            int length = in.read(array);
            while (length > 0) {
                out.write(array, 0, length);
                length = in.read(array);
            }
            LOGGER.info("Found class {}", className);
            return defineClass(className.replace(".class", ""), out.toByteArray(), 0, out.size());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.error("Can't find class {}", className);
        return null;
    }

}
