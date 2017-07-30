package com.ro0kiey.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ro0kiey.percentprogressbar.PercentProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private PercentProgressBar ppb1, ppb2, ppb3, ppb4, ppb5, ppb6, ppb7, ppb8, ppb9, ppb10, ppb11, ppb12;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ppb1 = (PercentProgressBar)findViewById(R.id.ppb1);
        ppb2 = (PercentProgressBar)findViewById(R.id.ppb2);
        ppb3 = (PercentProgressBar)findViewById(R.id.ppb3);
        ppb4 = (PercentProgressBar)findViewById(R.id.ppb4);
        ppb5 = (PercentProgressBar)findViewById(R.id.ppb5);
        ppb6 = (PercentProgressBar)findViewById(R.id.ppb6);
        ppb7 = (PercentProgressBar)findViewById(R.id.ppb7);
        ppb8 = (PercentProgressBar)findViewById(R.id.ppb8);
        ppb9 = (PercentProgressBar)findViewById(R.id.ppb9);
        ppb10 = (PercentProgressBar)findViewById(R.id.ppb10);
        ppb11 = (PercentProgressBar)findViewById(R.id.ppb11);
        ppb12 = (PercentProgressBar)findViewById(R.id.ppb12);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ppb7.progressIncreasedBy(1);
                        ppb8.progressIncreasedBy(2);
                        ppb9.progressIncreasedBy(3);
                        ppb10.progressIncreasedBy(1);
                        ppb11.progressIncreasedBy(2);
                        ppb12.progressIncreasedBy(3);
                    }
                });
            }
        }, 1000, 100);

    }
}
