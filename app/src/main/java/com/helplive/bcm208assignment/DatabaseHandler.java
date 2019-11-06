package com.helplive.bcm208assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private ArrayList<String> mhs_tables = new ArrayList<String>();

    public DatabaseHandler(Context context){
        super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        mhs_tables.add("User");
        mhs_tables.add("HousingOfficer");
        mhs_tables.add("Applicant");
        mhs_tables.add("Residence");
        mhs_tables.add("Application");
        mhs_tables.add("Allocation");
        mhs_tables.add("Unit");


        String CREATE_USER_TABLE = "CREATE TABLE " + mhs_tables.get(0)
                + "(username TEXT, password TEXT, fullname TEXT PRIMARY KEY)";

        db.execSQL(CREATE_USER_TABLE);

        String CREATE_HO_TABLE = "CREATE TABLE " + mhs_tables.get(1)
                + "(fullname TEXT PRIMARY KEY, email TEXT, monthlyIncome REAL, payslip BLOB)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
