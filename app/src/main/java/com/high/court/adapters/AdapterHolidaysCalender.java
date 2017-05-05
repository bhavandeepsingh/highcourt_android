package com.high.court.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.http.models.HolidaysModel;

import java.util.List;


public class AdapterHolidaysCalender extends RecyclerView.Adapter<AdapterHolidaysCalender.ViewHolder> {
    Context context;

    public AdapterHolidaysCalender(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()) .inflate(R.layout.adapter_holidays, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(viewHolder.holidays_date != null) viewHolder.holidays_date.setText(getHolidaysList().get(i).getFormattedDate());
        if(viewHolder.holiday_title != null) viewHolder.holiday_title.setText(getHolidaysList().get(i).getTitle());
        if(viewHolder.holiday_highcourt != null) viewHolder.holiday_highcourt.setText(getHolidaysList().get(i).getDisplayHighcourtName());
    }

    @Override
    public int getItemCount() {
        return getHolidaysList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       TextView holidays_date, holiday_title, holiday_highcourt;

        public ViewHolder(View itemView) {
            super(itemView);
            holidays_date = (TextView) itemView.findViewById(R.id.holidays_date);
            holiday_title = (TextView) itemView.findViewById(R.id.holiday_title);
            holiday_highcourt = (TextView) itemView.findViewById(R.id.holiday_highcourt);
        }
    }

    List<HolidaysModel.Holidays> getHolidaysList(){
        if(HighCourtApplication.getHolidaysModel() != null){
            return HighCourtApplication.getHolidaysModel().getHolidaysList();
        }return null;
    }

}

