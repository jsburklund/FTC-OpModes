package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class FrankenVexTeleop extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    Servo claw;
    Servo arm;

    double VEX_MAX_FORWARD = 1.0;
    double VEX_STOP = 0.5;
    double VEX_MAX_BACKWARD = 0.0;

    /*
    * Constructor
    */
    public FrankenVexTeleop() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        //Get the motor objects from the config file
        leftMotor = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");

        //Reverse the right motor, because this robot is geared
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Get the servo objects from the config file
        claw = hardwareMap.servo.get("servo_1");
        arm = hardwareMap.servo.get("servo_6");

        //Reverse the arm servo
        arm.setDirection(Servo.Direction.REVERSE);

    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //get gamepad data, and calculate arcade drive
        float y = -gamepad1.left_stick_y;
        float x = gamepad1.left_stick_x;
        double leftSpeed = y + x;
        double rightSpeed = y - x;

        //clip the values from arcade drive so they don't exceed 1.0 or -1.0
        leftSpeed = Range.clip(leftSpeed, -1, 1);
        rightSpeed = Range.clip(rightSpeed, -1, 1);

        leftMotor.setPower(leftSpeed);
        rightMotor.setPower(rightSpeed);

        //move the claw based on buttons
        double clawSpeed;
        if(gamepad1.x) {
            clawSpeed = VEX_MAX_FORWARD;
        } else if(gamepad1.b) {
            clawSpeed = VEX_MAX_BACKWARD;
        } else {
            clawSpeed = VEX_STOP;
        }
        claw.setPosition(clawSpeed);

        //move the arm based on buttons
        double armSpeed;
        if(gamepad1.y) {
            armSpeed = VEX_MAX_FORWARD;
        } else if(gamepad1.a) {
            armSpeed = VEX_MAX_BACKWARD;
        } else {
            armSpeed = VEX_STOP;
        }
        arm.setPosition(armSpeed);

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
