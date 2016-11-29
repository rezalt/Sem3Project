/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * Viser et givent fly der flyver fra og til en destination
 */
@Entity
@Table(name = "FLIGHTS" ) 
@NamedQueries({
    @NamedQuery(name = "Flight.findFlights", query = "SELECT p FROM Flight p WHERE p.origin = :origin AND p.destination = :destination AND p.date >= :theDay AND p.date < :theNextDay"),
    @NamedQuery(name = "Flight.findFlightsFrom", query = "SELECT p FROM Flight p WHERE p.origin = :origin AND p.date >= :theDay AND p.date < :theNextDay"),
@NamedQuery(name = "Flight.findFlightByFlightNumber", query = "SELECT p FROM Flight p WHERE p.flightNumber = :flightNumber")})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    int id;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    @Column(name = "NUMBER_OF_SEATS")
    private int noOfSeats;

    @Column(name = "TRAVELTIME")
    private int travelTime;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "TRAVELDATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @OneToMany(mappedBy = "flight")
    private List<Reservation> reservations;

    public Flight() {

    }

    public Flight(String origin, String destination, String flightNumber, int noOfSeats, int travelTime, Double price, Date date) {

        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.noOfSeats = noOfSeats;
        this.travelTime = travelTime;
        this.price = price;
        this.date = date;
    }

    public List<Reservation> getReservations() {
        return reservations;

    }

    public void setReservations(List<Reservation> reservations) {

        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {

        reservations.add(reservation);
    }

    public int getTravelTime() {

        return travelTime;

    }

    public void setTravelTime(int travelTime) {

        this.travelTime = travelTime;
    }

    public Date getTravelDate() {

        return date;

    }

    public void setTravelDate(Date travelDate) {

        this.date = travelDate;

    }

    public void getDataFrom(String dataForm) {

        this.origin = dataForm;

    }

    public String getDataTo() {

        return destination;

    }

    public void setDataTo(String dataTo) {

        this.destination = dataTo;

    }

    public Double getPrice() {

        return price;

    }

    public void setPrice(Double price) {

        this.price = price;
    }

    public String getFlightNumber() {

        return flightNumber;

    }

    public void setFlightNumber(String flightNumber) {

        this.flightNumber = flightNumber;

    }

    public int getNoOfSeats() {

        return noOfSeats;
    }

    public String toString() {
        String result = "Data Code From = " + this.origin + ", Data Code To = " + this.destination
                + ", price = " + this.price + ", Flight Number = " + this.flightNumber + ", Number Of Seats = " + this.noOfSeats + ", travel Date = "
                + this.date + ", Travel Time = " + this.travelTime;
        return result;
    }
}
