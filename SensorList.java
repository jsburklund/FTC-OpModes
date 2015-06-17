package com.qualcomm.ftcrobotcontroller.opmodes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.List;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class SensorList extends OpMode {
    Context context;
    SensorManager mSensorManager;
    List<Sensor> sensors;
    Sensor mRotVec;

    /*
    * Constructor
    */
    public SensorList() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        context = hardwareMap.appContext;
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        for(int i=0; i < sensors.size(); i++) {
            telemetry.addData("sensor"+i, sensors.get(i).getName()+", "+sensors.get(i).getVendor());
        }

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
