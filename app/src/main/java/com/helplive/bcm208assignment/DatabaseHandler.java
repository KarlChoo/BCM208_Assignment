package com.helplive.bcm208assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context){
        super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " +  Util.mhstables[0]
                + "(" + Util.USER_ID +"TEXT PRIMARY KEY," + Util.USER_USERNAME + " TEXT, " + Util.USER_PASSWORD + " TEXT,"
                + Util.USER_FULLNAME + " TEXT," + Util.USER_EMAIL +" TEXT, " + Util.USER_MONTHLYINCOME + " REAL)";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXIST ");
        for (String TableName: Util.mhstables) {
            db.execSQL(DROP_TABLE, new String[]{TableName});
        }
        onCreate(db);
    }

    //User table methods
    public void addApplicant(Applicant applicant){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String sql = "SELECT COUNT(*) FROM " + Util.mhstables[0] + "WHERE userID LIKE 'AP%'";
        Cursor cursor = db.rawQuery(sql,null);

        int currentIdNum = cursor.getInt(0) + 1;
        String newUserID = "AP" + String.format("%04d",currentIdNum);

        contentValues.put(Util.USER_ID,newUserID);
        contentValues.put(Util.USER_USERNAME,applicant.getUsername());
        contentValues.put(Util.USER_PASSWORD,applicant.getPassword());
        contentValues.put(Util.USER_FULLNAME,applicant.getFullname());
        contentValues.put(Util.USER_EMAIL,applicant.getEmail());
        contentValues.put(Util.USER_MONTHLYINCOME,applicant.getMonthlyIncome());

        db.insert(Util.mhstables[0],null,contentValues);
        db.close();

    }

}
