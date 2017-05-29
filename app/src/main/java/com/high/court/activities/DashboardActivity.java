package com.high.court.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.high.court.R;
import com.high.court.adapters.AdapterDashBoard;
import com.high.court.backround_service.NotificationService;
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.NetworkHelper;
import com.high.court.helpers.UserHelper;
import com.high.court.http.models.BannnerModel;
import com.high.court.http.models.http_interface.BannerInterface;
import com.high.court.layouts.SideProfileDrawer;

import java.util.Timer;
import java.util.TimerTask;

public class DashboardActivity extends HighCourtActivity implements BannerInterface{

    Context context = DashboardActivity.this;
    ImageView adimageview;

    int bannerchangetime=15000;
    BannnerModel bannnerModels;
    int banner_index = 0;
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

        adimageview = (ImageView) findViewById(R.id.adimagevieww);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if(NetworkHelper.state()){
            BannnerModel.getBanner(this);
        }
        else{
            adimageview.setVisibility(View.GONE);
        }

    }

    public void changingAddImages() {
        banner_index = 0;
        if(bannnerModels.getBanners() != null && bannnerModels.getBanners().size() > 0) {
            adimageview.setVisibility(View.VISIBLE);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (banner_index >= bannnerModels.getBanners().size()) banner_index = 0;
                            ImageHelper.loadImage(bannnerModels.getBanners().get(banner_index).getImage_path(), adimageview);
                            banner_index++;
                        }
                    });
                }
            }, 0, bannnerModels.getBanner_timing());
        }
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

    @Override
    public void onSuccess(BannnerModel bannnerModel) {
        if(bannnerModel != null){
            this.bannnerModels = bannnerModel;
            changingAddImages();
        }
    }

    @Override
    public void onBannerError(Throwable t) {
        adimageview.setVisibility(View.GONE);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(UserHelper.getFirstRun() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityCompat.requestPermissions(this, new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 233);
            UserHelper.setFirstRun();
        }
    }
}
