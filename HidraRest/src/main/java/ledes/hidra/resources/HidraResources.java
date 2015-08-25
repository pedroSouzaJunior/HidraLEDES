/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.resources;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author pedro
 */
public class HidraResources {

    public boolean assetExist(File path, final String fileName) {

        System.out.println("path: --------" + path);
        System.out.println("FILENAME: --------" + fileName);
        File[] matchingFiles = path.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {

                return name.equals(fileName + ".zip");
            }
        });

        return matchingFiles.length != 0;

    }
}
