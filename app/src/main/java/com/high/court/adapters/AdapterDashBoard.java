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
import com.high.court.activities.AchievementActivity;
import com.high.court.activities.CalenderActivity;
import com.high.court.activities.CaseLawActivity;
import com.high.court.activities.ExicutiveCommettieeActivity;
import com.high.court.activities.HighCourtActivity;
import com.high.court.activities.HonbleJudgesActivity;
import com.high.court.activities.MemberDirectoryActivity;
import com.high.court.activities.NoificationActivity;
import com.high.court.activities.RosterActivity;
import com.high.court.activities.WebViewActivity;
import com.high.court.helpers.Globals;
import com.high.court.helpers.HighCourtLoader;
import com.high.court.helpers.ToastHelper;
import com.high.court.http.models.CaseLawModel;
import com.high.court.http.models.HolidaysModel;
import com.high.court.http.models.JudgesModel;
import com.high.court.http.models.NotificationModel;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.RosterModel;
import com.high.court.http.models.http_interface.CaseLawInterface;
import com.high.court.http.models.http_interface.ExceutiveMemberInterface;
import com.high.court.http.models.http_interface.HolidayInterface;
import com.high.court.http.models.http_interface.JudgesModelInterface;
import com.high.court.http.models.http_interface.MemberInterface;
import com.high.court.http.models.http_interface.NotificationInterface;
import com.high.court.http.models.http_interface.RosterInterface;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import java.util.HashMap;
import java.util.List;


public class AdapterDashBoard extends RecyclerView.Adapter<AdapterDashBoard.ViewHolder> implements ExceutiveMemberInterface, MemberInterface, NotificationInterface, HolidayInterface, JudgesModelInterface, CaseLawInterface, RosterInterface {

    Context context;

    String[] get_judgesnamelist;

    int[] imageId;

    HighCourtLoader highCourtLoader;
    String displayboard_url="https://phhc.gov.in/display_board_full_width.php";

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
                ExcecutiveMemberModel.getMembersList(AdapterDashBoard.this, new HashMap<String, String>(), 0, false);
            }
            if (i == 2) {
                getHighCourtLoader().start();
                JudgesModel.getJudges(AdapterDashBoard.this, null, 0, false);
            }
            if (i == 3) {
                getHighCourtLoader().start();
                NotificationModel.getNotificationList(AdapterDashBoard.this);
            }
            if (i == 4) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url",displayboard_url);
                context.startActivity(intent);
            }
            if (i == 5) {
                getHighCourtLoader().start();
                HolidaysModel.getHolidays(AdapterDashBoard.this, null);
            }
            if (i == 6) {
                getHighCourtLoader().start();
                RosterModel.getRoster(AdapterDashBoard.this, null, 1, false);
            }
            if (i == 7) {
                getHighCourtLoader().start();
                CaseLawModel.getCaseLaw(AdapterDashBoard.this, 1);
            }if (i == 8) {
               Intent intent = new Intent(context, AchievementActivity.class);
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
        getHighCourtLoader().stop();
        if(notificationModel != null) {
            HighCourtApplication.setNotifcationList(notificationModel.getNotificationses());
            context.startActivity(new Intent(context, NoificationActivity.class));
        }
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

    @Override
    public void onHolidaysSuccess(HolidaysModel holidaysModel) {
        getHighCourtLoader().stop();
        HighCourtApplication.setHolidaysModel(holidaysModel);
        context.startActivity(new Intent(context, CalenderActivity.class));
    }

    @Override
    public void onHolidaysFailur() {
        getHighCourtLoader().stop();
    }

    @Override
    public void onHolidaysFailur(Throwable t) {
        getHighCourtLoader().stop();
        ToastHelper.showToast(t.getMessage(), getContext());
    }

    @Override
    public void onJudgesSuccess(JudgesModel judgesModel) {
        getHighCourtLoader().stop();
        HighCourtApplication.setJudgesModel(judgesModel);
        getContext().startActivity(new Intent(getContext(), HonbleJudgesActivity.class));
    }

    @Override
    public void onJudgesFailur(Throwable t) {
        getHighCourtLoader().stop();
        ToastHelper.showToast(t.getMessage(), getContext());
    }

    @Override
    public void onJudgesSearch(JudgesModel judgesModel) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onCaseLawSuccess(CaseLawModel caseLawModel) {
        getHighCourtLoader().stop();
        HighCourtApplication.setCaseLawModel(caseLawModel);
        getContext().startActivity(new Intent(context, CaseLawActivity.class));
    }

    @Override
    public void onCaseLawFailur(Throwable t) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onRosterSuccess(RosterModel rosterModel) {
        getHighCourtLoader().stop();
        if(rosterModel != null){
            HighCourtApplication.setRosterModel(rosterModel);
            context.startActivity(new Intent(context, RosterActivity.class));
        }
    }

    @Override
    public void onRosterSearch(RosterModel rosterModel) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onRosterFailur(Throwable t) {
        getHighCourtLoader().stop();
    }

    @Override
    public void onRosterFailur() {
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

