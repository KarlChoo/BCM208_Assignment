package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ApplicantMenu extends AppCompatActivity {


    private String currentUser;
    private String fullname;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_menu);

        Bundle extras = getIntent().getExtras();

        //For testing this page
        if(extras == null){
            currentUser = "Not set";
            fullname = "Test Applicant";
        }
        currentUser = extras.getString("CurrentUser");
        fullname = extras.getString("UserFullname");

        welcomeText = findViewById(R.id.welcomeTextView);
        welcomeText.setText("Welcome, "+ fullname);

        //Toast.makeText(this,currentUser,Toast.LENGTH_SHORT).show();
    }

    public void goViewApplication(View view){
        Intent intent = new Intent(ApplicantMenu.this,ViewApplicationApplicant.class);
        intent.putExtra("CurrentUser",currentUser);
        startActivity(intent);
    }

    public void goViewResidence(View view){
        Intent intent = new Intent(ApplicantMenu.this,ViewResidenceApplicant.class);
        intent.putExtra("CurrentUser",currentUser);
        startActivity(intent);
    }

    public void logout(View view){
        finish();
    }
}
