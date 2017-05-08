package com.high.court.backround_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.high.court.helpers.BadgeUtils;
import com.high.court.http.models.NotificationModel;

/**
 * Created by bhavan on 5/7/17.
 */

public class NotificationService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    NotificationModel.getUnReadCount(NotificationService.this);
                }
            }
        }).start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void updateBadge(int i) {
        if(i > 0) BadgeUtils.setBadge(getApplicationContext(), i);
        else BadgeUtils.clearBadge(getApplicationContext());
    }
}
