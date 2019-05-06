package unittest;

import dto.CarDTO;
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
        TestUtils.setupTestUsers(emf);
    }

    @Test
    public void getAllCars() {
        List<CarDTO> cars = facade.getAllCars();
        assertEquals(5, cars.size());
    }

    @Test // Since no booking in test data (YET!!!) 
    public void getAllCarsByPeriod() throws ParseException {
        List<CarDTO> cars = facade.getAllCarsByPeriod("2019-05-04", "2019-05-13");
        assertEquals(5, cars.size());
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

}