package com.high.court.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.high.court.R;
import com.high.court.adapters.AdapterHonbleHudges;

public class HonbleJudgesActivity extends HighCourtActivity {

    Context context = HonbleJudgesActivity.this;

    public static String[] judgesnamelist = {
            "Satish Kumar Mittal",
            "Satish Kumar Mittal",

    };
    public static String[] courtroomlist = {
            "75/-",
            "75/-",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honble_judges);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Hon'ble Judges");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterHonbleHudges adapter = new AdapterHonbleHudges(context,  judgesnamelist,courtroomlist);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);


    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

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
