package facade;

import dto.BookingInformationDTO;
import dto.CarDTO;
import entity.BookingInformation;
import entity.Car;
import exceptions.BookingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import exceptions.CarException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.SetupTestData;

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
            if (cars.isEmpty()) {
                SetupTestData.createData();
                cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
            }
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

    public BookingInformationDTO rentCar(String regNo, String start, String end) throws ParseException, BookingException {
        StringToDate(start);
        EntityManager em = emf.createEntityManager();
        BookingInformationDTO bookingInformationDTO;
        try {
            em.getTransaction().begin();
            Car car = em.find(Car.class, regNo);
            Date s = StringToDate(start);
            Date e = StringToDate(end);
            long days = getDays(s, e);
            BookingInformation bi = new BookingInformation(s.toString(), e.toString(), new Date(), (car.getPrice() * days));
            bi.setCar(car);
            em.persist(bi);
            bookingInformationDTO = new BookingInformationDTO(bi);
            em.getTransaction().commit();
        } catch (ParseException e) {
            throw new BookingException("One or Both of the dates are invalid!");
        } finally {
            em.close();
        }
        return bookingInformationDTO;
    }

    private Date StringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj;
        dateObj = sdf.parse(date);
        return dateObj;
    }

    private long getDays(Date s, Date e) {
        Date d1 = new Date(s.getYear(), s.getMonth(), s.getDay());
        Date d2 = new Date(e.getYear(), e.getMonth(), e.getDay());
        return (d2.getTime() - d1.getTime()) / 1000 / 60 / 60 / 24;
    }
}
