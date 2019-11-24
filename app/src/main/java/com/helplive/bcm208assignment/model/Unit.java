package com.helplive.bcm208assignment.model;

public class Unit {

    private int unitNo;

    public Unit() {

    }

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

    private int availability;
    private int residence;

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
