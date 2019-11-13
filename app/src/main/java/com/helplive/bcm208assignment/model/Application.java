package com.helplive.bcm208assignment.model;

import java.time.LocalDate;

public class Application {
    private static int appGenID = 1;

    private String applicationID;
    private LocalDate applicationDate;
    private int requiredMonth;
    private int requiredYear;
    private String status;

    private Allocation allocation;
    private Applicant applicant;
    private Residence residence;

    public Application(String applicationID, LocalDate appDate, int requiredMonth, int requiredYear) {
        setApplicationID(applicationID);
        setApplicationDate(appDate);
        setRequiredMonth(requiredMonth);
        setRequiredYear(requiredYear);
        setStatus("New");
    }

    /**
     * @return the appGenID
     */
    public static int getAppGenID() {
        return appGenID;
    }

    /**
     * @paramaAppGenID the appGenID to set
     */
    public static void setAppGenID(int anAppGenID) {
        appGenID = anAppGenID;
    }

    /**
     * @return the applicationID
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * @paramapplicationID the applicationID to set
     */
    //to be revised
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * @return the applicationDate
     */
    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    /**
     * @param applicationDate the applicationDate to set
     */
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * @return the requiredMonth
     */
    public int getRequiredMonth() {
        return requiredMonth;
    }

    /**
     * @param requiredMonth the requiredMonth to set
     */
    public void setRequiredMonth(int requiredMonth) {
        this.requiredMonth = requiredMonth;
    }

    /**
     * @return the requiredYear
     */
    public int getRequiredYear() {
        return requiredYear;
    }

    /**
     * @param requiredYear the requiredYear to set
     */
    public void setRequiredYear(int requiredYear) {
        this.requiredYear = requiredYear;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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

    /**
     * @return the applicant
     */
    public Applicant getApplicant() {
        return applicant;
    }

    /**
     * @paramapplicant the applicant to set
     */
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    /**
     * @return the residence
     */
    public Residence getResidence() {
        return residence;
    }

    /**
     * @param residence the residence to set
     */
    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public String toString() {
        return getApplicationID() + " submitted by " +
                getApplicant().getFullname() + " Status: " +
                getStatus();
    }
}
