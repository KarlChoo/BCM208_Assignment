package com.helplive.bcm208assignment.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.helplive.bcm208assignment.model.User;
import com.helplive.bcm208assignment.model.Allocation;
import com.helplive.bcm208assignment.model.Applicant;
import com.helplive.bcm208assignment.model.Application;
import com.helplive.bcm208assignment.model.HousingOfficer;
import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.util.Constants;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final Context context;
    private final SimpleDateFormat dateToStrDB = new SimpleDateFormat("yyyy-MM-dd");

    public DatabaseHandler(Context context) {
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
                    + Constants.RESIDENCE_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + Constants.RESIDENCE_ADDRESS + " TEXT,"
                    + Constants.RESIDENCE_NOOFUNITS + " INTEGER,"
                    + Constants.RESIDENCE_SIZEPERUNIT + " INTEGER,"
                    + Constants.RESIDENCE_MONTHLYRENTAL + " REAL,"
                    + Constants.RESIDENCE_OWNER_ID + " TEXT);";

            db.execSQL(CREATE_RESIDENCE_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate Residence: ", e.getMessage());
        }

        try {
            //create application table
            String CREATE_APPLICATION_TABLE = "CREATE TABLE " + Constants.mhstables[2] + "("
                    + Constants.APPLICATION_ID + " INTEGER PRIMARY KEY NOT NULL,"
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
                    + Constants.ALLOCATION_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + Constants.ALLOCATION_UNITNO+ " TEXT,"
                    + Constants.ALLOCATION_FROMDATE + " TEXT,"
                    + Constants.ALLOCATION_DURATION + " INTEGER,"
                    + Constants.ALLOCATION_ENDDATE + " TEXT);";

            db.execSQL(CREATE_ALLOCATION_TABLE);
        } catch (Exception e) {
            Log.d("OnCreate allocation: ", e.getMessage());
        }

        try {
            //create unit table
            String CREATE_UNIT_TABLE = "CREATE TABLE " + Constants.mhstables[4] + "("
                    + Constants.UNIT_NO + " INTEGER NOT NULL,"
                    + Constants.UNIT_RESIDENCE_ID + " INTEGER,"
                    + Constants.UNIT_RESIDENCE_ID + " INTEGER,"
                    + Constants.UNIT_AVAILABITLITY + " INTEGER,"
                    +" PRIMARY KEY ("+ Constants.UNIT_NO + "," + Constants.UNIT_RESIDENCE_ID +"));";

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


    //Use if only no data in table
    public void initializeData() {
        SQLiteDatabase db = getWritableDatabase();
        try{
            /**
             * INSERT INTO user (user_ID,username,password,fullname,email,monthly_income)
             * VALUES
             * ("AP0005","another","123","sndkknadnka","xzcnznck",32),
             * ("HO0005","two","123","dasasdad","xcaddqwd",65);
             */
        }catch (Exception e){
            Log.d("Initialize fail:",e.getMessage());
        }
        db.close();
    }

    public void deleteData(String tablename){
        SQLiteDatabase db = getWritableDatabase();
        try{
            String sql = "DELETE * FROM " + tablename + ";";
            db.execSQL(sql);
        }catch (Exception e){
            Log.d("Delete table data: " , e.getMessage());
        }
        db.close();
    };

    public void manipulateDB(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }catch (Exception e){
            Log.d("Manipulate: " , e.getMessage());
        }
        db.close();
    }

    /**
     *
     *
     *     User table methods
     *
     *
     *
      */
    public void addApplicant(Applicant applicant){
        SQLiteDatabase db = this.getWritableDatabase();
        try{

            ContentValues contentValues = new ContentValues();

            //Generate applicant user id
            String sql = "SELECT * FROM " + Constants.mhstables[0] + " WHERE user_ID LIKE 'AP%';";
            Cursor cursor = db.rawQuery(sql,null);
            int currentIdNum = cursor.getCount();
            String newUserID = "AP" + String.format("%04d",++currentIdNum);

            contentValues.put(Constants.USER_ID,newUserID);
            //contentValues.put(Constants.USER_ID,"AP0001");
            contentValues.put(Constants.USER_USERNAME,applicant.getUsername());
            contentValues.put(Constants.USER_PASSWORD,applicant.getPassword());
            contentValues.put(Constants.USER_FULLNAME,applicant.getFullname());
            contentValues.put(Constants.USER_EMAIL,applicant.getEmail());
            contentValues.put(Constants.USER_MONTHLYINCOME,applicant.getMonthlyIncome());

            db.insert(Constants.mhstables[0],null,contentValues);

        } catch (Exception e) {
            Log.d("Add Applicant: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
    }

    public void addHousingOfficer(HousingOfficer housingOfficer){
        SQLiteDatabase db = this.getWritableDatabase();
        try{

            ContentValues contentValues = new ContentValues();


            String sql = "SELECT * FROM " + Constants.mhstables[0] + " WHERE "+Constants.USER_ID+" LIKE 'HO%'";
            Cursor cursor = db.rawQuery(sql,null);

            int currentIdNum = cursor.getCount();
            String newUserID = "HO" + String.format("%04d",++currentIdNum);

            contentValues.put(Constants.USER_ID,newUserID);
            //contentValues.put(Constants.USER_ID,"AP0001");
            contentValues.put(Constants.USER_USERNAME,housingOfficer.getUsername());
            contentValues.put(Constants.USER_PASSWORD,housingOfficer.getPassword());
            contentValues.put(Constants.USER_FULLNAME,housingOfficer.getFullname());

            db.insert(Constants.mhstables[0],null,contentValues);

        } catch (Exception e) {
            Log.d("Add Housing Officer: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
    }

    public User authenticate(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.mhstables[0],// Selecting Table
                new String[]{Constants.USER_ID, Constants.USER_USERNAME, Constants.USER_PASSWORD,Constants.USER_FULLNAME},//Selecting columns want to query
                Constants.USER_USERNAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null, null, null);


        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));
            user1.setUserID(cursor.getString(0));


            //Match both passwords check they are same or not

            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean validateUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            //String sql = "SELECT * FROM " + Constants.mhstables[0] + ";";
            //Cursor cursor = db.rawQuery(sql, null);

            Cursor cursor = db.query(Constants.mhstables[0],// Selecting Table
                    new String[]{Constants.USER_ID, Constants.USER_USERNAME, Constants.USER_PASSWORD,Constants.USER_FULLNAME},//Selecting columns want to query
                    Constants.USER_USERNAME + "=?",
                    new String[]{username},//Where clause
                    null, null, null);


            if(cursor != null && cursor.moveToNext()){
                return false;
            }
        }catch (Exception e){
            Log.d("Validate username:",e.getMessage());
        }
        db.close();
        return true;
    }


    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectSQL = "SELECT * FROM " + Constants.mhstables[0];

        Cursor cursor = db.rawQuery(selectSQL,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(0).substring(0,1).equalsIgnoreCase("AP")){
                    Applicant applicant = new Applicant();
                    applicant.setUserID(cursor.getString(0));
                    applicant.setUsername(cursor.getString(1));
                    applicant.setPassword(cursor.getString(2));
                    applicant.setFullname(cursor.getString(3));
                    applicant.setEmail(cursor.getString(4));
                    applicant.setMonthlyIncome(cursor.getDouble(5));
                    users.add(applicant);
                }else if (cursor.getString(0).substring(0,1).equalsIgnoreCase("HO")){
                    HousingOfficer ho = new HousingOfficer();
                    ho.setUserID(cursor.getString(0));
                    ho.setUsername(cursor.getString(1));
                    ho.setPassword(cursor.getString(2));
                    ho.setFullname(cursor.getString(3));
                    users.add(ho);
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    /**
     *
     *
     *     Residence table methods
     *
     *
     *
     */
    public void ADDResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();
        try{

            ContentValues contentValues = new ContentValues();

            contentValues.put(Constants.RESIDENCE_ADDRESS,residence.getAddress());
            contentValues.put(Constants.RESIDENCE_NOOFUNITS,residence.getNumUnits());
            contentValues.put(Constants.RESIDENCE_SIZEPERUNIT,residence.getSizePerUnit());
            contentValues.put(Constants.RESIDENCE_MONTHLYRENTAL,residence.getMonthlyRental());
            contentValues.put(Constants.RESIDENCE_OWNER_ID,residence.getStaffID());

            db.insert(Constants.mhstables[1],null,contentValues);

            addUnit(residence.getNumUnits(),residence.getResidenceID());


        } catch (Exception e) {
            Log.d("ADD Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
    }

    public Residence GetResidence(int residenceID) {
        Residence residence = new Residence();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            //read object from database

            //"=?" refers to the String id
            Cursor cursor = db.query(Constants.mhstables[1],
                    new String[]{Constants.RESIDENCE_ID,
                            Constants.RESIDENCE_ADDRESS,
                            Constants.RESIDENCE_NOOFUNITS,
                            Constants.RESIDENCE_SIZEPERUNIT,
                            Constants.RESIDENCE_MONTHLYRENTAL},
                    //Constants.RESIDENCE_OWNER_ID},
                    Constants.RESIDENCE_ID + "=?",
                    new String[]{String.valueOf(residenceID)},
                    null, null, null);

            //if have data
            if (cursor != null) {
                //the first id that match with user input id
                cursor.moveToFirst();
            }

            //get each value from cursor and store to contact
            residence = new Residence();
            residence.setResidenceID(Integer.parseInt(cursor.getString(0)));
            residence.setAddress(cursor.getString(1));
            residence.setNumUnits(Integer.parseInt(cursor.getString(2)));
            residence.setSizePerUnit(Integer.parseInt(cursor.getString(3)));
            residence.setMonthlyRental(Integer.parseInt(cursor.getString(4)));
        } catch (Exception e) {
            Log.d("Get Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
        return residence;
    }

    //For applicant view
    public List<Residence> getAllResidences(){

        List<Residence> residenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try{

            String selectAll = "SELECT * FROM " + Constants.mhstables[1] ;
            //only one for rawQuery, query has more than 1
            Cursor cursor = db.rawQuery(selectAll,null);

            if(cursor.moveToFirst() == true){
                //do-while moveToNext returns false
                do{
                    Residence residence = new Residence();
                    residence.setResidenceID(Integer.parseInt(cursor.getString(0)));
                    residence.setAddress(cursor.getString(1));
                    residence.setNumUnits(Integer.parseInt(cursor.getString(2)));
                    residence.setSizePerUnit(Integer.parseInt(cursor.getString(3)));
                    residence.setMonthlyRental(Integer.parseInt(cursor.getString(4)));

                    residenceList.add(residence);
                }while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("GET All Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
        return residenceList;
    }

    //For housing officer view
    public List<Residence> GETAllResidences(String currentUser){

        List<Residence> residenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try{

            String selectAll = "SELECT * FROM " + Constants.mhstables[1] + " WHERE " + Constants.RESIDENCE_OWNER_ID +  " = '" + currentUser + "';";
            //only one for rawQuery, query has more than 1
            Cursor cursor = db.rawQuery(selectAll,null);

            if(cursor.moveToFirst() == true){
                //do-while moveToNext returns false
                do{
                    Residence residence = new Residence();
                    residence.setResidenceID(Integer.parseInt(cursor.getString(0)));
                    residence.setAddress(cursor.getString(1));
                    residence.setNumUnits(Integer.parseInt(cursor.getString(2)));
                    residence.setSizePerUnit(Integer.parseInt(cursor.getString(3)));
                    residence.setMonthlyRental(Integer.parseInt(cursor.getString(4)));

                    residenceList.add(residence);
                }while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("GET All Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
        return residenceList;
    }

    public void DeleteAllResidences(){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            String deleteAll = "DELETE FROM " + Constants.mhstables[1];

            db.execSQL(deleteAll);
        } catch (Exception e) {
            Log.d("Delete All Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
    }

    public void DeleteResidence(Residence residence) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{

            db.delete(Constants.mhstables[1], Constants.RESIDENCE_ID + "=?",
                    new String[]{String.valueOf(residence.getResidenceID())});
        } catch (Exception e) {
            Log.d("Delete Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();
    }

    public int UpdateResidence(Residence residence) {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.RESIDENCE_ADDRESS, residence.getAddress());
            contentValues.put(Constants.RESIDENCE_NOOFUNITS, residence.getNumUnits());
            contentValues.put(Constants.RESIDENCE_SIZEPERUNIT, residence.getSizePerUnit());
            contentValues.put(Constants.RESIDENCE_MONTHLYRENTAL, residence.getMonthlyRental());

            result = db.update(Constants.mhstables[1], contentValues,
                    Constants.RESIDENCE_ID + "=?",
                    new String[]{String.valueOf(residence.getResidenceID())});
        } catch (Exception e) {
            Log.d("Update Residence: ", e.getMessage());
            e.printStackTrace();
        }
        db.close();

        return result;
    }


    /**
     *
     *  Application table methods
     *
     */

    public void createApplication(Application application){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(Constants.APPLICATION_DATE,application.getApplicationDate());
            contentValues.put(Constants.APPLICATION_REQUIREDMONTH,application.getRequiredMonth());
            contentValues.put(Constants.APPLICATION_REQUIREDYEAR,application.getRequiredYear());
            contentValues.put(Constants.APPLICATION_STATUS,application.getStatus());
            contentValues.put(Constants.APPLICATION_APPLICANT,application.getApplicant());
            contentValues.put(Constants.APPLICATION_RESIDENCE,application.getResidenceID());

            db.insert(Constants.mhstables[2],null,contentValues);
        }catch (Exception e){
            Log.d("Application create:",e.getMessage());
        }
        db.close();
    }

    public void withdrawApp(Application application){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            String sql = "UPDATE " + Constants.mhstables[2] +" SET status = 'Withdraw' WHERE "+ Constants.APPLICATION_ID
                    + " = " + application.getApplicationID() +";";

            db.execSQL(sql);

        }catch (Exception e){
            Log.d("Withdraw application",e.getMessage());
        }
        db.close();
    }

    public List<Application> getAllApplicationApplicant(String currentUser){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Application> applicationList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM " + Constants.mhstables[2] + " WHERE "
                    + Constants.APPLICATION_APPLICANT + " = '" + currentUser +"';";
            Cursor cursor = db.rawQuery(sql,null);

            if(cursor.moveToFirst() == true){
                //do-while moveToNext returns false
                do{
                    Application application = new Application();
                    application.setApplicationID(Integer.parseInt(cursor.getString(0)));
                    application.setApplicationDate(cursor.getString(1));
                    application.setRequiredMonth(Integer.parseInt(cursor.getString(2)));
                    application.setRequiredYear(Integer.parseInt(cursor.getString(3)));
                    application.setStatus(cursor.getString(4));

                    applicationList.add(application);
                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Get all application:",e.getMessage());
        }
        db.close();
        return applicationList;
    }

    public List<Application> getAllApplicationHO(String currentUser){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Application> applicationList = new ArrayList<>();

        try{
            String sql = "SELECT * FROM " + Constants.mhstables[2] + " WHERE RESIDENCE IN ( SELECT "  + Constants.RESIDENCE_ID + " FROM " + Constants.mhstables[1] + " WHERE " + Constants.RESIDENCE_OWNER_ID + " = '" + currentUser +"');";
            Cursor cursor = db.rawQuery(sql,null);

            Log.d("SQL TEST",sql);

            if(cursor.moveToFirst() == true){
                //do-while moveToNext returns false
                do{
                    Application application = new Application();
                    application.setApplicationID(Integer.parseInt(cursor.getString(0)));
                    application.setApplicationDate(cursor.getString(1));
                    application.setRequiredMonth(Integer.parseInt(cursor.getString(2)));
                    application.setRequiredYear(Integer.parseInt(cursor.getString(3)));
                    application.setStatus(cursor.getString(4));

                    applicationList.add(application);
                }while(cursor.moveToNext());
            }


        }catch (Exception e){
            Log.d("Get all application:",e.getMessage());
        }
        db.close();
        return applicationList;
    }


    /**
     *
     * Allocation table methods
     *
     */
    public void makeAllocation(Allocation allocation){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //contentValues.put(Constants.ALLOCATION_UNITNO,allocation.getResidenceUnit().getUnitNo());
        //issue with LocalDate
        contentValues.put(Constants.ALLOCATION_FROMDATE,dateToStrDB.format(allocation.getFromDate()));
        contentValues.put(Constants.ALLOCATION_DURATION,allocation.getDuration());
        contentValues.put(Constants.ALLOCATION_ENDDATE,dateToStrDB.format(allocation.getEndDate()));


        db.insert(Constants.mhstables[3],null,contentValues);
        db.close();
    }

    /**
     *
     *
     *     Unit table methods
     *
     *
     *
     */
    public void addUnit(int numOfUnits, int residenceID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String newAvailability = "available";

        for(int i=0; i<numOfUnits; i++) {

            contentValues.put(Constants.UNIT_RESIDENCE_ID, residenceID);
            contentValues.put(Constants.UNIT_AVAILABITLITY, newAvailability);

            db.insert(Constants.mhstables[4], null, contentValues);
        }
        db.close();
    }

}
