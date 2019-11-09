package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterApplicant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_applicant);
    }

    public void registerApplicant(View view){
        EditText editTextName = findViewById(R.id.nameEditText);
        EditText editTextFullname = findViewById(R.id.editTextFullname);
        EditText editTextPass = findViewById(R.id.passwordEditText);
        EditText editTextPass2 = findViewById(R.id.password2EditText);
        EditText editTextEmail = findViewById(R.id.emailEditText);
        EditText editTextMonthSal = findViewById(R.id.monthSalEditText);

        String name = editTextName.getText().toString().trim();
        String fullname = editTextFullname.getText().toString().trim();
        String password =  editTextPass.getText().toString().trim();
        String confpassword = editTextPass2.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String monthlySalaryStr = editTextMonthSal.getText().toString().trim();
        Integer monthlySalary = 0;

        //Toast.makeText(this.getBaseContext(),"TEST",Toast.LENGTH_SHORT).show();

        //validations
        //1. Can't be empty
        if(name.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the name field",Toast.LENGTH_SHORT).show();
            editTextName.requestFocus();
            return;
        }

        if(password.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the password field",Toast.LENGTH_SHORT).show();
            editTextPass.requestFocus();
            return;
        }

        if(confpassword.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the confirm password field",Toast.LENGTH_SHORT).show();
            editTextPass2.requestFocus();
            return;
        }

        if(email.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(),"Please fill in the email field",Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
            return;
        }

        try{
           Integer.parseInt(monthlySalaryStr);
        }catch (Exception e){
            Toast.makeText(this.getBaseContext(),"Please fill in the monthly salary field",Toast.LENGTH_SHORT).show();
            editTextMonthSal.requestFocus();
            return;
        }

        //Pass must be >8 chars
        if(password.length() < 8){
            Toast.makeText(this.getBaseContext(),"Password must be longer than 8 characters",Toast.LENGTH_SHORT).show();
            editTextPass.requestFocus();
            return;
        }
        //2. Pass and conform pass must be same
        if(!(password.equals(confpassword))){
            Toast.makeText(this.getBaseContext(),"Please fill in the monthly salary field",Toast.LENGTH_SHORT).show();
            editTextPass.requestFocus();
            return;
        }
        //3. Email must have @
        if(!(email.contains("@")) || email.substring(0).equals("@") || email.substring(email.length()-1).equals("@")){
            Toast.makeText(this.getBaseContext(),"Invalid email format",Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
            return;
        }
        //4. Monthly Salary can't be 0
        if(monthlySalary <= 0){
            Toast.makeText(this.getBaseContext(),"Monthly salary cannot be RM 0",Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
            return;
        }

        //registration successful
        Applicant a = new Applicant(name,password,fullname,email,monthlySalary);

        Toast.makeText(this.getBaseContext(),"Registration successful",Toast.LENGTH_SHORT).show();

    }
}
