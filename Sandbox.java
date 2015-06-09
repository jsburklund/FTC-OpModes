package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class Sandbox extends OpMode {

    DcMotor rightMotor;
    ElapsedTime timeAtTarget = new ElapsedTime();
    int TARGET_POSITION = 1440*6;

    /*
    * Constructor
    */
    public Sandbox() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        rightMotor = hardwareMap.dcMotor.get("motor_2");

        rightMotor.setTargetPosition(TARGET_POSITION);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setPower(0.75);

    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //telemetry.addData("mode", "Mode: " + rightMotor.getChannelMode().toString());
        //telemetry.addData("power", "Power: " + rightMotor.getPower());
        int currPos = rightMotor.getCurrentPosition();
        telemetry.addData("pos", "Position: " + currPos);
        boolean isThere = hueristicIsAtTarget(TARGET_POSITION, currPos, 10, 1);
        telemetry.addData("done", "At Position?: " + isThere);

    }



    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }

    private boolean hueristicIsAtTarget(int target, int current, int threshold, double heuristicTime) {
        int error = Math.abs(target - current);
        if(error > threshold) {
            timeAtTarget.reset();
            return false;
        } else {
            if(timeAtTarget.time() > heuristicTime) {
                return true;
            } else {
                return false;
            }
        }
    }
}
