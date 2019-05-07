package unittest;

import dto.BookingInformationDTO;
import dto.CarDTO;
import exceptions.BookingException;
import facade.CarFacade;
import exceptions.CarException;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testutils.TestUtils;
import utils.PuSelector;

public class CarTest {

    private static CarFacade facade;

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory emf = PuSelector.getEntityManagerFactory("pu_unit_test_mock");
        facade = CarFacade.getInstance(emf);
        TestUtils.setupTestData(emf);
    }

    @Test
    public void getAllCars() {
        List<CarDTO> cars = facade.getAllCars();
        assertEquals(5, cars.size());
    }

    @Test
    public void getAllCarsByPeriod() throws ParseException {
        List<CarDTO> cars = facade.getAllCarsByPeriod("2019-05-04", "2019-05-13");
        assertEquals(4, cars.size());
    }

    @Test(expected = ParseException.class)
    public void getAllCarsByPeriodFail() throws CarException, ParseException {
        facade.getAllCarsByPeriod("20190504", "2019-05-13");
    }

    @Test
    public void getCarByRegNo() throws CarException {
        CarDTO car = facade.getCarByRegNo("AA12345");
        assertEquals("Fiat", car.getManufactor());
    }

    @Test(expected = CarException.class)
    public void getCarByRegNoFail() throws CarException {
        facade.getCarByRegNo("FF12345");
    }

    @Test
    public void rentCar() throws BookingException {
        BookingInformationDTO booking = facade.rentCar("AA12345", "13-05-2019", "14-05-2019");
        assertEquals("AA12345", booking.getCar().getRegno());
    }

    @Test(expected = BookingException.class)
    public void rentCarFail1() throws BookingException {
        facade.rentCar("AA12345", "13052019", "14-05-2019");
    }

    @Test(expected = BookingException.class)
    public void rentCarFail2() throws BookingException {
        facade.rentCar("XXXXXXX", "13-05-2019", "14-05-2019");
    }

}
