package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.helplive.bcm208assignment.data.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        //db.testSelect();
    }

    public void goRegisterApplicant(View view){
        Intent intent = new Intent(MainActivity.this,RegisterApplicant.class);
        startActivity(intent);
    }
}
