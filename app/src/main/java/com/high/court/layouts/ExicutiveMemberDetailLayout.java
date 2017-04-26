package com.high.court.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.high.court.R;
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.UserHelper;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 4/26/2017.
 */

public class ExicutiveMemberDetailLayout extends LinearLayout {


    CircleImageView profilePicShow;

    public ExicutiveMemberDetailLayout(Context context) {
        super(context);
    }

    public ExicutiveMemberDetailLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExicutiveMemberDetailLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
    }

    private void init() {
        setProfilePicShow((CircleImageView) findViewById(R.id.quick_start_cropped_image));
    }

    public CircleImageView getProfilePicShow() {
        return profilePicShow;
    }

    public ExicutiveMemberDetailLayout setProfilePicShow(CircleImageView profilePicShow) {
        if(profilePicShow != null) ImageHelper.loadImage(UserHelper.getAppUserProfilePic(), profilePicShow);
        this.profilePicShow = profilePicShow;
        return this;
    }
}
