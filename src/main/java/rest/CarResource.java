package rest;

import com.google.gson.Gson;
import exceptions.CarException;
import exceptions.GenericExceptionMapper;
import facade.CarFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    GenericExceptionMapper m = new GenericExceptionMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public Response test() {
        return Response.ok().entity(gson.toJson("You passed the test!")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response allCars() {
        try {
            return Response.ok().entity(gson.toJson(f.getAllCars())).build();
        } catch (CarException ex) {
            return m.toResponse(ex);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{regNo}")
    public Response getFromUser(@PathParam("regNo") String regNo) {
        try {
            return Response.ok().entity(gson.toJson(f.getCarByRegNo(regNo))).build();
        } catch (CarException ex) {
            return m.toResponse(ex);
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("rent?regNo={regNo}&start={start}&end={end}")
    public String rentCar(@PathParam("regNo") String regNo, @PathParam("start") String start, @PathParam("end") String end) {
        return "hola from spain!";
    }
}
