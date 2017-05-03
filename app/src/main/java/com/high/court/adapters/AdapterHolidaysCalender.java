package com.high.court.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.high.court.R;


public class AdapterHolidaysCalender extends RecyclerView.Adapter<AdapterHolidaysCalender.ViewHolder> {
    Context context;

    public AdapterHolidaysCalender(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_holidays, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {



    }

    @Override
    public int getItemCount() {
       // return get_ratelist_title.length;
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       // TextView title, title_val;

        public ViewHolder(View itemView) {
            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.title);
//            title_val = (TextView) itemView.findViewById(R.id.title_val);
        }
    }

}

