package utils;

import entity.Car;
import entity.Country;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

public class SetupTestData {

    private static final TestDataGenerator GEN = new TestDataGenerator();
    private static final int AMOUNT_OF_CARS = 100;

    public static void main(String[] args) {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();

        em.getTransaction().begin();
        List<Country> countries = em.createNamedQuery("Country.findAll", Country.class).getResultList();

        for (int i = 0; i < AMOUNT_OF_CARS; i++) {
            String manufactor = GEN.getRandomManufactor();
            String[] place = GEN.getRandomPlace();
            Car car = new Car(GEN.getRandomRegNo(), GEN.getRandomPrice(), manufactor, GEN.getRandomModel(manufactor), GEN.getType(manufactor), GEN.getRandomYear(), GEN.getRandomDist(), 5, "Manual", "Gas", place[0], place[1], place[2]);
            car.setCountry(countries.get(new Random().nextInt(countries.size() - 1)));
            em.persist(car);
        }

        em.getTransaction().commit();
    }
}
