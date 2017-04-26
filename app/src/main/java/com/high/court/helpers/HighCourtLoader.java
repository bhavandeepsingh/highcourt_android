package com.high.court.helpers;

import android.app.ProgressDialog;
import android.content.Context;

import com.high.court.HighCourtApplication;

/**
 * Created by Akshit on 26/04/2017.
 */

public class HighCourtLoader extends ProgressDialog {

    static HighCourtLoader highCourtLoader;

    public HighCourtLoader(Context context) {
        super(context);
    }

    public static HighCourtLoader init(Context context) {
        highCourtLoader = new HighCourtLoader(context);
        highCourtLoader.setProgressStyle(highCourtLoader.STYLE_SPINNER);
        highCourtLoader.setCancelable(false);
        highCourtLoader.setMessage("Loading...");
        return highCourtLoader;
    }

    public static void init(Context context, String msg) {
        highCourtLoader = new HighCourtLoader(context);
        highCourtLoader.setMessage(msg);
    }

    public HighCourtLoader start() {
        highCourtLoader.show();
        return this;
    }

    public void stop() {
        highCourtLoader.hide();
    }

}