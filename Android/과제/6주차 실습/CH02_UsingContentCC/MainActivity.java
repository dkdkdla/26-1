package com.example.ch02_usingcontentcc;

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

        imageView = findViewById(R.id.picture);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        queryImages();
    }

    private void queryImages() {
        String[] projection = { MediaStore.Images.ImageColumns.DATA };
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            displayImage();
        }
    }


    public void moveToNext(View v) {
        if (cursor != null && cursor.moveToNext()) {
            displayImage();
        } else {
            Toast.makeText(this, "마지막 이미지입니다.", Toast.LENGTH_SHORT).show();
            if (cursor != null) cursor.moveToLast();
        }
    }

    public void moveToPrevious(View v) {
        if (cursor != null && cursor.moveToPrevious()) {
            displayImage();
        } else {
            Toast.makeText(this, "첫 번째 이미지입니다.", Toast.LENGTH_SHORT).show();
            if (cursor != null) cursor.moveToFirst();
        }
    }


    private void displayImage() {
        String imageLocation = cursor.getString(0);
        File imageFile = new File(imageLocation);
        if (imageFile.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(imageLocation);
            imageView.setImageBitmap(bm);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) cursor.close();
    }
}