package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        String address = addressEditText1.getText().toString().trim();
        String noUnitsStr = numOfUnitsEditText1.getText().toString().trim();
        int numOfUnits;
        String unitSizeStr = sizeOfUnitEditText1.getText().toString().trim();
        int sizePerUnit;
        String monthlyRentalStr = monthlyRentalEditText1.getText().toString().trim();
        double monthlyRental;

        //Validation
        //1. Cant be empty
        if(address.equalsIgnoreCase("")){
            Toast.makeText(this,"Please enter the residence address",Toast.LENGTH_SHORT).show();
            addressEditText1.requestFocus();
            return;
        }

        try{
            numOfUnits = Integer.parseInt(noUnitsStr);
        }catch (Exception e){
            Toast.makeText(this,"Please enter the number of units",Toast.LENGTH_SHORT).show();
            numOfUnitsEditText1.requestFocus();
            return;
        }

        try{
            sizePerUnit = Integer.parseInt(unitSizeStr);
        }catch (Exception e){
            Toast.makeText(this,"Please enter the size of units",Toast.LENGTH_SHORT).show();
            sizeOfUnitEditText1.requestFocus();
            return;
        }

        try{
            monthlyRental = Double.parseDouble(monthlyRentalStr);
        }catch (Exception e){
            Toast.makeText(this,"Please enter the monthly rental of residence",Toast.LENGTH_SHORT).show();
            sizeOfUnitEditText1.requestFocus();
            return;
        }

        //2. Num of units, size per unit and rental cannot be 0
        if(numOfUnits <=0){
            Toast.makeText(this,"Number of unit cannot be 0",Toast.LENGTH_SHORT).show();
            numOfUnitsEditText1.requestFocus();
            return;
        }

        if(sizePerUnit <=0){
            Toast.makeText(this,"Size of a unit cannot be 0",Toast.LENGTH_SHORT).show();
            sizeOfUnitEditText1.requestFocus();
            return;
        }

        if(monthlyRental <=0){
            Toast.makeText(this,"Monthly rental cannot be 0",Toast.LENGTH_SHORT).show();
            monthlyRentalEditText1.requestFocus();
            return;
        }


        Residence residence = new Residence();
        residence.setAddress(address);
        residence.setNumUnits(numOfUnits);
        residence.setSizePerUnit(sizePerUnit);
        residence.setMonthlyRental(monthlyRental);
        residence.setStaffID(currentUser);

        databaseHandler.ADDResidence(residence);
        Toast.makeText(this, "Residence created", Toast.LENGTH_SHORT).show();
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