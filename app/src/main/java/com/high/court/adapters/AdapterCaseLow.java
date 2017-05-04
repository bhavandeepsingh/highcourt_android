package com.high.court.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.high.court.R;


public class AdapterCaseLow extends RecyclerView.Adapter<AdapterCaseLow.ViewHolder> {

    Context context;

    public AdapterCaseLow(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_caselow, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return 22;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}

