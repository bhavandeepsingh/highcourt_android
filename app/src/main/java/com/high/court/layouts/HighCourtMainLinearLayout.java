package com.high.court.layouts;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.high.court.activities.HighCourtActivity;
import com.high.court.helpers.HighCourtLoader;

/**
 * Created by Akshit on 27/04/2017.
 */

public class HighCourtMainLinearLayout extends LinearLayout {

    HighCourtLoader highCourtLoader;

    boolean initiate = false;

    public HighCourtMainLinearLayout(@NonNull Context context) {
        super(context);
    }

    public HighCourtMainLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HighCourtMainLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    HighCourtActivity getHighCourtActivity(){
        return (HighCourtActivity) getContext();
    }

    HighCourtLoader getHighCourtLoader(){
        return (highCourtLoader == null) ? highCourtLoader = HighCourtLoader.init(getContext()): highCourtLoader;
    }

    public boolean isInitiate() {
        return initiate;
    }

    public void setInitiate(boolean initiate) {
        this.initiate = initiate;
    }

    public void init() {
        setInitiate(true);
    }

}
