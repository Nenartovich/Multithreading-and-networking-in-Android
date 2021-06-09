package com.nenartovich.task2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

public class IncreaseProgressService extends Service {

    private final IBinder iBinder = new LocalService();
    private int progress = 0;
    private boolean isStopped = false;
    public static final String DECREASE_ACTION = "com.nenartovich.task2.DECREASE_ACTION";
    BroadcastReceiver decreaseReceiver;

    public IncreaseProgressService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        decreaseReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                setProgress(Math.max(getProgress() - 55, -5));
                if (isStopped) {
                    doIncreasing();
                    isStopped = false;
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(DECREASE_ACTION);
        registerReceiver(decreaseReceiver, intentFilter);
        doIncreasing();
    }


    public class LocalService extends Binder {
        IncreaseProgressService getService () {
            return IncreaseProgressService.this;
        }
    }

    public synchronized void setProgress(int value) {
        progress = value;
    }
    public synchronized int getProgress() {
        return progress;
    }

    Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
    private void doIncreasing() {
        Thread thread = new Thread(() -> {
            while (getProgress() <= 95) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgress(getProgress()+5);
                intent.putExtra(MainActivity.PROGRESS, progress);
                sendBroadcast(intent);
            }
            isStopped = true;
        });
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(decreaseReceiver);
    }
}
