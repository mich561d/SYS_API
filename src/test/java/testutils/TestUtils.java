package testutils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestUtils {

    public static void setupTestUsers(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from Car").executeUpdate();
            em.createQuery("delete from BookingInformation").executeUpdate();

            System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
