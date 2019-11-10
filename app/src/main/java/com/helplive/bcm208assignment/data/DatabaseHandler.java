package com.helplive.bcm208assignment.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.helplive.bcm208assignment.Applicant;
import com.helplive.bcm208assignment.model.Allocation;
import com.helplive.bcm208assignment.model.Application;
import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.util.Constants;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final Context context;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //mhstables = {"User","Residence","Application","Allocation","Unit"};
        try {
            //create user table
            String CREATE_USER_TABLE = "CREATE TABLE " + Constants.mhstables[0] + "("
                    + Constants.USER_ID + " TEXT PRIMARY KEY,"
                    + Constants.USER_USERNAME + " TEXT,"
                    + Constants.USER_PASSWORD + " TEXT,"
                    + Constants.USER_FULLNAME + " TEXT,"
                    + Constants.USER_EMAIL + " TEXT,"
                    + Constants.USER_MONTHLYINCOME + " REAL);";

            db.execSQL(CREATE_USER_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate User: ", e.getMessage());
        }

        try {
            //create residence table
            String CREATE_RESIDENCE_TABLE = "CREATE TABLE " + Constants.mhstables[1] + "("
                    + Constants.RESIDENCE_ID + " TEXT PRIMARY KEY,"
                    + Constants.RESIDENCE_ADDRESS + " TEXT,"
                    + Constants.RESIDENCE_NOOFUNITS + " INTEGER,"
                    + Constants.RESIDENCE_SIZEPERUNIT + " INTEGER,"
                    + Constants.RESIDENCE_MONTHLYRENTAL + " REAL,"
                    + Constants.RESIDENCE_OWNER + " TEXT);";

            db.execSQL(CREATE_RESIDENCE_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate Residence: ", e.getMessage());
        }

        try {
            //create application table
            String CREATE_APPLICATION_TABLE = "CREATE TABLE " + Constants.mhstables[2] + "("
                    + Constants.APPLICATION_ID + " TEXT PRIMARY KEY,"
                    + Constants.APPLICATION_DATE + " TEXT,"
                    + Constants.APPLICATION_REQUIREDMONTH + " INTEGER,"
                    + Constants.APPLICATION_REQUIREDYEAR + " INTEGER,"
                    + Constants.APPLICATION_STATUS + " TEXT,"
                    + Constants.APPLICATION_APPLICANT + " TEXT,"
                    + Constants.APPLICATION_RESIDENCE + " TEXT);";

            db.execSQL(CREATE_APPLICATION_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate Application: ", e.getMessage());
        }

        try {
            //create allocation table
            String CREATE_ALLOCATION_TABLE = "CREATE TABLE " + Constants.mhstables[3] + "("
                    + Constants.ALLOCATION_ID + " TEXT PRIMARY KEY,"
                    + Constants.ALLOCATION_UNITNO+ " TEXT,"
                    + Constants.ALLOCATION_FROMDATE + " TEXT,"
                    + Constants.ALLOCATION_DURATION + " INTEGER,"
                    + Constants.ALLOCATION_TODATE + " TEXT);";

            db.execSQL(CREATE_ALLOCATION_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate allocation: ", e.getMessage());
        }

        try {
            //create unit table
            String CREATE_UNIT_TABLE = "CREATE TABLE " + Constants.mhstables[4] + "("
                    + Constants.UNIT_NO + " TEXT PRIMARY KEY,"
                    + Constants.UNIT_RESIDENCE + " TEXT,"
                    + Constants.UNIT_AVAILABITLITY + " INTEGER);";

            db.execSQL(CREATE_UNIT_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate unit: ", e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //delete and create for new use
            for (String db_name: Constants.mhstables) {
                db.execSQL("DROP TABLE IF EXISTS " + db_name);
            }
            onCreate(db);
        } catch (Exception e) {
            Log.d("OnUpgrade: ", e.getMessage());
        }
    }


    //User table methods
    public void addApplicant(Applicant applicant){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String sql = "SELECT COUNT(*) FROM " + Constants.mhstables[0] + "WHERE userID LIKE 'AP%'";
        Cursor cursor = db.rawQuery(sql,null);

        int currentIdNum = cursor.getInt(0) + 1;
        String newUserID = "AP" + String.format("%04d",currentIdNum);

        contentValues.put(Constants.USER_ID,newUserID);
        contentValues.put(Constants.USER_USERNAME,applicant.getUsername());
        contentValues.put(Constants.USER_PASSWORD,applicant.getPassword());
        contentValues.put(Constants.USER_FULLNAME,applicant.getFullname());
        contentValues.put(Constants.USER_EMAIL,applicant.getEmail());
        contentValues.put(Constants.USER_MONTHLYINCOME,applicant.getMonthlyIncome());

        db.insert(Constants.mhstables[0],null,contentValues);
        db.close();

    }

    public void addResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String sql = "SELECT COUNT(*) FROM " + Constants.mhstables[1] + "WHERE residenceID LIKE 'RD%'";
        Cursor cursor = db.rawQuery(sql,null);

        int currentIdNum = cursor.getInt(0) + 1;
        String newResidenceID = "RD" + String.format("%04d",currentIdNum);

        contentValues.put(Constants.RESIDENCE_ID,newResidenceID);
        contentValues.put(Constants.RESIDENCE_ADDRESS,residence.getAddress());
        contentValues.put(Constants.RESIDENCE_NOOFUNITS,residence.getNumUnits());
        contentValues.put(Constants.RESIDENCE_SIZEPERUNIT,residence.getSizePerUnit());
        contentValues.put(Constants.RESIDENCE_MONTHLYRENTAL,residence.getMonthlyRental();
        contentValues.put(Constants.RESIDENCE_OWNER,residence.getOwner();

        db.insert(Constants.mhstables[1],null,contentValues);
        db.close();
    }

    public void createApplication(Application application){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String sql = "SELECT COUNT(*) FROM " + Constants.mhstables[2] + "WHERE applicationID LIKE 'AN%'";
        Cursor cursor = db.rawQuery(sql,null);

        int currentIdNum = cursor.getInt(0) + 1;
        String newApplicationID = "AN" + String.format("%04d",currentIdNum);

        contentValues.put(Constants.APPLICATION_ID,newApplicationID);
        //issue with LocalDate
        contentValues.put(Constants.APPLICATION_DATE,application.getApplicationDate());
        contentValues.put(Constants.APPLICATION_REQUIREDMONTH,application.getRequiredMonth());
        contentValues.put(Constants.APPLICATION_REQUIREDYEAR,application.getRequiredYear());
        contentValues.put(Constants.APPLICATION_STATUS,application.getStatus());
        contentValues.put(Constants.APPLICATION_APPLICANT,application.getApplicant());
        contentValues.put(Constants.APPLICATION_RESIDENCE,application.getResidence());

        db.insert(Constants.mhstables[2],null,contentValues);
        db.close();
    }

    public void makeAllocation(Allocation allocation){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String sql = "SELECT COUNT(*) FROM " + Constants.mhstables[3] + "WHERE residenceID LIKE 'AL%'";
        Cursor cursor = db.rawQuery(sql,null);

        int currentIdNum = cursor.getInt(0) + 1;
        String newAllocationID = "AL" + String.format("%04d",currentIdNum);

        contentValues.put(Constants.ALLOCATION_ID,newAllocationID);
        contentValues.put(Constants.ALLOCATION_UNITNO,allocation.getResidenceUnit().getUnitNo());
        //issue with LocalDate
        contentValues.put(Constants.ALLOCATION_FROMDATE,allocation.getFromDate());
        contentValues.put(Constants.ALLOCATION_DURATION,allocation.getDuration());
        contentValues.put(Constants.ALLOCATION_TODATE,allocation.getToDate());


        db.insert(Constants.mhstables[3],null,contentValues);
        db.close();
    }

    public void addUnit(Residence.Unit Unit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String sql = "SELECT COUNT(*) FROM " + Constants.mhstables[4] + "WHERE unitNo LIKE 'UN%'";
        Cursor cursor = db.rawQuery(sql,null);

        int currentIdNum = cursor.getInt(0) + 1;
        String newUnitNo = "UN" + String.format("%04d",currentIdNum);
        String newAvailability = "available";

        contentValues.put(Constants.UNIT_NO,newUnitNo);
        //issue accessing inner-class methods
        contentValues.put(Constants.UNIT_RESIDENCE,Unit.getResidence());
        contentValues.put(Constants.UNIT_AVAILABITLITY,newAvailability);


        db.insert(Constants.mhstables[4],null,contentValues);
        db.close();
    }
}
