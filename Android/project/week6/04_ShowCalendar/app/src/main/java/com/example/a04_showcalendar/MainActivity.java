package com.example.a04_showcalendar;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    public TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.output);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALENDAR}, 1);
    }

    public void onClick(View v) {
        String[] projection = {
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE
        };

        Cursor cursor = getContentResolver().query(
                CalendarContract.Events.CONTENT_URI,
                projection, null, null, null);

        int nameIdx = cursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE);
        int idIdx = cursor.getColumnIndexOrThrow(CalendarContract.Events._ID);

        while (cursor.moveToNext()) {
            String id = cursor.getString(idIdx);
            String title = cursor.getString(nameIdx);

            message.append(id + " : " + title + "\n\n");
        }
        cursor.close();
    }
}