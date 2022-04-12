package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;



public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
        public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public User getByUUID(String reset_password_uuid) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.createQuery(
            "SELECT u FROM User u WHERE u.resetPasswordUuid = :targetUUID", User.class)
            .setParameter("targetUUID",reset_password_uuid).getSingleResult();
            return user;
        } finally {
            em.close();
        }        
    }
    
    
}
