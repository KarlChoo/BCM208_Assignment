package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Applicant;
import com.helplive.bcm208assignment.model.Application;

import java.util.ArrayList;
import java.util.List;

public class ViewApplicationApplicant extends AppCompatActivity {

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_applicant);

        databaseHandler = new DatabaseHandler( this);

        List<Application> applicationList = new ArrayList<>();
        for(Application a: applicationList){
            Log.d("Oncreate",a.toString());
        }

    }
}
