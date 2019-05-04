package unittest;

import dto.CarDTO;
import facade.CarFacade;
import exceptions.CarException;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testutils.TestUtils;
import utils.PuSelector;

public class TestUsers {

    private static CarFacade facade;

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory emf = PuSelector.getEntityManagerFactory("pu_unit_test_mock");
        facade = CarFacade.getInstance(emf);
        TestUtils.setupTestUsers(emf);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void getAllCars() throws CarException {
        List<CarDTO> cars = facade.getAllCars();
        assertEquals(5, cars.size());
    }

    @Test // Since no booking in test data (YET!!!) 
    public void getAllCarsByPeriod() throws CarException, ParseException {
        List<CarDTO> cars = facade.getAllCarsByPeriod("04-05-2019", "13-05-2019");
        assertEquals(5, cars.size());
    }

    @Test
    public void getCarByRegNo() throws CarException {
        CarDTO car = facade.getCarByRegNo("AA12345");
        assertEquals("Fiat", car.getManufactor());
    }

}
