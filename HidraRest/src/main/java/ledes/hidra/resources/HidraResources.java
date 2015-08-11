/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author pedro
 */
public class HidraResources {

    public boolean assetExist(File path, String name) {

        File[] matchingFiles = path.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return (name.startsWith("pedro") && name.endsWith("zip"));
            }
        });

        return matchingFiles != null;

    }
}
