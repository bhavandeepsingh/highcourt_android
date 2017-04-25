package com.high.court.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.high.court.R;
import com.high.court.adapters.AdapterDisplayBoardDetail;
import com.high.court.adapters.AdapterExicutiveComm;

public class DisplayBoardDetail extends AppCompatActivity {


    Context context = DisplayBoardDetail.this;


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
        setContentView(R.layout.activity_display_board_detail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Display Board");


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterDisplayBoardDetail adapter = new AdapterDisplayBoardDetail(context,  judgesnamelist,courtroomlist);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }


}
