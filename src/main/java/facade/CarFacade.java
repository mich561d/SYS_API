package facade;

import dto.CarDTO;
import entity.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import exceptions.CarException;
import java.util.ArrayList;
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

    public List<CarDTO> getAllCars() throws CarException {
        EntityManager em = emf.createEntityManager();
        List<CarDTO> carsDTO = new ArrayList();
        try {
            List<Car> cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
            for (Car car : cars) {
                CarDTO dto = new CarDTO(car.getRegno(), car.getPrice(), car.getManufactor(), car.getModel(), car.getType(), car.getReleaseYear(), car.getDrivingDist(), car.getSeats(), car.getDrive(), car.getFuelType(), car.getLongitude(), car.getLatitude(), car.getAddress(), car.getCountry().getCountry());
                carsDTO.add(dto);
            }
        } finally {
            em.close();
        }
        return carsDTO;
    }

    public CarDTO getCarByRegNo(String regNo) throws CarException {
        EntityManager em = emf.createEntityManager();
        CarDTO carDTO;
        try {
            Car car = em.find(Car.class, regNo);
            carDTO = new CarDTO(car.getRegno(), car.getPrice(), car.getManufactor(), car.getModel(), car.getType(), car.getReleaseYear(), car.getDrivingDist(), car.getSeats(), car.getDrive(), car.getFuelType(), car.getLongitude(), car.getLatitude(), car.getAddress(), car.getCountry().getCountry());
        } catch (Exception e) {
            throw new CarException("There is no car with the registration number: " + regNo);
        } finally {
            em.close();
        }
        return carDTO;
    }
}
