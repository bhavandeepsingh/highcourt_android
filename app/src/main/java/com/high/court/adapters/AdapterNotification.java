package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.HonbleJudgesActivity;
import com.high.court.helpers.DateHelper;
import com.high.court.http.models.NotificationModel;

import java.util.List;


public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolder> {
    Context context;

    List<NotificationModel.Notifications> notificationsList;

    public AdapterNotification(Context ctx) {
        super();
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

        if(viewHolder.notification_description != null) viewHolder.notification_description.setText(getNotificationsList().get(i).getDescription());
        if(viewHolder.notification_time != null) viewHolder.notification_time.setText(DateHelper.getTimeAgo(Long.parseLong(getNotificationsList().get(i).getCreated_at())));
        if(viewHolder.download_file != null){
            if(getNotificationsList().get(i).getIs_file() == NotificationModel.NOTIFAICATION_HAS_ATTACHMENT){
                viewHolder.download_file.setVisibility(View.VISIBLE);
                viewHolder.download_file.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, HonbleJudgesActivity.class).putExtra("file_url_for_web_view", getNotificationsList().get(i).getNotification_src()));
                    }
                });
            }else{
                viewHolder.download_file.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return getNotificationsList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView notification_description, notification_time;
        LinearLayout download_file;

        public ViewHolder(View itemView) {
            super(itemView);
            notification_description = (TextView) itemView.findViewById(R.id.notification_description);
            notification_time = (TextView) itemView.findViewById(R.id.notification_time);
            download_file = (LinearLayout)itemView.findViewById(R.id.download_file);
        }
    }

    public List<NotificationModel.Notifications> getNotificationsList() {
        if(notificationsList == null) notificationsList = HighCourtApplication.getNotificationsList();
        return notificationsList;
    }
}

