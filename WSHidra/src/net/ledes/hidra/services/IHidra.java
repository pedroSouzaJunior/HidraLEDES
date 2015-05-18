package net.ledes.hidra.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface IHidra {

    @WebMethod
    public boolean start(@WebParam(name = "localPath") String localPath);
    
    @WebMethod
    public boolean addOn(@WebParam(name = "fileName") String fileName);
    
    @WebMethod
    public boolean remove(@WebParam(name = "filename") String filename);
    
    @WebMethod
    public boolean commit(@WebParam(name = "message") String message);
    
    @WebMethod
    public boolean clone(@WebParam(name = "remotePath") String remotePath, @WebParam(name = "localPath") String localPath);
    
    @WebMethod
    public boolean status();
    
    @WebMethod
    public boolean logs();
    
    @WebMethod
    public boolean showBranches();
    
    @WebMethod
    public boolean createBranch(@WebParam(name = "nameBranch")String nameBranch);
    
    @WebMethod
    public void getConfigUser();
    
    @WebMethod
    public void getConfigRemoteRepository();
    
    @WebMethod
    public boolean setConfigUser(@WebParam(name ="name") String name, @WebParam(name = "email") String email);
    
    @WebMethod
    public boolean setConfigRemoteRepo(@WebParam(name = "url")String url);
    
    @WebMethod
    public boolean unSetConfigUser();
    
    @WebMethod
    public boolean unSetConfigEmail();
    
    @WebMethod
    public boolean unSetConfigRemoteRepo();
    
    @WebMethod
    public boolean send(@WebParam (name="user")String user, @WebParam(name="password")String password);
    
    @WebMethod
    public boolean hasRemoteRepository();
    
    @WebMethod
    public boolean mergeWithoutConflicts(@WebParam (name="branch") String branch);
    
    @WebMethod 
    public boolean checkout(@WebParam (name ="branch") String branch);
    
    @WebMethod
    public boolean createLightTag(@WebParam(name = "nameTag") String nameTag, @WebParam(name = "msgTag") String msgTag);
    
    @WebMethod
    public boolean createAnnotatedTag(@WebParam(name = "nameTag") String nameTag, @WebParam(name = "msgTag") String msgTag);
    
    @WebMethod
    public boolean deleteTags(@WebParam (name = "nameTag") String nameTag);
    
    @WebMethod
    public boolean listTags();
}
