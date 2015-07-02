package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class ExampleAutoDriveWEncoders extends OpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    int ENCODER_PPR = 1440;
    int LEFT_TARGET_FORWARD = -2*ENCODER_PPR;
    int RIGHT_TARGET_FORWARD = 2*ENCODER_PPR;
    int THRESHOLD = 10;

    enum State {ResetEncoders, StartEncoders, WaitUntilInPosition, OptionalStopMotors, Done};
    State state;

    /*
    * Constructor
    */
    public ExampleAutoDriveWEncoders() {

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
        
        //set the first state to go to
        state = State.ResetEncoders;

    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {

        switch(state) {
            case ResetEncoders:
                //Reset both encoders
                leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

                //Transition to the next state
                state = State.StartEncoders;
                break;

            case StartEncoders:
                //Set the target position to run the motors to
                leftMotor.setTargetPosition(LEFT_TARGET_FORWARD);
                rightMotor.setTargetPosition(RIGHT_TARGET_FORWARD);

                //Set the motor channel to run to the target position set above
                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                //Set the motors to drive at the given speed to the position
                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);

                //transition to the next state to wait for the motors to get in position
                state = State.WaitUntilInPosition;
                break;

            case WaitUntilInPosition:
                //get the current positions of the motors
                int currLeftPos = leftMotor.getCurrentPosition();
                int currRightPos = rightMotor.getCurrentPosition();

                //Check if the motors are close enough to being in position
                boolean leftIsInPos = isAtTargetThreshold(LEFT_TARGET_FORWARD, currLeftPos, THRESHOLD);
                boolean rightIsInPos = isAtTargetThreshold(RIGHT_TARGET_FORWARD, currRightPos, THRESHOLD);

                //Display if each motor is in position
                telemetry.addData("linpos", "Is left in position?: " + leftIsInPos);
                telemetry.addData("rinpos", "Is right in position?: " + rightIsInPos);

                //If the motors are in position, transition to the next state
                if(leftIsInPos && rightIsInPos) {
                    //Change this to State.OptionalStopMotors if you want the robot to
                    //just stop moving the motors instead of holding position
                    state = State.Done;
                }
                break;

            case OptionalStopMotors:
                //This is an optional state to tell the motor to stop moving completely
                //If the power is not set to 0, the encoders will just hold their position
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                state = State.Done;
                break;

            case Done:
                //Idle and do nothing.
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

    private boolean isAtTargetThreshold(int target, int current, int threshold) {
        int error = target - current;
        if (Math.abs(error) < threshold) {
            return true;
        } else {
            return false;
        }
    }
}
