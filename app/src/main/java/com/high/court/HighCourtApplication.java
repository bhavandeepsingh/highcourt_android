package com.high.court;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.high.court.activities.HighCourtActivity;
import com.high.court.helpers.UILApplication;

/**
 * Created by gurpreetsingh on 24/04/17.
 */

public class HighCourtApplication extends UILApplication {


    static HighCourtApplication highCourtApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        adjustFontScale(getApplicationContext(),getApplicationContext().getResources().getConfiguration());
        highCourtApplication = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // In some cases modifying newConfig leads to unexpected behavior,
        // so it's better to edit new instance.
        Configuration configuration = new Configuration(newConfig);
        adjustFontScale(getApplicationContext(), configuration);
    }

    public static void adjustFontScale(Context context, Configuration configuration) {
        if (configuration.fontScale != 1) {
            configuration.fontScale = 1;
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            context.getResources().updateConfiguration(configuration, metrics);
        }
    }

    public static HighCourtApplication getHighCourtApplication() {
        return highCourtApplication;
    }

    public void setHighCourtApplication(HighCourtApplication highCourtApplication) {
        this.highCourtApplication = highCourtApplication;
    }

    public static Context getHighCourtApplicationContext(){ return getHighCourtApplication().getApplicationContext(); }

    public static HighCourtActivity getHighCourtActivity(Context context){
        return (HighCourtActivity) context;
    }

}
