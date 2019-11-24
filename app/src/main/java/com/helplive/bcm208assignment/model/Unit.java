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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    private boolean availability;
    private Residence residence;

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public Unit(int unitNo) {
        setUnitNo(0);
        setAvailability(true);
    }
}
