package net.ledes.hidra.sources;

/**
 * @author Urbieta Souza
 *
 */

import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
//import org.eclipse.jgit.lib.Constants;
//import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;


/**
 * @author Danielli Urbieta e Pedro Souza Junior
 * 
 * 
 *         class Hidra
 */
public class Hidra {

	private Git git;
	private String localPath;
	private String remotePath;
	private Repository localRepository;
	private String diretoryToCopy;
	private Repository repository;
	private Set<String> added;
	private Set<String> removed;
	private Status status;

	
	public Hidra(Git git){
		this.git = git;
	}
	
	
	public Hidra(String localPath, String remotePath) {

		this.localPath = localPath;
		this.remotePath = remotePath;
	}

	public Hidra(String localPath) {
		super();
		this.localPath = localPath;
	}


	public Hidra() {
		super();
	}


	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}


	public String getRemotePath() {
		return this.remotePath;
	}


	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}


	public Repository getLocalRepository() {
		return this.localRepository;
	}


	public void setLocalRepository(Repository localRepository) {
		this.localRepository = localRepository;
	}


	public Git getGit() {
		return this.git;
	}


	public void setGit(Git git) {
		this.git = git;
	}


	public Repository getrepository() {
		return this.repository;
	}


	public void setrepository(Repository repository) {
		this.repository = repository;
	}

	public Set<String> getAdded() {
		return this.added;
	}


	public void setAdded(Set<String> added) {
		this.added = added;
	}

	public Set<String> getRemoved() {
		return removed;
	}


	public void setRemoved(Set<String> removed) {
		this.removed = removed;
	}


	public String getDiretoryToCopy() {
		return diretoryToCopy;
	}


	public void setDiretoryToCopy(String diretoryToCopy) {
		this.diretoryToCopy = diretoryToCopy;
	}

	public Repository getRepository() {
		return git.getRepository();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}