/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class ExampleServos extends OpMode {
    Servo servo1;
    Servo servo6;

    //Position constants for the buttons
    double DOWN_POSITION = 0.6;
    double UP_POSITION = 0.4;

    //Scale constant to scale the joystick value by
    double SCALE = 0.01;
    //Variable for the servo's current position
    double currentPosition;

  public ExampleServos() {

  }

  @Override
  public void start() {
      servo1 = hardwareMap.servo.get("servo_1");
      servo6 = hardwareMap.servo.get("servo_6");
      //Set the current position to a known value
      currentPosition = 0.5;
  }


  @Override
  public void loop() {

      //**** Move a Servo with Buttons ****
      //Move servo 1 to the up position when a button is pressed
      if(gamepad1.a) {
          servo1.setPosition(UP_POSITION);
      }
      //Move servo 1 to the down position when a button is pressed
      if(gamepad1.b) {
          servo1.setPosition(DOWN_POSITION);
      }

      //**** Move a Servo with a Joystick ****
      //Get the joystick value and scale it to set how much the servo should change
      double delta = (-gamepad1.left_stick_y)*SCALE;
      //Add the amount to change by to the current position, and
      //limit the values to a range the servo can accept
      currentPosition = Range.clip((currentPosition + delta), 0, 1.0);
      //apply the value to the servo
      servo6.setPosition(currentPosition);
  }

  /*
   * Code to run when the op mode is first disabled goes here
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
   */
  @Override
  public void stop() {

  }
}
