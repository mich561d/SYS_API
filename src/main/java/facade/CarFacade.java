package facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import exceptions.AuthenticationException;
import utils.PuSelector;

/**
 * @author lam@cphbusiness.dk
 */
public class CarFacade {

    private static EntityManagerFactory emf;
    private static CarFacade instance;

    private CarFacade() {
    }

    public static CarFacade getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new CarFacade();
        }
        return instance;
    }

//    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
//        EntityManager em = emf.createEntityManager();
//        User user;
//        try {
//            user = em.find(User.class, username);
//            if (user == null || !user.verifyPassword(password)) {
//                throw new AuthenticationException("Invalid user name or password");
//            }
//        } finally {
//            em.close();
//        }
//        return user;
//    }
}
