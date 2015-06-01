package ledes.hidra.prototype;

import java.util.HashMap;

/**
 * This class is a Strategy Pattern for parsing commands in a command line
 * prompt. All generic commands of a command line interface should be put here
 * such as help command.
 *
 * @see http://pt.wikipedia.org/wiki/Strategy
 * @author Geraldo B. Landre
 */
public class Commands {

    /**
     * Strategy Pattern Collection.
     */
    protected final HashMap<String, Command> strategies;

    /**
     * Creates a new Commands object and fills the strategies collection.
     */
    public Commands() {
        strategies = new HashMap<>();
        strategies.put("help", new HelpCommand());
        // put generic commands here
    }
    
    /**
     * Parses a command
     * @param cmdStr the command to be parsed
     * @param argument the arguments of the command
     * @return the result of the execution of the command
     */
    public String parse(String cmdStr, String argument) {
        try {
            Command cmd = strategies.getOrDefault(cmdStr.toLowerCase(), new HelpCommand());
            String result = cmd.execute(argument);

            return result;
        } catch (Exception ex) {
            return "This command seems not to exist...\n"
                    + "Error: " + ex.getMessage();
        }
    }
    
    /**
     * Prints usage
     * @param jarFile the name of the running jar file.
     * @return 
     */
    public String usage(String jarFile) {
        return "Usage:> java -jar " + jarFile + " command [arguments]\n" +
                "For a list of commands type:\n:> java -jar " + jarFile + " help";
    }

    /**
     * Abstract super class for all possible commands
     *
     * @author Geraldo B. Landre
     */
    public abstract class Command {

        abstract public String execute(String arg);
    }
    
    /**
     * Prints help
     * @author Geraldo B. Landre
     */
    class HelpCommand extends Command {

        @Override
        public String execute(String arg) {
            StringBuilder strb = new StringBuilder("Commands available:\n");
            for(String key : strategies.keySet()) {
                strb.append(key).append('\n');
            }
            return strb.toString();
        }
        
    }
}
