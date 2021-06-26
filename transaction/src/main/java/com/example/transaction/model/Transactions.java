package com.example.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Transactions extends Reward{
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Customer customer;
    private Double total;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date savedDate;

    public Transactions() {
        super();
    }

    public Transactions(Long id, String description, Double total, Date date) {
        this.id = id;
        this.description = description;
        this.total = total;
        this.savedDate = date;
    }
    public Transactions(Long id, Customer customer, Double total, String description, Date date) {
        super();
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.description = description;
        this.savedDate = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    @Override
    public Long getPoints() {

        this.points = 0l;
        if (this.total > 50 && this.total <= 100) {
            this.points += (this.total.intValue() - 50) * 1;

        }

        if (this.total > 100) {
            this.points += 50;
            this.points += (this.total.intValue() - 100) * 2;
        }

        return this.points;
    }
}
