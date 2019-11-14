package com.helplive.bcm208assignment.model;

import java.util.ArrayList;

public class Applicant extends User{
    private String email;
    private double monthlyIncome;

    private ArrayList<Application> applications;

    public Applicant(String username, String password, String fullname,
                     String email, double monthlyIncome) {
        super(username, password, fullname);
        setEmail(email);
        setMonthlyIncome(monthlyIncome);
        this.applications = new ArrayList<>();
    }

    public Applicant(){

    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the maonthlyIncome
     */
    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @parammonthlyIncome the maonthlyIncome to set
     */
    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String toString() {
        return super.toString() + "\nemail: " + getEmail() +
                " and incomde: " + getMonthlyIncome();
    }

    /**
     * @return the applications
     */
    public ArrayList<Application> getApplications() {
        return applications;
    }

    /**
     * @param applications the applications to set
     */
    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application) {
        getApplications().add(application);
    }

    public int noOfApplications() {
        return getApplications().size();
    }

    public boolean hasApplications() {
        return noOfApplications() != 0;
    }
}


