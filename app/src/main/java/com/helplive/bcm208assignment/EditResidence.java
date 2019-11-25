package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.data.DatabaseHandler;

public class EditResidence extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    EditText addressEditText2;
    EditText numOfUnitsEditText2;
    EditText sizeOfUnitEditText2;
    EditText monthlyRentalEditText2;
    Residence residence;

    public void updateResidenceButton(View view){

        String address = addressEditText2.getText().toString().trim();
        String unitSizeStr = sizeOfUnitEditText2.getText().toString().trim();
        int sizePerUnit;
        String monthlyRentalStr = monthlyRentalEditText2.getText().toString().trim();
        double monthlyRental;

        //Validation
        //1. Cant be empty
        if(address.equalsIgnoreCase("")){
            Toast.makeText(this,"Please enter the residence address",Toast.LENGTH_SHORT).show();
            addressEditText2.requestFocus();
            return;
        }


        try{
            sizePerUnit = Integer.parseInt(unitSizeStr);
        }catch (Exception e){
            Toast.makeText(this,"Please enter the size of units",Toast.LENGTH_SHORT).show();
            sizeOfUnitEditText2.requestFocus();
            return;
        }

        try{
            monthlyRental = Double.parseDouble(monthlyRentalStr);
        }catch (Exception e){
            Toast.makeText(this,"Please enter the monthly rental of residence",Toast.LENGTH_SHORT).show();
            sizeOfUnitEditText2.requestFocus();
            return;
        }

        //2. Size per unit and rental cannot be 0
        if(sizePerUnit <=0){
            Toast.makeText(this,"Size of a unit cannot be 0",Toast.LENGTH_SHORT).show();
            sizeOfUnitEditText2.requestFocus();
            return;
        }

        if(monthlyRental <=0){
            Toast.makeText(this,"Monthly rental cannot be 0",Toast.LENGTH_SHORT).show();
            monthlyRentalEditText2.requestFocus();
            return;
        }


        residence.setAddress(address);
        residence.setSizePerUnit(sizePerUnit);
        residence.setMonthlyRental(monthlyRental);

        databaseHandler.UpdateResidence(residence);
        Toast.makeText(this,"Residence has been updated",Toast.LENGTH_SHORT).show();
        databaseHandler.close();
        finish();
    }

    public void cancelButton(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_residence);

        databaseHandler = new DatabaseHandler(EditResidence.this);
        addressEditText2 = findViewById(R.id.addressEditText2);
        numOfUnitsEditText2 = findViewById(R.id.numOfUnitsEditText2);
        sizeOfUnitEditText2 = findViewById(R.id.sizeOfUnitEditText2);
        monthlyRentalEditText2 = findViewById(R.id.monthlyRentalEditText2);

        Intent intent = getIntent();

        int residenceID = intent.getIntExtra("residenceID",-1);

        if(residenceID != -1){
            residence = databaseHandler.GetResidence(residenceID);
            addressEditText2.setText(residence.getAddress());
            numOfUnitsEditText2.setText(String.valueOf(residence.getNumUnits()));
            sizeOfUnitEditText2.setText(String.valueOf(residence.getSizePerUnit()));
            String monthlyRental = new Double(residence.getMonthlyRental()).toString();
            monthlyRentalEditText2.setText(monthlyRental);
        }
    }
}

