package unittest;

import facade.CarFacade;
import exceptions.AuthenticationException;
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

//    @Test
//    public void getUserValid() throws AuthenticationException {
////        User u = facade.getVeryfiedUser("user", "test");
////        assertEquals("user", u.getUserName());
//    }
//
//    @Test(expected = AuthenticationException.class)
//    public void getUserInValid() throws AuthenticationException {
////        User u = facade.getVeryfiedUser("user", "testxxxx");
////        assertEquals("user", u.getUserName());
//    }

}
