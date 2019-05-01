package facade;

import entity.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import exceptions.CarException;
import java.util.List;

public class CarFacade {

    private static EntityManagerFactory emf;
    private static CarFacade instance;

    public static CarFacade getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new CarFacade();
        }
        return instance;
    }

    public List<Car> getAllCars() throws CarException {
        EntityManager em = emf.createEntityManager();
        List<Car> cars;
        try {
            cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
        } finally {
            em.close();
        }
        return cars;
    }

    public Car getCarByRegNo(String regNo) throws CarException {
        EntityManager em = emf.createEntityManager();
        Car car;
        try {
            car = em.find(Car.class, regNo);
        } catch (Exception e) {
            throw new CarException("There is no car with the registration number: " + regNo);
        } finally {
            em.close();
        }
        return car;
    }
}
