package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.HousingOfficer;

public class RegisterHousingOfficer extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_housing_officer);

        databaseHandler = new DatabaseHandler(RegisterHousingOfficer.this);
    }

    public void registerHousingOfficer(View view){
        EditText editTextName = findViewById(R.id.usernameEditText);
        EditText editTextFullname = findViewById(R.id.editTextFullname);
        EditText editTextPass = findViewById(R.id.passwordEditText);
        EditText editTextPass2 = findViewById(R.id.password2EditText);

        String name = editTextName.getText().toString().trim();
        String fullname = editTextFullname.getText().toString().trim();
        String password =  editTextPass.getText().toString().trim();
        String confpassword = editTextPass2.getText().toString().trim();


        //validations
        //1. Can't be empty
        if(name.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the username field.",Toast.LENGTH_SHORT).show();
            editTextName.requestFocus();
            return;
        }

        if(fullname.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the full name field.",Toast.LENGTH_SHORT).show();
            editTextFullname.requestFocus();
            return;
        }

        if(password.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the password field.",Toast.LENGTH_SHORT).show();
            editTextPass.requestFocus();
            return;
        }

        if(confpassword.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the confirm password field.",Toast.LENGTH_SHORT).show();
            editTextPass2.requestFocus();
            return;
        }

        //Pass must be >= 8 chars
        if(password.length() < 8){
            Toast.makeText(this.getBaseContext(),"Password must be longer than 8 characters.",Toast.LENGTH_SHORT).show();
            editTextPass.requestFocus();
            return;
        }
        //2. Pass and confirm pass must be same
        if(!(password.equals(confpassword))){
            Toast.makeText(this.getBaseContext(),"Password and confirm password fields are different.",Toast.LENGTH_SHORT).show();
            editTextPass.requestFocus();
            return;
        }

        //Validate for repeat username
        if(databaseHandler.validateUsername(name)){
            //registration successful
            HousingOfficer ho = new HousingOfficer(name,password,fullname);
            databaseHandler.addHousingOfficer(ho);
            Toast.makeText(this.getBaseContext(),"Registration successful.",Toast.LENGTH_SHORT).show();
            databaseHandler.close();
            finish();
        }else{
            editTextName.setText("");
            editTextName.requestFocus();
            Toast.makeText(this.getBaseContext(),"Username already exists.",Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelRegister(View view){
        finish();
    }
}
