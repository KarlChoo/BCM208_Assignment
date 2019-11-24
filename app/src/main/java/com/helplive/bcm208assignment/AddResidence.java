package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.data.DatabaseHandler;

public class AddResidence extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    EditText addressEditText1;
    EditText numOfUnitsEditText1;
    EditText sizeOfUnitEditText1;
    EditText monthlyRentalEditText1;

    private String currentUser;

    public void addResidence(View view){
        Bundle extras = getIntent().getExtras();
        currentUser = extras.getString("CurrentUser");

        Residence residence = new Residence();
        residence.setAddress(addressEditText1.getText().toString().trim());
        residence.setNumUnits(Integer.parseInt(numOfUnitsEditText1.getText().toString().trim()));
        residence.setSizePerUnit(Integer.parseInt(sizeOfUnitEditText1.getText().toString().trim()));
        residence.setMonthlyRental(Float.parseFloat(monthlyRentalEditText1.getText().toString().trim()));
        residence.setStaffID(currentUser);

        databaseHandler.ADDResidence(residence);
        databaseHandler.close();
        finish();
    }

    public void cancelButton(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_residence);

        databaseHandler = new DatabaseHandler(AddResidence.this);
        Bundle extra = getIntent().getExtras();
        currentUser = extra.getString("CurrrentUser");

        addressEditText1 = findViewById(R.id.addressEditText1);
        numOfUnitsEditText1 = findViewById(R.id.numOfUnitsEditText1);
        sizeOfUnitEditText1 = findViewById(R.id.sizeOfUnitEditText1);
        monthlyRentalEditText1 = findViewById(R.id.monthlyRentalEditText1);
    }
}