package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class FileWriteTest extends OpMode {
    File outputfile;
    FileWriter out;
    String text;


    /*
    * Constructor
    */
    public FileWriteTest() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        //Declare the file path
        outputfile = new File("/sdcard/testout.txt");

        //Set the file for write mode
        try {
            out = new FileWriter(outputfile);
        } catch (IOException e) {
            DbgLog.logStacktrace(e);
        }

        //Write some text to the file
        text = "Hello World\nThis is another Line!";
        try {
            out.write(text);
        } catch (IOException e) {
            DbgLog.logStacktrace(e);
        }

        //Close the resource
        if(out != null) {
            try {
                out.close();
            } catch (IOException e) {
                DbgLog.logStacktrace(e);
            }
        }


    }


    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {

    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {

    }
}
