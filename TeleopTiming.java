package com.qualcomm.ftcrobotcontroller.opmodes;

import android.text.format.Time;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class TeleopTiming extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    LightSensor light;

    long currTime;
    long prevTime;

    /*
    * Constructor
    */
    public TeleopTiming() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        leftMotor = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");

        light = hardwareMap.lightSensor.get("light");
        light.enableLed(true);

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        prevTime = System.currentTimeMillis();
        currTime = System.currentTimeMillis();
    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        currTime = System.currentTimeMillis();

        leftMotor.setPower(-gamepad1.left_stick_y);
        rightMotor.setPower(-gamepad1.right_stick_y);

        double reflectedValue = light.getLightLevel();

        double timeDiff = (currTime - prevTime)/1000.0;
        double frequency = (1.0f/timeDiff);


        telemetry.addData("timediff", "Time Difference: " + timeDiff);
        telemetry.addData("frequency", "Frequency: " + frequency );
        telemetry.addData("rval", "Reflected Value: " + reflectedValue);

        prevTime = currTime;
    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }

    float clipToRange(float value, float min, float max) {
        if (value > max) {
            value = max;
        } else if (value < min) {
            value = min;
        }
        return value;
    }
}
