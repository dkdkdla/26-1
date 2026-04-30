package com.example.a03_publicimage;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends Activity implements View.OnTouchListener {
    ImageView imageView;
    Bitmap orgBitmap;
    Bitmap changedBitmap;
    Canvas canvas;
    Paint paint;
    Matrix matrix;
    float x1 = 0, y1 = 0, x2 = 0, y2 = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.setOnTouchListener(this);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public void choose(View v) {
        Intent choosePictureIntent
                = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(choosePictureIntent, 0);
    }

    public void save(View v) {
        OutputStream fos;
        if (changedBitmap == null) return;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues contentValues = new ContentValues(3);
                contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "My Pictures");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/bmp");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,
                        Environment.DIRECTORY_PICTURES);
                Uri imageFileUri = getContentResolver()
                        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                contentValues);
                fos = getContentResolver().openOutputStream(imageFileUri);
            } else { // Version Q 미만
                String imagesDir
                        = Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .toString();
                File image = new File(imagesDir, "My Picture");
                fos = new FileOutputStream(image);
            }
            changedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        } catch (Exception e) {
            Log.v("EXCEPTION", e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            Uri uri = intent.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                orgBitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(uri), null, bmpFactoryOptions);
                changedBitmap = Bitmap.createBitmap(orgBitmap.getWidth(),
                        orgBitmap.getHeight(),
                        orgBitmap.getConfig());

                canvas = new Canvas(changedBitmap);
                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeWidth(10);
                matrix = new Matrix();
                canvas.drawBitmap(orgBitmap, matrix, paint);

                imageView.setImageBitmap(changedBitmap);
                imageView.setOnTouchListener(this);
            } catch (Exception e) {
                Log.v("ERROR", e.toString());
            }
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                y2 = event.getY();
                canvas.drawLine(x1, y1, x2, y2, paint);
                imageView.invalidate();
                x1 = x2;
                y1 = y2;
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                canvas.drawLine(x1, y1, x2, y2, paint);
                imageView.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }
}