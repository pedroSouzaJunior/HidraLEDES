/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ledes.hidra.sources;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author danielli
 */
public class Main {
    
    public static void main(String [ ] args) throws IOException
    {
      File original = new File("/home/danielli/testeMergeLocal/testDiff.txt");
      File revisado = new File("/home/danielli/testeMergeLocal/testDiff2.txt");
      
      FileComparator fileComparator = new FileComparator(original, revisado);
      
      
      /*
      while(!fileComparator.getChangesFromOriginal().isEmpty()){  
        System.out.println(fileComparator.getChangesFromOriginal());  
      }*/
     
      
      Iterator it = fileComparator.getChangesFromOriginal().iterator();  
      while (it.hasNext()) {  
          System.out.println("Mudanças do Original: ");
        System.out.println(it.next().toString());  
        }  
      
       it = fileComparator.getDeletesFromOriginal().iterator();  
        while (it.hasNext()) {  
          System.out.println("Itens removidos do original: ");
        System.out.println(it.next().toString());  
        }  
        
         it = fileComparator.getInsertsFromOriginal().iterator();  
        while (it.hasNext()) {  
          System.out.println("Itens inseridos do original: ");
        System.out.println(it.next().toString());  
        }  
    }
}
    

