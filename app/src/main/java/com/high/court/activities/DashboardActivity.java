package com.high.court.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.high.court.R;
import com.high.court.adapters.AdapterDashBoard;
import com.high.court.backround_service.NotificationService;
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.UserHelper;
import com.high.court.layouts.SideProfileDrawer;

import java.util.Timer;
import java.util.TimerTask;

import static com.high.court.R.id.adimagevieww;

public class DashboardActivity extends HighCourtActivity {

    Context context = DashboardActivity.this;
    ImageView adimageview;
    int banner_change_count;
int bannerchangetime=15000;
    public static String[] judgesnamelist = {
            "Executive",
            "Member's directory",
            "Hon'ble judges",
            "Notification",
            "Display Board",
            "Calendar",
            "Roster",
            "Case Law",
            "Achievement",
    };

    int[] iconlist = {
            R.drawable.ic_executivecommitee,
            R.drawable.ic_membedirectory,
            R.drawable.ic_honblejudges,
            R.drawable.ic_notif,
            R.drawable.ic_displayboard,
            R.drawable.ic_calender,
            R.drawable.ic_roaster,
            R.drawable.ic_caselaw,
            R.drawable.ic_achievment,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UserHelper.getState()) {
            setupDashboard();
        } else {
            setupLogin();
        }

    }

    void setupLogin() {
        setContentView(R.layout.activity_login);
    }

    void setupDashboard() {
        setContentView(R.layout.activity_dashboard);
        startService(new Intent(getApplicationContext(), NotificationService.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        startService(new Intent(context, NotificationService.class));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterDashBoard adapter = new AdapterDashBoard(context, judgesnamelist, iconlist);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        adimageview = (ImageView) findViewById(adimagevieww);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        changingAddImages();

    }

    public void changingAddImages() {

        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner_change_count++;
                        if (banner_change_count ==1){
                            adimageview.setImageResource(R.drawable.image_ad);
                        }else if (banner_change_count ==2){
                            adimageview.setImageResource(R.drawable.image_ad2);
                        }else if (banner_change_count ==3){
                            adimageview.setImageResource(R.drawable.image_ad3);
                            banner_change_count =0;
                        }
                    }
                });

            }
        };
        timerObj.schedule(timerTaskObj, 0, bannerchangetime);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notif) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SideProfileDrawer sideProfileDrawer = (SideProfileDrawer) findViewById(R.id.drawer_layout);
        if (sideProfileDrawer != null && sideProfileDrawer.getProfileImageView() != null) {

            sideProfileDrawer.getProfileImageView().setImageResource(0);
            ImageHelper.loadImage(UserHelper.getAppUserProfilePic(), sideProfileDrawer.getProfileImageView());
            sideProfileDrawer.getProfileImageView().invalidate();
        }
    }

}
