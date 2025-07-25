package com.example.demo.HR.Model;

import java.sql.Date;

public class TravelRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTravel_location() {
        return travel_location;
    }

    public void setTravel_location(String travel_location) {
        this.travel_location = travel_location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTravel_type() {
        return travel_type;
    }

    public void setTravel_type(String travel_type) {
        this.travel_type = travel_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    private String name;
    private String travel_location;
    private String reason;
    private String travel_type;
    private String status;
    private java.sql.Date start_date; // New
    private java.sql.Date end_date;
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }// New




}
