package ledes.hidra.prototype;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;
import ledes.hidra.util.Configuration;

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
    Hidra hidra = new Hidra(Configuration.properties.getProperty("LocalPath"));

    /**
     * Creates a new Commands object and fills the strategies collection.
     */
    public Commands() {
        strategies = new HashMap<>();
        strategies.put("help", new HelpCommand());
        strategies.put("start", new StartRepoCommand());
        strategies.put("add", new AddAssetCommand());
        strategies.put("remove", new RemoveAssetCommand());
        strategies.put("list", new ListAssetsCommand());
        strategies.put("logs", new ShowLogsCommand());
        strategies.put("save", new SaveChangesCommand());
        strategies.put("status", new ShowStatusCommand());
        strategies.put("update", new UpdateAssetCommand());
        strategies.put("set-user", new SetUserCommand());
        strategies.put("set-remote",new SetRemoteRepo());
        strategies.put("log-asset",new ShowLogsParameterCommand());
        strategies.put("get-classification", new GetClasssificationCommand());
        strategies.put("get-related", new GetRelatedAssetsCommand());
        strategies.put("get-solution", new GetSolutionCommand());
        strategies.put("get-remote", new GetRemoteRepo());
        strategies.put("get-usage", new GetUsageCommand());
        strategies.put("validate", new ValidateAssetCommand());

        // put generic commands here
    }

    /**
     * Parses a command
     *
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
     *
     * @param jarFile the name of the running jar file.
     * @return
     */
    public String usage(String jarFile) {
        return "Usage: java -jar " + jarFile + " command [arguments]\n"
                + "For a list of commands type:\n:> java -jar " + jarFile + " help";
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
     *
     * @author Geraldo B. Landre
     */
    class HelpCommand extends Command {

        @Override
        public String execute(String arg) {
            StringBuilder strb = new StringBuilder("Commands available:\n");
            for (String key : strategies.keySet()) {
                strb.append(key).append('\n');
            }
            return strb.toString();
        }

    }

    /**
     * Inicia
     */
    class StartRepoCommand extends Command {

        @Override
        public String execute(String arg) {
            
            

            try {
                if (!hidra.startRepository(Configuration.properties.getProperty("LocalPath"))) {
                } else {
                    return "Repository Inizialized in " + Configuration.properties.getProperty("LocalPath");
                }
            } catch (IOException | JAXBException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }

            return "Fail ";

        }
    }

    /**
     * Adiciona um ativo no repositorio
     *
     */
    class AddAssetCommand extends Command {

        @Override
        public String execute(String arg) {

            System.out.println(arg+"a");
            if (!hidra.addAsset(arg)) {
            } else {
                return "Asset add in " + Configuration.properties.getProperty("LocalPath");
            }

            return "Fail ";
        }

    }

    class RemoveAssetCommand extends Command {

        @Override
        public String execute(String arg) {
            if (!hidra.removeAsset(arg)) {
            } else {
                return "Asset remove in " + Configuration.properties.getProperty("LocalPath");
            }

            return "Fail ";
        }

    }

    class ListAssetsCommand extends Command {

        @Override
        public String execute(String arg) {
            Map<String, String> list = hidra.listAssets();
            String result = null;
            for (Map.Entry<String, String> entry : list.entrySet()) {
                result = "\n" + entry.getKey() + " : " + entry.getValue();
            }
            return result;
        }

    }

    class ShowLogsCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.showLogs();
        }

    }

    class SaveChangesCommand extends Command {

        @Override
        public String execute(String arg) {

            if (hidra.save(arg)) {
                return "Save Changes";
            }
            return "Fail";
        }

    }

    class ShowStatusCommand extends Command {

        @Override
        public String execute(String arg) {
           
            String result = null;
            for (Map.Entry<String, Set<String>> entry : hidra.showStatus().entrySet()) {
                result = entry.getKey() + " : " + entry.getValue();
            }

            return result;

        }

    }

    class GetSolutionCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getSolution(arg);
        }

    }

    class ValidateAssetCommand extends Command {

        @Override
        public String execute(String arg) {
            if (hidra.validateAsset(arg)) {
                return "Valid Asset";
            }
            return "Invalid Asset";
        }

    }

    class GetClasssificationCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getClassification(arg);
        }

    }

    class GetUsageCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getUsage(arg);
        }

    }

    class GetRelatedAssetsCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getRelatedAssets(arg);
        }

    }

    class ShowLogsParameterCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getLog(arg);
        }

    }

    class UpdateAssetCommand extends Command {

        @Override
        public String execute(String arg) {

            if (hidra.updateAsset(arg)) {
                return "Asset Updated";
            }
            return "Failure to update the asset";
        }

    }

    class SetUserCommand extends Command {

        
        @Override
        public String execute(String arg) {
            
            hidra.setUser(Configuration.properties.getProperty("UserName"), 
                    Configuration.properties.getProperty("UserEmail"));
            return "User configuration updated: \n"
                    + hidra.getUser();
        }

    }

    class GetUserRepoCommand extends Command {

        @Override
        public String execute(String arg) {
            String result = null;
            for (Map.Entry<String, String> entry : hidra.getUser().entrySet()) {
                
                    result = entry.getKey() + " : " + entry.getValue();
                }

                return result;
            

        }
    }
    
    class SetRemoteRepo extends Command{

        @Override
        public String execute(String arg) {
            hidra.setRemoteRepo(arg);
            return "Remote Path Updated";
        }
    
    
    
    }
    
    
    class GetRemoteRepo extends Command{

        @Override
        public String execute(String arg) {
            return hidra.getRemoteRepo();
        }
        
    
    }
}
