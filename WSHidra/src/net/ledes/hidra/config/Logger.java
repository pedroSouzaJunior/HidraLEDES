package net.ledes.hidra.config;

/**
 * This is a Wrapper to the default java.utils.Logger
 * Its aim is to control the use of Logger methods so that it is used in a specific
 * and predefined way.
 * Here are useful references about java.utils.Logger:
 * https://docs.oracle.com/javase/7/docs/api/java/util/logging/Logger.html
 * http://stackoverflow.com/questions/5950557/good-examples-using-java-util-logging
 * http://hanoo.org/index.php?article=how-to-generate-logs-in-java
 * 
 * This class is not public and should only be used in the 
 * net.ledes.hidra.config.Hidra class
 * 
 * TODO: Not implemented yet.
 * @author geraldo
 */
class Logger {
    private static java.util.logging.Logger logger;
    
    static Logger getLogger(String name) {
        logger = java.util.logging.Logger.getLogger(name);
        // TODO implement rules to constraint the logger.
        return null;
        
    }
    
}
