package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.User;
import com.helplive.bcm208assignment.util.Constants;


public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    EditText editTextUsername;
    EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        //databaseHandler.initializeData(); //For hardcode insert data
        //databaseHandler.manipulateDB(sql); //For hardcode db manipulate
}

    public void login(View view){
        editTextUsername = findViewById(R.id.usernameEditText);
        editTextPassword = findViewById(R.id.passwordEditText);

        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        User currentUser = databaseHandler.authenticate(new User(username, password,""));

        if(currentUser != null){
            Intent intent;
            if(currentUser.getUserID().substring(0,2).equalsIgnoreCase("AP")){
                //Go to applicant
                //Toast.makeText(this,"Applicant",Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, ApplicantMenu.class);
                intent.putExtra("CurrentUser",currentUser.getUserID());
                intent.putExtra("UserFullname",currentUser.getFullname());
                startActivity(intent);
            }else  if(currentUser.getUserID().substring(0,2).equalsIgnoreCase("HO")){
                //Go to HO page
                //Toast.makeText(this,"Housing Officer",Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, HousingOfficerMenu.class);
                intent.putExtra("CurrentUser",currentUser.getUserID());
                intent.putExtra("UserFullname",currentUser.getFullname());
                startActivity(intent);
            }
            editTextPassword.setText("");
            editTextUsername.setText("");
        }else{
            Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this.getBaseContext(),allUsers.get(0).toString(),Toast.LENGTH_SHORT).show();
    }

    public void goRegisterApplicant(View view){
        Intent intent = new Intent(MainActivity.this,RegisterApplicant.class);
        startActivity(intent);
    }

    public void goRegisterHO(View view){
        Intent intent = new Intent(MainActivity.this,RegisterHousingOfficer.class);
        startActivity(intent);
    }
}
