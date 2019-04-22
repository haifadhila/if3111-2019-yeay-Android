package com.example.catsmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProximityActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    private long focusTime;
    private int time;
    private CountDownTimer countDownTimer;
    private TextView alertText;
    private TextView timer;
    private boolean finishTimer, justStart, timerRuning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        alertText = findViewById(R.id.alert);
        timer = findViewById(R.id.timer);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        String timeValue= getIntent().getStringExtra("timeValue");
        time = 0;
        if (timeValue.equals("1 minutes")) {
            time = 10000;
        } else if (timeValue.equals("15 minutes")) {
            time = 15;
        } else if (timeValue.equals("30 minutes")) {
            time = 30;
        } else if (timeValue.equals("45 minutes")) {
            time = 45;
        } else if (timeValue.equals("1 hour")) {
            time = 60;
        } else if (timeValue.equals("2 hours")) {
            time = 120;
        }
//        focusTime= time*60*1000;
        Log.d("proximity_focusTime", String.valueOf(focusTime));
        finishTimer=true;
        justStart=true;
        timerRuning=false;
        alertText.setText("Put your phone down!");
        @SuppressLint("DefaultLocale") String initTime = String.format("%02d:%02d:%02d", (focusTime/1000)/3600, ((focusTime/1000)%3600)/60, ((focusTime/1000)%3600)%60);
        timer.setText(initTime);
        if (proximitySensor == null) {
            alertText.setText("Proximity sensor not found.");
        } else {
            Log.d("proximity_status:", "Available");
            proximitySensorListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
                        justStart=false;
                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                        Log.d("proximity", "Proximity Nyala");
                        startTimer();
                    } else {
                        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                        Log.d("proximity", "Proximity Jauh");
                        if (!justStart) {
                            resetTimer();
                        }
                    }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            };
        }
    }

    private void startTimer() {
        timerRuning=true;
        countDownTimer = new CountDownTimer(focusTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                focusTime = millisUntilFinished;
                Log.d("proximity_countDownTime", String.valueOf(focusTime));
                updateTimerView();

            }
            @Override
            public void onFinish() {
                if (finishTimer) {
                    alertText.setText("Done!");
                    Log.d("YAGITU", "timer finish");
                    Intent myIntent = new Intent(ProximityActivity.this, GetCatActivity.class);
                    myIntent.putExtra("focustime", time);
                    ProximityActivity.this.startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(ProximityActivity.this, MainActivity.class);
                    ProximityActivity.this.startActivity(myIntent);
                }
                alertText.setVisibility(View.VISIBLE);
            }
        }.start();
        alertText.setVisibility(View.INVISIBLE);
    }

    private void resetTimer() {
        focusTime = 0;
        updateTimerView();
        countDownTimer.cancel();
        finishTimer=false;
        timerRuning=false;
        if (!finishTimer) {
            Toast.makeText(ProximityActivity.this, "You failed!",
                    Toast.LENGTH_LONG).show();
        }
        Intent myIntent = new Intent(ProximityActivity.this, MainActivity.class);
        ProximityActivity.this.startActivity(myIntent);
    }

    private void updateTimerView() {
        int hours = (int) (focusTime/1000)/3600;
        int minutes = (int) ((focusTime/1000)%3600)/60;
        int seconds = (int) ((focusTime/1000)%3600)%60;

        @SuppressLint("DefaultLocale") String timeLeft = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timer.setText(timeLeft);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(proximitySensorListener, proximitySensor,
                2 * 1000 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (timerRuning) {
            focusTime = 0;
            finishTimer=false;
            countDownTimer.cancel();
        }
    }
}