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
        strategies.put("start-synchronized", new StartSynchronizedRepository());
        strategies.put("start-synchronized-authentification", new StartSynchronizedRepositoryA());
        strategies.put("add", new AddAssetCommand());
        strategies.put("remove", new RemoveAssetCommand());
        strategies.put("list", new ListAssetsCommand());
        strategies.put("logs", new ShowLogsCommand());
        strategies.put("save", new SaveChangesCommand());
        strategies.put("status", new ShowStatusCommand());
        strategies.put("send-updates", new UpdateRemote());
        strategies.put("receive-updates", new UpdateLocal());
        strategies.put("set-user", new SetUserCommand());
        strategies.put("get-user", new GetUserCommand());
        strategies.put("set-remote", new SetRemoteRepo());
        strategies.put("log-asset", new ShowLogsParameterCommand());
        strategies.put("get-classification", new GetClasssificationCommand());
        strategies.put("get-related", new GetRelatedAssetsCommand());
        strategies.put("get-solution", new GetSolutionCommand());
        strategies.put("get-remote", new GetRemoteRepo());
        strategies.put("get-usage", new GetUsageCommand());
        strategies.put("start-bare", new StartBareCommand());
        strategies.put("create-branch", new CreateBranch());
        strategies.put("checkout", new CheckoutBranch());
        strategies.put("show-branches", new ShowBranch());
        strategies.put("merge", new MergeBranch());
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
            return "Error: " + ex.getMessage();
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

            if (hidra.addAsset(arg)) {
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
                return "Asset removed in " + Configuration.properties.getProperty("LocalPath");
            }

            return "Fail ";
        }

    }

    class ListAssetsCommand extends Command {

        @Override
        public String execute(String arg) {
            StringBuilder assets = new StringBuilder();
            Map<String, String> list = hidra.listAssets();
            //String result = null;
            for (Map.Entry<String, String> entry : list.entrySet()) {
                assets.append("\n").append(entry.getKey()).append(" : ").append(entry.getValue());
            }

            return assets.toString();

        }

    }

    class ShowLogsCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getLog();
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

            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Set<String>> entry : hidra.showStatus().entrySet()) {
                if (entry.getValue().size() > 0) {
                    result.append("\n").append(entry.getKey()).append(" : ");
                    for (String s : entry.getValue()) {
                        result.append("\n\t").append(s);
                    }
                }
            }

            return result.toString();

        }
    }

    class GetSolutionCommand extends Command {

        @Override
        public String execute(String arg) {
            return hidra.getSolution(arg);
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

    class SetRemoteRepo extends Command {

        @Override
        public String execute(String arg) {
            hidra.setRemoteRepo(Configuration.properties.getProperty("RemoteURI"));
            return "Remote Path Updated";
        }

    }

    class GetRemoteRepo extends Command {

        @Override
        public String execute(String arg) {
            String remote = hidra.getRemoteRepo();
            return remote != null ? remote : "Remote repository not set";
        }

    }

    class StartSynchronizedRepository extends Command {

        @Override
        public String execute(String arg) {
            if (hidra.startSynchronizedRepository(Configuration.properties.getProperty("LocalPath"), Configuration.properties.getProperty("RemoteURI"))) {
                return "Started Synchronized Repository";
            } else {
                return "Fail";
            }
        }

    }

    class StartSynchronizedRepositoryA extends Command {

        @Override
        public String execute(String arg) {
            String localPath = Configuration.properties.getProperty("LocalPath");
            String remotePath = Configuration.properties.getProperty("RemoteURI");
            String user = Configuration.properties.getProperty("UserName");
            String password = Configuration.properties.getProperty("Password");
            if (hidra.startSynchronizedRepository(localPath, remotePath, user, password)) {
                return "Started Synchronized Repository";
            } else {
                return "Fail";
            }
        }

    }

    class UpdateRemote extends Command {

        @Override
        public String execute(String arg) {
            String user = Configuration.properties.getProperty("UserName");
            String password = Configuration.properties.getProperty("Password");
            return hidra.sendUpdates(user, password);

        }
    }

    class UpdateLocal extends Command {

        @Override
        public String execute(String arg) {
            String user = Configuration.properties.getProperty("UserName");
            String password = Configuration.properties.getProperty("Password");
            if (hidra.receiveUpdates(user, password)) {
                return "Local Repository Updated";
            } else {
                return "Fail";
            }

        }
    }

    class GetUserCommand extends Command {

        @Override
        public String execute(String arg) {

            return "User configuration: \n"
                    + hidra.getUser();
        }
    }

    class CreateBranch extends Command {

        @Override
        public String execute(String arg) {
            return hidra.createBranch(arg);

        }

    }

    class CheckoutBranch extends Command {

        @Override
        public String execute(String arg) {

            if (hidra.checkoutBranch(arg)) {
                return "Branch trocado";
            } else {
                return "Fail";
            }
        }

    }

    class ShowBranch extends Command {

        @Override
        public String execute(String arg) {
            if (hidra.showBranches()) {
                return "Succesfully";
            } else {
                return "Fail";
            }
        }

    }

    class MergeBranch extends Command {

        @Override
        public String execute(String arg) {
            if (hidra.mergeBranch(arg)) {
                return "Successfully Merge";
            } else {
                return "Erro!";
                
            }
        }

    }
    
    class StartBareCommand extends Command{

        @Override
        public String execute(String arg) {
            try {
                if (!hidra.startRepositoryBare(Configuration.properties.getProperty("LocalPath"))) {
                } else {
                    return "Repository Inizialized in " + Configuration.properties.getProperty("LocalPath");
                }
            } catch (IOException | JAXBException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Fail ";
        }
    
    
    
    }

}
