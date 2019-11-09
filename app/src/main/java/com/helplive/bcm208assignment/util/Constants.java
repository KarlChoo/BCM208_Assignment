package com.helplive.bcm208assignment.util;

public class Constants {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "mhsDB";
    public static final String[] mhstables = {"User","Residence","Application","Allocation","Unit"};

    //User table columns
    public static final String USER_ID = "user_ID";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FULLNAME = "fullname";
    public static final String USER_EMAIL = "email";
    public static final String USER_MONTHLYINCOME = "monthly_income";

    //Residence table columns
    public static final String RESIDENCE_ID = "residence_ID";
    public static final String RESIDENCE_ADDRESS = "address";
    public static final String RESIDENCE_NOOFUNITS = "number_of_units";
    public static final String RESIDENCE_SIZEPERUNIT = "size_per_unit";
    public static final String RESIDENCE_MONTHLYRENTAL = "monthly_rental";
    public static final String RESIDENCE_OWNER = "owner";

    //Application table columns
    public static final String APPLICATION_ID = "application_ID";
    public static final String APPLICATION_DATE = "application_date";
    public static final String APPLICATION_REQUIREDMONTH = "required_month";
    public static final String APPLICATION_REQUIREDYEAR = "required_year";
    public static final String APPLICATION_STATUS = "status"; //Approved = A, Waitlsted = W, Rejected = R
    public static final String APPLICATION_APPLICANT = "applicant";
    public static final String APPLICATION_RESIDENCE = "residence";


    //Allocation table columns
    public static final String ALLOCATION_APPID = "application_ID";
    public static final String ALLOCATION_UNITNO = "unit_no";
    public static final String ALLOCATION_FROMDATE = "from_date";
    public static final String ALLOCATION_DURATION = "duration";
    public static final String ALLOCATION_TODATE = "to_date";


    //Unit table columns
    public static final String UNIT_NO = "unit_no";
    public static final String UNIT_RESIDENCE = "residence";
    public static final String UNIT_AVAILABITLITY = "availability";
}
