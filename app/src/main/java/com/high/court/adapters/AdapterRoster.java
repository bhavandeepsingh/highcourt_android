package com.high.court.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.http.models.RosterModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class AdapterRoster extends RecyclerView.Adapter<AdapterRoster.ViewHolder> {
    Context context;

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
        if(viewHolder.roster_sr_no != null) viewHolder.roster_sr_no.setText(String.valueOf(i+1));
        if(viewHolder.roster_bench_name != null) viewHolder.roster_bench_name.setText(getRosterList().get(i).getBenchNames());
        if(viewHolder.roster_judges_name != null) viewHolder.roster_judges_name.setText(getRosterList().get(i).getJugesName());
        if(viewHolder.itemView != null){
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
        return getRosterList().size();
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

    List<RosterModel.Roster> getRosterList(){
        if(HighCourtApplication.getRosterModel() != null){
            return HighCourtApplication.getRosterModel().getRosterList();
        }
        return new ArrayList<>();
    }

    void showDialog(String title, String description){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_roster);
        if(dialog.findViewById(R.id.roster_dialog_title) != null) ((TextView) dialog.findViewById(R.id.roster_dialog_title)).setText(title);
        if(dialog.findViewById(R.id.roster_dialog_description) != null) ((TextView) dialog.findViewById(R.id.roster_dialog_description)).setText(description);
        dialog.show();
    }

}

