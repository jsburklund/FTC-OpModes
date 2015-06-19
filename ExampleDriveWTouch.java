package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleDriveWTouch extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    //TouchSensor touchSensor;

    /*
    * Constructor
    */
    public ExampleDriveWTouch() {

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

        //get a reference to the touch sensor
        //touchSensor = hardwareMap.touchSensor.get("touch");
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {

        /*if(touchSensor.isPressed()) {
            //Stop the motors if the touch sensor is pressed
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else {
            //Keep driving if the touch sensor is not pressed
            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);
        }*/

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
