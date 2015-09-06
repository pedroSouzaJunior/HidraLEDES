package ledes.hidra.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ledes.hidra.Repository;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RebaseResult;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.archive.ArchiveFormats;

/**
 * This class is a Facade Pattern to jGit features. Its aim is to simplify
 * access to JGit features in order to achieve repository objectives.
 *
 * @see http://pt.wikipedia.org/wiki/Fa%C3%A7ade
 *
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class GitFacade {

    /**
     * @param Git - Usado para utilizar os comandas JGit api
     */
    private Git assistant;

    private final String localPath;

    private org.eclipse.jgit.lib.Repository repository;

    public GitFacade(String localPath) {
        super();
        this.localPath = localPath;
    }

    /**
     * Metódo responsável para criar ou iniciar um repositório.
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
     * Método responsável por clonar um repositório remoto em um diretório local. Autentificação é requerida com o protocolo https.
     * O diretório local deve estar vazio.
     * 
     * @param directory
     * @param remotePath
     * @param user
     * @param password
     * @return
     * @throws GitAPIException
     */
    public boolean cloneRepository(File directory, String remotePath, String user, String password) throws GitAPIException {

      

        UsernamePasswordCredentialsProvider credential = new UsernamePasswordCredentialsProvider(user, password);
        if (directory.exists() && directory.listFiles().length != 0) {
            System.out.println("Repository not empty , Canceled Operation");
            return false;
        }

        try {
            assistant = Git.cloneRepository().setURI(remotePath).setCredentialsProvider(credential)
                    .setDirectory(directory).call();
            System.out.println("Repository successfully cloned "
                    + assistant.getRepository().getDirectory());
            return true;
        } catch (Exception ex) {
          
            return false;
        } 

    }

    /**
     * Método responsável por clonar um repositório remoto em um diretório local. 
     * O diretório local deve estar vazio.
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
     * Verifica se o diretório é um repositório Git
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

    /**
     * Verifica se o diretório é um repositório Git
     * @param directory
     * @return 
     */
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
     * Recebe nome e email para configurar o conta do usuário do repositório
     * @param name
     * @param email
     *
     */
    public void setConfigurationUser(String name, String email) throws IOException {

        if (isRepositoryInitialized()) {
            StoredConfig config = assistant.getRepository().getConfig();
            config.setString("user", null, "name", name);
            config.setString("user", null, "email", email);
            config.save();

        }

    }

    /**
     * Retornar uma lista de chave valor com dados sobre o usuário.
     * @return 
     */
    public Map<String, String> getConfigurationUser() {

        if (isRepositoryInitialized()) {
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
        return null;
    }

    /**
     * Remove a configuração do usuário
     */
    public void unSetConfigurationUser() {

        if (isRepositoryInitialized()) {
            Config config = assistant.getRepository().getConfig();
            config.unsetSection("user", "email");
            config.unsetSection("user", "name");
        }

    }

    /**
     * Define um novo repositório remoto.
     * Recebe a URL do repositório remoto.
     *
     * @param remoteRepository
     */
    public void setConfigRemote(String remoteRepository) throws IOException {

        if (isRepositoryInitialized()) {
            StoredConfig config = assistant.getRepository().getConfig();
            config.setString("remote", "origin", "url", remoteRepository);
            config.save();


        }
    }

    /**
     * Remove o repositório remoto
     *
     */
    public void unSetConfigRemote() {

        if (isRepositoryInitialized()) {
            Config config = assistant.getRepository().getConfig();
            config.unset("remote", "origin", "url");
        }
    }

    /**
     * Retorna a URL do repositório remoto
     *
     * @return String url
     */
    public String getConfigRemote() {

        if (isRepositoryInitialized()) {
            Config config = assistant.getRepository().getConfig();
            String url = config.getString("remote", "origin", "url");
            assistant.close();
            return url;

        }
        
        return null;
    }

    /**
     * Retorna se há um repositório remoto configurado.
     * @return true, if there are remote repository configured
     */
    public boolean hasRemoteRepository() {

        if (isRepositoryInitialized()) {
            Config config = assistant.getRepository().getConfig();
            String url = config.getString("remote", "origin", "url");
             assistant.close();
            return url != null;
        }
       
        return false;
    }

    /**
     * Método responsável pela adição de um arquivo na área de seleção do
     * repositório.
     *
     * @param fileName - recebe como parâmetro uma String com o nome do arquivo.
     * @return
     * @throws GitAPIException - exceção padrão da API Git
     */
    public boolean add(String fileName) throws GitAPIException {

        if (isRepositoryInitialized()) {
            assistant.add().addFilepattern(fileName).call();
            assistant.close();
            return true;

        }
        return false;
    }

    /**
     * Método responsável pelo commit das mudanças do repositório.
     *
     * @param message - String com a mensagem a ser adicionada ao commit.
     * @return
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     */
    public boolean commit(String message) throws GitAPIException {

        if (isRepositoryInitialized()) {

            System.out.println(assistant.getRepository().getDirectory().getAbsolutePath());
            assistant.commit().setMessage(message)
                    .call();

            assistant.close();
            return true;
        }

        
        return false;
    }

    
    /**
     * Mostra o status do repositório.
     * @return
     * @throws GitAPIException 
     */
    public Map<String, Set<String>> status() throws GitAPIException {
        if (!isRepositoryInitialized()) {
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

        if (!isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
            return false;
        } else {

            file = new File(localPath + File.separator + filename);

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
            assistant.close();

        }
        return false;

    }

    /**
     * Retorna o log de todo repositório.
     *
     * @return
     * @throws GitAPIException
     */
    public String getLogs() throws GitAPIException {

        String logs = null;
        if (!isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
        } else {

            Iterable<RevCommit> log;

            log = assistant.log().call();
            for (RevCommit rev : log) {
                logs = "Author: " + rev.getAuthorIdent().getName()
                        + "\nMessage: " + rev.getFullMessage();
                
            }
            assistant.close();
            return logs;
            
        }
        return logs;
    }

    /**
     * Retorna os logs dos commits realizados em um ativo especifico.
     *
     * @param nameAsset
     * @return
     * @throws GitAPIException
     */
    public String getLogs(String nameAsset) throws GitAPIException {
        String logs = null;

        if (!isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
        } else {

            // Repository repository1 = git1.getRepository();
            //ObjectId head = repository1.resolve("HEAD");
            Iterable<RevCommit> log;

            log = assistant.log().addPath(nameAsset).call();
            for (RevCommit rev : log) {
                logs = "Author: " + rev.getAuthorIdent().getName()
                        + "\nMessage: " + rev.getFullMessage();
                
            }
            assistant.close();
            return logs;

        }
        return logs;
    }

    /**
     * Atualiza o repositorio remoto com as alterações do repositorio local.
     * Recebe como parâmetro o usuário e senha cadastrados no repositorio
     * remoto.
     *
     * @param user
     * @param password
     * @return
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     */
    public boolean push(String user, String password) throws GitAPIException {
        if (isRepositoryInitialized()) {
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(user, password);
            PushCommand pc = assistant.push();
            pc.setCredentialsProvider(cp).setForce(true).setPushAll();
            Iterator<PushResult> it;

            if (!hasRemoteRepository()) {

                System.err.println("Please add a remote repository");
                return false;

            }

            it = pc.call().iterator();
            if (it.hasNext()) {
                System.out.println(it.next().toString());
                return true;
            }

        }
        return false;
    }

    /**
     * Atualiza o repositorio local.
     *
     * @param user
     * @param password
     * @return
     * @throws GitAPIException
     */
    public boolean pull(String user, String password) throws GitAPIException {

        CredentialsProvider credential = new UsernamePasswordCredentialsProvider(user, password);

        if (isRepositoryInitialized()) {
            if (hasRemoteRepository()) {
                PullResult pullResult;

                pullResult = assistant.pull().setCredentialsProvider(credential).call();

                System.out.println(pullResult);

                MergeResult mergeResult = pullResult.getMergeResult();
                if (mergeResult != null) {
                    resolveConflictsMerge(mergeResult);
                }
                RebaseResult rebaseResult = pullResult.getRebaseResult();
                if (rebaseResult != null) {
                    //TODO
                }

                return true;
            } else {
                System.err.println("Please add a remote repository");
            }

        }
        return false;
    }

    /**
     * Trata os conflitos gerados pelo merge de dois repositorios ou branches.
     *
     * @param conflict
     */
    public void resolveConflictsMerge(MergeResult conflict) {

        switch (conflict.getMergeStatus()) {
            case CONFLICTING:
                System.out.println("CONFLICT (content): Merge conflict in: adicionar arquivo que tem conflito"
                        + "Automatic merge failed; fix conflicts and then commit the result.");
                System.out.println(conflict.getConflicts());
                break;
            case FAILED:
                System.out.println("FAILED: " + conflict.getFailingPaths());
                break;
            case ABORTED:
                System.out.println("ABORTED: ");
                break;
            case ALREADY_UP_TO_DATE:
                System.out.println("ALREADY UP TO DATE: ");
                break;
            case CHECKOUT_CONFLICT:
                System.out.println("CHECKOUT_CONFLICT: meaning that nothing could be merged, "
                        + "as the pre-scan for the trees already failed for certain files");
                break;
            default:
                System.out.println("Unsuccessfully");

        }

    }

    /**
     * Realiza a junção de dois repositórios ou dois branches.
     *
     * @param branch
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public boolean merge(String branch) throws IOException, GitAPIException {
        MergeCommand mgCmd = assistant.merge();

        mgCmd.include(assistant.getRepository().getRef(branch));
        MergeResult res = mgCmd.call();
        System.out.println(res.getMergeStatus());

        if (!res.getMergeStatus().isSuccessful()) {
            resolveConflictsMerge(res);
            return false;
        } else {
            return true;
        }

    }

    /**
     * Troca de branch.
     *
     * @param branch
     * @return
     */
    public boolean checkout(String branch) {

        if (isRepositoryInitialized()) {
            try {
                assistant.checkout().setName(branch).call();

                return true;
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);

                return false;

            }

        }
        return false;
    }

    /**
     * Troca e cria um novo branch no repositorio. Recebe o nome do novo branch
     * a ser criado.
     *
     * @param branch
     * @return
     */
    public boolean checkoutCreateBranch(String branch) {

        if (isRepositoryInitialized()) {
            try {
                assistant.checkout().setCreateBranch(true).setName(branch).call();
                return true;
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        return false;
    }

    /**
     * Cria uma tag simples. Recebe o nome da tag e a mensagem a ser salva.
     *
     * @param tagName
     * @param tagMsg
     * @return
     */
    public boolean createLightTag(String tagName, String tagMsg) {

        if (isRepositoryInitialized()) {
            try {
                assistant.tag().setName(tagName).setMessage(tagMsg).call();
                return true;
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    /**
     * Cria uma tag com anotações e mais informações.
     *
     * @param tagName
     * @param tagMgs
     * @return
     */
    public boolean createAnnotatedTag(String tagName, String tagMgs) {

        if (isRepositoryInitialized()) {
            try {
                assistant.tag().setName(tagName).setAnnotated(true).setMessage(tagName).call();
                return true;
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Lista todas as tags criadas.
     *
     * @return
     */
    public String listTags() {

        String tags = null;
        if (isRepositoryInitialized()) {

            try {
                for (org.eclipse.jgit.lib.Ref ref : assistant.tagList().call()) {

                    tags = "\n " + ref.getName();

                }

                return tags;
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tags;
    }

    public boolean tagDelete(String nameTags) {
        if (isRepositoryInitialized()) {
            try {
                assistant.tagDelete().setTags(nameTags).call();
                return true;
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Concluir
     *
     * @param nameTag
     * @return
     */
    public boolean showTags(String nameTag) {

        if (isRepositoryInitialized()) {
            System.out.println(assistant.tag().getMessage());

            return true;
        }
        return false;

    }

    public boolean showBranches() {
        if (!isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                System.out.println("Branches: ");
                for (org.eclipse.jgit.lib.Ref ref : assistant.branchList().call()) {
                    System.out.println(ref.getName());

                }
                System.out.println("Current Branch: " + assistant.getRepository().getBranch());
                return true;
            } catch (GitAPIException | IOException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        assistant.getRepository().close();
        return false;
    }

    public String createBranch(String nameBranch) {
        String branch = null;
        if (!isRepositoryInitialized()) {
            System.err.println("Repositorio nao inicializado");
        } else {

            try {
                assistant.branchCreate().setName(nameBranch).call();
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<org.eclipse.jgit.lib.Ref> call = null;

            try {
                call = new Git(assistant.getRepository()).branchList()
                        .call();
            } catch (GitAPIException ex) {
                Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (org.eclipse.jgit.lib.Ref ref : call) {
                branch = "Branch Created: " + " " + ref.getName();

            }
            assistant.getRepository().close();

            return branch;
        }
        return branch;
    }

    /**
     * Download de todo repositorio em formato zip.
     *
     * @param format
     * @param branch
     * @param fileDest
     * @return
     * @throws FileNotFoundException
     * @throws IncorrectObjectTypeException
     * @throws RevisionSyntaxException
     * @throws IOException
     * @throws GitAPIException
     */
    public boolean archive(String format, String branch, String fileDest) throws FileNotFoundException, IncorrectObjectTypeException, RevisionSyntaxException, IOException, GitAPIException {
        File file = new File(fileDest);
        OutputStream out = new FileOutputStream(file);
        ArchiveFormats.registerAll();
        if (isRepositoryInitialized()) {

            assistant.archive().setTree(assistant.getRepository().resolve(branch))
                    .setFormat(format)
                    .setOutputStream(out)
                    .call();
            return true;
        }
        return false;
    }

    //git archive --remote=git@github.com:foo/bar.git --prefix=path/to/ HEAD:path/to/ |  tar xvf -
    public boolean archive(String format, String branch, String fileDest, String prefix) throws FileNotFoundException, IncorrectObjectTypeException, RevisionSyntaxException, IOException, GitAPIException {

        File file = File.createTempFile("test", fileDest);
        OutputStream out = new FileOutputStream(fileDest);
        if (isRepositoryInitialized()) {

            assistant.archive().setTree(assistant.getRepository().resolve(branch))
                    .setPrefix(prefix)
                    .setFormat(format)
                    .setOutputStream(out)
                    .call();
        }
        return false;
    }
}
