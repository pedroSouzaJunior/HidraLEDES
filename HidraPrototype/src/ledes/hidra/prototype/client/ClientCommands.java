package ledes.hidra.prototype.client;

import ledes.hidra.prototype.Commands;

/**
 * This class contains Client specific commands.
 * All commands specific to the client should be put here.
 * 
 * @author Geraldo B. Landre
 */
public class ClientCommands extends Commands {

    public ClientCommands() {
        super();
        
        strategies.put("create", new CreateCommand());
        strategies.put("list", new ListCommand());
        strategies.put("webmethods", new ListWebMethodsCommand());
        // put client commands here
    }
    
    /**
     * @TODO not implemented yet.
     * @author Danielli Urbieta e Pedro Souza Junior
     */
    class CreateCommand extends Command {

        @Override
        public String execute(String arg) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * @TODO not implemented yet.
     * @author Danielli Urbieta e Pedro Souza Junior
     */
    class ListCommand extends Command {

        @Override
        public String execute(String arg) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * @TODO not implemented yet.
     * @author Danielli Urbieta e Pedro Souza Junior
     */
    class ListWebMethodsCommand extends Command {

        @Override
        public String execute(String arg) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
