package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class FileBufferedReadTest extends OpMode {

    File inputFile;
    BufferedReader input;
    String text;

    /*
    * Constructor
    */
    public FileBufferedReadTest() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        //set the text to an empty string;
        text = "";

        //Declare the filepath to read from
        inputFile = new File("/sdcard/testout.txt");
        try {
            //Set the input file
            input = new BufferedReader(new FileReader(inputFile));

            //grab the contents one character at a time and append it to the output string
            String temp;
            int i = 1;
            while ((temp = input.readLine()) != null) {
                text += "Line "+i+": "+temp;
                i++;
            }

            //close the file resource
            input.close();


        } catch (IOException e) {
            DbgLog.logStacktrace(e);
        }

    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
        telemetry.addData("text", text);

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
