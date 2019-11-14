package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        residence.setAddress(addressEditText2.getText().toString().trim());
        residence.setNumUnits(Integer.parseInt(numOfUnitsEditText2.getText().toString().trim()));
        residence.setSizePerUnit(Integer.parseInt(sizeOfUnitEditText2.getText().toString().trim()));
        residence.setMonthlyRental(Integer.parseInt(monthlyRentalEditText2.getText().toString().trim()));

        databaseHandler.UpdateResidence(residence);
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

        String id = intent.getStringExtra("null");

        if(!(id.equalsIgnoreCase("null"))){
            residence = databaseHandler.GetResidence(id);
            addressEditText2.setText(residence.getAddress());
            numOfUnitsEditText2.setText(residence.getNumUnits());
            sizeOfUnitEditText2.setText(residence.getSizePerUnit());
            String monthlyRental = new Double(residence.getMonthlyRental()).toString();
            monthlyRentalEditText2.setText(monthlyRental);
        }
    }
}

