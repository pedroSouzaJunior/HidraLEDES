package net.ledes.hidra.services;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.ledes.hidra.sources.Command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

@WebService(serviceName = "HidraControl/Command")
public class HidraServices implements IHidra{

	Command commands = new Command();

	@WebMethod
	public boolean start(@WebParam(name = "localPath") String localPath) {
		boolean ret = false;
		File directory = new File(localPath);

		try {
			Git auxiliary = Git.init().setDirectory(directory).call();
			commands.inicializar(directory, auxiliary);
			ret = true;
		} catch (GitAPIException e) {
			System.out.println(e.getMessage());
		}

		return ret;
	}

	@WebMethod
        @Override
	public boolean addOn(@WebParam(name = "fileName") String fileName) {
		boolean ret = false;

		try {
			ret = commands.adicionar(fileName);

		} catch (Exception e) {
			System.err.println("Error adding file");
		}
		return ret;

	}

	@WebMethod
        @Override
	public boolean remove(@WebParam(name = "filename") String filename) {
		boolean ret = false;

		try {
			commands.remove(filename);
			ret = true;
		} catch (Exception e) {
			System.err.println("Error deleting file");
		}
		return ret;

	}

	@WebMethod
        @Override
	public boolean commit(@WebParam(name = "message") String message) {
		boolean ret = false;

		try {
			commands.commit(message);
			ret = true;
		} catch (Exception e) {
			System.err.println("Error during commit process");
		}
		return ret;

	}

	@WebMethod
	public boolean clone(@WebParam(name = "remotePath") String remotePath,
			@WebParam(name = "localPath") String localPath) {
		boolean ret = false;
		File directory = new File(localPath);

		try {
			ret = commands.cloneW(remotePath, directory);
		} catch (IOException e) {
			System.err.println("Error during cloning process");
		} catch (GitAPIException e) {
			System.err.println("Error during cloning process");
		}
		return ret;
	}

	@WebMethod
	public boolean status() {
		boolean ret = false;

		try {
			System.out.println(commands.status());
			ret = true;
		} catch (Exception e) {

		}
		return ret;
	}

	@WebMethod
        @Override
	public boolean logs() {
		boolean ret = false;

		try {
			System.out.println(commands.getLogs());
			ret = true;
		} catch (Exception e) {
			System.err.println("Error during Logs process");
		}
		return ret;

	}

	@WebMethod
        @Override
	public boolean showBranches() {
		boolean ret = false;

		try {
			commands.showBranches();
			ret = true;
		} catch (Exception e) {
			System.err.println("Error during Show Branches process");
		}

		return ret;

	}

	@WebMethod
        @Override
	public boolean createBranch(@WebParam(name = "nameBranch") String nameBranch) {
		boolean ret = false;

		try {
			System.out.println(commands.createBranch(nameBranch));
			ret = true;
		} catch (Exception e) {
			System.err.println("Error During branch Creation Process");
		}
		return ret;
	}

	@WebMethod
	public boolean update() {
		boolean ret = false;

		try {
			commands.pull();
			ret = true;
		} catch (Exception e) {
			System.err.println("Error During Upgrade Process");
		}

		return ret;
	}

	@WebMethod
        @Override
	public boolean send(String user, String password) {
		boolean ret = false;

		try {
			commands.push(user,password);
			
			ret = true;
		} catch (Exception e) {
			System.err
					.println("Error during process of sending to remote repository");
		}
		return ret;
	}

	@WebMethod
	public boolean diff(@WebParam(name = "BranchOne") String BranchOne,
			@WebParam(name = "BranchTwo") String BranchTwo) {
		boolean ret = false;
		
		try {
			commands.getDiff(BranchOne, BranchTwo);
		} catch (Exception e) {
			System.err.println("Error during Diff process");
		}
		return ret;
	}
        
        @WebMethod
        @Override
        public void getConfigUser(){
            commands.getConfigurationUser();
        }
        
        @WebMethod
        @Override
        public void getConfigRemoteRepository(){
            commands.getConfigRemote();
        }
        
        @WebMethod
        @Override
        public boolean setConfigUser(@WebParam(name ="name") String name, @WebParam(name = "email") String email){
            return commands.setConfigurationUser(name, email);
         }

    @Override
    @WebMethod
    public boolean setConfigRemoteRepo(@WebParam(name = "url")String url) {
      return commands.setConfigRemote(url);
    }
    
    @Override
    @WebMethod
    public boolean hasRemoteRepository(){
        return commands.hasRemoteRepository();
    
    }

    @Override
    @WebMethod
    public boolean unSetConfigUser() {
        return commands.unSetConfigurationUser();
    }

    @Override
    @WebMethod
    public boolean unSetConfigEmail() {
        return commands.unSetConfigurationEmail();
    }

    @Override
    @WebMethod
    public boolean unSetConfigRemoteRepo() {
        return commands.unSetConfigRemote(); 
    }
    

    @Override
    @WebMethod
    public boolean mergeWithoutConflicts(@WebParam (name="branch") String branch){
    
    
            try {
                return commands.merge(branch);
            } catch (IOException ex) {
                Logger.getLogger(HidraServices.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GitAPIException ex) {
                Logger.getLogger(HidraServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return false;
    }

    @Override
    public boolean checkout(@WebParam (name="branch")String branch){
        return commands.checkout(branch);
    
    }

    @Override
    public boolean createLightTag(String nameTag, String msgTag) {
       return commands.createLightTag(nameTag, msgTag);
    }

    @Override
    public boolean createAnnotatedTag(String nameTag, String msgTag) {
       return commands.createAnnotatedTag(nameTag, msgTag);
    }

    @Override
    public boolean deleteTags(String nameTag) {
        return commands.tagDelete(nameTag);
    }

    @Override
    public boolean listTags() {
       return commands.listTags();
    }
    
    
}
