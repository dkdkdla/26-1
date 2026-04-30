package com.example.ch01_quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new StartFragment())
                    .commit();
        }
    }

    public void goQuiz1(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.fragment_container, new Quiz1Fragment());
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    public void goQuiz2(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.fragment_container, new Quiz2Fragment());
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    public void goFinish(View v) {
        Toast.makeText(this, "퀴즈가 모두 끝났습니다!", Toast.LENGTH_SHORT).show();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.fragment_container, new StartFragment());
        ft.commitAllowingStateLoss();
    }
}