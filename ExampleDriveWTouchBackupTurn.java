package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleDriveWTouchBackupTurn extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    TouchSensor touchSensor;
    ElapsedTime timer;

    //Robot states
    enum State{Drive, Backup, Turn}
    State state;

    //Time constants
    double BACKUP_TIME = 1.0;
    double TURN_TIME = 0.25;


    /*
    * Constructor
    */
    public ExampleDriveWTouchBackupTurn() {

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
        touchSensor = hardwareMap.touchSensor.get("touch");

        //set the state to driving
        state = State.Drive;
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        switch(state) {
            case Drive:
                //Set the motors to drive forward at 50% power
                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);

                //Switch states if the touch sensor is pressed
                if(touchSensor.isPressed()) {
                    state = State.Backup;
                    //reset the timer for backing up
                    timer.reset();
                }
                break;
            case Backup:
                //Set the motors to drive backwards at 25% power
                leftMotor.setPower(0.25);
                rightMotor.setPower(0.25);

                //switch states when it has backed up for a set amount of time
                if(timer.time() >= BACKUP_TIME) {
                    state = State.Turn;
                    //reset the timer for turning
                    timer.reset();
                }
                break;
            case Turn:
                //Set the motors to turn the robot right at 25% power
                leftMotor.setPower(0.25);
                rightMotor.setPower(0.25);

                //switch states when it has turned for a set amount of time
                if(timer.time() >= TURN_TIME) {
                    state = State.Drive;
                }
                break;
        }

        telemetry.addData("state", "Current State: " + state.name());
    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
