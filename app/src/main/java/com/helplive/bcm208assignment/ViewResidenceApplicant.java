package com.helplive.bcm208assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Residence;

import java.util.ArrayList;
import java.util.List;

public class ViewResidenceApplicant extends AppCompatActivity {

    private ListView residenceListView;
    private DatabaseHandler databaseHandler;
    private List<Residence> residenceList;
    AlertDialog.Builder alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_residence_applicant);

        databaseHandler = new DatabaseHandler(this);


        List<Residence> residenceList = new ArrayList<>();

        //residenceList = databaseHandler.GETAllResidences();

        for (Residence residence : residenceList) {
            Log.d("Main", "onCreate: " + residence.getResidenceID());
        }

    }
}
