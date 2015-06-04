package ledes.hidra.util;

/**
 * This class is responsible for managing the Configuration library and should control
 its resources in general. For example, logging and properties.
 * No rule specific to its business is implemented here.
 * @author Geraldo B. Landre
 */
public class Configuration {
    /**
     * Properties object.
     * This object contains the set of available properties loaded from
     * text a file with the name "hidra.properties". If this file doesn't exist,
     * than its first call creates the file and set the properties with default
     * values.
     */
    public static final Properties properties = Properties.getProperties();
    
    /**
     * This avoids the class to be instantiated
     */
    private Configuration(){}
}
