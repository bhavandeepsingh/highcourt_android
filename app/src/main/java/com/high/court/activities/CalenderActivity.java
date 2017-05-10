package com.high.court.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.adapters.AdapterHolidaysCalender;
import com.high.court.decorators.EventDecorator;
import com.high.court.helpers.DateHelper;
import com.high.court.http.models.HolidaysModel;
import com.high.court.layouts.HighCourtCalender;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalenderActivity extends HighCourtActivity {

    Context context = CalenderActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Calendar");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterHolidaysCalender adapter = new AdapterHolidaysCalender(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);


        HighCourtCalender highCourtCalender = (HighCourtCalender) findViewById(R.id.calendarView);

        if(HighCourtApplication.getHolidaysModel() != null && HighCourtApplication.getHolidaysModel().getHolidaysList() != null){
            ArrayList<CalendarDay> dates = new ArrayList<>();
            List<HolidaysModel.Holidays> holidaysList = HighCourtApplication.getHolidaysModel().getHolidaysList();
            Calendar calendar = Calendar.getInstance();

            for(int i = 0 ;i < holidaysList.size(); i++){
                try {
                    calendar.setTime(new Date(DateHelper.getHolidayDateTime(holidaysList.get(i).getDate())));
                    dates.add(CalendarDay.from(calendar));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(dates.size() > 0) highCourtCalender.addDecorator(new EventDecorator(Color.RED, dates));
        }
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
