/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

/**
 *
 * @author pedro
 * 
 * 
 * Interface responsável por definir métodos utilizados pelo Banco de dados Hidra.
 * Versão inicial contendo métodos CRUD.
 * @param <Object>
 */
public interface HidraDataBaseInterface<Object> {

    public boolean insert(Object obj);

    public boolean update(Object obj);

    public boolean remove(Object obj);

}
