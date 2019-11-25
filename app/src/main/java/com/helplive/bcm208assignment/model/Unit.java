package com.helplive.bcm208assignment.model;

public class Unit {

    private int unitNo;
    private int availability;
    private int residence;

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }

    public int isAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }


    public int getResidence() {
        return residence;
    }

    public void setResidence(int residence) {
        this.residence = residence;
    }

    public Unit(int unitNo) {
        setUnitNo(0);
        setAvailability(1);
    }

}
