package com.helplive.bcm208assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context){
        super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_USER_TABLE = "CREATE TABLE " +  Util.mhstables[0]
                + "(userId TEXT PRIMARY KEY,username TEXT,password TEXT,fullname TEXT" +
                ", email TEXT, monthlyIncome REAL,birthDate DATE)";

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


}
