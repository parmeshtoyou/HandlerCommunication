package com.example.handlercommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CustomHandler.AppReceiver {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);

        Button button = findViewById(R.id.startService);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                CustomHandler handler = new CustomHandler(MainActivity.this);
                intent.putExtra("handler", new Messenger(handler));
                startService(intent);
            }
        });

        findViewById(R.id.looperActivity).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LooperActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.foregroundService).setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent foregroundIntent = new Intent(MainActivity.this, ForegroundService.class);
                foregroundIntent.setAction(ForegroundService.START_ACTION);
                startForegroundService(foregroundIntent);
            }
        });
    }

    @Override
    public void onUpdateUI(String msg) {
        textView.setText(msg);
    }
}
