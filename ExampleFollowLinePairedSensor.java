package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleFollowLinePairedSensor extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    LightSensor leftSensor;
    LightSensor rightSensor;

    //Proportional constant for scaling the sensor value to the motor value
    double kP = 0.07;

    /*
    * Constructor
    */
    public ExampleFollowLinePairedSensor() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        //get references to the motors from the hardware map
        leftMotor = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");

        //reverse the left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        //get a reference to the light sensors
        leftSensor = hardwareMap.lightSensor.get("lightsensor_left");
        rightSensor = hardwareMap.lightSensor.get("lightsensor_right");

        //turn the red LED on the light sensors on
        leftSensor.enableLed(true);
        rightSensor.enableLed(true);

    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //get the reflected light values from the sensors
        //(1-value) converts it so that white has a high value, and dark has a low value
        double leftReflectance = 1.0-leftSensor.getLightLevel();
        double rightReflectance = 1.0-rightSensor.getLightLevel();

        //multiply each reflectance value by kp, and apply it to the motor
        //when one sensor is over a dark area, it will slow that motor down, turning
        //the robot towards the center of the line
        leftMotor.setPower(leftReflectance * kP);
        rightMotor.setPower(rightReflectance * kP);

        //display the reflectance values on the driver station
        telemetry.addData("reflL", "Left Value: " + leftReflectance);
        telemetry.addData("reflR", "Right Value: " + rightReflectance);
    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
