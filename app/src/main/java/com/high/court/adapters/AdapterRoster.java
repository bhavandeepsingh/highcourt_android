package com.high.court.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.HighCourtActivity;
import com.high.court.http.models.RosterModel;

import java.util.List;


public class AdapterRoster extends RecyclerView.Adapter<AdapterRoster.ViewHolder> {

    Context context;


    List<RosterModel.Roster> rosterList;

    public AdapterRoster(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_roster, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (viewHolder.roster_sr_no != null) viewHolder.roster_sr_no.setText(String.valueOf(i + 1));
        if (viewHolder.roster_bench_name != null)
            viewHolder.roster_bench_name.setText(getRosterList().get(i).getBenchNames());
        if (viewHolder.roster_judges_name != null)
            viewHolder.roster_judges_name.setText(getRosterList().get(i).getJugesName());
        if (viewHolder.itemView != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(getRosterList().get(i).getTitle(), getRosterList().get(i).getDescription());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        int list_size = (getRosterList() != null) ? getRosterList().size() : 0;
        if (list_size <= 0) showNoRecords();
        else hideNoRecords();
        return list_size;
    }

    private void hideNoRecords() {
        if (getNoRecordsFounds() != null) {
            getNoRecordsFounds().setVisibility(View.GONE);
        }
    }

    private void showNoRecords() {
        if (getNoRecordsFounds() != null) {
            getNoRecordsFounds().setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView roster_sr_no, roster_bench_name, roster_judges_name;

        public ViewHolder(View itemView) {
            super(itemView);


            roster_sr_no = (TextView) itemView.findViewById(R.id.roster_sr_no);
            roster_bench_name = (TextView) itemView.findViewById(R.id.roster_bench_name);
            roster_judges_name = (TextView) itemView.findViewById(R.id.roster_judges_name);
        }
    }

    public List<RosterModel.Roster> getRosterList() {
        if (rosterList == null) rosterList = HighCourtApplication.getRosterModel().getRosterList();
        return rosterList;
    }

    void showDialog(String title, String description) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_roster);
        if (dialog.findViewById(R.id.roster_dialog_title) != null)
            ((TextView) dialog.findViewById(R.id.roster_dialog_title)).setText(title);
        if (dialog.findViewById(R.id.roster_dialog_description) != null)
            ((TextView) dialog.findViewById(R.id.roster_dialog_description)).setText(description);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.findViewById(R.id.crossicon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public RelativeLayout getNoRecordsFounds() {
        return (RelativeLayout) ((HighCourtActivity) context).findViewById(R.id.no_records_found);
    }

    public void setRosterList(List<RosterModel.Roster> rosterList) {
        this.rosterList = rosterList;
    }
}

