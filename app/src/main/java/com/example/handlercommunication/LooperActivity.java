package com.example.handlercommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LooperActivity extends AppCompatActivity {

    private TextView textView;
    private MyThreadHandler threadHandler;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);

        textView = findViewById(R.id.looperTv);
        threadHandler = new MyThreadHandler();
        threadHandler.start();

        findViewById(R.id.startThread).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Thread thread = new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Message msg = new Message();
                            msg.what = i;
                            threadHandler.handler.sendMessage(msg);
                        }
                    }
                };
                thread.start();*/

                threadHandler.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (i = 0; i < 10; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("Thread "+Thread.currentThread().getId() +" count:"+i);
                                }
                            });
                        }
                    }
                });
            }
        });


    }
}

class MyThreadHandler extends Thread {
    Handler handler;

    @Override
    public void run() {
        Looper.prepare();
        /*handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.d("MyThreadHandler", "ThreadId:" + Thread.currentThread().getId() + ", Message:" + msg.what);
            }
        };*/
        handler = new Handler();
        Looper.loop();
    }
}
