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

    public boolean removeAsset(Asset asset) {

        return removeRelatedAssets(asset.getRelatedAssetsList())
                && removeUsage(asset.getUsage())
                && removeClassification(asset.getClassification())
                && remove(asset.getProfile());
    }

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
            //throw new Exception(e.getMessage());
        }
        return false;
    }

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

}
