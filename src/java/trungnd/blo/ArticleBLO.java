/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.db.Account;
import trungnd.db.Article;

/**
 *
 * @author HOME
 */
public class ArticleBLO implements Serializable {

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

    public List searchByLikeInput(String searchValue, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select a From Article a Where a.articleDescription like :articleDescription and a.articleStatus = 'Active'"
                + " Order By a.articleDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("articleDescription", "%" + searchValue + "%");
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List resultList = query.getResultList();
        return resultList;
    }

    public int getAmountFindByLikeInput(String searchValue, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(accID) from Article where articleStatus='Active' and articleDescription like ?";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", "%" + searchValue + "%");
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        if (count % pageMaxSize == 0)
            return count/pageMaxSize;
        return count/pageMaxSize + 1;
    }
    
    public boolean createArticle
        (String articleID, String articleTitle, String articleDescription, 
                Date articleDate, String articleStatus, String articleImage, Account accID) {
        EntityManager em = emf.createEntityManager();
        
        Article art = em.find(Article.class, articleID);
        if (art == null) {
            art = new Article(articleID, articleTitle, articleDescription, articleImage, articleDate, articleStatus, accID);
            em.getTransaction().begin();
            em.persist(art);
            em.getTransaction().commit();
            
            return true;
        }
        return false;
    }
        
//    public boolean deletePost_1(String articleID) {
//        EntityManager em = emf.createEntityManager();
//        System.out.println("-------------" + articleID + "-------------");
//        Article art = em.find(Article.class, articleID);
//        if (art != null) {
//            em.getTransaction().begin();
//            em.remove(art);
//            em.getTransaction().commit();
//            return true;
//        }
//        return false;
//        
//    }
    
    public boolean deletePost(String articleID) {
        EntityManager em = emf.createEntityManager(); 
        Article art = em.find(Article.class, articleID);
        em.getTransaction().begin();
        art.setArticleStatus("Delete");
        em.getTransaction().commit();
        return true;    
    }
    
    public Article getArticleFromDB(String articleID) {
        EntityManager em = emf.createEntityManager();
        Article art = null; 
        String jpql = "Article.findByArticleID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleID", articleID);
        art = (Article) query.getSingleResult();
        return art;
    }
}
