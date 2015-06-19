package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleFollowLineSensorDifference extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    LightSensor leftSensor;
    LightSensor rightSensor;

    //Constants
    double TURNING_CONSTANT = 0.035;  //Proportional Constant for turning
    double FORWARD_SPEED = 0.045;    //Base speed to drive forward at

    /*
    * Constructor
    */
    public ExampleFollowLineSensorDifference() {

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
        //(1-value) converts it so that light colors have a high value, and dark colors have a low value
        double leftReflectance = 1.0-leftSensor.getLightLevel();
        double rightReflectance = 1.0-rightSensor.getLightLevel();

        //Calculate the difference between the two sensors
        double difference = leftReflectance - rightReflectance;

        //Control the motors just like arcade mode, but with different inputs for x and y
        double leftPower = FORWARD_SPEED + difference*TURNING_CONSTANT;
        double rightPower = FORWARD_SPEED - difference*TURNING_CONSTANT;

        //Set the motors at the calculated speeds
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        //display the values on the driver station
        telemetry.addData("reflL", "Left Value: " + leftReflectance);
        telemetry.addData("reflR", "Right Value: " + rightReflectance);
        telemetry.addData("diff", "Difference: " + difference);
    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
