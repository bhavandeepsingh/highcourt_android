package com.high.court.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.adapters.AdapterExicutiveComm;
import com.high.court.adapters.AdapterHolidaysCalender;

import java.lang.reflect.Field;

public class CalenderActivity extends HighCourtActivity {

    Context context = CalenderActivity.this;

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
        setContentView(R.layout.activity_calender);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Calendar");





//        try
//        {
//            CalendarView cv = (CalendarView) this.findViewById(R.id.simpleCalendarView);
//            Class<?> cvClass = cv.getClass();
//            Field field = cvClass.getDeclaredField("mMonthName");
//            field.setAccessible(true);
//
//            try
//            {
//                TextView tv = (TextView) field.get(cv);
//                tv.setTextColor(Color.RED);
//            }
//            catch (IllegalArgumentException e)
//            {
//                e.printStackTrace();
//            }
//            catch (IllegalAccessException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        catch (NoSuchFieldException e)
//        {
//            e.printStackTrace();
//        }




        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterHolidaysCalender adapter = new AdapterHolidaysCalender(context,  judgesnamelist,courtroomlist);
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
