package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Applicant;
import com.helplive.bcm208assignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    EditText editTextUsername;
    EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(MainActivity.this);
    }

    public void login(View view){
        List<User> allUsers = databaseHandler.getAllUsers();
        editTextUsername = findViewById(R.id.usernameEditText);
        editTextPassword = findViewById(R.id.passwordEditText);

        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Test user
        //allUsers.add(new Applicant("AP0001","karl","12345678","kasdlakdnad",32));

        if(!(allUsers.isEmpty())){
            //for each
            for (User u: allUsers) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                    Toast.makeText(this,"Login success",Toast.LENGTH_SHORT).show();
                }
            }
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
