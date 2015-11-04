/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

/**
 *
 * @author pedro
 * @param <Object>
 */
public interface HidraDataBaseInterface<Object> {

    public boolean insert(Object obj);

    public boolean update(Object obj);
}
