/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.db.Account;
import trungnd.db.Article;
import trungnd.db.Emotion;

/**
 *
 * @author HOME
 */
public class EmotionBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("J3.L.P0010MiniSocialNetworkJPAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public List<String> countEmotionLike(Article articleID , boolean emotionType) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Emotion.countEmotionID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleID", articleID);
        query.setParameter("emotionType", emotionType); 
        List<String> count = query.getResultList();
        return count;        
       
    }
    
    public List<String> countEmotionDislike(Article articleID, boolean emotionType) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Emotion.countEmotionID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleID", articleID);
        query.setParameter("emotionType", emotionType); 
        List<String> count = query.getResultList();
        return count;        
    }
    
    public boolean createEmotion(String emotionID, boolean emotionType, Account accID, Article articleID) {
        EntityManager em = emf.createEntityManager();
        Emotion emo = em.find(Emotion.class, emotionID);
        if (emo == null) {
            emo = new Emotion(true, emotionID, emotionType, accID, articleID);
            em.getTransaction().begin();
            em.persist(emo);
            em.getTransaction().commit();         
        } else {
            em.getTransaction().begin();
            if (emo.getEmotionType() == emotionType) { // tính tip              
                if (emo.getStatus()) {
                    emo.setStatus(false);
                } else {
                    emo.setStatus(true);
                }
            } else {
                emo.setStatus(true);
                emo.setEmotionType(emotionType);
            }        
            em.getTransaction().commit();         
        }
        return true;
    }
    
//    public boolean createDislikeEmotion(String emotionID, boolean emotionType, Account accID, Article articleID) {
//        EntityManager em = emf.createEntityManager();
//        Emotion emo = em.find(Emotion.class, emotionID);
//        if (emo == null) {
//            emo = new Emotion(true, emotionID, emotionType, accID, articleID);
//            em.getTransaction().begin();
//            em.persist(emo);
//            em.getTransaction().commit();         
//        } else {
//            em.getTransaction().begin();
//            if (emo.getEmotionType() == emotionType) { // tính tip
//                emo.setStatus(false);
//            } else {
//                emo.setStatus(true);
//                emo.setEmotionType(emotionType);
//            }        
//            em.getTransaction().commit();         
//        }
//        return true;
//    }
}
