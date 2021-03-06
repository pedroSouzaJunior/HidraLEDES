package ledes.hidra.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ledes.hidra.Repository;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.archive.ArchiveFormats;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.transport.URIish;

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
     * Metódo responsável por criar ou iniciar um repositório. Recebe como
     * parâmetro o diretório no o repositório se encontra.
     *
     * @param directory - diretorio do repositório.
     * @return - true caso operação ocorra com sucesso, false caso contrário.
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
     * Metódo responsável por criar ou iniciar um repositório "bare". Recebe como
     * parâmetro o diretório no o repositório se encontra.
     *
     * @param directory - diretorio do repositório.
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public final boolean startBare(File directory) {

        if (!isRepositoryInitialized()) {
            try {
                assistant = Git.init().setDirectory(directory).setBare(true).call();
                return true;

            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }
        return true;

    }

    /**
     * Método responsável por clonar um repositório remoto em um diretório
     * local. Autentificação é requerida com o protocolo https. O diretório
     * local deve estar vazio.
     *
     * @param directory - diretório aonde se deseja clonar o repositório.
     * @param remotePath - caminho do repositório remoto.
     * @param user - usuário para autenticação no repositório remoto.
     * @param password - senha para autenticação no repositório remoto.
     * @return - true caso operação ocorra com sucesso, false caso contrário.
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
            System.out.println("Repository successfully syncroni");
            return true;
        } catch (Exception ex) {

            return false;
        }

    }

    /**
     * Método responsável por clonar um repositório remoto em um diretório
     * local. O diretório local deve estar vazio.
     *
     * @param directory - diretório aonde se deseja fazer o clone.
     * @param remotePath - caminho do repositório remoto.
     * @return true caso a operação ocorra com sucesso, false caso contrário.
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
     * Verifica se o diretório é um repositório inicializado
     *
     * @return true se repositório ja é inicializado, false caso contrário.
     */
    public boolean isRepositoryInitialized() {

        try {
            assistant = Git.open(new F‌ile(localPath + "/.git"));
            return true;
        } catch (IOException ex) {
            // Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Verifica se o diretório é um repositório inicializado Recebe como
     * parâmetro o caminho absoluto do repositório.
     *
     * @param directory
     * @return true se repositório ja é inicializado, false caso contrário.
     */
    public boolean isRepositoryInitialized(String directory) {

        try {
            assistant = Git.open(new F‌ile(directory + "/.git"));
            return true;
        } catch (IOException ex) {
            // Logger.getLogger(GitFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Recebe nome e email para configurar o conta do usuário do repositório
     *
     * @param name
     * @param email
     * @throws java.io.IOException
     *
     */
    public void setConfigurationUser(String name, String email) throws IOException {

        if (isRepositoryInitialized()) {
            StoredConfig config = assistant.getRepository().getConfig();

            config.setString("email", null, "email", email);
            config.setString("user", null, "name", name);
            config.save();

        }

    }

    /**
     * Retornar uma lista de chave valor com dados sobre o usuário.
     *
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
     * Define um novo repositório remoto. Recebe a URL do repositório remoto.
     *
     * @param remoteRepository
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public void setConfigRemote(String remoteRepository) throws IOException, URISyntaxException {

        if (isRepositoryInitialized()) {
            StoredConfig config = assistant.getRepository().getConfig();
            RemoteConfig remoteConfig = new RemoteConfig(config, "origin");
            URIish uri = new URIish(remoteRepository);
            remoteConfig.addURI(uri);
            remoteConfig.update(config);
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

            StringBuilder url = new StringBuilder();
                    //config.getString("remote", "origin", "url");

            Set<String> remotes = config.getSubsections("remote");

            for (String remoteName : remotes) {
                url.append("Remote: ").append(remoteName).append(" ").append(config.getString("remote", remoteName, "url")).append("\n");
              //  System.out.println(remoteName + " " + url);
            }

            assistant.close();
            return url.toString();

        }

        return null;
    }

    /**
     * Retorna se há um repositório remoto configurado.
     *
     * @return true, se existe um repositório remoto, false caso contrário.
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
     * @return true caso operação ocorra com sucesso, false caso contrário.
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
     * @param message - mensagem informando as alteração a serem gravadas no
     * repositório.
     * @return true caso operação ocorra com sucesso, false caso contrário.
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
     * Método responsável por obter informações referentes ao status do
     * repositório.
     *
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
     * @return - true caso operação ocorra com sucesso, false caso contrário.
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
     * @return informações de Log do repositório.
     * @throws GitAPIException
     */
    public String getLogs() throws GitAPIException {

        StringBuilder logs = new StringBuilder();
        if (!isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
        } else {

            Iterable<RevCommit> log;

            log = assistant.log().call();
            for (RevCommit rev : log) {
                logs.append("\nAuthor: ").append(rev.getAuthorIdent().getName()).append("\nMessage: ").append(rev.getFullMessage());

            }
            assistant.close();
            return logs.toString();

        }
        return logs.toString();
    }

    /**
     * Retorna os logs dos commits realizados em um ativo especifico.
     *
     * @param nameAsset - nome do ativo.
     * @return informações relativas ao log de um ativo.
     * @throws GitAPIException
     */
    public String getLogs(String nameAsset) throws GitAPIException {
        StringBuilder logs = new StringBuilder();

        if (!isRepositoryInitialized()) {
            System.err.println("Repository uninitialized");
        } else {
            Iterable<RevCommit> log;

            log = assistant.log().addPath(nameAsset).call();
            for (RevCommit rev : log) {
                logs.append("\nAuthor: ")
                        .append(rev.getAuthorIdent()
                                .getName())
                        .append("\nMessage: ")
                        .append(rev.getFullMessage());

            }
            assistant.close();
            return logs.toString();

        }
        return null;
    }

    /**
     * Atualiza o repositorio remoto com as alterações do repositorio local.
     * Recebe como parâmetro o usuário e senha para autenticação no repositório.
     * remoto.
     *
     * @param user - usuário.
     * @param password - senha.
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     */
    public boolean push(String user, String password) throws GitAPIException, IOException {
        if (isRepositoryInitialized()) {
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(user, password);
            PushCommand pc = assistant.push();
            pc.setCredentialsProvider(cp).setPushAll();
            Iterator<PushResult> it;

            if (!hasRemoteRepository()) {

                System.err.println("Please add a remote repository");
                return false;
            }
            Iterable<PushResult> results = pc.call();
            for (PushResult result : results) {
                ObjectReader reader = assistant.getRepository().newObjectReader();
                try {
                    printPushResult(reader, result.getURI(), result);
                } finally {
                    reader.release();
                }
            }

        }
        return false;
    }

    private void printPushResult(final ObjectReader reader, final URIish uri,
            final PushResult result) throws IOException {

        for (final RemoteRefUpdate rru : result.getRemoteUpdates()) {

            if (rru.getStatus() == org.eclipse.jgit.transport.RemoteRefUpdate.Status.OK) {
                printRefUpdateResult(reader, uri, result, rru);
            }
        }

        for (final RemoteRefUpdate rru : result.getRemoteUpdates()) {

            if (rru.getStatus() != org.eclipse.jgit.transport.RemoteRefUpdate.Status.OK
                    && rru.getStatus() != org.eclipse.jgit.transport.RemoteRefUpdate.Status.UP_TO_DATE) {
                printRefUpdateResult(reader, uri, result, rru);
            }
        }

    }

    private void printRefUpdateResult(final ObjectReader reader,
            final URIish uri, final PushResult result, final RemoteRefUpdate rru)
            throws IOException {

        switch (rru.getStatus()) {
            case OK:
                System.out.println("Remote was successfully updated");
                break;

            case NON_EXISTING:
                System.out.println("Remote ref didn't exist. Can occur on delete request of a non existing branch.");
                break;

            case REJECTED_NODELETE:
                System.out.println("REJECTED_NODELETE");
                break;

            case REJECTED_NONFASTFORWARD:
                System.out.println(" Non-fast forward updates were rejected, try receive-updates and after send-receives");
                break;

            case REJECTED_REMOTE_CHANGED:
                System.out.println("REJECTED_REMOTE_CHANGED: " + rru.getMessage());
                break;

            case REJECTED_OTHER_REASON:
                System.out.println("REJECTED_OTHER_REASON: " + rru.getMessage());
                break;

            case UP_TO_DATE:
                System.out.println("Already up-to-date");
                break;

            case NOT_ATTEMPTED:
            case AWAITING_REPORT:
                System.out.println("AWAITING_REPORT: " + rru.getMessage());
                break;
        }
    }

    /**
     * Atualiza o repositorio local com alterações ocorridas no repositório
     * remoto. Utiliza de usuário e senha para autenticação no repositório.
     *
     * @param user - usuário
     * @param password - senha
     * @return true caso operação ocorra com sucesso, false caso contrário.
     * @throws GitAPIException
     */
    public boolean pull(String user, String password) throws GitAPIException {

        CredentialsProvider credential = new UsernamePasswordCredentialsProvider(user, password);
        
        if (isRepositoryInitialized()) {
            if (hasRemoteRepository()) {
                PullResult pullResult;

                pullResult = assistant.pull().setCredentialsProvider(credential).call();

                MergeResult mergeResult = pullResult.getMergeResult();
                if (mergeResult != null) {
                    resolveConflictsMerge(mergeResult);
                    return false;
                }
//                RebaseResult rebaseResult = pullResult.getRebaseResult();
//                if (rebaseResult != null) {
//                    //TODO
//                }
                return true;
            } else {
                System.err.println("Please add a remote repository");
            }
        }
        return false;
    }

    
    /**
     * Informa conflitos gerados pelo merge de dois repositorios ou branches.
     *
     * @param conflict
     */
    public void resolveConflictsMerge(MergeResult conflict) {

        switch (conflict.getMergeStatus()) {
            case CONFLICTING:
                System.out.println("CONFLICT (content): Merge conflict in: adicionar arquivo que tem conflito"
                        + "\nAutomatic merge failed; fix conflicts and then commit the result.");

                // System.out.println(conflict.getConflicts());
                break;
            case FAILED:
                System.out.println("FAILED: " + conflict.getFailingPaths());
                break;
            case ABORTED:
                System.out.println("ABORTED: ");
                break;
            case ALREADY_UP_TO_DATE:
                System.out.println("ALREADY UP TO DATE ");
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
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     * @throws IOException
     * @throws GitAPIException
     */
    public boolean merge(String branch) throws IOException, GitAPIException {

        if (!isRepositoryInitialized()) {
            System.err.println("Repository Uninitialized");
        } else {
            MergeResult res = assistant.merge().include(assistant.getRepository().getRef(branch)).call();
            if (!res.getMergeStatus().isSuccessful()) {
                resolveConflictsMerge(res);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Troca do branch atual para um novo branch.
     *
     * @param branch
     * @return - true caso operação ocorra com sucesso, false caso contrário.
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
     * @return - true caso operação ocorra com sucesso, false caso contrário.
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
     * Trabalho futuro.
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

    /**
     * Mostra o branch atual de trabalho e todos os branches criados no
     * repositório.
     *
     * @return
     */
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

    /**
     * Apaga branches
     *
     * @param branchNames
     * @return
     * @throws GitAPIException
     */
    public boolean deleteBranch(String[] branchNames) throws GitAPIException {

        if (isRepositoryInitialized()) {
            System.err.println("Repository Uninitialized");
            return false;
        } else {
            assistant.branchDelete().setBranchNames(branchNames).call();
            return true;
        }
    }

    /**
     * Cria um novo branch.
     *
     * @param nameBranch
     * @return
     */
    public String createBranch(String nameBranch) {
        String branch = null;
        if (!isRepositoryInitialized()) {
            System.err.println("Repository Uninitialized");
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
    public boolean archive(String format, String branch, String fileDest) throws
            FileNotFoundException,
            IncorrectObjectTypeException,
            RevisionSyntaxException,
            IOException,
            GitAPIException {

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

    public boolean archive(String format, String branch, String fileDest, String prefix) throws
            FileNotFoundException,
            IncorrectObjectTypeException,
            RevisionSyntaxException,
            IOException,
            GitAPIException {

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
