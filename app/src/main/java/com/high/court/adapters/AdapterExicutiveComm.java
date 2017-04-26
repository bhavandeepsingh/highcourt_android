package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.high.court.R;
import com.high.court.activities.ExicutiveMemberDetail;


public class AdapterExicutiveComm extends RecyclerView.Adapter<AdapterExicutiveComm.ViewHolder> {
    Context context;
    String[] get_judgesnamelist;
    String[] get_courtroomlist;

    public AdapterExicutiveComm(Context ctx, String[] judgesnamelist, String[] courtroomlist) {
        super();
        get_judgesnamelist = judgesnamelist;
        get_courtroomlist = courtroomlist;

        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_exicutive_comm, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {



    }

    @Override
    public int getItemCount() {
       // return get_ratelist_title.length;
        return 22;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       // TextView title, title_val;
        LinearLayout rowview;

        public ViewHolder(View itemView) {
            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.title);
//            title_val = (TextView) itemView.findViewById(R.id.title_val);
            rowview = (LinearLayout) itemView.findViewById(R.id.rowview);

            rowview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExicutiveMemberDetail.class);
                    context.startActivity(intent);
                }
            });

        }
    }

}

