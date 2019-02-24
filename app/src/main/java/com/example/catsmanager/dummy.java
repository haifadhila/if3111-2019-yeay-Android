//package com.example.catsmanager;
//
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.location.Location;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class TabFragment {
//
//    private FusedLocationProviderClient fusedLocationClient;
//    private Button intentButton;
//    private Location loc;
//
//    public TabFragment1() {
//        // Required empty public constructor
//    }
//    @SuppressLint("MissingPermission")
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.tab_fragment1, container, false);
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            loc = location;
//                            Log.d("YAGITU",location.toString());
//                        }
//                    }
//                });
//
//        intentButton = (Button) view.findViewById(R.id.button);
//        intentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMaps(view,loc);
//            }
//        });
//
//        // Inflate the layout for this fragment
//        return view;
//    }
//
//
//
//    public void openMaps(View v, Location location) {
//        // Create a Uri from an intent string. Use the result to create an Intent.
//        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+location.getLatitude()+","+location.getLongitude()+"(You found the Cat here!)");
//
//        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        // Make the Intent explicit by setting the Google Maps package
//        mapIntent.setPackage("com.google.android.apps.maps");
//
//        // Attempt to start an activity that can handle the Intent
//        startActivity(mapIntent);
//    }
//
//
//
//}
