package com.project001.Regency.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String userName;
    private String email;
    private Long roomId;
    private Long numberOfGuests;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String phoneNumber;
    private String specialReq;
    private boolean available = true;

    public Booking() {
    }

    public Booking(Long roomId, Long numberOfGuests,LocalDate checkInDate, LocalDate checkOutDate,String phoneNumber,String specialReq,String username,String email,Long userId) {

        this.userName = username;
        this.email=email;
        this.roomId = roomId;
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.phoneNumber = phoneNumber;
        this.specialReq = specialReq;
        this.userId = userId;
    }

    // Getters & Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialReq() {
        return specialReq;
    }

    public void setSpecialReq(String specialReq) {
        this.specialReq = specialReq;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Long numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Long getId() {
        return id;
    }
    
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
