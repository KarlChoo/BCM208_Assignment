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
import com.helplive.bcm208assignment.model.Allocation;
import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.model.Unit;
import com.helplive.bcm208assignment.util.Constants;

import java.text.SimpleDateFormat;
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
    Spinner spinnerDuration;
    private int applicationID;
    private int unitNo;
    String dateformat;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_housing);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getString("CurrentUser");

        spinnerApplicationID = (Spinner) findViewById(R.id.spinnerApplicationID);

        spinnerUnitNo = (Spinner) findViewById(R.id.spinnerUnitNo);

        spinnerDuration = (Spinner) findViewById(R.id.spinnerDuration);

        loadSpinnerApplicationID();

        // Get reference of widgets from XML layout
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerDuration);

        // Initializing a String Array
        String[] duration = new String[]{
                "12",
                "18",
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
                calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(year, month, day);

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

        if(applicationIDList.isEmpty()){
            Toast.makeText(this, "No applications to allocate", Toast.LENGTH_SHORT).show();
            finish();
        }

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

        switch(parent.getId()) {
            case R.id.spinnerApplicationID:
                applicationID = Integer.parseInt(spinnerApplicationID.getSelectedItem().toString());
                loadSpinnerUnitNo(applicationID);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void allocateButton(View view) {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        if (rdbtnWaitlist.isChecked()) {
            applicationID = Integer.parseInt((String)spinnerApplicationID.getSelectedItem());
            db.setWaitlist(applicationID);
            Toast.makeText(this, "Application waitlisted", Toast.LENGTH_SHORT).show();
        }
        else if(rdbtnReject.isChecked()){
            applicationID = Integer.parseInt((String)spinnerApplicationID.getSelectedItem());
            db.setRejected(applicationID);
            Toast.makeText(this, "Application rejected", Toast.LENGTH_SHORT).show();
        }
        else{
            //Get application ID
            applicationID = Integer.parseInt((String)spinnerApplicationID.getSelectedItem());
            db.setApproved(applicationID); //Set to application to approve and the rest to rejected

            //Get unit no
            unitNo = Integer.parseInt((String)spinnerUnitNo.getSelectedItem());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate;

            //Requires validation in case user does not choose a date
            try {
                fromDate = sdf.format(calendar.getTime());
            }catch (Exception e){
                Toast.makeText(this, "Please select a from date with the calendar", Toast.LENGTH_SHORT).show();
                return;
            }

            //Get duration
            int duration = Integer.parseInt((String)spinnerDuration.getSelectedItem());

            //Get to date
            calendar.add(Calendar.MONTH,duration);
            String toDate = sdf.format(calendar.getTime());

            //get residenceID
            int residenceID = db.retrieveResidenceID(applicationID);

            Allocation a = new Allocation(fromDate,toDate,duration,applicationID,residenceID,unitNo);
            db.makeAllocation(a);
            Toast.makeText(this,"Allocation successfully created!",Toast.LENGTH_SHORT).show();
            Log.d("ALLOCATION",a.toString());
        }
        finish();
    }

    public void cancelButton(View view){
        finish();
    }

}
