package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.high.court.R;
import com.high.court.activities.WebViewActivity;


public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolder> {
    Context context;
    String[] get_judgesnamelist;
    String[] get_courtroomlist;

    public AdapterNotification(Context ctx, String[] judgesnamelist, String[] courtroomlist) {
        super();
        get_judgesnamelist = judgesnamelist;
        get_courtroomlist = courtroomlist;

        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_notification, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.notif_layer.setVisibility(View.GONE);
        viewHolder.download_layer.setVisibility(View.VISIBLE);
        viewHolder.download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        // return get_ratelist_title.length;
        return 22;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // TextView title, title_val;
        LinearLayout notif_layer, download_layer, download_button;

        public ViewHolder(View itemView) {
            super(itemView);

            notif_layer = (LinearLayout) itemView.findViewById(R.id.notif_layer);
            download_layer = (LinearLayout) itemView.findViewById(R.id.download_layer);
            download_button = (LinearLayout) itemView.findViewById(R.id.download_button);

//            title = (TextView) itemView.findViewById(R.id.title);
//            title_val = (TextView) itemView.findViewById(R.id.title_val);
        }
    }

}

