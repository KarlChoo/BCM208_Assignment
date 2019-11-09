package com.helplive.bcm208assignment;


public class Util {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MHS_db";
    public static final String[] mhstables = {"User","Residence","Application","Allocation","Unit"};

    //User table columns
    public static final String USER_ID = "userID";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FULLNAME = "fullname";
    public static final String USER_EMAIL = "email";
    public static final String USER_MONTHLYINCOME = "monthlyIncome";

    //Residence table columns
    public static final String RESIDENCE_ID = "residenceID";
    public static final String RESIDENCE_ADDRESS = "address";
    public static final String RESIDENCE_NOOFUNITS = "numberOfUnits";
    public static final String RESIDENCE_SIZEPERUNIT = "sizePerUnit";
    public static final String RESIDENCE_MONTHLYRENTAL = "monthlyRental";
    public static final String RESIDENCE_OWNER = "owner";

    //Application table columns
    public static final String APPLICATION_ID = "applicationID";
    public static final String APPLICATION_DATE = "applicationDate";
    public static final String APPLICATION_REQUIREDMONTH = "requiredMonth";
    public static final String APPLICATION_REQUIREDYEAR = "requiredYear";
    public static final String APPLICATION_STATUS = "status";
    public static final String APPLICATION_APPLICANT = "applicant";
    public static final String APPLICATION_RESIDENCE = "residence";


    //Allocation table columns
    public static final String ALLOCATION_APPID = "applicationID";
    public static final String ALLOCATION_UNITNO = "unitNo";
    public static final String ALLOCATION_FROMDATE = "fromDate";
    public static final String ALLOCATION_DURATION = "duration";
    public static final String ALLOCATION_TODATE = "toDate";



    //Unit table columns
}
