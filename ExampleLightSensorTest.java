package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleLightSensorTest extends OpMode {
    LightSensor lightSensor;

    /*
    * Constructor
    */
    public ExampleLightSensorTest() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        //get the reference, and turn the red LED on the light sensor on
        lightSensor = hardwareMap.lightSensor.get("lightsensor_left");
        lightSensor.enableLed(true);
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //get the data from the sensor, and send it to the driverstation
        telemetry.addData("lightValue", "Light Value: "+lightSensor.getLightLevel());

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
