package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.helplive.bcm208assignment.model.HousingOfficer;

public class HousingOfficerMenu extends AppCompatActivity {

    private String currentUser;
    private String fullname;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_officer_menu);

        Bundle extras = getIntent().getExtras();

        //Test this page
        if(extras == null){
            currentUser = "Not set";
            fullname = "Test Housing Officer";
        }
        currentUser = extras.getString("CurrentUser");
        fullname = extras.getString("UserFullname");

        welcomeText = findViewById(R.id.welcomeTextView);
        welcomeText.setText("Welcome, "+ fullname);

    }


    public void goViewApplication(View view){
        Intent intent = new Intent(HousingOfficerMenu.this,ViewApplicationHO.class);
        intent.putExtra("CurrentUser",currentUser);
        startActivity(intent);
    }

    public void goViewResidence(View view){
        Intent intent = new Intent(HousingOfficerMenu.this,SetUpNewResidence.class);
        intent.putExtra("CurrentUser",currentUser);
        startActivity(intent);
    }

    public void goAllocateHousing(View view){
        Intent intent = new Intent(HousingOfficerMenu.this,AllocateHousing.class);
        intent.putExtra("CurrentUser",currentUser);
        startActivity(intent);
    }

    public void logout(View view){
        finish();
    }
}
