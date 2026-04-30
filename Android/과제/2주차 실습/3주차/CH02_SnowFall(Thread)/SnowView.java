package com.example.ch02_snowfallthread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SnowView extends SurfaceView implements SurfaceHolder.Callback {
    private final int SNOW_COUNT = 50;
    private SnowFlake[] snowflakes = new SnowFlake[SNOW_COUNT];
    private SnowThread thread;
    private Bitmap background;

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        thread = new SnowThread(getHolder());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        for (int i = 0; i < SNOW_COUNT; i++) {
            snowflakes[i] = new SnowFlake(getWidth(), getHeight());
        }
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) { }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder h, int f, int w, int h1) { }

    class SnowThread extends Thread {
        private boolean mRun = false;
        private SurfaceHolder mHolder;

        public SnowThread(SurfaceHolder holder) { mHolder = holder; }
        public void setRunning(boolean b) { mRun = b; }

        @Override
        public void run() {
            while (mRun) {
                Canvas c = null;
                try {
                    c = mHolder.lockCanvas();
                    if (c != null) {
                        c.drawBitmap(background, null, new Rect(0, 0, getWidth(), getHeight()), null);
                        synchronized (mHolder) {
                            for (SnowFlake flake : snowflakes) {
                                flake.draw(c);
                            }
                        }
                    }
                } finally {
                    if (c != null) mHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}