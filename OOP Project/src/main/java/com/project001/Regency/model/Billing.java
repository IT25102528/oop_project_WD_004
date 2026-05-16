package com.project001.Regency.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "billings")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private Long userId;

    private double amount;

    private String status; // PAID / UNPAID

    private LocalDate billingDate;

    private String userName;


    public Billing() {
    }

    public Billing(Long bookingId, double amount, String status, LocalDate billingDate,Long userId,String userName) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
        this.billingDate = billingDate;
        this.userId = userId;
        this.userName = userName;
    }

    // Getters & Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }
}

