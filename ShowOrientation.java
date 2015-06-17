package com.qualcomm.ftcrobotcontroller.opmodes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.List;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ShowOrientation extends OpMode implements SensorEventListener {
    Context context;
    SensorManager mSensorManager;
    List<Sensor> sensors;
    Sensor mRotVec;
    volatile String accuracyString;
    volatile float[] data;

    /*
    * Constructor
    */
    public ShowOrientation() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        context = hardwareMap.appContext;
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        //get a list of all of the sensors available
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        //get a reference to the default Rotation Vector
        mRotVec = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        //register this opmode as a Sensor Listener, and register to listen for the Rotation Vector
        mSensorManager.registerListener(this, mRotVec, SensorManager.SENSOR_DELAY_FASTEST);
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //Show the accuracy reported by the sesor
        telemetry.addData("accuracy", "Accuracy: "+accuracyString);
        //display any data that the sensor returned
        if(data != null) {
            for (int i = 0; i < data.length; i++) {
                telemetry.addData("data"+i, "Value " + i + ": " + data[i]);
            }
        } else {
            telemetry.addData("data", "No Data");
        }

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {
        //unregister the sensor event listener
        mSensorManager.unregisterListener(this);

    }

    /**
     * Code to run when the Sensor accuracy changes
     */
    @Override
    public final void onAccuracyChanged(Sensor mSensor, int accuracy) {
        //get the current accuracy type, and convert to a string
        switch(accuracy) {
            case SensorManager.SENSOR_STATUS_NO_CONTACT:
                accuracyString = "No Contact";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyString = "Unreliable";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyString = "Low";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyString = "Medium";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyString = "High";
                break;
        }

    }

    /**
     * Code to run when the sensor value changes
     */
    @Override
    public final void onSensorChanged(SensorEvent event) {
        //get all of the data available
        data = event.values;
    }
}
