package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.HighCourtActivity;
import com.high.court.activities.HonableMemberDetail;
import com.high.court.http.models.JudgesModel;

import java.util.ArrayList;
import java.util.List;


public class AdapterHonbleHudges extends RecyclerView.Adapter<AdapterHonbleHudges.ViewHolder> {

    Context context;

    public AdapterHonbleHudges(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_honble_judges, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(viewHolder.itemView != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, HonableMemberDetail.class).putExtra(HonableMemberDetail.PROFILE_LIST_JUDGE_INDEX, String.valueOf(i)));
                }
            });
        }
        if(viewHolder.judge_name != null) viewHolder.judge_name.setText(getJudgesList().get(i).getName());
        if(viewHolder.judge_court_room != null) viewHolder.judge_court_room.setText(getJudgesList().get(i).getCourt_room());
    }

    @Override
    public int getItemCount() {
        int list_size = getJudgesList().size();
        if(list_size <= 0) showNoRecords();
        else hideNoRecords();
        return list_size;
    }

    private void hideNoRecords() {
        if(getNoRecordsFounds() != null){
            getNoRecordsFounds().setVisibility(View.GONE);
        }
    }

    private void showNoRecords() {
        if(getNoRecordsFounds() != null){
            getNoRecordsFounds().setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView judge_name, judge_court_room;
        public ViewHolder(View itemView) {
            super(itemView);
            judge_name = (TextView) itemView.findViewById(R.id.judge_name);
            judge_court_room = (TextView) itemView.findViewById(R.id.judge_court_room);
        }
    }


    public List<JudgesModel.Judge>  getJudgesList(){
        if(HighCourtApplication.getJudgesModel() != null) return HighCourtApplication.getJudgesModel().getJudgeList();
        return new ArrayList<>();
    }

    public RelativeLayout getNoRecordsFounds(){
        return  (RelativeLayout) ((HighCourtActivity) context).findViewById(R.id.no_records_found);
    }



}

