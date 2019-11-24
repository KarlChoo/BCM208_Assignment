package com.helplive.bcm208assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AllocateHousing extends AppCompatActivity{
    private RadioButton rdbtnApprove;
    private RadioButton rdbtnReject;
    private RadioButton rdbtnWaitlist;
    private TextView fromDate;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_housing);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<Residence> listResidenceID = databaseHandler.getAllResidences();
        Spinner spinnerResidenceID = (Spinner) findViewById(R.id.spinnerResidenceID);
        ArrayAdapter<String> adapterResidenceID = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        spinnerResidenceID.setAdapter(adapterResidenceID);

        List<Unit> unitNo = databaseHandler.getAllUnits();
        final Spinner spinnerUnitNo = (Spinner) findViewById(R.id.spinnerUnitNo);
        ArrayAdapter<String> adapterUnitNo = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        spinnerUnitNo.setAdapter(adapterUnitNo);


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
                    fromDate.setText("Select a date");
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
                    fromDate.setText("Select a date");
                    spinner.setEnabled(false);
                }
            }
        });
    }
}
