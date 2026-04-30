package com.example.a02_threadbasic1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Thread w;
    boolean running = true;

    @Override
    public void onStart() {
        super.onStart();
        w = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20 && running; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    Log.v("THREAD", "time=" + i);
                }
            }
        });
        running = true;
        w.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
    }
}