package ledes.hidra.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an Adapter to the default java.util.Properties Its aim is to control
 * the use of Properties methods so that it is used in a specific and predefined
 * way. Here are useful references about java.utils.Properties:
 * http://www.mkyong.com/java/java-properties-file-examples/
 * http://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
 * https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html
 *
 * This class is not public and should only be used in the
 * net.ledes.hidra.config.Hidra class
 *
 * @author Geraldo B. Landre
 */
public class Properties {

    /**
     * Default Properties File Name. For now it is only possible to manipulate
     * properties from a file with this name.
     */
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "hidra.properties";

    /**
     * Static instance of this class. It is created by calling getProperties
     * methods for the first time
     */
    private static Properties properties;

    /**
     * Original properties file.
     */
    private java.util.Properties props;

    /**
     * Returns the properties object. If it is not created yet, than creates it.
     *
     * @param fileName the name of the properties file
     * @return the properties object
     */
    public static Properties getProperties(String fileName) {
        if (properties == null) {
            properties = new Properties(fileName);
        }
        return properties;
    }

    /**
     * Returns the properties object. If it is not created yet, than creates it.
     *
     * @return the properties object
     */
    public static Properties getProperties() {
        return getProperties(DEFAULT_PROPERTIES_FILE_NAME);
    }

    /**
     * *
     * Contrutor publico
     */
    public Properties() {

        props = new java.util.Properties();
        File file = new File("hidra.properties");

        try {
            FileInputStream fileInput;
            //fileInput = new FileInputStream(file);
            fileInput = new FileInputStream(Thread.currentThread()
                    .getContextClassLoader()
                    .getResource("hidra.properties")
                    .getPath());
            props.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Private constructor. Creates a new instance of Properties. And load the
     * file properties hidra.properties from the classpath
     *
     * @param propertiesFile the properties file name
     */
    private Properties(String propertiesFile) {
        props = new java.util.Properties(props);

        File file = new File(propertiesFile);
        if (file.exists()) {
            try {
                props.load(new FileInputStream(file));
            } catch (IOException ex) {
                Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            props = getDefaultProps();
            try {
                props.store(new FileOutputStream(file), null);
            } catch (IOException ex) {
                Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Returns a properties object with the default properties.
     *
     * @return a properties object with the default properties
     *
     */
    private java.util.Properties getDefaultProps() {
        java.util.Properties defaults = new java.util.Properties();

        defaults.setProperty("localPath", "");
        defaults.setProperty("ManifestName", "rasset");
        defaults.setProperty("ManifestExtension", "xml");

        //@task: criar propriedade para conexao com SGBD
        return defaults;
    }

    /**
     * Returns a property which the key is informed.
     *
     * @param key Property key
     * @return The value corresponding to the passed key
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }
}
