package com.helplive.bcm208assignment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.helplive.bcm208assignment.util.Constants;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final Context context;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    private ArrayList<String> mhs_tables = new ArrayList<String>();

    @Override
    public void onCreate(SQLiteDatabase db) {
        mhs_tables.add("User");
        mhs_tables.add("HousingOfficer");
        mhs_tables.add("Applicant");
        mhs_tables.add("Residence");
        mhs_tables.add("Application");
        mhs_tables.add("Allocation");
        mhs_tables.add("Unit");

        try {
            //create our own database
            String CREATE_ITEM_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                    + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                    + Constants.KEY_ITEM_NAME + " TEXT,"
                    + Constants.KEY_ITEM_COLOR + " TEXT,"
                    + Constants.KEY_ITEM_QUANTITY + " INTEGER,"
                    + Constants.KEY_ITEM_SIZE + " INTEGER,"
                    + Constants.KEY_ITEM_DATE_ADDED + " LONG);";

            db.execSQL(CREATE_ITEM_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate: ", e.getMessage());
        }

        String CREATE_USER_TABLE = "CREATE TABLE " + mhs_tables.get(0)
                + "(username TEXT, password TEXT, fullname TEXT PRIMARY KEY)";

        db.execSQL(CREATE_USER_TABLE);

        String CREATE_HO_TABLE = "CREATE TABLE " + mhs_tables.get(1)
                + "(fullname TEXT PRIMARY KEY, email TEXT, monthlyIncome REAL, payslip BLOB)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //delete and create for new use
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
            onCreate(db);
        } catch (Exception e) {
            Log.d("OnUpgrade: ", e.getMessage());
        }
    }


}
