/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ProfileType;
import ledes.hidra.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author pedro
 */
public class HidraDataBase implements HidraDataBaseInterface<Object> {

    private Session getSession() {

        return (Session) HibernateUtil.getSession();
    }

    /*
     public void insertAsset(Asset asset) {

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

     public void insertProfile(ProfileType profile) {

     Session session = getSession();
     try {
     session.beginTransaction();
     session.save(profile);
     session.getTransaction().commit();
     } catch (Exception e) {
     session.getTransaction().rollback();
     e.printStackTrace();
     }
     }
     */
    @Override
    public void insert(Object obj) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
