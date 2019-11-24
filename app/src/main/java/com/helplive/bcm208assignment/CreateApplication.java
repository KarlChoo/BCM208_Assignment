package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.helplive.bcm208assignment.data.DatabaseHandler;

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
    }

    public void create(View view){
        monthEditText = findViewById(R.id.editTextMonth);
        yearEditText = findViewById(R.id.editTextYear);

        //String month = monthEditText.getText();
    }

    public void cancel(View view){
        finish();
    }
}
