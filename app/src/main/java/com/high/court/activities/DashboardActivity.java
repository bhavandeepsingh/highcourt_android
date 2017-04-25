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
import android.view.View;
import android.widget.LinearLayout;

import com.high.court.R;
import com.high.court.adapters.AdapterDashBoard;

public class DashboardActivity extends HighCourtActivity {
    Context context = DashboardActivity.this;

    LinearLayout myprofile_row, paymudues_row, changepassword_row, logout_row;


    public static String[] judgesnamelist = {
            "Executive",
            "Member's directory",
            "Hon'ble judges",
            "Notification",
            "Display Board",
            "Calendar",
            "Roster",
            "Case Law",
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
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterDashBoard adapter = new AdapterDashBoard(context, judgesnamelist, iconlist);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    public void click_myprofile(View view) {
        Intent intent = new Intent(getApplication(), ProfileActivity2.class);
        startActivity(intent);
    }

    public void click_paymudues(View view) {
        Intent intent = new Intent(getApplication(), MySubscriptionActivity.class);
        startActivity(intent);
    }

    public void click_changepassword(View view) {
        Intent intent = new Intent(getApplication(), ChangePassword.class);
        startActivity(intent);
    }

    public void clicklogout(View view) {
        Intent intent = new Intent(getApplication(), LoginActivity.class);
        startActivity(intent);
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
