package com.example.ch01_memoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etFilename, etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFilename = findViewById(R.id.et_filename);
        etContent = findViewById(R.id.et_content);

        // WRITE 버튼: 파일 생성 및 저장
        findViewById(R.id.btn_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = etFilename.getText().toString();
                String content = etContent.getText().toString();
                if (filename.isEmpty()) {
                    Toast.makeText(MainActivity.this, "파일 이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, filename + " 저장 완료", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // READ 버튼: 파일 불러오기
        findViewById(R.id.btn_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = etFilename.getText().toString();
                try {
                    FileInputStream fis = openFileInput(filename);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    etContent.setText(new String(buffer));
                    Toast.makeText(MainActivity.this, "파일 읽기 성공", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "파일을 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // DELETE 버튼: 파일 삭제 및 입력창 초기화
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = etFilename.getText().toString();
                if (deleteFile(filename)) {
                    etFilename.setText("");
                    etContent.setText("");
                    Toast.makeText(MainActivity.this, filename + " 삭제 완료", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "삭제 실패 (파일 없음)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}