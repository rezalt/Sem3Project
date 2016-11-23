/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;
import dao.AirportDAO;
import entity.Airport;
import exceptions.FlightException;
import exceptions.RestException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import utility.AirportScraper;
/**
 *
 * @author josephawwal
 */
public class AirportFacade {
 
    boolean airportsFetched = false;
    AirportDAO dao;
    
    public static AirportFacade instance = null;
    
    private AirportFacade(){
        dao = new AirportDAO();
        this.saveAirports();
        
    }
    
    public static AirportFacade getInstance(){
        
        if (instance == null){
            
            instance = new AirportFacade();
        }
        
        return instance;
    }
    
    public Airport getAirportByIATA(String IATA) throws RestException {
        
        try {
            return dao.getAirportByIATA(IATA);
            
        } catch (NoResultException e){
            throw new RestException("we donot supply fights specific IATA", Response.Status.NO_CONTENT);
            
        }
    }
    
    private void saveAirports(){
        
        try {
            
            dao.getAirportByIATA("CPH");
                    
        } catch (NoResultException e){
            dao.deleteAll("AIRPORTS");
            List<Airport> airports = AirportScraper.fetchAirportData();
            dao.createFromList(airports);
        }
    }
    
    public List<Airport> getAirportsByCity(String city) throws FlightException {
        return dao.getAirportsByCity(city);
        
    }
    
    public Boolean validateAirport(String iata){
        
        try {
            dao.getAirportByIATA(iata);
            return true;
        } catch (NoResultException e){
            return false;
        }
    }
    
    public List<Airport> getAirportsByUnknown(String string){
        List<Airport> results = new ArrayList();
        
        try {
            results.add(dao.getAirportByIATA(string));
            
        } catch (NoResultException e){
            System.out.println("Nothing found by iata");
        }
        
        
    
    try {
    for (Airport airport : dao.getAirportsByCity(string)){
        if (!results.contains(airport)){
            results.add(airport);
        }
    }
    
    
} catch (Exception e){
            System.out.println("Nothing found by this city code");
}
    try {
        for (Airport airport : dao.getAirportsByName(string)){
            if (!results.contains(airport)){
                results.add(airport);
            }
        }
    } catch(Exception e){
        
        System.out.println("Nothing found by this name");
    }
    return results;
}
}