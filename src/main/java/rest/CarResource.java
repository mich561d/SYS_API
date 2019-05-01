package rest;

import com.google.gson.Gson;
import exceptions.CarException;
import facade.CarFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.PuSelector;

@Path("car")
public class CarResource {

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    CarFacade f = CarFacade.getInstance(PuSelector.getEntityManagerFactory("pu"));
    Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public String test() {
        return "You passed the test!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allCars() {
        try {
            return gson.toJson(f.getAllCars());
        } catch (CarException ex) {
            return gson.toJson(ex);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{regNo}")
    public String getFromUser(@PathParam("regNo") String regNo) {
        try {
            return gson.toJson(f.getCarByRegNo(regNo));
        } catch (CarException ex) {
            return gson.toJson(ex);
        }
    }
}
