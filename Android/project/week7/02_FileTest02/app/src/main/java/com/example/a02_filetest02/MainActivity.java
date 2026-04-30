package com.example.a02_filetest02;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String FILENAME = "test.txt";
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED) == false) {
            Toast.makeText(this, "외부 스토리지 실패", Toast.LENGTH_SHORT).show();
        }

        edit = (EditText) findViewById(R.id.EditText01);

        // 읽기 버튼 설정
        Button readButton = (Button) findViewById(R.id.read);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    InputStream is;
                    is = new FileInputStream(file);
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    is.close();
                    edit.setText(new String(buffer));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 쓰기 버튼 설정
        Button writeButton = (Button) findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(edit.getText().toString().getBytes());
                    os.close();
                    edit.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}