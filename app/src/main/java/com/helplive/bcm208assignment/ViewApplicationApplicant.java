package com.helplive.bcm208assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.helplive.bcm208assignment.data.DatabaseHandler;
import com.helplive.bcm208assignment.model.Applicant;
import com.helplive.bcm208assignment.model.Application;
import com.helplive.bcm208assignment.model.Residence;

import java.util.ArrayList;
import java.util.List;

public class ViewApplicationApplicant extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private ListView allApplicationListView;
    AlertDialog.Builder alert;
    Application application;
    private String currentUser;

    @Override
    public void onResume(){
        super.onResume();
        getAllApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_applicant);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            currentUser = "Not set";
        }

        currentUser = extras.getString("CurrentUser");

        databaseHandler = new DatabaseHandler( this);
        allApplicationListView = findViewById(R.id.allApplicationListView);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Withdraw Application");
        alert.setMessage("Are you sure you want to withdraw?");
        alert.setIcon(R.drawable.delete_icon);

        allApplicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                application = (Application) allApplicationListView.getItemAtPosition(position);

                for (int i=0; i<allApplicationListView.getChildCount(); i++){
                    if(position == i){
                        allApplicationListView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.selectItem));
                    }
                    else{
                        allApplicationListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        getAllApplication();
    }

    public void getAllApplication(){
        application = null;
        List<Application> applicationList = databaseHandler.getAllApplicationApplicant(currentUser);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, applicationList);
        allApplicationListView.setAdapter(adapter);
    }
}
