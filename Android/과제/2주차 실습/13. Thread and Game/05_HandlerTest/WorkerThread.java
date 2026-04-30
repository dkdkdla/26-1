package com.example.a05_handlertest;

import android.os.Handler;
import android.os.Message;

public class WorkerThread extends Thread {
    Handler handler;

    WorkerThread(Handler handler) {
        this.handler = handler;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            Message msg = new Message();
            msg.what = 1;
            msg.arg1 = i;
            handler.sendMessage(msg);
        }
    }
}