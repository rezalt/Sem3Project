/*
 * To change this license header, choose License Headers in Project Properties.
 * To change thi s template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try
        {  
            String uri = "http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-01T00:00:00.000Z/1";
            URL url = new URL(uri);
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null)
            {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(FlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ProtocolException ex)
        {
            Logger.getLogger(FlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(FlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }
    
    
    @GET
    @Path("getFlight")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAFlight(String content)
    {
        
        
        
        
        return content;
    }

}



