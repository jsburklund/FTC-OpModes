package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class FileReadTest extends OpMode {

    File inputFile;
    FileReader input;
    String text;

    /*
    * Constructor
    */
    public FileReadTest() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        //set the text to an empty string;

        //Declare the filepath to read from
        inputFile = new File("/sdcard/testout.txt");
        try {
            //Set the input file
            input = new FileReader(inputFile);

            //grab the contents one character at a time and append it to the output string
            int c;
            while((c = input.read()) != -1) {
                text += (char) c;
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
