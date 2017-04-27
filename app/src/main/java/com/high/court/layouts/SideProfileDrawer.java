package com.high.court.layouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.activities.ChangePassword;
import com.high.court.activities.CommingSoonActivity;
import com.high.court.activities.DashboardActivity;
import com.high.court.activities.ExicutiveMemberDetail;
import com.high.court.activities.HighCourtActivity;
import com.high.court.activities.ProfileActivity2;
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.helpers.UserHelper;


/**
 * Created by admin on 4/26/2017.
 */

public class SideProfileDrawer extends DrawerLayout implements View.OnClickListener{

    ImageView profileImageView;

    TextView profileNameText, myProfile_text, payMyDuesText, chnagePassword;
    LinearLayout logoutRow;

    boolean initiate = false;

    public SideProfileDrawer(Context context) {
        super(context);
    }

    public SideProfileDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideProfileDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void init(){
        setInitiate(true);
        setProfileNameText((TextView) findViewById(R.id.side_profile_name))
        .setProfileImageView((ImageView) findViewById(R.id.side_profile_pic))
        .setMyProfile_text((TextView) findViewById(R.id.side_my_profile))
        .setPayMyDuesText((TextView) findViewById(R.id.side_my_pay_dues))
        .setChnagePassword((TextView) findViewById(R.id.side_change_password))
        .setLogoutText((LinearLayout) findViewById(R.id.logout_row));
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        if(!isInitiate()) init();
    }

    public ImageView getProfileImageView() {
        return profileImageView;
    }

    public SideProfileDrawer setProfileImageView(ImageView profileImageView) {
        if(profileImageView != null) ImageHelper.loadImage(UserHelper.getAppUserProfilePic(), profileImageView);
        this.profileImageView = profileImageView;
        return this;
    }

    public TextView getProfileNameText() {
        return profileNameText;
    }

    public SideProfileDrawer setProfileNameText(TextView profileNameText) {
        if(profileNameText != null){
            profileNameText.setOnClickListener(this);
            profileNameText.setText(UserHelper.getAppUserName());
        }
        this.profileNameText = profileNameText;
        return this;
    }

    public TextView getMyProfile_text() {
        return myProfile_text;
    }

    public SideProfileDrawer setMyProfile_text(TextView myProfile_text) {
        if(myProfile_text != null) myProfile_text.setOnClickListener(this);
        this.myProfile_text = myProfile_text;
        return this;
    }

    public TextView getPayMyDuesText() {
        return payMyDuesText;
    }

    public SideProfileDrawer setPayMyDuesText(TextView payMyDuesText) {
        if(payMyDuesText != null) payMyDuesText.setOnClickListener(this);
        this.payMyDuesText = payMyDuesText;
        return this;
    }

    public TextView getChnagePassword() {
        return chnagePassword;
    }

    public SideProfileDrawer setChnagePassword(TextView chnagePassword) {
        if(chnagePassword != null) chnagePassword.setOnClickListener(this);
        this.chnagePassword = chnagePassword;
        return this;
    }

    public LinearLayout getLogoutText() {
        return logoutRow;
    }

    public SideProfileDrawer setLogoutText(LinearLayout logoutRow) {
        if(logoutRow != null) logoutRow.setOnClickListener(this);
        this.logoutRow = logoutRow;
        return this;
    }

    public void onClickMyProfile(){
        getHighCourtActivity().startActivity(new Intent(getContext(), ExicutiveMemberDetail.class));
    }


    HighCourtActivity getHighCourtActivity(){
        return (HighCourtActivity) getContext();
    }

    void onClickMyDues(){
        getHighCourtActivity().startActivity(new Intent(getContext(), CommingSoonActivity.class));
    }

    void onClickChangePassword(){
        getHighCourtActivity().startActivity(new Intent(getContext(), ChangePassword.class));
    }

    void onClickLogout(){
        if(UserHelper.logout()) {
            getHighCourtActivity().startActivity(new Intent(getContext(), DashboardActivity.class));
        }else{
            ToastHelper.showLogoutFailuer(getContext());
        }
    }

    @Override
    public void onClick(View v) {

        if(getMyProfile_text().getId() == v.getId()){
            onClickMyProfile();
        }else if(getPayMyDuesText().getId() == v.getId()){
            onClickMyDues();
        }else if(getChnagePassword().getId() == v.getId()){
            onClickChangePassword();
        }else if(getLogoutText().getId() == v.getId()){
            onClickLogout();
        }
    }

    public boolean isInitiate() {
        return initiate;
    }

    public void setInitiate(boolean initiate) {
        this.initiate = initiate;
    }
}