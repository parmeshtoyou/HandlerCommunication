package com.example.handlercommunication;

import android.os.Handler;
import android.os.Message;

public class CustomHandler extends Handler {

    AppReceiver appReceiver;

    CustomHandler(AppReceiver receiver) {
        appReceiver = receiver;
    }

    interface AppReceiver {
        void onUpdateUI(String msg);
    }

    @Override
    public void handleMessage(Message msg) {
        appReceiver.onUpdateUI(String.valueOf("This is the value: "+ msg.what));
    }
}
