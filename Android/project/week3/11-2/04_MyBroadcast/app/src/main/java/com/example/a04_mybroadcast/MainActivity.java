package com.example.a04_mybroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        // 방송의 고유 액션(암호명)을 설정한다.
        intent.setAction("com.example.CUSTOM_INTENT");
        // 시스템 전체에 방송을 송출한다.
        sendBroadcast(intent);
    }
}