package com.helplive.bcm208assignment.model;

import java.util.ArrayList;

public class HousingOfficer extends User{


    public HousingOfficer(String username, String password,
                          String fullname) {
        super(username, password, fullname);
    }
    public HousingOfficer(){

    }

    /*
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
    }*/

}

