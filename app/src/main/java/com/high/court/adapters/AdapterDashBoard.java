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

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.CommingSoonActivity;
import com.high.court.activities.ExicutiveCommettieeActivity;
import com.high.court.activities.HighCourtActivity;
import com.high.court.activities.MemberDirectoryActivity;
import com.high.court.activities.NoificationActivity;
import com.high.court.helpers.Globals;
import com.high.court.helpers.HighCourtLoader;
import com.high.court.http.models.NotificationModel;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_interface.ExceutiveMemberInterface;
import com.high.court.http.models.http_interface.MemberInterface;
import com.high.court.http.models.http_interface.NotificationInterface;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;


public class AdapterDashBoard extends RecyclerView.Adapter<AdapterDashBoard.ViewHolder> implements ExceutiveMemberInterface, MemberInterface, NotificationInterface {

    Context context;

    String[] get_judgesnamelist;

    int[] imageId;

    HighCourtLoader highCourtLoader;

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
                getHighCourtLoader().start();
                ExcecutiveMemberModel.getExecutiveMembers(AdapterDashBoard.this);
            }
            if (i == 1) {
                getHighCourtLoader().start();
                ExcecutiveMemberModel.getMembersList(AdapterDashBoard.this, new HashMap<String, RequestBody>(), 0, false);
            }
            if (i == 2) {
             //   Intent intent = new Intent(context, HonbleJudgesActivity.class);
                Intent intent = new Intent(context, CommingSoonActivity.class);
                context.startActivity(intent);
            }
            if (i == 3) {
                getHighCourtLoader().start();
                NotificationModel.getNotificationList(AdapterDashBoard.this);
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

    @Override
    public void onListMembers(List<ProfileModel> profileModels) {
        getHighCourtLoader().stop();
        HighCourtApplication.setProfileModels(profileModels);
        getHighCourtActivity().startActivity(new Intent(getContext(), ExicutiveCommettieeActivity.class));
    }

    @Override
    public void onListMemberFailur(ExcecutiveMemberModel excecutiveMemberModel) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onListMemberFailur(Throwable t) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onNotificationSuccess(NotificationModel notificationModel) {
        HighCourtApplication.setNotifcationList(notificationModel.getNotificationses());
        getHighCourtLoader().stop();
        context.startActivity(new Intent(context, NoificationActivity.class));
    }

    @Override
    public void onNotificationFailur(Throwable t) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onNotificationError(NotificationModel notificationModel) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onProfileMembers(ExcecutiveMemberModel excecutiveMemberModel) {
        getHighCourtLoader().stop();
        if(excecutiveMemberModel != null) {
            HighCourtApplication.setProfileModels(excecutiveMemberModel.getProfileModels());
            getHighCourtActivity().startActivity(new Intent(getContext(), MemberDirectoryActivity.class));
        }
    }

    @Override
    public void onProfileMemberSearch(ExcecutiveMemberModel excecutiveMemberModel) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onProfileMemberFailur(ExcecutiveMemberModel excecutiveMemberModel) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onProfileMemberFailur(Throwable t) {
        getHighCourtLoader().stop();
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

    public HighCourtLoader getHighCourtLoader() {
        return (highCourtLoader == null)? setHighCourtLoader(HighCourtLoader.init(getContext())): highCourtLoader;
    }

    public HighCourtLoader setHighCourtLoader(HighCourtLoader highCourtLoader) {
        return this.highCourtLoader = highCourtLoader;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    HighCourtActivity getHighCourtActivity(){
        return (HighCourtActivity) getContext();
    }

}

