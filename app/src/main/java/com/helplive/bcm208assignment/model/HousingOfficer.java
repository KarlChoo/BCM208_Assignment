package com.helplive.bcm208assignment.model;

import java.util.ArrayList;

public class HousingOfficer extends User{
    static int genNum = 1;
    private String staffID;

    private ArrayList<Residence> residences;

    public HousingOfficer(String username, String password,
                          String fullname) {
        super(username, password, fullname);
        setStaffID();
        this.residences = new ArrayList<>();
    }

    public void setStaffID() {
        staffID = String.format("SN%03d", genNum++);
    }

    public String getStaffID() {
        return staffID;
    }

    public static void main(String[] args) {
        HousingOfficer[] s = new HousingOfficer[150];
        for (int i = 0; i < s.length; i++)
            s[i] = new HousingOfficer("un" + (i+1),
                    String.format("%03d", i+1), "Name" + i);

        System.out.println(s[0]);
        System.out.println(s[5]);
        System.out.println(s[15]);
        System.out.println(s[90]);
        System.out.println(s[109]);
    }

    /**
     * @return the residences
     */
    public ArrayList<Residence> getResidences() {
        return residences;
    }

    /**
     * @paramresidences the residences to set
     */
    public void setResidences(ArrayList<Residence> residences) {
        this.residences = residences;
    }

    public void addResidence(Residence r) {
        getResidences().add(r);
    }

    public int noOfResidences() {
        return getResidences().size();
    }
}

