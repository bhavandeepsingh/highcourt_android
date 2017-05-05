package com.high.court.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.adapters.AdapterDashBoard;
import com.high.court.adapters.AdapterHolidaysCalender;
import com.high.court.adapters.AdapterRoster;
import com.high.court.tabs.Pager;

import java.lang.reflect.Field;

public class RosterActivity extends HighCourtActivity{

    Context context = RosterActivity.this;
    AdapterRoster adapterRoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Roster");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterRoster = new AdapterRoster(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterRoster);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterRoster getAdapterRoster() {
        return adapterRoster;
    }

    public void setAdapterRoster(AdapterRoster adapterRoster) {
        this.adapterRoster = adapterRoster;
    }
}
