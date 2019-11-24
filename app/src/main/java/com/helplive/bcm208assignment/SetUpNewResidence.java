package com.helplive.bcm208assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.helplive.bcm208assignment.model.Residence;
import com.helplive.bcm208assignment.data.DatabaseHandler;

import java.util.List;

public class SetUpNewResidence extends AppCompatActivity {

    ListView allResidencesListView;
    DatabaseHandler databaseHandler;
    Residence residence;
    AlertDialog.Builder alert;
    private String currentUser;

    public void editButton(View view){
        //user at least select 1 residence
        if(residence != null){
            Intent intent = new Intent(this, EditResidence.class);
        int x = residence.getResidenceID();
            intent.putExtra("residenceID",x);
            intent.putExtra("CurrentUser",currentUser);
            startActivity(intent);
        }
    }

    public void deleteButton(View view){
        if(residence != null){
            databaseHandler.DeleteResidence(residence);
            getAllResidences();
        }
    }

    public void deleteAllButton(View view) {

        if (allResidencesListView.getAdapter().getCount() > 0) {
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    databaseHandler.DeleteAllResidences();
                    getAllResidences();
                }
            });

            alert.setNegativeButton("No",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.dismiss();
                }
            });
            alert.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllResidences();
    }

    public void addButton(View view){
        Intent intent = new Intent(this, AddResidence.class);
        intent.putExtra("CurrentUser",currentUser);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_new_residence);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            currentUser = "Not set";
        }

        currentUser = extras.getString("CurrentUser");

        databaseHandler = new DatabaseHandler(SetUpNewResidence.this);
        allResidencesListView= findViewById(R.id.allResidenceListView);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Residence");
        alert.setMessage("Are you sure you want to delete?");
        alert.setIcon(R.drawable.delete_icon);

        allResidencesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                residence = (Residence) allResidencesListView.getItemAtPosition(position);

                for (int i=0; i<allResidencesListView.getChildCount(); i++){
                    if(position == i){
                        allResidencesListView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.selectItem));
                    }
                    else{
                        allResidencesListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        getAllResidences();
    }
    public void getAllResidences(){
        residence = null;
        List<Residence> residenceList  = databaseHandler.GETAllResidences();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, residenceList);
        allResidencesListView.setAdapter(adapter);
    }
}

