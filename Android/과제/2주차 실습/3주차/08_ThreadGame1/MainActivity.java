package com.example.a08_threadgame1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button gameButton;
    private TextView scoreTextView;
    private RelativeLayout layout;
    private int score = 0;
    private Handler handler = new Handler();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameButton = findViewById(R.id.gameButton);
        layout = findViewById(R.id.layout);
        scoreTextView = findViewById(R.id.scoreTextView);

        // 버튼을 클릭할 때의 동작
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 시 점수 증가 및 위치 변경
                score++;
                scoreTextView.setText("점수: " + score);
                changeButtonPosition();
            }
        });

        // 앱 시작 3초 뒤에 최초로 버튼 위치를 변경
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { changeButtonPosition(); }
        }, 3000);
    }

    private void changeButtonPosition() {
        // 현재 화면의 가로 폭과 세로 높이를 가져옴
        int screenWidth = layout.getWidth();
        int screenHeight = layout.getHeight();

        // 버튼을 임의의 위치로 이동하기 위한 레이아웃 파라미터 생성
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // 무작위로 새로운 위치를 설정 (버튼이 화면 밖으로 나가지 않게 계산)
        params.leftMargin = random.nextInt(screenWidth - gameButton.getWidth());
        params.topMargin = random.nextInt(screenHeight - gameButton.getHeight());

        // 버튼의 위치를 업데이트
        gameButton.setLayoutParams(params);

        // 새로운 위치에서 3초 뒤에 다시 이동하도록 스케줄링 (무한 루프)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { changeButtonPosition(); }
        }, 3000);
    }
}