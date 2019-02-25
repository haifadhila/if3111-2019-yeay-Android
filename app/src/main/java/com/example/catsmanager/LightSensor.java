package com.example.catsmanager;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class LightSensor extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightSensorListener;
    int minLux = 0;
    double currentLux = 0;
    double maxLux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            Toast.makeText(this, "Light sensor is not available !", Toast.LENGTH_LONG).show();
            finish();
        }
        lightSensorListener = new SensorEventListener() {
            TextView lightMessage = (TextView) findViewById(R.id.textView2);
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
                    double lightValue = sensorEvent.values[0];
                    Log.d("SENSOR", "onSensorChanged: "+sensorEvent.values[0]);
                    if (lightValue < 100 && lightValue > 40){
                        Log.d("IDENTIFY: ", "Perfect to learn!");
                        lightMessage.setText("Perfect lighting for your focused eyes!");
                    }
                    else if (lightValue > 100){
                        Log.d("IDENTIFY: ", "It's brighter than usual. Can you your laptop screen clearly?");
                        lightMessage.setText("It's brighter than usual. Can you your laptop screen clearly?");
                    }
                    else{
                        Log.d("IDENTIFY: ", "Isn't it too dim? Don't hurt your eyes!");
                        lightMessage.setText("Isn't it too dim? Don't hurt your eyes!");
                    }
//                    Log.d("SENSOR: ", String.valueOf(sensorEvent.values[0]));
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightSensorListener, lightSensor,
                2 * 1000 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightSensorListener);
    }
}