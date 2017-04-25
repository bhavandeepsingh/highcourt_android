package com.high.court.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.high.court.R;

public class DisplayBoardActivity extends HighCourtActivity {

    Context context = DisplayBoardActivity.this;

    LinearLayout caselawlayer,judjelayer;

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
        setContentView(R.layout.activity_display_board);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Display Board");


        caselawlayer = (LinearLayout)findViewById(R.id.caselawlayer);
        judjelayer = (LinearLayout)findViewById(R.id.judjelayer);

        caselawlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayBoardDetail.class);
                startActivity(intent);
            }
        });
        judjelayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayBoardDetail.class);
                startActivity(intent);
            }
        });






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
