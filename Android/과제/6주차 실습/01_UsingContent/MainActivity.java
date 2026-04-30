package com.example.a01_usingcontent;

import android.Manifest;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Cursor cursor;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.picture);

        // 권한 요청
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public void displayFirstImage(View v) {
        Toast.makeText(getApplicationContext(), "displayFirstImage()", Toast.LENGTH_LONG).show();

        try {
            String[] projection = new String[]{
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.DATA
            };

            // 컨텐츠 제공자 쿼리 실행
            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);

            int size = cursor.getCount();
            if (size == 0) {
                Toast.makeText(getApplicationContext(), "장치에 이미지가 없음!", Toast.LENGTH_LONG).show();
            } else {
                // 쿼리 결과 처리
                if (cursor.moveToFirst()) {
                    String imageLocation = cursor.getString(1);
                    Toast.makeText(getApplicationContext(), imageLocation, Toast.LENGTH_LONG).show();

                    // 이미지 파일을 찾아 읽어서 화면에 표시한다.
                    File imageFile = new File(imageLocation);
                    if (imageFile.exists()) {
                        Bitmap bm = BitmapFactory.decodeFile(imageLocation);
                        imageView.setImageBitmap(bm);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}