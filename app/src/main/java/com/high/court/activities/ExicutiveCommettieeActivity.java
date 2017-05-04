package com.high.court.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;


import com.high.court.R;
import com.high.court.adapters.AdapterExicutiveComm;

public class ExicutiveCommettieeActivity extends HighCourtActivity {

    Context context = ExicutiveCommettieeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exicutive_committee);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Executive");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterExicutiveComm adapter = new AdapterExicutiveComm(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }

        if (id == R.id.action_notif) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
