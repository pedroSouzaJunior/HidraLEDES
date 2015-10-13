package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import ledes.hidra.asset.RelatedAssets;
import ledes.hidra.util.Properties;

/**
 *
 * <h1> Classe que provê todas as funcionalidades referentes a biblioteca
 * Hidra.</h1>
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Hidra {

    /**
     *
     * @param repository
     */
    private Repository repository;
    private Properties hidraProperties;

    /**
     *
     * @param localPath
     */
    public Hidra(String localPath) {
        super();
        hidraProperties = new Properties();
        repository = new Repository(localPath);
    }

    public Hidra() {
        hidraProperties = new Properties();
        repository = new Repository(hidraProperties.getProperty("localPath"));
    }

    /**
     * Retorna um repositorio Hidra.
     *
     * @return
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * Define o repositório Hidra.
     *
     * @param repository
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Properties getHidraProperties() {
        return hidraProperties;
    }

    public void setHidraProperties(Properties hidraProperties) {
        this.hidraProperties = hidraProperties;
    }

    /**
     * Inicializa um repositório local sem um repositório master associado. Se
     * não existir diretório ele será criado, se já existir um repositório no
     * diretório indicado nada será alterado
     *
     * @param localPath - String com o caminho que o repositório será criado
     * @return - true se não houve problemas
     * @throws java.io.IOException
     * @throws javax.xml.bind.JAXBException
     *
     */
    public boolean startRepository() throws IOException, JAXBException {
        boolean initialized = false;

        repository = new Repository(hidraProperties.getProperty("localPath"));

        if (repository.isRepository()) {
            initialized = true;
        }
        try {
            return repository.init(initialized);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Inicializa um repositório bare para ser utilizado no servidor sem um
     * repositório master associado. Se não existir diretório ele será criado,
     * se já existir um repositório no diretório indicado nada será alterado
     *
     * @param localPath - String com o caminho que o repositório será criado
     * @return - true se não houve problemas
     * @throws java.io.IOException
     * @throws javax.xml.bind.JAXBException
     *
     */
    public boolean startRepositoryBare() throws IOException, JAXBException {
        boolean initialized = false;

        repository = new Repository(hidraProperties.getProperty("localPath"));

        if (repository.isRepository()) {
            initialized = true;
        }
        try {
            return repository.initBare(initialized);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

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
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean startSynchronizedRepository() {
        
        repository = new Repository(hidraProperties.getProperty("localPath"), hidraProperties.getProperty("remotePath"));
        boolean ret = repository.cloneRepository();
        if (ret) {
            try {
                startRepository();
            } catch (IOException | JAXBException ex) {
                Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }

    /**
     * Cria, em um diretório vazio, uma cópia de um repositório indicado que
     * requer autentificação com protocolo https. Se o diretório não existir,
     * ele será criado. Se o diretório não for vazio, ou tiver um repositório
     * vazio inicializado, retornará erro.
     *
     * @param localPath - String que indica o caminho onde o repositório sera
     * copiado
     * @param remotePath - String que indica a URL - caso seja um repositório
     * remoto - ou o caminho do repositorio a ser copiado
     * @param user - String com o usuario do repositorio
     * @param password - String com a senha do usuario do repositorio
     *
     * @return true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean startSynchronizedRepositoryAuthentification() {

        repository = new Repository(hidraProperties.getProperty("localPath"), hidraProperties.getProperty("remotePath"));
        boolean ret = repository.cloneRepository(hidraProperties.getProperty("user"), hidraProperties.getProperty("password"));
        if (!ret) {
            repository.removeRepository();
            return false;
        }
        try {
            startRepository();
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * RF-01 A biblioteca Hidra deve possibilitar a inclusão de ativos de
     * software, levando em consideração a composição de um ativo por diferentes
     * artefatos.
     *
     * Adiciona um ativo válido com seus artefatos ao repositório. Recebe nome
     * do ativo no repositório
     *
     * @param nameAsset - nome de um ativo.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
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
     * RF-02 A biblioteca Hidra deve fornecer mecanismos a fim de listar
     * artefatos que compõem um ativo de software armazenado no repositório
     *
     * RF-13 A biblioteca Hidra deve fornecer mecanismos a fim de garantir a
     * atomicidade, consistência e isolamento de transações de controle de
     * ativos de software.
     *
     * Obtém informações referentes a Solution de um ativo.
     *
     * @param assetName - nome de um ativo.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public String getSolution(String assetName) {
        return repository.getSolution(assetName);
    }

    /**
     * RF-02 A biblioteca Hidra deve fornecer mecanismos a fim de listar
     * artefatos que compõem um ativo de software armazenado no repositório
     *
     * RF-13 A biblioteca Hidra deve fornecer mecanismos a fim de garantir a
     * atomicidade, consistência e isolamento de transações de controle de
     * ativos de software.
     *
     * Obtém informações referentes a Solution de um ativo. As informações
     * descritas por meio de um SolutionType, podendo assim serem convertidas
     * para XML.
     *
     * @param assetName - nome de um ativo.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public SolutionType describeSolution(String assetName) {
        return repository.describeSolution(assetName);
    }

    /**
     * RF-02 A biblioteca Hidra deve fornecer mecanismos a fim de listar
     * artefatos que compõem um ativo de software armazenado no repositório
     *
     * RF-13 A biblioteca Hidra deve fornecer mecanismos a fim de garantir a
     * atomicidade, consistência e isolamento de transações de controle de
     * ativos de software.
     *
     * @param assetId - nome de um ativo.
     * @param solution - Objeto que compõe as informações atualizadas referentes
     * a solução de um ativo.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean setSolutionType(String assetId, SolutionType solution) {
        return repository.setSolutionType(assetId, solution);
    }

    /**
     * RF-04 A biblioteca Hidra deve possibilitar que todo novo ativo de
     * software seja validado e certificado de acordo com o padrão adotado.
     *
     * Permite verificar se o manifesto de um ativo é válido de acordo com o
     * padrão RAS adotado.
     *
     * @param assetPath - caminho referente ao ativo que se deseja validar.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean validateAsset(String assetPath) {
        try {
            return repository.validateAsset(assetPath + File.separator);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * RF-05 A biblioteca Hidra deve possibilitar que ativos de software, que
     * não forem mais utilizados, sejam removidos do repositório.
     *
     * Remove um ativo do repositório.
     *
     * @param assetName - Recebe o nome do ativo a ser removido.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     *
     */
    public boolean removeAsset(String assetName) {
        try {
            return repository.removeAsset(assetName);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * RF-06 A biblioteca Hidra deve possibilitar a adição de informações para
     * classificação de um ativo e também o contexto de sua utilização.
     *
     * Obtem informações referentes a classificação de um ativo
     *
     * @param assetId - nome de um ativo.
     *
     * @return - informações referentes a classificação de um ativo.
     */
    public String getClassification(String assetId) {
        return repository.getClassification(assetId);
    }

    /**
     * RF-06 A biblioteca Hidra deve possibilitar a adição de informações para
     * classificação de um ativo e também o contexto de sua utilização.
     *
     * Obtém informações referentes a Solution de um ativo. As informações
     * descritas por meio de um ClassificationType, podendo assim serem
     * convertidas para XML.
     *
     * @param assetId - nome de um ativo.
     *
     * @return - objeto ClassificationType composto por informações que
     * descrevem a classificação de um ativo.
     */
    public ClassificationType describeClassification(String assetId) {
        return repository.describeClassification(assetId);
    }

    /**
     * RF-06 A biblioteca Hidra deve possibilitar a adição de informações para
     * classificação de um ativo e também o contexto de sua utilização.
     *
     * Define a classificação de um ativo.
     *
     * @param assetID - nome do ativo
     * @param classification - Objeto ClassificationType composto por
     * informações atualizadas referentes a classificação de um ativo.
     *
     * @return - true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean setClassification(String assetID, ClassificationType classification) {
        return repository.setClassification(assetID, classification);
    }

    /**
     * RF-07 A biblioteca Hidra deve possibilitar a adição de informações sobre
     * regras para instalação, personalização, e utilização do ativo
     *
     * RF-14 A biblioteca Hidra deve fornecer mecanismos a fim de permitir a
     * persistência de diferentes tipos de ativos.
     *
     * Retorna a utilização (Usage) de um ativo.
     *
     * @param assetId - nome do ativo.
     *
     * @return - informações referentes ao uso de um ativo.
     */
    public String getUsage(String assetId) {
        return repository.getUsage(assetId);
    }

    /**
     * RF-07 A biblioteca Hidra deve possibilitar a adição de informações sobre
     * regras para instalação, personalização, e utilização do ativo
     *
     * RF-14 A biblioteca Hidra deve fornecer mecanismos a fim de permitir a
     * persistência de diferentes tipos de ativos.
     *
     * Retorna a utilização (Usage) de um ativo. Obtém informações referentes ao
     * Usage de um ativo. As informações descritas por meio de um UsageType,
     * podendo assim serem convertidas para XML.
     *
     * @param assetId - nome de um ativo.
     *
     * @return UsageType composto por informações referentes ao uso de um ativo.
     */
    public UsageType describleUsage(String assetId) {
        return repository.describeUsage(assetId);
    }

    /**
     * RF-07 A biblioteca Hidra deve possibilitar a adição de informações sobre
     * regras para instalação, personalização, e utilização do ativo
     *
     * RF-14 A biblioteca Hidra deve fornecer mecanismos a fim de permitir a
     * persistência de diferentes tipos de ativos.
     *
     * Define o usage de um ativo.
     *
     * @param assetId - nome do ativo.
     *
     * @param usage - UsageType composto por informações referentes ao uso de um
     * ativo.
     * @return true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean setUsage(String assetId, UsageType usage) {
        return repository.setUsage(assetId, usage);
    }

    /**
     * RF-08 A biblioteca Hidra deve possibilitar o registro de dependência
     * entre ativos.
     *
     * Retorna a dependência (RelatedAssets) entre ativos.
     *
     * @param assetId - nome de um ativo.
     * @return - informações referentes a dependência entre ativos.
     */
    public String getRelatedAssets(String assetId) {
        return repository.getRelatedAssets(assetId);
    }

    /**
     * RF-08 A biblioteca Hidra deve possibilitar o registro de dependência
     * entre ativos.
     *
     * Retorna a dependência (RelatedAssets) entre ativos. Obtém informações
     * referentes a dependência entre ativos. As informações descritas por meio
     * de um RelatedAssets, podendo assim serem convertidas para XML.
     *
     * @param assetId - nome do ativo.
     * @return - RelatedAssets composto por informações referentes a dependência
     * entre ativos.
     */
    public RelatedAssets describeRelatedAssets(String assetId) {
        return repository.describeRelatedAssets(assetId);
    }

    /**
     * RF-08 A biblioteca Hidra deve possibilitar o registro de dependência
     * entre ativos.
     *
     * Define a dependência entre os ativos.
     *
     * @param assetId - Ativo que deseja registrar dependência
     * @param relatedId - Ativo que se tem dependência.
     * @return -true caso operação ocorra com sucesso, false caso contrário.s
     */
    public boolean setRelatedAsset(String assetId, String relatedId) {
        return repository.setRelatedAsset(assetId, relatedId);
    }

    /**
     * RF-09 A biblioteca Hidra deve fornecer mecanismos a fim de oferecer
     * informações relevantes a todos os interessados, sobre mudanças que
     * aconteçam no ativo de software: data de alteração, autor da alteração, o
     * que foi alterado e descrição sobre a alteração.
     *
     * Retorna informações sobre mudanças de um ativo específico.
     *
     * @param assetName - nome de um ativo.
     * @return - informações referentes a mudanças ocorridas em um ativo.
     */
    public String getLog(String assetName) {
        return repository.getLog(assetName);
    }

    /**
     * Retorna informações sobre mudanças em todo repositório.
     *
     * @return - informações referentes a mudanças ocorridas no repositório.
     */
    public String getLog() {
        return repository.getLog();
    }

    /**
     * RF-10 A biblioteca Hidra deve fornecer mecanismos a fim de listar ativos
     * armazenados no repositório.
     *
     * Retorna uma lista com todos os ativos validados e monitorados no
     * repositório.
     *
     * @return informações sobre a relação de ativos presente no repositório.
     */
    public Map<String, String> listAssets() {
        return repository.listAssets();
    }

    /**
     * RF-11 A biblioteca Hidra deve fornecer mecanismos a fim de recuperar um
     * ativo armazenado no repositório (download).
     *
     * Realiza o download do ativo em formato compactado.
     *
     * @param format - formato do arquivo
     * @param path - destino do download
     * @param assetName - nome do ativo
     * @return
     * @throws java.io.FileNotFoundException
     */
    public boolean downloadAsset(String format, String path, String assetName) throws FileNotFoundException {
        return repository.retrieveAsset(format, path, assetName);

    }

    /**
     * Salva todas as alterações realizadas no repositório. É obrigatório o
     * envio de uma mensagem informando as alterações realizadas.
     *
     * @param message - mensagem informando as mudanças que serão submetidas ao
     * repositório.
     * @return true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean save(String message) {

        try {
            return repository.saveChanges(message);
        } catch (SAXException | IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Atualiza o repositório local com as mudanças do repositório remoto. É
     * necessário autentificação para habilitar acesso.
     *
     * @param user - Usuário para autenticação no repositório.
     * @param password - Senha para autenticação no repositório.
     * @return true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean receiveUpdates(String user, String password) {

        return repository.synchronizeRepository(user, password);

    }

    /**
     * Envia as alterações do repositório local ao repositório remoto.
     *
     * @param user - Usuário para autenticação no repositório.
     * @param password - Senha para autenticação no repositório.
     * @return
     */
    public String sendUpdates(String user, String password) {

        return repository.updateRepository(user, password);
    }

    /**
     * Retorna informações sobre o estado do repositório.
     *
     * @return
     */
    public Map<String, Set<String>> showStatus() {
        return repository.showStatus();

    }

    /**
     * Define o usuário do repositório.
     *
     * @param name - nome de usuário.
     * @param email - email de usuário.
     */
    public void setUser(String name, String email) {
        repository.setUserRepo(name, email);
    }

    /**
     * Retorna o usuário atual do repositório
     *
     * @return
     */
    public Map<String, String> getUser() {
        return repository.getUserRepo();

    }

    /**
     * Define o repositório remoto de um repositório local.
     *
     * @param url
     */
    public void setRemoteRepo(String url) {
        repository.setRemoteRepo(url);
    }

    /**
     * Retorna informações sobre o repositório remoto
     *
     * @return o caminho do repositório remoto.
     */
    public String getRemoteRepo() {
        return repository.getRemoteRepo();
    }

    /**
     * Verifica se dado o nome do ativo o mesmo se encontra versionado no
     * repositório.
     *
     * @param assetName
     * @return
     */
    public boolean findAsset(String assetName) {
        return repository.findAsset(assetName);
    }

    /**
     * Cria um Tag com anotações.
     *
     * @param name
     * @param message
     * @return
     */
    public boolean createTag(String name, String message) {
        return repository.createTagAnotted(name, message);

    }

    /**
     * Cria um novo ramo(branch) no repositório
     *
     * @param branchName - recebe o nome do novo branch
     * @return
     */
    public String createBranch(String branchName) {
        return repository.createBranch(branchName);

    }

    /**
     * Realiza a troca de branch
     *
     * @param branchName - Recebe o nome do branch que se deseja alternar.
     * @return
     */
    public boolean checkoutBranch(String branchName) {

        return repository.checkoutBranch(branchName);
    }

    /**
     * Mostra todos os branches disponíveis no repositório e o branch atual de
     * trabalho.
     *
     * @return
     */
    public boolean showBranches() {
        return repository.showBranches();
    }

    /**
     * Recebe um vetor de Strings contendo os nomes dos branches que se deseja
     * remover do repositório.
     *
     * @param branchName
     * @return
     */
    public boolean deleteBranch(String[] branchName) {

        return repository.deleteBranch(branchName);
    }

    /**
     * Realiza a mesclagem dos branches.
     *
     * @param branchName - Recebe como parâmetro o nome do branch que se deseja
     * para fazer a mesclagem.
     * @return
     */
    public boolean mergeBranch(String branchName) {

        return repository.merge(branchName);
    }
}
