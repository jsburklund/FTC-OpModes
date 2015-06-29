package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * An empty op mode serving as a template for custom OpModes
 */
public class Notifications extends OpMode {
    Context context;
    Notification.Builder mBuilder;
    Notification mNotification;
    NotificationManager mNotificationManager;
    int notificationID = 3550;

    /*
    * Constructor
    */
    public Notifications() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
    */
    @Override
    public void start() {
        context = hardwareMap.appContext;
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(context);
        //These three are required for the notification to Display
        mBuilder.setContentTitle("Robot Status");
        mBuilder.setContentText("Hello World!");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);  //Just an icon that is already in R, can be anything
        mBuilder.setLights(Color.RED, 100, 25);
        mBuilder.setPriority(Notification.PRIORITY_MAX);    //required to set the led blinking
        //This sometimes works, but only when plugged in and there are other notifications
        //The light also does not stay consistently on

        mNotification = mBuilder.build();
        mNotification.flags |= Notification.FLAG_SHOW_LIGHTS;


        mNotificationManager.notify(notificationID, mNotification);

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
        mNotificationManager.cancel(notificationID);

    }
}
