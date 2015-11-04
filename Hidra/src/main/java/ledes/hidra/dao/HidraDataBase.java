/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

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

    @Override
    public boolean insert(Object obj) {
        Session session = getSession();

        try {
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object obj) {
        Session session = getSession();

        try {
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

}
