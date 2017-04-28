package com.high.court.layouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.helpers.UserHelper;


/**
 * Created by admin on 4/26/2017.
 */

public class SideProfileDrawer extends DrawerLayout implements View.OnClickListener {

    ImageView profileImageView;
    ImageView icon_profile, icon_paymydues, icon_changepassword, icon_logout;
    TextView profileNameText, myProfile_text, payMyDuesText, chnagePassword, side_logouttext;
    LinearLayout lmyprofile_row, paymudues_row, changepassword_row, logout_row, logoutRow;

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

    public TextView getSide_logouttext() {
        return side_logouttext;
    }

    public void setSide_logouttext(TextView side_logouttext) {
        this.side_logouttext = side_logouttext;
    }

    void init(){
        setInitiate(true);
        setProfileNameText((TextView) findViewById(R.id.side_profile_name))
        .setProfileImageView((ImageView) findViewById(R.id.side_profile_pic))
        .setMyProfile_text((TextView) findViewById(R.id.side_my_profile))
        .setPayMyDuesText((TextView) findViewById(R.id.side_my_pay_dues))
        .setChnagePassword((TextView) findViewById(R.id.side_change_password))
        .setSide_logouttext((TextView) findViewById(R.id.side_logouttext));
    //    .setLogoutText((LinearLayout) findViewById(R.id.logout_row));


        setIcon_profile((ImageView) findViewById(R.id.icon_profile));
        setIcon_paymydues((ImageView) findViewById(R.id.icon_paymydues));
        setIcon_changepassword((ImageView) findViewById(R.id.icon_changepassword));
        setIcon_logout((ImageView) findViewById(R.id.icon_logout));

        setLmyprofile_row((LinearLayout) findViewById(R.id.myprofile_row));
        setPaymudues_row((LinearLayout) findViewById(R.id.paymudues_row));
        setChangepassword_row((LinearLayout) findViewById(R.id.changepassword_row));
        setLogout_row((LinearLayout) findViewById(R.id.logout_row));


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        if(!isInitiate()) init();
    }

    public ImageView getProfileImageView() {
        return profileImageView;
    }

    public SideProfileDrawer setProfileImageView(ImageView profileImageView) {
        if (profileImageView != null)
            ImageHelper.loadImage(UserHelper.getAppUserProfilePic(), profileImageView);
        this.profileImageView = profileImageView;
        return this;
    }

    public TextView getProfileNameText() {
        return profileNameText;
    }

    public SideProfileDrawer setProfileNameText(TextView profileNameText) {
        if (profileNameText != null) {
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
      //  if (myProfile_text != null) myProfile_text.setOnClickListener(this);
        this.myProfile_text = myProfile_text;
        return this;
    }

//    public TextView getSide_logouttext() {
//        return side_logouttext;
//    }

//    public void setSide_logouttext(TextView side_logouttext) {
//        this.side_logouttext = side_logouttext;
//    }

    public TextView getPayMyDuesText() {
        return payMyDuesText;
    }

    public SideProfileDrawer setPayMyDuesText(TextView payMyDuesText) {
      //  if (payMyDuesText != null) payMyDuesText.setOnClickListener(this);
        this.payMyDuesText = payMyDuesText;
        return this;
    }

    public TextView getChnagePassword() {
        return chnagePassword;
    }

    public SideProfileDrawer setChnagePassword(TextView chnagePassword) {
    //    if (chnagePassword != null) chnagePassword.setOnClickListener(this);
        this.chnagePassword = chnagePassword;

        return this;
    }


    public ImageView getIcon_profile() {
        return icon_profile;
    }

    public void setIcon_profile(ImageView icon_profile) {
        this.icon_profile = icon_profile;
    }


    public ImageView getIcon_paymydues() {
        return icon_paymydues;
    }

    public void setIcon_paymydues(ImageView icon_paymydues) {
        this.icon_paymydues = icon_paymydues;
    }

    public ImageView getIcon_changepassword() {
        return icon_changepassword;
    }

    public void setIcon_changepassword(ImageView icon_changepassword) {
        this.icon_changepassword = icon_changepassword;
    }

    public ImageView getIcon_logout() {
        return icon_logout;
    }

    public void setIcon_logout(ImageView icon_logout) {
        this.icon_logout = icon_logout;
    }

    public LinearLayout getLogoutText() {
        return logoutRow;
    }

    public LinearLayout getLmyprofile_row() {
        return lmyprofile_row;
    }

    public void setLmyprofile_row(LinearLayout lmyprofile_row) {
        if (lmyprofile_row != null) lmyprofile_row.setOnClickListener(this);
        this.lmyprofile_row = lmyprofile_row;
    }

    public LinearLayout getPaymudues_row() {
        return paymudues_row;
    }

    public void setPaymudues_row(LinearLayout paymudues_row) {
        if (paymudues_row != null) paymudues_row.setOnClickListener(this);
        this.paymudues_row = paymudues_row;
    }

    public LinearLayout getChangepassword_row() {
        return changepassword_row;
    }

    public void setChangepassword_row(LinearLayout changepassword_row) {
        if (changepassword_row != null) changepassword_row.setOnClickListener(this);
        this.changepassword_row = changepassword_row;
    }

    public LinearLayout getLogout_row() {
        return logout_row;
    }

    public void setLogout_row(LinearLayout logout_row) {
        if (logout_row != null) logout_row.setOnClickListener(this);
        this.logout_row = logout_row;
    }

    public SideProfileDrawer setLogoutText(LinearLayout logoutRow) {
        if (logoutRow != null) logoutRow.setOnClickListener(this);
        this.logoutRow = logoutRow;
        return this;
    }

    public void onClickMyProfile() {
        getHighCourtActivity().startActivity(new Intent(getContext(), ExicutiveMemberDetail.class));
        select_MyProfile();
    }


    HighCourtActivity getHighCourtActivity() {
        return (HighCourtActivity) getContext();
    }

    void onClickMyDues() {
        getHighCourtActivity().startActivity(new Intent(getContext(), CommingSoonActivity.class));
        select_PayMyDues();
    }

    void onClickChangePassword(){
        getHighCourtActivity().startActivity(new Intent(getContext(), ChangePassword.class));
        select_ChangePass();
    }

    void onClickLogout() {
//        if (UserHelper.logout()) {
//            getHighCourtActivity().startActivity(new Intent(getContext(), DashboardActivity.class));
//        } else {
//            ToastHelper.showLogoutFailuer(getContext());
//        }
        select_Logout();

    }


    void select_MyProfile() {
        getMyProfile_text().setTextColor(getContext().getColor(R.color.clr_white));
        getLmyprofile_row().setBackgroundResource(R.color.colorPrimary);
        getIcon_profile().setColorFilter(getContext().getColor(R.color.clr_white));

        getPayMyDuesText().setTextColor(getContext().getColor(R.color.clr_black));
        getPaymudues_row().setBackgroundResource(R.color.clr_white);
        getIcon_paymydues().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getChnagePassword().setTextColor(getContext().getColor(R.color.clr_black));
        getChangepassword_row().setBackgroundResource(R.color.clr_white);
        getIcon_changepassword().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getSide_logouttext().setTextColor(getContext().getColor(R.color.clr_black));
        getLogout_row().setBackgroundResource(R.color.clr_white);
        getIcon_logout().setColorFilter(getContext().getColor(R.color.colorPrimary));
    }

    void select_PayMyDues() {
        getMyProfile_text().setTextColor(getContext().getColor(R.color.clr_black));
        getLmyprofile_row().setBackgroundResource(R.color.clr_white);
        getIcon_profile().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getPayMyDuesText().setTextColor(getContext().getColor(R.color.clr_white));
        getPaymudues_row().setBackgroundResource(R.color.colorPrimary);
        getIcon_paymydues().setColorFilter(getContext().getColor(R.color.clr_white));

        getChnagePassword().setTextColor(getContext().getColor(R.color.clr_black));
        getChangepassword_row().setBackgroundResource(R.color.clr_white);
        getIcon_changepassword().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getSide_logouttext().setTextColor(getContext().getColor(R.color.clr_black));
        getLogout_row().setBackgroundResource(R.color.clr_white);
        getIcon_logout().setColorFilter(getContext().getColor(R.color.colorPrimary));
    }

    void select_ChangePass() {
        getMyProfile_text().setTextColor(getContext().getColor(R.color.clr_black));
        getLmyprofile_row().setBackgroundResource(R.color.clr_white);
        getIcon_profile().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getPayMyDuesText().setTextColor(getContext().getColor(R.color.clr_black));
        getPaymudues_row().setBackgroundResource(R.color.clr_white);
        getIcon_paymydues().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getChnagePassword().setTextColor(getContext().getColor(R.color.clr_white));
        getChangepassword_row().setBackgroundResource(R.color.colorPrimary);
        getIcon_changepassword().setColorFilter(getContext().getColor(R.color.clr_white));

        getSide_logouttext().setTextColor(getContext().getColor(R.color.clr_black));
        getLogout_row().setBackgroundResource(R.color.clr_white);
        getIcon_logout().setColorFilter(getContext().getColor(R.color.colorPrimary));
    }

    void select_Logout() {
        getMyProfile_text().setTextColor(getContext().getColor(R.color.clr_black));
        getLmyprofile_row().setBackgroundResource(R.color.clr_white);
        getIcon_profile().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getPayMyDuesText().setTextColor(getContext().getColor(R.color.clr_black));
        getPaymudues_row().setBackgroundResource(R.color.clr_white);
        getIcon_paymydues().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getChnagePassword().setTextColor(getContext().getColor(R.color.clr_black));
        getChangepassword_row().setBackgroundResource(R.color.clr_white);
        getIcon_changepassword().setColorFilter(getContext().getColor(R.color.colorPrimary));

        getSide_logouttext().setTextColor(getContext().getColor(R.color.clr_white));
        getLogout_row().setBackgroundResource(R.color.colorPrimary);
        getIcon_logout().setColorFilter(getContext().getColor(R.color.clr_white));
    }

    @Override
    public void onClick(View v) {
        if (getLmyprofile_row().getId() == v.getId()) {
            onClickMyProfile();
        } else if (getPaymudues_row().getId() == v.getId()) {
            onClickMyDues();
        } else if (getChangepassword_row().getId() == v.getId()) {
            onClickChangePassword();
        } else if (getLogout_row().getId() == v.getId()) {
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