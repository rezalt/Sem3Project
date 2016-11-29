/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Airport;
import java.util.List;

/**
 *
 * @author LegendaryJohn
 */

public class AirportDAO extends DataManager<Airport, Integer>
{
    /**
     * Fetches an airport by IATA code.
     * @param IATA      IATA code to lookup with
     * @return          Airport object
     */
    public Airport getAirportByIATA(String IATA) {
        Airport airport = (Airport) this.getManager().createNamedQuery("Airport.findAirportByIATA")
            .setParameter("IATAcode", IATA)
            .getSingleResult();
        
        return airport;
    }
    
    /**
     * Returns an airport by name.
     * @param name
     * @return 
     */
    public Airport getAirportByName(String name) {
        
        Airport airports = (Airport) this.getManager().createNamedQuery("Airport.findAirportByName")
            .setParameter("name", name)
            .getSingleResult();
        
        return airports;
    }
    
    /**
     * Returns a list of airports by name.
     * @param name
     * @return 
     */
    public List<Airport> getAirportsByName(String name) {
        
        List<Airport> airports = this.getManager().createNamedQuery("Airport.findAirportsByName")
            .setParameter("name", name + "%")
            .getResultList();
        
        return airports;
    }
    
    /**
     * Finds an aiport by cityname.
     * @param city          The airport city to look for
     * @return              The found airport
     */
    public List<Airport> getAirportByCity(String city) {
        
        List<Airport> airports = this.getManager().createNamedQuery("Airport.findAirportByCity")
            .setParameter("city", city)
            .getResultList();
        
        return airports;
    }
    
    /**
     * Fetches an airport by city.
     * @param city      City to lookup.
     * @return          Airport as object
     */
    public List<Airport> getAirportsByCity(String city) {
        
        List<Airport> airports = this.getManager().createNamedQuery("Airport.findAirportsByCity")
            .setParameter("city", city  + "%")
            .getResultList();
        
        return airports;
    }
}
