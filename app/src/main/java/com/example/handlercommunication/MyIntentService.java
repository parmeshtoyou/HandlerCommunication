package com.example.handlercommunication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super(MyIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            Messenger messenger = intent.getParcelableExtra("handler");
            startBackgroundService(messenger);
        }
    }

    private void startBackgroundService(Messenger messenger) {
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.what = i;

            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
