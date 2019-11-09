package com.helplive.bcm208assignment.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Allocation {
    private LocalDate fromDate;
    private LocalDate endDate;
    private int duration;   // in 12 or 18 months

    private Application application;
    private Residence.Unit residenceUnit;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Allocation(Application application, Residence.Unit residenceUnit,
                      int duration, LocalDate fromDate) {
        setApplication(application);
        setResidenceUnit(residenceUnit);
        setDuration(duration);
        setFromDate(fromDate);
        setEndDate(fromDate.plusMonths(duration).minusDays(1));
    }

    /**
     * @return the fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
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

    /**
     * @return the application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * @return the residenceUnit
     */
    public Residence.Unit getResidenceUnit() {
        return residenceUnit;
    }

    /**
     * @param residenceUnit the residenceUnit to set
     */
    public void setResidenceUnit(Residence.Unit residenceUnit) {
        this.residenceUnit = residenceUnit;
    }

    public String toString() {
        return "Residence " + getResidenceUnit().toString() +
                " to application id of " + getApplication().getApplicationID() +
                ", submitted by " + getApplication().getApplicant().getFullname() +
                "\n  for " + getDuration() + " months" +
                " from " + getFromDate() + " to " + getEndDate();

    }

}