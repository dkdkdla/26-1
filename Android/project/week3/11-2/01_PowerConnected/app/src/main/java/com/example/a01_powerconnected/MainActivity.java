package com.example.a01_powerconnected;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver chargerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 방송 수신자 객체를 생성한다.
        chargerReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(), "전원이 연결되었습니다.", Toast.LENGTH_SHORT).show();
            }
        };

        // 방송 수신자를 동적으로 등록한다. (전원 연결 신호 감지)
        registerReceiver(
                chargerReceiver,
                new IntentFilter(Intent.ACTION_POWER_CONNECTED)
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 앱 종료 시 수신자 등록을 해제하여 메모리 누수를 방지한다.
        unregisterReceiver(chargerReceiver);
    }
}