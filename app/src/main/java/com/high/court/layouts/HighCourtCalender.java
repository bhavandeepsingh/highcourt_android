package com.high.court.layouts;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.CalendarView;

/**
 * Created by Akshit on 04/05/2017.
 */

public class HighCourtCalender extends CalendarView {

    public HighCourtCalender(@NonNull Context context) {
        super(context);
    }

    public HighCourtCalender(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HighCourtCalender(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HighCourtCalender(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
