package com.example.catsmanager;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.support.v4.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = null;
        View view = inflater.inflate(R.layout.tab_fragment1, container, false);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        final TextView focusStartAlert = (TextView) view.findViewById(R.id.focusStartAlert);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        
        //handle spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String text = parent.getItemAtPosition(i).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // handle start button to start countdown
        Button startButton = (Button) view.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusStartAlert.setText("Put your phone down!");
                String timeValue =  spinner.getSelectedItem().toString();
                Integer focus_time = 0;
                Log.d("nama", timeValue);
                if (timeValue.equals("15 minutes")) {
                    focus_time = 15;
                } else if (timeValue.equals("30 minutes")) {
                    focus_time = 30;
                } else if (timeValue.equals("45 minutes")) {
                    focus_time = 45;
                } else if (timeValue.equals("1 hour")) {
                    focus_time = 60;
                } else if (timeValue.equals("2 hours")) {
                    focus_time = 120;
                }

                //check proximity
                Intent myIntent = new Intent(getActivity(), ProximityActivity.class);
                TabFragment1.this.startActivity(myIntent);
//                Sensor myProximitySensor = mySensorManager.getDefaultSensor(
//                        Sensor.TYPE_PROXIMITY);
//                if (myProximitySensor == null){
//                    focusStartAlert.setText("No Proximity Sensor!");
//                }else{
//                    focusStartAlert.setText("Proximity Sensor Available");
//                }
                //start countdown
            }
        });
        return view;
    }
}
