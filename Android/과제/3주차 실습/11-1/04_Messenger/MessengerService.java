package com.example.a04_messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {
    static final int MSG_SAY_HELLO = 1;

    // 클라이언트로부터 오는 메시지를 처리하는 핸들러
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(),
                            "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    // 클라이언트가 메시지를 보낼 수 있는 타겟
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),
                "binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }
}
