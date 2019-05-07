package testutils;

import entity.BookingInformation;
import entity.Car;
import entity.Country;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestUtils {

    // Test data
    private static final String[] R = {"AA12345", "BB12345", "CC12345", "DD12345", "EE12345"};
    private static final double[] P = {50.0, 75.0, 100.0, 125.0, 67.5};
    private static final String[] MA = {"Fiat", "Toyota", "Opel", "Audi", "Volkswagen"};
    private static final String[] MO = {"Punto", "Yaris", "Corsa D", "A3", "Up!"};
    private static final int[] Y = {2016, 2015, 2014, 2013, 2012};
    private static final int[] D = {25433, 54326, 62144, 85432, 100543};

    public static void setupTestData(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete existing cars to get a "fresh" database
            em.createQuery("delete from BookingInformation").executeUpdate();
            em.createQuery("delete from Car").executeUpdate();
            //Create new cars
            Country country = new Country(1, "Denmark");
            BookingInformation booking = new BookingInformation("2019-05-03", "2019-05-05", new Date(), 999.09);
            em.persist(country);
            for (int i = 0; i < 5; i++) {
                Car car = new Car(R[i], P[i], MA[i], MO[i], "Hatchback", Y[i], D[i], 5, "Manual", "Gas", "12.6509822", "55.6091282", "Copenhagen Airport");
                car.setCountry(country);
                if (i == 3) {
                    car.getBookingInformationCollection().add(booking);
                    booking.setCar(car);
                }
                em.persist(car);
            }
            System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
