package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleStayInsideCircle extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    LightSensor lightSensor;
    ElapsedTime timer;

    //Light Sensor constants
    double lightValue = 0.9;
    double darkValue = 0.3;
    double threshold = (lightValue+darkValue) / 2;

    //Robot States
    enum State {Drive, Backup, Turn}
    State state;

    //Time Constants
    double BACKUP_TIME = 0.5;
    double TURN_TIME = 0.25;

    /*
    * Constructor
    */
    public ExampleStayInsideCircle() {

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

        //get the reference, and turn the red LED on the light sensor on
        lightSensor = hardwareMap.lightSensor.get("lightsensor_left");
        lightSensor.enableLed(true);

        //set the initial state
        state = State.Drive;

        //set up the timer
        timer = new ElapsedTime();
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //Get the amount of reflected light as a value from 0 to 1
        double reflectance = 1.0-lightSensor.getLightLevel();

        //Run the appropriate case based on the current state
        switch(state) {
            case Drive:
                //Check if the light sensor detects the line, stop, and switch states
                if(reflectance < threshold) {
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    state = State.Backup;
                    //reset the timer for backing up
                    timer.reset();
                } else {
                    //Otherwise, Set the motors to drive forward slowly
                    leftMotor.setPower(0.1);
                    rightMotor.setPower(0.1);
                }
                break;
            case Backup:
                //Set the motors to drive backwards
                leftMotor.setPower(-0.25);
                rightMotor.setPower(-0.25);

                //Check if the time to back up is complete, and switch states to turning
                if(timer.time() >= BACKUP_TIME) {
                    state = State.Turn;
                    timer.reset();
                }
                break;
            case Turn:
                //Set the motors to turn the robot right
                leftMotor.setPower(0.15);
                rightMotor.setPower(-0.15);

                //Check if the time to turn is complete, and switch states back to driving
                if(timer.time() >= TURN_TIME) {
                    state = State.Drive;
                }
                break;
        }

        telemetry.addData("state", "Current State: " + state.name());
        telemetry.addData("reflectance", "Reflectance Value: " + reflectance);
    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
