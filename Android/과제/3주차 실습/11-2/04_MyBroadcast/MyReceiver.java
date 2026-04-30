package com.example.a04_mybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 방송이 감지되면 사용자에게 토스트 메시지를 보여준다.
        Toast.makeText(context, "인텐트가 감지되었음!", Toast.LENGTH_LONG).show();
    }
}