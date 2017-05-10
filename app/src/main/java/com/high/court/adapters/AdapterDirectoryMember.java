package com.high.court.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.ExicutiveMemberDetail;
import com.high.court.activities.HighCourtActivity;
import com.high.court.helpers.DialerHelper;
import com.high.court.helpers.ImageHelper;
import com.high.court.http.models.ProfileModel;

import java.util.List;

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
        if(viewHolder.mobile_phone!= null){
            viewHolder.mobile_phone.setText(getProfileModelList().get(i).getMobile());
            viewHolder.mobile_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    phoneCallDialog("21212112","32323322","34555555");


                  //  DialerHelper.dial(context, getProfileModelList().get(i).getMobile());


                }
            });
        }
        if(viewHolder.landline_no!= null){
            viewHolder.landline_no.setText(getProfileModelList().get(i).getLandline());
            viewHolder.landline_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, getProfileModelList().get(i).getLandline());
                }
            });
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ExicutiveMemberDetail.class).putExtra(ExicutiveMemberDetail.PROFILE_INDEX_KEY, String.valueOf(i)));
            }
        });
    }

    @Override
    public int getItemCount() {
        int list_size = getProfileModelList().size();
        if(list_size <= 0) showNoRecords();
        else hideNoRecords();
        return list_size;
    }

    private void hideNoRecords() {
        if(getNoRecordsFounds() != null){
            getNoRecordsFounds().setVisibility(View.GONE);
        }
    }

    private void showNoRecords() {
        if(getNoRecordsFounds() != null){
            getNoRecordsFounds().setVisibility(View.VISIBLE);
        }
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

    public RelativeLayout getNoRecordsFounds(){
        return  (RelativeLayout) ((HighCourtActivity) context).findViewById(R.id.no_records_found);
    }

    void phoneCallDialog(final String phonenum1, final String phonenum2, final String phonenum3) {
        if (phonenum1.length() != 0 && phonenum2.length() == 0 && phonenum3.length() == 0) {
            DialerHelper.dial(context, phonenum1);
        }
        else {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_phone);
            TextView ponenumber_val1, ponenumber_val2, ponenumber_val3;
            LinearLayout phonelayer1, phonelayer2, phonelayer3;
            Button callButton1, callButton2, callButton3;

            phonelayer1 = (LinearLayout) dialog.findViewById(R.id.phonelayer);
            phonelayer2 = (LinearLayout) dialog.findViewById(R.id.phonelayer2);
            phonelayer3 = (LinearLayout) dialog.findViewById(R.id.phonelayer3);
            ponenumber_val1 = (TextView) dialog.findViewById(R.id.ponenumber_val);
            ponenumber_val2 = (TextView) dialog.findViewById(R.id.ponenumber_val2);
            ponenumber_val3 = (TextView) dialog.findViewById(R.id.ponenumber_val3);
            callButton1 = (Button) dialog.findViewById(R.id.call1);
            callButton2 = (Button) dialog.findViewById(R.id.call2);
            callButton3 = (Button) dialog.findViewById(R.id.call3);

            if (phonenum1.length() != 0 && phonenum2.length() != 0 && phonenum3.length() == 0) {
                phonelayer1.setVisibility(View.VISIBLE);
                phonelayer2.setVisibility(View.VISIBLE);
                phonelayer3.setVisibility(View.GONE);
            } else {
                phonelayer1.setVisibility(View.VISIBLE);
                phonelayer2.setVisibility(View.VISIBLE);
                phonelayer3.setVisibility(View.VISIBLE);
            }
            ponenumber_val1.setText(phonenum1);
            ponenumber_val2.setText(phonenum2);
            ponenumber_val3.setText(phonenum3);

            callButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, phonenum1);
                    dialog.dismiss();
                }
            });
            callButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, phonenum2);
                    dialog.dismiss();
                }
            });
            callButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, phonenum3);
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }



}

