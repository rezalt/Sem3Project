/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Airport;
import exceptions.FlightException;
import exceptions.RestException;
import facade.AirportFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author josephawwal
 */

@Path("airport")
public class AirportService {
    
    
    @Context
    private UriInfo context;
    private Gson gson;
    
    AirportFacade facade = AirportFacade.getInstance();
    
    public AirportService(){
        
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @GET
    @Path("{iata}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAirportByIATA(@PathParam("iata") String iata) throws RestException{
        
        Airport airport = facade.getAirportByIATA(iata);
        return gson.toJson(airport);
    }
    
    @GET
    @Path("lookup/{string}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAirportByUnknown(@PathParam("string") String string) throws FlightException{
        if (string.length() < 1)
            throw new FlightException("Minimum character length: 1", Response.Status.BAD_REQUEST, 4);
    List<Airport> res = facade.getAirportsByUnknown(string);
    
    return gson.toJson(res);
    
    }
    
    @GET
    @Path("valid/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String validateCity(@PathParam("city") String city) throws FlightException {
        if (city.length() < 1)
            throw new FlightException("A minimum character length of 1:", Response.Status.BAD_REQUEST, 4);
        
        JsonObject obj = new JsonObject();
        obj.addProperty("valid", "" + facade.validateAirport(city));
        
        return gson.toJson(obj);
        
        
    }
    
    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAirportByCity(@PathParam("city") String city) throws FlightException{
        if (city.length() < 1) 
            throw new FlightException("Minimum 1 charater.", Response.Status.BAD_REQUEST, 4);
        
        
        List<Airport> airports = facade.getAirportsByCity(city);
        
        return gson.toJson(airports);
    }
}
