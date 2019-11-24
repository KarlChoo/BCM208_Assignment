package com.helplive.bcm208assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Residence;

import java.util.ArrayList;
import java.util.List;

public class ViewResidenceApplicant extends AppCompatActivity {

    private ListView residenceListView;
    private DatabaseHandler databaseHandler;
    private List<Residence> residenceList;
    AlertDialog.Builder alert;
    Residence residence;
    private String currentUser;

    public void createApplication(View view){
        if(residence!=null){
            Intent intent = new Intent(ViewResidenceApplicant.this,CreateApplication.class);
            intent.putExtra("CurrentUser",currentUser);
            intent.putExtra("ResidenceID",residence.getResidenceID());
        }else{
            Toast.makeText(this,"Please select a residence first!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getResidenceApplicant();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_residence_applicant);


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            currentUser = "Not set";
        }
        currentUser = extras.getString("CurrentUser");

        databaseHandler = new DatabaseHandler(this);
        residenceListView = findViewById(R.id.allApplicationListView);

        List<Residence> residenceList = new ArrayList<>();

        /*
        alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Residence");
        alert.setMessage("Are you sure you want to delete?");
        alert.setIcon(R.drawable.delete_icon);*/

        for (Residence residence : residenceList) {
            Log.d("Main", "onCreate: " + residence.getResidenceID());
        }

        residenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                residence = (Residence) residenceListView.getItemAtPosition(position);

                for (int i=0; i<residenceListView.getChildCount(); i++){
                    if(position == i){
                        residenceListView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.selectItem));
                    }
                    else{
                        residenceListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        getResidenceApplicant();
    }

    public void getResidenceApplicant(){
        residence = null;
        List<Residence> residenceList = databaseHandler.getAllResidences();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, residenceList);
        residenceListView.setAdapter(adapter);
    }

}
