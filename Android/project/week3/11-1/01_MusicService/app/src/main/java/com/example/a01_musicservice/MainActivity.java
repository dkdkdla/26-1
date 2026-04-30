package com.example.a01_musicservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "MusicService";
    Button start, stop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View src) {
        int id = src.getId();

        // switch 대신 if-else를 사용하면 에러가 사라진다.
        if (id == R.id.start) {
            Log.d(TAG, "onClick() start ");
            startService(new Intent(this, MusicService.class));
        } else if (id == R.id.stop) {
            Log.d(TAG, "onClick() stop");
            stopService(new Intent(this, MusicService.class));
        }
    }
}