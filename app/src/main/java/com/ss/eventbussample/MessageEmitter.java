package com.ss.eventbussample;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2/10/18.
 */

public class MessageEmitter {
    private Timer timer;
    private Queue<String> messages;
    public MessageEmitter(){
        messages = new ArrayDeque<>();
        messages.add("Hello saeed :) How are you?");
        messages.add("your latest library has a ton of issues, fix iiiiiit!");
        messages.add("do you want to work at google? i can help you to join our team.");
        messages.add("you are the best programmer i have ever seen.");
        messages.add("see you soon");
        messages.add("bye");
    }

    public void start(){
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String messageContent = messages.poll();
                if (messageContent == null) return;
                Message message=new Message();
                message.setFullName("Jake wharton");
                message.setContent(messageContent);
                message.setDate(Calendar.getInstance().getTime().toString());
                // TODO: 2/10/18 Send message using eventbus
            }
        }, 2000, 6000);
    }

    public void stop() {
        timer.purge();
        timer.cancel();
    }
}
