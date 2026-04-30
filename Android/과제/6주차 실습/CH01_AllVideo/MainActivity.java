package com.example.ch01_allvideo;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> videoTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.video_list);
        videoTitles = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_VIDEO}, 1);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    public void onClick(View v) {
        videoTitles.clear();
        ContentResolver cr = getContentResolver();

        String[] projection = { MediaStore.Video.Media.DISPLAY_NAME };

        Cursor cursor = cr.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "장치에 동영상이 없습니다!", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    videoTitles.add(cursor.getString(0));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, videoTitles);
                listView.setAdapter(adapter);
            }
            cursor.close();
        }
    }
}