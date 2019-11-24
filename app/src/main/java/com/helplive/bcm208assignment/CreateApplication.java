package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateApplication extends AppCompatActivity {

    private DatabaseHandler databaseHandler;

    private String currentUser;
    private int residenceID;
    private EditText yearEditText;
    private EditText monthEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_application);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getString("CurrentUser");
        residenceID = extras.getInt("ResidenceID");

        databaseHandler = new DatabaseHandler(this);
    }

    public void create(View view){
        monthEditText = findViewById(R.id.editTextMonth);
        yearEditText = findViewById(R.id.editTextYear);

        String monthStr = monthEditText.getText().toString().trim();
        String yearStr = yearEditText.getText().toString().trim();

        if(monthStr.equalsIgnoreCase("") || yearStr.equalsIgnoreCase("")){
            Toast.makeText(this,"Month and year cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr);

        //Month validate
        if (month == 0 || month > 12){
            Toast.makeText(this,"Please enter values between 1 - 12 for month",Toast.LENGTH_SHORT).show();
            return;
        }

        //year validate
        if (year < 2018 || year > 2100){
            Toast.makeText(this,"Please enter values between 2019 - 2100 for year",Toast.LENGTH_SHORT).show();
            return;
        }

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date); //Output = 2019-11-24


        Application a = new Application();
        a.setRequiredMonth(month);
        a.setRequiredYear(year);
        a.setStatus("New");
        a.setApplicationDate(dateStr);
        a.setApplicant(currentUser);
        a.setResidenceID(residenceID);

        databaseHandler.createApplication(a);
        Toast.makeText(this,"Application created",Toast.LENGTH_SHORT).show();

        databaseHandler.close();
        finish();

    }

    public void cancel(View view){
        finish();
    }
}
