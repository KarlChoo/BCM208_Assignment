package com.helplive.bcm208assignment;

public class HousingOfficer extends User{

    private String staffID;
    private static int genNum = 0;

    public HousingOfficer(String username,String password,String fullname){
        super(username,password,fullname);
        String inStaffID = "HO" + String.format("%03d",++genNum);
        setStaffID(inStaffID);// format of staffID : HOXXX
    }


    public String getStaffID() {
        return staffID;
    }

    private void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public static int getGenNum() {
        return genNum;
    }

    public static void setGenNum(int genNum) {
        HousingOfficer.genNum = genNum;
    }

    public String toString() {
        return super.toString()
                + " | Staff ID: " + getStaffID();
    }
}
