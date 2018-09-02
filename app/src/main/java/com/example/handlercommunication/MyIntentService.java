package com.example.handlercommunication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super(MyIntentService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Intent-Service", "On-Create");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("Intent-Service", "On-Start-Command");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Intent-Service", "On-Handle Intent");
        if (intent != null) {
            Messenger messenger = intent.getParcelableExtra("handler");
            startBackgroundService(messenger);
        }
    }

    private void startBackgroundService(final Messenger messenger) {

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Message message = Message.obtain();
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
        }.start();

    }
}
