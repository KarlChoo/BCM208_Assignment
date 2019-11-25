package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.model.Unit;
import com.helplive.bcm208assignment.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AllocateHousing extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RadioButton rdbtnApprove;
    private RadioButton rdbtnReject;
    private RadioButton rdbtnWaitlist;
    private TextView fromDate;
    private String currentUser;
    DatePickerDialog datePickerDialog;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    Spinner spinnerApplicationID;
    Spinner spinnerUnitNo;
    private int applicationID;
    protected Adapter initializedAdapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_housing);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getString("CurrentUser");

        spinnerApplicationID = (Spinner) findViewById(R.id.spinnerApplicationID);

        spinnerUnitNo = (Spinner) findViewById(R.id.spinnerUnitNo);

        loadSpinnerApplicationID();

        // Get reference of widgets from XML layout
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerDuration);

        // Initializing a String Array
        String[] duration = new String[]{
                "Select a duration...",
                "12 months",
                "18 months",
        };

        final List<String> durationList = new ArrayList<>(Arrays.asList(duration));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,durationList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Duration : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fromDate=findViewById(R.id.txtFromDate);
        fromDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AllocateHousing.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        fromDate.setText(day+" - "+(month+1)+" - "+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        rdbtnApprove = findViewById(R.id.rdbtnApprove);
        rdbtnReject = findViewById(R.id.rdbtnReject);
        rdbtnWaitlist = findViewById(R.id.rdbtnWaitlist);

        rdbtnApprove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    spinnerUnitNo.setEnabled(true);
                    fromDate.setEnabled(true);
                    fromDate.setText("Select a date");
                    spinner.setEnabled(true);
                }
            }
        });

        rdbtnReject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    spinnerUnitNo.setEnabled(false);
                    fromDate.setEnabled(false);
                    fromDate.setText("Date not applicable");
                    spinner.setEnabled(false);
                }
            }
        });

        rdbtnWaitlist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    spinnerUnitNo.setEnabled(false);
                    fromDate.setEnabled(false);
                    fromDate.setText("Date not applicable");
                    spinner.setEnabled(false);
                }
            }
        });
        spinnerApplicationID.setOnItemSelectedListener(this);
        spinnerUnitNo.setOnItemSelectedListener(this);
    }

    public void loadSpinnerApplicationID() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());


        List<String> applicationIDList = db.getAllApplicationID(currentUser);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, applicationIDList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerApplicationID.setAdapter(dataAdapter);

    }

    public void loadSpinnerUnitNo(int applicationID){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        List<String> unitNoList = db.getAllUnitNo(applicationID);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,unitNoList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUnitNo.setAdapter(dataAdapter);
    }


        @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (view.getId()) {

            case R.id.spinnerApplicationID:
                int applicationID = (int) parent.getItemAtPosition(position);
                loadSpinnerUnitNo(applicationID);
                break;
            default:
                break;
        }
        /*
            else if(province.matches("Free State")){
                spinPro = 2;
                populateDist();
            }
        switch(parent.getId()) {
            case R.id.spinnerApplicationID:
                applicationID = Integer.parseInt(spinnerApplicationID.getSelectedItem().toString());
                loadSpinnerUnitNo(applicationID);
                Log.d("ITEMSELECTED", String.valueOf(applicationID));
                break;
            default:
                break;
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void allocateButton(View view) {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        if (rdbtnWaitlist.isChecked()) {
            applicationID = Integer.parseInt((String)spinnerApplicationID.getSelectedItem());
            db.setWaitlist(applicationID);
        }
        else if(rdbtnReject.isChecked()){
            applicationID = Integer.parseInt((String)spinnerApplicationID.getSelectedItem());
            db.setRejected(applicationID);
        }
        else{
            applicationID = Integer.parseInt((String)spinnerApplicationID.getSelectedItem());
            db.setApproved(applicationID);
        }
        finish();
    }

    public void cancelButton(View view){
        finish();
    }

}
