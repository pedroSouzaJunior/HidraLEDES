/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import ledes.hidra.services.Command;

/**
 *
 * @author pedro
 */
public class HidraClient {

    public static int SUCESS = 200;

    public static void main(String[] args) {

        try {

            URL url = new URL("http://localhost:8080/ServicesHidra/hidra/services/getcommand");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != SUCESS) {
                throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
            }

            BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            try {

                JAXBContext context = JAXBContext.newInstance(Command.class);
                Unmarshaller unmarsh = context.createUnmarshaller();
                Command command = (Command) unmarsh.unmarshal(buff);

                StringBuilder stb = new StringBuilder("\n");
                stb.append("result: \n");
                stb.append(command.getName()).append("\n");
                stb.append(command.getNumberOfParameters());
                System.out.println(stb.toString());

            } catch (JAXBException ex) {
                Logger.getLogger(HidraClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            connection.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(HidraClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HidraClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
