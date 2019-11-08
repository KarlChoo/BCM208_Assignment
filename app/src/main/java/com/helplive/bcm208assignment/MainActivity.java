package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goRegisterApplicant(View view){
        Intent intent = new Intent(MainActivity.this,RegisterApplicant.class);
        //Toast.makeText(this.getBaseContext(),"TEST",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
