package com.example.catsmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetCatActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private DatabaseReference mDatabase;
    private Button intentButton;
    private Location loc;
    public FirebaseAuth mAuth;
    private TextView longitudeText, latitudeText;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("YAGITU","created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catfound);
        intentButton = findViewById(R.id.intentButton);
        final Intent intent = getIntent();
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
                            FirebaseUser user = mAuth.getInstance().getCurrentUser();
                            if (user!= null) {
                                Log.d("YAGITU", "user not null");
                                String email = user.getEmail();
                                Log.d("YAGITU", email);
                                DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
                                Date date = new Date();
                                Random rand = new Random();
                                int i = rand.nextInt(9);
                                LocationPosition longLat = new LocationPosition(loc.getLongitude(), loc.getLatitude());
                                Log.d("YAGITU", "longLat created");
                                int focustime = intent.getExtras().getInt("focustime");
                                addToDatabase(i, dateFormat.format(date),focustime, longLat, email);
                                Log.d("YAGITU", "added to database");
                            }
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

    public void addToDatabase(Number pic, String date, Number focustime, LocationPosition location, String email) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("database", "get instance from firebase database ok");
        String userId = mDatabase.push().getKey();
        Cat cat = new Cat(pic, date, focustime, location, email);
        Log.d("database", "new cat created");
        mDatabase.child("cat").child(userId).setValue(cat);
        Log.d("database", "set value by cat ok");
    }


}
