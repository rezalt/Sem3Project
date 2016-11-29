/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Flight;
import exceptions.FlightException;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LegendaryJohn
 */
public class FlightDAO extends DataManager<Flight, Integer>
{
    
    public Flight getByFlightNumber(String flightNumber) throws FlightException {
        try{
            return (Flight) this.getManager().createNamedQuery("Flight.findFlightByFlightNumber")
           .setParameter("flightNumber", flightNumber)
           .getSingleResult();
        } catch (NoResultException ex){
            throw new FlightException("Flight not found", Response.Status.BAD_REQUEST, 3);
        }
        
    }
    
    public List<Flight> findFlights(String from, String to, Date date, Date nextDay) {
        
        return this.getManager().createNamedQuery("Flight.findFlights")
                .setParameter("origin", from)
                .setParameter("destination", to)
                .setParameter("theDay", date, TemporalType.DATE)
                .setParameter("theNextDay", nextDay, TemporalType.DATE)
                .getResultList();
    }
    
    public List<Flight> findFlights(String from, Date date, Date nextDay) {
        
        return this.getManager().createNamedQuery("Flight.findFlightsFrom")
                .setParameter("origin", from)
                .setParameter("theDay", date, TemporalType.DATE)
                .setParameter("theNextDay", nextDay, TemporalType.DATE)
                .getResultList();
    }
}
