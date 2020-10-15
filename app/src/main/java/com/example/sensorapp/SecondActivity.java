package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;

    private TextView txtlight,txtproximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtlight = findViewById(R.id.txtLight);
        txtproximity = findViewById(R.id.txtProximity);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        String no_sensor = getResources().getString(R.string.error_no_sensor);

        if(mSensorLight ==  null){
            txtlight.setText(no_sensor);
        }

        if(mSensorProximity == null){
            txtproximity.setText(no_sensor);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mSensorLight !=  null){
            mSensorManager.registerListener(this,mSensorLight,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(mSensorProximity != null){
            mSensorManager.registerListener(this,mSensorProximity,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        float currentValue = event.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                txtlight.setText(getResources().getString(R.string.label_light,currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                txtproximity.setText(getResources().getString(R.string.label_proximity,currentValue));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}