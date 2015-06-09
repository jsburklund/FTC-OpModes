package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

/**
 * Created by Jordan Burklund on 6/9/2015.
 */
public class PhilsRSLOp extends OpMode {

    private Camera camera;
    private Parameters parm;
    private int blinker;

    /**
     * Constructor
     */
    public PhilsRSLOp() {
    }

    /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void start() {

        // Required to control the Camera LED
        camera = Camera.open();
        parm = camera.getParameters();
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {

        // Blink the LED.
        blink();
    }

    /*
     * Code to run when the op mode is first disabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {
        if (camera != null) {
            camera.release();
        }
    }

    /*
     * Code to blink the LED at a regular interval
     * Turn on the LED for 10 cylces and turn off at 100 cycles
     */
    public void blink() {
        if (blinker == 0) {
            parm.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parm);
        }
        else  if (blinker == 10) {
            parm.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(parm);
        }

        // Increment blinker and wrap at 100
        blinker = ++blinker % 100;
    }
}
