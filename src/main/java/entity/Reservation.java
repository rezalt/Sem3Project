/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author josephawwal
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT p FROM Reservation p"),
    @NamedQuery(name = "Reservation.findAllByUser", query = "SELECT p FROM Reservation p WHERE p.owner.userName = :userName = :userName AND p.deleted = false")
})
@Table(name = "RESERVATIONS")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private int id;

    @Column(name = "DELETED")
    private boolean deleted = false;

    @Column(name = "PRICE")
    private Double price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PASSENGER_ID")
    private List<Passenger> passengers;
    
    @ManyToOne
    private User owner;
    

    @ManyToOne
    private Flight flight;

    private String reserveName;
    private String reserveEmail;
    private String reservePhone;

    private int numOfSeats;

    public Reservation() {

        passengers = new ArrayList();
    }

    public Reservation(List<Passenger> passengers) {

        this.passengers = passengers;
    }

    public Reservation(Double price, List<Passenger> passengers, String reserveName, String reserveEmail, String reservePhone) {

        this.price = price;
        this.passengers = passengers;
        this.reserveName = reserveName;
        this.reserveEmail = reserveEmail;
        this.reservePhone = reservePhone;

    }

    public void addPassenger(Passenger passenger) {

        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        if (passengers.contains(passenger)) {
            passengers.remove(passenger);
        }
    }

    public String getReserveName() {

        return reserveName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {

        this.owner = owner;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public String getReserveEmail() {
        return reserveEmail;
    }

    public void setReserveEmail(String reserveEmail) {

        this.reserveEmail = reserveEmail;
    }

    public String getReservePhone() {
        return reservePhone;
    }

    public void setReservePhone(String reservePhone) {
        this.reservePhone = reservePhone;
    }

    public int getNumberOfSeats() {
        return numOfSeats;
    }

    public void setNumberOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    
    public Flight getFlight(){
        return flight;
    }
    
    public void setFlight(Flight flight){
        this.flight = flight;
    }
    
    public boolean isDeleted(){
        return deleted;
    }
    
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

}
