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
import javax.persistence.Table;

/**
 *
 * Storing passenger
 * @Author: Joseph Awwal
 */
@Entity
@Table(name = "PASSENGERS")
public class Passenger  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSENGER_ID")
    
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Column(name = "LASTNAME")
    private String lastName;
    
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "EMAIL")
    private String email;
    
    @ManyToMany(mappedBy = "passengers", cascade = CascadeType.ALL)
    @JoinColumn(name = "RESERVATION_ID")
    private List<Reservation> reservations;
    
    
    public Passenger(){
        reservations = new ArrayList<>();
    }
    
    public Passenger(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        
    }
    
    public void removeReservation(Reservation reservation){
        if (reservations.contains(reservations))
            reservations.remove(reservation);
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName(){
        
        return firstName;
    }
    
    public void setFirstName(String firstName){
        
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public List<Reservation> getReservations(){
        
        return reservations;
    }
    
    public void setReservations(List<Reservation> reservatons){
        
        this.reservations = reservations;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
 
 
    @Override
    public String toString() {
        return "entity.Passenger[ id=" + id + " ]";
    }
    
}
