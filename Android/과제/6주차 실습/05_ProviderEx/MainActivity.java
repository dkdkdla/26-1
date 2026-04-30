package com.example.a05_providerex;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.output);

        // 전체 보기 버튼
        findViewById(R.id.queryall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(MovieProvider.CONTENT_URI,
                        null, null, null, null);

                if (cursor != null) {
                    String s = "";
                    while (cursor.moveToNext()) {
                        // Title : Score
                        s += (cursor.getString(1) + ":" +
                                cursor.getString(2) + "\n");
                    }
                    display.setText(s);
                    cursor.close();
                }
            }
        });

        // 3개 영화 추가 버튼
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();

                values.put("TITLE", "Avatar");
                values.put("SCORE", 5.0);
                cr.insert(MovieProvider.CONTENT_URI, values);

                values.put("TITLE", "Back to the Future");
                values.put("SCORE", 4.0);
                cr.insert(MovieProvider.CONTENT_URI, values);

                values.put("TITLE", "Terminator 4");
                values.put("SCORE", 4.5);
                cr.insert(MovieProvider.CONTENT_URI, values);

                display.setText("3개의 영화 정보를 입력했습니다.");
            }
        });

        // 전체 삭제 버튼
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ContentResolver cr = getContentResolver();
                cr.delete(MovieProvider.CONTENT_URI, null, null);
                display.setText("모든 자료를 삭제했습니다.");
            }
        });
    }
}