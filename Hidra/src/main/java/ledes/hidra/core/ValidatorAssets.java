/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.core;

import java.io.File;
import java.io.FilenameFilter;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.SolutionType;

/**
 * Classe responsável por validar um ativo de acordo com as normas definidas.
 * @author danielli
 */
public class ValidatorAssets {

    private final String localAsset;
    private final static String separator = File.separator;

    public ValidatorAssets(String localAsset) {
        super();
        this.localAsset = localAsset;
    }

    private boolean isValidDirectory(String path) {
        File dir = new File(path);
        return  dir.isDirectory()&&dir.listFiles().length!=0;

    }

    private boolean isValidFile(final String fileName, String reference) {
        File path;
        if(reference.equals("/")){
              path = new File(localAsset + reference);
            
        }
        else{
             path = new File(localAsset + separator + reference);
        }
        
        File[] matchingFiles = path.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               
                return name.equals(fileName);
            }
        });


        return matchingFiles.length != 0;
    }

    public boolean isValidAsset(Asset asset) {

        StringBuilder reasons = new StringBuilder();
        reasons.append("\n");

        
        boolean result = true;
        SolutionType.Artifacts artifacts = asset.getSolution().getArtifacts();
        for (ArtifactType art : artifacts.getArtifact()) {
            if ("Folder".equalsIgnoreCase(art.getType())) {
               
                    
                result = isValidDirectory(localAsset + art.getReference() + art.getName());
                
                if (!result) {
                    reasons.append(art.getName()).append(" is not folder\n");
                    System.out.println(reasons);
                    return false;
                }
            } else if ("File".equalsIgnoreCase(art.getType())) {

                
                result = isValidFile(art.getName(), art.getReference());
              

                if (!result) {
                    reasons.append(art.getReference()).append(art.getName()).append(" is not found\n");
                    System.out.println(reasons);
                    return false;

                }

            }

        }
        for (ArtifactType req : asset.getSolution().getRequirements().getArtifact()) {

            if ("Folder".equalsIgnoreCase(req.getType())) {
                result = isValidDirectory(localAsset + req.getReference()+separator + req.getName());

                if (!result) {
                    reasons.append(req.getReference()).append(req.getName()).append(" is not folder\n");
                    System.out.println(reasons);
                    return false;
                }
            } else if ("File".equalsIgnoreCase(req.getType())) {

                result = isValidFile(req.getName(), req.getReference());
              

                if (!result) {
                    reasons.append(req.getReference()).append(req.getName()).append(" is not found\n");
                    System.out.println(reasons);
                    return false;
                }

            }

        }

        for (ArtifactType des : asset.getSolution().getDesign().getArtifact()) {

            if ("Folder".equalsIgnoreCase(des.getType())) {
                result = isValidDirectory(localAsset + des.getReference() + des.getName());

                if (!result) {
                   reasons.append(des.getReference()).append(des.getName()).append(" is not found\n");
                    System.out.println(reasons);
                    return false;
                }
            } else if ("File".equalsIgnoreCase(des.getType())) {

                result = isValidFile(des.getName(), des.getReference());
               

                if (!result) {
                    reasons.append(des.getReference()).append(des.getName()).append(" is not found\n");
                    System.out.println(reasons);
                    return false;
                }

            }

        }

        for (ArtifactType imp : asset.getSolution().getImplementation().getArtifact()) {

            if ("Folder".equalsIgnoreCase(imp.getType())) {
                result = isValidDirectory(localAsset + imp.getReference()+separator + imp.getName());

                if (!result) {
                    reasons.append(imp.getReference()).append(imp.getName()).append(" is not found\n");
                    System.out.println(reasons);
                    return false;
                }
            } else if ("File".equalsIgnoreCase(imp.getType())) {

                result = isValidFile(imp.getName(), imp.getReference());
               
              
                if (!result) {
                    reasons.append(imp.getReference()).append(imp.getName()).append(" is not found\n");
                    System.out.println(reasons);
                    return false;
                }

            }

        }

        return result;

    }


}
