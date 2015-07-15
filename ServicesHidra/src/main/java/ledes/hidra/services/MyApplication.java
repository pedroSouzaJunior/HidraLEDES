/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author pedro
 */
@ApplicationPath("hidra")
public class MyApplication extends ResourceConfig{
    
     public MyApplication() {
         ResourceConfig register = register(HidraServices.class);
    }
}
