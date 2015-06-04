package ledes.hidra;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Config;

/**
 * This class is responsible to manage a central repository (server).
 *
 * All operations to manage a local respository is implemented here. The
 * webservice layer uses this class to make it publically available.
 *
 * @TODO: transferir todos os métodos da classe net.ledes.hidra.sources.Hidra
 * relacionados a manipulação de repositório central para cá. Exemplo: criar
 * repositório local sem conexão com nenhum outro master.
 *
 * @TODO: Documentar todos membros da classe (atributos, métodos, construtores),
 * independente do modificador de acesso (public, protected, private),
 * imediatamente assim que criá-los.
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Repository {

    /**
     * @param String localPath - Define full path to the repository local
     */
    private String localPath;
    /**
     * @param String remotePath - Define URL or path to the repository remote
     */
    private String remotePath;

    /**
     * @param Git - Used to get the Git commands api
     */
    private Git assistant;

    /**
     * @param File - Directory where the repository
     */
    private final File directory;

    /**
     * Builder of the repository class with one parameter. You must start the
     * repository after the instantiation of the repository.
     *
     * @param localPath - Full path to the repository local
     */
    public Repository(String localPath) {
        super();
        this.localPath = localPath;
        this.directory = new File(localPath);

    }

    /**
     * Builder of the repository class with two parameters. You must start the
     * repository after the instantiation of the repository.
     *
     * @param localPath - Full path to the repository local
     * @param remotePath - URL or path to the repository remote
     */
    public Repository(String localPath, String remotePath) {
        super();
        this.localPath = localPath;
        this.remotePath = remotePath;
        this.directory = new File(localPath);

    }

    /**
     * Return path repository
     *
     * @return localPath
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Set path repository
     *
     * @param localPath
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    /**
     * Return remote repository path
     *
     * @return
     */
    public String getRemotePath() {
        return remotePath;
    }

    /**
     * Set remote repository path
     *
     * @param remotePath
     */
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    /**
     *
     * @return assistant
     */
    public Git getAssistant() {
        return assistant;
    }

    /**
     * Method responsible for creating or starting a repository.
     */
    public final void start() {

        try {
            assistant = Git.init().setDirectory(directory).call();

        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Method responsible for cloning a remote repository in a local directory.
     * The destination directory should be empty.
     *
     * @return boolean. False if not empty directory.
     * @throws GitAPIException
     */
    public boolean cloneRepository() throws GitAPIException {

        if (directory.exists() && directory.listFiles().length != 0) {
            System.out.println("Repository not empty , Canceled Operation");
            return false;
        } else {
            assistant = Git.cloneRepository().setURI(remotePath)
                    .setDirectory(directory).call();

            try {
                System.out.println("Repository successfully cloned "
                        + assistant.getRepository().getDirectory());
            } finally {
                assistant.close();
            }
            return true;
        }

    }

    /**
     * Responsible method to delete a repository
     */
    public void removeRepository() {
        if (directory.isDirectory()) {
            File[] sun = directory.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }

        }
    }

    /**
     *
     * @return returns true if repository initialized
     */
    public boolean isRepositoryInitialized() {

        return !assistant.getRepository().isBare();

    }

    /**
     * Receives name and email to configure User Account.
     *
     * @param name
     * @param email
     *
     */
    public void setConfigurationUser(String name, String email) {

        Config config = assistant.getRepository().getConfig();
        config.setString("user", null, "name", name);
        config.setString("user", null, "email", email);

    }

    /**
     * Returns a key list and value with data about the user
     *
     * @return Map<String, String>
     */
    public Map<String, String> getConfigurationUser() {
        Config config = assistant.getRepository().getConfig();

        String name = config.getString("user", null, "name");
        String email = config.getString("user", null, "email");
        Map<String, String> configuration = new HashMap<>();
        if (name == null || email == null) {
            System.out.println("User identity is unknown!");
            return null;
        } else {
            configuration.put(name, email);
            return configuration;
        }

    }

    /**
     * Remove User settings
     */
    public void unSetConfigurationUser() {

        Config config = assistant.getRepository().getConfig();
        config.unsetSection("user", "email");
        config.unsetSection("user", "name");

    }

    /**
     * Receives the URL of the remote repository
     *
     * @param remoteRepository
     */
    public void setConfigRemote(String remoteRepository) {

        Config config = assistant.getRepository().getConfig();
        config.setString("remote", "origin", "url", remoteRepository);
    }

    /**
     *  Remove remote repository.
     * 
     */
    public void unSetConfigRemote() {

        Config config = assistant.getRepository().getConfig();
        config.unset("remote", "origin", "url");
    }

    
    /**
     * Return url of the remote repository.
     * @return String url
     */
    public String getConfigRemote() {

        Config config = assistant.getRepository().getConfig();
        String url = config.getString("remote", "origin", "url");
        if (url != null) {
            System.out.println("Origin comes from " + url);
            return url;
        }
        return null;
    }

    /**
     * 
     * @return true, if there are remote repository configured
     */
    public boolean hasRemoteRepository() {

        Config config = assistant.getRepository().getConfig();
        String url = config.getString("remote", "origin", "url");
        return url != null;
    }

}
