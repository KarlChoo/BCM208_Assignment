package com.helplive.bcm208assignment;

public class Applicant extends User {
    private String email;
    private int monthlyIncome;

    public Applicant(String username,String password,String fullname,String email,int monthlyIncome){
        super(username, password, fullname);
        setEmail(email);
        setMonthlyIncome(monthlyIncome);
    }
/*
    public Applicant(){
        this("not set","not set","not set","not set",0);
    }
*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String toString(){
        return super.toString() + " | Email: " + getEmail()
                + " | Monthly Income: RM" + getMonthlyIncome();

    }
}
