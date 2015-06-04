package net.ledes.hidra.sources;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RebaseResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class Command {

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    public void setHidra(Hidra hidra) {
        this.hidra = hidra;
    }

    public void inicializar(File directory, Git Auxiliary) {

        hidra = new Hidra(Auxiliary);
        hidra.setLocalPath(directory.getAbsolutePath());

    }

    public boolean adicionar(String fileName) {
        File file;
        String extension[];

        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            file = new File(hidra.getLocalPath() + "/" + fileName);
            if (!file.exists()) {
                System.err.println("file does not exist");
                return false;
            } else {
                extension = fileName.split("\\.");
                if (!extension[1].equals("txt")) {
                    System.err.println("Incorrect file type: " + extension[1]);
                    return false;
                } else {
                    try {
                        hidra.getGit().add().addFilepattern(fileName).call();
                        hidra.setAdded(hidra.getGit().status().call()
                                .getAdded());
                    } catch (GitAPIException e) {
                        System.out.println(e.getMessage());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(String filename) {
        File file;

        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {

            file = new File(hidra.getLocalPath() + "/" + filename);

            if (!file.exists()) {
                System.err.println("file does not exist");
            } else {
                try {
                    hidra.getGit().rm().addFilepattern(filename).call();
                    hidra.setRemoved(this.hidra.getGit().status().call()
                            .getUntracked());
                } catch (GitAPIException e) {
                    System.out.println(e.getMessage());
                }

                if (this.hidra.getRemoved() != null) {
                    System.out.println("Successfully excluded file");
                }
            }
            return true;
        }
        return false;
    }

    public boolean commit(String message) {

        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                RevCommit commit = hidra.getGit().commit().setMessage(message)
                        .call();
                System.out.println(commit.getId().getName());
            } catch (GitAPIException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    public boolean cloneW(String remotePath, File directory)
            throws IOException, InvalidRemoteException, TransportException,
            GitAPIException {

        if (directory.exists() && directory.listFiles().length != 0) {
            System.out.println("Repository not empty , Canceled Operation");
            return false;
        } else {
            Git result = Git.cloneRepository().setURI(remotePath)
                    .setDirectory(directory).call();

            try {
                System.out.println("Repository successfully cloned "
                        + result.getRepository().getDirectory());
            } finally {
                result.close();
            }
            return true;
        }
    }

    public String status() {
        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                hidra.setStatus(hidra.getGit().status().call());
                String showStatus = "Added: "
                        + this.hidra.getStatus().getAdded() + "\nChanged"
                        + this.hidra.getStatus().getChanged()
                        + "\nConflicting: "
                        + this.hidra.getStatus().getConflicting()
                        + "\nConflictingStageState: "
                        + this.hidra.getStatus().getConflictingStageState()
                        + "\nIgnoredNotInIndex: "
                        + this.hidra.getStatus().getIgnoredNotInIndex()
                        + "\nMissing: " + this.hidra.getStatus().getMissing()
                        + "\nModified: " + this.hidra.getStatus().getModified()
                        + "\nRemoved: " + this.hidra.getStatus().getRemoved()
                        + "\nUntracked: "
                        + this.hidra.getStatus().getUntracked()
                        + "\nUntrackedFolders: "
                        + this.hidra.getStatus().getUntrackedFolders()
                        + "\nUncommitted Changes"
                        + this.hidra.getStatus().getUncommittedChanges();

                return showStatus;
            } catch (NoWorkTreeException e) {
                e.printStackTrace();
            } catch (GitAPIException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getLogs() {
        String logs = null;
        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {

            // Repository repository1 = git1.getRepository();
            //ObjectId head = repository1.resolve("HEAD");
            Iterable<RevCommit> log = null;
            try {
                log = hidra.getGit().log().call();
            } catch (NoHeadException e) {
                e.printStackTrace();
            } catch (GitAPIException e) {
                e.printStackTrace();
            }

            @SuppressWarnings("rawtypes")
            Iterator itr = log.iterator();

            while (itr.hasNext()) {
                // Object element = itr.next();
                RevCommit rev = (RevCommit) itr.next();
                // System.out.println(element);
                logs = "Author: " + rev.getAuthorIdent().getName()
                        + "\nMessage: " + rev.getFullMessage();

                return logs;
            }

        }
        return logs;
    }

    public boolean showBranches() {
        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                System.out.println("Branches: ");
                for (org.eclipse.jgit.lib.Ref ref : hidra.getGit().branchList().call()) {
                    System.out.println(ref.getName());

                }
                System.out.println("Current Branch: " + hidra.getGit().getRepository().getBranch());
                return true;
            } catch (GitAPIException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        hidra.getGit().getRepository().close();
        return false;
    }

    public String createBranch(String nameBranch) {
        String branch = null;
        if (hidra == null) {
            System.err.println("Repositorio nao inicializado");
        } else {

            try {
                hidra.getGit().branchCreate().setName(nameBranch).call();
            } catch (GitAPIException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<org.eclipse.jgit.lib.Ref> call = null;

            try {
                call = new Git(hidra.getGit().getRepository()).branchList()
                        .call();
            } catch (GitAPIException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (org.eclipse.jgit.lib.Ref ref : call) {
                branch = "Branch Created: " + " " + ref.getName();

            }
            hidra.getGit().getRepository().close();

            return branch;
        }
        return branch;
    }

    public void pull() {

        PullResult pullResult = null;
        try {
            pullResult = hidra.getGit().pull().call();
        } catch (NoHeadException e) {

            e.printStackTrace();
        } catch (TransportException e) {

            e.printStackTrace();
        } catch (GitAPIException e) {

            e.printStackTrace();
        }
        System.out.println(pullResult);

        MergeResult mergeResult = pullResult.getMergeResult();
        if (mergeResult != null) {
            //TODO     
        }
        RebaseResult rebaseResult = pullResult.getRebaseResult();
        if (rebaseResult != null) {
            //TODO
        }

    }

    public boolean push(String user, String password) {

        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(user, password);
        PushCommand pc = hidra.getGit().push();
        pc.setCredentialsProvider(cp).setForce(true).setPushAll();
        Iterator<PushResult> it = null;

        if (!hasRemoteRepository()) {

            System.err.println("Please add a remote repository");
            return false;

        }

        try {
            it = pc.call().iterator();
            if (it.hasNext()) {
                System.out.println(it.next().toString());
                return true;
            }
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void getDiff(String branch1, String branch2) {

        Git gitDiff = hidra.getGit();
        Repository repo = gitDiff.getRepository();
        AbstractTreeIterator oldTreeParser = prepareTreeParser(repo,
                "refs/heads/" + branch1);
        AbstractTreeIterator newTreeParser = prepareTreeParser(repo,
                "refs/heads/" + branch2);

        List<DiffEntry> diff;
        
        try {
            diff = new Git(repo).diff().setOldTree(oldTreeParser)
                    .setNewTree(newTreeParser).call();
             for (DiffEntry entry : diff) {
            System.out.println("Entry: " + entry);
             }
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
       

        repo.close();
    }

    public AbstractTreeIterator prepareTreeParser(Repository repository,
            String ref) {

        Ref head = null;
        RevWalk walk = new RevWalk(repository);
        RevCommit commit = null;

        
        try {
            head = repository.getRef(ref);
             commit = walk.parseCommit(head.getObjectId());
        } catch (IOException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }     
                

        RevTree tree = null;

        try {
            tree = walk.parseTree(commit.getTree().getId());
        } catch (MissingObjectException e) {
            e.printStackTrace();
        } catch (IncorrectObjectTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
        ObjectReader oldReader = repository.newObjectReader();

        try {
            try {
                oldTreeParser.reset(oldReader, tree.getId());
            } catch (IncorrectObjectTypeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            oldReader.release();
        }
        return oldTreeParser;
    }

    public boolean setConfigurationUser(String name, String email) {

        try {
            Config config = hidra.getRepository().getConfig();
            config.setString("user", null, "name", name);
            config.setString("user", null, "email", email);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;

    }

    public void getConfigurationUser() {
        Config config = hidra.getRepository().getConfig();
        String name = config.getString("user", null, "name");
        String email = config.getString("user", null, "email");
        if (name == null || email == null) {
            System.out.println("User identity is unknown!");
        } else {
            System.out.println("User identity is " + name + " <" + email + ">");
        }

    }

    public boolean unSetConfigurationUser() {

        try {
            Config config = hidra.getRepository().getConfig();
            config.unsetSection("user", "email");
            config.unsetSection("user", "name");
            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public boolean unSetConfigurationEmail() {

        try {
            Config config = hidra.getRepository().getConfig();
            config.unset("user", null, "email");
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean setConfigRemote(String remoteRepository) {
        try {
            Config config = hidra.getRepository().getConfig();
            config.setString("remote", "origin", "url", remoteRepository);
            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public boolean unSetConfigRemote() {
        try {
            Config config = hidra.getRepository().getConfig();
            config.unset("remote", "origin", "url");
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public void getConfigRemote() {

        Config config = hidra.getRepository().getConfig();
        String url = config.getString("remote", "origin", "url");
        if (url != null) {
            System.out.println("Origin comes from " + url);
        }
    }

    public boolean hasRemoteRepository() {

        Config config = hidra.getRepository().getConfig();
        String url = config.getString("remote", "origin", "url");
        return url != null;
    }
    
    public void resolveConflictsMerge(MergeResult conflict){
        
        switch(conflict.getMergeStatus()){
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

    public boolean merge(String branch) throws IOException, GitAPIException {
        MergeCommand mgCmd = hidra.getGit().merge();

        mgCmd.include(hidra.getGit().getRepository().getRef(branch));
        MergeResult res = mgCmd.call();
        System.out.println(res.getMergeStatus());
        
        if(!res.getMergeStatus().isSuccessful()){
            resolveConflictsMerge(res);
            return false;
        }
        else
            return true;

//        if (res.getMergeStatus().equals(MergeResult.MergeStatus.CONFLICTING)) {
//            System.out.println(res.getConflicts().toString());
//            return false;
//        } else if (res.getMergeStatus().equals(MergeResult.MergeStatus.FAILED)) {
//            System.out.println(res.getFailingPaths());
//
//            return false;
//        }
        

    }

    public boolean checkout(String branch) {

        try {
            hidra.getGit().checkout().setName(branch).call();

            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        }
    }

    public boolean checkoutCreateBranch(String branch) {

        try {
            hidra.getGit().checkout().setCreateBranch(true).setName(branch).call();
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public boolean createLightTag(String tagName, String tagMsg) {

        try {
            hidra.getGit().tag().setName(tagName).setMessage(tagMsg).call();
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean createAnnotatedTag(String tagName, String tagMgs) {

        try {
            hidra.getGit().tag().setName(tagName).setAnnotated(true).setMessage(tagName).call();
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean listTags() {
        try {
            for (org.eclipse.jgit.lib.Ref ref : hidra.getGit().tagList().call()) {

                System.out.println(ref.getName());

            }

            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean tagDelete(String nameTags) {
        try {
            hidra.getGit().tagDelete().setTags(nameTags).call();
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean showTags(String nameTag) {

        System.out.println(hidra.getGit().getRepository().getTags());

        return true;

    }

    

}
