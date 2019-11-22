package com.helplive.bcm208assignment.model;

import java.util.ArrayList;

public class Residence {
    //static int genID = 1;
    private int residenceID;
    private String address;
    private int numUnits;
    private int sizePerUnit;
    private double monthlyRental;

    private ArrayList<Application> applications;
    private ArrayList<Unit> units;
    private String staffID;

    public class Unit {
        private int unitNo;
        private boolean availability;
        private Allocation allocation;
        private Residence residence;

        public Residence getResidence() {
            return residence;
        }

        public void setResidence(Residence residence) {
            this.residence = residence;
        }

        public Unit(int unitNo) {
            setUnitNo(unitNo);
            setAvailability(true);
        }
        /**
         * @return the unitNo
         */
        public int getUnitNo() {
            return unitNo;
        }

        /**
         * @paramunitNo the unitNo to set
         */
        public void setUnitNo(int unitNo) {
            this.unitNo = unitNo;
        }

        /**
         * @return the availability
         */
        public boolean isAvailability() {
            return availability;
        }

        /**
         * @paramavailability the availability to set
         */
        public void setAvailability(boolean availability) {
            this.availability = availability;
        }

        public String toString() {
            return String.format("%s: Unit%03d, ", getResidenceID(), +
                    getUnitNo()) + (isAvailability()? "is available":
                    "has been allocated");
        }

        /**
         * @return the allocation
         */
        public Allocation getAllocation() {
            return allocation;
        }

        /**
         * @param allocation the allocation to set
         */
        public void setAllocation(Allocation allocation) {
            this.allocation = allocation;
        }
    }

    public Residence(String address, int numUnits, int sizePerUnit,
                     double monthlyRental) {
        setResidenceID(0);
        setAddress(address);
        setNumUnits(numUnits);
        setSizePerUnit(sizePerUnit);
        setMonthlyRental(monthlyRental);
        setApplications(new ArrayList<Application>());

        initialisedAllUnits();
    }

    public Residence(){};

    public Unit getUnit(int unitNo) {
        return getUnits().get(unitNo-1);
    }

    private void initialisedAllUnits() {
        units = new ArrayList<Unit>(getNumUnits());

        for (int i = 0; i < getNumUnits(); i++) { // units.size(); i++) {
            Unit aUnit = new Unit(i+1);
            units.add(aUnit);
        }
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

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
        return getResidenceID() + " with " + getNumUnits() +
                " units at " + getAddress();
    }

    // side effect: application added to residence
    public void addApplication(Application app) {
        getApplications().add(app);
        app.setResidence(this);

    }

    public Application findApplication(String appID) {
        for (Application anApp: getApplications())
            if (anApp.getApplicationID().equalsIgnoreCase(appID))
                return anApp;
        return null;
    }

    /**
     * @return the staffID
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * @param staffID the staffID to set
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * @return the applicatiions
     */
    public ArrayList<Application> getApplications() {
        return applications;
    }

    /**
     * @paramapplications the applicatiions to set
     */
    public void setApplications(ArrayList<Application> applicatiions) {
        this.applications = applicatiions;
    }

    public int getNoOfAvailableUnits() {
        int count = 0;
        for (Residence.Unit aUnit: getUnits())
            if (aUnit.isAvailability())
                count ++;
        return count;
    }

    public String getAvailableUnitNos() {
        if (getNoOfAvailableUnits() == 0)
            return "All units in this residence have been taken.";
        StringBuffer sb = new StringBuffer();
        for (Residence.Unit aUnit: getUnits())
            if (aUnit.isAvailability()) {
                sb.append(aUnit.getUnitNo());
                sb.append(" ");
            }
        return sb.toString();
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