package com.helplive.bcm208assignment.model;

import java.util.ArrayList;

public class Residence {
    //static int genID = 1;
    private int residenceID;
    private String address;
    private int numUnits;
    private int sizePerUnit;
    private double monthlyRental;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    private String staffID;


    public Residence(String address, int numUnits, int sizePerUnit,
                     double monthlyRental) {
        setResidenceID(0);
        setAddress(address);
        setNumUnits(numUnits);
        setSizePerUnit(sizePerUnit);
        setMonthlyRental(monthlyRental);

    }

    public Residence(){};


    /**
     * @return the residenceID
     */
    public int getResidenceID() {
        return residenceID;
    }

    /**
     * @paramresidenceID the residenceID to set
     */
    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @paramaddress the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the numUnits
     */
    public int getNumUnits() {
        return numUnits;
    }

    /**
     * @paramnumUnits the numUnits to set
     */
    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    /**
     * @return the sizePerUnit
     */
    public int getSizePerUnit() {
        return sizePerUnit;
    }

    /**
     * @paramsizePerUnit the sizePerUnit to set
     */
    public void setSizePerUnit(int sizePerUnit) {
        this.sizePerUnit = sizePerUnit;
    }

    /**
     * @return the monthlyRental
     */
    public double getMonthlyRental() {
        return monthlyRental;
    }

    /**
     * @parammonthlyRental the monthlyRental to set
     */
    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    @Override
    public String toString() {
        return  "ResidenceID: "+ getResidenceID() + "| "
                + "Address: "+ getAddress() + "| "
                + "Monthly Rental: RM"+ String.format("%.2f",getMonthlyRental()) + "| "
                + "Size per Unit: "+ getSizePerUnit() + "| "
                + "No of units: " + getNumUnits();
        /*
        return "ResidenceID " + getResidenceID() + ", with " + getNumUnits() +
                " unit(s) at " + getAddress();*/
    }



/*
    public static void main(String[] args) {
        Residence sunway = new Residence("SUNWAY", 20, 200, 250);

        sunway.getUnit(5).setAvailability(false);
        sunway.getUnit(19).setAvailability(false);

        ArrayList<Unit> units = sunway.getUnits();
        for (Unit u: units)
            System.out.println(u);

        System.out.println("Available unit numbers:\n" + sunway.getAvailableUnitNos());
    }
*/
}