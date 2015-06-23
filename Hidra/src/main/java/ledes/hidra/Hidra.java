package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import org.xml.sax.SAXException;

/**
 * This class provides all operation of a repository, by manipulating Repository
 * and LocalRepository
 *
 * @TODO: Os métodos dessa classe são os equivalentes aos da classe
 * net.ledes.hidra.sources.Command, mas usando a terminologia da Cambuci.
 *
 * @TODO: Documentar todos membros da classe (atributos, métodos, construtores),
 * independente do modificador de acesso (public, protected, private),
 * imediatamente assim que criá-los.
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Hidra {

    /**
     *
     * @param repository
     */
    private Repository repository;

    /**
     * Inicializa um repositório local sem um repositório master associado. Se
     * não existir diretório ele será criado, se já existir um repositório no
     * diretório indicado nada será alterado
     *
     * @param localPath - String com o caminho que o repositório será criado
     * @return - true se não houve problemas
     * @throws java.io.IOException
     */
    public boolean startRepository(String localPath) throws IOException, JAXBException {
        repository = new Repository(localPath);
        if (repository.isRepository());

        return repository.init();

    }

    /**
     * Cria, em um diretório vazio, uma cópia de um repositório indicado. Se o
     * diretório não existir, ele será criado. Se o diretório não for vazio, ou
     * tiver um repositório vazio inicializado, retornará erro.
     *
     * @param localPath - String que indica o caminho onde o repositório sera
     * copiado
     * @param remotePath - String que indica a URL - caso seja um repositório
     * remoto - ou o caminho do repositorio a ser copiado
     * @return
     */
    public boolean startSynchronizedRepository(String localPath, String remotePath) {
        repository = new Repository(localPath, remotePath);
        return repository.cloneRepository();
    }

  
    /**
     * RF-01
     *
     * @param nameAsset
     * @return
     * 
     */
    public boolean addAsset(String nameAsset) {
        try {
            return repository.addAsset(nameAsset);
        } catch (SAXException | IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * RF-02, RF-13
     *
     * @param assetId
     * @return
     */

    public String getSolution(String assetId) {
        return repository.getSolution(assetId);

    }

    /**
     * RF-02, RF-13
     *
     * @param assetId
     * @param solution
     * @return
     */
    public boolean setSolutionType(String assetId, SolutionType solution){
        return repository.setSolutionType(assetId, solution);
    }

    /**
     * RF-04
     *
     * @param assetPath
     * @return
     */
    public boolean validateAsset(String assetPath) {
        try {
            return repository.validateAsset(assetPath);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * RF-05
     *
     * @param assetId
     * @return
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.FileNotFoundException
     */
    public boolean removeAsset(String assetId) throws JAXBException, FileNotFoundException {
        return repository.removeAsset(assetId);
    }

    /**
     * RF-06
     *
     * @param assetId
     * @return
     */
    public String getClassification(String assetId){
        return repository.getClassification(assetId);
    }

    /**
     * RF-06
     *
     * @param classification
     * @return
     */
    public boolean setClassification(ClassificationType classification) {
        return repository.setClassification(classification);
    }

    /**
     * RF-07, RF-14
     *
     * @param assetId
     * @return
     */
    public String getUsage(String assetId){
        return repository.getUsage(assetId);
    }

    /**
     * RF-07
     *
     * @param usage
     * @return
     */
    public boolean setUsage(UsageType usage) {
        return repository.setUsage(usage);
    }

    /**
     * RF-08
     *
     * @param assetId
     * @return
     */
    public List<Asset> getRelatedAssets(String assetId) {
        return repository.getRelatedAssets(assetId);
    }

    /**
     * RF-08
     *
     * @param assetId
     * @param relatedId
     * @return
     */
    public boolean setRelatedAsset(String assetId, String relatedId) {
        return repository.setRelatedAsset(assetId, relatedId);
    }

    /**
     * RF-09
     *
     * @param assetName
     * @return
     */
    public String getLog(String assetName) {
        return repository.getLog(assetName);
    }

//    /**
//     * RF-09
//     *
//     * @param assetId
//     * @return
//     */
//    public String getLog(String assetId) {
//        return repository.getLog(assetId, false);
//    }

    /**
     * RF-10
     *
     * @return
     */
    public Map<String, String> listAssets() {
        return repository.listAssets();
    }

    /**
     * RF-11
     *
     * @param assetId
     * @return
     * @throws java.io.FileNotFoundException
     */
    public File downloadAsset(String assetId) throws FileNotFoundException {
        return repository.downloadAsset(assetId);
    }

}
