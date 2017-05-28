package com.high.court.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.high.court.R;
import com.high.court.adapters.AdapterNotification;
import com.high.court.backround_service.NotificationService;
import com.high.court.http.models.NotificationModel;

import java.util.HashMap;
import java.util.Map;

public class NoificationActivity extends HighCourtActivity {

    Context context = NoificationActivity.this;
    Map<String, Integer> notification_read = new HashMap<>();

    AdapterNotification adapterNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Notification");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterNotification = new AdapterNotification(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterNotification);

        recyclerView.addOnChildAttachStateChangeListener(new ChildAttachListener(llm, this));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private class ChildAttachListener implements RecyclerView.OnChildAttachStateChangeListener {

        LinearLayoutManager llm;
        NoificationActivity noificationActivity;

        public ChildAttachListener(LinearLayoutManager llm, NoificationActivity noificationActivity) {
            super();
            this.llm = llm;
            this.noificationActivity = noificationActivity;
        }

        @Override
        public void onChildViewAttachedToWindow(final View view) {
            noificationActivity.addReadNotification(llm.findLastVisibleItemPosition());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        noificationActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(view.findViewById(R.id.notif_layer) != null) view.findViewById(R.id.notif_layer).setBackgroundResource(R.drawable.border_subscription);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    }

    public void addReadNotification(int index){
        if(index >= 0){
            if(getAdapterNotification().getNotificationsList().get(index).getIsRead() <= 0) {
                int notification_id = getAdapterNotification().getNotificationsList().get(index).getId();
                getNotification_read().put(String.valueOf(index), notification_id);
                getAdapterNotification().getNotificationsList().get(index).setIsRead(1);
            }
        }
    }

    public AdapterNotification getAdapterNotification() {
        return adapterNotification;
    }

    public void setAdapterNotification(AdapterNotification adapterNotification) {
        this.adapterNotification = adapterNotification;
    }

    public Map<String, Integer> getNotification_read() {
        return notification_read;
    }

    public void setNotification_read(Map<String, Integer> notification_read) {
        this.notification_read = notification_read;
    }

    @Override
    public void onBackPressed() {
        unreadNotification();
        super.onBackPressed();
    }

    private void unreadNotification() {
        if(getNotification_read() != null && getNotification_read().size() > 0){
            Map<String, Integer> stringIntegerMap = new HashMap<>();
            Object[] strings = getNotification_read().values().toArray();
            for(int i = 0; i < strings.length; i++){
                if(strings[i].toString() != null) stringIntegerMap.put("Notification[notification_id]["+String.valueOf(i)+"]", Integer.parseInt(strings[i].toString()));
            }

            if(stringIntegerMap.size() > 0){
                NotificationModel.unReadNotification(stringIntegerMap);
                NotificationService.updateCount(stringIntegerMap.size());
            }
        }
    }
}
