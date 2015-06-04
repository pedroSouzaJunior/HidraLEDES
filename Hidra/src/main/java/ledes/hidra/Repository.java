package ledes.hidra;

import java.io.File;
import java.util.List;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;

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

    boolean addAsset(Asset asset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    SolutionType getSolution(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setSolutionType(String assetId, SolutionType solution) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean validateAsset(Asset asset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ClassificationType getClassification(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setClassification(ClassificationType classification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    UsageType getUsage(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setUsage(UsageType usage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Asset> getRelatedAssets(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setRelatedAsset(String assetId, String relatedId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getLog(String assetId, boolean complete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Asset> listAssets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    File downloadAsset(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean removeAsset(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
