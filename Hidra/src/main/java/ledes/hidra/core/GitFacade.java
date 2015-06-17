package ledes.hidra.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ledes.hidra.Repository;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * This class is a Facade Pattern to jGit features. Its aim is to simplify
 * access to JGit features in order to achieve repository objectives.
 *
 * @see http://pt.wikipedia.org/wiki/Fa%C3%A7ade
 *
 * @TODO Transferir das classes net.ledes.hidra.sources.Hidra e
 * net.ledes.hidra.sources.Command, todos os métodos de pura manipulação jGit
 * para cá.
 *
 * @TODO Documentar todos membros da classe (atributos, métodos, construtores),
 * independente do modificador de acesso (public, protected, private),
 * imediatamente assim que criá-los.
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class GitFacade {

    /**
     * @param Git - Used to get the Git commands api
     */
    private Git assistant;

    private final String localPath;

    public GitFacade(String localPath) {
        super();
        this.localPath = localPath;
    }

    /**
     * Method responsible for creating or starting a repository.
     *
     * @param directory
     * @return
     */
    public final boolean start(File directory) {

        if (!isRepositoryInitialized()) {
            try {
                assistant = Git.init().setDirectory(directory).call();
                return true;

            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }
        return true;

    }

    /**
     * Method responsible for cloning a remote repository in a local directory.
     * The destination directory should be empty.
     *
     * @param directory
     * @param remotePath
     * @return boolean. False if not empty directory.
     * @throws GitAPIException
     */
    public boolean cloneRepository(File directory, String remotePath) throws GitAPIException {

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
     * Pensando em utilizar apenas no GitFacade
     *
     *
     * @return returns true if repository initialized
     */
    public boolean isRepositoryInitialized() {

        try {
            assistant = Git.open(new F‌ile(localPath + "/.git"));
            return true;
        } catch (IOException ex) {
            // Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        // return false;

    }

    public boolean isRepositoryInitialized(String directory) {

        try {
            assistant = Git.open(new F‌ile(directory + "/.git"));
            return true;
        } catch (IOException ex) {
            // Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        // return false;

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
     * Remove remote repository.
     *
     */
    public void unSetConfigRemote() {

        Config config = assistant.getRepository().getConfig();
        config.unset("remote", "origin", "url");
    }

    /**
     * Return url of the remote repository.
     *
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

    /**
     * Método responsável pela adição de um arquivo na área de seleção do
     * repositório.
     *
     * @param fileName - recebe como parâmetro uma String com o nome do arquivo.
     * @throws GitAPIException - exceção padrão da API Git
     */
    public boolean add(String fileName) throws GitAPIException {

        if (isRepositoryInitialized()) {
            assistant.add().addFilepattern(fileName).call();
            return true;
        }
        return false;
    }

    /**
     * Método responsável pelo commit das mudanças do repositório.
     *
     * @param message - String com a mensagem a ser adicionada ao commit.
     * @return
     */
    public boolean commit(String message) {

        if (isRepositoryInitialized(localPath)) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                RevCommit commit = assistant.commit().setMessage(message)
                        .call();

                System.out.println(commit.getId().getName());
                return true;
            } catch (GitAPIException e) {
                System.out.println(e.getMessage());
            }

        }
        return false;
    }

    public Map<String, Set<String>> status() throws GitAPIException {
        if (isRepositoryInitialized(localPath)) {
            System.err.println("Repository uninitialized");
            return null;
        }
        Map<String, Set<String>> statusList = new HashMap<>();
        Status status = assistant.status().call();
        statusList.put("Added", status.getAdded());
        statusList.put("Changed", status.getChanged());
        statusList.put("Conflicting", status.getConflicting());
        statusList.put("Igonred Not Index", status.getIgnoredNotInIndex());
        statusList.put("Missing", status.getMissing());
        statusList.put("Modified", status.getModified());
        statusList.put("Removed", status.getRemoved());
        statusList.put("Untracked", status.getUntracked());
        statusList.put("UntrackedFolders", status.getUntrackedFolders());
        statusList.put("Uncommitted Changes", status.getUncommittedChanges());
        assistant.close();
        return statusList;

    }

    /**
     * Método responsável pela remoção de arquivos da area de seleção do
     * diretório de trabalho e do repositório.
     *
     * @param filename - recebe uma string com o nome do arquivo a ser removido
     * @return - retorna true se a operação for bem sucedida
     */
    public boolean remove(String filename) {
        File file;

        if (isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
            return false;
        } else {

            file = new File(localPath + "/" + filename);

            if (!file.exists()) {
                System.err.println("Asset does not exist");
            } else {
                try {
                    assistant.rm().addFilepattern(filename).call();
                    return true;
                } catch (GitAPIException e) {
                    System.out.println(e.getMessage());

                }

            }

        }
        return false;

    }
    
    
    /**
     * Retorna os logs dos commits realizados no repositorio.
     * @return
     * @throws GitAPIException 
     */

    public String getLogs() throws GitAPIException {
        String logs = null;
        if (isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
        } else {

            // Repository repository1 = git1.getRepository();
            //ObjectId head = repository1.resolve("HEAD");
            Iterable<RevCommit> log ;

            log = assistant.log().call();
            for (RevCommit rev : log) {
                logs = "Author: " + rev.getAuthorIdent().getName()
                        + "\nMessage: " + rev.getFullMessage();
                return logs;
            }

        }
        return logs;
    }

}
