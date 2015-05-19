package main.java.by.epam.mentoring.classloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
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
    public Class<?> findClass(String className) {
        Enumeration<JarEntry> entries = this.jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            try {
                InputStream in = this.jarFile.getInputStream(entry);
                byte[] array = new byte[in.available()];
                ByteArrayOutputStream out = new ByteArrayOutputStream(array.length);
                int length = in.read(array);

                while (length > 0) {
                    out.write(array, 0, length);
                    length = in.read(array);
                }
                return defineClass(className, out.toByteArray(), 0, out.size());
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.error("Can't find class {}", className);
        return null;
    }

}
