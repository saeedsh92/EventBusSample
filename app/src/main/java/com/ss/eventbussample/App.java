package com.ss.eventbussample;

import android.app.Application;

/**
 * Created by admin on 2/10/18.
 */

public class App extends Application {
    private MessageEmitter messageEmitter;
    private int activityCount;
    @Override
    public void onCreate() {
        super.onCreate();
        messageEmitter=new MessageEmitter();
        messageEmitter.start();
    }

    public void onCreateNewActivity(){
        activityCount+=1;
        checkState();
    }

    public void onDestroySomeActivity(){
        activityCount-=1;
        checkState();
    }

    private void checkState(){
        if (activityCount==0){
            messageEmitter.stop();
        }
    }
}
