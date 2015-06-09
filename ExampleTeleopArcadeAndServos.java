package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleTeleopArcadeAndServos extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    Servo arm;
    Servo claw;

    final double ARM_UP_POSITION = 0.2;
    final double ARM_DOWN_POSITION = 0.9;
    final double CLAW_OPEN_POSITION = 0.2;
    final double CLAW_CLOSED_POSITION = 0.7;

    /*
    * Constructor
    */
    public ExampleTeleopArcadeAndServos() {

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

        //get references to the servos from the hardware map
        arm = hardwareMap.servo.get("servo_1");
        claw = hardwareMap.servo.get("servo_6");
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        //get the values from the gamepads
        //note: pushing the stick all the way up returns -1, so we need to reverse the y values
        float xValue = gamepad1.left_stick_x;
        float yValue = -gamepad1.left_stick_y;

        //calculate the power needed for each motor
        float leftPower = yValue + xValue;
        float rightPower = yValue - xValue;

        //clip the power values so that it only goes from -1 to 1
        leftPower = Range.clip(leftPower, -1, 1);
        rightPower = Range.clip(rightPower, -1, 1);

        //set the power of the motors with the gamepad values
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        //Control the arm servo with the gamepad
        if(gamepad1.y) {
            //y button is pressed -> move the arm up
            arm.setPosition(ARM_UP_POSITION);
        } else if(gamepad1.a) {
            //a button is pressed -> move the arm down
            arm.setPosition(ARM_DOWN_POSITION);
        }

        //control the claw servo with the gamepad
        if(gamepad1.x) {
            //x button is pressed -> open the claw
            claw.setPosition(CLAW_OPEN_POSITION);
        } else if(gamepad1.b) {
            //b button is pressed -> close the claw
            claw.setPosition(CLAW_CLOSED_POSITION);
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
