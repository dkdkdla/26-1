package com.example.a09_pingpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

public class Ball {
    int x, y, xInc = 1, yInc = 1; // xInc와 yInc는 한번에 움직이는 거리이다.
    int diameter; // 공의 반지름
    static int WIDTH = 1080, HEIGHT = 1920; // 움직이는 공간의 크기
    Paint paint;
    Random random = new Random();

    public Ball(int d) { // 생성자
        this.diameter = d;

        // 볼의 위치를 랜덤하게 설정
        x = (int) (Math.random() * (WIDTH - d) + 3);
        y = (int) (Math.random() * (HEIGHT - d) + 3);

        // 한번에 움직이는 거리도 랜덤하게 설정
        xInc = (int) (Math.random() * 30 + 1);
        yInc = (int) (Math.random() * 30 + 1);
        paint = new Paint();
        int color = Color.argb(255, random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256));
        paint.setColor(color);
    }

    // 여기서 공을 그린다.
    public void paint(Canvas g) {
        // 벽이 부딪히면 반사하게 한다.
        if (x < diameter || x > (WIDTH - diameter))
            xInc = -xInc;
        if (y < diameter || y > (HEIGHT - diameter))
            yInc = -yInc;

        // 볼의 좌표를 갱신한다.
        x += xInc;
        y += yInc;

        // 볼을 화면에 그린다.
        g.drawCircle(x, y, diameter, paint);
    }
}