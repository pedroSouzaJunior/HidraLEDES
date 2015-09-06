package ledes.hidra.prototype;




/**
 * This Prototype aims to proof Hidra's applicability.
 * It is a simple main class which accepts commands from the command line and
 * tries to execute them based on a Command class
 *
 * @author Geraldo B. Landre
 */
public class HidraPrototype {
    private static Commands commands;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // setUpRepository();

        commands = new Commands();
        if (args == null || args.length == 0) {
            System.out.println(commands.usage(HidraPrototype.class.getSimpleName()));
        } else {
            String command = args[0];
            //System.out.println(command);

            StringBuilder arguments = new StringBuilder();
            if (args.length > 1) {
                for (int i = 1; i < args.length; ++i) {
                    //arguments.append(args[i]).append(' ');
                    arguments.append(args[i]);
                }
            }

         
         
            
            String result = commands.parse(command, arguments.toString());

            System.out.println(result);
        }
    }
    
    /**
     * Within this method, the Hidra library should be set up, i.e.,
     * its property file should be read, in this way, the program should
     * know:
     * - if the local repository already exists or not;
     * - if it exists, where is the existing repository (Local directory);
     * - where is the remote repository (URI);
     * - which credentials should be used to connect to remote repository;
     * - etc.
     */
//    private static void setUpRepository() {
//        String type = Configuration.properties.getProperty("ApplicationType");
//        
//        // TODO
//        
//        commands = new ClientCommands();
//        if(type.equals("Repository")) {
//            commands = new RepositoryCommands();
//        }
//    }

}
