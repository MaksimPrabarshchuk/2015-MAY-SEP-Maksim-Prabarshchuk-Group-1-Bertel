package by.epam.mentoring.classloader;

import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.generic.ClassGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Antoni Bertel on 17.05.2015.
 */
public class CustomClassLoader extends ClassLoader {
    private static final Logger LOGGER = LogManager.getLogger(CustomClassLoader.class.getName());
    public static final String CLASS_EXTENSION = ".class";

    {
        classNames = new ArrayList<String>();
        classes = new HashMap<String, byte[]>();
    }

    /**
     * A List containing all of the class names within this jar.
     */
    private List<String> classNames;

    /**
     * A Map containing a byte[] as a value for each class name as a key.
     */
    private Map<String, byte[]> classes;

    /**
     * Load classes from jar to memory
     *
     * @param jarfile jar file with classes, which will be loaded
     * @throws IOException
     */
    public void loadJar(JarFile jarfile) throws IOException {
        Enumeration<JarEntry> entries = jarfile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith(CLASS_EXTENSION)) {
                String className = entryName.substring(0, entryName.indexOf(CLASS_EXTENSION));
                InputStream in = jarfile.getInputStream(entry);
                ClassParser classParser = new ClassParser(in, entryName);
                ClassGen classGen = new ClassGen(classParser.parse());
                byte[] buffer = classGen.getJavaClass().getBytes();
                classes.put(className, buffer);
                classNames.add(className);
                LOGGER.info("Loaded class {}", className);
            }
        }
    }


    /**
     * @param name The class name to search for.
     * @return The Class whose name is that of the given arg.
     */
    @Override
    public Class<?> loadClass(String name) {
        byte[] buffer = classes.get(name);
        if (buffer != null) {
            classes.remove(name);
            return defineClass(name, buffer, 0, buffer.length);
        }
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


    public Map<String, byte[]> getClasses() {
        return classes;
    }

    public List<String> getClassNames() {
        return classNames;
    }
}
