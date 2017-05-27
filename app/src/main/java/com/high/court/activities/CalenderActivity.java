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
import com.high.court.helpers.HighCourtLoader;
import com.high.court.http.models.HolidaysModel;
import com.high.court.http.models.http_interface.HolidayInterface;
import com.high.court.layouts.HighCourtCalender;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CalenderActivity extends HighCourtActivity implements HolidayInterface {

    HighCourtLoader highCourtLoader;

    Context context = CalenderActivity.this;

    HighCourtCalender highCourtCalender;

    AdapterHolidaysCalender adapterHolidaysCalender;

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
        adapterHolidaysCalender = new AdapterHolidaysCalender(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterHolidaysCalender);

        highCourtCalender = (HighCourtCalender) findViewById(R.id.calendarView);

        setSelectDates();

        highCourtCalender.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                getHighCourtLoader().start();
                HolidaysModel.getHolidays(CalenderActivity.this, makeRequest(date));
            }
        });

    }

    private void setSelectDates() {
        highCourtCalender.addDecorator(null);
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

    private Map<String, String> makeRequest(CalendarDay date) {
        Map<String, String> stringStringMap = new HashMap<>();
        int year = date.getYear();
        int month = date.getMonth();
        if(month == 12){
            year = year+1;
            month = 1;
        }else{ month = month+1; }
        stringStringMap.put("HolidaysSearch[date]",  year + "-" + month);
        return stringStringMap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onHolidaysSuccess(HolidaysModel holidaysModel) {
        getHighCourtLoader().stop();
        if(holidaysModel != null){
            HighCourtApplication.setHolidaysModel(holidaysModel);
            getAdapterHolidaysCalender().notifyDataSetChanged();
            setSelectDates();
        }
    }

    @Override
    public void onHolidaysFailur() {
        getHighCourtLoader().stop();
    }

    @Override
    public void onHolidaysFailur(Throwable t) {
        getHighCourtLoader().stop();
    }

    public AdapterHolidaysCalender getAdapterHolidaysCalender() {
        return adapterHolidaysCalender;
    }

    public void setAdapterHolidaysCalender(AdapterHolidaysCalender adapterHolidaysCalender) {
        this.adapterHolidaysCalender = adapterHolidaysCalender;
    }

    public HighCourtLoader getHighCourtLoader() {
        if(highCourtLoader == null) highCourtLoader = HighCourtLoader.init(context);
        return highCourtLoader;
    }
}
