package com.example.a04_preftest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button saveButton;
    private String sharedPrefFile = "my_settings";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });

        // 앱이 시작될 때 저장된 설정을 읽어옵니다.
        loadSettings();
    }

    private void saveSettings() {
        String username = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    private void loadSettings() {
        String username = sharedPreferences.getString("username", "");
        editText.setText(username);
    }
}