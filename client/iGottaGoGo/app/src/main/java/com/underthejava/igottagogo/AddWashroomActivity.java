package com.underthejava.igottagogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

public class AddWashroomActivity extends AppCompatActivity implements
        View.OnClickListener
{

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvFloor;
    private TextView tvStalls;
    private CheckBox cCustomers;
    private CheckBox cDisability;
    private CheckBox cFamily;
    private CheckBox cMale;
    private CheckBox cFemale;
    private CheckBox cHygiene;
    private CheckBox cShowers;
    private RatingBar rbRating;
    private Button bSave;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_washroom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeVariables();

        bSave.setOnClickListener(this);
    }

    private void initializeVariables() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvFloor = (TextView) findViewById(R.id.tvFloor);
        tvStalls = (TextView) findViewById(R.id.tvStalls);

        cCustomers = (CheckBox) findViewById(R.id.cCustomers);
        cDisability = (CheckBox) findViewById(R.id.cDisability);
        cFamily = (CheckBox) findViewById(R.id.cFamily);
        cMale = (CheckBox) findViewById(R.id.cMale);
        cFemale = (CheckBox) findViewById(R.id.cFemale);
        cHygiene = (CheckBox) findViewById(R.id.cHygiene);
        cShowers = (CheckBox) findViewById(R.id.cShowers);

        rbRating = (RatingBar) findViewById(R.id.ratingBar);

        bSave = (Button) findViewById(R.id.bSaveWash);

        latitude = getIntent().getDoubleExtra("LAT", 0.0);
        longitude = getIntent().getDoubleExtra("LONG", 0.0);
    }

    @Override
    public void onClick(View v) {
        // On click save

        String title = tvTitle.getText().toString();
        String description = tvDescription.getText().toString();

        int floor = 0;
        String sFloor = tvFloor.getText().toString();
        if(!sFloor.isEmpty()) {
            floor = Integer.parseInt(sFloor);
        }
        int stalls = 0;
        String sStalls = tvStalls.getText().toString();
        if(!sStalls.isEmpty()) {
            stalls = Integer.parseInt(sStalls);
        }
        //int hours;

        boolean customersOnly = cCustomers.isChecked();
        boolean disability = cDisability.isChecked();
        boolean family = cFamily.isChecked();
        boolean male = cMale.isChecked();
        boolean female = cFemale.isChecked();
        boolean hygiene = cHygiene.isChecked();
        boolean showers = cShowers.isChecked();

        float rating = rbRating.getRating();

        Washroom washroom = new Washroom(title, description, floor, stalls, customersOnly, disability, family,
                male, female, hygiene, showers, rating, latitude, longitude);
        WashroomManager.getInstance().addWashroom(washroom);

        onReturnToMaps();
    }

    private void onReturnToMaps() {
        finish();
    }
}
