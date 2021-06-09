package com.nenartovich.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = "com.nenartovich.task2.BROADCAST_ACTION";
    public static final String PROGRESS = "com.nenartovich.task2.PROGRESS";

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IncreaseProgressService.LocalService localService = (IncreaseProgressService.LocalService)iBinder;
            myService = localService.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    IncreaseProgressService myService;
    boolean isBind = false;
    BroadcastReceiver br;
    ProgressBar progressBar;
    private TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, IncreaseProgressService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        tvProgress = findViewById(R.id.tvProgress);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        Button decreaseProgressButton = findViewById(R.id.btnDecreaseProgress);
        decreaseProgressButton.setOnClickListener(view ->
                sendBroadcast(new Intent(IncreaseProgressService.DECREASE_ACTION)));

        br = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = intent.getIntExtra(PROGRESS, 0);
                if (result == 100) {
                    Toast.makeText(MainActivity.this, "Loading completed.", Toast.LENGTH_SHORT).show();
                }
                progressBar.setProgress(result);
                tvProgress.setText(getResources().getString(R.string.progress)+ " " + result);
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind) {
            unbindService(serviceConnection);
            isBind = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}