package com.helplive.bcm208assignment.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Allocation {
    private String fromDate;
    private String endDate;
    private int duration;   // in 12 or 18 months
    private int applicationID;
    private int residenceID;
    private int unitNo;

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Allocation(Application application,
                      int duration, LocalDate fromDate) {
        setDuration(duration);
    }

    public Allocation(String fromDate, String endDate, int duration, int applicationID, int residenceID, int unitNo) {
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.duration = duration;
        this.applicationID = applicationID;
        this.residenceID = residenceID;
        this.unitNo = unitNo;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "fromDate='" + fromDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", duration=" + duration +
                ", applicationID=" + applicationID +
                ", residenceID=" + residenceID +
                ", unitNo=" + unitNo +
                '}';
    }

    public Allocation() {
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }


}