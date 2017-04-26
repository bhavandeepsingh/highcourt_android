package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.activities.CalenderActivity;
import com.high.court.activities.CaseLowActivity;
import com.high.court.activities.CommingSoonActivity;
import com.high.court.activities.DisplayBoardActivity;
import com.high.court.activities.ExicutiveCommettieeActivity;
import com.high.court.activities.HonbleJudgesActivity;
import com.high.court.activities.MemberDirectoryActivity;
import com.high.court.activities.NoificationActivity;
import com.high.court.activities.RosterActivity;
import com.high.court.helpers.Globals;


public class AdapterDashBoard extends RecyclerView.Adapter<AdapterDashBoard.ViewHolder> {
    Context context;
    String[] get_judgesnamelist;
    int[] imageId;


    public AdapterDashBoard(Context ctx, String[] judgesnamelist, int[] prgmImages) {
        super();
        get_judgesnamelist = judgesnamelist;
        imageId = prgmImages;

        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_dashboard, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.titles.setText(get_judgesnamelist[i]);
        Globals.LoadImage("drawable://" + imageId[i], viewHolder.icon);

        viewHolder.rowview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Intent intent = new Intent(context, ExicutiveCommettieeActivity.class);
                    context.startActivity(intent);
                }
                if (i == 1) {
                    Intent intent = new Intent(context, MemberDirectoryActivity.class);
                    context.startActivity(intent);
                }
                if (i == 2) {
                 //   Intent intent = new Intent(context, HonbleJudgesActivity.class);
                    Intent intent = new Intent(context, CommingSoonActivity.class);
                    context.startActivity(intent);
                }
                if (i == 3) {
//                    Intent intent = new Intent(context, NoificationActivity.class);
                    Intent intent = new Intent(context, CommingSoonActivity.class);
                    context.startActivity(intent);
                }
                if (i == 4) {
//                    Intent intent = new Intent(context, DisplayBoardActivity.class);
                    Intent intent = new Intent(context, CommingSoonActivity.class);
                    context.startActivity(intent);
                }
                if (i == 5) {
//                    Intent intent = new Intent(context, CalenderActivity.class);
                    Intent intent = new Intent(context, CommingSoonActivity.class);
                    context.startActivity(intent);
                }
                if (i == 6) {
//                    Intent intent = new Intent(context, RosterActivity.class);
                    Intent intent = new Intent(context, CommingSoonActivity.class);
                    context.startActivity(intent);
                }
                if (i == 7) {
//                    Intent intent = new Intent(context, CaseLowActivity.class);
                    Intent intent = new Intent(context, CommingSoonActivity.class);
                    context.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return get_judgesnamelist.length;
        // return 22;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titles;
        ImageView icon;
        RelativeLayout rowview;

        public ViewHolder(View itemView) {
            super(itemView);
            //  selectcheckbox = (CheckBox) itemView.findViewById(R.id.selectcheckbox);
            titles = (TextView) itemView.findViewById(R.id.titles);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            rowview = (RelativeLayout) itemView.findViewById(R.id.rowview);
//            title_val = (TextView) itemView.findViewById(R.id.title_val);


        }
    }

}

