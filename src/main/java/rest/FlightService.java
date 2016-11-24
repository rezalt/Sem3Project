/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author josephawwal
 */
@Path("flight")
public class FlightService
{
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("testFlight")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAFlight()
    {
        
        return gson.toJson(null);
    }

}
