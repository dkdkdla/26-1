package com.example.a01_musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        // 서비스가 처음 실행되면 음악 재생기를 생성한다.
        player = MediaPlayer.create(this, R.raw.old_pop);
        player.setLooping(false);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Music Service가 중지되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy()");
        player.stop(); // 서비스가 중지되면 음악 재생기를 중지한다.
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Music Service가 시작되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart()");
        player.start(); // 서비스가 시작될 때마다 음악 재생을 시작한다.
        return super.onStartCommand(intent, flags, startId);
    }
}