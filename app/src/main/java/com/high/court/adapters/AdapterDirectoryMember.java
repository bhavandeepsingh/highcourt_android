package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.ExicutiveMemberDetail;
import com.high.court.activities.ProfileActivity2;
import com.high.court.helpers.ImageHelper;
import com.high.court.http.models.ProfileModel;

import org.w3c.dom.Text;

import java.util.List;
import java.util.StringTokenizer;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterDirectoryMember extends RecyclerView.Adapter<AdapterDirectoryMember.ViewHolder> {

    Context context;
    List<ProfileModel> profileModelList;

    public AdapterDirectoryMember(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_directory_memm, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(viewHolder.profile_image!= null) ImageHelper.loadImage(getProfileModelList().get(i).getProfile_pic(), viewHolder.profile_image );
        if(viewHolder.profile_name!= null) viewHolder.profile_name.setText(getProfileModelList().get(i).getName());
        if(viewHolder.mobile_phone!= null) viewHolder.mobile_phone.setText(getProfileModelList().get(i).getMobile());
        if(viewHolder.landline_no!= null) viewHolder.landline_no.setText(getProfileModelList().get(i).getLandline());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ExicutiveMemberDetail.class).putExtra(ExicutiveMemberDetail.PROFILE_INDEX_KEY, String.valueOf(i)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getProfileModelList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image;
        TextView profile_name, mobile_phone, landline_no;
        public ViewHolder(View itemView) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            profile_name = (TextView) itemView.findViewById(R.id.profile_name);
            mobile_phone = (TextView) itemView.findViewById(R.id.mobile_phone);
            landline_no = (TextView) itemView.findViewById(R.id.landline_no);
        }
    }


    public List<ProfileModel> getProfileModelList() {
        if(profileModelList == null) profileModelList = HighCourtApplication.getProfileModels();
        return profileModelList;
    }

    public void setProfileModelList(List<ProfileModel> profileModelList) {
        this.profileModelList = profileModelList;
    }
}

