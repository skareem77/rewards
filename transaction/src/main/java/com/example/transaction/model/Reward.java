package com.example.transaction.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Transient;

public abstract class Reward {

    @JsonInclude
    @Transient
    protected Long points;

    public abstract Long getPoints();
}
