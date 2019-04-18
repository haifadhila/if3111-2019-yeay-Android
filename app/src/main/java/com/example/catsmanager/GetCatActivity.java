package com.example.catsmanager;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetCatActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private Button intentButton;
    private Location loc;
    private TextView longitudeText, latitudeText;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("YAGITU","created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catfound);
        intentButton = findViewById(R.id.intentButton);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this
                        , new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            loc = location;
                            Log.d("YAGITU",location.toString());
//                            longitudeText.setText("longitude: " + location.getLatitude());
//                            latitudeText.setText("latitude: " + location.getLatitude());
                            intentButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    openMaps(loc);
                                }
                            });
                        }
                        else{
                            Log.d("YAGITU","GA JALAAANN");
                        }
                    }
                });


    }

    public void openMaps(Location location) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+location.getLatitude()+","+location.getLongitude()+"(You found the Cat here!)");
        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }



}
