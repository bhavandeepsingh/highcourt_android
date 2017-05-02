package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.ExicutiveMemberDetail;
import com.high.court.helpers.DialerHelper;
import com.high.court.helpers.ImageHelper;
import com.high.court.http.models.ProfileModel;

import java.util.List;


public class AdapterExicutiveComm extends RecyclerView.Adapter<AdapterExicutiveComm.ViewHolder> {

    Context context;
    String[] get_judgesnamelist;
    String[] get_courtroomlist;

    List<ProfileModel> profileModelList;

    public AdapterExicutiveComm(Context ctx, String[] judgesnamelist, String[] courtroomlist) {
        super();
        get_judgesnamelist = judgesnamelist;
        get_courtroomlist = courtroomlist;

        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_exicutive_comm, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(viewHolder.exicutive_name != null) viewHolder.exicutive_name.setText(getProfileModelList().get(i).getName());
        if(viewHolder.visrpresident != null) viewHolder.visrpresident.setText(getProfileModelList().get(i).getProfile());
        if(viewHolder.ponenumber_val != null) viewHolder.ponenumber_val.setText(getProfileModelList().get(i).getMobile());
        if(viewHolder.profile_image != null) ImageHelper.loadImage(getProfileModelList().get(i).getProfile_pic(), viewHolder.profile_image);

        if(viewHolder.rowview != null) viewHolder.rowview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExicutiveMemberDetail.class);
                intent.putExtra(ExicutiveMemberDetail.PROFILE_INDEX_KEY, String.valueOf(i));
                context.startActivity(intent);
            }
        });

        viewHolder.phonelayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DialerHelper.dial(context, getProfileModelList().get(i).getMobile());
            }
        });

    }

    @Override
    public int getItemCount() {
        return getProfileModelList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rowview;

        TextView exicutive_name, visrpresident, ponenumber_val;
        ImageView profile_image;
        LinearLayout phonelayer;

        public ViewHolder(View itemView) {
            super(itemView);
            rowview = (LinearLayout) itemView.findViewById(R.id.rowview);
            exicutive_name = (TextView) rowview.findViewById(R.id.exicutive_name);
            visrpresident = (TextView) rowview.findViewById(R.id.visrpresident);
            ponenumber_val = (TextView) rowview.findViewById(R.id.ponenumber_val);
            profile_image = (ImageView) rowview.findViewById(R.id.profile_image);
            phonelayer = (LinearLayout) rowview.findViewById(R.id.phonelayer);
        }
    }

    public List<ProfileModel> getProfileModelList() {
        return (profileModelList == null)? HighCourtApplication.getProfileModels(): profileModelList;
    }

    public void setProfileModelList(List<ProfileModel> profileModelList) {
        this.profileModelList = profileModelList;
    }

}

