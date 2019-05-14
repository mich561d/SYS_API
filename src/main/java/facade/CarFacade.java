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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
            if (cars.isEmpty()) {
                throw new CarException("A problem occurred while trying to get all cars! Please contact IT Support!");
            }
            for (Car car : cars) {
                CarDTO dto = new CarDTO(car);
                carsDTO.add(dto);
            }
        } finally {
            em.close();
        }
        return carsDTO;
    }

    public List<CarDTO> getAllCarsByPeriod(String start, String end) throws CarException {
        EntityManager em = emf.createEntityManager();
        List<CarDTO> carsDTO = new ArrayList();
        try {
            List<Car> cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
            if (cars.isEmpty()) {
                SetupTestData.createData();
                cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
            }
            List<BookingInformation> bookings = em.createNamedQuery("BookingInformation.findAll", BookingInformation.class).getResultList();
            Date startDate = StringToDate(start);
            Date endDate = StringToDate(end);
            for (BookingInformation booking : bookings) {
                Date s = booking.getStartPeriod();
                Date e = booking.getEndPeriod();
                boolean inside = startDate.after(s) && endDate.before(e);
                boolean through = startDate.before(s) && endDate.after(e);
                boolean atStart = startDate.before(s) && endDate.after(s);
                boolean atEnd = startDate.before(e) && endDate.after(e);
                if (inside || through || atStart || atEnd) {
                    cars.removeIf(car -> (car.getRegno().equals(booking.getCar().getRegno())));
                }
            }
            for (Car car : cars) {
                CarDTO dto = new CarDTO(car);
                carsDTO.add(dto);
            }
        } catch (ParseException ex) {
            throw new CarException("Date format is unsupported!");
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

    public BookingInformationDTO rentCar(String regNo, String start, String end) throws BookingException {
        EntityManager em = emf.createEntityManager();
        BookingInformationDTO bookingInformationDTO;
        try {
            em.getTransaction().begin();
            Car car = em.find(Car.class, regNo);
            if (car == null) {
                throw new BookingException("There is no car with the regNo: " + regNo);
            }
            Date s = StringToDate(start);
            Date e = StringToDate(end);
            // Re check availability
            List<BookingInformation> bookings = em.createNamedQuery("BookingInformation.findAll", BookingInformation.class).getResultList();
            for (BookingInformation booking : bookings) {
                if (booking.getCar().getRegno().equals(regNo)) {
                    Date bookingStartDate = booking.getStartPeriod();
                    Date bookingEndDate = booking.getEndPeriod();
                    boolean inside = s.after(bookingStartDate) && e.before(bookingEndDate);
                    boolean through = s.before(bookingStartDate) && e.after(bookingEndDate);
                    boolean atStart = s.before(bookingStartDate) && e.after(bookingStartDate);
                    boolean atEnd = s.before(bookingEndDate) && e.after(bookingEndDate);
                    if (inside || through || atStart || atEnd) {
                        throw new BookingException("This car (" + car.getRegno() + ") and these two dates (" + s + " to " + e + ") is already booked!");
                    }
                }
            }
            double price = car.getPrice() * getDays(s, e);
            s.setHours(10);
            e.setHours(8);
            BookingInformation bi = new BookingInformation(s, e, Calendar.getInstance(TimeZone.getTimeZone("da_DK")).getTime(), price);
            bi.setCar(car);
            em.persist(bi);
            bookingInformationDTO = new BookingInformationDTO(bi);
            em.getTransaction().commit();
        } catch (ParseException e) {
            throw new BookingException("One or Both of the dates are invalid!");
        } catch (BookingException e) {
            throw new BookingException(e.getMessage());
        } finally {
            em.close();
        }
        return bookingInformationDTO;
    }

    private Date StringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        return dateObj;
    }

    private long getDays(Date s, Date e) throws BookingException {
        if (e.before(s)) {
            throw new BookingException("Start date:" + s + " and end date: " + e + " is not a valid period");
        }
        Date d1 = new Date(s.getYear(), s.getMonth(), s.getDay(), 0, 0, 0);
        Date d2 = new Date(e.getYear(), e.getMonth(), e.getDay(), 0, 0, 0);
        long t1 = d1.getTime();
        long t2 = d2.getTime();
        if (t2 < t1) {
            throw new BookingException("While calculating days between dates an error occurred! t2:" + t2 + " - t1:" + t1 + " = " + (t2 - t1));
        }
        System.out.println("t2:" + t2);
        System.out.println("t1:" + t1);
        System.out.println("t2-t1:" + (t2 - t1));
        long seconds = (t2 - t1) / 1000;
        System.out.println("seconds:" + seconds);
        long minutes = seconds / 60;
        System.out.println("minutes:" + minutes);
        long hours = minutes / 60;
        System.out.println("hours:" + hours);
        long days = hours / 24;
        System.out.println("days:" + days);
        if (days < 0) {
            throw new BookingException("While calculating days between dates an error occurred! Days is lower than 0 (days: " + days + ")");
        }
        return days;
    }

    public boolean cancelBooking(int bookingId) throws BookingException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            BookingInformation booking = em.find(BookingInformation.class, bookingId);
            if (booking == null) {
                throw new BookingException("There is no booking with the id: " + bookingId);
            }
            boolean hasStarted = booking.getStartPeriod().after(Calendar.getInstance(TimeZone.getTimeZone("da_DK")).getTime());
            if (hasStarted) {
                throw new BookingException("This booking has already started therefore it cannot be cancelled!");
            }
            em.remove(booking);
            em.getTransaction().commit();
        } catch (BookingException e) {
            throw new BookingException(e.getMessage());
        } finally {
            em.close();
        }
        return true;
    }

}
