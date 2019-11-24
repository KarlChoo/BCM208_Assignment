package com.helplive.bcm208assignment.model;

import java.time.LocalDate;

public class Application {
    //private static int appGenID = 1;

    private int applicationID;
    private String applicationDate;
    private int requiredMonth;
    private int requiredYear;
    private String status;
    private String applicant;
    private int residenceID;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }



    public Application(int applicationID, String appDate, int requiredMonth, int requiredYear) {
        setApplicationID(applicationID);
        setApplicationDate(appDate);
        setRequiredMonth(requiredMonth);
        setRequiredYear(requiredYear);
        setStatus("New");
    }

    public Application() {
        setApplicationID(0);
        setApplicationDate("not set");
        setRequiredMonth(0);
        setRequiredYear(0);
        setStatus("not set");
    }


    /**
     * @return the applicationID
     */
    public int getApplicationID() {
        return applicationID;
    }

    /**
     * @paramapplicationID the applicationID to set
     */
    //to be revised
    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * @return the applicationDate
     */
    public String getApplicationDate() {
        return applicationDate;
    }

    /**
     * @param applicationDate the applicationDate to set
     */
    public void setApplicationDate(String applicationDate) {
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


    public String toString() {
        return  "Application ID:" + getApplicationID() + "| "
                +"Application Date: " + getApplicationDate() + "| "
                + "Required Month: "+ getRequiredMonth() +"| "
                + "Required Year:" + getRequiredYear() + "| "
                + "Status: " + getStatus();
    }
}
