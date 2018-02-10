package com.ss.eventbussample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2/10/18.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).onCreateNewActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((App) getApplication()).onDestroySomeActivity();
    }
}
