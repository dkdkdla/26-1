package com.example.a05_handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WorkerThread thread;
    TextView tv;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text_cnt);

        handler = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    tv.setText("카운터=" + msg.arg1);
                    return true;
                }
                return false;
            }
        });

        thread = new WorkerThread(handler);
        thread.start();
    }
}