/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

import ledes.hidra.asset.Asset;
import ledes.hidra.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author pedro
 */
public class HidraDataBase {

    private Session getSession() {

        return (Session) HibernateUtil.getSession();
    }
    
    public void insert(Asset asset){
    
        Session session = getSession();
        try {
            session.beginTransaction();
            session.save(asset);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
