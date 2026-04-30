package com.example.ch02_snowfallthread;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

public class SnowFlake {
    private int x, y, speed, size;
    private int screenWidth, screenHeight;
    private Paint paint;
    private Random random = new Random();

    public SnowFlake(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.paint = new Paint();
        this.paint.setColor(Color.WHITE);
        reset();
    }

    public void reset() {
        x = random.nextInt(screenWidth);
        y = -random.nextInt(screenHeight);
        size = random.nextInt(15) + 5;
        speed = random.nextInt(10) + 5;
    }

    public void draw(Canvas canvas) {
        y += speed;

        if (y > screenHeight) {
            reset();
            y = 0;
        }
        canvas.drawCircle(x, y, size, paint);
    }
}