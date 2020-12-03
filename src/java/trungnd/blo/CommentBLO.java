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
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.db.Account;
import trungnd.db.Article;
import trungnd.db.Comment;

/**
 *
 * @author HOME
 */
public class CommentBLO implements Serializable {

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

    public List<Comment> showComment(Article articleID, boolean statusCmt) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Comment.findByArticleID&Status";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleID", articleID);
        query.setParameter("status", statusCmt);
        List<Comment> resultList = query.getResultList();
        return resultList;
    }
    
    public boolean createComment(String cmtID, Article articleID, Account accID, String content, boolean status, Date cmtDate) {
        EntityManager em = emf.createEntityManager();
        Comment cmt = em.find(Comment.class, cmtID);
        if (cmt == null) {
            cmt = new Comment(cmtDate, cmtID, content, accID, articleID, status);
            em.getTransaction().begin();
            em.persist(cmt);
            em.getTransaction().commit();
            
            return true;
        }
        return false;
    }

    public boolean deleteComment(String put_txtCmtID) {
        EntityManager em = emf.createEntityManager();
        
        Comment cmt = em.find(Comment.class, put_txtCmtID);
        if (cmt != null) {
            em.getTransaction().begin();
            em.remove(cmt); 
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
