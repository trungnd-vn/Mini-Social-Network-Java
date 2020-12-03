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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.db.Account;

/**
 *
 * @author HOME
 */
public class AccountBLO implements Serializable {

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
    
    public boolean checkLogin(String email, String password) {
        EntityManager em = emf.createEntityManager();
        
        String jpql = "Select a from Account a Where a.accID = :accID And a.accPassword = :accPassword";
        
        Query query = em.createQuery(jpql);
        query.setParameter("accID", email);
        query.setParameter("accPassword", password);
        
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
//    public boolean checkAdmin(String email) {
//        EntityManager em = emf.createEntityManager();
//        
//        String jpql = "Select a from Account a Where a.accID = :accID And a.roleName = :roleName";
//        
//        String roleName = "admin";
//        
//        Query query = em.createQuery(jpql);
//        query.setParameter("accID", email);
//        query.setParameter("roleName", roleName);
//        
//        try {
//            query.getSingleResult();
//            return true;
//        } catch (NoResultException e) {
//            return false;
//        }
//    }
    
    public Account loginPage(String email) {
        Account acc = null;
        EntityManager em = emf.createEntityManager();
        
        Query queryLogin = em.createNamedQuery("Account.findByAccID");
        queryLogin.setParameter("accID", email);
        
        try {
            acc = (Account) queryLogin.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public boolean createAccount 
        (String accID, String password, String name, String roleName, String status) {
        EntityManager em = emf.createEntityManager();
        
        Account acc = em.find(Account.class, accID);
        if (acc == null) {
            acc = new Account(accID, password, name, roleName, status);
            
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
            
            return true;
        }       
        return false;
    }
}
