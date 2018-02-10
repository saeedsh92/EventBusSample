package com.ss.eventbussample;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2/10/18.
 */

public class MessageEmitter {
    private Timer timer;
    public MessageEmitter(){

    }

    public void start(){
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.setFullName("Jake wharton");
                message.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
                message.setDate(Calendar.getInstance().getTime().toString());
                // TODO: 2/10/18 Emmit message here
            }
        },2000,4000);
    }

    public void stop(){
        timer.purge();
        timer.cancel();
    }
}
