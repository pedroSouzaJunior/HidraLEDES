/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

import java.util.List;
import ledes.hidra.asset.Activity;
import ledes.hidra.asset.ArtifactActivy;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.Context;
import ledes.hidra.asset.DescriptionGroup;
import ledes.hidra.asset.RelatedAssetType;
import ledes.hidra.asset.RelatedAssets;
import ledes.hidra.asset.UsageType;
import ledes.hidra.asset.VariabilityPointBinding;
import ledes.hidra.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author pedro
 *
 * Classe responsavel por implementar metodos responsavel pela manipulacao do
 * banco de dados. Implementa a Inteface HidraCrud.
 */
public class HidraDataBase implements HidraCrud<Object> {

    /**
     * Uma sessao com o banco de dados sera obtida por meio deste metodo.
     *
     * @return Session
     */
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

    @Override
    public boolean remove(Object obj) {
        Session session = getSession();

        try {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * *
     * Conjunto de metetodos reponsavel por remover os componentes de um ativo
     * em forma de cascata.
     *
     * @param asset
     * @return true os metodos delegados executem corretamente. false caso
     * contrario
     */
    public boolean removeAsset(Asset asset) {

        return removeRelatedAssets(asset.getRelatedAssetsList())
                && removeUsage(asset.getUsage())
                && removeClassification(asset.getClassification())
                && remove(asset.getProfile());
    }

    /**
     * *
     * Um ativo possui uma lista de componentes que representam seus ativos
     * relacionados. Percorre a lista de ativos relacionados e remove cada um
     * deles.
     *
     * @param relatedAssetsList
     * @return
     */
    private boolean removeRelatedAssets(RelatedAssets relatedAssetsList) {

        try {
            for (RelatedAssetType details : relatedAssetsList.getListOfRelatedAssets()) {
                remove(details);
            }
            remove(relatedAssetsList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * *
     * Um ativo possiu um componente que representa os metadados relacionados ao
     * seu uso. Percorre a lista de artifactActivities removendo cada uma delas.
     *
     * @param usage
     * @return
     */
    private boolean removeUsage(UsageType usage) {

        try {
            for (ArtifactActivy artifactActivity : usage.getArtifactActivities()) {
                removeActivity(artifactActivity.getActivities());
            }
            return remove(usage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * *
     * Percorre a lista de atividades relacionadas ao ativo e remove cada um de
     * seus elementos.
     *
     * @param activities
     * @return
     */
    private boolean removeActivity(List<Activity> activities) {

        try {
            for (Activity activity : activities) {
                for (VariabilityPointBinding variability : activity.getVariability()) {
                    remove(variability);
                }
                remove(activity);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * *
     * Um ativo possui um componente que representa a sua classificação. O
     * metodo removeClassification() percorre a lista de componentes que
     * representam os grupos descritores e a lista de Contextos, removendo cada
     * um destes.
     *
     * @param classification
     * @return
     */
    private boolean removeClassification(ClassificationType classification) {

        try {
            for (DescriptionGroup description
                    : classification.getDescriptionGroups()) {
                remove(description);
            }
            for (Context context : classification.getContexts()) {
                remove(context);
            }
            remove(classification);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
