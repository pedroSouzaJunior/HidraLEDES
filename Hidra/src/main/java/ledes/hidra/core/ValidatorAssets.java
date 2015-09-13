/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.core;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.SolutionType;

/**
 * Classe responsável por validar um ativo de acordo com as normas definidas.
 *
 * @author danielli
 */
public class ValidatorAssets {

    private final String localAsset;
    private final static String separator = File.separator;
    private List<String> validAssets = new ArrayList<>();
    private List<String> invalidAssets = new ArrayList<>();
    private String pathAux;

    public ValidatorAssets(String localAsset) {
        super();
        this.localAsset = localAsset;

    }

    /**
     * Verifica se o caminho passado corresponde a um diretório e se o não está
     * vazio.
     *
     * @param path
     * @return
     */
    private boolean isValidDirectory(String path) {
        File dir = new File(path);
        return dir.isDirectory() && dir.listFiles().length != 0;

    }

    /**
     * Verifica se eo arquivo existe
     *
     * @param fileName
     * @param reference
     * @return
     */
    private boolean isValidFile(final String fileName, String reference) {
        File path;

        if (reference.equals("/")) {

          
            path = new File(localAsset + reference);
            pathAux = localAsset + reference + fileName;
            

        } else {
            path = new File(localAsset + separator + reference);
            String aux = localAsset + reference;
            pathAux = aux + separator + fileName;
               if (!isValidDirectory(aux.replace("/", separator))) {
                return false;
            }
        }
        File f = new File(pathAux);
        return f.exists()&& !f.isDirectory();
    }

    public void isValidAsset(Asset asset) {

        String aux;
        boolean result;
        String path;
        SolutionType.Artifacts artifacts = asset.getSolution().getArtifacts();
        for (ArtifactType art : artifacts.getArtifact()) {

            if ("Folder".equalsIgnoreCase(art.getType())) {

                aux = localAsset + art.getReference() + art.getName();
            //    result = );

                if (!isValidDirectory(aux.replace("/", separator))) {
                    invalidAssets.add(aux + " is not folder or folder is empty.\n");
                } else {

                    validAssets.add(aux.substring(localAsset.length()));
                }

            } else if ("File".equalsIgnoreCase(art.getType())) {

               // result = ;

                if (!isValidFile(art.getName(), art.getReference())) {

                    invalidAssets.add(pathAux + " is not found\n");
                } else {

                    validAssets.add(pathAux.substring(localAsset.length()));
                }

            }

        }
        for (ArtifactType req : asset.getSolution().getRequirements().getArtifact()) {

            if ("Folder".equalsIgnoreCase(req.getType())) {

               

                if (!isValidDirectory(localAsset + req.getReference().replace("/", separator) + separator + req.getName())) {
                
                    invalidAssets.add(localAsset + req.getReference().replace("/", separator) + separator + req.getName() + " is not folder or folder is empty.\n");
                } else {

                    validAssets.add( req.getReference().replace("/", separator) + separator + req.getName());
                }
            } else if ("File".equalsIgnoreCase(req.getType())) {


                if (!isValidFile(req.getName(), req.getReference())) {
                 
                    invalidAssets.add(pathAux + " is not found\n");
                } else {
                       
                    
                    validAssets.add(pathAux.substring(localAsset.length()));
                }

            }

        }

        for (ArtifactType des : asset.getSolution().getDesign().getArtifact()) {

            if ("Folder".equalsIgnoreCase(des.getType())) {
                result = isValidDirectory(localAsset + des.getReference().replace("/", separator) + des.getName());

                if (!result) {
                    invalidAssets.add(localAsset + des.getReference().replace("/", separator) + separator + des.getName() + " is not folder or folder is empty.\n");
                } else {

                    validAssets.add( des.getReference().replace("/", separator) + separator + des.getName());
                }
            } else if ("File".equalsIgnoreCase(des.getType())) {

                result = isValidFile(des.getName(), des.getReference());

                if (!result) {                
                    invalidAssets.add(pathAux + " is not found\n");
                } else {

                    validAssets.add(pathAux.substring(localAsset.length()));
                }
            }

        }

        for (ArtifactType imp : asset.getSolution().getImplementation().getArtifact()) {

            if ("Folder".equalsIgnoreCase(imp.getType())) {
               

                if (!isValidDirectory(localAsset + imp.getReference().replace("/", separator) + separator + imp.getName())) {
                    invalidAssets.add(localAsset + imp.getReference().replace("/", separator) + separator + imp.getName() + " is not folder or folder is empty.\n");
                } else {

                    validAssets.add( imp.getReference().replace("/", separator) + separator + imp.getName());
                }
            } else if ("File".equalsIgnoreCase(imp.getType())) {

             //   result = ;

                if (!isValidFile(imp.getName(), imp.getReference())) {
                    invalidAssets.add(pathAux + " is not found\n");
                } else {

                    validAssets.add(pathAux.substring(localAsset.length()));
                }

            }

        }
        
        
       

      
    }

    public List<String> getValidAssets() {
        return validAssets;
    }

    public List<String> getInvalidAssets() {
        return invalidAssets;
    }

}
